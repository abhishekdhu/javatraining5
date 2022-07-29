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
 * Servlet implementation class ComplainFurther
 */
@WebServlet("complainFurtherServlet")
public class ComplainFurther extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public ComplainFurther() {
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
		String whom = request.getParameter("whom");
		String number = request.getParameter("number");
		String poleNumber = request.getParameter("poleNumber");
		PrintWriter out = response.getWriter();
		try {
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("update query set status="+whom+" where user_phone_number="+number+" and pole_id="+poleNumber);
			if(result>0){
				out.print("<H1>Compain Updated Successfully</H1>");
			}
			else {
				out.print("<H1>Error Updating the Complain</H1>");
			}
		} catch (SQLException e) {
			out.print("<H1>Error Updating the complain</H1>");
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
