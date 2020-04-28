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
        
        populateMap();
        
        //create a stand-in invisible root node
        TreeItem<String> root = new TreeItem<>();
        root.setExpanded(true);
        
        //creating the preference categories
        TreeItem<String> gui = new TreeItem<>("GUI");
        gui.setExpanded(true);
        gui.setGraphic(makeImage("resources/icons/mkvtoolnix-gui-small.png"));
        
        TreeItem<String> multiplexer = new TreeItem<>("Multiplexer");
        multiplexer.setExpanded(true);
        multiplexer.setGraphic(makeImage("resources/icons/multiplexer-small.png"));
        
        TreeItem<String> infoTool = new TreeItem<>("Info Tool");
        infoTool.setGraphic(makeImage("resources/icons/info-tool-small.png"));
        
        TreeItem<String> headerEditor = new TreeItem<>("Header Editor");
        headerEditor.setGraphic(makeImage("resources/icons/header-editor-small.png"));
        
        TreeItem<String> chapterEditor = new TreeItem<>("Chapter Editor");
        chapterEditor.setGraphic(makeImage("resources/icons/chapter-editor-small.png"));
        
        TreeItem<String> job = new TreeItem<>("Jobs & job queue");
        job.setGraphic(makeImage("resources/icons/job-output-small.png"));
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
                    pane = paneMap.get(newValue.getValue());
                    if(pane != null)
                    {
                        preferencePane.getChildren().clear();
                        preferencePane.getChildren().add(pane);
                    }
                });
        
        preferencePane.getChildren().add(paneMap.get("GUI"));
    }    
    
    
    private ImageView makeImage(String imagePath)
    {
        ImageView image = new ImageView(new Image(imagePath));
        image.setFitHeight(22);
        image.setFitWidth(22);
        return image;
    }
    
    private void populateMap()
    {
        paneMap.put("GUI", makeNewScreenshotPane("resources/screenshots/Preference-GUI.png"));
        paneMap.put("- Often used selections", makeNewScreenshotPane("resources/screenshots/Preference-Often_used_selections.png"));
        paneMap.put("Multiplexer", makeNewScreenshotPane("resources/screenshots/Preference-Multiplexer.png"));
        paneMap.put("- Predefined values", new PredefinedValuesController());
        paneMap.put("- Default values", makeNewScreenshotPane("resources/screenshots/Preference-Default_values.png"));
        paneMap.put("- Deriving track languages", makeNewScreenshotPane("resources/screenshots/Preference-Deriving_track_languages.png"));
        paneMap.put("- Enabling items", makeNewScreenshotPane("resources/screenshots/Preference-Enabling_items.png"));
        paneMap.put("- Playlists & Blu-rays", makeNewScreenshotPane("resources/screenshots/Preference-Playlists_&_Blu-rays.png"));
        paneMap.put("Info Tool", makeNewScreenshotPane("resources/screenshots/Preference-Info_tool.png"));
        paneMap.put("Header Editor", makeNewScreenshotPane("resources/screenshots/Preference-Header_editor.png"));
        paneMap.put("Chapter Editor", makeNewScreenshotPane("resources/screenshots/Preference-Chapter_editor.png"));
        paneMap.put("Jobs & job queue", makeNewScreenshotPane("resources/screenshots/Preference-Jobs_&_job_queue.png"));
        paneMap.put("- Executing actions", new ExecutingActionsController());
    }
    
    private Pane makeNewScreenshotPane(String imagePath)
    {
        Pane temp = null;
        FXMLLoader loader = new FXMLLoader(HCI_MKVToolNix.class.getResource("ScreenshotPreferencePane.fxml"));
        try
        {
            temp = loader.load();
            ImageView image = (ImageView)temp.getChildren().get(0);
            image.setImage(new Image(imagePath));
        }
        catch(IOException e)
        {
            throw new RuntimeException(e.getMessage());
        }
        return temp;
    }
}