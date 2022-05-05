package com.pruebaCTIC.springboot.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository repo;

    public List<usuario> listarTodo(){
         return (List<usuario>) repo.findAll();
     }

    public List<usuario> listarMedicos(){ return repo.listarMedicos(); }

    public List<usuario> listarPacientes(){ return repo.listarPacientes(); }

    public void guardar(usuario usu) {
         repo.save(usu);
    }

    public usuario get(Integer id) throws UserNotFoundException {
        Optional<usuario> resultado = repo.findById(id);
        if(resultado.isPresent()){
            return resultado.get();
        }
        throw new UserNotFoundException("No se ha encontrado el usuario " + id);
    }
}
