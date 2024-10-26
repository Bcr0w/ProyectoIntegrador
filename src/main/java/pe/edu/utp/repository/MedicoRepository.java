package pe.edu.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.utp.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    // Métodos personalizados pueden ir aquí
}