package gitlet.entity;

import gitlet.config.FileConfig;

import java.io.File;
import java.io.Serializable;

import static gitlet.Utils.*;

public class Blob  implements Serializable{
    private final String trackerFilePath;
    private final String trackedFileName;
    private final String content;
    public static final File DIR = FileConfig.OBJECTS_DIR;

    Blob(Builder builder){
        this.trackerFilePath = builder.trackerFilePath;
        this.trackedFileName = builder.trackedFileName;
        this.content = builder.content;
    }

    public String getName(){ return generateSHA(); }
    private String generateSHA(){ return sha1(trackerFilePath, trackedFileName, content); }

    public static class Builder{
        private String trackerFilePath;
        private String trackedFileName;
        private String content;

        public void setTrackerFilePath(String trackerFilePath) {
            this.trackerFilePath = trackerFilePath;
        }

        public void setTrackedFileName(String trackedFileName) {
            this.trackedFileName = trackedFileName;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
