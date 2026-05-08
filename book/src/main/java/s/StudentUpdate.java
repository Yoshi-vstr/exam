package s;

import java.io.IOException;

import bean.Student;
import dao.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/s/studentupdate"})
public class StudentUpdate extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String no = request.getParameter("no");

            StudentDAO dao = new StudentDAO();
            Student student = dao.get(no);

            request.setAttribute("student", student);

            request.getRequestDispatcher("/t/studentupdate.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");

            String no = request.getParameter("no");
            String name = request.getParameter("name");
            int entYear = Integer.parseInt(request.getParameter("entYear"));
            String classNum = request.getParameter("classNum");

            Student student = new Student();
            student.setNo(no);
            student.setName(name);
            student.setEntYear(entYear);
            student.setClassNum(classNum);
            boolean attend = request.getParameter("attend") != null;
            student.setAttend(attend);
            StudentDAO dao = new StudentDAO();

            dao.save(student);

            response.sendRedirect(request.getContextPath() + "/s/studentlist");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}