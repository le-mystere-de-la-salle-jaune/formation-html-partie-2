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
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contrôleur responsable du traitement de la réquête : POST /clients.
 */
public class ClientController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    /**
     * Page HTML de la réponse en cas d'insertion effectuée.
     * Fichier présent dans le répertoire src/main/resources.
     */
    public static final String TEMPLATE_CLIENT_INSERE = "templates/client_insere.html";
    public static final String TEMPLATE_LISTE_CLIENT = "templates/lister_client.html";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // récupération du paramètre nom
        // <input name="nom">
        String nom = req.getParameter("nom");
        
        LOGGER.info("Paramètre nom reçu " + nom);
        
     // récupération du paramètre prenom
        String prenom = req.getParameter("prenom");
        
        LOGGER.info("Paramètre prenom reçu " + prenom);
        
     // récupération du paramètre ville
        String ville = req.getParameter("ville");
        
        LOGGER.info("Paramètre ville reçu " + ville);
        
     // récupération du paramètre age
        Integer age = Integer.valueOf(req.getParameter("age"));
        
        LOGGER.info("Paramètre age reçu " + age);

        
        // TODO insérer un nouveau client
        Helpers.PIZZERIA_SERVICE.sauverClient(nom, prenom, ville, age);

       

        try {
            // réponse au format UTF-8 pour le support des accents
            resp.setCharacterEncoding("UTF-8");

            // récupération du contenu du fichier template
            String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENT_INSERE).toURI())).stream().collect(Collectors.joining());
            
            String templateModifie = template.replace("{{nom}}", nom);

            // écriture dans le corps de la réponse
            PrintWriter writer = resp.getWriter();
            writer.write(templateModifie);

        } catch (URISyntaxException e) {
           LOGGER.error("Fichier HTML non trouvé", e);
        }
    }

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  try {
	            // réponse au format UTF-8 pour le support des accents
	            resp.setCharacterEncoding("UTF-8");

	            // récupération du contenu du fichier template
	            String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LISTE_CLIENT).toURI())).stream().collect(Collectors.joining());
	            
	            List<Client> listerClients = Helpers.PIZZERIA_SERVICE.listerClients();
	            
	            String lesLignes = listerClients.stream().map(client ->  {
	            	
	            	String uneLigneClient = "<tr><td>" + client.getUuid() + "</td><td>" + client.getNom() + "</td><td>" + client.getPrenom() + "</td><td>" + client.getVille() + "</td><td>" + "</td><td>" + client.getAge() + "</td>";
	            	return uneLigneClient;
	            }).collect(Collectors.joining());
	            
	            String templateModifie = template.replace("{{listeclients}}", lesLignes);

	            // écriture dans le corps de la réponse
	            PrintWriter writer = resp.getWriter();
	            writer.write(templateModifie);

	        } catch (URISyntaxException e) {
	           LOGGER.error("Fichier HTML non trouvé", e);
	        }
	}
}
