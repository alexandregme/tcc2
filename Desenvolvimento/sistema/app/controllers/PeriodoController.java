package controllers;

import java.util.List;

import models.Periodo;
import play.mvc.Controller;
import utils.MessageHelper;
import utils.Protocol;
import utils.RequestSerializer;

/**
 * @author Alexandre Gonzaga Mendes 27/09/2013
 */
public class PeriodoController extends Controller {

	final static String complement = "periodo";
	private static Protocol protocol = null;

	/**
	 * Salva um periodo no banco
	 */
	public static void save() {

		try {

			Periodo p = RequestSerializer.get(request.body, Periodo.class);

			p.save();

			protocol = new Protocol('s', MessageHelper.get("INSERT_OK", complement), p, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("INSERT_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Altera um periodo no banco
	 */
	public static void edit() {

		try {

			Periodo p = RequestSerializer.get(request.body, Periodo.class);

			Periodo periodo = Periodo.findById(p.id);

			periodo.colegiado = p.colegiado;

			periodo.periodo = p.periodo;

			periodo.optativo = p.optativo;

			periodo.save();

			protocol = new Protocol('s', MessageHelper.get("UPDATE_OK", complement), periodo, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("UPDATE_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Deleta um periodo no banco
	 */
	public static void delete() {

		try {

			Periodo p = RequestSerializer.get(request.body, Periodo.class);

			Periodo periodo = Periodo.findById(p.id);

			periodo.delete();

			protocol = new Protocol('s', MessageHelper.get("DELETE_OK", complement), periodo, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("DELETE_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Lista todos os registros de periodo do banco
	 */
	public static void list() {

		try {

			List<Periodo> listPeriodo = Periodo.findAll();

			if (listPeriodo.size() == 0)
				protocol = new Protocol('e', MessageHelper.get("LIST_EMPTY", complement), listPeriodo, listPeriodo.size());
			else
				protocol = new Protocol('s', MessageHelper.get("LIST_OK", complement), listPeriodo, listPeriodo.size());

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("LIST_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Renderiza a interface de periodo
	 */
	public static void view() {
		renderTemplate("periodo.html");
	}

}