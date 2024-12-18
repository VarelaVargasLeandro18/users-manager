package com.lvv.users_manager.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lvv.users_manager.entities.User;

public interface UsersRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Page<User> findAllByApplicationUuid(String uuid, PageRequest pageRequest);

}