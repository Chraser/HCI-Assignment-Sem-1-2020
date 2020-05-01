
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci_mkvtoolnix;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Kay Men Yap 19257442
 */
public class TrackModel 
{    
    private CheckBox codec;
    
    private String trackType;
    
    private Label copyItem;
    
    private String language;
    
    private String trackName;
    
    private Label defaultTrack;
    
    private Label forcedTrack;
    
    private String sourceFile;
    
    private String directory;

    public TrackModel(CheckBox codec, String trackType, Label copyItem, String language, String trackName, Label defaultTrack, Label forcedTrack, String sourceFile, String directory) {
        this.codec = codec;
        this.trackType = trackType;
        this.copyItem = copyItem;
        this.language = language;
        this.trackName = trackName;
        this.defaultTrack = defaultTrack;
        this.forcedTrack = forcedTrack;
        this.sourceFile = sourceFile;
        this.directory = directory;
    }

    public CheckBox getCodec() {
        return codec;
    }

    public String getTrackType() {
        return trackType;
    }

    public Label getCopyItem() {
        return copyItem;
    }

    public String getLanguage() {
        return language;
    }

    public String getTrackName() {
        return trackName;
    }

    public Label getDefaultTrack() {
        return defaultTrack;
    }

    public Label getForcedTrack() {
        return forcedTrack;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public String getDirectory() {
        return directory;
    }

    public void setCodec(CheckBox codec) {
        this.codec = codec;
    }

    public void setTrackType(String trackType) {
        this.trackType = trackType;
    }

    public void setCopyItem(Label copyItem) {
        this.copyItem = copyItem;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public void setDefaultTrack(Label defaultTrack) {
        this.defaultTrack = defaultTrack;
    }

    public void setForcedTrack(Label forcedTrack) {
        this.forcedTrack = forcedTrack;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
    
    public TrackModel clone()
    {
        CheckBox codecCpy = new CheckBox(codec.getText());
        codecCpy.setSelected(codec.isSelected());
        return new TrackModel(codec, trackType, makeNewLabel(copyItem.getText()), language,
                              trackName, makeNewLabel(defaultTrack.getText()), 
                              makeNewLabel(forcedTrack.getText()), sourceFile, directory);
    }
    
    private Label makeNewLabel(String s)
    {
        Label label;
        ImageView image;
        if(s.equals("Yes"))
        {
            image = new ImageView(new Image("resources/icons/green-tick.png"));
            image.setFitWidth(20);
            image.setFitHeight(20);
            label = new Label("Yes", image);
        }
        else
        {
            image = new ImageView(new Image("resources/icons/dialog-cancel.png"));
            image.setFitWidth(20);
            image.setFitHeight(20);
            label = new Label("No", image);
        }
        return label;
    }
}
