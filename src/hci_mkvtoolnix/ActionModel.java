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
public class ActionModel 
{
    private boolean configActive;
    
    private String name;
    
    private int type;
    
    private boolean jobSuccess;
    
    private boolean jobExit;
    
    private boolean jobStopped;
    
    private String[] extraInfo;

    public ActionModel(boolean configActive, String name, int type, boolean jobSuccess, boolean jobExit, boolean jobStopped, String[] extraInfo) {
        this.configActive = configActive;
        this.name = name;
        this.type = type;
        this.jobSuccess = jobSuccess;
        this.jobExit = jobExit;
        this.jobStopped = jobStopped;
        this.extraInfo = extraInfo;
    }
    
    public boolean getConfigActive()
    {
        return configActive;
    }
    
    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public boolean getJobSuccess() {
        return jobSuccess;
    }

    public boolean getJobExit() {
        return jobExit;
    }

    public boolean getJobStopped() {
        return jobStopped;
    }

    public String[] getExtraInfo() {
        return extraInfo;
    }
    
    public void setConfigActive()
    {
        this.configActive = !this.configActive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(int type) {
        this.type = type;
        if(type == 0)
        {
            extraInfo = new String[]{"-someArgs"};
        }
        else if(type == 1)
        {
            extraInfo = new String[]{"CodingSounds.ogg","1000%"};
        }
        else
        {
            extraInfo = null;
        }
    }

    public void setJobSuccess() {
        this.jobSuccess = !this.jobSuccess;
    }

    public void setJobExit() {
        this.jobExit = !this.jobExit;
    }

    public void setJobStopped() {
        this.jobStopped = !this.jobStopped;
    }
}
