package com.lvv.users_manager.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USERS", uniqueConstraints = { @UniqueConstraint(name = "UK_CREATION_DATE", columnNames = "CREATION_DATE"), @UniqueConstraint(name = "UK_LAST_UPDATED", columnNames = "LAST_UPDATED") })
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

    @CreationTimestamp
    @Column(name = "CREATION_DATE", insertable = false, updatable = false)
    private LocalDateTime creationDate;

    @UpdateTimestamp
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

    @ManyToMany
    @JoinTable(
        name = "USER_ROLES",
        joinColumns = @JoinColumn(
            name = "USER_ID", 
            referencedColumnName = "USER_ID", 
            insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "FK_USER_ROLES_USERS")    
        ),
        inverseJoinColumns = @JoinColumn(
            name = "ROLE_ID", 
            referencedColumnName = "ROLE_ID", 
            insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "FK_USER_ROLES_ROLES")    
        )
    )
    private Set<Role> roles;

}