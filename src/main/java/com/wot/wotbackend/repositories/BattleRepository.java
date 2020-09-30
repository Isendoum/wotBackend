package com.wot.wotbackend.repositories;

import com.wot.wotbackend.documents.Battle;
import com.wot.wotbackend.documents.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BattleRepository extends MongoRepository<Battle,String> {
    Battle findBattleByPlayer(Player player);
}
