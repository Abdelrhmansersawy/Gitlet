package gitlet.entity;

import gitlet.config.FileConfig;

import java.io.File;
import java.io.Serializable;

public class Branch implements Serializable {
    private final String name;
    private String head;
    public static final File DIR = FileConfig.BranchRefs;

    public Branch(String name, String head) {
        this.name = name;
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public String getHead() {
        return head;
    }
}
