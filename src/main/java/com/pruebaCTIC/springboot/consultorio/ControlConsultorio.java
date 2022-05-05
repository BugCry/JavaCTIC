package com.pruebaCTIC.springboot.consultorio;

import com.pruebaCTIC.springboot.usuario.UserNotFoundException;
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
public class ControlConsultorio {
    @Autowired
    private ServiciosConsultorio service;

    @GetMapping("/ListadoConsultorios")
    public String mostrarListadoConsultorios(Model model){
        List<consultorio> ListadoConsultorios = service.listarTodo();
        model.addAttribute("ListadoConsultorios", ListadoConsultorios);

        return "Consultorio/ListadoConsultorios"; //debe ser el mismo nombre del archivo tamplate.html
    }

    @GetMapping("/consultorio/nuevo")
    public String nuevoConsultorio(Model model){
        model.addAttribute("consultorio", new consultorio());
        model.addAttribute("Titulo", "Crear nuevo consultorio");
        model.addAttribute("accion", "Crear");
        return "Consultorio/NuevoConsultorio";
    }

    @GetMapping("/consultorio/editar/{id}")
    public String modificarConsultorio(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            consultorio consul = service.get(id);
            model.addAttribute("consultorio", consul);
            model.addAttribute("Titulo", "Modificar consultorio: " + consul.getNombre());
            model.addAttribute("accion", "Modificar");
            return "Consultorio/ModificarConsultorio";
        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message", "Consultorio no encontrado");
            return "redirect:/ListadoConsultorios";
        }
    }

    @PostMapping("/consultorio/crear")
    public String crearConsultorio(consultorio consul, RedirectAttributes ra){
        //usu.setSegundoNombre("" + ((usu.getNombres().split(" ", 1).length > 0) ? usu.getNombres().split(" ", 1)[1] : "" ));
        //usu.setSegundoApellido("" + ((usu.getApellidos().split(" ", 1).length > 0) ? usu.getApellidos().split(" ", 1)[1] : "" ));
        String msg = consul.getId() != null ? "modificado" : "creado";

        service.guardar(consul);
        ra.addFlashAttribute("message", "Se ha " + msg + " el consultorio :D.");
        return "redirect:/ListadoConsultorios";
    }
}
