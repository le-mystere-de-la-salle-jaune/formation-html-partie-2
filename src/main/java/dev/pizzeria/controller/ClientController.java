package dev.pizzeria.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.pizzeria.domain.Client;
import dev.pizzeria.service.Helpers;

/**
 * Contrôleur responsable du traitement de la réquête : POST /clients.
 */
@SuppressWarnings("serial")
public class ClientController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	/**
	 * Page HTML de la réponse en cas d'insertion effectuée. Fichier présent
	 * dans le répertoire src/main/resources.
	 */
	public static final String TEMPLATE_CLIENT_INSERE = "templates/client_insere.html";
	public static final String TEMPLATE_LISTER_CLIENTS = "templates/lister_clients.html";
	public static final String TEMPLATE_UN_CLIENT = "templates/un_client.html";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// récupération du paramètre {truc}
		// <input name="truc">
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String ville = req.getParameter("ville");
		Integer age = Integer.valueOf(req.getParameter("age"));

		LOGGER.info("Paramètre nom reçu " + nom);
		LOGGER.info("Paramètre prénom reçu " + prenom);
		LOGGER.info("Paramètre ville reçu " + ville);
		LOGGER.info("Paramètre âge reçu " + age);

		// TODO insérer un nouveau client
		Helpers.PIZZERIA_SERVICE.sauverClient(nom, prenom, ville, age);

		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENT_INSERE).toURI()))
					.stream().collect(Collectors.joining());

			String templateModifie = template.replace("{{nom}}", nom);

			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(templateModifie);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setCharacterEncoding("UTF-8");

			String templateUnClient = Files
					.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_UN_CLIENT).toURI()))
					.stream().collect(Collectors.joining());

			String templateListerClients = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LISTER_CLIENTS).toURI()))
					.stream().collect(Collectors.joining());

			StringBuilder templateFinalSB = new StringBuilder();

			for (Client c : Helpers.PIZZERIA_SERVICE.listeCients()) {
				String templateFinal = templateUnClient.replace("{{id}}", c.getUuid().toString())
						.replace("{{nom}}", c.getNom()).replace("{{prenom}}", c.getPrenom())
						.replace("{{ville}}", c.getVille()).replace("{{age}}", c.getAge().toString());
				templateFinalSB.append(templateFinal);
			}

			String tF = templateFinalSB.toString();

			String templateListerClientsModifie = templateListerClients.replace("{{liste_clients}}", tF);

			PrintWriter writer = resp.getWriter();
			writer.write(templateListerClientsModifie);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}
	}
}
