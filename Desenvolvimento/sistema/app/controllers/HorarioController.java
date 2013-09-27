package controllers;

import java.util.Calendar;
import java.util.List;

import models.Alocacao;
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
	static void configurarHorarios() {

		try {

			Alocacao.deleteAll();
			DisciplinaHorario.deleteAll();
			Horario.deleteAll();
			Turno.deleteAll();

			Calendar c = Calendar.getInstance();

			Turno manha = new Turno();
			manha.descricao = "Manhã";
			manha.save();

			Turno tarde = new Turno();
			tarde.descricao = "Tarde";
			tarde.save();

			Turno noite = new Turno();
			noite.descricao = "Noite";
			noite.save();

			// horarios manhã
			Horario h1 = new Horario();
			h1.turno = manha;
			c.set(0, 0, 0, 7, 30);
			h1.horarioDe = c.getTime();
			c.set(0, 0, 0, 8, 20);
			h1.horarioAte = c.getTime();
			h1.save();

			Horario h2 = new Horario();
			h2.turno = manha;
			c.set(0, 0, 0, 8, 20);
			h2.horarioDe = c.getTime();
			c.set(0, 0, 0, 9, 10);
			h2.horarioAte = c.getTime();
			h2.save();

			Horario h3 = new Horario();
			h3.turno = manha;
			c.set(0, 0, 0, 9, 30);
			h3.horarioDe = c.getTime();
			c.set(0, 0, 0, 10, 20);
			h3.horarioAte = c.getTime();
			h3.save();

			Horario h4 = new Horario();
			h4.turno = manha;
			c.set(0, 0, 0, 10, 20);
			h4.horarioDe = c.getTime();
			c.set(0, 0, 0, 11, 10);
			h4.horarioAte = c.getTime();
			h4.save();

			Horario h5 = new Horario();
			h5.turno = manha;
			c.set(0, 0, 0, 11, 10);
			h5.horarioDe = c.getTime();
			c.set(0, 0, 0, 12, 00);
			h5.horarioAte = c.getTime();
			h5.save();

			// horarios tarde
			Horario h6 = new Horario();
			h6.turno = tarde;
			c.set(0, 0, 0, 13, 00);
			h6.horarioDe = c.getTime();
			c.set(0, 0, 0, 13, 45);
			h6.horarioAte = c.getTime();
			h6.save();

			Horario h7 = new Horario();
			h7.turno = tarde;
			c.set(0, 0, 0, 13, 50);
			h7.horarioDe = c.getTime();
			c.set(0, 0, 0, 14, 40);
			h7.horarioAte = c.getTime();
			h7.save();

			Horario h8 = new Horario();
			h8.turno = tarde;
			c.set(0, 0, 0, 14, 50);
			h8.horarioDe = c.getTime();
			c.set(0, 0, 0, 15, 40);
			h8.horarioAte = c.getTime();
			h8.save();

			Horario h9 = new Horario();
			h9.turno = tarde;
			c.set(0, 0, 0, 15, 40);
			h9.horarioDe = c.getTime();
			c.set(0, 0, 0, 16, 30);
			h9.horarioAte = c.getTime();
			h9.save();

			Horario h10 = new Horario();
			h10.turno = tarde;
			c.set(0, 0, 0, 16, 30);
			h10.horarioDe = c.getTime();
			c.set(0, 0, 0, 17, 20);
			h10.horarioAte = c.getTime();
			h10.save();

			Horario h11 = new Horario();
			h11.turno = tarde;
			c.set(0, 0, 0, 17, 20);
			h11.horarioDe = c.getTime();
			c.set(0, 0, 0, 18, 10);
			h11.horarioAte = c.getTime();
			h11.save();

			// horarios noite
			Horario h12 = new Horario();
			h12.turno = noite;
			c.set(0, 0, 0, 19, 00);
			h12.horarioDe = c.getTime();
			c.set(0, 0, 0, 19, 50);
			h12.horarioAte = c.getTime();
			h12.save();

			Horario h13 = new Horario();
			h13.turno = noite;
			c.set(0, 0, 0, 19, 50);
			h13.horarioDe = c.getTime();
			c.set(0, 0, 0, 20, 40);
			h13.horarioAte = c.getTime();
			h13.save();

			Horario h14 = new Horario();
			h14.turno = noite;
			c.set(0, 0, 0, 20, 50);
			h14.horarioDe = c.getTime();
			c.set(0, 0, 0, 21, 40);
			h14.horarioAte = c.getTime();
			h14.save();

			Horario h15 = new Horario();
			h15.turno = noite;
			c.set(0, 0, 0, 21, 40);
			h15.horarioDe = c.getTime();
			c.set(0, 0, 0, 22, 30);
			h15.horarioAte = c.getTime();
			h15.save();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
