package com.abhishek.user.servelets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 * Servlet implementation class CompalinEntry
 */
@WebServlet("/complainServlet")
public class CompalinEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public CompalinEntry() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
	public void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/abhishek","root","Password@123");
			System.out.print(connection);
		}
		catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String phoneNumber = request.getParameter("number");
		String poleNumber = request.getParameter("poleNumber");
		String status = "Not Assigned";
		LocalDate localDate = LocalDate.now();
		PrintWriter out = response.getWriter();
		try {
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("insert into query values('"+phoneNumber+"','"+poleNumber+"','"+status+"','"+localDate+"')");
			if(result>0){
				out.print("<H1>Complain Registred</H1>");
//				out.println("<a href=”http://localhost:8080/HelloWorld/test”> Hello World Servlet </a>");
			}
			else {
				out.print("<H1>Error Creating complain</H1>");
			}
		} catch (SQLException e) {
			out.print("<H1>Error Creating complain</H1>");
			e.printStackTrace();
		}
		
//		doGet(request, response);
	}
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
