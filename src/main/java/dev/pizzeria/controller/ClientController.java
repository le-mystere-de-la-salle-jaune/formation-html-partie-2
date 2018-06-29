package dev.pizzeria.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.pizzeria.domain.Client;
import dev.pizzeria.service.Helpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Contrôleur responsable du traitement de la réquête : POST /clients.
 */
public class ClientController extends AbstractController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	/**
	 * Page HTML de la réponse en cas d'insertion effectuée.
	 * Fichier présent dans le répertoire src/main/resources.
	 */
	public static final String TEMPLATE_CLIENT_INSERE = "templates/client_insere.html";
	public static final String TEMPLATE_CLIENT_LISTE = "templates/liste_client.html";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		// récupération du paramètre nom
		// <input name="nom">
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String city = req.getParameter("city");
		int age = Integer.parseInt(req.getParameter("age"));

		LOGGER.info("Client reçu " + firstname + " " + lastname);


		// TODO insérer un nouveau client
		Helpers.PIZZERIA_SERVICE.sauverClient(firstname, lastname, city, age);


		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENT_INSERE).toURI())).stream().collect(Collectors.joining());

			String templateModifie = template.replace("{{firstname}}", firstname);
			templateModifie = templateModifie.replace("{{lastname}}", lastname.toUpperCase());
			templateModifie = templateModifie.replace("{{city}}", city);
			templateModifie = templateModifie.replace("{{age}}", age+"");

			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(templateModifie);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Client> clients =  (ArrayList<Client>) Helpers.PIZZERIA_SERVICE.findAllClients();

		String tempTable = "";

		for(Client c : clients){
			tempTable += "<tr>";

			tempTable += "<td>"+c.getLastName()+"</td>";
			tempTable += "<td>"+c.getFirstName()+"</td>";
			tempTable += "<td>"+c.getCity()+"</td>";
			tempTable += "<td>"+c.getAge()+"</td>";

			tempTable += "</tr>";
		}

		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENT_LISTE).toURI())).stream().collect(Collectors.joining());

			String templateModifie = template.replace("{{table}}", tempTable);
			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(templateModifie);


		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}
	}
}
