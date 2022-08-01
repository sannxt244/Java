package dal.san;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Course;

public class CourseDBContext extends DBContext {

    public ArrayList<Course> find() {
        ArrayList<Course> courseList = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[name]\n"
                    + "      ,[isActive]\n"
                    + "      ,[created_by]\n"
                    + "      ,[description]\n"
                    + "      ,[categoryId]\n"
                    + "  FROM [SWP].[dbo].[Course]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setIsActive(rs.getBoolean("isActive"));
                course.setCreated_by(rs.getString("created_by"));
                course.setDescription(rs.getString("description"));
                course.setCategoryId(rs.getInt("categoryId"));

                courseList.add(course);
            }
            return courseList;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public Course find(int id) {
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[name]\n"
                    + "      ,[isActive]\n"
                    + "      ,[created_by]\n"
                    + "      ,[description]\n"
                    + "      ,[categoryId]\n"
                    + "  FROM [SWP].[dbo].[Course]"
                    + "  WHERE id = " + id;
            System.out.println(sql);
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setIsActive(rs.getBoolean("isActive"));
                course.setCreated_by(rs.getString("created_by"));
                course.setDescription(rs.getString("description"));
                course.setCategoryId(rs.getInt("categoryId"));
                return course;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
}
