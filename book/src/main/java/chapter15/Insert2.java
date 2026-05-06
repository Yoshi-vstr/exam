package chapter15;

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
import tool.Page;

@WebServlet(urlPatterns={"/chapter15/insert2"})
public class Insert2 extends HttpServlet {
	public void doPost (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Page.header(out);
		try {
			String name=request.getParameter("name");
			int price=Integer.parseInt(request.getParameter("price"));
			
			Product p=new Product();
			p.setName(name);
			p.setPrice(price);
			
			ProductDAO dao=new ProductDAO();
			dao.insert(p);
			
			List<Product> list=dao.search("");
			for (Product q: list) {
				out.println(q.getId());
				out.println(":");
				out.println(q.getName());
				out.println(":");
				out.println(q.getPrice());
				out.println("<br>");
			}
		} catch (Exception e) {
			e.printStackTrace(out);
		}
		Page.footer(out);
	}
}

