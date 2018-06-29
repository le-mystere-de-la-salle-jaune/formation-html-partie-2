package dev.pizzeria.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.pizzeria.service.Helpers;

/**
 * Contrôleur responsable du traitement de la réquête : POST /pizzas.
 */
@SuppressWarnings("serial")
public class PizzaController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);

	/**
	 * Page HTML de la réponse en cas d'insertion effectuée. Fichier présent
	 * dans le répertoire src/main/resources.
	 */
	public static final String TEMPLATE_PIZZA_INSERE = "templates/pizza_inseree.html";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// récupération du paramètre {truc}
		// <input name="truc">
		String libelle = req.getParameter("libelle");
		String reference = req.getParameter("reference");
		Double prix = Double.valueOf(req.getParameter("prix"));
		String photo = req.getParameter("age");

		LOGGER.info("Paramètre libelle reçu " + libelle);
		LOGGER.info("Paramètre référence reçu " + reference);
		LOGGER.info("Paramètre prix reçu " + prix);
		LOGGER.info("Paramètre photo reçu " + photo);

		// TODO insérer un nouveau client
		Helpers.PIZZERIA_SERVICE.sauverPizza(libelle, reference, prix, photo);

		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_PIZZA_INSERE).toURI()))
					.stream().collect(Collectors.joining());

			String templateModifie = template.replace("{{libelle}}", libelle);

			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(templateModifie);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}
	}
}