package controllers;

import java.util.List;

import models.Turno;
import play.mvc.Controller;
import utils.MessageHelper;
import utils.Protocol;
import utils.RequestSerializer;

/**
 * @author Alexandre Gonzaga Mendes 27/09/2013
 */
public class TurnoController extends Controller {

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

	/**
	 * Renderiza a interface de turno
	 */
	public static void view() {
		// renderTemplate("usuario.html", parameters);
	}

}
