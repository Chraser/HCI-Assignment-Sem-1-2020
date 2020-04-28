/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci_mkvtoolnix;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Kay Men Yap 19257442
 */
public class PredefinedValuesController extends AnchorPane
{
    @FXML
    private ComboBox predefinedList;
            
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
        
        predefinedList.getItems().addAll("Predefined video track names", 
                                         "Predefined audio track names", 
                                         "Predefined subtitle track names",
                                         "Predefined split sizes",
                                         "Predefined split duration");
        predefinedList.getSelectionModel().selectFirst();
    }
}
