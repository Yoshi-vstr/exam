package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Teacher;

public class TeacherDAO extends DAO {
	public Teacher search(String login, String password)
		throws Exception {
		Teacher teacher=null;

		Connection con=getConnection();

		PreparedStatement st;
		st=con.prepareStatement(
			"select * from teacher where login=? and password=?");
		st.setString(1, login);
		st.setString(2, password);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			teacher=new Teacher();
			teacher.setId(rs.getInt("id"));
			teacher.setLogin(rs.getString("login"));
			teacher.setPassword(rs.getString("password"));
		}

		st.close();
		con.close();
		return teacher;
	}
}
