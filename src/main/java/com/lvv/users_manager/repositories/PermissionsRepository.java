package com.lvv.users_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lvv.users_manager.entities.Permission;

public interface PermissionsRepository extends JpaRepository<Permission, Integer> {

}
