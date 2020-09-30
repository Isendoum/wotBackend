package com.wot.wotbackend.repositories;

import com.wot.wotbackend.documents.Player;
import com.wot.wotbackend.documents.WorldStructure;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorldStructureRepository extends MongoRepository<WorldStructure, String> {

}
