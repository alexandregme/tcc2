package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import algoritimoGenetico.Algoritimo;
import algoritimoGenetico.Populacao;

import models.*;

public class Application extends Controller {

	public static void index() {

		// HorarioController.configurarHorarios();

		 renderTemplate("index.html");

	}
}