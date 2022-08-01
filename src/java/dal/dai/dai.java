/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.dai;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CourseRegister;
import model.dai.mycourse;

/**
 *
 * @author HDC
 */
public class dai extends DBContext {

    public List<CourseRegister> CourseRegister(int id) {
        List<CourseRegister> list = new ArrayList<>();
        String query = "select *\n"
                + "from Price_Package, Package\n"
                + "where Package.id= Price_Package.packageId\n"
                + "and Price_Package.courseId=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int pid = rs.getInt(5);
                int price = rs.getInt(3);
                String pname = rs.getString(6);
                int duration = rs.getInt(7);

                CourseRegister cr = new CourseRegister(pid, price, pname, duration);
                list.add(cr);
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }

        return list;
    }

    public void buy(String username, int courseId, int packageId) { // insert

    
        String sql = "insert into Course_Register (username, courseId, date_register,stateId, packageId) values (?,?,GETDATE(),3,?)";
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setInt(2, courseId);
            st.setInt(3, packageId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    
    public List<mycourse> AllMyCourse(String username) {
        List<mycourse> list = new ArrayList<>();
        String query = "select Course_Register.courseId, course.name, Course_Register.date_register,Package.package_name, state.state \n" +
"from Package, Course_Register, State ,course\n" +
"where Package.id=Course_Register.packageId \n" +
"and Course_Register.stateId= State.id\n" +
"and course.id= Course_Register.courseId\n" +
"and Course_Register.username='"+username+"'\n" +
"and state.state = 'Success'";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id=rs.getInt(1);
                String name=rs.getString(2);
                String date=rs.getString(3);
                String package_name=rs.getString(4);
                String state =rs.getString(5);
                mycourse mc = new mycourse(id, name, date, package_name, state);
                list.add(mc);
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }

        return list;
    }


       public List<mycourse> AllMyRegistrations(String username) {
        List<mycourse> list = new ArrayList<>();
        String query = "select Course_Register.courseId, course.name, Course_Register.date_register,Package.package_name, state.state \n" +
"from Package, Course_Register, State ,course\n" +
"where Package.id=Course_Register.packageId \n" +
"and Course_Register.stateId= State.id\n" +
"and course.id= Course_Register.courseId\n" +
"and Course_Register.username='"+username+"'\n" +
"and state.state = 'Submitted'";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id=rs.getInt(1);
                String name=rs.getString(2);
                String date=rs.getString(3);
                String package_name=rs.getString(4);
                String state =rs.getString(5);
                mycourse mc = new mycourse(id, name, date, package_name, state);
                list.add(mc);
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }

        return list;
    }
       
    public void change(int courseId, String username){
        String sql ="UPDATE [dbo].[Course_Register]\n" +
"   SET [stateId] = '2'\n" +
" WHERE courseId= ?\n" +
" and username =?";
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, courseId);
            st.setString(2, username);
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
}
