package com.dlms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateTheory
 */
@WebServlet("/UpdateTheory")
public class UpdateTheory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTheory() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    private String driverName = "com.mysql.cj.jdbc.Driver";
    private String dbUrl = "jdbc:mysql://localhost:3306/dlms_db";
    private String dbUsername = "root";
    private String dbPassword = "root";
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setHeader("Pragma","no-cache");
	      response.setHeader("Cache-Control","no-store");
	      response.setHeader("Expires","0");
	      response.setDateHeader("Expires",-1);
	        PrintWriter out = response.getWriter();
	        HttpSession sessionA = request.getSession(false); // Retrieve the session without creating a new one if it doesn't exist
	        HttpSession session=request.getSession(true);
	        if (session.getAttribute("session_username")==null||session.getAttribute("Autority")==null)
	        {
	        response.sendRedirect("Login");
	         return;
	        }
	        if(!session.getAttribute("Autority").equals("Registrar")) {
	         response.sendRedirect("Login"); //redirect to Home page

	        }
	        String Session_user_name=null;
	        session=request.getSession();
	         if(session!=null) {
	        	 Session_user_name=(String)session.getAttribute("session_username");
	         //user_type=(String)session.getAttribute("utype");
	         }	        Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        PreparedStatement ps1 = null;
	        ResultSet rs1 = null;
	     

	        String driverId = request.getParameter("id");
	        String  Write=request.getParameter("write");
	        String  Save=request.getParameter("save");

	        try {
//	        	if(Write==null && Save==null) {
	            Class.forName(driverName);
	            conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

	            String sql = "select * from driverpersonalinformation where Drivers_id=?";
	            ps = conn.prepareStatement(sql);
	            ps.setString(1, driverId);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                String license_id = rs.getString("LISENCE_ID");

	                String sql1 = "select * from theoryresult where Drivers_id=?";
	                ps1 = conn.prepareStatement(sql1);
	                ps1.setString(1, driverId);
	                rs1 = ps1.executeQuery();

	                out.println("<html>");
	                out.println("<head>");
	                out.println("<title>Update Theory result</title>");
	                out.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/style1.css' />");
	                out.println("</head>");
	                out.println("<body>");

	                if (rs1.next()) {
	                    // Pre-fill the form with existing data
	                	out.print("<form action='Save_Theory?save=1&id="+driverId+"' class='H' method='post'>");
	                    out.print("<div class='Bform2'>");
	                    out.print("<div class='lo p'>");
	                    out.print("<label for='Dl'>Driving License Number</label>");
	                    out.print("<input type='number' name='license_id' value='" + license_id + "' id='Dl' required>");
	                    out.print("</div>");
	                    out.print("<div class='lo p'>");
	                    out.print("<label for='year'>Year</label>");
	                    out.print("<input type='date' name='year' id='year' value='" + rs1.getString("year") + "' required>");
	                    out.print("</div>");
	                    out.print("<div class='lo p'>");
	                    out.print("<label for='tresult'>Theory Result</label>");
	                    out.print("<input type='number' max='50' name='tresult' id='tresult' value='" + rs1.getInt("thoery_result") + "' required>");
	                    out.print("</div>");
	                    out.print("<br><br>");
	                    out.print("<input class='stu' type='submit' name='write' value='Update'> ");
	                    out.print("<a href='Theory_Result'>Back</a> ");
	                    out.print("</div>");
	                    out.print("</form>");
	                } else {
	                    out.println("<p>No record found fo-r driver ID: " + license_id + "</p>");
	                }
	           
//	            } else if (Write == null && Save!=null){
	                	  
//	                	  
	                		
	                 

	                out.println("</body>");
	                out.println("</html>");
	            
//	        	}
	        } 
	            }catch (Exception e) {
	            out.println("<p>Error: " + e.getMessage() + "</p>");
	        } finally {
	            // Close connections and statements in a finally block
	            try {
	                if (rs != null) rs.close();
	                if (ps != null) ps.close();
	                if (rs1 != null) rs1.close();
	                if (ps1 != null) ps1.close();
	              
	                if (conn != null) conn.close();
	            } catch (Exception e) {
	                out.println("<p>Error in closing resources: " + e.getMessage() + "</p>");
	            }
	        }
	}

}
