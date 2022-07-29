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

/**
 * Servlet implementation class CreateUserServelet
 */
@WebServlet("/addServlet")
public class CreateUserServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public CreateUserServelet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String name = request.getParameter("name");
		String email = request.getParameter("emailId");
		String phoneNumber = request.getParameter("phoneNumber");
		String city = request.getParameter("city");
		String sector = request.getParameter("sector");
		String password = request.getParameter("password");
		String role = request.getParameter("type");
		PrintWriter out = response.getWriter();
		try {
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("insert into users values('"+phoneNumber+"','"+name+"','"+email+"','"+password+"','"+city+"','"+sector+"','"+role+"')");
			if(result>0){
				out.print("<H1>User Created</H1>");
//				out.println("<a href=”http://localhost:8080/HelloWorld/test”> Hello World Servlet </a>");
			}
			else {
				out.print("<H1>Error Creating the user</H1>");
			}
		} catch (SQLException e) {
			out.print("<H1>Error Creating the user</H1>");
			e.printStackTrace();
		}
		
	}
	
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
