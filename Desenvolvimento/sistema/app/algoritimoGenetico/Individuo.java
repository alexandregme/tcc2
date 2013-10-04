package algoritimoGenetico;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.Horario;
import models.Sala;

public class Individuo {

	public List<Gene> cromossomo;

	public Integer fitness;
	
	public String genoma;

	
	public Individuo() {

		create();

		fitness();

	}// fim método Individuo

	public Individuo(List<Gene> c) {

		cromossomo = c;
		fitness();

	}// fim método Individuo

	public void create(){
		cromossomo = new ArrayList<Gene>();

		List<Sala> salas = Sala.findAll();
		List<Horario> horarios = Horario.findAll();

		for (Sala sala : salas) {
			for (int dia = 1; dia < 8; dia++) {
				for (Horario horario : horarios) {
					Gene g = new Gene();
					g.sala = sala;
					g.horario = horario;
					g.diaSemana = dia;
					g.disciplina = null;
					cromossomo.add(g);
				}// fim for horarios
			}// fim for dias
		}// fim for salas
		fitness();
		print();

	}
	public void fitness() {

		Random r = new Random();
		fitness = r.nextInt(8);

	}

	public void print() {
		genoma = "";
		
		for (Gene g : cromossomo) {
			if (g.disciplina == null) {
				//genoma += g.sala.id + " " + g.horario.id + " " + g.diaSemana + " " + "0";
				genoma +="0";
			} else {
				genoma +="1";
			}
		}
	}// fim método print
}
