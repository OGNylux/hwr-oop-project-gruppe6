package hwr.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Objects;

public class ToDoList {
    private String name;
    private List<ToDoItem> listToDos;
    private String fileName;
    private List<Bucket> buckets;

    public ToDoList(String name) {
        this(name, null);
    }

    public ToDoList(String name, String fileName) {
        this.name = name;
        this.listToDos = new ArrayList<>();
        this.fileName = fileName;
        this.buckets = new ArrayList<>();
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

    public List<ToDoItem> getListToDos() {
        return this.listToDos;
    }

    public List<Bucket> getBuckets() {
        return this.buckets;
    }

    public void addBucket(String newBucket) {
        List<Bucket> bucketsCopy = this.buckets;
        if (bucketsCopy == null){
            this.buckets.add(new Bucket(newBucket));
        } else {
            int help = 0;
            for (int i = 0; i < bucketsCopy.size(); i++) {
                if(Objects.equals(bucketsCopy.get(i).getBucket(), newBucket)){
                    help++;
                    break;
                }
            }
            if (help == 0) {
                this.buckets.add((new Bucket(newBucket)));
            }
        }
    }

    public void setListToDos(List<ToDoItem> listToDos) {
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

    public void editBucket (int index, String newBucket) {
        this.buckets.set(index, new Bucket(newBucket));
    }

    public void add(ToDoItem toDoItem) {
        listToDos.add(toDoItem);
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

    public void sortByTitle(String order) {
        if (order.equals("asc")) listToDos.sort(Comparator.comparing(ToDoItem::getTitle));
        else if (order.equals("desc")) listToDos.sort(Comparator.comparing(ToDoItem::getTitle, Comparator.reverseOrder()));
    }

    public void sortByDone(String order) {
        if (order.equals("asc")) listToDos.sort(Comparator.comparing(ToDoItem::isDone, Comparator.reverseOrder()));
        else if (order.equals("desc")) listToDos.sort(Comparator.comparing(ToDoItem::isDone));
    }
}
