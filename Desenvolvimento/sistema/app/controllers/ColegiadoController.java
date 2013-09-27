package controllers;

import java.util.List;

import models.Colegiado;
import play.mvc.Controller;
import utils.MessageHelper;
import utils.Protocol;
import utils.RequestSerializer;

/**
 * @author Alexandre Gonzaga Mendes 27/09/2013
 */
public class ColegiadoController extends Controller {

	final static String complement = "colegiado";
	private static Protocol protocol = null;

	/**
	 * Salva um colegiado no banco
	 */
	public static void save() {

		try {

			Colegiado c = RequestSerializer.get(request.body, Colegiado.class);

			c.save();

			protocol = new Protocol('s', MessageHelper.get("INSERT_OK", complement), c, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("INSERT_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Altera um colegiado no banco
	 */
	public static void edit() {

		try {

			Colegiado c = RequestSerializer.get(request.body, Colegiado.class);

			Colegiado colegiado = Colegiado.findById(c.id);

			colegiado.curso = c.curso;

			colegiado.descricao = c.descricao;

			colegiado.save();

			protocol = new Protocol('s', MessageHelper.get("UPDATE_OK", complement), colegiado, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("UPDATE_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Deleta um colegiado no banco
	 */
	public static void delete() {

		try {

			Colegiado c = RequestSerializer.get(request.body, Colegiado.class);

			Colegiado colegiado = Colegiado.findById(c.id);

			colegiado.delete();

			protocol = new Protocol('s', MessageHelper.get("DELETE_OK", complement), colegiado, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("DELETE_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Lista todos os registros de colegiado do banco
	 */
	public static void list() {

		try {

			List<Colegiado> listColegiado = Colegiado.findAll();

			if (listColegiado.size() == 0)
				protocol = new Protocol('e', MessageHelper.get("LIST_EMPTY", complement), listColegiado, listColegiado.size());
			else
				protocol = new Protocol('s', MessageHelper.get("LIST_OK", complement), listColegiado, listColegiado.size());

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("LIST_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Renderiza a interface de colegiado
	 */
	public static void view() {
		// renderTemplate("usuario.html", parameters);
	}

}