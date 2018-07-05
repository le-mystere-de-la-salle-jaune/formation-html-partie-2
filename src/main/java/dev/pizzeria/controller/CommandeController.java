package dev.pizzeria.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
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
import dev.pizzeria.domain.Commande;
import dev.pizzeria.service.Helpers;

public class CommandeController extends HttpServlet
{
	private static final Logger LOGGER = LoggerFactory.getLogger(CommandeController.class);

    /**
     * Page HTML de la réponse en cas d'insertion effectuée.
     * Fichier présent dans le répertoire src/main/resources.
     */
    public static final String TEMPLATE_COMMANDE_INSERE = "templates/commande_insere.html";
    public static final String TEMPLATE_COMMANDE_MAJ = "templates/commande_maj.html";
    public static final String TEMPLATE_COMMANDE_SUPPRIME = "templates/commande_supprime.html";
    public static final String TEMPLATE_LISTE_COMMANDE = "templates/liste_commande.html";   
    public static final String TEMPLATE_UNE_COMMANDE = "templates/ligne_une_commande.html";
    public static final String TEMPLATE_ERROR = "templates/erreur_insertion.html";
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // récupération du paramètre nom
        // <input name="nom">
        String nomClient = req.getParameter("nomClient");
        String prenomClient = req.getParameter("prenomClient");
        String nomLivreur = req.getParameter("nomLivreur");
        String prenomLivreur = req.getParameter("prenomLivreur");
      
        
        LOGGER.info("\nParamètres reçu : " 
        			+ "\n" + nomClient 
        			+ "\n" + prenomClient 
        			+ "\n" + nomLivreur 
        			+ "\n" + prenomLivreur);


        // TODO insérer un nouveau client
        Helpers.PIZZERIA_SERVICE.sauverCommande(nomClient,prenomClient,nomLivreur,prenomLivreur);


        try {
            // réponse au format UTF-8 pour le support des accents
            resp.setCharacterEncoding("UTF-8");

            // récupération du contenu du fichier template
            String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_COMMANDE_INSERE).toURI())).stream().collect(Collectors.joining());
            
            String templateModifie = template.replace("{{nomClient}}", nomClient).replace("{{prenomClient}}", prenomClient).replace("{{nomLivreur}}",nomLivreur).replace("{{prenomLivreur}}", prenomLivreur);
         

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
            String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_UNE_COMMANDE).toURI())).stream().collect(Collectors.joining());
            
            Map<UUID,Commande> tempMap = Helpers.PIZZERIA_SERVICE.listerCommandes();
            Set<UUID> setUuid = tempMap.keySet();
            
            StringBuilder sb = new StringBuilder();
            
            for(UUID u : setUuid)
            {
            	Commande c = tempMap.get(u);
            	UUID id = c.getUuid();
            	Integer numeroCommande = c.getNumeroCommande();
            	LocalDateTime dateCommande = c.getDateCommande();
            	String nomClient = c.getClient().getNom();
            	String prenomClient = c.getClient().getPrenom();
            	String nomLivreur = c.getLivreur().getNom();
            	String prenomLivreur = c.getLivreur().getPrenom();
            	
            	String idString = id.toString();
            	String numeroCommandeString = Integer.toString(numeroCommande);
            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            	String dateCommandeString = (dateCommande).format(formatter);
            	
            	 String templateModifie = template.replace("{{id}}",idString).replace("{{numeroCommande}}", numeroCommandeString)
            			 .replace("{{dateCommande}}", dateCommandeString).replace("{{nomClient}}", nomClient)
            			 .replace("{{prenomClient}}", prenomClient).replace("{{nomLivreur}}", nomLivreur)
            			 .replace("{{prenomLivreur}}", prenomLivreur);
                
                // écriture dans le corps de la réponse
            	 sb.append(templateModifie);
              
            }
            
            String templateListeCommande = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LISTE_COMMANDE).toURI())).stream().collect(Collectors.joining());
            
            
            PrintWriter writer = resp.getWriter();
            writer.write(templateListeCommande.replace("{{listeCommandes}}",sb.toString()));
           

        } catch (URISyntaxException e) {
           LOGGER.error("Fichier HTML non trouvé", e);
           
        }
	}


	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            // réponse au format UTF-8 pour le support des accents
            resp.setCharacterEncoding("UTF-8");

            // récupération du contenu du fichier template
            String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_COMMANDE_MAJ).toURI())).stream().collect(Collectors.joining());
            
            String nomClient = req.getParameter("nomClient");
            String prenomClient = req.getParameter("prenomClient");
            String nomLivreur = req.getParameter("nomLivreur");
            String prenomLivreur = req.getParameter("prenomLivreur");
            
            StringBuilder sb = new StringBuilder();
            
         //   Commande c = ;
                  
            Helpers.PIZZERIA_SERVICE.modifierCommande(c,nomClient,prenomClient,nomLivreur,prenomLivreur);
            	
            String templateModifie = template.replace("{{id}}",c.getUuid()).replace("{{numeroCommande}}", c.getNumeroCommande())
            			 .replace("{{dateCommande}}", c.getDateCommande()).replace("{{nomClient}}", nomClient)
            			 .replace("{{prenomClient}}", prenomClient).replace("{{nomLivreur}}", nomLivreur)
            			 .replace("{{prenomLivreur}}", prenomLivreur);
                
                // écriture dans le corps de la réponse
            sb.append(templateModifie);
              
            
            
            String templateListeCommande = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LISTE_COMMANDE).toURI())).stream().collect(Collectors.joining());
            
            
            PrintWriter writer = resp.getWriter();
            writer.write(templateListeCommande.replace("{{listeCommandes}}",sb.toString()));
           

        } catch (URISyntaxException e) {
           LOGGER.error("Fichier HTML non trouvé", e);
           
        }
	}


	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            // réponse au format UTF-8 pour le support des accents
            resp.setCharacterEncoding("UTF-8");

            // récupération du contenu du fichier template
            String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_COMMANDE_SUPPRIME).toURI())).stream().collect(Collectors.joining());
            
           // Commande commande = req
            Helpers.PIZZERIA_SERVICE.supprimerCommande(commande);
          
        } catch (URISyntaxException e) {
           LOGGER.error("Fichier HTML non trouvé", e);
           
        }
	}
}
