/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.hahieu;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.haha.Package;

/**
 *
 * @author LAPTOP D&N
 */
public class PackageDBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Package> getAllPackage() {
        List<Package> list = new ArrayList<>();
        String query = "select * from Package";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Package(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public ArrayList<Package> getAll() {
        ArrayList<Package> packageList = new ArrayList<>();
        try {
            String sql = "select * from package";
            conn = new DBContext().connection;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Package pack_age = new Package();
                pack_age.setId(rs.getInt("id"));
                pack_age.setPackage_name(rs.getString("package_name"));
                pack_age.setDuration(rs.getInt("duration"));
                packageList.add(pack_age);
            }
            return packageList;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public int totalCourse() throws SQLException {
        String query = "select COUNT(*) from [dbo].[Price_Package]";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public Package getPackageByID(int pid) {
        String query = "select * from package where id = ? ";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, pid);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Package(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void editPackage(String package_name, int duration, int id) {
        String query = "update Package\n"
                + "set \n"
                + "package_name = ?, \n"
                + "duration = ?\n"
                + "where id = ?";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, package_name);
            ps.setInt(2, duration);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public static void main(String[] args) throws SQLException {
        PackageDBContext db = new PackageDBContext();
        List<Package> p = db. getAllPackage() ;
        for (Package package1 : p) {
            System.out.println(package1.toString());
        }
    }
}
