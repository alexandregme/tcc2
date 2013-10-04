package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Alocacao;
import models.AlocacaoInterface;
import models.AlocacaoSemana;
import models.DisciplinaHorario;
import models.DisciplinaSemana;
import models.Horario;
import models.Sala;
import models.Turno;
import play.mvc.Controller;
import utils.MessageHelper;
import utils.Protocol;
import utils.RequestSerializer;
import algoritimoGenetico.Algoritimo;
import algoritimoGenetico.Populacao;
import algoritimoGenetico.Populacaobackup;

/**
 * @author Alexandre Gonzaga Mendes 27/09/2013
 */
public class AlgoritimoController extends Controller {

	final static String complement = "algoritimoGenetico";
	private static Protocol protocol = null;

	public static void result() {

		Alocacao.deleteAll();
		
		Populacao p = new Populacao();
		
		p.alocar(p.melhor());

		protocol = new Protocol('s', MessageHelper.get("Genetica modificada"), p.populacao, 1);

		renderJSON(protocol);

		List<String> listSolucacao = new ArrayList<String>();

		// Define a solução
		Algoritimo.setSolucao("Olá Mundo");
		// Define os caracteres existentes
		Algoritimo.setCaracteres("!,.:;?áÁãÃâÂõÕôÔóÓéêÉÊíQWERTYUIOPASDFGHJKLÇZXCVBNMqwertyuiopasdfghjklçzxcvbnm1234567890 ");
		// taxa de crossover de 60%
		Algoritimo.setTaxaDeCrossover(0.6);
		// taxa de mutação de 3%
		Algoritimo.setTaxaDeMutacao(0.3);
		// elitismo
		boolean eltismo = true;
		// tamanho da população
		int tamPop = 2000;
		// numero máximo de gerações
		int numMaxGeracoes = 10000;

		// define o número de genes do indivíduo baseado na solução
		int numGenes = Algoritimo.getSolucao().length();

		// cria a primeira população aleatérioa
		Populacaobackup populacao = new Populacaobackup(numGenes, tamPop);

		boolean temSolucao = false;
		int geracao = 0;

		listSolucacao.add("Iniciando... Aptidão da solução: " + Algoritimo.getSolucao().length());

		// loop até o critério de parada
		while (!temSolucao && geracao < numMaxGeracoes) {
			geracao++;

			// cria nova populacao
			populacao = Algoritimo.novaGeracao(populacao, eltismo);

			listSolucacao.add("Geração " + geracao + " | Aptidão: " + populacao.getIndivduo(0).getAptidao() + " | Melhor: " + populacao.getIndivduo(0).getGenes());

			// verifica se tem a solucao
			temSolucao = populacao.temSolocao(Algoritimo.getSolucao());
		}

		if (geracao == numMaxGeracoes) {
			listSolucacao.add("Número Maximo de Gerações | " + populacao.getIndivduo(0).getGenes() + " " + populacao.getIndivduo(0).getAptidao());
		}

		if (temSolucao) {
			listSolucacao.add("Encontrado resultado na geração " + geracao + " | " + populacao.getIndivduo(0).getGenes() + " (Aptidão: " + populacao.getIndivduo(0).getAptidao() + ")");
		}

		protocol = new Protocol('s', MessageHelper.get("Genetica modificada"), listSolucacao, 1);

		renderJSON(protocol);

	}

	/**
	 * retorna a lista de alocação por salas
	 */

	public static void alocacao() {

		List<AlocacaoInterface> listAi = new ArrayList<AlocacaoInterface>();

		List<Sala> listSalas = Sala.findAll();

		for (Sala sala : listSalas) {

			AlocacaoInterface ai = new AlocacaoInterface();

			ai.sala = sala;

			ai.alocacao = new ArrayList<AlocacaoSemana>();

			List<Horario> listHorarios = Horario.findAll();

			for (int i = 1; i <= listHorarios.size(); i++) {

				AlocacaoSemana as = new AlocacaoSemana();

				as.horario = listHorarios.get(i - 1);

				as.segunda = Alocacao.find("sala.id = " + sala.id + "AND dia = 1 AND horario = " + listHorarios.get(i - 1).id).first();

				as.terca = Alocacao.find("sala.id = " + sala.id + "AND dia = 2 AND horario = " + listHorarios.get(i - 1).id).first();

				as.quarta = Alocacao.find("sala.id = " + sala.id + "AND dia = 3 AND horario = " + listHorarios.get(i - 1).id).first();

				as.quinta = Alocacao.find("sala.id = " + sala.id + "AND dia = 4 AND horario = " + listHorarios.get(i - 1).id).first();

				as.sexta = Alocacao.find("sala.id = " + sala.id + "AND dia = 5 AND horario = " + listHorarios.get(i - 1).id).first();

				as.sabado = Alocacao.find("sala.id = " + sala.id + "AND dia = 6 AND horario = " + listHorarios.get(i - 1).id).first();

				as.domingo = Alocacao.find("sala.id = " + sala.id + "AND dia = 7 AND horario = " + listHorarios.get(i - 1).id).first();

				ai.alocacao.add(as);
			}

			listAi.add(ai);

		}

		if (listAi.size() == 0)

			protocol = new Protocol('a', MessageHelper.get("LIST_EMPTY", "alocacao"), listAi, listAi.size());

		else

			protocol = new Protocol('s', MessageHelper.get("LIST_OK", "alocacao"), listAi, listAi.size());


		renderJSON(protocol);
	}

	/**
	 * Renderiza o relatorio na tela
	 */
	public static void relatorio() {
		renderTemplate("relatorio.html");
	}

	/**
	 * Renderiza a interface de turno
	 */
	public static void view() {
		renderTemplate("algoritimo.html");
	}

}
