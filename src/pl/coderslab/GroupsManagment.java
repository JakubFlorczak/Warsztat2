package pl.coderslab;

import pl.coderslab.dao.User_GroupDAO;
import pl.coderslab.model.User_group;

import java.util.Scanner;

public class GroupsManagment {

    public static void main(String[] args) {
        User_GroupDAO groupAll = new User_GroupDAO();
        User_group[] groups = groupAll.findAll();
        for (User_group group : groups){
            System.out.println(group);
        }
        Scanner scanner = new Scanner(System.in);
        label:
        while (true) {
            System.out.println("Wybierz jedną z opcji:\n" +
                    "\n" +
                    "add – dodanie grupy,\n" +
                    "edit – edycja grupy,\n" +
                    "delete – usunięcie grupy,\n" +
                    "quit – zakończenie programu.");
            String answer = scanner.nextLine();
            switch (answer) {
                case "add": {
                    User_group groupCreate = new User_group();
                    System.out.println("Podaj nazwę grupy:");
                    String name = scanner.nextLine();
                    groupCreate.setName(name);
                    User_GroupDAO groupDAO = new User_GroupDAO();
                    groupDAO.create(groupCreate);
                    break;
                }
                case "edit": {
                    User_group groupEdit = new User_group();
                    System.out.println("Podaj id grupy do edycji:");
                    int id = scanner.nextInt();
                    groupEdit.setId(id);
                    System.out.println("Podaj nową nazwę:");
                    String name= scanner.nextLine();
                    groupEdit.setName(name);
                    User_GroupDAO groupDAO = new User_GroupDAO();
                    groupDAO.create(groupEdit);
                    break;
                }
                case "delete": {
                    System.out.println("Podaj id zadania do usunięcia:");
                    int id = scanner.nextInt();
                    User_GroupDAO groupDAO = new User_GroupDAO();
                    groupDAO.delete(id);
                    break;
                }
                default:
                    System.out.println("Koniec programu.");
                    break label;

            }
        }
    }
}
