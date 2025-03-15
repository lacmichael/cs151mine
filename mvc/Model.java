package mvc;

import java.io.Serializable;

public class Model extends Publisher implements Serializable {
    
    Boolean unsavedChanges = false;
    String fileName = null;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        changed();
    }

    public boolean hasUnsavedChanges() {
        return unsavedChanges;
    }

    public void setUnsavedChanges(boolean value) {
        this.unsavedChanges = value;
    }

    protected void changed() {
        this.unsavedChanges = true;
        notifySubscribers();
    }
}
