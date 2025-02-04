package gitlet.service;

import gitlet.entity.Blob;

import java.io.File;

import static gitlet.Utils.readObject;
import static gitlet.Utils.writeObject;

public class BlobService {
    public static Blob read(String SHA){
        File file = new File(Blob.DIR, SHA);
        return readObject(file, Blob.class);
    }
    public static void save(Blob blob){
        File file = new File(Blob.DIR, blob.getName());
        writeObject(file, blob);
    }
}
