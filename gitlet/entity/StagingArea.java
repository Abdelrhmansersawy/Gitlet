package gitlet.entity;

import gitlet.config.FileConfig;
import gitlet.service.GitInitService;

import java.io.Serializable;

import static gitlet.Utils.readObject;
import static gitlet.Utils.writeObject;

public class StagingArea implements Serializable {
    private static StagingArea instance;

    public static StagingArea getInstance() {
        if (instance == null) {
            if(GitInitService.isAlreadyInitialized()){
                instance = read();
            }else{
                instance = new StagingArea();
            }
        }
        return instance;
    }

    public static StagingArea read(){
        return readObject(FileConfig.INDEX, StagingArea.class);
    }

    public void save(){
        writeObject(FileConfig.INDEX, StagingArea.getInstance());
    }

}
