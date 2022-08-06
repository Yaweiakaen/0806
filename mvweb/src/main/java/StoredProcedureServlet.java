

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.*;
/**
 * Servlet implementation class StoredProcedureServlet
 */
@WebServlet("/StoredProcedureServlet")
public class StoredProcedureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoredProcedureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		    String url = "jdbc:mysql://localhost:3306/classicmodels?serverTimezone=Asia/Taipei";
	        String username = "root";
	        String password = "1234";
	        PrintWriter out = response.getWriter();
	        String city=request.getParameter("city");
	        if(city==null || city.equals(""))
	        	   city="NYC";
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection(url, username, password);
	            CallableStatement cStmt = con.prepareCall("CALL GetEmployeeByCity(?)");
	            cStmt.setString(1, city);
	            ResultSet rs=cStmt.executeQuery();
	            print(out, rs);
	            con.close();
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	 public void print(PrintWriter out ,ResultSet rs)
	    {       
	        try {          
	            out.println("<table border='1'>");
	            while (rs.next()) {             
	                out.println("<tr><td>");
	                String no = rs.getString("employeeNumber");
	                out.println(""+no);
	                out.println("</td><td>");
	                String firstname = rs.getString("firstname");
	                out.println(""+firstname);
	                out.println("</td><td>");
	                String lastname = rs.getString("lastname");
	                out.println(""+lastname);
	                out.println("</td><td>");
	                String email = rs.getString("email");
	                 out.println(""+email);
	                out.println("</td>");                
	            }
	            out.println("</table>");
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }     
	    
	    }

}
