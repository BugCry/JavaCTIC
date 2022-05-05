package com.pruebaCTIC.springboot.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource data;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/ListadoUsuarios").hasAuthority("administrador")
                .antMatchers("/NuevoUsuario").permitAll()//hasAuthority("administrador").authenticated()
                .antMatchers("/usuarios/**").hasAuthority("administrador") //solo modifica y realiza operaciones con usuarios el administrador
                .antMatchers("/AgregarDiagnostico").hasAnyAuthority("administrador", "medico")//.authenticated()
                .antMatchers("/ListadoConsultorios").hasAuthority("administrador")//.authenticated()
                .antMatchers("/consultorio/**").hasAuthority("administrador") //solo modifica y realiza operaciones con consultorios el administrador
                .antMatchers("/ListadoPaciente").hasAnyAuthority("paciente", "administrador") //solo enlista a pacientes
                .antMatchers("/ListadoCitas").hasAnyAuthority("administrador", "medico") //solo enlista a pacientes
                .antMatchers("/citas/**").hasAuthority("administrador") //solo admin puede crear citasw
                .antMatchers("/usuarios/nuevo").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                    .usernameParameter("email")
                    .defaultSuccessUrl("/")
                    //.permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }
}

