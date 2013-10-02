package controllers;

import java.util.Calendar;
import java.util.List;

import org.joda.time.LocalDateTime;

import models.Alocacao;
import models.Disciplina;
import models.DisciplinaHorario;
import models.Horario;
import models.Turno;
import play.mvc.Controller;
import utils.MessageHelper;
import utils.Protocol;
import utils.RequestSerializer;

/**
 * @author Alexandre Gonzaga Mendes 27/09/2013
 */
public class HorarioController extends Controller {

	final static String complement = "horario";
	private static Protocol protocol = null;

	/**
	 * Salva um horario no banco
	 */
	public static void save() {

		try {
			Horario h = RequestSerializer.get(request.body, Horario.class);

			h.save();

			protocol = new Protocol('s', MessageHelper.get("INSERT_OK", complement), h, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("INSERT_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Altera um horario no banco
	 */
	public static void edit() {

		try {

			Horario h = RequestSerializer.get(request.body, Horario.class);

			Horario horario = Horario.findById(h.id);

			horario.turno = h.turno;

			horario.horarioDe = h.horarioDe;

			horario.horarioAte = h.horarioAte;

			horario.save();

			protocol = new Protocol('s', MessageHelper.get("UPDATE_OK", complement), horario, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("UPDATE_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Deleta um horario no banco
	 */
	public static void delete() {

		try {

			Horario h = RequestSerializer.get(request.body, Horario.class);

			Horario horario = Horario.findById(h.id);

			horario.delete();

			protocol = new Protocol('s', MessageHelper.get("DELETE_OK", complement), horario, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("DELETE_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Lista todos os registros de horario do banco
	 */
	public static void list() {

		try {

			List<Horario> listHorario = Horario.findAll();

			if (listHorario.size() == 0)
				protocol = new Protocol('e', MessageHelper.get("LIST_EMPTY", complement), listHorario, listHorario.size());
			else
				protocol = new Protocol('s', MessageHelper.get("LIST_OK", complement), listHorario, listHorario.size());

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("LIST_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Renderiza a interface de horario
	 */
	public static void view() {
		renderTemplate("horario.html");
	}

	/**
	 * Configura automaticamente os primeiros horarios
	 */
	static void gerarHorarios(Disciplina d) {

		try {

			List<Horario> horarios = Horario.findAll();
			// dias da semana
			int linha = 1;
			for (Horario horario : horarios) {
				for (int i = 1; i <= 7; i++) {
					DisciplinaHorario dh = new DisciplinaHorario();
					dh.disciplina = d;
					dh.horario = horario;
					dh.dia = i;
					dh.alocado = false;
					dh.linha = linha;
					dh.save();
				}
				linha++;
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
