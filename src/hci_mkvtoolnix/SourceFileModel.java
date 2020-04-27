/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci_mkvtoolnix;

/**
 *
 * @author Kay Men Yap 19257442
 */
public class SourceFileModel 
{    
    private String fileName;
    
    private String fileType;
    
    private String fileSize;
    
    private String directory;

    public SourceFileModel(String fileName, String fileType, String fileSize, String directory) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.directory = directory;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getDirectory() {
        return directory;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
