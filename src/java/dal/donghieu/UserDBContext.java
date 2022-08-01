/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.donghieu;

import dal.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.ResetLog;
import model.Role;
import model.User;

/**
 *
 * @author dell
 */
public class UserDBContext extends DBContext {

    public int getLastId() {
        try {
            String sql = "SELECT MAX(UserID) FROM dbo.userInfomation";
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public User getUserByUsername(String userName) {
        try {
            String sql = "SELECT * FROM dbo.[user] WHERE username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, userName);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserName(rs.getString(1));
                u.setPassword(rs.getString(2));
                Role r = new Role();
                r.setId(rs.getInt(3));
                u.setRole(r);
                u.setFullName(rs.getString(4));
                u.setGender(rs.getBoolean(5));
                u.setEmail(rs.getString(6));
                u.setPhoneNumber(rs.getString(7));
                u.setAvatar(rs.getString(8));
                u.setVerify(rs.getBoolean(9));
                u.setRegisterDate(rs.getDate(10));
                u.setRegisterStatus(rs.getInt(11));
                return u;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkEmailExist(String email) {
        try {
            String sql = "SELECT * FROM dbo.[user] WHERE email = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void registerUser(User user) {
        String sql = "INSERT INTO [user]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[roleId]\n"
                + "           ,[fullname]\n"
                + "           ,[gender]\n"
                + "           ,[email]\n"
                + "           ,[mobile]\n"
                + "           ,[verify]\n"
                + "           ,[registerDate]\n"
                + "           ,[registerStatus])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,GETDATE()\n"
                + "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, user.getUserName());
            stm.setString(2, user.getPassword());
            stm.setInt(3, user.getRole().getId());
            stm.setString(4, user.getFullName());
            stm.setBoolean(5, user.isGender());
            stm.setString(6, user.getEmail());
            stm.setString(7, user.getPhoneNumber());
            stm.setBoolean(8, false);
            if (user.getRole().getId() == 2) {
                stm.setInt(9, 2);
            } else {
                stm.setInt(9, 1);
            }
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public User getUserByEmail(String email) {
        try {
            String sql = "SELECT * FROM dbo.[user] WHERE email = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserName(rs.getString(1));
                u.setPassword(rs.getString(2));
                Role r = new Role();
                r.setId(rs.getInt(3));
                u.setRole(r);
                u.setFullName(rs.getString(4));
                u.setGender(rs.getBoolean(5));
                u.setEmail(rs.getString(6));
                u.setPhoneNumber(rs.getString(7));
                u.setAvatar(rs.getString(8));
                u.setVerify(rs.getBoolean(9));
                u.setRegisterDate(rs.getDate(10));
                u.setRegisterStatus(rs.getInt(11));
                return u;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<ResetLog> getResetLog(User user) {
        try {
            ArrayList<ResetLog> resetLogs = new ArrayList<>();
            String sql = "SELECT * FROM dbo.ResetPassword_Log\n"
                    + "WHERE username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user.getUserName());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ResetLog resetLog = new ResetLog();
                resetLog.setId(rs.getInt(1));
                resetLog.setChanged(rs.getBoolean(2));
                User u = new User();
                u.setUserName(rs.getString(3));
                resetLog.setUser(u);
                resetLog.setFrom(rs.getTimestamp(4));
                resetLog.setTo(rs.getTimestamp(5));
                resetLogs.add(resetLog);
            }
            return resetLogs;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateResetLog(User user, int time, boolean changed) {
//        long millis = System.currentTimeMillis();
//        Timestamp from = new Timestamp(millis);
//        Timestamp to = new Timestamp(millis + 60 * time * 1000);

        String sql = "UPDATE [ResetPassword_Log]\n"
                + "   SET [changed] = ?\n"
                + " WHERE username = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            if (changed == false) {
                stm.setBoolean(1, false);
            } else {
                stm.setBoolean(1, true);
            }
            stm.setString(2, user.getUserName());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void createResetLog(User user, int time) {
        long millis = System.currentTimeMillis();
        Timestamp from = new Timestamp(millis);
        Timestamp to = new Timestamp(millis + 60 * time * 1000);

        String sql = "INSERT INTO [ResetPassword_Log]\n"
                + "           ([changed]\n"
                + "           ,[username]\n"
                + "           ,[from]\n"
                + "           ,[to])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setBoolean(1, false);
            stm.setString(2, user.getUserName());
            stm.setTimestamp(3, from);
            stm.setTimestamp(4, to);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void updateUser(User user) {
        String sql = "UPDATE [user]\n"
                + "   SET [username] = ?\n"
                + "      ,[password] = ?\n"
                + "      ,[roleId] = ?\n"
                + "      ,[fullname] = ?\n"
                + "      ,[gender] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[mobile] = ?\n"
                + " WHERE username = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, user.getUserName());
            stm.setString(2, user.getPassword());
            stm.setInt(3, user.getRole().getId());
            stm.setString(4, user.getFullName());
            stm.setBoolean(5, user.isGender());
            stm.setString(6, user.getEmail());
            stm.setString(7, user.getPhoneNumber());
            stm.setString(8, user.getUserName());
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public int getLastResetLogIdByUsername(User user) {
        try {
            String sql = "SELECT MAX(id)  FROM dbo.ResetPassword_Log\n"
                    + "GROUP BY username\n"
                    + "HAVING username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user.getUserName());

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ResetLog getResetLogById(int logId) {
        try {

            String sql = "SELECT * FROM dbo.ResetPassword_Log\n"
                    + "WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, logId);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                ResetLog resetLog = new ResetLog();
                resetLog.setId(rs.getInt(1));
                resetLog.setChanged(rs.getBoolean(2));
                User u = new User();
                u.setUserName(rs.getString(3));
                resetLog.setUser(u);
                resetLog.setFrom(rs.getTimestamp(4));
                resetLog.setTo(rs.getTimestamp(5));
                return resetLog;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User getAccount(String userName, String password) {
        try {
            String sql = "SELECT * FROM dbo.[user]\n"
                    + "WHERE username = ? AND password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, userName);
            stm.setString(2, password);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserName(rs.getString(1));
                u.setPassword(rs.getString(2));
                Role r = new Role();
                r.setId(rs.getInt(3));
                u.setRole(r);
                u.setFullName(rs.getString(4));
                u.setGender(rs.getBoolean(5));
                u.setEmail(rs.getString(6));
                u.setPhoneNumber(rs.getString(7));
                u.setAvatar(rs.getString(8));
                u.setVerify(rs.getBoolean(9));
                u.setRegisterDate(rs.getDate(10));
                u.setRegisterStatus(rs.getInt(11));
                return u;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void verifyUser(String username) {
        String sql = "UPDATE [dbo].[user]\n"
                + "   SET \n"
                + "      [verify] = ?\n"
                + " WHERE username = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setBoolean(1, true);
            stm.setString(2, username);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    // Minh
    public void User_update(User u) {
        String sql = "update [user] set [password] = ?, roleId = ?, fullname = ?, gender = ?, email =?, mobile =? where username =?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, u.getPassword());
            st.setInt(2, u.getRole().getId());
            st.setString(3, u.getFullName());
            st.setBoolean(4, u.isGender());
            st.setString(5, u.getEmail());
            st.setString(6, u.getPhoneNumber());
            st.setString(7, u.getUserName());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void User_changePassword(String username, String password) {
        String sql = "update [user] set [password] = ? where username = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, password);
            st.setString(2, username);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getStatus(String username) {
        try {
            String sql = "SELECT registerStatus FROM dbo.[user]\n"
                    + "WHERE username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public boolean isVerified(String username) {
        try {
            String sql = "SELECT verify FROM dbo.[user] WHERE username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getBoolean(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void addByAdmin(User user) {
        int gender = user.isGender() ? 1 : 0;
        String sql = "INSERT INTO [dbo].[user]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[roleId]\n"
                + "           ,[fullname]\n"
                + "           ,[gender]\n"
                + "           ,[email]\n"
                + "           ,[mobile]\n"
                + "           ,[verify]\n"
                + "           ,[registerDate]\n"
                + "           ,[registerStatus]\n"
                + "           ,[createdBy])\n"
                + "     VALUES\n"
                + "           ('" + user.getUserName() + "'\n"
                + "           ,'1'\n"
                + "           ,1\n"
                + "           ,''\n"
                + "           ," + gender + "\n"
                + "           ,'" + user.getEmail() + "'\n"
                + "           ,'" + user.getPhoneNumber() + "'\n"
                + "           ,1\n"
                + "           ,GETDATE()\n"
                + "           ,1\n"
                + "           ,'" + user.getCreatedBy() + "')";
        PreparedStatement stm = null;
        System.out.println(sql);
        try {
            stm = connection.prepareStatement(sql);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }


    public int getNumberOfRoles(String userName, String url) {

        try {
            String sql = "SELECT COUNT(*) AS total  FROM dbo.[user] JOIN dbo.Role\n"
                    + "ON Role.id = [user].roleId\n"
                    + "JOIN dbo.Role_Feature\n"
                    + "ON Role_Feature.roleId = Role.id\n"
                    + "JOIN dbo.Feature ON Feature.id = featureId\n"
                    + "WHERE username = ? AND link = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, userName);
            stm.setString(2, url);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return  rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }


}
