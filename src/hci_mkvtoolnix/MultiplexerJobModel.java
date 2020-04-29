/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci_mkvtoolnix;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kay Men Yap 19257442
 */
public class MultiplexerJobModel
{
    private static int counter = 0;
    
    private String name;
    private List<SourceFileModel> sourceFileList = new ArrayList<>();
    private List<TrackModel> trackList = new ArrayList<>();
    
    public static MultiplexerJobModel makeNewJob()
    {
        counter++;
        return new MultiplexerJobModel("NewJob" + counter + ".mp4");
    }

    public MultiplexerJobModel(String name) {
        this.name = name;
    }
    
    public MultiplexerJobModel(String name, List<SourceFileModel> sourceFileList, List<TrackModel> trackList) {
        this.name = name;
        this.sourceFileList = sourceFileList;
        this.trackList = trackList;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setSourceFileList(List<SourceFileModel> sourceFileList)
    {
        this.sourceFileList = sourceFileList;
    }
    
    public void setTrackList(List<TrackModel> trackList)
    {
        this.trackList = trackList;
    }
    
    public void addFile(SourceFileModel file)
    {
        sourceFileList.add(file);
    }
    
    public void addTrack(TrackModel track)
    {
        trackList.add(track);
    }
    
    public void removeFile(int index)
    {
        sourceFileList.remove(index);
    }
    
    public void removeTrack(int index)
    {
        trackList.remove(index);
    }

    public String getName() {
        return name;
    }

    public List<SourceFileModel> getSourceFileList() {
        return sourceFileList;
    }

    public List<TrackModel> getTrackList() {
        return trackList;
    }
}
