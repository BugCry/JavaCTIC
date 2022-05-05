package com.pruebaCTIC.springboot.consultorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepositorioConsultorio extends CrudRepository<consultorio, Integer> {

    //@Query("SELECT u FROM usuario u WHERE u.email = ?1")
    //consultorio encontrarXEmail(String email);
}
