/**
 * @auhtor 1972004 - Yehezkiel Christian
 */

package com.example.squiddemo.dao;

import com.example.squiddemo.entity.Hutang;
import com.example.squiddemo.entity.Player;
import com.example.squiddemo.util.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HutangDAO implements daointerface<Hutang>{
    @Override
    public int addData(Hutang data) {
        int result =0 ;
        try {
            String query ="INSERT INTO Hutang (id,PemberiUtang,Jumlah,Player_id) VALUES (?,?,?,?)";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1,data.getId());
            ps.setString(2,data.getPemberiHutang());
            ps.setDouble(3,data.getJumlah());
            ps.setInt(4,data.getPlayer().getId());
            result =ps.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public int delData(Hutang data) {
        int result = 0;
        try {
            String query ="DELETE FROM Hutang WHERE id = ? ";
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
    public int updateData(Hutang data) {
        return 0;
    }

    @Override
    public ObservableList<Hutang> showData() {
        ObservableList<Hutang> hutangs = FXCollections.observableArrayList();
        try {
            String query ="SELECT s.id, s.PemberiUtang, s.Jumlah, d.Player_id FROM" + "Hutang s JOIN Player d ON d.Player_id = d.id ";
            PreparedStatement ps;
            ps= JDBCConnection.getConnection().prepareStatement(query);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String PemberiUtang = rs.getString("PemberiUtang");
                double Jumlah = rs.getDouble("Jumlah");
                int Player_id = rs.getInt("Player_id");
                Player player = new Player(Player_id);
                Hutang hutang = new Hutang(id,PemberiUtang,Jumlah,player);
                hutangs.add(hutang);
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return hutangs;
    }
}

