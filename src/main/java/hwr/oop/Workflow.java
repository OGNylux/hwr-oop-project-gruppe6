package hwr.oop;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Workflow {

    public static void displayError(String message){
        System.out.println(ConsoleColors.RED_BOLD + message + ConsoleColors.RESET);
    }
    public static void generalWorkflowMessage(){
        System.out.print("Workflow has been created. Do the following tasks: \n");
    }

    public static void displayWorkflow(String tasks){
        System.out.print(ConsoleColors.GREEN + tasks + "\n" + ConsoleColors.RESET);
    }

    public static void createWorkflow(BufferedReader availableTime){
        //Problem: how can I access all the "EstimatedTime" attributes of each task?
        int timeForWorkflow = Integer.parseInt(availableTime.toString());
        if (timeForWorkflow == 1){
            int taskCounter = 0;
            while (taskCounter < 2) {
                if (... ==EstimatedTime.SHORT){
                    displayWorkflow(/*Taskname*/);
                    taskCounter += 1;
                } else {
                    if (taskCounter == 1) {
                        displayError("No more tasks available for your selected period of time!");
                        break;
                    } else {
                        displayError("No tasks available for your selected period of time!");
                        break;
                    }
                }
            }
        } else if (timeForWorkflow == 2){
            float taskCounter = 0;
            while (taskCounter < 6.0) {
                if (... ==EstimatedTime.SHORT){
                    displayWorkflow(/*Taskname*/);
                    taskCounter += 1;
                } else if (... == EstimatedTime.MEDIUM){
                    displayWorkflow(/*Taskname*/);
                    taskCounter += 2;
                } else {
                    if (taskCounter > 1 && taskCounter < 4) {
                        displayError("No more tasks available for your selected period of time!");
                        break;
                    } else if (taskCounter == 0){
                        displayError("No tasks available for your selected period of time!");
                        break;
                }
            }
        } else if (timeForWorkflow == 3) {
            float taskCounter = 0;
            while (taskCounter <= 6.0) {
                if (... ==EstimatedTime.SHORT){
                    displayWorkflow(/*Taskname*/);
                    taskCounter += 0.5;
                } else if (... == EstimatedTime.MEDIUM){
                    displayWorkflow(/*Taskname*/);
                    taskCounter += 2;
                } else if (... == EstimatedTime.LONG){
                    displayWorkflow(/*Taskname*/);
                    taskCounter += 3;
                } else {
                    if (taskCounter > 1 && taskCounter < 4) {
                        displayError("No more tasks available for your selected period of time!");
                        break;
                    } else {
                        displayError("No tasks available for your selected period of time!");
                        break;
                }
            }

        } else if (timeForWorkflow == 4) {
            float taskCounter = 0;
            while (taskCounter < 12.0) {
                if (... ==EstimatedTime.SHORT){
                    displayWorkflow(/*Taskname*/);
                    taskCounter += 0.5;
                } else if (... == EstimatedTime.MEDIUM){
                    displayWorkflow(/*Taskname*/);
                    taskCounter += 2;
                } else if (... == EstimatedTime.LONG){
                    displayWorkflow(/*Taskname*/);
                    taskCounter += 3;
                } else if (... == EstimatedTime.XLONG){
                    displayWorkflow(/*Taskname*/);
                    taskCounter +=6;
                } else {
                    if (taskCounter > 1 && taskCounter < 4) {
                        displayError("No more tasks available for your selected period of time!");
                        break;
                    } else {
                        displayError("No tasks available for your selected period of time!");
                        break;
                    }
            }
        }else {
            displayError("There is no workflow available for your time.\n");
            System.out.println("Typing Mistake? No worries, just start a new Workflow?");
        }
        }
    }
}