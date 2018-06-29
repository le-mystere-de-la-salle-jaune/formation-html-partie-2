package dev.pizzeria.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dev.pizzeria.domain.Pizza;
import dev.pizzeria.service.Helpers;

public class PizzaController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);

	public static final String TEMPLATE_PIZZA_INSERE = "templates/pizza_insere.html";
	public static final String TEMPLATE_PIZZA_LIST = "templates/pizza_liste.html";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<UUID, Pizza> pizzas = Helpers.PIZZERIA_SERVICE.getPizzas();

		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_PIZZA_LIST).toURI()))
					.stream().collect(Collectors.joining());

			String templateModifie;
			if (pizzas.isEmpty()) {
				templateModifie = template.replace("{{liste_pizza}}", "");
			} 
			else {
				String strReplace = "";
				for (Map.Entry<UUID, Pizza> e : pizzas.entrySet()) {
					strReplace = strReplace + "<tr>" + "<td scope='row'>" + e.getValue().getUuid() + "</td>"
							+ "<td scope='row'>" + e.getValue().getLibelle() + "</td>" + "<td scope='row'>"
							+ e.getValue().getReference() + "</td>" + "<td scope='row'>" + e.getValue().getPrix()
							+ "</td>" + "<td scope='row'><a href=\"" + e.getValue().getUrl() + "\">Image</a></td>" + "</tr>";
				}

				templateModifie = template.replace("{{liste_pizza}}", strReplace);
			}
			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(templateModifie);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String libelle = req.getParameter("libelle");
		String reference = req.getParameter("reference");
		double prix = Double.parseDouble(req.getParameter("prix"));
		String url = req.getParameter("url");

		LOGGER.info("Paramètre libelle reçu " + libelle);
		LOGGER.info("Paramètre reference reçu " + reference);
		LOGGER.info("Paramètre prix reçu " + prix);
		LOGGER.info("Paramètre url reçu " + url);

		Helpers.PIZZERIA_SERVICE.sauverPizza(libelle, reference, prix, url);
		
		resp.setCharacterEncoding("UTF-8");

		try {
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_PIZZA_INSERE).toURI()))
					.stream().collect(Collectors.joining());

			String templateModifie = template.replace("{{msg_statut}}", "L'insertion a été correctement effectué");

			PrintWriter writer = resp.getWriter();
			writer.write(templateModifie);
		} catch (URISyntaxException e) {
			resp.setCharacterEncoding("UTF-8");

			try {
				String template = Files
						.readAllLines(
								Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_PIZZA_INSERE).toURI()))
						.stream().collect(Collectors.joining());
				String templateModifie = template.replace("{{msg_statut}}", "L'insertion à échouer !");

				PrintWriter writer = resp.getWriter();
				writer.write(templateModifie);

			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
