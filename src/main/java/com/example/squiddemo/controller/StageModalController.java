/**
 * @auhtor 1972004 - Yehezkiel Christian
 */

package com.example.squiddemo.controller;

import com.example.squiddemo.dao.PlayerDAO;
import com.example.squiddemo.entity.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StageModalController implements Initializable {
    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNama;

    @FXML
    private TextField txtUmur;

    @FXML
    private TextField txtKeahlian;

    @FXML
    private Button CANCEL;

    private SquidController sc;

    @FXML
    public void okk (){
        Player player = new Player();
        player.setId(Integer.valueOf(txtId.getText()));
        player.setNama(txtNama.getText());
        player.setUmur(Integer.valueOf(txtUmur.getText()));
        player.setKeahlian(txtKeahlian.getText());
        PlayerDAO playerdao = new PlayerDAO();
        playerdao.addData(player);
    }

    @FXML
    public void CANCELBTN(){
        Stage stage = (Stage) CANCEL.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setController (SquidController sc){
        this.sc =sc;
    }
}
