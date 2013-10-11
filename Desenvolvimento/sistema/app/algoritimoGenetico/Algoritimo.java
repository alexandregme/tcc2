package algoritimoGenetico;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder.In;

import models.Alocacao;
import models.Disciplina;
import models.DisciplinaHorario;
import models.Horario;
import models.Parametros;
import models.Sala;

public class Algoritimo {

	private Parametros parametros = Parametros.findById(1);

	public Algoritimo() {

		Alocacao.deleteAll();

		Populacao p = new Populacao(parametros);

		p.populacaoInicial(parametros.tamanhoPopulacao);

		boolean temSolucao = false;

		int geracao = 0;

		Individuo i = null;

		do {

			Populacao nova = novaGeracao(p);

			p = nova;

			i = p.melhor();

			temSolucao = p.temSolucao(i);

			geracao++;

			System.out.println(geracao);

		} while (!temSolucao && geracao < parametros.numeroMaximoGeracoes);

		p.alocar(i);

		System.out.println(i.genoma);
		System.out.println(i.fitness);
		System.out.println(p.melhor().genoma);

	}

	public Populacao novaGeracao(Populacao populacao) {

		Random r = new Random();

		Populacao p = new Populacao(parametros);

		// se tiver elitismo, mantém o melhor indivíduo da geração atual
		if (parametros.elitismo) {

			int elitismoPeople = (int) (populacao.populacao.size() * 0.2);

			for (int i = 0; i < elitismoPeople; i++) {

				p.populacao.add(populacao.populacao.get(i));

			}

			System.out.println("elitismo" + populacao.melhor().fitness);

		}

		// insere novos indivíduos na nova população, até atingir o tamanho
		// máximo

		for (int i = p.populacao.size(); i < parametros.tamanhoPopulacao; i++) {

			// seleciona os 2 pais por torneio
			Individuo[] pais = selecaoTorneio(populacao);

			Individuo[] filhos = new Individuo[2];

			// verifica a taxa de crossover, se sim realiza o crossover, se não,
			// mantém os pais selecionados para a próxima geração

			if (r.nextDouble() <= parametros.taxaCrossover) {

				filhos = crossover(pais[1], pais[0]);

			} else {

				filhos[0] = pais[0];
				filhos[1] = pais[1];

			}

			if (r.nextDouble() <= parametros.taxaMutacao) {

				filhos[0] = mutacao(populacao);

			}

			// adiciona os filhos na nova geração
			p.populacao.add(filhos[0]);
			p.populacao.add(filhos[1]);

		}// fim preenche populacao

		System.out.println(p.populacao.size() + "populacao");

		// ordena a nova população
		p.ordenar();

		return p;

	}

	public Individuo[] crossover(Individuo pai, Individuo mae) {

		Random r = new Random();

		// sorteia o ponto de corte
		int pontoCorte = r.nextInt(pai.cromossomo.size());
		// int pontoCorte1 = r.nextInt((pai.cromossomo.size() / 2) - 2) + 1;
		// int pontoCorte2 = r.nextInt((pai.cromossomo.size() / 2) - 2) +
		// pai.cromossomo.size() / 2;

		Individuo[] filhos = new Individuo[2];

		Individuo aux = new Individuo(parametros);

		Individuo i1 = new Individuo(parametros);

		// Individuo i2 = new Individuo(parametros);

		// filho 1
		aux = mae;

		for (int i = 0; i < pontoCorte; i++) {

			i1.cromossomo.get(i).disciplinaHorario = pai.cromossomo.get(i).disciplinaHorario;

			// percorre o aux para apagar o item que ja foi inserido
				for (int j = 0; j < aux.cromossomo.size(); j++) {
					if ((aux.cromossomo.get(j).disciplinaHorario != null) && (pai.cromossomo.get(i).disciplinaHorario != null) && (pai.cromossomo.get(i).disciplinaHorario.id == aux.cromossomo.get(j).disciplinaHorario.id)) {

						aux.cromossomo.get(j).disciplinaHorario = null;
						j = aux.cromossomo.size();
					}
				}

		}// fim for ponto de corte
//
//		for (int i = 0; i < pontoCorte; i++) {
//			if (aux.cromossomo.get(i).disciplinaHorario != null) {
//
//				for (int j = pontoCorte; j < aux.cromossomo.size(); j++) {
//					if (aux.cromossomo.get(j).disciplinaHorario == null)
//						i1.cromossomo.get(j).disciplinaHorario = aux.cromossomo.get(j).disciplinaHorario;
//
//				}// fim for completa
//
//			}
//		}

		for (int j = pontoCorte; j < aux.cromossomo.size(); j++) {
			i1.cromossomo.get(j).disciplinaHorario = aux.cromossomo.get(j).disciplinaHorario;
		}// fim for completa

		// filho 2

		i1.fitness();

		filhos[0] = i1;
		filhos[1] = pai;

		return filhos;

	}

	public Individuo[] selecaoTorneio(Populacao p) {

		Random r = new Random();

		// população intermediaria
		Populacao pi = new Populacao(parametros);

		// seleciona 3 indivíduos aleatóriamente na população
		pi.populacao.add(p.populacao.get(r.nextInt(p.populacao.size())));
		pi.populacao.add(p.populacao.get(r.nextInt(p.populacao.size())));
		pi.populacao.add(p.populacao.get(r.nextInt(p.populacao.size())));

		// ordena a população
		pi.ordenar();

		Individuo[] pais = new Individuo[2];

		// seleciona os 2 melhores deste população
		pais[0] = pi.populacao.get(0);
		pais[1] = pi.populacao.get(1);

		return pais;

	}

	public Individuo mutacao(Populacao p) {

		Random r = new Random();

		int ri = r.nextInt(p.populacao.size());

		Individuo individuo = p.populacao.get(ri);

		int c1 = r.nextInt(individuo.cromossomo.size());

		int c2 = r.nextInt(individuo.cromossomo.size());

		DisciplinaHorario aux = individuo.cromossomo.get(c1).disciplinaHorario;

		individuo.cromossomo.get(c1).disciplinaHorario = individuo.cromossomo.get(c2).disciplinaHorario;

		individuo.cromossomo.get(c2).disciplinaHorario = aux;

		return individuo;

	}
}
