package pe.edu.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.utp.model.CitaMedica;

@Repository
public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Integer> {
    // Métodos personalizados pueden ir aquí
}