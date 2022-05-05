package com.pruebaCTIC.springboot.usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<usuario, Integer> {

    @Query("SELECT u FROM usuario u WHERE u.email = ?1")
    usuario encontrarXEmail(String email);

    @Query("SELECT u FROM usuario u WHERE u.tpUsuarioId = 2") //al parecer es el nombre de las entidades,mas no el de la base de datos lol
    List<usuario> listarMedicos();

    @Query("SELECT u FROM usuario u WHERE u.tpUsuarioId = 3")
    List<usuario> listarPacientes();
}
