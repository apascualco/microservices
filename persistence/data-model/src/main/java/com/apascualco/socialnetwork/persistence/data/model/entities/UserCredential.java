package com.apascualco.socialnetwork.persistence.data.model.entities;

import com.apascualco.socialnetwork.persistence.data.model.Audit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "USER_CREDENTIAL")
@EqualsAndHashCode( of = "user", callSuper = false)
public class UserCredential extends Audit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;


    @NotNull(message = "USER_CREDENTIALS's email can't be null")
    @Column(name = "USER", nullable = false, unique = true)
    private String user;


    @NotNull(message = "USER_CREDENTIALS's password can't be null")
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "VERIFICATION_CODE")
    private String verificationCode;

    @NotNull(message = "USER_CREDENTIALS's userRoles can't be null")
    @OneToMany
    @JoinColumn(name="ROLE_ID")
    private List<UserRoles> userRoles;

}
