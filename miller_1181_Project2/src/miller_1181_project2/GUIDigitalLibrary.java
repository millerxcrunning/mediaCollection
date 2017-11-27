/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miller_1181_project2;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Michael Miller
 * CS1181L-06
 * Instructor: C. Rodabough
 * TA: Steve Betts
 */
public class GUIDigitalLibrary extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        
        Parent root = (Parent) loader.load();
        
        FXMLDocumentController documentController = (FXMLDocumentController) loader.getController();
        
        Scene scene = new Scene(root);
        stage.setTitle("Media Collection");
        stage.setOnCloseRequest((WindowEvent e)->
        {
            try {
                documentController.library.storeCollection();
                System.out.println("Changes were saved.");
            } catch (Exception ex) {
                System.out.println("Cannot save and close.");
                ex.getMessage();
            }
        });
        
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
