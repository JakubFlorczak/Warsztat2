package pl.coderslab;

import pl.coderslab.dao.SolutionDAO;
import pl.coderslab.model.Solution;


import java.util.Scanner;

public class UserSolutionManagement {

    public static void main(String[] args) {
        int userId = Integer.parseInt(args[0]);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz jedną z opcji:\n" +
                "\n" +
                "add – dodawanie rozwiązania,\n" +
                "view – przeglądanie swoich rozwiązań,\n" +
                "quit – zakończenie programu.");
        String answer = scanner.nextLine();
        label:
        switch (answer){
            case "add":{
                SolutionDAO solutionDAO = new SolutionDAO();
                Solution[] unsolved = solutionDAO.findAllUnsolvedByUserId(userId);
                for( Solution solution : unsolved){
                    System.out.println(solution.getExercise_id());
                }
                System.out.println("Podaj id zadania:");
                int unsolvedExercise = scanner.nextInt();
                System.out.println("Podaj rozwiązanie");
                String description = scanner.nextLine();
                Solution solution = new Solution();
                solution.setExercise_id(unsolvedExercise);
                solution.setDescription(description);
                solution.setUsers_id(userId);
                solutionDAO.updateUnsolved(solution);
                break;
            }
            case "view":{
                SolutionDAO solutionDao = new SolutionDAO();
                Solution[] solutions = solutionDao.findAllByUserId(userId);
                for( Solution solution : solutions){
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
