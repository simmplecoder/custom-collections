package filesystem;

import custom.collections.LinkedListStack;
import custom.collections.Queue;
import custom.collections.SortedQueue;
import custom.collections.Stack;
import custom.utilities.ANSIIColors;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSystem implements FileSystemInterface {
    private Folder root;
    private Folder current;
    private Stack<Command> commandStack;

    boolean keepRunning;

    public FileSystem(String name) {
        root = new Folder(name, null);
        current = root;
        commandStack = new LinkedListStack<>();
        keepRunning = true;
    }

    @Override
    public void runUI()
    {
        System.out.println("welcome to filesystem v 0.1. Please have a look at the readme.md" +
                "before using the program");

        Scanner scanner = new Scanner(System.in);

        String computerName = "computer";
        System.out.println("Computer name is inferred as \"computer\". Enter username:");

        String name = scanner.next();

        while (name.equals(""))
        {
            scanner.nextLine(); //get rid of that fracking newline
            System.out.println("Incorrect username, try again");
        }
        scanner.nextLine(); //get rid of that fracking newline

//        Pattern pattern = Pattern.compile("(exit)|(ls)|((cd)|(touch)|(mkdir))\\s+(\\S+)");
        Pattern pattern = Pattern.compile("(exit)|(ls)|(\\S+)\\s+(\\S+)");

        Matcher matcher = pattern.matcher("");

        while (true)
        {
            System.out.print(ANSIIColors.green + name + "@" + computerName + ":");
            System.out.print(ANSIIColors.blue + current.getFullPath() + ANSIIColors.reset + "$ ");
            //keepRunning = false;
            String s = scanner.nextLine();
            matcher.reset(s);
            boolean matches = matcher.matches();
            if (matches) {
                if (matcher.group(1) != null)
                {
                    break;
                }

                if (matcher.group(2) != null)
                {
                    listContents();
                }
                else
                {
                    String command = matcher.group(3);
                    int lastGroup = matcher.groupCount();
                    switch (command)
                    {
                        case "cd":
                            String dest = matcher.group(lastGroup);
                            if (dest.equals(".."))
                            {
                                goUp();
                            }
                            else if (!dest.equals("."))
                            {
                                goIntoFolder(matcher.group(lastGroup));
                            }
                            break;
                        case "touch":
                            makeDocument(matcher.group(lastGroup));
                            break;
                        case "mkdir":
                            makeFolder(matcher.group(lastGroup));
                            break;
                        case "rmfile":
                            removeDocument(matcher.group(lastGroup));
                            break;
                        case "rmdir":
                            removeFolder(matcher.group(lastGroup));
                            break;
                        default:
                            System.out.println("Unknown command");
                    }

                    System.out.println();
                }
            }
            else
            {
                System.out.println("Unknown command");
            }
        }

        System.out.println("Exiting...");
    }

    @Override
    public void doCommand(Command cmd) {
        commandStack.push(cmd);
        switch (cmd.getCommandCode()) {
            case Command.MAKE_FOLDER:
                makeFolder(cmd.getName());
                break;
            case Command.MAKE_DOCUMENT:
                makeDocument(cmd.getName());
                break;
            case Command.REMOVE_DOCUMENT:
                removeDocument(cmd.getName());
                break;
            case Command.REMOVE_EMPTY_FODLER:
                removeFolder(cmd.getName());
                break;
            case Command.GO_INTO_FOLDER:
                goIntoFolder(cmd.getName());
                break;
            default:
                cmd.attachName(current.getName());
                goUp();
                break;
        }
    }

    @Override
    public void undoLastCommand() {
        if (commandStack.size() == 0)
        {
            System.out.println("No command to undo");
            return;
        }

        Command cmd = commandStack.pop();
        switch (cmd.getCommandCode()) {
            case Command.MAKE_FOLDER:
                removeFolder(cmd.getName());
                break;
            case Command.MAKE_DOCUMENT:
                removeDocument(cmd.getName());
                break;
            case Command.REMOVE_DOCUMENT:
                makeDocument(cmd.getName());
                break;
            case Command.REMOVE_EMPTY_FODLER:
                makeFolder(cmd.getName());
                break;
            case Command.GO_INTO_FOLDER:
                goUp();
                break;
            default:
                goIntoFolder(cmd.getName());
                break;
        }
    }

    @Override
    public void listContents() {
        SortedQueue<String> folderContents = current.getContentNames();
        String list = folderContents.toString();
        list = list.substring(1, list.length() - 1);
        System.out.println(list);
    }

    @Override
    public Queue<String> getAllPaths() {
        return root.getAllPaths();
    }

    private void makeFolder(String name) {
        if (!current.addEntry(new Folder(name, current)))
        {
            System.out.println("Error. Couldn't add folder " + name);
        }
    }

    private void makeDocument(String name) {
        if (!current.addEntry(new Document(name, current)))
        {
            System.out.println("Error. Couldn't add document " + name);
        }
    }

    private void removeDocument(String name) {
        if (current.isFolder(name))
        {
            System.out.println("Error. The file corresponds to a folder");
            return;
        }

        if (current.removeEntry(name)) {
            System.out.println("Successfully removed document " + name);
        } else {
            System.out.println("Error. The document doesn't exist");
        }
    }

    private void removeFolder(String name) {
        if (current.removeEntry(name)) {
            System.out.println("Successfully removed folder " + name);
        } else {
            System.out.println("Error. The folder doesn't exist or name corresponds to file");
        }
    }

    private void goIntoFolder(String name) {
        if (current.isFolder(name)) {
            current = current.get(name);
            return;
        }

        System.out.println("Error. The folder doesn't exist");
    }

    private void goUp() {
        if (current.getParent() != null) {
            current = current.getParent();
        }
        else
        {
            System.out.println("Error. Already in the top directory");
        }
    }
}
