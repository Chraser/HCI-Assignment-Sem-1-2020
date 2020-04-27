
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci_mkvtoolnix;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

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

    public TrackModel(CheckBox codec, String trackType, Label copyItem, String language, String trackName) {
        this.codec = codec;
        this.trackType = trackType;
        this.copyItem = copyItem;
        this.language = language;
        this.trackName = trackName;
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
}
