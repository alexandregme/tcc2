package algoritimoGenetico;

import java.util.ArrayList;
import java.util.List;

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

		// boolean trocou = true;

		// while (trocou) {
		//
		// trocou = false;
		//
		// for (int i = 0; i < populacao.size() - 1; i++) {
		//
		// if (populacao.get(i).fitness < populacao.get(i + 1).fitness) {
		//
		// System.out.println(i);
		//
		// System.out.println(populacao.get(i).fitness);
		//
		// System.out.println(populacao.get(i + 1).fitness);
		//
		// Individuo temp = populacao.get(i);
		//
		// populacao.add(i, populacao.get(i + 1));
		//
		// populacao.add(i + 1, temp);
		//
		// trocou = true;
		//
		// }
		//
		// }
		// }

	}

	public void alocar(Individuo i) {

		for (Gene g : i.cromossomo) {

			Alocacao a = new Alocacao();

			a.sala = g.sala;

			a.horario = g.horario;

			a.dia = g.diaSemana;

			a.disciplina = g.disciplina;

			a.save();

		}

	}
}