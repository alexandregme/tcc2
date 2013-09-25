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
@Table(name = "sala", schema = "public")
public class Sala extends GenericModel {

	@Id
	@Column(name = "id_sala")
	@GeneratedValue(generator = "sala_id_sala_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "sala_id_sala_seq", sequenceName = "sala_id_sala_seq")
	public Integer id;

	@ManyToOne
	@JoinColumn(name = "id_predio")
	public Predio predio;

	@Column(name = "numero_andar")
	public Integer andar;

	@Column(name = "numero_vagas")
	public Integer vagas;

	@Column(name = "nome_sala")
	public String nome;

	@Column(name = "flag_iluminacao")
	public Integer iluminacao;

	@Column(name = "flag_ativa")
	public Integer ativa;

}