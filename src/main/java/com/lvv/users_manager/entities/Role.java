package com.lvv.users_manager.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ROLES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @Column(name = "ROLE_ID")
    private Integer id;

    @Column(name = "ROLE_NAME")
    private String name;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn(
        name = "APP_ID", 
        referencedColumnName = "APP_ID", 
        insertable = false, updatable = false,
        foreignKey = @ForeignKey(name = "FK_ROLES_APPLICATIONS")    
    )
    private Application application;

    @OneToMany
    @JoinColumn(
        name = "ROLE_ID", 
        referencedColumnName = "ROLE_ID", 
        insertable = false, updatable = false,
        foreignKey = @ForeignKey(name = "FK_PERMISSIONS_ROLES")    
    )
    private Set<Permission> permissions;

}