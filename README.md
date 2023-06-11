# Getting Things Done - A TODO List in Java


This repository contains a student project created for an ongoing lecture on object-oriented programming with Java at HWR Berlin (summer term 2023).

## Overview
## Table of Contents



### Project Description
This project is a implementation of a TODO List according to the principles of Getting Things done (GTD).
It focuses on Test Driven Development and the principles of Object Oriented Programming.
The final product is a command line application that allows the user to create, edit and delete tasks and projects.
Task rank automatically and can be sorted and grouped with buckets .
The automated ranking will also take into account what activities the user has done in the past and what activities the user has planned for the future.
### Features
The application allows you to prioritize the tasks you create.
Arrange your to-dos according to their specifics and use the hierarchical order that this tool offers you. Provide your task lists with their topic in order to keep track of your tasks and check them off.
## Feature List

[TODO]: # (For each feature implemented, add a row to the table!)

| Number | Feature                                     | Tests                                              | 
|--------|---------------------------------------------|----------------------------------------------------|
| 1      | Save Lists to JSON                          | JsonToListTest                                     |
| 2      | Load Lists from JSON                        | ListToJsonTest                                     |
| 3      | Edit, Remove, Add Item, Done -> to TodoList | MainTest.java                                      |
| 4      | Save Exit TodoList                          | MainTest.java                                      |
| 5      | Sort  Todos                                 | MainTest.java, ListClassTest.java                  |
| 6      | List Todos                                  | MainTest.java, ListClassTest.java (toString() method) |
| 7      | State Management                            | MainTest.java                                      |
| 8      | Management Priority Tests                   | PriorityTest.java                                  |

## Additional Dependencies

[TODO]: # (For each additional dependency your project requires- Add an additional row to the table!)

| Number | Dependency Name | Dependency Description | Why is it necessary? |
|--------|-----------------|------------------------|----------------------|
| 1      | GSON | Googles Json Library for JAVA | Loading and Saving Lists |
| 2      | /               | /                      | /                    |
| 3      | /               | /                      | /                    |
| 4      | /               | /                      | /                    |
| 5      | /               | /                      | /                    |

## Running Locally

1. Clone your fork and go enter the repository.
```
git clone <repo-url>
cd <repo-folder>
```
2. run the main
```
cd src/main && java -jar main.jar
```
