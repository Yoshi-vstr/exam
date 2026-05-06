package chapter14;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns={"/chapter14/insert"})
public class Insert extends HttpServlet {
	public void doPost (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Page.header(out);
		try {
			InitialContext ic=new InitialContext();
			DataSource ds=(DataSource)ic.lookup(
				"java:/comp/env/jdbc/book");
			Connection con=ds.getConnection();
			
			String name=request.getParameter("name");
			int price=Integer.parseInt(request.getParameter("price"));
			
			PreparedStatement st=con.prepareStatement("insert into product(name,price) values(?, ?)");
			st.setString(1,name);
			st.setInt(2, price);
			int line=st.executeUpdate(); //sqlの実行
							//SELECT以外の時
			if (line>0) {
				out.print("追加に成功しました。");
			}

			st.close();
			con.close(); //databaseDisconnection
		} catch (Exception e) {
			e.printStackTrace(out);
		}
		Page.footer(out);
	}
}