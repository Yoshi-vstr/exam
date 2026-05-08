package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDAO extends DAO {

    public ClassNum get(String classNum, School school) throws Exception {

        ClassNum cn = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM class_num WHERE class_num=? AND school_cd=?"
        );
        st.setString(1, classNum);
        st.setString(2, school.getCd());

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            cn = new ClassNum();
            cn.setClass_num(rs.getString("class_num"));
            cn.setSchool(school);
        }

        st.close();
        con.close();

        return cn;
    }

    public List<String> filter(School school) throws Exception {

        List<String> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT class_num FROM class_num WHERE school_cd=?"
        );
        st.setString(1, school.getCd());

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            list.add(rs.getString("class_num"));
        }

        st.close();
        con.close();

        return list;
    }

    public boolean save(ClassNum classNum) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "INSERT INTO class_num(class_num, school_cd) VALUES(?,?)"
        );
        st.setString(1, classNum.getClass_num());
        st.setString(2, classNum.getSchool().getCd());

        int result = st.executeUpdate();

        st.close();
        con.close();

        return result > 0;
    }

    public boolean save(ClassNum classNum, String newClassNum) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "UPDATE class_num SET class_num=? WHERE class_num=? AND school_cd=?"
        );
        st.setString(1, newClassNum);
        st.setString(2, classNum.getClass_num());
        st.setString(3, classNum.getSchool().getCd());

        int result = st.executeUpdate();

        st.close();
        con.close();

        return result > 0;
    }
}