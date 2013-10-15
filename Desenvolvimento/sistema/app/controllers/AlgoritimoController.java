package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Alocacao;
import models.AlocacaoInterface;
import models.AlocacaoSemana;
import models.Horario;
import models.Parametros;
import models.Sala;
import net.sf.ehcache.hibernate.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import play.db.jpa.JPA;
import play.mvc.Controller;
import play.mvc.Scope.Session;
import utils.MessageHelper;
import utils.Protocol;
import utils.RequestSerializer;
import algoritimoGenetico.Algoritimo;

/**
 * @author Alexandre Gonzaga Mendes 27/09/2013
 */
public class AlgoritimoController extends Controller {

	final static String complement = "algoritimoGenetico";
	private static Protocol protocol = null;

	public static void result() throws CloneNotSupportedException {

		try {

			Parametros p = RequestSerializer.get(request.body, Parametros.class);

			Parametros parametros = Parametros.findById(p.id);

			parametros.elitismo = p.elitismo;

			parametros.taxaCrossover = p.taxaCrossover;

			parametros.taxaMutacao = p.taxaMutacao;

			parametros.tamanhoPopulacao = p.tamanhoPopulacao;

			parametros.numeroMaximoGeracoes = p.numeroMaximoGeracoes;

			parametros.pesoDiscliplinaAlocada = p.pesoDiscliplinaAlocada;

			parametros.pesoGraduacao = p.pesoGraduacao;

			parametros.pesoPos = p.pesoPos;

			parametros.pesoMesmoPeriodo = p.pesoMesmoPeriodo;

			parametros.pesoCapacidade = p.pesoCapacidade;

			parametros.pesoOptativa = p.pesoOptativa;

			parametros.pesoIluminacao = p.pesoIluminacao;

			parametros.save();

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("PARAMETROS_ERROR"), null, 0);

			renderJSON(protocol);
		}

		Algoritimo a = new Algoritimo();

		protocol = new Protocol('s', MessageHelper.get("Genetica modificada"), a, 1);

		renderJSON(protocol);

	}

	/**
	 * retorna a lista de alocação por salas
	 */

	public static void alocacao() {

		List<AlocacaoInterface> listAi = new ArrayList<AlocacaoInterface>();

		List<Sala> listSalas = JPA.em().createNativeQuery("SELECT * FROM Sala ORDER BY nome_sala", Sala.class).getResultList();

		for (Sala sala : listSalas) {

			AlocacaoInterface ai = new AlocacaoInterface();

			ai.sala = sala;

			ai.alocacao = new ArrayList<AlocacaoSemana>();

			List<Horario> listHorarios = Horario.findAll();

			for (int i = 0; i < listHorarios.size(); i++) {

				AlocacaoSemana as = new AlocacaoSemana();

				as.horario = listHorarios.get(i);

				as.segunda = Alocacao.find("sala.id = " + sala.id + "AND dia = 1 AND horario.id = " + listHorarios.get(i).id).first();

				as.terca = Alocacao.find("sala.id = " + sala.id + "AND dia = 2 AND horario.id = " + listHorarios.get(i).id).first();

				as.quarta = Alocacao.find("sala.id = " + sala.id + "AND dia = 3 AND horario.id = " + listHorarios.get(i).id).first();

				as.quinta = Alocacao.find("sala.id = " + sala.id + "AND dia = 4 AND horario.id = " + listHorarios.get(i).id).first();

				as.sexta = Alocacao.find("sala.id = " + sala.id + "AND dia = 5 AND horario.id = " + listHorarios.get(i).id).first();

				as.sabado = Alocacao.find("sala.id = " + sala.id + "AND dia = 6 AND horario.id = " + listHorarios.get(i).id).first();

				as.domingo = Alocacao.find("sala.id = " + sala.id + "AND dia = 7 AND horario.id = " + listHorarios.get(i).id).first();

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

	public static void parametroGet() {

		Parametros p = Parametros.findById(1);

		protocol = new Protocol('s', MessageHelper.get("LIST_OK", complement), p, 1);

		renderJSON(protocol);
	}

}
