package dev.pizzeria.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.pizzeria.domain.Client;
import dev.pizzeria.domain.Livreur;
import dev.pizzeria.service.Helpers;

public class LivreurController extends HttpServlet {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LivreurController.class);

    /**
     * Page HTML de la réponse en cas d'insertion effectuée.
     * Fichier présent dans le répertoire src/main/resources.
     */
    public static final String TEMPLATE_LIVREUR_INSERE = "templates/livreur_insere.html";
    public static final String TEMPLATE_LISTE_LIVREUR = "templates/liste_livreur.html";   
    public static final String TEMPLATE_UN_LIVREUR = "templates/ligne_un_livreur.html";
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // récupération du paramètre nom
        // <input name="nom">
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String telephone = req.getParameter("telephone");
        String dateEmbauche = req.getParameter("dateEmbauche");
        
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateEmbaucheLocalDate = LocalDate.parse(dateEmbauche, formater);
        
        LOGGER.info("\nParamètres reçu : " 
        			+ "\n" + nom 
        			+ "\n" + prenom 
        			+ "\n" + telephone 
        			+ "\n" + dateEmbauche);


        // TODO insérer un nouveau client
        Helpers.PIZZERIA_SERVICE.sauverLivreur(nom,prenom,telephone,dateEmbaucheLocalDate);


        try {
            // réponse au format UTF-8 pour le support des accents
            resp.setCharacterEncoding("UTF-8");

            // récupération du contenu du fichier template
            String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LIVREUR_INSERE).toURI())).stream().collect(Collectors.joining());
            
            String templateModifie = template.replace("{{nom}}", nom).replace("{{prenom}}", prenom).replace("{{telephone}}", telephone).replace("{{dateEmbauche}}", dateEmbauche);
         

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
            String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_UN_LIVREUR).toURI())).stream().collect(Collectors.joining());
            
            Map<UUID,Livreur> tempMap = Helpers.PIZZERIA_SERVICE.listerLivreurs();
            Set<UUID> setUuid = tempMap.keySet();
            
            StringBuilder sb = new StringBuilder();
            
            for(UUID u : setUuid)
            {
            	Livreur l = tempMap.get(u);
            	UUID id = l.getUuid();
            	String nom = l.getNom();
            	String prenom = l.getPrenom();
            	String telephone = l.getTelephone();
            	LocalDate dateEmbauche = l.getDateEmbauche();
            	
            	String idString = id.toString();
            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            	String dateEmbaucheString = (dateEmbauche).format(formatter);
            	
            	String templateModifie = template.replace("{{id}}",idString).replace("{{nom}}", nom).replace("{{prenom}}", prenom).replace("{{telephone}}", telephone).replace("{{dateEmbauche}}", dateEmbaucheString);
                
                // écriture dans le corps de la réponse
            	 sb.append(templateModifie);
              
            }
            
            String templateListeClient = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LISTE_LIVREUR).toURI())).stream().collect(Collectors.joining());
            
            
            PrintWriter writer = resp.getWriter();
            writer.write(templateListeClient.replace("{{listeLivreurs}}",sb.toString()));
           

        } catch (URISyntaxException e) {
           LOGGER.error("Fichier HTML non trouvé", e);
        }
	}

}
