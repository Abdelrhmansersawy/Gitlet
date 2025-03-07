package gitlet.service;

import gitlet.config.FileConfig;
import gitlet.GitletException;
import gitlet.entity.Branch;
import gitlet.entity.Commit;
import gitlet.entity.Repository;
import gitlet.entity.StagingArea;

import java.io.File;
/**
* Service class for initializing a Gitlet repository.
*/
public class GitInitService {
    private static Commit rootCommit;
    public static void initializeRepository() {
        initializeDirectory();
        initializeCommit();
        initializeMainBranch();
        initializeHEAD();
        initializeIndex();

    }


    public static boolean isAlreadyInitialized() {
        return FileConfig.GITLET_DIR.exists();
    }

    // --------------------- Helper Methods ---------------------
    private static void initializeCommit(){
        rootCommit = new Commit(new Commit.Builder());
        CommitService.save(rootCommit);
    }
    private static void initializeDirectory() {
        for (File file : FileConfig.listRootDirectories()) {
            if (!createDirectory(file)) {
                throw new GitletException("Failed to create a directory with name " + file.getName());
            }
        }
    }
    private static void initializeMainBranch(){
        Branch branch = new Branch("main", rootCommit.getName());
        BranchService.save(branch);
    }
    private static void initializeHEAD(){
        Repository.getInstance().setHead(rootCommit);
        Repository.getInstance().save();
    }
    private static void initializeIndex(){
        StagingArea.getInstance().save();

    }

    private static boolean createDirectory(File directory) {
        if(directory.exists()) return true;
        return directory.mkdirs();
    }
}