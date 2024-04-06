package shoppingcart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import shoppingcart.FileStoreCounterDao;

/**
 * Servlet implementation class FileStoreCounterServlet
 */
public class FileStoreCounterServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        
        FileStoreCounterDao counterDao = new FileStoreCounterDao();
		int times = counterDao.getVisitCount();
        
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.print("This servlet has been visited " + times + " times totally.");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
