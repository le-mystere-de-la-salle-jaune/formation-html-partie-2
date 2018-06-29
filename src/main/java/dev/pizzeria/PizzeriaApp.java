package dev.pizzeria;

import dev.pizzeria.controller.ClientController;
import dev.pizzeria.controller.LivreurController;
import dev.pizzeria.controller.PizzaController;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class PizzeriaApp {

    public static void main(String[] args) throws Exception {

        Server server = new Server();

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.setConnectors(new Connector[] { connector });
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");

        DefaultServlet defaultServlet = new DefaultServlet();
        ServletHolder holderPwd = new ServletHolder("default", defaultServlet);
        holderPwd.setInitParameter("resourceBase", "./src/main/webapp/static");
        context.addServlet(holderPwd, "/*");


        // Les contrôleurs de l'application

        // ClientController prend la main pour les requêtes /clients
        context.addServlet(ClientController.class, "/clients");
        context.addServlet(PizzaController.class, "/pizzas");
        context.addServlet(LivreurController.class, "/livreurs");


        server.setHandler(context);
        server.start();
        server.join();
    }
}
