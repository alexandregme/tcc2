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
@Table(name = "periodo", schema = "public")
public class Periodo extends GenericModel {

	@Id
	@Column(name = "id_periodo")
	@GeneratedValue(generator = "periodo_id_periodo_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "periodo_id_periodo_seq", sequenceName = "periodo_id_periodo_seq")
	public Integer id;

	@ManyToOne
	@JoinColumn(name = "id_colegiado")
	public Colegiado colegiado;

	@Column(name = "texto_descricao")
	public String descricao;

	@Column(name = "flag_optativo")
	public Integer flagOptativo;

}
