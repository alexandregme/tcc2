package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Disciplina;
import models.DisciplinaHorario;
import models.DisciplinaSemana;
import models.Horario;
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
			for (Horario horario : horarios) {
				for (int i = 1; i <= 7; i++) {
					DisciplinaHorario dh = new DisciplinaHorario();
					dh.disciplina = d;
					dh.horario = horario;
					dh.dia = i;
					dh.alocado = false;
					dh.save();
				}
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	/**
	 * Gera uma grade de horarios vazia sem disciplina
	 */
	public static void getGrade() {

		try {

			List<DisciplinaSemana> listDS = new ArrayList<DisciplinaSemana>();

			List<Horario> listHorarios = Horario.findAll();

			for (int i = 1; i <= listHorarios.size(); i++) {

				DisciplinaSemana ds = new DisciplinaSemana();

				ds.horario = listHorarios.get(i - 1);

				ds.segunda = new DisciplinaHorario();
				ds.segunda.disciplina = null;
				ds.segunda.horario = listHorarios.get(i - 1);
				ds.segunda.dia = 1;
				ds.segunda.alocado = false;

				ds.terca = new DisciplinaHorario();
				ds.terca.disciplina = null;
				ds.terca.horario = listHorarios.get(i - 1);
				ds.terca.dia = 2;
				ds.terca.alocado = false;

				ds.quarta = new DisciplinaHorario();
				ds.quarta.disciplina = null;
				ds.quarta.horario = listHorarios.get(i - 1);
				ds.quarta.dia = 3;
				ds.quarta.alocado = false;

				ds.quinta = new DisciplinaHorario();
				ds.quinta.disciplina = null;
				ds.quinta.horario = listHorarios.get(i - 1);
				ds.quinta.dia = 4;
				ds.quinta.alocado = false;

				ds.sexta = new DisciplinaHorario();
				ds.sexta.disciplina = null;
				ds.sexta.horario = listHorarios.get(i - 1);
				ds.sexta.dia = 5;
				ds.sexta.alocado = false;

				ds.sabado = new DisciplinaHorario();
				ds.sabado.disciplina = null;
				ds.sabado.horario = listHorarios.get(i - 1);
				ds.sabado.dia = 6;
				ds.sabado.alocado = false;

				ds.domingo = new DisciplinaHorario();
				ds.domingo.disciplina = null;
				ds.domingo.horario = listHorarios.get(i - 1);
				ds.domingo.dia = 7;
				ds.domingo.alocado = false;
				listDS.add(ds);
			}

			protocol = new Protocol('s', MessageHelper.get("LIST_OK", complement), listDS, listDS.size());

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("LIST_ERROR", complement), null, 0);

		}

		renderJSON(protocol);
	}
}
