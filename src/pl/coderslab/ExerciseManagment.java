package pl.coderslab;

import pl.coderslab.dao.ExerciseDAO;
import pl.coderslab.model.Exercise;

import java.util.Scanner;

public class ExerciseManagment {

    public static void main(String[] args) {
        ExerciseDAO exerciseAll = new ExerciseDAO();
        Exercise[] exercises = exerciseAll.findAll();
        for (Exercise exercise : exercises){
            System.out.println(exercise);
        }
        Scanner scanner = new Scanner(System.in);
        label:
        while (true) {
            System.out.println("Wybierz jedną z opcji:\n" +
                    "\n" +
                    "add – dodanie zadania,\n" +
                    "edit – edycja zadania,\n" +
                    "delete – usunięcia zadania,\n" +
                    "quit – zakończenie programu.");
            String answer = scanner.nextLine();
            switch (answer) {
                case "add": {
                    Exercise exerciseCreate = new Exercise();
                    System.out.println("Podaj tytuł zadania:");
                    String title = scanner.nextLine();
                    exerciseCreate.setTitle(title);
                    System.out.println("Podaj opis zadania:");
                    String description= scanner.nextLine();
                    exerciseCreate.setDescription(description);
                    ExerciseDAO exerciseDAO = new ExerciseDAO();
                    exerciseDAO.create(exerciseCreate);
                    break;
                }
                case "edit": {
                    Exercise exerciseEdit = new Exercise();
                    System.out.println("Podaj id zadania do edycji:");
                    int id = scanner.nextInt();
                    exerciseEdit.setId(id);
                    System.out.println("Podaj nowy tytuł:");
                    String title= scanner.nextLine();
                    exerciseEdit.setTitle(title);
                    System.out.println("Podaj nowy opis zadania:");
                    String description = scanner.nextLine();
                    exerciseEdit.setDescription(description);
                    ExerciseDAO exerciseDAO = new ExerciseDAO();
                    exerciseDAO.update(exerciseEdit);
                    break;
                }
                case "delete": {
                    System.out.println("Podaj id zadania do usunięcia:");
                    int id = scanner.nextInt();
                    ExerciseDAO exerciseDAO = new ExerciseDAO();
                    exerciseDAO.delete(id);
                    break;
                }
                default:
                    System.out.println("Koniec programu.");
                    break label;

            }
        }
    }
}
