package com.lvv.users_manager.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "USER_ID")
    private Integer id;

    @Column(name = "USER_EMAIL", unique = true, updatable = false)
    private String email;

    @Column(name = "USER_NAME", unique = true, updatable = false)
    private String username;

    @Column(name = "USER_HASH_PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstname;

    @Column(name = "LAST_NAME")
    private String lastname;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthdate;

    @Column(name = "CREATION_DATE", insertable = false, updatable = false)
    private LocalDateTime creationDate;

    @Column( name = "LAST_UPDATED", insertable = false, updatable = false)
    private LocalDateTime lastUpdated;

    @ManyToOne
    @JoinColumn(
        name = "APP_ID", 
        referencedColumnName = "APP_ID", 
        insertable = true, updatable = false,
        foreignKey = @ForeignKey(name = "USERS_APPLICATIONS_FK")    
    )
    private Application application;

}