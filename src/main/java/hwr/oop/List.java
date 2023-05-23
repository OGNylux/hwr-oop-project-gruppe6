package hwr.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class List {
    private String name;
    private ToDoItem[] listToDos;
    private String fileName;
    private java.util.List<Bucket> Buckets;

    public List(String name) {
        this(name, null);
    }
    public List(String name, String fileName) {
        this.name = name;
        this.listToDos = new ToDoItem[0];
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

    public ToDoItem[] getListToDos() {
        return this.listToDos;
    }

    public java.util.List<Bucket> getBuckets() {
        return Buckets;
    }

    public void addBucket(String newBucket) {
        this.Buckets.add(new Bucket(newBucket));
    }

    public void setListToDos(ToDoItem[] listToDos) {
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
        if (this.listToDos == null) {
            this.listToDos = new ToDoItem[1];
            this.listToDos[0] = toDoItem;
            return;
        } else {
            ToDoItem[] newList = new ToDoItem[this.listToDos.length + 1];
            System.arraycopy(this.listToDos, 0, newList, 0, this.listToDos.length);
            newList[newList.length - 1] = toDoItem;
            this.listToDos = newList;
        }
    }

    public void remove(int index) {
        ToDoItem[] newList = new ToDoItem[this.listToDos.length - 1];
        System.arraycopy(this.listToDos, 0, newList, 0, index);
        System.arraycopy(this.listToDos, index + 1, newList, index, this.listToDos.length - 1);
        this.listToDos = newList;
    }

    public void sortByPriority(String order) {
        if (order.equals("asc")) {
            for (int i = 0; i < this.listToDos.length; i++) {
                for (int j = 0; j < this.listToDos.length - 1; j++) {
                    if (this.listToDos[j].getPriority().toInt() > this.listToDos[j + 1].getPriority().toInt()) {
                        ToDoItem temp = this.listToDos[j];
                        this.listToDos[j] = this.listToDos[j + 1];
                        this.listToDos[j + 1] = temp;
                    }
                }
            }
        } else if (order.equals("desc")) {
            for (int i = 0; i < this.listToDos.length; i++) {
                for (int j = 0; j < this.listToDos.length - 1; j++) {
                    if (this.listToDos[j].getPriority().toInt() < this.listToDos[j + 1].getPriority().toInt()) {
                        ToDoItem temp = this.listToDos[j];
                        this.listToDos[j] = this.listToDos[j + 1];
                        this.listToDos[j + 1] = temp;
                    }
                }
            }
        }
    }

    public void bubbleUpBucket(String bucket) {
        for (int i = this.listToDos.length-1; i >= 0; i--) {
            for (int j = this.listToDos.length-1; j > 0; j--) {
                if (this.listToDos[j].getBucket().contains(bucket) && !this.listToDos[j - 1].getBucket().contains(bucket)) {
                    ToDoItem temp = this.listToDos[j];
                    this.listToDos[j] = this.listToDos[j - 1];
                    this.listToDos[j - 1] = temp;
                }
            }
        }
    }
    public void sortByCreatedAt(String order) {
        if (order.equals("asc"))
            for (int i = 0; i < this.listToDos.length; i++) {
                for (int j = 0; j < this.listToDos.length-1; j++) {
                    if (this.listToDos[j].getCreatedAt().compareTo(this.listToDos[j + 1].getCreatedAt()) > 0) {
                        ToDoItem temp = this.listToDos[j];
                        this.listToDos[j] = this.listToDos[j + 1];
                        this.listToDos[j + 1] = temp;
                    }
                }
            }
        else if (order.equals("desc")) {
            for (int i = 0; i < this.listToDos.length; i++) {
                for (int j = 0; j < this.listToDos.length - 1; j++) {
                    if (this.listToDos[j].getCreatedAt().compareTo(this.listToDos[j + 1].getCreatedAt()) < 0) {
                        ToDoItem temp = this.listToDos[j];
                        this.listToDos[j] = this.listToDos[j + 1];
                        this.listToDos[j + 1] = temp;
                    }
                }
            }
        }
    }
}
