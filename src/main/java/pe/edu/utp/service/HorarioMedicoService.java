package pe.edu.utp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.utp.model.HorarioMedico;
import pe.edu.utp.repository.HorarioMedicoRepository;

@Service
@Transactional
public class HorarioMedicoService {

    @Autowired
    private HorarioMedicoRepository horarioMedicoRepository;

    // Método para registrar un nuevo horario médico
    public HorarioMedico registrarHorarioMedico(HorarioMedico horarioMedico) {
        return horarioMedicoRepository.save(horarioMedico);
    }

    // Método para obtener todos los horarios médicos
    public List<HorarioMedico> obtenerTodosHorariosMedicos() {
        return horarioMedicoRepository.findAll();
    }

    // Método para buscar un horario médico por ID
    public Optional<HorarioMedico> buscarHorarioMedicoPorId(Integer id) {
        return horarioMedicoRepository.findById(id);
    }

    // Método para actualizar un horario médico existente
    public HorarioMedico actualizarHorarioMedico(HorarioMedico horarioMedico) {
        if (horarioMedicoRepository.existsById(horarioMedico.getIdHorario())) {
            return horarioMedicoRepository.save(horarioMedico);
        }
        throw new RuntimeException("Horario médico no encontrado");
    }

    // Método para eliminar un horario médico
    public void eliminarHorarioMedico(Integer id) {
        if (horarioMedicoRepository.existsById(id)) {
            horarioMedicoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Horario médico no encontrado");
        }
    }
}