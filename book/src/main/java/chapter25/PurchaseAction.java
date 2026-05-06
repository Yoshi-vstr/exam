package chapter25;

import java.util.List;

import bean.Item;
import dao.PurchaseDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class PurchaseAction extends Action {
	@SuppressWarnings("unchecked")
	public String execute( 
		HttpServletRequest request, HttpServletResponse response
	)throws Exception{
		
		HttpSession session=request.getSession();
		
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		if (name.isEmpty()|| address.isEmpty()) {
			return"purchase-error-empty.jsp";
		}
		
		PurchaseDAO dao= new PurchaseDAO();
		List<Item> cart=(List<Item>)session.getAttribute("cart");
		if (cart==null || !dao.insert(cart,  name,  address)) {
			return "purchase-error-insert.jsp";
		}
		
		session.removeAttribute("cart");
		return "purchase-out.jsp";
	}
}