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
    private List<ToDoItem> items;
    private String fileName;
    private List<Bucket> buckets;

    public ToDoList(String name) {
        this(name, null);
    }

    public ToDoList(String name, String fileName) {
        this.name = name;
        this.items = new ArrayList<>();
        this.fileName = fileName;
        this.buckets = new ArrayList<>();
    }

    //setter
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setListToDos(List<ToDoItem> listToDos) {
        this.items = listToDos;
    } //TODO: needs test

    public String getFileName() {
        return this.fileName;
    }

    public String getName() {
        return this.name;
    }

    public List<ToDoItem> getItems() {
        return this.items;
    }

    public List<Bucket> getBuckets() {
        return this.buckets;
    }


    public void add(ToDoItem toDoItem) {
        items.add(toDoItem);
    }

    public void remove(int index) {
        this.items.remove(index);
    }

    public void addBucket(String newBucket) {
        List<Bucket> bucketsCopy = this.buckets;
        boolean containsBucket = bucketsCopy.stream().anyMatch(o -> newBucket.equals(o.getBucket()));

        if (!containsBucket || bucketsCopy.isEmpty()){
            this.buckets.add(new Bucket(newBucket));
        }
    }

    public void editBucket (int index, String newBucket) {
        this.buckets.set(index, new Bucket(newBucket));
    }

    public void sortByPriority(String order) {
        if (order.equals("asc")) items.sort(Comparator.comparingInt(o -> o.getPriority().toInt()));
        else if (order.equals("desc")) items.sort(Comparator.comparing(ToDoItem::getPriority, Comparator.reverseOrder()));
    }

    public void bubbleUpBucket(String bucket) {
        items.sort((o1, o2) -> {
            if(Objects.equals(o1.getBucket(), o2.getBucket())){
                return 0;
            }
            return Objects.equals(o1.getBucket(), bucket) ? -1 : 1;
        });
    }

    public void sortByCreatedAt(String order) {
        if (order.equals("asc")) items.sort(Comparator.comparing(ToDoItem::getCreatedAt));
        else if (order.equals("desc")) items.sort(Comparator.comparing(ToDoItem::getCreatedAt, Comparator.reverseOrder()));
    }

    public void sortByTitle(String order) {
        if (order.equals("asc")) items.sort(Comparator.comparing(ToDoItem::getTitle));
        else if (order.equals("desc")) items.sort(Comparator.comparing(ToDoItem::getTitle, Comparator.reverseOrder()));
    }

    public void sortByDone(String order) {
        if (order.equals("asc")) items.sort(Comparator.comparing(ToDoItem::isDone, Comparator.reverseOrder()));
        else if (order.equals("desc")) items.sort(Comparator.comparing(ToDoItem::isDone));
    }

    public void writeToJSON(String fileName) {
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
}
