package com.apascualco.socialnetwork.persistence.data.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Data
@MappedSuperclass
public abstract class Audit {

    @NotNull
    @Column(name = "AUDIT_USER_UPDATE", nullable = false)
    private String auditUser;

    @NotNull
    @Column(name = "AUDIT_LAST_UPDATE", nullable = false)
    private Calendar auditLastUpdate;



}
