package dal.san;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Registration;

public class CourseRegisterDBContext extends DBContext {

    /**
     * Get all records
     *
     * @return
     */
    public ArrayList<Registration> find() {
        ArrayList<Registration> registrations = new ArrayList<>();
        String sql = "SELECT [username]\n"
                + "      ,[courseId]\n"
                + "      ,[date_register]\n"
                + "      ,[stateId]\n"
                + "      ,[packageId]\n"
                + "  FROM [dbo].[Course_Register]";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Registration registration = new Registration();
                registration.setUsername(rs.getString("username"));
                registration.setCourseId(rs.getInt("courseId"));
                registration.setDateRegister(rs.getDate("date_register"));
                registration.setStateId(rs.getInt("stateId"));
                registration.setPackageId(rs.getInt("packageId"));

                registrations.add(registration);
            }
            return registrations;
        } catch (SQLException ex) {
            Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Get a records
     *
     * @param username
     * @param courseId
     * @return
     */
    public Registration find(String username, int courseId) {
        String sql = "SELECT [username]\n"
                + "      ,[courseId]\n"
                + "      ,[date_register]\n"
                + "      ,[stateId]\n"
                + "      ,[packageId]\n"
                + "  FROM [dbo].[Course_Register]\n"
                + "  WHERE [username] = '" + username + "' AND [courseId] = " + courseId;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Registration registration = new Registration();
                registration.setUsername(rs.getString("username"));
                registration.setCourseId(rs.getInt("courseId"));
                registration.setDateRegister(rs.getDate("date_register"));
                registration.setStateId(rs.getInt("stateId"));
                registration.setPackageId(rs.getInt("packageId"));

                return registration;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Registration> findByCourseId(int courseId) {
        ArrayList<Registration> registrations = new ArrayList<>();
        String sql = "SELECT [username]\n"
                + "      ,[courseId]\n"
                + "      ,[date_register]\n"
                + "      ,[stateId]\n"
                + "      ,[packageId]\n"
                + "      ,[lastUpdatedBy]\n"
                + "  FROM [dbo].[Course_Register]\n"
                + "  WHERE [courseId] = " + courseId;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Registration registration = new Registration();
                registration.setUsername(rs.getString("username"));
                registration.setCourseId(rs.getInt("courseId"));
                registration.setDateRegister(rs.getDate("date_register"));
                registration.setStateId(rs.getInt("stateId"));
                registration.setPackageId(rs.getInt("packageId"));
                registration.setLastUpdateBy(rs.getString("lastUpdatedBy"));

                registrations.add(registration);
            }
            return registrations;
        } catch (SQLException ex) {
            Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Update item
     *
     * @param registration
     * @return
     */
    public Registration update(Registration registration) {
        String sql = "UPDATE [dbo].[Course_Register]\n"
                + "   SET [date_register] = '" + registration.getDateRegister() + "'\n"
                + "      ,[stateId] = " + registration.getStateId() + "\n"
                + "      ,[packageId] = " + registration.getPackageId() + "\n"
                + "      ,[lastUpdatedBy] = '" + registration.getLastUpdateBy() + "'\n"
                + "   WHERE [username] = '" + registration.getUsername() + "' AND [courseId] = " + registration.getCourseId() + "";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.executeUpdate();
            return registration;
        } catch (SQLException ex) {
            Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Add item
     *
     * @param registration
     * @return
     */
    public Registration add(Registration registration) {
        String sql = "INSERT INTO [dbo].[Course_Register]\n"
                + "           ([username]\n"
                + "           ,[courseId]\n"
                + "           ,[date_register]\n"
                + "           ,[stateId]\n"
                + "           ,[packageId])\n"
                + "     VALUES\n"
                + "           ('" + registration.getUsername() + "'\n"
                + "           ," + registration.getCourseId() + "\n"
                + "           ,GETDATE()\n"
                + "           ," + registration.getStateId() + "\n"
                + "           ," + registration.getPackageId() + ")";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.executeUpdate();
            return registration;
        } catch (SQLException ex) {
            Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
