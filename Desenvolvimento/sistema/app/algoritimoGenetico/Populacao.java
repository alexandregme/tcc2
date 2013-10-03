package algoritimoGenetico;

import java.util.ArrayList;
import java.util.List;

import models.Horario;
import models.Sala;

public class Populacao {

	public List<Individuo> populacao;

	public Populacao() {
		populacao = new ArrayList<Individuo>();
		for (int i = 0; i < 100; i++) {
			Individuo individuo = new Individuo();
			populacao.add(individuo);
		}

	}

}