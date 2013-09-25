package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

@Entity
@Table(name = "turno", schema = "public")
public class Turno extends GenericModel {

	@Id
	@Column(name = "id_turno")
	@GeneratedValue(generator = "turno_id_turno_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "turno_id_turno_seq", sequenceName = "turno_id_turno_seq")
	public Integer id;

	@Column(name = "texto_descricao")
	public String descricao;
}
