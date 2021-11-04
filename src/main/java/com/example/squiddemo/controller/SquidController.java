/**
 * @auhtor 1972004 - Yehezkiel Christian
 */

package com.example.squiddemo.controller;

import com.example.squiddemo.dao.HutangDAO;
import com.example.squiddemo.dao.PlayerDAO;
import com.example.squiddemo.entity.Hutang;
import com.example.squiddemo.entity.Player;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SquidController implements Initializable {

    @FXML
    private TextField txtPemberiUtang;

    @FXML
    private TextField txtJumlah;

    @FXML
    private ComboBox<Player> cmbPeserta;

    @FXML
    private TableView<Player> tablePemain;

    @FXML
    private TableColumn<Player, String> col1;

    @FXML
    private TableColumn<Player, String> col2;

    @FXML
    private TableColumn<Player, String> col3;

    @FXML
    private TableColumn<Player, String> col4;

    @FXML
    private TableView<Hutang> tableHutang;

    @FXML
    private TableColumn <Hutang, String> colid;

    @FXML
    private TableColumn <Hutang, String> colhutang;

    @FXML
    private TableColumn <Hutang, String> coljumlah;

    private ObservableList <Player>players;
    private ObservableList <Hutang> hutangs;
    private ObservableList <Player> getPlayers(){return players;}

    @FXML
    private void addAction() throws IOException {
        Stage new_stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModalPage.fxml"));
        Parent root =fxmlLoader.load();

        Scene new_scene = new Scene(root);
        new_stage.setScene(new_scene);
        new_stage.initModality(Modality.WINDOW_MODAL);
        new_stage.setTitle("Add Pemain");
        new_stage.show();

    }

    @FXML
    private void editAction() throws IOException{
        Stage new_stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModalPage.fxml"));
        Parent root =fxmlLoader.load();

        Scene new_scene = new Scene(root);
        new_stage.setScene(new_scene);
        new_stage.initModality(Modality.WINDOW_MODAL);
        new_stage.setTitle("Edit Pemain");
        new_stage.show();
    }

    @FXML
    private void delAction() throws IOException{
        Player selectedplayer =tablePemain.getSelectionModel().getSelectedItem();
        PlayerDAO playerdao = new PlayerDAO();
        playerdao.delData(selectedplayer);
        players.clear();
        players.addAll(playerdao.showData());
        tablePemain.refresh();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PlayerDAO playerdao = new PlayerDAO();
        players =playerdao.showData();
        HutangDAO hutangdao = new HutangDAO();
        hutangs = hutangdao.showData();
        cmbPeserta.setItems(players);
        tablePemain.setItems(players);
        col1.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        col2.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getNama()));
        col3.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getUmur())));
        col4.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getKeahlian()));
        tableHutang.setItems(hutangs);
        colid.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getPlayer().getId())));
        colhutang.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getPemberiHutang()));
        coljumlah.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getJumlah())));
    }
}
