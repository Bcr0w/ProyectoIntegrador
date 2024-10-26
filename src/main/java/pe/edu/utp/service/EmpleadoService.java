package pe.edu.utp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.utp.model.Empleado;
import pe.edu.utp.repository.EmpleadoRepository;

@Service
@Transactional
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    // Método para registrar un nuevo empleado
    public Empleado registrarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    // Método para obtener todos los empleados
    public List<Empleado> obtenerTodosEmpleados() {
        return empleadoRepository.findAll();
    }

    // Método para buscar un empleado por ID
    public Optional<Empleado> buscarEmpleadoPorId(Integer id) {
        return empleadoRepository.findById(id);
    }

    // Método para actualizar un empleado existente
    public Empleado actualizarEmpleado(Empleado empleado) {
        if (empleadoRepository.existsById(empleado.getIdEmpleado())) {
            return empleadoRepository.save(empleado);
        }
        throw new RuntimeException("Empleado no encontrado");
    }

    // Método para eliminar un empleado
    public void eliminarEmpleado(Integer id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Empleado no encontrado");
        }
    }
}
