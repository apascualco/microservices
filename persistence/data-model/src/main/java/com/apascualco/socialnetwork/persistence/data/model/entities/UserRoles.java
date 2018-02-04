package com.apascualco.socialnetwork.persistence.data.model.entities;

import com.apascualco.socialnetwork.persistence.data.model.Audit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "USER_ROLE")
@EqualsAndHashCode( of = "userCredential", callSuper = false)
public class UserRoles extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @NotNull
    @Column(name = "ROL", nullable = false)
    private String ROL;

    @ManyToMany(mappedBy = "userRoles")
    @ElementCollection(targetClass=UserCredential.class)
    private List<UserCredential> userCredentials;



}

