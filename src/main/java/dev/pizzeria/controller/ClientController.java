package dev.pizzeria.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


       


        try {
        	
        	 // récupération du paramètre nom
            // <input name="nom">
            String nom = req.getParameter("nom");
            String prenom = req.getParameter("prenom");
            String ville = req.getParameter("ville");
            int age = Integer.parseInt(req.getParameter("age"));
            
            LOGGER.info("Paramètre nom reçu " + nom + prenom + ville + age);

            String sucess_message="L'insertion a été correctement effectuée.";
            String error_message="L'insertion à échouée";

            // TODO insérer un nouveau client
            Helpers.PIZZERIA_SERVICE.sauverClient(nom, prenom, ville, age);
            // réponse au format UTF-8 pour le support des accents
            resp.setCharacterEncoding("UTF-8");

            // récupération du contenu du fichier template
            String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENT_INSERE).toURI())).stream().collect(Collectors.joining());
            
            String templateModifie = template.replace("{{message}}", sucess_message);

            // écriture dans le corps de la réponse
            PrintWriter writer = resp.getWriter();
            writer.write(templateModifie);

        } catch (URISyntaxException e) {
           LOGGER.error("Fichier HTML non trouvé", e);
        }
        catch (Exception e) {
        	resp.getWriter().write("Ooppssss " + e.getMessage());
         }
    }
}
