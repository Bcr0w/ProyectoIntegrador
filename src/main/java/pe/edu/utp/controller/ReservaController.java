package pe.edu.utp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.utp.model.CitaMedica;
import pe.edu.utp.service.facade.CitaMedicaFacade;

@Controller
@RequestMapping("/reserva")
public class ReservaController {
    
    @Autowired
    CitaMedicaFacade facade;

    
    
   
}
