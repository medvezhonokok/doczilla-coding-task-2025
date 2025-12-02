package ru.medvezhonokok.doczilla.service;

import jakarta.transaction.Transactional;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.medvezhonokok.doczilla.model.FileUpload;
import ru.medvezhonokok.doczilla.model.User;
import ru.medvezhonokok.doczilla.repository.FileRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {
    public final static String UPLOADS_DIR = "uploads/";
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public void uploadFile(MultipartFile file, User author) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        FileUpload upload = new FileUpload();
        upload.setUser(author);
        upload.setHashedFileName(saveFileToDisk(file));

        fileRepository.save(upload);
    }

    public List<FileUpload> findAll() {
        return fileRepository.findAll();
    }

    private String saveFileToDisk(MultipartFile file) throws IOException {
        File dir = new File(UPLOADS_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String hashedFileName = generateRandomHash() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        Path pathToFile = Paths.get(UPLOADS_DIR, hashedFileName);
        Files.copy(file.getInputStream(), pathToFile, StandardCopyOption.REPLACE_EXISTING);

        return hashedFileName;
    }

    private String generateRandomHash() {
        String randomString = UUID.randomUUID().toString();

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(randomString.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return UUID.randomUUID().toString().replace("-", "");
        }
    }

    public File getFileForDownload(String hashedFileName) {
        fileRepository.updateLastDownloadTime(hashedFileName);
        return new File(UPLOADS_DIR + hashedFileName);
    }
}
