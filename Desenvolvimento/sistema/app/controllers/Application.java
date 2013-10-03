package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import algoritimoGenetico.Algoritimo;
import algoritimoGenetico.Populacaobackup;

import models.*;

public class Application extends Controller {

	public static void index() {

		renderTemplate("index.html");

	}
}