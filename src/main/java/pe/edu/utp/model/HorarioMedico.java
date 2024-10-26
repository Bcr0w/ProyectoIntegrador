package pe.edu.utp.model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "Horario_Medico")
public class HorarioMedico  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHorario;

    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;

    @Column(name = "dia_semana", length = 20)
    private String diaSemana;

    private LocalTime horaInicio;
    private LocalTime horaFin;

    public HorarioMedico() {
		// TODO Auto-generated constructor stub
	}

	public HorarioMedico(Integer idHorario, Medico medico, String diaSemana, LocalTime horaInicio, LocalTime horaFin) {
		super();
		this.idHorario = idHorario;
		this.medico = medico;
		this.diaSemana = diaSemana;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public Integer getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(Integer idHorario) {
		this.idHorario = idHorario;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}
    
}