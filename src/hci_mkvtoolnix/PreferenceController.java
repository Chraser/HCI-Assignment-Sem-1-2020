/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci_mkvtoolnix;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chraser
 */
public class PreferenceController implements Initializable {
    
    @FXML
    private TreeView<String> preferenceTree;
    
    @FXML
    private Pane preferencePane;
    
    private Pane toBeImplementedPane = null;
    
    private Map<String, Pane> paneMap = new HashMap<>();
    
    private Stage stage;
    
    @FXML
    private void handleOkAction(ActionEvent event){
        stage.close();
    }
    
    @FXML
    private void handleCancelAction(ActionEvent event){
        stage.close();
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //initialise toBeImplementedPane
        FXMLLoader loader = new FXMLLoader(HCI_MKVToolNix.class.getResource("ToBeImplemented.fxml"));
        try
        {
            toBeImplementedPane = loader.load();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e.getMessage());
        }
        
        //populate the paneMap
        paneMap.put("- Predefined values", new Pane());
        paneMap.put("- Executing actions", new Pane());
        
        //create a stand-in invisible root node
        TreeItem<String> root = new TreeItem<>();
        root.setExpanded(true);
        
        //creating the preference categories
        TreeItem<String> gui = new TreeItem<>("GUI");
        gui.setExpanded(true);
        gui.setGraphic(makeImage("icons/mkvtoolnix-gui-small.png"));
        
        TreeItem<String> multiplexer = new TreeItem<>("Multiplexer");
        multiplexer.setExpanded(true);
        multiplexer.setGraphic(makeImage("icons/multiplexer-small.png"));
        
        TreeItem<String> infoTool = new TreeItem<>("Info Tool");
        infoTool.setGraphic(makeImage("icons/info-tool-small.png"));
        
        TreeItem<String> headerEditor = new TreeItem<>("Header Editor");
        headerEditor.setGraphic(makeImage("icons/header-editor-small.png"));
        
        TreeItem<String> chapterEditor = new TreeItem<>("Chapter Editor");
        chapterEditor.setGraphic(makeImage("icons/chapter-editor-small.png"));
        
        TreeItem<String> job = new TreeItem<>("Jobs & job queue");
        job.setGraphic(makeImage("icons/job-output-small.png"));
        job.setExpanded(true);
        
        //now creating and adding the sub parts of each category in preference list
        gui.getChildren().add(new TreeItem<String>("- Often used selections"));
        multiplexer.getChildren().addAll(new TreeItem<String>("- Predefined values"),
                                         new TreeItem<String>("- Default values"),
                                         new TreeItem<String>("- Deriving track languages"),
                                         new TreeItem<String>("- Enabling items"),
                                         new TreeItem<String>("- Playlists & Blu-rays"));
        job.getChildren().add(new TreeItem<String>("- Executing actions"));
        
        //adding all of the categories of preference list to root node
        root.getChildren().addAll(gui, multiplexer, infoTool, headerEditor, chapterEditor, job);
        
        //adding root to the tree view and hiding root node
        preferenceTree.setRoot(root);
        preferenceTree.setShowRoot(false);
        
        preferenceTree.setFixedCellSize(25.0);
        
        //add the listener so that each tree item can have click interactions
        preferenceTree.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends TreeItem<String>> obs, TreeItem<String> oldValue, TreeItem<String> newValue) ->
                {
                    Pane pane;
                    if(paneMap.containsKey(newValue.getValue()))
                    {
                        pane = paneMap.get(newValue.getValue());
                    }
                    else
                    {
                        pane = toBeImplementedPane;
                    }
                    preferencePane.getChildren().clear();
                    preferencePane.getChildren().add(pane);
                });
        
        preferencePane.getChildren().add(toBeImplementedPane);
    }    
    
    
    private ImageView makeImage(String imagePath)
    {
        ImageView image = new ImageView(new Image(imagePath));
        image.setFitHeight(22);
        image.setFitWidth(22);
        return image;
    }
}
