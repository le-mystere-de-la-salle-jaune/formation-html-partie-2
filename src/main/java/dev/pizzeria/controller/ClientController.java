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
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Contrôleur responsable du traitement de la réquête : POST /clients.
 */
public class ClientController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	/**
	 * Page HTML de la réponse en cas d'insertion effectuée. Fichier présent
	 * dans le répertoire src/main/resources.
	 */
	public static final String TEMPLATE_CLIENT_INSERE = "templates/client_insere.html";
	public static final String TEMPLATE_CLIENT_LIST = "templates/client_liste.html";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<UUID, Client> clients = Helpers.PIZZERIA_SERVICE.getClients();

		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENT_LIST).toURI()))
					.stream().collect(Collectors.joining());

			String templateModifie;
			if (clients.isEmpty()) {
				templateModifie = template.replace("{{liste_client}}", "");
			} 
			else {
				String strReplace = "";
				for (Map.Entry<UUID, Client> e : clients.entrySet()) {
					strReplace = strReplace + "<tr>" + "<td scope='row'>" + e.getValue().getUuid() + "</td>"
							+ "<td scope='row'>" + e.getValue().getNom() + "</td>" + "<td scope='row'>"
							+ e.getValue().getPrenom() + "</td>" + "<td scope='row'>" + e.getValue().getVille()
							+ "</td>" + "<td scope='row'>" + e.getValue().getAge() + "</td>" + "</tr>";
				}

				templateModifie = template.replace("{{liste_client}}", strReplace);

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

		// récupération du paramètre nom, prenom, ville, age
		// <input name="nom">
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String ville = req.getParameter("ville");
		int age = Integer.parseInt(req.getParameter("age"));

		LOGGER.info("Paramètre nom reçu " + nom);
		LOGGER.info("Paramètre prenom reçu " + prenom);
		LOGGER.info("Paramètre ville reçu " + ville);
		LOGGER.info("Paramètre age reçu " + age);

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

			String templateModifie = template.replace("{{msg_statut}}",
					"L'insertion a été correctement effectué");

			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(templateModifie);

		} catch (URISyntaxException e) {

			try {
				String template = Files
						.readAllLines(
								Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENT_INSERE).toURI()))
						.stream().collect(Collectors.joining());
				String templateModifie = template.replace("{{msg_statut}}",
						"L'insertion à échouer !");

				PrintWriter writer = resp.getWriter();
				writer.write(templateModifie);
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			LOGGER.error("Fichier HTML non trouvé", e);
		}
	}
}
