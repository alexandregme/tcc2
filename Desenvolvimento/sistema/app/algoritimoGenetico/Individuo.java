package algoritimoGenetico;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.Disciplina;
import models.DisciplinaHorario;
import models.Horario;
import models.Parametros;
import models.Sala;

public class Individuo {

	public List<Gene> cromossomo;

	public Integer fitness = 0;

	public String genoma = "";

	private Parametros parametros;

	public Individuo(Parametros p) {

		parametros = p;

		create();

		fitness();

	}// fim método Individuo

	public void create() {

		cromossomo = new ArrayList<Gene>();

		for (Sala sala : parametros.listSalas) {

			for (int dia = 1; dia < 8; dia++) {

				for (Horario horario : parametros.listHorarios) {

					Gene g = new Gene();

					g.sala = sala;

					g.horario = horario;

					g.diaSemana = dia;

					g.disciplinaHorario = null;

					cromossomo.add(g);

				}// fim for horarios

			}// fim for dias

		}// fim for salas

		fitness();

	}
	
	public void populate(){

		for (int i = 0; i < parametros.listHorarioDisciplina.size(); i++) {

			cromossomo.get(i).disciplinaHorario = parametros.listHorarioDisciplina.get(i);
		}

		fitness();

	}

	public void fitness() {

		int somatorioPesos = 0;
		int countTotalAlocados = 0, countNecessario = 0;

		int fitness02 = 0;
		int fitness01 = 0;

		for (Gene g : cromossomo) {

			if (g.disciplinaHorario != null) {
				
				if ((g.horario.id == g.disciplinaHorario.horario.id) && (g.diaSemana == g.disciplinaHorario.dia)) {

					countTotalAlocados++;

				}

			}

		}

		if (countTotalAlocados == 0)

			fitness01 = 0;

		else

			fitness01 = (countTotalAlocados * 100) / parametros.listHorarioDisciplina.size();

		// penalidade para disciplinas com alocação indevida

//		for (Disciplina d : parametros.listDisciplinas) {
//
//			countNecessario = 0;
//
//			countTotalAlocados = 0;
//
//			for (DisciplinaHorario hp : parametros.listHorarioDisciplina) {
//
//				if (hp.disciplina.id == d.id)
//
//					countNecessario++;
//
//			}
//
//			for (Gene g : cromossomo) {
//
//				if ((g.disciplinaHorario != null) && (g.disciplinaHorario.disciplina.id == d.id))
//
//					countTotalAlocados++;
//			}
//
//			if (countTotalAlocados > countNecessario) {
//				fitness02 -= (parametros.listHorarioDisciplina.size() * 100) / (countTotalAlocados - countNecessario);
//			}
//
//		}

		// penalidades
		
		fitness = fitness01;
		
		print();

		//System.out.println(fitness);

	}

	public void print() {
		genoma = "";

		for (Gene g : cromossomo) {

			if (g.disciplinaHorario == null) {

				genoma += "0";

			} else {

				genoma += "1";

			}

		}

	}// fim método print
}
