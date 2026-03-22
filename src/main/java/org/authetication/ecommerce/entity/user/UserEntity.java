package org.authetication.ecommerce.entity.user;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import org.authetication.ecommerce.entity.roles.RolesEntity;

@Entity
@Table(name = "users_table")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userid;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false,unique = true)
    private String username;

    @NotBlank
     @Column(nullable = false,unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

     @ManyToOne
     @JoinColumn(name = "role_id")
     private RolesEntity role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserDtlEntity userdetail;



    public UserEntity() {}

    public UserEntity(String name, String email, String password, String username,RolesEntity role) {
        this.name     = name;
        this.email    = email;
        this.password = password;
        this.username = username;
         this.role = role;
    }


    public Long getuserid()                    { return this.userid; }
    public String getName()                { return name; }
    public void setName(String name)       { this.name = name; }
     public String getUsername()                { return username; }
    public void setUsername(String username)       { this.username = username; }
    public String getEmail()               { return email; }
    public void setEmail(String email)     { this.email = email; }
    public String getPassword()            { return password; }
    public void setPassword(String pw)     { this.password = pw; }
     public RolesEntity getRole()                { return role; }
     public void setRole(RolesEntity role)       { this.role = role; }

    public UserDtlEntity getUserdetail() {
        return userdetail;
    }
}
