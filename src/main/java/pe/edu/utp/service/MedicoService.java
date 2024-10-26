package pe.edu.utp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.utp.model.Medico;
import pe.edu.utp.repository.MedicoRepository;

@Service
@Transactional
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    // Método para registrar un nuevo médico
    public Medico registrarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    // Método para obtener todos los médicos
    public List<Medico> obtenerTodosMedicos() {
        return medicoRepository.findAll();
    }

    // Método para buscar un médico por ID
    public Optional<Medico> buscarMedicoPorId(Integer id) {
        return medicoRepository.findById(id);
    }

    // Método para actualizar un médico existente
    public Medico actualizarMedico(Medico medico) {
        if (medicoRepository.existsById(medico.getIdMedico())) {
            return medicoRepository.save(medico);
        }
        throw new RuntimeException("Médico no encontrado");
    }

    // Método para eliminar un médico
    public void eliminarMedico(Integer id) {
        if (medicoRepository.existsById(id)) {
            medicoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Médico no encontrado");
        }
    }
}