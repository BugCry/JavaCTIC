package com.pruebaCTIC.springboot.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        usuario usu = repo.encontrarXEmail(email);
        if(usu == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new CustomUserDetails(usu);
    }
}
