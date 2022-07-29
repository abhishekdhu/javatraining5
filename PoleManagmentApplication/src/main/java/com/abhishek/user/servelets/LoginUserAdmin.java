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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class LoginUserAdmin
 */
@WebServlet("/loginServlet")
public class LoginUserAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public LoginUserAdmin() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
//		doGet(request, response);
		String number = request.getParameter("number");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		PrintWriter out = response.getWriter();
		try {
			Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users where phoneNumber="+number+" and password="+password);
            if(rs.next()) 
            {    
                String nm = rs.getString("role");
                String n = rs.getString("name");
                String ci = rs.getString("city");
                String no = rs.getString("phoneNumber");
                String em = rs.getString("email");
                String sector = rs.getString("sector");
                if(nm.equalsIgnoreCase(type)) {
                	if(nm.equalsIgnoreCase("Admin")) {
                		ResultSet rs1 = statement.executeQuery("select * from query where status <> \"Done\"");
                		List<String> lphn = new ArrayList<>();
                		List<String> pid = new ArrayList<>();
                		List<String> statu = new ArrayList<>();
                		List<String> doq = new ArrayList<>();
                		while(rs1.next()) {
                			lphn.add(rs1.getString("user_phone_number"));
                			pid.add(rs1.getString("pole_id"));
                			statu.add(rs1.getString("status"));
                			doq.add(rs1.getString("doq"));
                		}
                		if(lphn.size()==0) {
                			lphn.add("Empty");
                			pid.add("Empty");
                			statu.add("Empty");
                			doq.add("Empty");
                		}
//                		out.print("finally");
                		request.setAttribute("lphn", lphn);
                		request.setAttribute("pid", pid);
                		request.setAttribute("statu", statu);
                		request.setAttribute("doq", doq);
                		request.setAttribute("Name",n );
                        request.setAttribute("City",ci );
                        request.setAttribute("Sector",sector );
                        request.setAttribute("PhoneNumber",no );
                        request.setAttribute("Email",em );
                        request.getRequestDispatcher("Admin.jsp").forward(request, response);
                	}
                	else {
                		ResultSet rs1 = statement.executeQuery("select * from query where user_phone_number="+number);
                		List<String> listPole = new ArrayList<>();
                		List<String> listStatus = new ArrayList<>();
                		List<String> l = new ArrayList<>();
                		while(rs1.next()) {
                			listPole.add(rs1.getString("pole_id"));
                			listStatus.add(rs1.getString("status"));
//                			out.print();
                			l.add(rs1.getString("doq").toString());
                		}
                		if(listPole.size()==0) {
                			listPole.add("No Data");
                			listStatus.add("No Data");
                			l.add("No Data");
                		}
                		request.setAttribute("listPole", listPole);
                		request.setAttribute("listStatus",listStatus);
                		request.setAttribute("l", l);
                        request.setAttribute("Name",n );
                        request.setAttribute("City",ci );
                        request.setAttribute("Sector",sector );
                        request.setAttribute("PhoneNumber",no );
                        request.setAttribute("Email",em );
                        request.getRequestDispatcher("Complain.jsp").forward(request, response);
                	}
                }
                else {
                	out.print("<H1>Check the credentials or may be something wrong with the user privledge!!!</H1>");
                }
            }
            
		} catch (SQLException e) {
			out.print("<H1>Error Getting the user</H1>");
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
