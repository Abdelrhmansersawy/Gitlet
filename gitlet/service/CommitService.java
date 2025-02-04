package gitlet.service;

import gitlet.entity.Blob;
import gitlet.entity.Commit;

import java.io.File;

import static gitlet.Utils.readObject;
import static gitlet.Utils.writeObject;

public class CommitService {
    public static Commit read(String SHA){
        File file = new File(Commit.DIR, SHA);
        return readObject(file, Commit.class);
    }
    public static void save(Commit commit){
        File file = new File(Blob.DIR, commit.getName());
        writeObject(file, commit);
    }
}
