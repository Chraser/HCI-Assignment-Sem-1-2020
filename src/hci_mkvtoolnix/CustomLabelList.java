/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci_mkvtoolnix;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

/**
 *
 * @author Chraser
 */
public class CustomLabelList extends ListCell<Label>
{
    @Override
    protected void updateItem(Label item, boolean empty) 
    {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setGraphic(null);
            setText(null);
        } else {
            setText(item.getText());
            setGraphic(item.getGraphic());
        }
    }
}
