package org.authetication.ecommerce.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "userdetail_table")
public class UserDtlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long userdetailid;
    @NotBlank
    @Column(unique = false,nullable = false)
    String address;

    @NotNull
    @Column(unique = false,nullable = false)
    Long pincode;

    @OneToOne
    @JoinColumn(name = "userid")
    private UserEntity user;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }
}
