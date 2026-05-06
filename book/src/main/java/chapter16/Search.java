package chapter16;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import bean.Product;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/chapter16/search"})
public class Search extends HttpServlet {
	public void doPost (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		try {
			String keyword=request.getParameter("keyword");
			
			ProductDAO dao=new ProductDAO();
			List<Product> list=dao.search(keyword);
			
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("attribute2.jsp") .forward(request, response);
			
		}catch (Exception e) {
			e.printStackTrace(out);
	
		}
	}
}		