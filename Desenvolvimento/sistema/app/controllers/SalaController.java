package controllers;

import java.util.Hashtable;
import java.util.Map;

import play.mvc.Controller;
import utils.Protocol;

public class SalaController extends Controller {

	final static String nameController = "";
	final static String complement = "";
	private static Protocol protocol = null;
	private static Map<String, Object> parameters = new Hashtable<String, Object>();

	/**
	 * Salva
	 */
	public static void save() {

		try {

		} catch (Exception e) {

		}

		renderJSON(protocol);
	}

	/**
	 * Altera
	 */
	public static void edit() {
		try {
		} catch (Exception e) {
		}
		renderJSON(protocol);

	}

	/**
	 * Deleta
	 */
	public static void delete() {

		try {
		} catch (Exception e) {
		}
		renderJSON(protocol);
	}

	/**
	 * lista todos os registros no banco
	 */
	public static void list() {

	}

	/**
	 * Renderiza
	 */
	public static void view() {
		renderTemplate("usuario.html", parameters);
	}

}
