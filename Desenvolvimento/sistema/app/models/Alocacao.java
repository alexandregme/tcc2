package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

/**
 * @author Alexandre Gonzaga Mendes 25/09/2013
 */

@Entity
@Table(name = "alocacao", schema = "public")
public class Alocacao extends GenericModel {
	
	@Id
	@Column(name = "id_alocacao")
	@GeneratedValue(generator = "alocacao_id_alocacao_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "alocacao_id_alocacao_seq", sequenceName = "alocacao_id_alocacao_seq")
	public Integer id;

	@ManyToOne
	@JoinColumn(name = "id_sala")
	public Sala sala;

	
	@ManyToOne
	@JoinColumn(name = "id_horario")
	public Horario horario;
	
	@ManyToOne
	@JoinColumn(name = "id_disciplina")
	public Disciplina disciplina;
	
}
