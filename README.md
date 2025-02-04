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
