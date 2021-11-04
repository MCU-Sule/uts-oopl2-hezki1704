/**
 * @auhtor 1972004 - Yehezkiel Christian
 */

package com.example.squiddemo.dao;

import com.example.squiddemo.entity.Player;
import com.example.squiddemo.util.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO implements daointerface<Player>{
    @Override
    public int addData(Player data) {
        int result =0 ;
        try {
            String query ="INSERT INTO Player (id,Nama,Umur,Keahlian) VALUES (?,?,?,?)";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1,data.getId());
            ps.setString(2,data.getNama());
            ps.setInt(3,data.getUmur());
            ps.setString(4,data.getKeahlian());
            result =ps.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public int delData(Player data) {
        int result = 0;
        try {
            String query ="DELETE FROM Player WHERE id = ? ";
            Connection conn = JDBCConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ps.setInt(1,data.getId());
            result =ps.executeUpdate();
            if (result!= 0){
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public int updateData(Player data) {
        int result = 0;
        try {
            String query = "UPDATE Player set Nama=?, Umur=?, Keahlian=? WHERE id =?";
            Connection conn =JDBCConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ps.setString(1,data.getNama());
            ps.setInt(2,data.getUmur());
            ps.setString(3,data.getKeahlian());
            ps.setInt(4,data.getId());
            result = ps.executeUpdate();
            if(result!=0){
                conn.commit();
            }else {
                conn.rollback();
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public ObservableList<Player> showData() {
        ObservableList<Player> players = FXCollections.observableArrayList();
        try {
            String query ="SELECT * FROM Player";
            PreparedStatement ps;
            ps= JDBCConnection.getConnection().prepareStatement(query);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String Nama = rs.getString("Nama");
                int Umur = rs.getInt("Umur");
                String Keahlian = rs.getString("Keahlian");
                Player player = new Player(id,Nama,Umur,Keahlian);
                players.add(player);
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return players;
    }
}
