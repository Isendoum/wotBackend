package com.wot.wotbackend.repositories;

import com.wot.wotbackend.documents.ERole;
import com.wot.wotbackend.documents.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findByName(ERole name);
}
