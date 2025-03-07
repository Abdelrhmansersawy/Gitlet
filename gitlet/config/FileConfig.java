package gitlet.config;


import gitlet.Utils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileConfig {
    public static final String CWD = System.getProperty("user.dir");
    public static final File GITLET_DIR = Utils.join(CWD, ".gitlet");
    public static final File OBJECTS_DIR = Utils.join(GITLET_DIR, "objects");
    public static final File REFS_DIR = Utils.join(GITLET_DIR, "refs");
    public static final File BranchRefs = Utils.join(REFS_DIR, "heads");
    public static final File HEAD = new File(GITLET_DIR, "HEAD");
    public static final File INDEX = new File(GITLET_DIR, "index");

    public static List<File> listRootDirectories(){
        return Arrays.asList(GITLET_DIR, OBJECTS_DIR, REFS_DIR, BranchRefs);
    }
}
