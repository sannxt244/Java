package controller.lesson;

import controller.authorization.BaseAuthController;
import dal.san.CourseRegisterDBContext;
import dal.san.QuizFormatDBContext;
import dal.san.LessonDBContext;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Lesson;
import model.Topic;
import model.User;
import model.quizFormat;

public class LessonServlet extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get user
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");
        session.setAttribute("account", account);
        // Get Database Context
        LessonDBContext lessonDB = new LessonDBContext();
        QuizFormatDBContext quizFormatDB = new QuizFormatDBContext();
        // Params
        String lessonIdRaw = request.getParameter("lesson");
        try {
            int courseId = Integer.parseInt(request.getParameter("course"));

            if (new CourseRegisterDBContext().find(account.getUserName(), courseId) != null && new CourseRegisterDBContext().find(account.getUserName(), courseId).getStateId() == 1) {

                ArrayList<Topic> topicList = lessonDB.getTopicsByCourseId(courseId);

                if (topicList.size() > 0) {
                    int lessonId = lessonDB.getTopicsByCourseId(courseId).get(0).getLessonList().get(0).getId();

                    if (lessonIdRaw != null) {
                        lessonId = Integer.parseInt(lessonIdRaw);
                    }

                    // Check quiz exist
                    for (Topic topic : topicList) {
                        quizFormat quizFormat = quizFormatDB.findByTopic(topic.getId());
                        topic.setQuizFormat(quizFormat);
                    }
                    Lesson lesson = lessonDB.getLessonById(lessonId);

                    request.setAttribute("courseId", courseId);
                    request.setAttribute("topicList", topicList);
                    request.setAttribute("lesson", lesson);

                }

                request.getRequestDispatcher("lesson-view.jsp").forward(request, response);
            } else {
                response.sendRedirect("course");
            }
        } catch (IOException | NumberFormatException | ServletException e) {
            System.out.println(e.toString());
            response.sendRedirect("course");
        }
    }
     protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         processGet(request, response);
    }
}
