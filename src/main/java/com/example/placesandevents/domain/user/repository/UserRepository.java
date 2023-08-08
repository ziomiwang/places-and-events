package com.example.placesandevents.domain.user.repository;

import com.example.placesandevents.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
    Optional<User> findByLoginAndPassword(String login, String password);
    Boolean existsUserByLogin(String login);
    List<User> findAllByIdIn(List<Long> ids);
}
