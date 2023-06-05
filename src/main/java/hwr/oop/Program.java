package hwr.oop;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

public class Program {
    private String DELIMITER = ",";
    public ToDoList loadToDoList(String fileName) {
        ToDoList toDoList = getToDoListFromJSON(fileName);
        if (toDoList != null && toDoList.getItems() == null) {
            toDoList.setItems(new ArrayList<>());
            return toDoList;
        }
        return toDoList;
    }


    private ToDoList getToDoListFromJSON(String fileName) {
        Gson gson = new Gson();
        String json;
        if (fileName.contains(".")) {
            fileName = fileName.substring(0, fileName.lastIndexOf('.'));
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + ".json"))){
            json = br.readLine();
        } catch (IOException e) {
            return null;
        }
        return gson.fromJson(json, ToDoList.class);
    }
    public String[] getEnvironmentVariables(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file + ".csv"))){
            String line;
            if ((line = br.readLine()) != null) {
                return line.split(DELIMITER);
            }
            return new String[0];
        } catch (FileNotFoundException e) {
            return new String[0];
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public void setEnvironmentVariables(String filePath, String listName, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName + ".csv")) {
            fileWriter.write(filePath + "," + listName);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}