package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Alocacao;
import models.AlocacaoInterface;
import models.AlocacaoSemana;
import models.Horario;
import models.Sala;
import play.mvc.Controller;
import utils.MessageHelper;
import utils.Protocol;
import algoritimoGenetico.Algoritimo;

/**
 * @author Alexandre Gonzaga Mendes 27/09/2013
 */
public class AlgoritimoController extends Controller {

	final static String complement = "algoritimoGenetico";
	private static Protocol protocol = null;

	public static void result() {

		Algoritimo a = new Algoritimo();

		protocol = new Protocol('s', MessageHelper.get("Genetica modificada"), a, 1);

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
