package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class RequestHelper {
	
	private static EmployeeService eserv = new EmployeeService(new EmployeeDao());
	private static ObjectMapper om = new ObjectMapper();
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// this method will be called by the FrontController when a user requests the login resources
		// we will extract the parameters from the forwarded request
		Employee e = eserv.confirmLogin(username, password);
		if (e != null) {
			HttpSession session = request.getSession();
			session.setAttribute("the-user", e);
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			out.println("<h1>Welcome " + e.getFirstName() + "!</h1>");
			out.println("<h3>You have succesfully logged in</h3>");
			out.println(om.writeValueAsString(e));
		}
		else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("no user found, sorry");
			response.setStatus(204);
		}
		// then we'll call the Service layer....etc...	
	}
	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		List<Employee> allEmployees = eserv.findAll();
		String jsonString = om.writeValueAsString(allEmployees);
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}
}