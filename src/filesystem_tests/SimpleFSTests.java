package filesystem_tests;

import custom.utilities.ANSIIColors;
import filesystem.Command;
import filesystem.FileSystem;
import filesystem.FileSystemInterface;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleFSTests {
    FileSystemInterface filesystem;
    boolean keepRunning;

    public SimpleFSTests()
    {
        filesystem = new FileSystem("/");
    }


    public static void main(String[] args) {
        FileSystemInterface filesystem = new FileSystem("/");
        filesystem.doCommand(new Command(Command.MAKE_DOCUMENT, "first.txt"));
        filesystem.doCommand(new Command(Command.MAKE_DOCUMENT, "second.txt"));
        filesystem.doCommand(new Command(Command.MAKE_DOCUMENT, "first.txt"));
        filesystem.doCommand(new Command(Command.MAKE_DOCUMENT, "third.txt"));
        filesystem.doCommand(new Command(Command.MAKE_FOLDER, "first"));

        filesystem.listContents();

        filesystem.doCommand(new Command(Command.GO_INTO_FOLDER, "first"));
        filesystem.doCommand(new Command(Command.MAKE_DOCUMENT, "firstfirst.txt"));
        filesystem.doCommand(new Command(Command.MAKE_DOCUMENT, "firstsecond.txt"));
        filesystem.doCommand(new Command(Command.MAKE_DOCUMENT, "firstfirst.txt"));
        filesystem.doCommand(new Command(Command.MAKE_DOCUMENT, "firstthird.txt"));
        filesystem.listContents();

        filesystem = new FileSystem("~");

        filesystem.runUI();
    }
}
