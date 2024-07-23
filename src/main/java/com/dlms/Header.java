package com.dlms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Header
 */
@WebServlet("/Header")
public class Header extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Header() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out= response.getWriter();
		  out.print("<html>");
		  out.print("<head>");
		  out.print("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
		  out.print("<title>header page</title>");
		  out.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
		  out.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"mainstyle.css\">");
		  out.print("</head>");
		  out.print("<body>");
	


          out.print("<div class='HeadingPart' >");
		  out.print("<img class='aa' src='images/Emblem_of_Ethiopia.svg.png'>");
		
		  out.print("<div class=\"head\">\r\n"
		  		+ "           \r\n"
		  		+ "                <h2> Debre Markos Driving License information\r\n"
		  		+ "                         Management System </h2>\r\n"
		  		+ "             </div> ");
		  out.print(" </div>");
		  out.print("</div>");
	      out.print("<ul class=menu>"
	        		+ "<li><a href=\"UserRegistrationPage\">Create User</a></li>"
	        		+ "<li><a href=\"UpdateUsersInformation\">Update User</a></li>"
	        		+ "<li><a href= \"AdminViewUsersPage\" >View User</a></li>"
	        		+ "<li><a href=  \"Logout\" >logout</a></li>"
	        		+ "</ul>");

		  out.print("</body>");
		  out.print("</html>");
	}

}
