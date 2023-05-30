package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.LogMode;
import hwr.oop.ToDoList;

public interface StateHandler {
    static void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        if (args.length >= 2) {
            try {
                if (args[1].equals("done") || args[1].equals("do")) {
                    done(toDoList, cui, args);
                } else if (args[1].equals("promote") || args[1].equals("p")) {
                    promote(toDoList, cui, args);
                } else if (args[1].equals("demote") || args[1].equals("d")) {
                    demote(toDoList, cui, args);
                } else if (args[1].equalsIgnoreCase("hold") || args[1].equals("h")) {
                    hold(toDoList, cui, args);
                } else {
                    cui.print(LogMode.ERROR, "Unknown command");
                }
            } catch (Exception e) {
                cui.print(LogMode.WARN, "Type gtd help to get help on commands.");
            }
        } else {
            cui.print(LogMode.ERROR, "Invalid number of arguments");
        }
    }

    static void done(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        int i = 0;
        int index = Integer.parseInt(args[2]);
        while (i == 0) {
            try {
                toDoList.getItems()[index].setDone();
                i++;
            } catch (Exception e) {
                index = cui.handleBadIndex("Please enter the index of the task you want to mark as done.");
                if (index == -1) return;
            }
        }
    }
    static void hold(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        int i = 0;
        int index = Integer.parseInt(args[2]);
        while (i == 0) {
            try {
                toDoList.getItems()[index].hold();
                i++;
            } catch (Exception e) {
                index = cui.handleBadIndex("Please enter the index of the task you want to mark as done.");
                if (index == -1) return;
            }
        }
    }
    static void promote(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        int i = 0;
        int index = Integer.parseInt(args[2]);
        while (i == 0) {
            try {
                toDoList.getItems()[index].promote();
                i++;
            } catch (Exception e) {
                index = cui.handleBadIndex("Please enter the index of the task you want to mark as done.");
                if (index == -1) return;
            }
        }
    }
    static void demote(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        int i = 0;
        int index = Integer.parseInt(args[2]);
        while (i == 0) {
            try {
                toDoList.getItems()[index].demote();
                i++;
            } catch (Exception e) {
                index = cui.handleBadIndex("Please enter the index of the task you want to mark as done.");
                if (index == -1) return;
            }
        }
    }

}
