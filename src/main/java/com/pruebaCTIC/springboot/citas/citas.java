package com.pruebaCTIC.springboot.citas;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "citas")
public class citas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "id_paciente")
    private Integer idPaciente;

    @Column(nullable = false, length = 150, name = "nom_paciente")
    private String nomPaciente;

    @Column(nullable = false, name = "id_medico")
    private Integer idMedico;

    @Column(nullable = false, length = 150, name = "nom_medico")
    private String nomMedico;

    @Column(nullable = false, name = "id_consultorio")
    private Integer idConsultorio;

    @Column(nullable = false, length = 150, name = "nom_consultorio")
    private String nomConsultorio;

    @Column(nullable = false, name = "fecha_cita")
    private Date fechaCita;

    @Column(nullable = false, length = 15, name = "hora_inicio")
    private String horaInicio;

    @Column(nullable = false, length = 15, name = "hora_fin")
    private String horaFin;

    @Column(length = 250)
    private String diagnostico;

    @Column(length = 150)
    private String medicamentos;

    @Column(length = 150, name = "justificacion_cancelacion")
    private String justificacionCancelacion;

    @Column(nullable = false)
    private boolean estado;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNomPaciente() {
        return nomPaciente;
    }

    public void setNomPaciente(String nomPaciente) {
        this.nomPaciente = nomPaciente;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public String getNomMedico() {
        return nomMedico;
    }

    public void setNomMedico(String nomMedico) {
        this.nomMedico = nomMedico;
    }

    public Integer getIdConsultorio() {
        return idConsultorio;
    }

    public void setIdConsultorio(Integer idConsultorio) {
        this.idConsultorio = idConsultorio;
    }

    public String getNomConsultorio() {
        return nomConsultorio;
    }

    public void setNomConsultorio(String nomConsultorio) {
        this.nomConsultorio = nomConsultorio;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getJustificacionCancelacion() {
        return justificacionCancelacion;
    }

    public void setJustificacionCancelacion(String justificacionCancelacion) {
        this.justificacionCancelacion = justificacionCancelacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
