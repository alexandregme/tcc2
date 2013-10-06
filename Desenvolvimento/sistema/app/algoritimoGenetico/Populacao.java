package algoritimoGenetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.mapping.Collection;

import models.Alocacao;

public class Populacao {

	public List<Individuo> populacao;

	public Populacao() {

		populacao = new ArrayList<Individuo>();

	}

	public void populacaoInicial(int tamanhoPopulacao) {

		for (int i = 0; i < tamanhoPopulacao; i++) {

			Individuo individuo = new Individuo();

			populacao.add(individuo);

		}

	}

	public Individuo melhor() {

		ordenar();

		return populacao.get(0);

	}

	public boolean temSolucao() {
		return false;
	}

	// ordena a população pelo valor de aptidão de cada indivíduo, do maior
	// valor para o menor, assim se eu quiser obter o melhor indivíduo desta
	// população, acesso a posição 0 do array de indivíduos
	public void ordenar() {

		Collections.sort(populacao, new Comparator() {
			public int compare(Object o1, Object o2) {
				Individuo i1 = (Individuo) o1;
				Individuo i2 = (Individuo) o2;
				return i1.fitness < i2.fitness ? +1
						: (i1.fitness > i2.fitness ? -1 : 0);
			}
		});
	}

	public void alocar(Individuo i) {

		for (Gene g : i.cromossomo) {

			Alocacao a = new Alocacao();

			a.sala = g.sala;

			a.horario = g.horario;

			a.dia = g.diaSemana;

			if (g.disciplinaHorario == null)
				
				a.disciplina = null;
			else
				a.disciplina = g.disciplinaHorario.disciplina;

			a.save();

		}

	}
}