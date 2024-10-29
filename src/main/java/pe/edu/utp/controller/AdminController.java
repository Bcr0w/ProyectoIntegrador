package pe.edu.utp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.utp.dto.EmpleadoDto;
import pe.edu.utp.dto.MedicoDto;
import pe.edu.utp.model.CitaMedica;
import pe.edu.utp.model.Empleado;
import pe.edu.utp.model.Especialidad;
import pe.edu.utp.model.Medico;
import pe.edu.utp.model.Rol;
import pe.edu.utp.model.Usuario;
import pe.edu.utp.service.facade.CitaMedicaFacade;
import pe.edu.utp.service.facade.EmpleadoFacade;
import pe.edu.utp.service.facade.EspecialidadFacade;
import pe.edu.utp.service.facade.MedicoFacade;
import pe.edu.utp.service.facade.RolFacade;
import pe.edu.utp.service.facade.UsuarioFacade;

@Controller
@RequestMapping("/admin")
public class AdminController {
 
 @Autowired
 EmpleadoFacade empleadoFacade;
 @Autowired
 CitaMedicaFacade citaFacade;
 @Autowired
 MedicoFacade medicoFacade;
 @Autowired
 RolFacade rolFacade;
 @Autowired
 EspecialidadFacade especialidadFacade;
 @Autowired
 UsuarioFacade usuarioFacade;
 
 @GetMapping("/dashboard")
 public String dashboard(Model model){
    List<CitaMedica> citas = citaFacade.obtenerTodasCitasMedicas();
    model.addAttribute("citas", citas);
    return "admin-home";
 }

 //GESTION EMPLEADOS
 @GetMapping("/empleados")
 public String empleados(Model model){
   List<Empleado> empleados = empleadoFacade.obtenerTodosEmpleados();
   EmpleadoDto empleadoDto = new EmpleadoDto();
   model.addAttribute("empleados", empleados);
   model.addAttribute("empleadoDto", empleadoDto);
   return "admin-empleados";
 }

  @PostMapping("/crearEmpleado")
 public String crearEmpleado(@ModelAttribute EmpleadoDto empleadoDto){
    Empleado empleado = new Empleado();
    Rol rol = rolFacade.findById(empleadoDto.getIdRol());
    Usuario usuario = new Usuario(null, empleadoDto.getCorreo(), empleadoDto.getContrasena(), rol);
    usuarioFacade.guardarUsuario(usuario);

    empleado.setNombre(empleadoDto.getNombre());
    empleado.setApellidos(empleadoDto.getApellidos());
    empleado.setCargo(empleadoDto.getCargo());
    empleado.setDireccion(empleadoDto.getDireccion());
    empleado.setTelefono(empleadoDto.getTelefono());
    empleado.setUsuario(usuario);

    empleadoFacade.registrarEmpleado(empleado);
    return "redirect:/admin/empleados";
 }

 @GetMapping("/buscarEmpleado")
 public String buscarEmpleado(@RequestParam("nombre") String nombre, Model model){
   List<Empleado> empleados = empleadoFacade.buscarPorNombre(nombre);
   EmpleadoDto empleadoDto = new EmpleadoDto();
   model.addAttribute("empleados", empleados);
   model.addAttribute("empleadoDto", empleadoDto);
   return "admin-empleados";
 }

 //GESTION DE MEDICOS
 @GetMapping("/medicos")
 public String medicos(Model model){
   List<Medico> medicos = medicoFacade.obtenerTodosMedicos();
   MedicoDto medicoDto = new MedicoDto();
   List<Especialidad> especialidades = especialidadFacade.obtenerTodasEspecialidades();
   model.addAttribute("medicos", medicos);
   model.addAttribute("medicoDto", medicoDto);
   model.addAttribute("especialidades", especialidades);
   return "admin-medicos";
 }

 @PostMapping("/crearMedico")
 public String crearMedico(@ModelAttribute MedicoDto medicoDto){
    Medico medico = new Medico();
    Rol rol = rolFacade.findById(medicoDto.getIdRol());
    Especialidad especialidad = especialidadFacade.buscarEspecialidadPorId(medicoDto.getIdEspecialidad());
    Usuario usuario = new Usuario(null, medicoDto.getCorreo(), medicoDto.getContrasena(), rol);
    usuarioFacade.guardarUsuario(usuario);

    medico.setNombre(medicoDto.getNombre());
    medico.setApellidos(medicoDto.getApellidos());
    medico.setEspecialidad(especialidad);
    medico.setDireccion(medicoDto.getDireccion());
    medico.setTelefono(medicoDto.getTelefono());
    medico.setUsuario(usuario);

    medicoFacade.registrarMedico(medico);
    return "redirect:/admin/medicos";
 }







}
