package com.pruebaCTIC.springboot.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;

    @GetMapping("/ListadoUsuarios")
    public String mostrarListadoUsuarios(Model model){
        List<usuario> ListadoUsuarios = service.listarTodo();
        model.addAttribute("ListadoUsuarios", ListadoUsuarios);

        return "ListadoUsuarios"; //debe ser el mismo nombre del archivo tamplate.html
    }

    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario(Model model){
        model.addAttribute("usuario", new usuario());
        model.addAttribute("Titulo", "Crear nuevo usuario");
        model.addAttribute("accion", "Crear");
        return "NuevoUsuario";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String modificarUsuario(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            usuario usu = service.get(id);
            model.addAttribute("usuario", usu);
            model.addAttribute("Titulo", "Modificar usuario: " + usu.getNombreCompleto());
            model.addAttribute("accion", "Modificar");
            return "Usuario/ModificarUsuario";
        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message", "Usuario no encontrado");
            return "redirect:/ListadoUsuarios";
        }
    }

    @GetMapping("/usuarios/cambiarEstado/{id}/{estado}")
    public String cambiarEstadoUsuario(@PathVariable("id") Integer id, @PathVariable("estado") boolean estado, Model model, RedirectAttributes ra){
        try{
            usuario usu = service.get(id);
            usu.setEstado(!estado);
            String msg = estado ? "desactivado" : "activado";

            service.guardar(usu);
            ra.addFlashAttribute("message", "Se ha " + msg + " el usuario :D.");
            return "redirect:/ListadoUsuarios";
        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message", "Usuario no encontrado");
            return "redirect:/ListadoUsuarios";
        }
    }

    //post que crea o modifica usuario
    @PostMapping("/usuarios/crear")
    public String crearUsuario(usuario usu, RedirectAttributes ra){
        //usu.setSegundoNombre("" + ((usu.getNombres().split(" ", 1).length > 0) ? usu.getNombres().split(" ", 1)[1] : "" ));
        //usu.setSegundoApellido("" + ((usu.getApellidos().split(" ", 1).length > 0) ? usu.getApellidos().split(" ", 1)[1] : "" ));
        usu.setNombreCompleto(usu.getNombres() + " " + usu.getApellidos());
        String msg = usu.getId() != null ? "modificado" : "creado";

        service.guardar(usu);
        ra.addFlashAttribute("message", "Se ha " + msg + " el usuario :D.");
        return "redirect:/ListadoUsuarios";
    }
}
