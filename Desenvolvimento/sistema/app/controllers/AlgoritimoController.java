package controllers;

import java.util.ArrayList;
import java.util.List;

import algoritimoGenetico.Algoritimo;
import algoritimoGenetico.Populacao;
import algoritimoGenetico.Populacaobackup;

import models.Turno;
import play.mvc.Controller;
import utils.MessageHelper;
import utils.Protocol;
import utils.RequestSerializer;

/**
 * @author Alexandre Gonzaga Mendes 27/09/2013
 */
public class AlgoritimoController extends Controller {

	final static String complement = "turno";
	private static Protocol protocol = null;

	/**
	 * Salva um turno no banco
	 */
	public static void save() {

		try {

			Turno t = RequestSerializer.get(request.body, Turno.class);

			t.save();

			protocol = new Protocol('s', MessageHelper.get("INSERT_OK", complement), t, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("INSERT_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Altera um turno no banco
	 */
	public static void edit() {

		try {

			Turno t = RequestSerializer.get(request.body, Turno.class);

			Turno turno = Turno.findById(t.id);

			turno.descricao = t.descricao;

			turno.save();

			protocol = new Protocol('s', MessageHelper.get("UPDATE_OK", complement), turno, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("UPDATE_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Deleta
	 */
	public static void delete() {

		try {

			Turno t = RequestSerializer.get(request.body, Turno.class);

			Turno turno = Turno.findById(t.id);

			turno.delete();

			protocol = new Protocol('s', MessageHelper.get("DELETE_OK", complement), turno, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("DELETE_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Lista todos os registros de turno do banco
	 */
	public static void list() {

		try {

			List<Turno> listTurno = Turno.findAll();

			if (listTurno.size() == 0)
				protocol = new Protocol('e', MessageHelper.get("LIST_EMPTY", complement), listTurno, listTurno.size());
			else
				protocol = new Protocol('s', MessageHelper.get("LIST_OK", complement), listTurno, listTurno.size());

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("LIST_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	public static void result() {

		Populacao p = new Populacao();

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
	 * Renderiza a interface de turno
	 */
	public static void view() {
		renderTemplate("algoritimo.html");
	}

}
