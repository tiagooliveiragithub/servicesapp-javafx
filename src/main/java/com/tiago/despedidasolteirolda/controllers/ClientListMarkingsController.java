package com.tiago.despedidasolteirolda.controllers;

import com.tiago.despedidasolteirolda.data.FileManager;
import com.tiago.despedidasolteirolda.entities.Marking;
import com.tiago.despedidasolteirolda.entities.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.ResourceBundle;

public class ClientListMarkingsController implements Initializable {

    @FXML
    private TableView<Marking> tableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search();
    }

    void search() {
        Collection<Marking> markings = FileManager.getFileManager().getMarkings().values();
        HashSet<Marking> userMarkings = new HashSet<>();
        for(Marking marking : markings) {
            if(marking.getClient() == Session.user) userMarkings.add(marking);
        }
        tableView.getItems().setAll(userMarkings);
    }

    @FXML
    void backScene(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("clientMenu.fxml"));
            Scene menuScene = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(menuScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
