package dev.pizzeria.controller;

import javax.servlet.http.HttpServlet;

import dev.pizzeria.service.Helpers;
import dev.pizzeria.service.IPizzeriaService;

public abstract class AbstractController extends HttpServlet {
	
	protected IPizzeriaService pizzeriaService = Helpers.PIZZERIA_SERVICE;

}
