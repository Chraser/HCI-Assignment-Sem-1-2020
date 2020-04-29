/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci_mkvtoolnix;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Kay Men Yap 19257442
 */
public class HCI_MKVToolNix extends Application {
    private static Stage primaryStage;
    
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader(StartMenuController.class.getResource("StartMenu.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
        
        Scene scene = new Scene(root);
        
        stage.setTitle("MKVToolNix GUI Start Menu");
        stage.setScene(scene);
        stage.getIcons().add(new Image("resources/icons/mkvtoolnix-gui-big.png"));
        stage.show();
        stage.setOnCloseRequest(e ->
        {
            Platform.exit();
            System.exit(0);
        });
    }
    
    public static Stage getStage()
    {
        return primaryStage;
    }
    
    @FXML
    public void exitApplication(ActionEvent event) {
       Platform.exit();
       System.out.println("am closing");
    }

    
    @Override
    public void stop(){
        System.out.println("Stage is closing");
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
