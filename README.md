# 

# File Hierarchy

```
gitlet/
├── HEAD # Points to the current branch reference (e.g., master)
├── index # Staging area, stores the state of the working directory (indexed files for the next commit)
├── objects/ # Stores Git objects (commits, trees, blobs, etc.)
│ ├── 01/ # Directory containing object files (hash-based storage)
│ ├── 02/
│ └── ... # Additional directories for other objects, based on their hash values
├── refs/ # References to commits in the repository
│ ├── heads/ # Branch references (e.g., master, feature-branch)
```


# TODO (Refactoring)  
I am currently refactoring this project by utilizing design patterns, design principles, and Java Streams.  
- [ ] Refactor initialization features.  
- [ ] Refactor the item addition feature.  
- [ ] Refactor the item removal feature.  
- [ ] Refactor commit functionality.  
- [ ] Refactor global log functionality.  
- [ ] Refactor log functionality.  
- [ ] Refactor find functionality.  
- [ ] Refactor status functionality.  
- [ ] Refactor checkout functionality.  
- [ ] Refactor branch management.  
- [ ] Refactor branch removal functionality.  
- [ ] Refactor reset functionality.  
- [ ] Refactor merge functionality.  
