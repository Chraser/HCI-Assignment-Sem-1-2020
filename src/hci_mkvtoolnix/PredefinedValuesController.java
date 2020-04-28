/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci_mkvtoolnix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Kay Men Yap 19257442
 */
public class PredefinedValuesController extends AnchorPane
{
    @FXML
    private ComboBox categoryList;
    
    @FXML
    private ListView listView;
    
    private List<ObservableList> valueList = new ArrayList<>();
            
    public PredefinedValuesController()
    {
        FXMLLoader loader = new FXMLLoader(HCI_MKVToolNix.class.getResource("PredefinedValues.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try
        {
            loader.load();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
        
        categoryList.getItems().addAll("Predefined video track names", 
                                         "Predefined audio track names", 
                                         "Predefined subtitle track names",
                                         "Predefined split sizes",
                                         "Predefined split duration");
        categoryList.getSelectionModel().selectFirst();
        
        for(int i = 0; i < 5; i++)
        {
            valueList.add(FXCollections.observableArrayList());
        }
        valueList.get(0).addAll("Video", "MP4", "WHY");
        valueList.get(1).addAll("Wind", "Woosh", "Boom");
        valueList.get(2).addAll("SRT", "srtV2", "sub");
        valueList.get(3).addAll("128G", "256G", "4000M");
        valueList.get(4).addAll("420s", "01:11:11", "123m");
        
        listView.setItems(valueList.get(0));
    }
    
    @FXML
    private void handleAddAction(ActionEvent event)
    {
        int currentCategory = categoryList.getSelectionModel().getSelectedIndex();
        
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Entering predefined value");
        dialog.setHeaderText("Please enter a value");
        
        Optional<String> result = dialog.showAndWait();
        
        //update the list if the user entered a value
        if(result.isPresent())
        {
            if(!(result.get().equals("")))
            {
                valueList.get(currentCategory).add(result.get());
                listView.getSelectionModel().clearSelection();
                listView.setItems(valueList.get(currentCategory));
            }
        }
    }
    
    @FXML
    private void handleRemoveAction(ActionEvent event)
    {
        int currentCategory = categoryList.getSelectionModel().getSelectedIndex();
        int valueIndex = listView.getSelectionModel().getSelectedIndex();
        listView.getSelectionModel().clearSelection();
        valueList.get(currentCategory).remove(valueIndex);
        listView.setItems(valueList.get(currentCategory));
    }
    
    @FXML
    private void changeSelection(ActionEvent event)
    {
        int currentCategory = categoryList.getSelectionModel().getSelectedIndex();
        listView.setItems(valueList.get(currentCategory));
    }
}
