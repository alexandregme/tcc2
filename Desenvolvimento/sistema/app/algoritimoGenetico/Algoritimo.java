package algoritimoGenetico;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.ParametrosAlgoritimo;

public class Algoritimo {

	private ParametrosAlgoritimo parametros = new ParametrosAlgoritimo();

	public Algoritimo() {

		parametros.taxaCrossover = 0.6;

		parametros.taxaMutacao = 0.3;

		parametros.elitismo = true;

		parametros.tamanhoPopulacao = 100;

		parametros.numeroMaximoGeracoes = 15;

		Populacao p = new Populacao();

		p.populacaoInicial(parametros.tamanhoPopulacao);


		boolean temSolucao = false;

		int geracao = 0;

		do {

			p = novaGeracao(p, parametros.elitismo);

			temSolucao = p.temSolucao();

			geracao++;

		} while (!temSolucao && geracao < parametros.numeroMaximoGeracoes);

		//p.alocar(p.melhor());

	}

	public Populacao novaGeracao(Populacao populacao, boolean elitismo) {

		Random r = new Random();

		Populacao p = new Populacao();

		// se tiver elitismo, mantém o melhor indivíduo da geração atual
		if (elitismo) {
			p.populacao.add(populacao.melhor());
		}

		// insere novos indivíduos na nova população, até atingir o tamanho
		// máximo
		while (p.populacao.size() < parametros.tamanhoPopulacao) {

			
			// seleciona os 2 pais por torneio
			Individuo[] pais = selecaoTorneio(populacao);

			Individuo[] filhos = new Individuo[2];

			// verifica a taxa de crossover, se sim realiza o crossover, se não,
			// mantém os pais selecionados para a próxima geração

			if (r.nextDouble() <= parametros.taxaCrossover) {

				filhos = crossover(pais[1], pais[0]);

			} else {

				filhos[0] = new Individuo(pais[0].cromossomo);
				filhos[1] = new Individuo(pais[1].cromossomo);

			}

			// adiciona os filhos na nova geração
			p.populacao.add(filhos[0]);
			p.populacao.add(filhos[1]);

		}

		// ordena a nova população
		p.ordenar();

		return p;

	}

	public static Individuo[] crossover(Individuo pai, Individuo mae) {

		Random r = new Random();

		// sorteia o ponto de corte
		int pontoCorte1 = r.nextInt((pai.cromossomo.size() / 2) - 2) + 1;
		int pontoCorte2 = r.nextInt((pai.cromossomo.size() / 2) - 2) + pai.cromossomo.size() / 2;

		Individuo[] filhos = new Individuo[2];

		// pega os genes dos pais
		List<Gene> cromossomoPai = pai.cromossomo;
		List<Gene> cromossomMae = mae.cromossomo;

		List<Gene> cromossomFilho1 = new ArrayList<Gene>();
		List<Gene> cromossomFilho2 = new ArrayList<Gene>();

		// filho 1
		for (int i = 0; i < pontoCorte1; i++) {
			cromossomFilho1.add(cromossomoPai.get(i));
		}

		for (int i = pontoCorte1; i < pontoCorte2; i++) {
			cromossomFilho1.add(cromossomMae.get(i));
		}

		for (int i = pontoCorte2; i < cromossomoPai.size(); i++) {
			cromossomFilho1.add(cromossomoPai.get(i));
		}

		// filho 2
		for (int i = 0; i < pontoCorte1; i++) {
			cromossomFilho1.add(cromossomMae.get(i));
		}

		for (int i = pontoCorte1; i < pontoCorte2; i++) {
			cromossomFilho1.add(cromossomoPai.get(i));
		}

		for (int i = pontoCorte2; i < cromossomoPai.size(); i++) {
			cromossomFilho1.add(cromossomMae.get(i));
		}

		// cria o novo indivíduo com os genes dos pais
		filhos[0] = new Individuo(cromossomFilho1);
		filhos[1] = new Individuo(cromossomFilho2);

		return filhos;

	}

	public Individuo[] selecaoTorneio(Populacao p) {

		Random r = new Random();

		// população intermediaria
		Populacao pi = new Populacao();

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

}