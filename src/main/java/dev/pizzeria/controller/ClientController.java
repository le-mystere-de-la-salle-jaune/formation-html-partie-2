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
     * Page HTML de la réponse en cas d'insertion effectuée.
     * Fichier présent dans le répertoire src/main/resources.
     */
    public static final String TEMPLATE_CLIENT_INSERE = "templates/client_insere.html";
    
    
    /**
     * Page HTML de la requète pour lister la liste de client actuelle
     */
    public static final String TEMPLATE_CLIENT_LISTE = "templates/ListerClient.html";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // récupération du paramètre nom
        // <input name="nom">
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String ville = req.getParameter("ville");
        int age = Integer.parseInt(req.getParameter("age"));
        
        LOGGER.info("Paramètre nom reçu " + nom);


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
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	
    	LOGGER.info("Fonction doGet invoqué");
    	
    	try{
            
            //On tente de récupérer la liste des clients
            Map<UUID,Client> map = Helpers.PIZZERIA_SERVICE.getClients();
            
         // réponse au format UTF-8 pour le support des accents
            resp.setCharacterEncoding("UTF-8");

            // récupération du contenu du fichier template
            String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENT_LISTE).toURI())).stream().collect(Collectors.joining());
            
            String templateModifie = template.replace("{{maListe}}", genererTableHtmlListeClients(map));

            // écriture dans le corps de la réponse
            PrintWriter writer = resp.getWriter();
            writer.write(templateModifie);
            
    		
    	}catch (URISyntaxException e) {
            LOGGER.error("Fichier HTML non trouvé", e);
         }
    	
    	
    }
    
    /** 
     * Fonction :  Générer une table Html qui liste les clients
     * @return
     */
    public String genererTableHtmlListeClients(Map<UUID,Client> map){
    	String res ="<tr><th>Id.</th><th>NOM</th><th>Prénom</th><th>Ville</th><th>Age</th></td>";
    	for(UUID id : map.keySet()){
    		res+="<tr><td>"+id+"</td>";
    		res+="<td>"+map.get(id).getNom()+"</td>";
    		res+="<td>"+map.get(id).getPrenom()+"</td>";
    		res+="<td>"+map.get(id).getVille()+"</td>";
    		res+="<td>"+map.get(id).getAge()+"</td></tr>";
    	}
    	
    	
    	return res;
    }
    
    
}
