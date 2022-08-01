package dal.san;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Price_Package;

public class PricePackageDBContext extends DBContext {


    public Price_Package findById(int courseId, int packageId) {

        try {
            String sql = "SELECT [courseId]\n"
                    + "      ,[packageId]\n"
                    + "      ,[price]\n"
                    + "      ,[priceSale]\n"
                    + "  FROM [dbo].[Price_Package]\n"
                    + "  WHERE [courseId] = " + courseId + " AND [packageId] = " + packageId;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Price_Package pricePackage = new Price_Package();
                pricePackage.setCourseId(rs.getInt("courseId"));
                pricePackage.setPackageId(rs.getInt("packageId"));
                pricePackage.setPrice(rs.getInt("price"));
                pricePackage.setPriceSale(rs.getInt("priceSale"));
                return pricePackage;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Price_Package> findByCourseId(int courseId) {
        ArrayList<Price_Package> pricePackageList = new ArrayList<>();
        try {
            String sql = "SELECT [courseId]\n"
                    + "      ,[packageId]\n"
                    + "      ,[price]\n"
                    + "      ,[priceSale]\n"
                    + "  FROM [dbo].[Price_Package]\n"
                    + "  WHERE [courseId] = " + courseId;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Price_Package pricePackage = new Price_Package();
                pricePackage.setCourseId(rs.getInt("courseId"));
                pricePackage.setPackageId(rs.getInt("packageId"));
                pricePackage.setPrice(rs.getInt("price"));
                pricePackage.setPriceSale(rs.getInt("priceSale"));
                pricePackageList.add(pricePackage);
            }
            return pricePackageList;
        } catch (SQLException ex) {
            Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Price_Package> find() {
        ArrayList<Price_Package> pricePackageList = new ArrayList<>();
        try {
            String sql = "SELECT [courseId]\n"
                    + "      ,[packageId]\n"
                    + "      ,[price]\n"
                    + "      ,[priceSale]\n"
                    + "  FROM [dbo].[Price_Package]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Price_Package pricePackage = new Price_Package();
                pricePackage.setCourseId(rs.getInt("courseId"));
                pricePackage.setPackageId(rs.getInt("packageId"));
                pricePackage.setPrice(rs.getInt("price"));
                pricePackage.setPriceSale(rs.getInt("priceSale"));

                pricePackageList.add(pricePackage);
            }
            return pricePackageList;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public Price_Package findByLowestPrice(int courseId) {
        try {
            String sql = "SELECT TOP 1 \n"
                    + "  [courseId]\n"
                    + " ,[packageId]\n"
                    + " ,[price]\n"
                    + " ,[priceSale]\n"
                    + "  FROM [SWP].[dbo].[Price_Package]\n"
                    + "	 WHERE [courseId] = ?\n"
                    + "  ORDER BY price ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Price_Package pricePackage = new Price_Package();
                pricePackage.setCourseId(rs.getInt("courseId"));
                pricePackage.setPackageId(rs.getInt("packageId"));
                pricePackage.setPrice(rs.getInt("price"));
                pricePackage.setPriceSale(rs.getInt("priceSale"));

                return pricePackage;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public ArrayList<Price_Package> getPricePackageByCourseId(ArrayList<Price_Package> pricePackageList, int courseId) {
        ArrayList<Price_Package> pricePackageByIdList = new ArrayList<>();
        for (Price_Package pricePackage : pricePackageList) {
            if (pricePackage.getCourseId() == courseId) {
                pricePackageByIdList.add(pricePackage);
            }
        }
        return pricePackageByIdList;
    }

    public ArrayList<Price_Package> getPricePackageById(ArrayList<Price_Package> pricePackageList, int id) {
        ArrayList<Price_Package> pricePackageByIdList = new ArrayList<>();
        for (Price_Package pricePackage : pricePackageList) {
            if (pricePackage.getPackageId() == id) {
                pricePackageByIdList.add(pricePackage);
            }
        }
        return pricePackageByIdList;
    }

}
