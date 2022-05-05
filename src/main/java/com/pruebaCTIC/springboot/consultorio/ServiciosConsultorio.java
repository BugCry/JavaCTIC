package com.pruebaCTIC.springboot.consultorio;

import com.pruebaCTIC.springboot.usuario.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiciosConsultorio {
    @Autowired
    private RepositorioConsultorio repo;

    public List<consultorio> listarTodo(){
        return (List<consultorio>) repo.findAll();
    }

    public void guardar(consultorio consu) {
        repo.save(consu);
    }

    public consultorio get(Integer id) throws UserNotFoundException {
        Optional<consultorio> resultado = repo.findById(id);
        if(resultado.isPresent()){
            return resultado.get();
        }
        throw new UserNotFoundException("No se ha encontrado el usuario " + id);
    }
}
