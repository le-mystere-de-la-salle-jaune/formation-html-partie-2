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
import java.util.Set;
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
    public static final String TEMPLATE_LISTE_CLIENT = "templates/liste_client.html";   
    public static final String TEMPLATE_UN_CLIENT = "templates/ligne_un_client.html";
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // récupération du paramètre nom
        // <input name="nom">
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String ville = req.getParameter("ville");
        String ageString = req.getParameter("age");
        Integer ageInteger =  Integer.valueOf(ageString);
        
        
        LOGGER.info("\nParamètres reçu : " 
        			+ "\n" + nom 
        			+ "\n" + prenom 
        			+ "\n" + ville 
        			+ "\n" + ageInteger);


        // TODO insérer un nouveau client
        Helpers.PIZZERIA_SERVICE.sauverClient(nom,prenom,ville,ageInteger);


        try {
            // réponse au format UTF-8 pour le support des accents
            resp.setCharacterEncoding("UTF-8");

            // récupération du contenu du fichier template
            String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENT_INSERE).toURI())).stream().collect(Collectors.joining());
            
            String templateModifie = template.replace("{{nom}}", nom).replace("{{prenom}}", prenom).replace("{{ville}}", ville).replace("{{age}}", ageString);
         

            // écriture dans le corps de la réponse
            PrintWriter writer = resp.getWriter();
            writer.write(templateModifie);

        } catch (URISyntaxException e) {
           LOGGER.error("Fichier HTML non trouvé", e);
        }
    }
    

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		
		try {
            // réponse au format UTF-8 pour le support des accents
            resp.setCharacterEncoding("UTF-8");

            // récupération du contenu du fichier template
            String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_UN_CLIENT).toURI())).stream().collect(Collectors.joining());
            
            Map<UUID,Client> tempMap = Helpers.PIZZERIA_SERVICE.listerClients();
            Set<UUID> setUuid = tempMap.keySet();
            
            StringBuilder sb = new StringBuilder();
            
            for(UUID u : setUuid)
            {
            	Client c = tempMap.get(u);
            	UUID id = c.getUuid();
            	String nom = c.getNom();
            	String prenom = c.getPrenom();
            	String ville = c.getVille();
            	Integer ageInteger = c.getAge();
            	
            	String idString = id.toString();
            	String ageString = Integer.toString(ageInteger);
            	
            	 String templateModifie = template.replace("{{id}}",idString).replace("{{nom}}", nom).replace("{{prenom}}", prenom).replace("{{ville}}", ville).replace("{{age}}", ageString);
                
                // écriture dans le corps de la réponse
            	 sb.append(templateModifie);
              
            }
            
            String templateListeClient = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LISTE_CLIENT).toURI())).stream().collect(Collectors.joining());
            
            
            PrintWriter writer = resp.getWriter();
            writer.write(templateListeClient.replace("{{listeClients}}",sb.toString()));
           

        } catch (URISyntaxException e) {
           LOGGER.error("Fichier HTML non trouvé", e);
        }
	}
    

}
