package com.pruebaCTIC.springboot.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CodificarContraseña {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "root123";
        String encode = encoder.encode(password);

        System.out.print(encode);
    }
}
