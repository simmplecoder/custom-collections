package filesystem;

import custom.collections.*;

public class Folder extends FolderOrDocument {

    //replaced with LinkedList, because it makes so much more sense
    private LinkedList<FolderOrDocument> contents;
    private String fullPath;

    public Folder(String name, Folder parent) {
        super(name, parent);
        contents = new LinkedList<>();
        fullPath = name;
        if (parent != null)
        {
            fullPath = parent.fullPath + "/" + fullPath;
        }
    }

    public Queue<FolderOrDocument> getContents() {
        return new LinkedListQueue<>(contents);
    }

    public String getFullPath()
    {
        return fullPath;
    }

    /**
     * Use to check if there is an item in this folder with
     * the given name.
     *
     * @param name the name to check for
     * @return true if there is a document or folder with
     * name in this folder
     */
    public boolean containsName(String name) {

        for (FolderOrDocument node : contents) {
            if (node.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    boolean addEntry(FolderOrDocument entry) {
        if (entry == null) {
            throw new IllegalArgumentException("Entry cannot be null");
        }

        for (FolderOrDocument node : contents) {
            if (node.getName().equals(entry.getName())) {
                return false;
            }
        }

        contents.add(entry);
        return true;
    }

    boolean removeEntry(String name) {
        for (FolderOrDocument node : contents) {
            if (node.getName().equals(name)) {
                if (node.isFolder()) {
                    if (((Folder) node).isEmpty()) {
                        contents.remove(node);
                        return true;
                    }

                    return false;
                }

                contents.remove(node);
                return true;
            }
        }

        return false;
    }

    Folder get(String name) {
        for (FolderOrDocument node : contents) {
            if (node.getName().equals(name)) {
                if (!node.isFolder()) {
                    throw new IllegalStateException("Internal Error. Node is not a folder, " +
                            "but previously did report that it is a folder");
                }

                return (Folder) node;
            }
        }

        throw new IllegalStateException("Internal Error. No such folder, but previously it existed");
    }

    /**
     * Returns a listing of names (not paths) of all the items
     * in this folder.
     *
     * @return the names of the documents and folders in this
     * folder, sorted by lexicographic order
     */
    public SortedQueue<String> getContentNames() {
        SortedQueue<String> contentNames = new LinkedListSortedQueue<>();

        for (FolderOrDocument node: contents)
        {
            contentNames.insert(node.getName());
        }

        return contentNames;
    }

    @Override
    public boolean isFolder() {
        return true;
    }

    public boolean isFolder(String name)
    {
        for (FolderOrDocument node: contents)
        {
            if (name.equals(node.getName()) && node.isFolder())
            {
                return true;
            }
        }

        return false;
    }

    private boolean isEmpty() {
        return contents.size() == 0;
    }


    // Don't change.... this is used for testing

    /**
     * @return queue of pathnames of all files in the system
     * rooted at the current folder
     */
    public Queue<String> getAllPaths() {
        Queue<String> results = new LinkedListQueue<>();
        results.enqueue(this.getName());
        getAllPaths(getName(), results);
        return results;
    }

    // Don't change.... this is used for testing
    protected void getAllPaths(String prePath, Queue<String> results) {

        SortedQueue<FolderOrDocument> sorted = new LinkedListSortedQueue<>();

        for (int i = 0; i < contents.size(); i++) {
            FolderOrDocument fod;
            try {
                fod = contents.remove(0);
                contents.add(fod);

                sorted.insert(fod);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        while (sorted.size() > 0) {
            try {
                FolderOrDocument fod = sorted.dequeue();
                String fodPath = prePath + "/" + fod.getName();
                results.enqueue(fodPath);
                if (fod.isFolder()) {
                    Folder folder = (Folder) fod;
                    folder.getAllPaths(fodPath, results);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
