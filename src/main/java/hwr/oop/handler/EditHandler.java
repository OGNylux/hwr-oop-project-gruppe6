package hwr.oop.handler;

import hwr.oop.*;
import hwr.oop.dialog.EditDialog;
import hwr.oop.dialog.HandleBadIndexDialog;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EditHandler {
    public EditHandler() {
        // Empty Until this is refactored without static methods
    }
    public static void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) throws ConsoleUserInterface.CouldNotReadInputException {
        try {
            if (args.length >= 3 && args[1].equals("edit") || args[1].equals("e")) {
                editTask(toDoList, cui, args);
            } else {
                cui.print(LogMode.NONE, "Invalid Command.");
            }
        } catch (IndexOutOfBoundsException e) {
            cui.print(LogMode.ERROR, "Please provide a valid Command.");
        }
    }
    public static void editTask(ToDoList toDoList, ConsoleUserInterface cui, String[] args) throws ConsoleUserInterface.CouldNotReadInputException {
        Map<ToDoItem, Number> itemAndIndex = getToDoItem(toDoList, cui, args[2]);
        if (itemAndIndex.isEmpty()) {
            return;
        }
        ToDoItem item = (ToDoItem) itemAndIndex.keySet().toArray()[0];
        int index = (int) itemAndIndex.values().toArray()[0];
        if (item == null) return;
        EditDialog editDialog = new EditDialog(cui, toDoList);
        ToDoItem copyToDoItem = editDialog.start(item, index);
        item.setTitle(copyToDoItem.getTitle());
        item.setDescription(copyToDoItem.getDescription());
        item.setPriority(copyToDoItem.getPriority());
        item.setBucket(copyToDoItem.getBucket());
        item.setDueDate(copyToDoItem.getDueDate());
        editDialog.end();
    }
    public static Map<ToDoItem, Number> getToDoItem(ToDoList toDoList, ConsoleUserInterface cui, String arg) {
        int index = Integer.parseInt(arg);
        Map<ToDoItem, Number> itemAndIndexMap = new HashMap<>();
        int toDoListLength = toDoList.getItems().size();
        while(index >= toDoListLength || index < 0) {
            index = new HandleBadIndexDialog(cui).start("Please enter the index of the task you want to edit.");
            if (index == -1) return Collections.emptyMap();
        }
        itemAndIndexMap.put(toDoList.getItems().get(index), index);
        return itemAndIndexMap;
    }
}
