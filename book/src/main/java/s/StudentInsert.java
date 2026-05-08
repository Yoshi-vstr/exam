package s;

import java.io.IOException;

import bean.School;
import bean.Student;
import dao.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns={"/s/studentinsert"})
public class StudentInsert extends HttpServlet {

    public void doPost(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String no = request.getParameter("no");
            String name = request.getParameter("name");

            int entYear = Integer.parseInt(request.getParameter("entYear"));
            String classNum = request.getParameter("classNum");

            HttpSession session = request.getSession();
            School school = (School) session.getAttribute("school");

            boolean attend = (entYear == 2026);

            Student stu = new Student();
            stu.setNo(no);
            stu.setName(name);
            stu.setEntYear(entYear);
            stu.setClassNum(classNum);
            stu.setAttend(attend);
            stu.setSchool(school);

            StudentDAO dao = new StudentDAO();
            boolean result = dao.save(stu);

            request.setAttribute("result", result);

            request.getRequestDispatcher("/t/studentinsertresult.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}