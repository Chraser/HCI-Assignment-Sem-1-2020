/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci_mkvtoolnix;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Kay Men Yap 19257442
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
            Image image = ((ImageView)item.getGraphic()).getImage();
            ImageView temp = new ImageView(image);
            temp.setFitWidth(32);
            temp.setFitHeight(32);
            setGraphic(temp);
        }
    }
}
