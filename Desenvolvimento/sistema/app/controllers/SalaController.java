package controllers;

import java.util.List;

import models.Sala;
import play.mvc.Controller;
import utils.MessageHelper;
import utils.Protocol;
import utils.RequestSerializer;

/**
 * @author Alexandre Gonzaga Mendes 27/09/2013
 */
public class SalaController extends Controller {

	final static String complement = "sala";
	private static Protocol protocol = null;

	/**
	 * Salva uma sala no banco
	 */
	public static void save() {

		try {

			Sala s = RequestSerializer.get(request.body, Sala.class);

			s.save();

			protocol = new Protocol('s', MessageHelper.get("INSERT_OK", complement), s, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("INSERT_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Altera uma sala no banco
	 */
	public static void edit() {

		try {

			Sala s = RequestSerializer.get(request.body, Sala.class);

			Sala sala = Sala.findById(s.id);

			sala.predio = s.predio;

			sala.andar = s.andar;

			sala.vagas = s.vagas;

			sala.nome = s.nome;

			sala.iluminacao = s.iluminacao.toString();

			sala.ativa = s.ativa.toString();

			sala.save();

			protocol = new Protocol('s', MessageHelper.get("UPDATE_OK", complement), sala, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("UPDATE_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Deleta uma sala no banco
	 */
	public static void delete() {

		try {

			Sala s = RequestSerializer.get(request.body, Sala.class);

			Sala sala = Sala.findById(s.id);

			sala.delete();

			protocol = new Protocol('s', MessageHelper.get("DELETE_OK", complement), sala, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("DELETE_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Lista todos os registros de sala do banco
	 */
	public static void list() {

		try {

			List<Sala> listSala = Sala.findAll();

			if (listSala.size() == 0)
				protocol = new Protocol('e', MessageHelper.get("LIST_EMPTY", complement), listSala, listSala.size());
			else
				protocol = new Protocol('s', MessageHelper.get("LIST_OK", complement), listSala, listSala.size());

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("LIST_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Renderiza a interface de sala
	 */
	public static void view() {
		renderTemplate("sala.html");
	}

}