package com.pruebaCTIC.springboot.usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private usuario usu;

    public CustomUserDetails(usuario usu) {
        this.usu = usu;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(usu.getTpUsuarioId() == 1 ? "administrador" : usu.getTpUsuarioId() == 2 ? "medico" : "paciente")); //pudo haber sido mas elegante si guardaba directamente el string, pero cuestiones de tiempo :D
        return authority;
    }

    @Override
    public String getPassword() {
        return usu.getPassword();
    }

    @Override
    public String getUsername() {
        return usu.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
