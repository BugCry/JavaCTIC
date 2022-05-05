package com.pruebaCTIC.springboot;

import com.pruebaCTIC.springboot.usuario.UserRepository;
import com.pruebaCTIC.springboot.usuario.usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNewUser(){
        usuario user = new usuario();
        user.setEmail("test@gmail.com");
        user.setPassword("root123");
        user.setNombres("Tito");
        user.setApellidos("Salas");
        user.setTelefono("3202933153");
        user.setDireccion("cll 21 sur #22-63");

        user.setSegundoNombre("andres");
        user.setSegundoApellido("casas");
        user.setNombreCompleto("tito andres salas casas");
        user.setTpUsuarioId((byte) 1);
        user.setEstado(true);

        usuario usuarioGuardado = repo.save(user);
        Assertions.assertThat(usuarioGuardado).isNotNull();
        Assertions.assertThat(usuarioGuardado.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<usuario> usuarios = repo.findAll();
        Assertions.assertThat(usuarios).hasSizeGreaterThan(0);

        for (usuario usu : usuarios){
            System.out.println(usu);
        }
    }

    @Test
    public void testUpdate(){
        Integer userId = 1;
        Optional<usuario> usuarioOptional = repo.findById(userId);
        usuario usu = usuarioOptional.get();
        usu.setPassword("holi");
        repo.save(usu);

        usuario updatedUsu = repo.findById(userId).get();
        Assertions.assertThat(updatedUsu.getPassword()).isEqualTo("holi");
    }

    @Test
    public void testGet(){
        Integer userId = 1;
        Optional<usuario> usuarioOptional = repo.findById(userId);
        Assertions.assertThat(usuarioOptional).isPresent();
        System.out.println(usuarioOptional.get());
    }

    @Test
    public void testDelete(){
        Integer userId = 2;
        repo.deleteById(userId);

        Optional<usuario> usuarioOptional = repo.findById(userId);
        Assertions.assertThat(usuarioOptional).isNotPresent();
    }
}
