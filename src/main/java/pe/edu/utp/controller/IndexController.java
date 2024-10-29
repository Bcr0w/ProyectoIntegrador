package pe.edu.utp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;





@Controller
@RequestMapping("/")
public class IndexController {


	@GetMapping("/home")
	public String home() {
		return "index";
	}
	
	@GetMapping("/servicios")
	public String servicios() {
		return "servicios";
	}
	
	@GetMapping("/nosotros")
	public String nosotros() {
		return "nosotros";
	}
	
	
}
