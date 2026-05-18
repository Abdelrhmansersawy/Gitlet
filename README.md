# Gitlet - A Java-Based Version Control System

## Requirements
- Java 17 or higher
- A terminal or command prompt to execute commands
- Sufficient permissions to create and modify files in the project directory

## Comming Idea
- [ ] Add Git GUI inspired by GitButler

## How to Run
1. **Compile the Java files:**
   ```sh
   javac gitlet/Main.java
   ```
2. **Execute Gitlet commands:**
   ```sh
   java gitlet.Main [command] [arguments]
   ```
   Example:  
   ```sh
   java gitlet.Main init
   ```

---

## Description
Gitlet is a lightweight version control system inspired by [Git](https://git-scm.com/) and implemented in Java.

## Internal Structure
Gitlet simplifies Git's internal structure by using:
- **Blobs**: Saved contents of files.
- **Trees**: Mappings of file names to blobs and directories.
- **Commits**: Contain log messages, timestamps, references to blobs, and parent commits.

Gitlet differs from Git by:
- Using a **flat directory structure** (no subdirectories).
- Supporting only **two-parent merges**.
- Storing only **timestamps and log messages** in metadata.

---

## Commands
### **1. `init`**
- **Usage**:  
  ```sh
  java gitlet.Main init
  ```
- **Description**:  
  Initializes a new Gitlet repository in the current directory.

### **2. `add`**
- **Usage**:  
  ```sh
  java gitlet.Main add [file name]
  ```
- **Description**:  
  Stages the file for addition.

### **3. `commit`**
- **Usage**:  
  ```sh
  java gitlet.Main commit [message]
  ```
- **Description**:  
  Saves a snapshot of the current state.

### **4. `rm`**
- **Usage**:  
  ```sh
  java gitlet.Main rm [file name]
  ```
- **Description**:  
  Removes a file from staging or tracking.

### **5. `log`**
- **Usage**:  
  ```sh
  java gitlet.Main log
  ```
- **Description**:  
  Displays commit history.

### **6. `global-log`**
- **Usage**:  
  ```sh
  java gitlet.Main global-log
  ```
- **Description**:  
  Displays all commits.

### **7. `find`**
- **Usage**:  
  ```sh
  java gitlet.Main find [commit message]
  ```
- **Description**:  
  Finds commits with the given message.

### **8. `status`**
- **Usage**:  
  ```sh
  java gitlet.Main status
  ```
- **Description**:  
  Displays branches, staged files, and untracked files.

### **9. `checkout`**
- **Usages**:
  ```sh
  java gitlet.Main checkout -- [file name]
  java gitlet.Main checkout [commit id] -- [file name]
  java gitlet.Main checkout [branch name]
  ```
- **Description**:  
  Restores files from a commit or branch.

### **10. `branch`**
- **Usage**:  
  ```sh
  java gitlet.Main branch [branch name]
  ```
- **Description**:  
  Creates a new branch.

### **11. `rm-branch`**
- **Usage**:  
  ```sh
  java gitlet.Main rm-branch [branch name]
  ```
- **Description**:  
  Deletes a branch.

### **12. `reset`**
- **Usage**:  
  ```sh
  java gitlet.Main reset [commit id]
  ```
- **Description**:  
  Moves the current branch to a specified commit.

### **13. `merge`**
- **Usage**:  
  ```sh
  java gitlet.Main merge [branch name]
  ```
- **Description**:  
  Merges another branch into the current branch.

