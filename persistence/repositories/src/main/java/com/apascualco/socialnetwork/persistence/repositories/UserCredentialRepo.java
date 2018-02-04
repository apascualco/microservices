package com.apascualco.socialnetwork.persistence.repositories;


import com.apascualco.socialnetwork.persistence.data.model.entities.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepo extends JpaRepository<UserCredential, Long>{
}
