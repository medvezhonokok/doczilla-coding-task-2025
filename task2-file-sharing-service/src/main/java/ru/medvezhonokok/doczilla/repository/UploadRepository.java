package ru.medvezhonokok.doczilla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.medvezhonokok.doczilla.model.Upload;

import java.util.List;

@Repository
public interface UploadRepository extends JpaRepository<Upload, Integer> {
    List<Upload> findByUserId(Long userId);

    List<Upload> findAll();
}
