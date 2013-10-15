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
@Table(name = "predio", schema = "public")
public class Predio extends GenericModel {

	@Id
	@Column(name = "id_predio")
	@GeneratedValue(generator = "predio_id_predio_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "predio_id_predio_seq", sequenceName = "predio_id_predio_seq")
	public Integer id;

	@Column(name = "nome_predio")
	public String nome;

	@Column(name = "texto_descricao")
	public String descricao;

}
