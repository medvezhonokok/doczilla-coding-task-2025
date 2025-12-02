package ru.medvezhonokok.doczilla.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.medvezhonokok.doczilla.model.User;
import ru.medvezhonokok.doczilla.service.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class FilePage extends Page {
    private final FileService fileService;

    @Autowired
    public FilePage(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/upload")
    public String upload(HttpSession session, Model model) {
        User user = getUser(session);

        if (user == null) {
            setMessage(session, "You must be logged in");
            return "redirect:/enter";
        }

        return "FileUploadPage";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpSession session, Model model) {
        User user = getUser(session);

        if (user == null) {
            return "redirect:/enter";
        }

        try {
            fileService.uploadFile(file, user);
            setMessage(session, "File uploaded successfully!");
        } catch (Exception e) {
            setMessage(session, "Error: " + e.getMessage());
        }

        return "FileUploadPage";
    }

    @GetMapping("/files")
    public String files(HttpSession session, Model model) {
        User user = getUser(session);

        if (user == null) {
            setMessage(session, "You must be logged in");
            return "redirect:/enter";
        }

        model.addAttribute("files", fileService.findAll());

        return "FilesPage";
    }

    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        final File file = fileService.getFileForDownload(fileName);

        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("application/octet-stream");
        response.setContentLengthLong(file.length());
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + fileName + "\"");

        Files.copy(file.toPath(), response.getOutputStream());
    }
}