package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import play.db.jpa.GenericModel;

@Entity
@Table(name = "parametros", schema = "public")
public class Parametros extends GenericModel {

	@Id
	@Column(name = "id_parametro")
	public Integer id;

	@Column(name = "elitismo")
	public boolean elitismo;

	@Column(name = "taxa_crossover")
	public double taxaCrossover;

	@Column(name = "taxa_mutacao")
	public double taxaMutacao;

	@Column(name = "tamanho_populacao")
	public int tamanhoPopulacao;

	@Column(name = "maximo_geracoes")
	public int numeroMaximoGeracoes;

	@Column(name = "peso_discliplina_alocada")
	public int pesoDiscliplinaAlocada;

	@Column(name = "peso_graduacao")
	public int pesoGraduacao;

	@Column(name = "peso_pos")
	public int pesoPos;

	@Column(name = "peso_mesmo_periodo")
	public int pesoMesmoPeriodo;

	@Column(name = "peso_capacidade")
	public int pesoCapacidade;

	@Column(name = "peso_optativa")
	public int pesoOptativa;

	@Column(name = "peso_iluminacao")
	public int pesoIluminacao;

	@Transient
	public List<Disciplina> listDisciplinas = Disciplina.findAll();

	@Transient
	public List<DisciplinaHorario> listHorarioDisciplina = DisciplinaHorario.find("alocado=true").fetch();

	@Transient
	public List<Sala> listSalas = Sala.findAll();

	@Transient
	public List<Horario> listHorarios = Horario.findAll();

}
