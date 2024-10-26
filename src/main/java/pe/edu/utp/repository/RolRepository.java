package pe.edu.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.utp.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    // Puedes agregar métodos personalizados aquí si es necesario
}