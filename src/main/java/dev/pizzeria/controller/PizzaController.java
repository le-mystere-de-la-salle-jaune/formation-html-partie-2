package dev.pizzeria.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.pizzeria.domain.Pizza;
import dev.pizzeria.service.Helpers;

public class PizzaController extends AbstractController{

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);

	public static final String TEMPLATE_PIZZA_INSERE = "templates/pizza_inserted.html";

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		String label = req.getParameter("label");
		String refference = req.getParameter("refference");
		double price = Double.parseDouble(req.getParameter("price"));
		String imageUrl = req.getParameter("imageURL");
		
		LOGGER.info("Pizza reçu : "+label);
		
		Helpers.PIZZERIA_SERVICE.saveNewPizza(new Pizza(label,refference,price,imageUrl));
		
		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_PIZZA_INSERE).toURI())).stream().collect(Collectors.joining());

			String templateModifie = template.replace("{{label}}", label);
			templateModifie = templateModifie.replace("{{refference}}", refference.toUpperCase());
			templateModifie = templateModifie.replace("{{price}}", price+"");
			templateModifie = templateModifie.replace("{{imageURL}}", "<img src=\""+imageUrl+"\" alt=\""+label+"\">");

			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(templateModifie);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}
	}


}
