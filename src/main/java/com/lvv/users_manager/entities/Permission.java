package com.lvv.users_manager.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PERMISSIONS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Permission {

    @Id
    @Column(name = "PERMISSION_ID")
    private Integer id;

    @Column(name = "PERMISSION_NAME")
    private String name;

    @ManyToOne
    @JoinColumn(
        name = "ROLE_ID", 
        referencedColumnName = "ROLE_ID", 
        insertable = false, updatable = false,
        foreignKey = @ForeignKey(name = "FK_PERMISSIONS_ROLES")    
    )
    private Role role;
}