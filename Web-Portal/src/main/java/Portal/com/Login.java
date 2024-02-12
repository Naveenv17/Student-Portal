package Portal.com;

import jakarta.servlet.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;


public class Login  extends HttpServlet{
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	String sql="select * from portal.student_portal WHERE Email=? AND Passwords=?";
	
	public void init() throws ServletException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/portal?user=root&password=Rocky@1.");
		}catch (ClassNotFoundException e) {
            throw new ServletException("Database driver not found", e);
        } catch (SQLException e) {
            throw new ServletException("Error connecting to the database", e);
        }
	}
	 
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 resp.setHeader("X-Content-Type-Options", "nosniff");
    	  resp.sendRedirect("Home.html");  
    }

	           
      
    

	 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 resp.setHeader("X-Content-Type-Options", "nosniff");
	        String email = req.getParameter("email");
	        String pass = req.getParameter("pass");
	       	        
	        try {
	        	ps=con.prepareStatement(sql);
	            ps.setString(1, email);
	            ps.setString(2, pass);
	           rs=ps.executeQuery();
	            if (rs.next()) {
	        	    resp.sendRedirect("Home.html");  
	                }
	            else {
	            	resp.setHeader("X-Content-Type-Options", "nosniff");
	    	    	resp.getWriter().println("<script>alert('Invalid Creditial');</script>");
	                RequestDispatcher rd = req.getRequestDispatcher("login.html");
	                rd.include(req, resp);                
	        }
	        
	        } catch (SQLException e) {
	        	resp.getWriter().println("An error occurred while processing your request. Please try again later.");
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (ps != null) ps.close();
	                if (con != null) con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	private DriverManager getWriter() {
		// TODO Auto-generated method stub
		return null;
	}
	}



