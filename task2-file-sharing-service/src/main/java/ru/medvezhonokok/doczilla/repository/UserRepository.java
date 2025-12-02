package ru.medvezhonokok.doczilla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.medvezhonokok.doczilla.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    boolean existsByLogin(String login);

    int countByLogin(String login);

    List<User> findAllByOrderByIdDesc();

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE users SET password_sha = SHA1(CONCAT('abfb33e9f6ccc', ?2, ?3)) WHERE id = ?1",
            nativeQuery = true
    )
    void updatePassword(long id, String login, String password);

    @Query(
            value = "SELECT * FROM users WHERE login = ?1 AND password_sha = SHA1(CONCAT('abfb33e9f6ccc', ?1, ?2))",
            nativeQuery = true
    )
    User findByLoginAndPassword(String login, String password);

    @Query(
            value = "SELECT * FROM users WHERE login = :login AND password_sha = SHA1(CONCAT('abfb33e9f6ccc', :login, :password))",
            nativeQuery = true
    )
    User findByLoginAndPasswordNamed(@Param("login") String login, @Param("password") String password);
}