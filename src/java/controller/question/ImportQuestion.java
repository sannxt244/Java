/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.question;

import dal.donghieu.QuestionDBContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.ws.spi.http.HttpContext;
import model.Answer;
import model.Course;
import model.Question;
import model.Role;
import model.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author dell
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1204 * 10,
        maxRequestSize = 1024 * 1024 * 50,
        location = "E:\\Projects\\merge4\\web\\question"
)
public class ImportQuestion extends HttpServlet {

    private final int COLUMN_INDEX_QUESTION = 0;
    private final int COLUMN_INDEX_ANSWER1 = 1;
    private final int COLUMN_INDEX_ANSWER2 = 2;
    private final int COLUMN_INDEX_ANSWER3 = 3;
    private final int COLUMN_INDEX_ANSWER4 = 4;
    private final int COLUMN_INDEX_IS_SOLUTION = 5;
    private final int COLUMN_INDEX_EXPLAIN = 6;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuestionDBContext questionDB = new QuestionDBContext();
        ArrayList<Course> courses = questionDB.getAllCourse();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("view/questionImport.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//         User a = new User();
//        a.setUserName("expert1");
//        Role r = new Role();
//        r.setId(2);
//        a.setRole(r);

        QuestionDBContext questionDB = new QuestionDBContext();
        ArrayList<Course> courses = questionDB.getAllCourse();
        request.setAttribute("courses", courses);
        User user = (User) request.getSession().getAttribute("account");

        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String[] topicId_Raw = request.getParameterValues("topicId");
        int check = 0;
        for (String string : topicId_Raw) {
            if (string.equals("-1")) {
                check = 1;
            }
        }

        int topicId = Integer.parseInt(request.getParameter("topicId"));
        if (check == 1) {
            topicId = -1;
        }
        Part part = part = request.getPart("questionExcel");
        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        if (filename.length() <= 0) {
            request.setAttribute("fileError", "You must upload your question file");
            request.getRequestDispatcher("view/questionImport.jsp").forward(request, response);
        } else {
            if (!(filename.endsWith(".xlsx") || filename.endsWith(".xls"))) {
                request.setAttribute("fileError", "You must upload excel file");
                request.getRequestDispatcher("view/questionImport.jsp").forward(request, response);
            } else {
                // String realPath = request.getServletContext().getRealPath("/img/blog");
//                String image = "";
//                String fullpath = realPath + "/" + filename;
//                if (!Files.exists(Paths.get(realPath))) {
//                    Files.createDirectory(Paths.get(realPath));
//                }
                part.write(File.separator + filename);

                // response.getWriter().print(url);
//                image = "img" + File.separator + "blog" + File.separator + filename;
                String excelPath = "E:\\Projects\\merge4\\web\\question\\" + filename;

                ArrayList<Question> question_Raw = readExcel(excelPath);
                ArrayList<Question> questions = new ArrayList<>();
                for (Question question : question_Raw) {
                    if (question.getContent() != null && question.getContent().length() > 0 && !questionDB.questionIsExist(question.getContent())) {
                        int checkSolution = 0;
                        for (Answer answer : question.getAnswerList()) {
                            if (answer.getContent() == null || answer.getContent().length() <= 0) {
                                question.getAnswerList().remove(answer);

                            }
                            if (answer.isIsSolution() == true) {
                                checkSolution = 1;
                            }
                        }

                        question.setTopicId(topicId);
                        if (question.getAnswerList().size() > 0 && checkSolution == 1) {
                            questions.add(question);
                        }
                    }
                }
                questionDB.importQuestion(user, questions);
                response.sendRedirect("questionlist");
            }
        }

    }

    public ArrayList<Question> readExcel(String excelPath) throws IOException {
        ArrayList<Question> questions = new ArrayList<>();

        // Get file
        InputStream inputStream = new FileInputStream(new File(excelPath));

        // Get workbook
        Workbook wb = getWorkbook(inputStream, excelPath);

        // Get sheet
        Sheet sheet = wb.getSheetAt(0);

        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }

            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            // Read cells and set value for book object
            Question question = new Question();
            int indexSolution = 0;

            while (cellIterator.hasNext()) {
                Answer answer = new Answer();
                answer.setIsSolution(false);
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case COLUMN_INDEX_QUESTION:
                        question.setContent((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_ANSWER1:
                        answer.setContent(getCellValue(cell).toString());
                        question.getAnswerList().add(answer);
                        break;
                    case COLUMN_INDEX_ANSWER2:
                        answer.setContent(getCellValue(cell).toString());
                        question.getAnswerList().add(answer);
                        break;
                    case COLUMN_INDEX_ANSWER3:
                        answer.setContent(getCellValue(cell).toString());
                        question.getAnswerList().add(answer);
                        break;
                    case COLUMN_INDEX_ANSWER4:
                        answer.setContent(getCellValue(cell).toString());
                        question.getAnswerList().add(answer);
                        break;
                    case COLUMN_INDEX_IS_SOLUTION:
                        try {
                            indexSolution = new BigDecimal((double) cellValue).intValue() - 1;
                            question.getAnswerList().get(indexSolution).setIsSolution(true);
                        } catch (Exception e) {
                            continue;
                        }

                        break;
                    case COLUMN_INDEX_EXPLAIN:
                        try {
                            question.getAnswerList().get(indexSolution).setExplain((String) getCellValue(cell));
                        } catch (Exception e) {
                            continue;
                        }

                        break;
                    default:
                        break;
                }

            }
            questions.add(question);
        }

        wb.close();
        inputStream.close();

        return questions;
    }

    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }

    private Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
