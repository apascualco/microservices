package com.apascualco.socialnetwork.persistence.repositories;

import com.apascualco.socialnetwork.persistence.data.model.entities.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRoles, Long> {
}
