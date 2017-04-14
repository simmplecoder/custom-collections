package filesystem;

public class Document extends FolderOrDocument {

    protected Document(String name, Folder parent) {
        super(name, parent);
    }

    @Override
    public boolean isFolder() {
        return false;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
