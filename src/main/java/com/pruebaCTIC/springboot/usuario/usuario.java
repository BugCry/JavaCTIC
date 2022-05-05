package com.pruebaCTIC.springboot.usuario;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false, length = 50)
    private String Nombres;

    @Column(nullable = false, length = 50)
    private String Apellidos;

    @Column(length = 50, name = "segundo_nombre")
    private String SegundoNombre;

    @Column(length = 50, name = "segundo_apellido")
    private String SegundoApellido;

    @Column(nullable = false, length = 150, name = "nombre_completo")
    private String NombreCompleto;

    @Column(length = 15)
    private String telefono;

    @Column(nullable = false, length = 150)
    private String direccion;

    @Column(nullable = false, name = "tp_usuario_id")
    private byte tpUsuarioId;

    @Column(nullable = false)
    private boolean estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getSegundoNombre() {
        return SegundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        SegundoNombre = segundoNombre;
    }

    public String getSegundoApellido() {
        return SegundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        SegundoApellido = segundoApellido;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        NombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public byte getTpUsuarioId() { return tpUsuarioId; }

    public void setTpUsuarioId(byte tpUsuarioId) { this.tpUsuarioId = tpUsuarioId; }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", Nombres='" + Nombres + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                ", SegundoNombre='" + SegundoNombre + '\'' +
                ", SegundoApellido='" + SegundoApellido + '\'' +
                ", NombreCompleto='" + NombreCompleto + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", tpUsuarioId='" + tpUsuarioId + '\'' +
                ", estado='" + estado+ '\'' +
                '}';
    }
}
