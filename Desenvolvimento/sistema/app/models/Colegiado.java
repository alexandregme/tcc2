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
@Table(name = "colegiado", schema = "public")
public class Colegiado extends GenericModel {

	@Id
	@Column(name = "id_colegiado")
	@GeneratedValue(generator = "colegiado_id_colegiado_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "colegiado_id_colegiado_seq", sequenceName = "colegiado_id_colegiado_seq")
	public Integer id;

	@ManyToOne
	@JoinColumn(name = "id_curso")
	public Curso curso;

	@Column(name = "nome_colegiado")
	public String nome;

	@Column(name = "texto_descricao")
	public String descricao;

	@Column(name = "tipo_colegiado")
	public String tipo;
	
}
