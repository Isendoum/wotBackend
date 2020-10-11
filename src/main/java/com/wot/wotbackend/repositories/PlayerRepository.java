package com.wot.wotbackend.repositories;

import com.wot.wotbackend.documents.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface PlayerRepository extends MongoRepository<Player, String> {

    Optional<Player> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
