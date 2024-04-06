package shoppingcart;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shoppingcart.VisitCountingDao;


//import VisitCounterDao, create an object, and use its counting method.
public class VisitCountingServlet extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		VisitCountingDao counterDao = new VisitCountingDao();
		int times = counterDao.getVisitTime();
		PrintWriter outPrintWriter = response.getWriter();
		outPrintWriter.print("This servlet has been visited " + times + " times");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
