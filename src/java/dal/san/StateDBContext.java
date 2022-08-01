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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.State;

/**
 *
 * @author sannx
 */
public class StateDBContext extends DBContext {

    public State find(int id) {
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[state]\n"
                    + "  FROM [dbo].[State]\n"
                    + "  WHERE id = " + id;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                State state = new State();
                state.setId(rs.getInt("id"));
                state.setState(rs.getString("state"));
                return state;
            }

        } catch (SQLException ex) {
            Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<State> findAll() {
        ArrayList<State> states = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[state]\n"
                + "  FROM [dbo].[State]";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                State state = new State();
                state.setId(rs.getInt("id"));
                state.setState(rs.getString("state"));
                states.add(state);
            }
            return states;
        } catch (SQLException ex) {
            Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
