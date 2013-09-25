package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

/**
 * @author Alexandre Gonzaga Mendes 25/09/2013
 */

@Entity
@Table(name = "curso", schema = "public")
public class Curso extends GenericModel {

	@Id
	@Column(name = "id_curso")
	@GeneratedValue(generator = "curso_id_curso_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "curso_id_curso_seq", sequenceName = "curso_id_curso_seq")
	public Integer id;

	@Column(name = "nome_curso")
	public String nomeCurso;

	@Column(name = "texto_descricao")
	public String textoDescricao;

}