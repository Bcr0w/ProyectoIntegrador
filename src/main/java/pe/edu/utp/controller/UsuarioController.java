package pe.edu.utp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/usuario")
public class UsuarioController {

 @GetMapping("/iniciar-sesion")
 public String iniciar() {
     return "iniciarSesion";
 }
 
 @GetMapping("/registrarse")
 public String registrarse(@RequestParam String param) {
     return "registrarse";
 }
 
 
}
