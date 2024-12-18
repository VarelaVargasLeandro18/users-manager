package com.lvv.users_manager.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "APPLICATIONS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Application {

    @Id
    @Column(name = "APP_ID")
    private String uuid;

    @Column(name = "APP_NAME")
    private String name;

}