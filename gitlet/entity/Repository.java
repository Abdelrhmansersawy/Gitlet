package gitlet.entity;

import gitlet.config.FileConfig;
import gitlet.service.GitInitService;

import java.io.Serializable;

import static gitlet.Utils.readObject;
import static gitlet.Utils.writeObject;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Repository implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */
    private static Repository instance;
    private Commit head;

    public static void setInstance(Repository repository) {
        instance = repository;
    }

    public static Repository getInstance() {
        if (instance == null) {
            if(GitInitService.isAlreadyInitialized()){
                instance = read();
            }else{
                instance = new Repository();
            }
        }
        return instance;
    }

    public static Repository read(){
        return readObject(FileConfig.HEAD, Repository.class);
    }

    public void save(){
        writeObject(FileConfig.HEAD, Repository.getInstance());
    }

    public void init(){
        if(GitInitService.isAlreadyInitialized()){
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            return;
        }
        GitInitService.initializeRepository();
    }

    public void setHead(Commit head) {
        this.head = head;
    }
}
