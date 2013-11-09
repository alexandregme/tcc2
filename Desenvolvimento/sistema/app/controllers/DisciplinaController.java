package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Disciplina;
import models.DisciplinaHorario;
import models.DisciplinaInterface;
import models.DisciplinaSemana;
import models.Horario;
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

			DisciplinaInterface di = RequestSerializer.get(request.body, DisciplinaInterface.class);

			Disciplina d = di.disciplina;

			d.save();

			saveListaSemana(di.listSemana, d);

			protocol = new Protocol('s', MessageHelper.get("INSERT_OK", complement), di, 1);

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

			DisciplinaInterface di = RequestSerializer.get(request.body, DisciplinaInterface.class);

			Disciplina d = Disciplina.findById(di.disciplina.id);

			d.periodo = di.disciplina.periodo;

			d.vagas = di.disciplina.vagas;

			d.codigo = di.disciplina.codigo;

			d.nome = di.disciplina.nome;

			d.turma = di.disciplina.turma;

			d.save();

			saveListaSemana(di.listSemana, d);

			protocol = new Protocol('s', MessageHelper.get("UPDATE_OK", complement), d, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("UPDATE_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	/**
	 * Salva o horario da semana no banco
	 */
	static void saveListaSemana(List<DisciplinaSemana> listSemana, Disciplina d) {

		DisciplinaHorario.delete("disciplina.id", d.id);

		for (DisciplinaSemana ds : listSemana) {

			DisciplinaHorario segunda = new DisciplinaHorario();
			segunda.disciplina = d;
			segunda.horario = Horario.findById(ds.horario.id);
			segunda.dia = ds.segunda.dia;
			segunda.alocado = ds.segunda.alocado;
			segunda.save();

			DisciplinaHorario terca = new DisciplinaHorario();
			terca.disciplina = d;
			terca.horario = Horario.findById(ds.horario.id);
			terca.dia = ds.terca.dia;
			terca.alocado = ds.terca.alocado;
			terca.save();

			DisciplinaHorario quarta = new DisciplinaHorario();
			quarta.disciplina = d;
			quarta.horario = Horario.findById(ds.horario.id);
			quarta.dia = ds.quarta.dia;
			quarta.alocado = ds.quarta.alocado;
			quarta.save();

			DisciplinaHorario quinta = new DisciplinaHorario();
			quinta.disciplina = d;
			quinta.horario = Horario.findById(ds.horario.id);
			quinta.dia = ds.quinta.dia;
			quinta.alocado = ds.quinta.alocado;
			quinta.save();

			DisciplinaHorario sexta = new DisciplinaHorario();
			sexta.disciplina = d;
			sexta.horario = Horario.findById(ds.horario.id);
			sexta.dia = ds.sexta.dia;
			sexta.alocado = ds.sexta.alocado;
			sexta.save();

			DisciplinaHorario sabado = new DisciplinaHorario();
			sabado.disciplina = d;
			sabado.horario = Horario.findById(ds.horario.id);
			sabado.dia = ds.sabado.dia;
			sabado.alocado = ds.sabado.alocado;
			sabado.save();

			DisciplinaHorario domingo = new DisciplinaHorario();
			domingo.disciplina = d;
			domingo.horario = Horario.findById(ds.horario.id);
			domingo.dia = ds.domingo.dia;
			domingo.alocado = ds.domingo.alocado;
			domingo.save();

		}
	}

	/**
	 * Deleta uma disciplina no banco
	 */
	public static void delete() {

		try {

			Disciplina d = RequestSerializer.get(request.body, Disciplina.class);

			DisciplinaHorario.delete("disciplina.id", d.id);

			Disciplina disciplina = Disciplina.findById(d.id);

			disciplina.delete();

			protocol = new Protocol('s', MessageHelper.get("DELETE_OK", complement), disciplina, 1);

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("DELETE_ERROR", complement), null, 0);

		}

		renderJSON(protocol);

	}

	public static void getHorarios() {

		try {

			Disciplina disciplina = RequestSerializer.get(request.body, Disciplina.class);

			List<DisciplinaSemana> listSemana = new ArrayList<DisciplinaSemana>();

			List<Horario> listHorarios = Horario.findAll();

			for (int i = 1; i <= listHorarios.size(); i++) {

				DisciplinaSemana ds = new DisciplinaSemana();

				ds.horario = listHorarios.get(i - 1);

				ds.segunda = DisciplinaHorario.find("disciplina.id = " + disciplina.id + "AND dia = 1 AND horario = " + listHorarios.get(i - 1).id).first();

				ds.terca = DisciplinaHorario.find("disciplina.id = " + disciplina.id + "AND dia = 2 AND horario = " + listHorarios.get(i - 1).id).first();

				ds.quarta = DisciplinaHorario.find("disciplina.id = " + disciplina.id + "AND dia = 3 AND horario = " + listHorarios.get(i - 1).id).first();

				ds.quinta = DisciplinaHorario.find("disciplina.id = " + disciplina.id + "AND dia = 4 AND horario = " + listHorarios.get(i - 1).id).first();

				ds.sexta = DisciplinaHorario.find("disciplina.id = " + disciplina.id + "AND dia = 5 AND horario = " + listHorarios.get(i - 1).id).first();

				ds.sabado = DisciplinaHorario.find("disciplina.id = " + disciplina.id + "AND dia = 6 AND horario = " + listHorarios.get(i - 1).id).first();

				ds.domingo = DisciplinaHorario.find("disciplina.id = " + disciplina.id + "AND dia = 7 AND horario = " + listHorarios.get(i - 1).id).first();

				listSemana.add(ds);
			}

			protocol = new Protocol('s', MessageHelper.get("LIST_OK", complement), listSemana, listSemana.size());

		} catch (Exception e) {

			e.printStackTrace();

			protocol = new Protocol('e', MessageHelper.get("LIST_ERROR", complement), null, 0);

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
