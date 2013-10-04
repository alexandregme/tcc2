package algoritimoGenetico;

import java.util.ArrayList;
import java.util.List;

import models.Alocacao;
import models.Horario;
import models.Sala;

public class Populacao {

	public static List<Individuo> populacao;

	public Populacao() {
		populacao = new ArrayList<Individuo>();
		for (int i = 0; i < 100; i++) {
			Individuo individuo = new Individuo();
			populacao.add(individuo);
		}
	}

	public Individuo melhor() {

		Individuo melhor = populacao.get(0);

		for (Individuo i : populacao) {

			if (melhor.fitness < i.fitness)
				melhor = i;
		}

		return melhor;

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