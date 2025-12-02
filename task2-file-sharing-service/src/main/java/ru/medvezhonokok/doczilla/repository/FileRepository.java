package ru.medvezhonokok.doczilla.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.medvezhonokok.doczilla.model.FileUpload;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileUpload, Integer> {
    List<FileUpload> findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE file_uploads SET last_download_time = NOW() WHERE hashed_file_name = ?1",
            nativeQuery = true)
    void updateLastDownloadTime(String hashedFileName);
}
