package pl.coderslab;


import pl.coderslab.dao.ExerciseDAO;
import pl.coderslab.dao.SolutionDAO;
import pl.coderslab.dao.UserDao;
import pl.coderslab.model.Exercise;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

import java.util.Scanner;

public class SolutionManagment {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        label:
        while (true) {
            System.out.println("Wybierz jedną z opcji:\n" +
                    "\n" +
                    "add – przypisywanie zadań do użytkowników,\n" +
                    "view – przeglądanie rozwiązań danego użytkownika,\n" +
                    "quit – zakończenie programu.");
            String answer = scanner.nextLine();
            switch (answer) {
                case "add": {
                    UserDao userAll = new UserDao();
                    User[] users = userAll.findAll();
                    for (User user : users) {
                        System.out.println(user);
                    }
                    System.out.println("Podaj id użytkownika:");
                    int userId = scanner.nextInt();

                    ExerciseDAO exerciseAll = new ExerciseDAO();
                    Exercise[] exercises = exerciseAll.findAll();
                    for (Exercise exercise : exercises) {
                        System.out.println(exercise);
                    }
                    System.out.println("Podaj id zadania:");
                    int exerciseId = scanner.nextInt();

                    Solution userSolution = new Solution();
                    userSolution.setId(userId);
                    userSolution.setExercise_id(exerciseId);
                    SolutionDAO solutionDAO = new SolutionDAO();
                    solutionDAO.create(userSolution);
                    break;
                }
                case "view": {
                    System.out.println("Podaj id użytkownika:");
                    int userId = scanner.nextInt();
                    SolutionDAO solutionDao = new SolutionDAO();
                    Solution[] solutions = solutionDao.findAllByUserId(userId);
                    for (Solution solution : solutions) {
                        System.out.println(solution.toString());
                    }
                    break;
                }

                default:
                    System.out.println("Koniec programu.");
                    break label;

            }
        }
    }
}
