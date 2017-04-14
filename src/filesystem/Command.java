package filesystem;


public class Command {

    /**
     * Integer constants used to represent the different kinds
     * of commands
     */
    public static final int MAKE_FOLDER = 1;
    public static final int MAKE_DOCUMENT = 2;
    public static final int REMOVE_EMPTY_FOLDER = 3;
    public static final int REMOVE_DOCUMENT = 4;
    public static final int GO_UP_ONE_FOLDER = 5;
    public static final int GO_INTO_FOLDER = 6;
    /*
     * Integer used to represent the kind of command (1-8)
     */
    private int cmdCode;
    /*
     * Name of the folder or document argument to this command;
     * will be initially null if the command doesn't need such a name
     */
    private String name;


    /**
     * Constructor for commands that should not take a name argument
     *
     * @param cmdCode the integer command code
     * @throws Exception if the command code is bad
     */
    public Command(int cmdCode) {
        if (cmdCode < 1 || cmdCode > 6) {
            throw new IllegalArgumentException("Illegal command code.");
        }

        if (needsName(cmdCode)) {
            throw new IllegalArgumentException("Given command code needs a name argument.");
        }

        this.cmdCode = cmdCode;
        this.name = null;
    }

    /**
     * Constructor for commands that should take a name argument for
     * the folder or document
     *
     * @param cmdCode the integer command code
     * @param name    argument for the command
     * @throws Exception if the command code is bad
     */
    public Command(int cmdCode, String name) {

        if (cmdCode < 1 || cmdCode > 6) {
            throw new IllegalArgumentException("Illegal command code.");
        }

        if (name != null && !needsName(cmdCode)) {
            throw new IllegalArgumentException("Given command code should not have a name argument.");
        }

        this.cmdCode = cmdCode;
        this.name = name;
    }

    /**
     * @return the integer command code
     */
    public int getCommandCode() {
        return cmdCode;
    }

    /**
     * @return the string argument for the command
     */
    public String getName() {
        return name;
    }

    /**
     * @param name to set to the string argument for the command
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * Checks if the cmdCode represents a command that needs a name
     * argument
     */
    private boolean needsName(int cmdCode) {
        return (cmdCode == Command.MAKE_FOLDER) ||
                (cmdCode == Command.MAKE_DOCUMENT) ||
                (cmdCode == Command.REMOVE_EMPTY_FOLDER) ||
                (cmdCode == Command.REMOVE_DOCUMENT) ||
                (cmdCode == Command.GO_INTO_FOLDER);
    }

    void attachName(String name)
    {
        if (cmdCode != Command.GO_UP_ONE_FOLDER)
        {
            throw new IllegalStateException("Trying to attach string to wrong command");
        }
        this.name = name;
    }

    /**
     * @return the string representation of the command
     */
    public String toString() {
        return "Code:" + cmdCode + " NameArg:" + name;
    }
}
