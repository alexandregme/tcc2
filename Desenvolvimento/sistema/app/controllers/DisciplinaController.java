package controllers;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.bouncycastle.crypto.DSA;

import models.Disciplina;
import models.Periodo;
import play.mvc.Controller;
import utils.MessageHelper;
import utils.Protocol;
import utils.RequestSerializer;

/**
 * @author Alexandre Gonzaga Mendes 27/09/2013
 */
public class DisciplinaController extends Controller {

	final static String complement = "disciplina";
	private static Protocol protocol = null;
	
	/**
	 * Salva uma disciplina no banco
	 */
	public static void save() {

		try {

			Disciplina d = RequestSerializer.get(request.body, Disciplina.class);

			d.save();

			protocol = new Protocol('s', MessageHelper.get("INSERT_OK", complement), d, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("INSERT_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Altera uma disciplina no banco
	 */
	public static void edit() {

		try {

			Disciplina d = RequestSerializer.get(request.body, Disciplina.class);

			Disciplina disciplina = Disciplina.findById(d.id);

			disciplina.periodo = d.periodo;
			
			disciplina.vagas = d.vagas;
			
			disciplina.codigo = d.codigo;
			
			disciplina.nome = d.nome;
			
			disciplina.turma = d.turma;

			disciplina.save();

			protocol = new Protocol('s', MessageHelper.get("UPDATE_OK", complement), disciplina, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("UPDATE_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Deleta uma disciplina no banco
	 */
	public static void delete() {

		try {

			Disciplina d = RequestSerializer.get(request.body, Disciplina.class);

			Disciplina disciplina = Disciplina.findById(d.id);

			disciplina.delete();

			protocol = new Protocol('s', MessageHelper.get("DELETE_OK", complement), disciplina, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("DELETE_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Lista todos os registros de disciplina do banco
	 */
	public static void list() {

		try {

			List<Disciplina> listDisciplina = Disciplina.findAll();

			if (listDisciplina.size() == 0)
				protocol = new Protocol('e', MessageHelper.get("LIST_EMPTY", complement), listDisciplina, listDisciplina.size());
			else
				protocol = new Protocol('s', MessageHelper.get("LIST_OK", complement), listDisciplina, listDisciplina.size());

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("LIST_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Renderiza a interface de disciplina
	 */
	public static void view() {
		renderTemplate("disciplina.html");
	}

}
