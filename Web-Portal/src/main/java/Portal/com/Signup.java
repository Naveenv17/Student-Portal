package Portal.com;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;
import java.io.IOException;

public class Signup extends HttpServlet {
	Connection con=null;
	PreparedStatement pr=null;
   ResultSet rs=null;
    String sql="Insert into portal.student_portal(first_name,last_name,dob,Email,degree,Passwords) values (?,?,?,?,?,?)";
    
    public void init() throws ServletException{
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/portal?user=root&password=Rocky@1.");
    		
    	}catch(ClassNotFoundException e) {
    		 throw new ServletException("Database driver not found", e);
    		
    	}catch(SQLException e) {
    		throw new ServletException("Error connecting to the database", e);
    	}
    	
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.sendRedirect("Home.html");
    	
    }
      
    
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
    	resp.setHeader("X-Content-Type-Options", "nosniff");
    	String fs=req.getParameter("fs");
    	String ls=req.getParameter("ls");
    	String date=req.getParameter("date");
    	String de=req.getParameter("de");
    	String email=req.getParameter("email");
    	String pass=req.getParameter("password");
    	String pass1=req.getParameter("cp");
  
    	try {
    		pr=con.prepareStatement(sql);
    	
    		pr.setString(1, fs);
    		pr.setString(2, ls);
    		pr.setString(3, date);
    		pr.setString(4, email);
    		pr.setString(5, de);
    		pr.setString(6, pass);
    	    int row = pr.executeUpdate();
    	    if(row>0) {
    	    	resp.getWriter().println("<script>alert('SignUp Succesfull...Now Please Login');</script>");
                RequestDispatcher rd = req.getRequestDispatcher("login.html");
                rd.include(req, resp);

    	    }else {
    	    	resp.getWriter().println("<script>alert('Failed to Signup...Please Check Detailes');</script>");
                RequestDispatcher rd = req.getRequestDispatcher("Signup.html");
                rd.include(req, resp);

    	    }
    	}   
        catch (NumberFormatException e) {
            resp.getWriter().println("Invalid input");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	try {
    		if (rs != null) rs.close();
            if (pr != null) pr.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    	
    }

}
