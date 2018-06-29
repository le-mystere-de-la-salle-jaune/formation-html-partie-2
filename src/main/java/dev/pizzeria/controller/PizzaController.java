package dev.pizzeria.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

import dev.pizzeria.domain.Pizza;
import dev.pizzeria.service.Helpers;

public class PizzaController extends HttpServlet
{
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);
	
	public static final String TEMPLATE_PIZZA_INSERE = "templates/pizza_insere.html";
    public static final String TEMPLATE_LISTE_PIZZA = "templates/liste_pizzas.html";   
    public static final String TEMPLATE_UNE_PIZZA = "templates/ligne_une_pizza.html";
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        // récupération du paramètre nom
        // <input name="nom">
        String libelle = req.getParameter("libelle");
        String reference = req.getParameter("reference");
        String prix = req.getParameter("prix");
        String url = req.getParameter("url");
        
        double prixDouble = Double.parseDouble(prix);
        
        
        LOGGER.info("\nParamètres reçu : " 
        			+ "\n" + libelle 
        			+ "\n" + reference 
        			+ "\n" + prix 
        			+ "\n" + url);


        // TODO insérer un nouveau client
        Helpers.PIZZERIA_SERVICE.sauverPizza(libelle,reference,prixDouble,url);


        try {
            // réponse au format UTF-8 pour le support des accents
            resp.setCharacterEncoding("UTF-8");

            // récupération du contenu du fichier template
            String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_PIZZA_INSERE).toURI())).stream().collect(Collectors.joining());
            
            String templateModifie = template.replace("{{libelle}}", libelle).replace("{{reference}}", reference).replace("{{prix}}", prix).replace("{{url}}", url);
         

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
            String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_UNE_PIZZA).toURI())).stream().collect(Collectors.joining());
            
            Map<UUID,Pizza> tempMap = Helpers.PIZZERIA_SERVICE.listerPizzas();
            Set<UUID> setUuid = tempMap.keySet();
            
            StringBuilder sb = new StringBuilder();
            
            for(UUID u : setUuid)
            {
            	Pizza p = tempMap.get(u);
            	
            	UUID id = p.getUuid();
            	String libelle = p.getLibelle();
            	String reference = p.getReference();
            	double prix = p.getPrix();
            	String url = p.getUrlImage();
            	
            	String idString = id.toString();
            	String prixString = Double.toString(prix);
            	
            	 String templateModifie = template.replace("{{id}}",idString).replace("{{libelle}}", libelle).replace("{{reference}}", reference).replace("{{prix}}", prixString).replace("{{url}}", url);
                
                // écriture dans le corps de la réponse
            	 sb.append(templateModifie);
              
            }
            
            String templateListeClient = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LISTE_PIZZA).toURI())).stream().collect(Collectors.joining());
            
            
            PrintWriter writer = resp.getWriter();
            writer.write(templateListeClient.replace("{{listePizzas}}",sb.toString()));
           

        } catch (URISyntaxException e) {
           LOGGER.error("Fichier HTML non trouvé", e);
        }
	}
    

}
