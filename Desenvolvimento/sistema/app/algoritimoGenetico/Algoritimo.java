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

		parametros.listDisciplinas = Disciplina.findAll();

		parametros.listHorarioDisciplina = DisciplinaHorario.find("alocado=true").fetch();

		parametros.listSalas = Sala.findAll();

		parametros.listHorarios = Horario.findAll();

		p.populacaoInicial(parametros.tamanhoPopulacao);

		boolean temSolucao = false;

		int geracao = 0;

		do {

			p = novaGeracao(p, parametros.elitismo);

			temSolucao = p.temSolucao();

			geracao++;

			System.out.println(geracao);

		} while (!temSolucao && geracao < parametros.numeroMaximoGeracoes);

		Individuo i = p.melhor();

		p.alocar(i);

		System.out.println(i.genoma);
		System.out.println(p.melhor().genoma);

	}

	public Populacao novaGeracao(Populacao populacao, boolean elitismo) {

		Random r = new Random();

		Populacao p = new Populacao(parametros);

		// se tiver elitismo, mantém o melhor indivíduo da geração atual
		if (elitismo) {

			p.populacao.add(populacao.melhor());

			System.out.println("elitismo" + p.melhor().fitness);

		}

		// insere novos indivíduos na nova população, até atingir o tamanho
		// máximo
		while (p.populacao.size() < parametros.tamanhoPopulacao) {

			// seleciona os 2 pais por torneio
			Individuo[] pais = selecaoTorneio(populacao);

			Individuo[] filhos = new Individuo[2];
			//
			filhos[0] = pais[0];
			filhos[1] = pais[1];
			// verifica a taxa de crossover, se sim realiza o crossover, se não,
			// mantém os pais selecionados para a próxima geração

			// if (r.nextDouble() <= parametros.taxaCrossover) {
			//
			// filhos = crossover(pais[1], pais[0]);
			//
			// } else {
			//
			// filhos[0] = pais[0];
			// filhos[1] = pais[1];
			//
			// }

			if (r.nextDouble() <= parametros.taxaMutacao) {

				Individuo i = mutacao(populacao);
				p.populacao.add(i);

			}

			// adiciona os filhos na nova geração
			p.populacao.add(filhos[0]);
			p.populacao.add(filhos[1]);

		}

		// ordena a nova população
		p.ordenar();

		return p;

	}

	public Individuo[] crossover(Individuo pai, Individuo mae) {

		Random r = new Random();

		// sorteia o ponto de corte
		int pontoCorte1 = r.nextInt((pai.cromossomo.size() / 2) - 2) + 1;
		int pontoCorte2 = r.nextInt((pai.cromossomo.size() / 2) - 2) + pai.cromossomo.size() / 2;

		Individuo[] filhos = new Individuo[2];

		Individuo i1 = new Individuo(parametros);

		i1.cromossomo = new ArrayList<Gene>();

		Individuo i2 = new Individuo(parametros);
		i2.cromossomo = new ArrayList<Gene>();

		// filho 1
		for (int i = 0; i < pontoCorte1; i++) {
			i1.cromossomo.add(pai.cromossomo.get(i));
		}

		for (int i = pontoCorte1; i < pontoCorte2; i++) {
			i1.cromossomo.add(mae.cromossomo.get(i));
		}

		for (int i = pontoCorte2; i < pai.cromossomo.size(); i++) {
			i1.cromossomo.add(pai.cromossomo.get(i));
		}

		// filho 2
		for (int i = 0; i < pontoCorte1; i++) {
			i2.cromossomo.add(mae.cromossomo.get(i));
		}

		for (int i = pontoCorte1; i < pontoCorte2; i++) {
			i2.cromossomo.add(pai.cromossomo.get(i));
		}

		for (int i = pontoCorte2; i < pai.cromossomo.size(); i++) {
			i2.cromossomo.add(mae.cromossomo.get(i));
		}

		// cria o novo indivíduo com os genes dos pais
		i1.fitness();
		i2.fitness();

		filhos[0] = i1;
		filhos[1] = i2;

		return filhos;

	}

	public Individuo[] selecaoTorneio(Populacao p) {

		Random r = new Random();

		// população intermediaria
		Populacao pi = new Populacao(parametros);

		// seleciona 3 indivíduos aleatóriamente na população
		pi.populacao.add(p.populacao.get(r.nextInt(parametros.tamanhoPopulacao)));
		pi.populacao.add(p.populacao.get(r.nextInt(parametros.tamanhoPopulacao)));
		pi.populacao.add(p.populacao.get(r.nextInt(parametros.tamanhoPopulacao)));

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

y		individuo.cromossomo.get(c2).disciplinaHorario = aux;

		return individuo;

	}
}
