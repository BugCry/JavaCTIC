package com.pruebaCTIC.springboot;

import com.pruebaCTIC.springboot.citas.ServiciosCitas;
import com.pruebaCTIC.springboot.citas.citas;
import com.pruebaCTIC.springboot.usuario.UserRepository;
import com.pruebaCTIC.springboot.usuario.UserService;
import com.pruebaCTIC.springboot.usuario.usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ServiciosCitas servicioCitas;

    @Autowired
    private UserService servicioUsu;

    @Autowired
    private UserRepository repoUsu;

    @GetMapping("")
    public String showHomePage(){
        return "index";
    }

    @GetMapping("/403")
    public String show403(){
        return "403";
    }

    @GetMapping("/ListadoPaciente")
    public String mostrarVistaPaciente(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        usuario usu = repoUsu.encontrarXEmail(auth.getName());
        List<citas> ListadoCitasXPaciente = servicioCitas.listarXPaciente(1);
        model.addAttribute("ListadoCitas", ListadoCitasXPaciente);
        return "Paciente/ListadoPaciente";
    }

    @GetMapping("/primerusuario")
    public String nuevoUsuario(Model model){
        model.addAttribute("usuario", new usuario());
        model.addAttribute("Titulo", "Crear primer usuario");
        model.addAttribute("accion", "Crear");
        return "Usuario/PrimerUsuario";
    }

    @PostMapping("/primerusuario/crear")
    public String crearUsuario(usuario usu, RedirectAttributes ra){
        //usu.setSegundoNombre("" + ((usu.getNombres().split(" ", 1).length > 0) ? usu.getNombres().split(" ", 1)[1] : "" ));
        //usu.setSegundoApellido("" + ((usu.getApellidos().split(" ", 1).length > 0) ? usu.getApellidos().split(" ", 1)[1] : "" ));
        usu.setNombreCompleto(usu.getNombres() + " " + usu.getApellidos());
        String msg = usu.getId() != null ? "modificado" : "creado";

        servicioUsu.guardar(usu);
        ra.addFlashAttribute("message", "Se ha " + msg + " el usuario :D.");
        return "redirect:/ListadoUsuarios";
    }
}
