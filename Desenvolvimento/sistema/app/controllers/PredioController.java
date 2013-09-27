package controllers;

import java.util.List;

import models.Predio;
import play.mvc.Controller;
import utils.MessageHelper;
import utils.Protocol;
import utils.RequestSerializer;

/**
 * @author Alexandre Gonzaga Mendes 27/09/2013
 */
public class PredioController extends Controller {

	final static String complement = "prédio";
	private static Protocol protocol = null;

	/**
	 * Salva um predio no banco
	 */
	public static void save() {

		try {

			Predio p = RequestSerializer.get(request.body, Predio.class);

			p.save();

			protocol = new Protocol('s', MessageHelper.get("INSERT_OK", complement), p, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("INSERT_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Altera um predio no banco
	 */
	public static void edit() {

		try {

			Predio p = RequestSerializer.get(request.body, Predio.class);

			Predio predio = Predio.findById(p.id);

			predio.nome = p.nome;

			predio.descricao = p.descricao;

			predio.save();

			protocol = new Protocol('s', MessageHelper.get("UPDATE_OK", complement), predio, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("UPDATE_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Deleta um predio no banco
	 */
	public static void delete() {

		try {

			Predio p = RequestSerializer.get(request.body, Predio.class);

			Predio predio = Predio.findById(p.id);

			predio.delete();

			protocol = new Protocol('s', MessageHelper.get("DELETE_OK", complement), predio, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("DELETE_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Lista todos os registros de predio no banco
	 */
	public static void list() {

		try {

			List<Predio> listPredio = Predio.findAll();

			if (listPredio.size() == 0)
				protocol = new Protocol('e', MessageHelper.get("LIST_EMPTY", complement), listPredio, listPredio.size());
			else
				protocol = new Protocol('s', MessageHelper.get("LIST_OK", complement), listPredio, listPredio.size());

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("LIST_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Renderiza a interface de predio
	 */
	public static void view() {
		// renderTemplate("usuario.html", parameters);
	}

}