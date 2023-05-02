package hwr.oop;

import java.time.LocalDate;

public class ToDoItem {
    private final int id;
    private String title;
    private String description;
    private String tag;
    private boolean done;
    private Priority priority;
    private Project project;
    private LocalDate deadline;


    public ToDoItem (int id, String title, String description, String tag, boolean done, Priority priority, Project project, LocalDate deadline) {
        this.id = id;
        this.title = title;
        this.description = description + "\nCreated " + getLocalDate();
        this.tag = tag;
        this.done = done;
        this.priority = priority;
        this.project = project;
        this.deadline = deadline;
    }
    void setTitle(String title) {
        this.title = title;
    }
    void setDescription(String description) {
        this.description = description + "\nCreated " + getLocalDate();
    } // TODO: FIX THIS: EVERY TIME I EDIT THE DESCRIPTION IT ADDS A NEW DATE
    void setDone(boolean done) {
        this.done = done;
    }
    void setPriority(Priority priority) {
        this.priority = priority;
    }
    void setTag(String tag) {
        this.tag = tag;
    }
    void setProjectName(String project) {
        this.project.setTitle(project);
    }
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
    static String getLocalDate() {
        return LocalDate.now().toString();
    }
    public String getTitle() {
        return title;
    }
    public String getTag() {
        return tag;
    }
    public String getDescription() {
        return description;
    }
    public boolean isDone() {
        return done;
    }
    public Priority getPriority() {
        return priority;
    }
    public int getId() {
        return id;
    }
    public String getProjectName() {
        return project.getTitle();
    }
    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        String doneSymbol = this.done ? "✅ " : "❌ " ;
        return  doneSymbol + title + '\n' +
                description + '\n' +
                "<" + tag + ">" + ' ' +
                priority + '\n' + deadline;
    }

    public String getCreatedAt() {
        return description.substring(description.length() - 10);
    }

}
