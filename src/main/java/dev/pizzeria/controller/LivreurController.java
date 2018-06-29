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

import dev.pizzeria.domain.Livreur;
import dev.pizzeria.service.Helpers;

public class LivreurController extends HttpServlet{

	private static final Logger LOGGER = LoggerFactory.getLogger(LivreurController.class);

	/**
	 * Page HTML de la réponse en cas d'insertion effectuée. Fichier présent
	 * dans le répertoire src/main/resources.
	 */
	public static final String TEMPLATE_LIVREUR_INSERE = "templates/livreur_insere.html";
	public static final String TEMPLATE_LIVREUR_LIST = "templates/livreur_liste.html";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<UUID, Livreur> livreurs = Helpers.PIZZERIA_SERVICE.getLivreurs();

		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LIVREUR_LIST).toURI()))
					.stream().collect(Collectors.joining());

			String templateModifie;
			if (livreurs.isEmpty()) {
				templateModifie = template.replace("{{liste_livreur}}", "");
			} 
			else {
				String strReplace = "";
				for (Map.Entry<UUID, Livreur> e : livreurs.entrySet()) {
					strReplace = strReplace + "<tr>" + "<td scope='row'>" + e.getValue().getUuid() + "</td>"
							+ "<td scope='row'>" + e.getValue().getNom() + "</td>" + "<td scope='row'>"
							+ e.getValue().getPrenom() + "</td>" + "<td scope='row'>" + e.getValue().getVille()
							+ "</td>" + "<td scope='row'>" + e.getValue().getAge() + "</td> <td scope=\"row\">"+ e.getValue().getLocomotion() +"</td></tr>";
				}

				templateModifie = template.replace("{{liste_livreur}}", strReplace);

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
				String locomotion = req.getParameter("locomotion");

				LOGGER.info("Paramètre nom reçu " + nom);
				LOGGER.info("Paramètre prenom reçu " + prenom);
				LOGGER.info("Paramètre ville reçu " + ville);
				LOGGER.info("Paramètre age reçu " + age);
				LOGGER.info("Paramètre locomotion reçu" + locomotion);

				// TODO insérer un nouveau livreur
				Helpers.PIZZERIA_SERVICE.sauverLivreurs(nom, prenom, ville, age, locomotion);

				try {
					// réponse au format UTF-8 pour le support des accents
					resp.setCharacterEncoding("UTF-8");

					// récupération du contenu du fichier template
					String template = Files
							.readAllLines(
									Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LIVREUR_INSERE).toURI()))
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
										Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LIVREUR_INSERE).toURI()))
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
