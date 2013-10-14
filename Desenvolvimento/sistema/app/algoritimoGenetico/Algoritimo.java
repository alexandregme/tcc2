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

	public Algoritimo() throws CloneNotSupportedException {

		boolean temSolucao = false;

		int geracao = 0;

		Individuo i = null;

		Alocacao.deleteAll();

		Populacao p = new Populacao(parametros);

		p.populacaoInicial(parametros.tamanhoPopulacao);

		do {

			p = new Populacao((Populacao) novaGeracao(p).clone());

			i = (Individuo) p.melhor().clone();

			temSolucao = p.temSolucao(i);

			geracao++;

			System.out.println(geracao);

		} while (!temSolucao && geracao < parametros.numeroMaximoGeracoes);

		p.alocar(i);

		System.out.println(i.getGenoma());
		System.out.println(i.getFitness());
	}

	public Populacao novaGeracao(Populacao populacao) throws CloneNotSupportedException {

		Random r = new Random();

		Populacao p = new Populacao(parametros);

		// se tiver elitismo, mantém o melhor indivíduo da geração atual
		if (parametros.elitismo) {

			int elitismoPeople = (int) (populacao.getPopulacao().size() * 0.2);

			for (int i = 0; i < elitismoPeople; i++) {

				p.addIndividuo(new Individuo((Individuo) populacao.getIndividuo(i).clone()));

			}

		}

		System.out.println(populacao.melhor().getFitness());

		// insere novos indivíduos na nova população, até atingir o tamanho
		// máximo

		for (int i = p.getPopulacao().size(); i < parametros.tamanhoPopulacao; i++) {

			// seleciona os 2 pais por torneio
			Individuo[] pais = new Individuo[2];

			pais = selecaoTorneio(populacao);

			Individuo[] filhos = new Individuo[2];

			// verifica a taxa de crossover, se sim realiza o crossover, se não,
			// mantém os pais selecionados para a próxima geração

			if (r.nextDouble() <= parametros.taxaCrossover) {

				filhos = crossover(new Individuo((Individuo) pais[0].clone()), (Individuo) new Individuo(pais[1]).clone());

			} else {

				filhos[0] = new Individuo(pais[0]);
				filhos[1] = new Individuo(pais[1]);

			}

			if (r.nextDouble() <= parametros.taxaMutacao) {

				filhos[0] = new Individuo((Individuo) mutacao(populacao).clone());

			}

			// adiciona os filhos na nova geração

			p.addIndividuo(filhos[0]);

			p.addIndividuo(filhos[1]);

		}// fim preenche populacao

		// ordena a nova população
		p.ordenar();

		return p;

	}

	public Individuo[] crossover(Individuo pai, Individuo mae) throws CloneNotSupportedException {

		Random r = new Random();

		// sorteia o ponto de corte
		int pontoCorte = r.nextInt(pai.getCromossomo().size());

		Individuo[] filhos = new Individuo[2];

		// filho 1
		Individuo i1 = new Individuo(parametros);

		Individuo auxMae = new Individuo((Individuo) mae.clone());

		for (int i = 0; i < pontoCorte; i++) {

			i1.setHorarioDiscplina((Gene) pai.getCromossomoPosition(i).clone(), i);

			if (pai.getCromossomoPosition(i).getDisciplinaHorario() != null) {

				for (int j = 0; j < auxMae.getCromossomo().size(); j++) {

					if ((auxMae.getCromossomoPosition(j).getDisciplinaHorario() != null) && (auxMae.getCromossomoPosition(j).getDisciplinaHorario().id == pai.getCromossomoPosition(i).getDisciplinaHorario().id)) {

						auxMae.removeCromossomoPosition(j);

						j = auxMae.getCromossomo().size();

					}

				}// fim for completa

			} else {

				for (int j = 0; j < auxMae.getCromossomo().size(); j++) {

					if (auxMae.getCromossomoPosition(j).getDisciplinaHorario() == null) {

						auxMae.removeCromossomoPosition(j);

						j = auxMae.getCromossomo().size();

					}

				}

			}

		}

		for (int j = 0; j < auxMae.getCromossomo().size(); j++) {

			i1.setHorarioDiscplina((Gene) auxMae.getCromossomoPosition(j).clone(), j + pontoCorte);

		}// fim for completa

		// filho 2
		Individuo i2 = new Individuo(parametros);

		Individuo auxPai = new Individuo((Individuo) pai.clone());

		for (int i = 0; i < pontoCorte; i++) {

			i2.setHorarioDiscplina((Gene) mae.getCromossomoPosition(i).clone(), i);

			if (mae.getCromossomoPosition(i).getDisciplinaHorario() != null) {

				for (int j = 0; j < auxPai.getCromossomo().size(); j++) {

					if ((auxPai.getCromossomoPosition(j).getDisciplinaHorario() != null) && (auxPai.getCromossomoPosition(j).getDisciplinaHorario().id == mae.getCromossomoPosition(i).getDisciplinaHorario().id)) {

						auxPai.removeCromossomoPosition(j);

						j = auxPai.getCromossomo().size();

					}

				}// fim for completa

			} else {

				for (int j = 0; j < auxPai.getCromossomo().size(); j++) {

					if (auxPai.getCromossomoPosition(j).getDisciplinaHorario() == null) {

						auxPai.removeCromossomoPosition(j);

						j = auxPai.getCromossomo().size();

					}

				}

			}

		}

		for (int j = 0; j < auxPai.getCromossomo().size(); j++) {

			i2.setHorarioDiscplina((Gene) auxPai.getCromossomoPosition(j).clone(), j + pontoCorte);

		}// fim for completa

		i1.validate();
		i2.validate();

		filhos[0] = new Individuo((Individuo) i1.clone());
		filhos[1] = new Individuo((Individuo) i2.clone());

		return filhos;

	}

	// escolhe 3 individuos aleatoriamente da população e pega os 2 melhores
	public Individuo[] selecaoTorneio(Populacao p) throws CloneNotSupportedException {

		Random r = new Random();

		// população intermediaria
		Populacao pi = new Populacao(parametros);

		// seleciona 3 indivíduos aleatóriamente na população
		pi.addIndividuo(p.getIndividuo(r.nextInt(p.getPopulacao().size())));
		pi.addIndividuo(p.getIndividuo(r.nextInt(p.getPopulacao().size())));
		pi.addIndividuo(p.getIndividuo(r.nextInt(p.getPopulacao().size())));

		// ordena a população
		pi.ordenar();

		Individuo[] pais = new Individuo[2];

		// seleciona os 2 melhores deste população
		pais[0] = new Individuo(pi.getIndividuo(0));
		pais[1] = new Individuo(pi.getIndividuo(1));

		return pais;

	}

	// mutação troca dois genes randomicamente de lugar.
	public Individuo mutacao(Populacao p) throws CloneNotSupportedException {

		Random r = new Random();

		int ri = r.nextInt(p.getPopulacao().size());

		Individuo individuo = new Individuo((Individuo) p.getIndividuo(ri).clone());

		int c1 = r.nextInt(individuo.getCromossomo().size());

		int c2 = r.nextInt(individuo.getCromossomo().size());

		DisciplinaHorario aux = individuo.getCromossomo().get(c1).getDisciplinaHorario();

		individuo.getCromossomo().get(c1).setDisciplinaHorario(individuo.getCromossomo().get(c2).getDisciplinaHorario());

		individuo.getCromossomo().get(c2).setDisciplinaHorario(aux);

		return new Individuo((Individuo) individuo.clone());

	}

}
