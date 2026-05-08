package s;

import java.io.IOException;
import java.util.List;

import bean.Student;
import dao.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/s/studentlist"})
public class StudentList extends HttpServlet {

    public void doGet(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        try {
            String entYear = request.getParameter("entYear");
            String classNum = request.getParameter("classNum");
            String attend = request.getParameter("attend");

            StudentDAO dao = new StudentDAO();
            List<Student> list;

            if ((entYear == null || entYear.equals("0")) &&
                (classNum == null || classNum.equals("0")) &&
                attend == null) {

                list = dao.selectAll();

            } else {

                list = dao.filter(entYear, classNum, attend != null);
            }

            request.setAttribute("list", list);

            request.getRequestDispatcher("/t/studentlist.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}