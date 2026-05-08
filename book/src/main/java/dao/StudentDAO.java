package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDAO extends DAO {

    // GET 
    public Student get(String no) throws Exception {

        Student student = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM student WHERE no=?"
        );
        st.setString(1, no);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            student = new Student();
            student.setNo(rs.getString("no"));
            student.setName(rs.getString("name"));
            student.setEntYear(rs.getInt("ent_year"));
            student.setClassNum(rs.getString("class_num"));
            student.setAttend(rs.getBoolean("is_attend"));
        }

        st.close();
        con.close();

        return student;
    }

    //  SELECT ALL
    public List<Student> selectAll() throws Exception {

        List<Student> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM student"
        );

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Student s = new Student();
            s.setNo(rs.getString("no"));
            s.setName(rs.getString("name"));
            s.setEntYear(rs.getInt("ent_year"));
            s.setClassNum(rs.getString("class_num"));
            s.setAttend(rs.getBoolean("is_attend"));
            list.add(s);
        }

        st.close();
        con.close();

        return list;
    }

    //  FILTER 
    public List<Student> filter(String entYear, String classNum, boolean attend) throws Exception {

        List<Student> list = new ArrayList<>();

        Connection con = getConnection();

        String sql = "SELECT * FROM student WHERE 1=1";

        if (entYear != null && !entYear.equals("0")) {
            sql += " AND ent_year = ?";
        }

        if (classNum != null && !classNum.equals("0")) {
            sql += " AND class_num = ?";
        }

        if (attend) {
            sql += " AND is_attend = true";
        }

        PreparedStatement st = con.prepareStatement(sql);

        int i = 1;

        if (entYear != null && !entYear.equals("0")) {
            st.setInt(i++, Integer.parseInt(entYear));
        }

        if (classNum != null && !classNum.equals("0")) {
            st.setString(i++, classNum);
        }

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Student s = new Student();
            s.setNo(rs.getString("no"));
            s.setName(rs.getString("name"));
            s.setEntYear(rs.getInt("ent_year"));
            s.setClassNum(rs.getString("class_num"));
            s.setAttend(rs.getBoolean("is_attend"));
            list.add(s);
        }

        st.close();
        con.close();

        return list;
    }

    //  SAVE (INSERT + UPDATE)
    public boolean save(Student student) throws Exception {

        Connection con = getConnection();

        PreparedStatement check = con.prepareStatement(
            "SELECT COUNT(*) FROM student WHERE no=?"
        );
        check.setString(1, student.getNo());

        ResultSet rs = check.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        PreparedStatement st;

        if (count > 0) {
            st = con.prepareStatement(
                "UPDATE student SET name=?, ent_year=?, class_num=?, is_attend=? WHERE no=?"
            );
            st.setString(1, student.getName());
            st.setInt(2, student.getEntYear());
            st.setString(3, student.getClassNum());
            st.setBoolean(4, student.isAttend());
            st.setString(5, student.getNo());

        } else {
            st = con.prepareStatement(
                "INSERT INTO student(no, name, ent_year, class_num, is_attend) VALUES(?,?,?,?,?)"
            );
            st.setString(1, student.getNo());
            st.setString(2, student.getName());
            st.setInt(3, student.getEntYear());
            st.setString(4, student.getClassNum());
            st.setBoolean(5, student.isAttend());
        }

        int result = st.executeUpdate();

        st.close();
        con.close();

        return result > 0;
    }
}