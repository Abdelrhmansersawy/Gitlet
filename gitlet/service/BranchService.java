package gitlet.service;

import gitlet.entity.Branch;
import java.io.File;

import static gitlet.Utils.readObject;
import static gitlet.Utils.writeObject;

public class BranchService {
    public static Branch read(String branchName){
        File file = new File(Branch.DIR, branchName);
        return readObject(file, Branch.class);
    }
    public static void save(Branch branch){
        File file = new File(Branch.DIR, branch.getName());
        writeObject(file, branch);
    }
}
