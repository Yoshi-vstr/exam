package chapter17;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import bean.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Page;

@WebServlet(urlPatterns={"/chapter17/cart-get"})
public class CartGet extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doPost (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		Page.header(out);
		
		HttpSession session=request.getSession();
		
	
		List<Product> cart=(List<Product>)session.getAttribute("cart");
		if (cart!=null) {
			for(Product p : cart) {
				out.println("<p>");
				out.println(p.getName());
				out.println(":");
				out.println(p.getPrice());
				out.println("</p>");
				
			}
		}
		Page.footer(out);
	}
}