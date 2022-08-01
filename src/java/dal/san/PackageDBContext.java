/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.san;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Package;

/**
 *
 * @author sannx
 */
public class PackageDBContext extends DBContext {

    public Package find(int id) {
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[package_name]\n"
                    + "      ,[duration]\n"
                    + "  FROM [dbo].[Package]\n"
                    + "  WHERE id = " + id;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Package pack = new Package();
                pack.setId(rs.getInt("id"));
                pack.setName(rs.getString("package_name"));
                pack.setDuration(rs.getString("duration"));
                return pack;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
