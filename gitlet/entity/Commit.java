package gitlet.entity;

// TODO: any imports you need here

import gitlet.config.FileConfig;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

import static gitlet.Utils.*;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */

public class Commit implements Serializable {

    private final LocalDateTime timeStamp;
    private final String branchName;
    private final String message;
    private final String firstParent;
    private final String secondParent;
    private final Map<String,String> blobs;
    private final int depth;
    public static final File DIR = FileConfig.OBJECTS_DIR;

    public Commit(Builder builder){
        this.timeStamp = builder.timeStamp;
        this.branchName = builder.branchName;
        this.message = builder.message;
        this.firstParent = builder.firstParent;
        this.secondParent = builder.secondParent;
        this.blobs = builder.blobs;
        this.depth = builder.depth;
    }

    public String getName(){
        return generateSHA();
    }
    private String generateSHA(){
        return sha1(timeStamp.toString(), branchName, message);
    }


    public static class Builder{
        private LocalDateTime timeStamp;
        private String branchName;
        private String message;
        private String firstParent;
        private String secondParent;
        private Map<String,String> blobs;
        private int depth;

        public Builder(){
            timeStamp = LocalDateTime.now();
            message = "initial commit.";
            branchName = "master";
            firstParent = null;
            secondParent = null;
            depth = 0; // root of the tree
        }
    }
}
