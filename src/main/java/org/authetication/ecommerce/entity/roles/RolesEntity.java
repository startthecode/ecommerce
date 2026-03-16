package org.authetication.ecommerce.entity.roles;


import jakarta.persistence.*;

@Entity
@Table(name = "role_table"
//        ,uniqueConstraints = @UniqueConstraint(columnNames = {"role_id","role"})
)
public class RolesEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int roleId;


    @Column(nullable = false,unique = true)
    private String role;


    public int getRoleId() {
        return roleId;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
