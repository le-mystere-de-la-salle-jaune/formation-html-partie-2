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

import dev.pizzeria.domain.Livreur;
import dev.pizzeria.domain.Pizza;
import dev.pizzeria.service.Helpers;

public class LivreurController extends AbstractController{

	private static final Logger LOGGER = LoggerFactory.getLogger(LivreurController.class);
	
	public static final String TEMPLATE_LIVREUR_INSERE = "templates/livreur_inserted.html";
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		
		LOGGER.info("Livreur reçu : "+firstname);
		
		Helpers.PIZZERIA_SERVICE.saveNewLivreur(new Livreur(firstname,lastname));
		
		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LIVREUR_INSERE).toURI())).stream().collect(Collectors.joining());

			String templateModifie = template.replace("{{firstname}}", firstname);
			templateModifie = templateModifie.replace("{{lastname}}", lastname.toUpperCase());

			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(templateModifie);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}
		
	}
	
}
