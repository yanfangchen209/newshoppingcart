package shoppingcart;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;  // Import HttpServletRequest
import javax.servlet.http.HttpServletResponse;  // Import HttpServletResponse




public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String gender = req.getParameter("gender");
		String name = req.getParameter("name");

		PrintWriter out = res.getWriter();
		String prefix = ""; 
		
		if ("m".equals(gender)){
			prefix = "Mr ";
		}
		if("f".equals(gender)) {
			prefix = "Mrs n ";
		}
		
		if(gender != null && name != null) {//http://localhost:8080/shoppingcart/hello?gender=p&name=gdgew
			out.print("Hello, ");
			out.print(prefix);
			out.print(name);
			out.print("!");
		}else if(gender != null && name == null) {
			out.print("Please enter your name: ");
		}else if(gender == null && name == null) {//http://localhost:8080/shoppingcart/hello
			out.print("Please enter your name and gender: ");
		}else if(gender == null && name != null) {
			out.print("Hello, ");
			out.print(name);
			out.print("!");
		}

		

		
		/*
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>My Dynamic HTML Hello Page</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello,</h1>");
        out.println("<p>" + prefix + "</p>");
        out.println("<p>" + name + "</p>");
        out.println("</body>");
        out.println("</html>");
        
		**/
			


	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
