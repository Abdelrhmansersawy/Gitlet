package gitlet;

import gitlet.entity.Repository;
import gitlet.entity.StagingArea;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */

    public static void main(String[] args) {
        Repository repo = Repository.getInstance();
        StagingArea stagingArea = StagingArea.getInstance();


        // TODO: what if args is empty?
        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                repo.init();
            case "add":
//                repo.add(args[1]);
                break;
            case "rm":
                break;
            case "commit":
                // TODO: FILL THE REST IN
                break;
            case "global-log":
                break;
            case "log":
                break;
            case "find":
                break;
            case "status":
                break;
            case "checkout":
                if(args.length == 3){
//                    repo.checkoutFile(args[2]); // java gitlet.Main checkout -- [file name]
                }else if(args.length == 4){
//                    repo.checkoutFile(args[3] , args[1]); // java gitlet.Main checkout [commit id] -- [file name]
                }else if(args.length == 2){
//                    repo.checkoutBranch(args[1]); //  java gitlet.Main checkout [branch name]
                }
                break;
            case "branch":
//                repo.createNewBranch(args[1]);
                break;
            case "rm-branch":
//                repo.removeBranch(args[1]);
                break;
            case "reset":
//                repo.reset(args[1]);
                break;
            case "merge":
//                repo.merge(args[1]);
                break;
        }
    }
}
