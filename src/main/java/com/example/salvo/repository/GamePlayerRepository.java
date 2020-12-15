package com.example.salvo.repository;

import com.example.salvo.model.GamePlayer;
import com.example.salvo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GamePlayerRepository extends JpaRepository<GamePlayer, Long> {
    Player findByid(@Param("id") long id);
}
