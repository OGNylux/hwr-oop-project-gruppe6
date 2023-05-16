package hwr.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class List {
    private String name;
    private java.util.ArrayList<ToDoItem> listToDos;
    private String fileName;
    private java.util.ArrayList<Bucket> Buckets;

    public List(String name) {
        this(name, null);
    }
    public List(String name, String fileName) {
        this.name = name;
        this.listToDos = new ArrayList<>();
        this.fileName = fileName;
        this.Buckets = new ArrayList<>();
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

    public java.util.ArrayList<ToDoItem> getListToDos() {
        return this.listToDos;
    }

    public java.util.ArrayList<Bucket> getBuckets() {
        return Buckets;
    }

    public void addBucket(String newBucket) {
        this.Buckets.add(new Bucket(newBucket));
    }

    public void setListToDos(java.util.ArrayList<ToDoItem> listToDos) {
        this.listToDos = listToDos;
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

    /*public void updateBuckets(){
        java.util.ArrayList<Bucket> copyBucket = new ArrayList<>();
        for (int i = 0; i < ListToDos.length; i++) {

            String element = ListToDos[i].getBucket();
            int help = 0;
            for (int j = 0; j < ListToDos.length; j++) {
                if(Buckets.get(j).getBucket() == element) {
                    help++;
                    break;
                }
            }

            if(help == 0) {
                copyBucket.add(new Bucket(element));
            }

        }

        Buckets = copyBucket;
    }*/


    public void editBucket (int index, String newBucket) {
        this.Buckets.set(index, new Bucket(newBucket));
    }

    public void add(ToDoItem toDoItem) {
        this.listToDos.add(toDoItem);
    }

    public void remove(int index) {
        this.listToDos.remove(index);
    }

    public void sortByPriority(String order) {
        if (order.equals("asc")) listToDos.sort(Comparator.comparingInt(o -> o.getPriority().toInt()));
        else if (order.equals("desc")) listToDos.sort(Comparator.comparing(ToDoItem::getPriority, Comparator.reverseOrder()));
    }

    public void bubbleUpBucket(String bucket) {
        for (int i = this.listToDos.size()-1; i >= 0; i--) {
            for (int j = this.listToDos.size()-1; j > 0; j--) {
                if (this.listToDos.get(j).getBucket().contains(bucket) && !this.listToDos.get(j - 1).getBucket().contains(bucket)) {
                    ToDoItem temp = this.listToDos.get(j);
                    this.listToDos.set(j, this.listToDos.get(j - 1));
                    this.listToDos.set(j - 1, temp);
                }
            }
        }
    }

    public void sortByCreatedAt(String order) {
        if (order.equals("asc")) listToDos.sort(Comparator.comparing(ToDoItem::getCreatedAt));
        else if (order.equals("desc")) listToDos.sort(Comparator.comparing(ToDoItem::getCreatedAt, Comparator.reverseOrder()));
    }
}
