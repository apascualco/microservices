package com.apascualco.socialnetwork.persistence.data.model.entities;

import com.apascualco.socialnetwork.persistence.data.model.Audit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Locale;

@Data
@Entity
@Table(name = "USER_INFO")
@EqualsAndHashCode( of = "userCredential", callSuper = false)
public class UserInfo extends Audit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastname;

    @Column(name = "LANGUAJE", nullable = false)
    private Locale language;

    @Column(name = "CREATED", nullable = false)
    private Calendar created;

    @Column(name = "ACTIVE" , nullable = false)
    private boolean isActive;

    @NotNull
    @OneToOne (cascade=CascadeType.ALL)
    @JoinColumn(name="CREDENTIALS_ID", unique= true, nullable=false)
    private UserCredential userCredential;

}
