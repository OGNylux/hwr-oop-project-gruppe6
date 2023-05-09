package hwr.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
public class ToDoList {
    private String name;
    private ToDoItem[] items;
    private String fileName;

    public ToDoList(String name) {
        this(name, null);
    }
    public ToDoList(String name, String fileName) {
        this.name = name;
        this.items = new ToDoItem[0];
        this.fileName = fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public ToDoItem[] getItems() {
        return this.items;
    }

    public void setItems(ToDoItem[] items) {
        this.items = items;
    }
    public String getFileName() {
        return this.fileName;
    }
    public void writeToJSON(String fileName) {
        //remove any file extension if present
        if (fileName.contains(".")) {
            fileName = fileName.substring(0, fileName.lastIndexOf('.'));
        }
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(this);

        try (FileWriter fileWriter = new FileWriter(fileName + ".json")) {
            fileWriter.write(json);
        } catch (FileNotFoundException e) {
            File file = new File(fileName + ".json");
            try {
                boolean fileExists = file.createNewFile();
                if (!fileExists) this.writeToJSON(fileName);
                else System.out.println("Sorry...File could not be neither found nor created.");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(ToDoItem toDoItem) {
        ToDoItem[] newList = new ToDoItem[this.items.length + 1];
        System.arraycopy(this.items, 0, newList, 0, this.items.length);
        newList[newList.length - 1] = toDoItem;
        this.items = newList;
    }

    public void remove(int index) {
        ToDoItem[] newList = new ToDoItem[this.items.length - 1];
        System.arraycopy(this.items, 0, newList, 0, index);
        System.arraycopy(this.items, index + 1, newList, index, this.items.length - 1);
        this.items = newList;
    }

    public void sortByPriority(String order) {
        if (order.equals("asc")) {
            for (int i = 0; i < this.items.length; i++) {
                for (int j = 0; j < this.items.length - 1; j++) {
                    if (this.items[j].getPriority().toInt() > this.items[j + 1].getPriority().toInt()) {
                        ToDoItem temp = this.items[j];
                        this.items[j] = this.items[j + 1];
                        this.items[j + 1] = temp;
                    }
                }
            }
        } else if (order.equals("desc")) {
            for (int i = 0; i < this.items.length; i++) {
                for (int j = 0; j < this.items.length - 1; j++) {
                    if (this.items[j].getPriority().toInt() < this.items[j + 1].getPriority().toInt()) {
                        ToDoItem temp = this.items[j];
                        this.items[j] = this.items[j + 1];
                        this.items[j + 1] = temp;
                    }
                }
            }
        }
    }

    public void bubbleUpTag(String tag) {
        for (int i = this.items.length-1; i >= 0; i--) {
            for (int j = this.items.length-1; j > 0; j--) {
                if (this.items[j].getTag().contains(tag) && !this.items[j - 1].getTag().contains(tag)) {
                    ToDoItem temp = this.items[j];
                    this.items[j] = this.items[j - 1];
                    this.items[j - 1] = temp;
                }
            }
        }
    }
    public void sortByCreatedAt(String order) {
        if (order.equals("asc"))
            for (int i = 0; i < this.items.length; i++) {
                for (int j = 0; j < this.items.length-1; j++) {
                    if (this.items[j].getCreatedAt().compareTo(this.items[j + 1].getCreatedAt()) > 0) {
                        ToDoItem temp = this.items[j];
                        this.items[j] = this.items[j + 1];
                        this.items[j + 1] = temp;
                    }
                }
            }
        else if (order.equals("desc")) {
            for (int i = 0; i < this.items.length; i++) {
                for (int j = 0; j < this.items.length - 1; j++) {
                    if (this.items[j].getCreatedAt().compareTo(this.items[j + 1].getCreatedAt()) < 0) {
                        ToDoItem temp = this.items[j];
                        this.items[j] = this.items[j + 1];
                        this.items[j + 1] = temp;
                    }
                }
            }
        }
    }
}
