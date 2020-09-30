package com.wot.wotbackend.repositories;

import com.wot.wotbackend.documents.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



public interface PlayerRepository extends MongoRepository<Player, String> {

}
