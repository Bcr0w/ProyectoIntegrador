package pe.edu.utp.service.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.model.CitaMedica;
import pe.edu.utp.service.CitaMedicaService;

@Service
public class CitaMedicaFacade {
    
    @Autowired
    private CitaMedicaService service;

    // Método para registrar una nueva cita médica
    public CitaMedica registrarCitaMedica(CitaMedica citaMedica) {
        return service.registrarCitaMedica(citaMedica);
    }

    // Método para obtener todas las citas médicas
    public List<CitaMedica> obtenerTodasCitasMedicas() {
        return service.obtenerTodasCitasMedicas();
    }

    // Método para buscar una cita médica por ID
    public CitaMedica buscarCitaMedicaPorId(Integer id) {
        return service.buscarCitaMedicaPorId(id);
    }

    // Método para actualizar una cita médica existente
    public CitaMedica actualizarCitaMedica(CitaMedica citaMedica) {
        return service.actualizarCitaMedica(citaMedica);
    }

    // Método para eliminar una cita médica
    public void eliminarCitaMedica(Integer id) {
        service.eliminarCitaMedica(id);
    }
}
