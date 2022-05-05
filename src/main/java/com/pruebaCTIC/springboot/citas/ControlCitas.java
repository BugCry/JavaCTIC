package com.pruebaCTIC.springboot.citas;

import com.pruebaCTIC.springboot.consultorio.ServiciosConsultorio;
import com.pruebaCTIC.springboot.consultorio.consultorio;
import com.pruebaCTIC.springboot.usuario.UserNotFoundException;
import com.pruebaCTIC.springboot.usuario.UserService;
import com.pruebaCTIC.springboot.usuario.usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ControlCitas {

    @Autowired
    private ServiciosCitas service;

    @Autowired
    private ServiciosConsultorio serviciosConsultorio;

    @Autowired
    private UserService serviciosUsu;

    @GetMapping("/ListadoCitas")
    public String mostrarListadoCitas(Model model){
        List<citas> ListadoCitas = service.listarTodo();
        model.addAttribute("ListadoCitas", ListadoCitas);

        return "Citas/ListadoCitas"; //debe ser el mismo nombre del archivo tamplate.html
    }

    @GetMapping("/citas/nuevo")
    public String nuevaCita(Model model){
        List<consultorio> ListaConsultorios = serviciosConsultorio.listarTodo();
        List<usuario> ListaMedicos = serviciosUsu.listarMedicos();
        List<usuario> ListaPacientes = serviciosUsu.listarPacientes();
        model.addAttribute("citas", new citas());
        model.addAttribute("consultorios", ListaConsultorios);
        model.addAttribute("medicos", ListaMedicos);
        model.addAttribute("pacientes", ListaPacientes);
        model.addAttribute("Titulo", "Crear nueva cita");
        model.addAttribute("accion", "Crear");
        return "Citas/NuevaCita";
    }

    @GetMapping("/citas/editar/{id}")
    public String modificarCita(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            citas cita = service.get(id);
            List<consultorio> ListaConsultorios = serviciosConsultorio.listarTodo();
            List<usuario> ListaMedicos = serviciosUsu.listarMedicos();
            List<usuario> ListaPacientes = serviciosUsu.listarPacientes();
            model.addAttribute("citas", cita);
            model.addAttribute("consultorios", ListaConsultorios);
            model.addAttribute("medicos", ListaMedicos);
            model.addAttribute("pacientes", ListaPacientes);
            model.addAttribute("Titulo", "Modificar cita: " + cita.getId());
            model.addAttribute("accion", "Modificar");
            return "Citas/ModificarCita";
        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message", "Cita no encontrada");
            return "redirect:/ListadoCitas";
        }
    }

    @GetMapping("/citas/diagnostico/{id}")
    public String diagnosticarCita(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            citas cita = service.get(id);
            model.addAttribute("citas", cita);
            return "Citas/DiagnosticarCita";
        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message", "Cita no encontrada");
            return "redirect:/ListadoCitas";
        }
    }

    @GetMapping("/citas/cancelar/{id}")
    public String cancelarCita(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            citas cita = service.get(id);
            model.addAttribute("citas", cita);
            return "Citas/CancelarCita";
        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message", "Cita no encontrada");
            return "redirect:/ListadoCitas";
        }
    }

    @PostMapping("/citas/crear")
    public String crearCita(citas cita, RedirectAttributes ra){
        //usu.setSegundoNombre("" + ((usu.getNombres().split(" ", 1).length > 0) ? usu.getNombres().split(" ", 1)[1] : "" ));
        //usu.setSegundoApellido("" + ((usu.getApellidos().split(" ", 1).length > 0) ? usu.getApellidos().split(" ", 1)[1] : "" ));
        String msg = cita.getId() != null ? "modificado" : "creado";

        service.guardar(cita);
        ra.addFlashAttribute("message", "Se ha " + msg + " la cita :D.");
        return "redirect:/ListadoCitas";
    }

    @PostMapping("/citas/diagnosticar")
    public String agregarDiagnosticoCita(citas cita, RedirectAttributes ra) {
        try{
            citas original = service.get(cita.getId());
            original.setDiagnostico(cita.getDiagnostico());
            original.setMedicamentos(cita.getMedicamentos());

            service.guardar(original);
            ra.addFlashAttribute("message", "Se ha diagnosticado la cita :D.");
            return "redirect:/ListadoCitas";
        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message", "Cita no encontrada");
            return "redirect:/ListadoCitas";
        }
    }

    @PostMapping("/citas/cancelar")
    public String justificarCancelacionCita(citas cita, RedirectAttributes ra) {
        try{
            citas original = service.get(cita.getId());
            original.setJustificacionCancelacion(cita.getJustificacionCancelacion());
            original.setEstado(false);

            service.guardar(original);
            ra.addFlashAttribute("message", "Se ha cancelado la cita :D.");
            return "redirect:/ListadoCitas";
        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message", "Cita no encontrada");
            return "redirect:/ListadoCitas";
        }
    }
}
