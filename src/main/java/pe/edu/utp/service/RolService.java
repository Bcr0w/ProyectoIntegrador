package pe.edu.utp.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.utp.model.Rol;
import pe.edu.utp.repository.RolRepository;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    // Método para registrar un nuevo rol
    public Rol registrarRol(Rol rol) {
        return rolRepository.save(rol);
    }

    // Método para obtener todos los roles
    public List<Rol> obtenerTodosRoles() {
        return rolRepository.findAll();
    }

    // Método para buscar un rol por ID
    public Optional<Rol> buscarRolPorId(Integer id) {
        return rolRepository.findById(id);
    }

    // Método para actualizar un rol existente
    public Rol actualizarRol(Rol rol) {
        if (rolRepository.existsById(rol.getIdRol())) {
            return rolRepository.save(rol);
        }
        throw new RuntimeException("Rol no encontrado");
    }

    // Método para eliminar un rol
    public void eliminarRol(Integer id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
        } else {
            throw new RuntimeException("Rol no encontrado");
        }
    }
}