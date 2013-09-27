package controllers;

import java.util.List;

import models.Curso;
import play.mvc.Controller;
import utils.MessageHelper;
import utils.Protocol;
import utils.RequestSerializer;

/**
 * @author Alexandre Gonzaga Mendes 25/09/2013
 */
public class CursoController extends Controller {

	final static String complement = "curso";
	private static Protocol protocol = null;

	/**
	 * Salva um curso no banco.
	 */
	public static void save() {

		try {

			Curso c = RequestSerializer.get(request.body, Curso.class);

			c.save();

			protocol = new Protocol('s', MessageHelper.get("INSERT_OK", complement), c, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("INSERT_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Altera um curso no banco
	 */
	public static void edit() {
		try {

			Curso c = RequestSerializer.get(request.body, Curso.class);

			Curso curso = Curso.findById(c.id);

			curso.nome = c.nome;

			curso.descricao = c.descricao;

			curso.save();

			protocol = new Protocol('s', MessageHelper.get("UPDATE_OK", complement), curso, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("UPDATE_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Deleta um curso no banco
	 */
	public static void delete() {

		try {

			Curso c = RequestSerializer.get(request.body, Curso.class);

			Curso curso = Curso.findById(c.id);

			curso.delete();

			protocol = new Protocol('s', MessageHelper.get("DELETE_OK", complement), curso, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("DELETE_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Lista todos os registros de curso do banco
	 */
	public static void list() {

		try {

			List<Curso> listCurso = Curso.findAll();

			if (listCurso.size() == 0)
				protocol = new Protocol('e', MessageHelper.get("LIST_EMPTY", complement), listCurso, listCurso.size());
			else
				protocol = new Protocol('s', MessageHelper.get("LIST_OK", complement), listCurso, listCurso.size());

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("LIST_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Renderiza a interface de curso
	 */
	public static void view() {
		renderTemplate("curso.html");
	}
}