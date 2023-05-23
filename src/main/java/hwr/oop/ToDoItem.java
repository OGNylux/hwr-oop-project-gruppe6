package hwr.oop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import static hwr.oop.ConsoleColors.*;

public class ToDoItem {
    private String title;
    private String description;

    private Bucket bucket;
    private Priority priority;
    private String createdAt;
    private State state;
    public ToDoItem (String title, String description, String bucket, Priority priority) {
        this.title = title;
        this.description = description;
        this.bucket = new Bucket(bucket);
        this.createdAt = LocalDateTime.now().toString();
        this.priority = priority;
        this.state = State.TODO;
    }
    void setTitle(String title) {
        this.title = title;
    }
    void setDescription(String description) {
        this.description = description;
    }
    void setDone(boolean done) {
        this.state = State.DONE;
    }
    void setPriority(Priority priority) {
        this.priority = priority;
    }
    void setBucket(String bucket) {
        this.bucket.setBucket(bucket);
    }
    static String getLocalDate() {
        return LocalDate.now().toString();
    }
    public String getTitle() {
        return title;
    }
    public String getBucket() {
        return bucket.getBucket();
    }
    public String getDescription() {
        return description;
    }

    public State getState() {
        return state;
    }
    public String getStateEmoji() {
        try {
            switch (state) {
                case DONE:
                    return "✅";
                case TODO:
                    return "⏭️";
                case IN_PROGRESS:
                    return "🏗️";
                case ON_HOLD:
                    return "🕑";
                default:
                    return "❓";
            }
        } catch (Exception e) {
            return "❓";
        }
    }
    public String getPriorityString() {
        switch (priority) {
            case LOW:
                return BLUE_BOLD + "LOW" + RESET;
            case MEDIUM:
                return YELLOW_BOLD + "MEDIUM" + RESET;
            case HIGH:
                return RED_BOLD + "HIGH" + RESET;
            default:
                return "❓";
        }
    }
    public void promote() {
        State nextState = state.nextState();
        this.state = nextState;
    }
    public void demote() {
        State previousState = state.previousState();
        this.state = state.previousState();
    }
    public void hold(){
        State hold = state.hold();
        this.state = state.hold();
    }
    public boolean isDone() {
        return state == State.DONE;
    }
    public Priority getPriority() {
        return priority;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt.toString();
    }
    @Override
    public String toString() {
        String stateSymbol = getStateEmoji() + ' ';
        String priorityString = getPriorityString();
        return  stateSymbol + title + '\n' +
                description + '\n' +
                "<" + bucket + ">" + ' ' +
                priorityString;
    }
    public String getCreatedAt() {
        return createdAt;
    }
}
