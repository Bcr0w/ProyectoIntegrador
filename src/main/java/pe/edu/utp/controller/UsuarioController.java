package pe.edu.utp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import pe.edu.utp.dto.registroDto;
import pe.edu.utp.model.Empleado;
import pe.edu.utp.model.Medico;
import pe.edu.utp.model.Paciente;
import pe.edu.utp.model.Rol;
import pe.edu.utp.model.Usuario;
import pe.edu.utp.model.Rol.RolEnum;
import pe.edu.utp.service.facade.EmpleadoFacade;
import pe.edu.utp.service.facade.MedicoFacade;
import pe.edu.utp.service.facade.PacienteFacade;
import pe.edu.utp.service.facade.RolFacade;
import pe.edu.utp.service.facade.UsuarioFacade;



@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
  @Autowired
  UsuarioFacade facade;
  @Autowired
  RolFacade rolFacade;
  @Autowired
  PacienteFacade pacienteFacade;
  @Autowired
  EmpleadoFacade empleadoFacade;
  @Autowired
  MedicoFacade medicoFacade;

 @GetMapping("/iniciar-sesion")
 public String iniciar() {
     return "iniciarSesion";
 }
 
 @GetMapping("/registrarse")
 public String registrarse(Model model) {
    model.addAttribute("registroDto", new registroDto());
     return "registrarse";
 }
 
 @PostMapping("/ingresar")
 public String ingresar(HttpSession session, @RequestParam("email") String email,
		                @RequestParam("password") String password,
		                Model model) {
	 //Rol rol = rolFacade.getRol("Paciente");
	 //Usuario user = new Usuario(null,correo,password,rol);
	 Usuario user = facade.getUsuario(email);

	 // Verificar si el usuario existe y la contraseña es correcta
	   // Verificar si el usuario existe y la contraseña es correcta
	   if (user != null && facade.verificarContraseña(password, user.getContrasena())) {
        // Si la contraseña es correcta, establecer la sesión
        session.setAttribute("seccion", true);
        session.setAttribute("rol", user.getRol().getNombreRol()); // Almacenar el rol del usuario
        
		//por dependiendo del rol se enviara el user y el id 
		switch(user.getRol().getNombreRol()){
			case PACIENTE:
		     Paciente paciente = pacienteFacade.buscarPorUsuario(user.getIdUsuario());
			 session.setAttribute("id", paciente.getIdPaciente());
			 session.setAttribute("nombre", paciente.getNombre());
			break;
			case MEDICO:
		     Medico medico = medicoFacade.buscarPorUsuario(user.getIdUsuario());
			 session.setAttribute("id", medico.getIdMedico());
			 session.setAttribute("nombre", medico.getNombre());
			break;
			case ADMIN, RECEPCIONISTA:
		     Empleado empleado = empleadoFacade.buscarPorUsuario(user.getIdUsuario());
			 session.setAttribute("id", empleado.getIdEmpleado());
			 session.setAttribute("nombre", empleado.getNombre());
			break;
            default:
                // Manejar un rol desconocido
                model.addAttribute("error", "Rol no reconocido");
                return "iniciarSesion"; // Redirigir a la página de inicio de sesión
		}

        // Redirigir a la página home
        return "redirect:/home"; // Asegúrate de que esta es la vista correcta
    } else {
        // Manejo de errores: usuario no encontrado o contraseña incorrecta
        model.addAttribute("error", "Correo o contraseña incorrectos");
        return "iniciarSesion"; // Redirigir a la página de inicio de sesión
    }
  }

  @PostMapping("/crear")
  public String crear(@ModelAttribute registroDto registroDTO, Model model){
	//Validar que las contraseñas coincidan
    if (!registroDTO.getContrasena().equals(registroDTO.getConfirmarContrasena())) {
        model.addAttribute("error", "Las contraseñas no coinciden.");
        return "registrarse"; // Redirigir al formulario de registro
    }

    // Crear objeto Usuario
    Rol rol = rolFacade.getRol(RolEnum.PACIENTE); // O el rol que desees asignar
    Usuario usuario = new Usuario(null, registroDTO.getCorreo(), registroDTO.getContrasena(), rol);
    facade.guardarUsuario(usuario); // Guardar el usuario en la base de datos

    // Crear objeto Paciente
    Paciente paciente = new Paciente();
    paciente.setUsuario(usuario); // Establecer la relación
    paciente.setNombre(registroDTO.getNombre());
    paciente.setApellidos(registroDTO.getApellidos());
    paciente.setTelefono(registroDTO.getTelefono());
    paciente.setDni(registroDTO.getDni());
    paciente.setFechaNacimiento(registroDTO.getFechaNacimiento());
    
    pacienteFacade.registrarPaciente(paciente); // Guardar el paciente en la base de datos

    return "redirect:/usuario/iniciar-sesion"; // Redirigir a la página de inicio de sesión
  }

  @GetMapping("/cerrar")
  public String cerrar(HttpSession session){

    session.removeAttribute("seccion");
    session.removeAttribute("rol");
    session.removeAttribute("id");
    session.removeAttribute("nombre");
    return "redirect:/home";

  }

 
}	
