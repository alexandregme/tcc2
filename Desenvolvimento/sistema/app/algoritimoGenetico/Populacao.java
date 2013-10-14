package algoritimoGenetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import models.Alocacao;
import models.Disciplina;
import models.Horario;
import models.Parametros;
import models.Sala;

public class Populacao implements Cloneable {

	private List<Individuo> populacao = null;

	private Parametros parametros;

	public Populacao(Populacao populacao) throws CloneNotSupportedException {

		this.populacao = new ArrayList<Individuo>();

		for (int i = 0; i < populacao.getPopulacao().size(); i++) {
			this.addIndividuo((Individuo) populacao.getIndividuo(i).clone());

		}

		this.parametros = populacao.parametros;

	}

	public Populacao(Parametros p) {

		populacao = new ArrayList<Individuo>();

		this.parametros = p;

	}

	public List<Individuo> getPopulacao() {

		return this.populacao;

	}

	public void addIndividuo(Individuo i) throws CloneNotSupportedException {

		this.populacao.add(new Individuo((Individuo) i.clone()));

	}

	public Individuo getIndividuo(Integer i) {

		return this.populacao.get(i);

	}

	public void populacaoInicial(int tamanhoPopulacao) {

		populacao = new ArrayList<Individuo>();

		for (int i = 0; i < tamanhoPopulacao; i++) {

			Individuo individuo = new Individuo(parametros);

			individuo.populate();

			populacao.add(individuo);

		}

	}

	public Individuo melhor() {

		return populacao.get(0);

	}

	// calcula se a população tem a solução metodo ainda não implementado
	public boolean temSolucao(Individuo i) {

		// TODO calcular a solução do problema

		if (i.getFitness() == 100.0)
			return true;

		return false;
	}

	// ordena a população pelo valor de aptidão de cada indivíduo, do maior
	// valor para o menor, assim se eu quiser obter o melhor indivíduo desta
	// população, acesso a posição 0 do array de indivíduos

	public void ordenar() {

		// TODO implementar um quicksort
		Collections.sort(populacao, new Comparator() {
			public int compare(Object o1, Object o2) {
				Individuo i1 = (Individuo) o1;
				Individuo i2 = (Individuo) o2;
				return i1.getFitness().compareTo(i2.getFitness()) * -1;
			}
		});

	}

	// salva a melhor individuo no banco / melhor solução do problema
	public void alocar(Individuo i) {

		for (Gene g : i.getCromossomo()) {

			Alocacao a = new Alocacao();

			a.sala = Sala.findById(g.getSala().id);

			a.horario = Horario.findById(g.getHorario().id);

			a.dia = g.getDiaSemana();

			if (g.getDisciplinaHorario() == null)

				a.disciplina = null;
			else
				a.disciplina = Disciplina.findById(g.getDisciplinaHorario().disciplina.id);

			a.save();

		}

	}

	@Override
	protected Object clone() throws CloneNotSupportedException {

		return super.clone();

	}

}