package com.pruebaCTIC.springboot.citas;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepositorioCitas extends CrudRepository<citas, Integer> {
    //@Query("SELECT u FROM usuario u WHERE u.email = ?1")
    //consultorio encontrarXEmail(String email);

    @Query("SELECT u FROM citas u WHERE u.idPaciente = 1")
    List<citas> listarXPaciente(Integer id);
}
