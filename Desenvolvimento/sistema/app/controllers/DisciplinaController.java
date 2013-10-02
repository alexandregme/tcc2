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

			HorarioController.gerarHorarios(d);

			// for (DisciplinaHorario dh : di.listHorarios) {
			//
			// DisciplinaHorario dhfind = DisciplinaHorario.find("horario.id = "
			// + dh.horario.id + " AND disciplina.id = " +
			// dh.disciplina.id).first();
			// dhfind.alocado = true;
			// dhfind.save();
			//
			// }

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

			Disciplina disciplina = Disciplina.findById(di.disciplina.id);

			disciplina.periodo = di.disciplina.periodo;

			disciplina.vagas = di.disciplina.vagas;

			disciplina.codigo = di.disciplina.codigo;

			disciplina.nome = di.disciplina.nome;

			disciplina.turma = di.disciplina.turma;
			
			disciplina.save();
			
			DisciplinaHorario.delete("disciplina.id", di.disciplina.id);
			
			for (DisciplinaSemana ds : di.listSemana) {

				DisciplinaHorario segunda = new DisciplinaHorario();
				segunda.disciplina = disciplina;
				segunda.horario = Horario.findById(ds.horario.id);
				segunda.dia = ds.segunda.dia;
				segunda.alocado = ds.segunda.alocado;
				segunda.linha = ds.segunda.linha;
				segunda.save();
				
				DisciplinaHorario terca = new DisciplinaHorario();
				terca.disciplina = disciplina;
				terca.horario = Horario.findById(ds.horario.id);
				terca.dia = ds.terca.dia;
				terca.alocado = ds.terca.alocado;
				terca.linha = ds.terca.linha;
				terca.save();
				
				
				DisciplinaHorario quarta = new DisciplinaHorario();
				quarta.disciplina = disciplina;
				quarta.horario = Horario.findById(ds.horario.id);
				quarta.dia = ds.quarta.dia;
				quarta.alocado = ds.quarta.alocado;
				quarta.linha = ds.quarta.linha;
				quarta.save();
				
				DisciplinaHorario quinta = new DisciplinaHorario();
				quinta.disciplina = disciplina;
				quinta.horario = Horario.findById(ds.horario.id);
				quinta.dia = ds.quinta.dia;
				quinta.alocado = ds.quinta.alocado;
				quinta.linha = ds.quinta.linha;
				quinta.save();
				
				DisciplinaHorario sexta = new DisciplinaHorario();
				sexta.disciplina = disciplina;
				sexta.horario = Horario.findById(ds.horario.id);
				sexta.dia = ds.sexta.dia;
				sexta.alocado = ds.sexta.alocado;
				sexta.linha = ds.sexta.linha;
				sexta.save();
				
				DisciplinaHorario sabado = new DisciplinaHorario();
				sabado.disciplina = disciplina;
				sabado.horario = Horario.findById(ds.horario.id);
				sabado.dia = ds.sabado.dia;
				sabado.alocado = ds.sabado.alocado;
				sabado.linha = ds.sabado.linha;
				sabado.save();
				
				DisciplinaHorario domingo = new DisciplinaHorario();
				domingo.disciplina = disciplina;
				domingo.horario = Horario.findById(ds.horario.id);
				domingo.dia = ds.domingo.dia;
				domingo.alocado = ds.domingo.alocado;
				domingo.linha = ds.domingo.linha;
				domingo.save();

			}

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

	/**
	 * Lista todos os registros de disciplina do banco
	 */

	public static void list() {

		try {

			List<DisciplinaInterface> listInterface = new ArrayList<DisciplinaInterface>();

			List<Disciplina> listDisciplina = Disciplina.findAll();

			for (Disciplina disciplina : listDisciplina) {

				DisciplinaInterface di = new DisciplinaInterface();

				di.listSemana = new ArrayList<DisciplinaSemana>();

				di.disciplina = disciplina;

				List<Horario> listHorarios = Horario.findAll();

				for (int i = 1; i <= listHorarios.size(); i++) {

					DisciplinaSemana ds = new DisciplinaSemana();

					ds.horario = listHorarios.get(i - 1);

					ds.segunda = DisciplinaHorario.find("disciplina.id = " + disciplina.id + "AND dia = 1 AND linha = " + i).first();

					ds.terca = DisciplinaHorario.find("disciplina.id = " + disciplina.id + "AND dia = 2 AND linha = " + i).first();

					ds.quarta = DisciplinaHorario.find("disciplina.id = " + disciplina.id + "AND dia = 3 AND linha = " + i).first();

					ds.quinta = DisciplinaHorario.find("disciplina.id = " + disciplina.id + "AND dia = 4 AND linha = " + i).first();

					ds.sexta = DisciplinaHorario.find("disciplina.id = " + disciplina.id + "AND dia = 5 AND linha = " + i).first();

					ds.sabado = DisciplinaHorario.find("disciplina.id = " + disciplina.id + "AND dia = 6 AND linha = " + i).first();

					ds.domingo = DisciplinaHorario.find("disciplina.id = " + disciplina.id + "AND dia = 7 AND linha = " + i).first();

					di.listSemana.add(ds);
				}

				listInterface.add(di);

			}

			if (listInterface.size() == 0)

				protocol = new Protocol('e', MessageHelper.get("LIST_EMPTY", complement), listInterface, listInterface.size());

			else

				protocol = new Protocol('s', MessageHelper.get("LIST_OK", complement), listInterface, listInterface.size());

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
