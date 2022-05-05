package com.pruebaCTIC.springboot.citas;

import com.pruebaCTIC.springboot.usuario.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiciosCitas {
    @Autowired
    private RepositorioCitas repo;

    public List<citas> listarTodo(){
        return (List<citas>) repo.findAll();
    }

    public List<citas> listarXPaciente(Integer id){
        return repo.listarXPaciente(id);
    }

    public void guardar(citas cita) {
        repo.save(cita);
    }

    public citas get(Integer id) throws UserNotFoundException {
        Optional<citas> resultado = repo.findById(id);
        if(resultado.isPresent()){
            return resultado.get();
        }
        throw new UserNotFoundException("No se ha encontrado el usuario " + id);
    }
}
