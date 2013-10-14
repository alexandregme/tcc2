package algoritimoGenetico;

import models.DisciplinaHorario;
import models.Horario;
import models.Sala;

public class Gene implements Cloneable {

	private Sala sala = null;

	private Integer diaSemana = null;

	private Horario horario = null;

	private DisciplinaHorario disciplinaHorario = null;

	public Gene(Sala s, Integer d, Horario h, DisciplinaHorario dh) {

		this.sala = s;

		this.diaSemana = d;

		this.horario = h;

		this.disciplinaHorario = dh;

	}

	public Gene(Gene g) {

		this.sala = g.sala;

		this.diaSemana = g.diaSemana;

		this.horario = g.horario;

		this.disciplinaHorario = g.disciplinaHorario;

	}

	public DisciplinaHorario getDisciplinaHorario() {

		return this.disciplinaHorario;

	}

	public Horario getHorario() {

		return this.horario;

	}

	public Integer getDiaSemana() {

		return this.diaSemana;

	}

	public Sala getSala() {

		return this.sala;

	}

	public void setDisciplinaHorario(DisciplinaHorario dh) {

		this.disciplinaHorario = dh;

	}

	@Override
	protected Object clone() throws CloneNotSupportedException {

		return super.clone();

	}
}
