package com.lvv.users_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lvv.users_manager.entities.Application;

public interface ApplicationRepository extends JpaRepository<Application, String>{

}