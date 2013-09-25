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
@Table(name = "relacionamento_disciplina_horario", schema = "public")
public class DisciplinaHorario extends GenericModel {

	@Id
	@Column(name = "id_relacionamento")
	@GeneratedValue(generator = "relacionamento_disciplina_horario_id_relacionamento_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "relacionamento_disciplina_horario_id_relacionamento_seq", sequenceName = "relacionamento_disciplina_horario_id_relacionamento_seq")
	public Integer id;

	@ManyToOne
	@JoinColumn(name = "id_horario")
	public Horario horario;

	@ManyToOne
	@JoinColumn(name = "id_disciplina")
	public Disciplina disciplina;

}
