/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miller_1181_project2;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

/**
 *
 * @author Michael Miller
 */
public class FXMLDocumentController implements Initializable {
    
    MediaCollection library = new MediaCollection();
    final ToggleGroup toggleGroup = new ToggleGroup();
    
    @FXML
    private TextField loanedToTF;

    @FXML
    private ListView<MediaItem> listView;

    @FXML
    private TextField loanedOnTF;

    @FXML
    private TextField formatTF;

    @FXML
    private TextField titleTF;
    
    @FXML
    private RadioButton byDateLoanedButton;

    @FXML
    private RadioButton byTitleButton;

    @FXML
    void loanOnAction(ActionEvent event) {
        try {
            System.out.println("1");
            String dateFormat = "dd/MM/yyyy";
            System.out.println("2");
            library.loanItem(listView.getSelectionModel().getSelectedItem(),
                    loanedToTF.getText(), new SimpleDateFormat(dateFormat).parse(loanedOnTF.getText()));
            System.out.println("3");
            listView.refresh();
        } catch (Exception ex) {
            System.out.println("Could not loan item");
        }
    }

    @FXML
    void addOnAction(ActionEvent event) {

        try {
            library.addItem(titleTF.getText(), formatTF.getText());
            listView.refresh();
        } catch (Exception ex) {
            System.out.println("Could not add " + titleTF.getText() + "." + 
                    ex.getMessage());
        }
        library.listItems();
    }

    @FXML
    void removeOnAction(ActionEvent event) {

        try {
            library.removeItem(listView.getSelectionModel().getSelectedItem());
            listView.refresh();
        } catch (Exception ex) {
            System.out.println("Could not remove " + 
                    listView.getSelectionModel().getSelectedItem() + "." + "\n" 
                    + ex.getMessage());
        }
        library.listItems();
    }

    @FXML
    void returnOnAction(ActionEvent event) {
        try {
            library.returnItem(listView.getSelectionModel().getSelectedItem());
            listView.refresh();
        } catch (Exception ex) {
            System.out.println("Could not return" + 
                    listView.getSelectionModel().getSelectedItem() + ".");;
        }
    }

    @FXML
    void byTitleButtonOnAction(ActionEvent event) {
        try
        {
            library.sortByTitle();
            listView.refresh();
        }
        catch (Exception ex) {
            System.out.println("Could not sort by title.");
        }
    }

    @FXML
    void byDateLoanedOnAction(ActionEvent event) {
        try
        {
            library.sortByDate();
            listView.refresh();
        }
        catch (Exception ex) {
            System.out.println("Could not sort by date.");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            library.readCollection();
        } catch (Exception ex) {
            System.out.println("Could not read the file.");
        }
        
        listView.setItems(library.getCollection());
        listView.refresh();
        
        byTitleButton.setToggleGroup(toggleGroup);
        byDateLoanedButton.setToggleGroup(toggleGroup);
        
    }    
    
}
