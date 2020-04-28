/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci_mkvtoolnix;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Kay Men Yap 19257442
 */
public class ExecutingActionsController extends AnchorPane
{
    public ExecutingActionsController()
    {
        FXMLLoader loader = new FXMLLoader(HCI_MKVToolNix.class.getResource("ExecutingActions.fxml"));
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
    }
}
