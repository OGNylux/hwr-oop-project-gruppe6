package hwr.oop;

import java.io.IOException;

import static hwr.oop.ConsoleColors.BLUE_BOLD;
import static hwr.oop.ConsoleColors.RESET;

public class Main {
    public static void initiateSort(ConsoleUserInterface cui, ToDoList list, String[] commandArray) {
        int nCommands = commandArray.length;
        if (nCommands == 2) {
            cui.sortHelp();
        } else if (nCommands == 3) {
            if (commandArray[2].equals("help")) {
                cui.sortHelp();
            }
        } else if (nCommands == 4) {
            assignSortingAlgorithm(cui, list, commandArray);
        }
    }

    public static void assignSortingAlgorithm(ConsoleUserInterface cui, ToDoList list, String[] commandArray) {
        if (commandArray[2].toLowerCase().contains("prio")) {
            if (commandArray[3].equals("asc")) {
                list.sortByPriority("asc");
            } else {
                list.sortByPriority("desc");
            }
        } else if (commandArray[2].toLowerCase().contains("create")) {
            if (commandArray[3].equals("asc")) {
                list.sortByCreatedAt("asc");
            } else {
                list.sortByCreatedAt("desc");
            }
        } else if (commandArray[2].toLowerCase().contains("tag")) {
            list.bubbleUpTag(commandArray[3]);
        } else {
            cui.sortHelp();
        }
    }
    public static void clear(ToDoList list) {
        list.setItems(null);
    }
    public void exit(ConsoleUserInterface cui, ToDoList list) {
        cui.say("exiting...");
        list.writeToJSON(list.getFileName());
        System.exit(0);
    }
    public static void main(String[] args) throws IOException, ConsoleUserInterface.CouldNotReadInputException {
        Main main = new Main();
        ConsoleUserInterface cui = new ConsoleUserInterface(System.out, System.in);
        ToDoList toDoList = cui.welcome();
        cui.say(BLUE_BOLD + "Please enter a command or type 'gtd help' for more information" + RESET);
        while (true) {
            cui.parseCommands(main, toDoList);
        }
    }
}

