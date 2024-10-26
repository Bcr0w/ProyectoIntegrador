package pe.edu.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.utp.model.HorarioMedico;

@Repository
public interface HorarioMedicoRepository extends JpaRepository<HorarioMedico, Integer> {
    // Métodos personalizados pueden ir aquí
}