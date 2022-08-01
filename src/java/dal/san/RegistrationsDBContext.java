package dal.san;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Registration;

public class RegistrationsDBContext extends DBContext {

    public ArrayList<Registration> filter(int courseId, String username, String from, String to, String status, String email, String order) {
        ArrayList<Registration> registrations = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT [user].[username], [user].[email], [Course_Register].[courseId], [Course_Register].[date_register]\n"
                    + "	, [course].[name], [Package].[package_name], [Price_Package].[price], [State].[state], [Course_Register].[stateId]\n"
                    + "	, [Course_Register].[lastUpdatedBy] as 'lastUpdatedBy'\n"
                    + "	, [Course_Register].[date_register] as 'valid_from', [Course_Register].[packageId], DATEADD(MONTH, [Package].[duration]\n"
                    + "	, [Course_Register].[date_register]) as 'valid_to' FROM [user]\n    "
                    + "JOIN [Course_Register] ON [user].[username] = [Course_Register].[username]\n"
                    + "JOIN [course] ON [Course_Register].[courseId] = [course].[id]\n"
                    + "JOIN [State] ON [user].[registerStatus] = [State].[id]\n"
                    + "JOIN [Package] ON [Package].[id] = [Course_Register].[packageId]\n"
                    + "JOIN [Price_Package] ON [Package].[id] =[Price_Package].packageId AND [Course_Register].[courseId] = [Price_Package].[courseId]\n"
                    + "WHERE [Course_Register].[courseId] = " + courseId + "\n";

            if (username != null && username.trim().length() > 0) {
                sql += "AND [user].[username] LIKE '%" + username + "%'\n";
            }
            if (from != null && from.trim().length() > 0) {
                sql += "AND [Course_Register].[date_register] > '" + from + "'\n";
            }
            if (to != null && to.trim().length() > 0) {
                sql += "AND [Course_Register].[date_register] < '" + to + "'\n";
            }
            if (status != null && status.trim().length() > 0) {
                sql += "AND [State].[id] = " + status + "\n";
            }
            if (email != null && email.trim().length() > 0) {
                sql += "AND [user].[email] LIKE '%" + email + "%'\n";
            }
            if (order != null && order.trim().length() > 0) {
                sql += "ORDER BY " + order;
            }
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
            System.out.println(ex);
        }
        return null;
    }
}
