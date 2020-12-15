package com.example.salvo.repository;

import com.example.salvo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource //para que reconozca a la interfaz como un repositorio
public interface PlayerRepository extends JpaRepository<Player, Long> { //parepository guarda varios metodos como borrar o buscar
    Player findByEmail(@Param("email") String email);
}
