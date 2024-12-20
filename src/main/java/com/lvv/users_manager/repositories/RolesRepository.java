package com.lvv.users_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lvv.users_manager.entities.Role;

public interface RolesRepository extends JpaRepository<Role, Integer> {

}
