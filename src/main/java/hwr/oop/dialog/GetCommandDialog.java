package hwr.oop.dialog;

import hwr.oop.consoleuserinterface.ConsoleUserInterface;
import hwr.oop.consoleuserinterface.LogMode;
import hwr.oop.ToDoList;
import hwr.oop.handler.CommandParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static hwr.oop.util.ConsoleColors.BLUE_BOLD;
import static hwr.oop.util.ConsoleColors.RESET;

public class GetCommandDialog {
    private final CommandParser commandParser;
    private final BufferedReader reader;
    private final ConsoleUserInterface cui;
    private final ToDoList toDoList;

    public GetCommandDialog(ToDoList toDoList, ConsoleUserInterface cui, CommandParser commandParser) {
        this.toDoList = toDoList;
        this.cui = cui;
        this.reader = new BufferedReader(new InputStreamReader(cui.getInputStream()));
        this.commandParser = commandParser;
    }
    public int start() throws CouldNotReadCommandException, CommandParser.CouldNotCallHandlerException {
        this.cui.print(LogMode.NONE,BLUE_BOLD + "Please enter a command or type 'gtd help' for more information" + RESET);
        this.cui.getOutputStream().print("> ");
        return getCommand(this.toDoList);
    }
    public int getCommand(ToDoList toDoList) throws CommandParser.CouldNotCallHandlerException, CouldNotReadCommandException {
        String command = readCommand();
        String[] commandArray = command.split(" ");
        return callHandler(toDoList, commandArray);
    }
    public String readCommand() throws CouldNotReadCommandException {
        try {
            return this.reader.readLine();
        } catch (IOException e) {
            throw new CouldNotReadCommandException("Could not read command");
        }
    }
    public int callHandler(ToDoList toDoList, String[] commandArray) throws CommandParser.CouldNotCallHandlerException {
        try {
            return this.commandParser.handle(toDoList, commandArray);
        } catch (CommandParser.CouldNotCallHandlerException e) {
            throw new CommandParser.CouldNotCallHandlerException();
        }
    }
    public static class CouldNotReadCommandException extends Exception {
        public CouldNotReadCommandException(String message) {
            super(message);
        }
    }
}