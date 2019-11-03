package pl.coderslab;

import pl.coderslab.dao.UserDao;
import pl.coderslab.model.User;

import java.util.Scanner;

public class UsersManagment {
    public static void main(String[] args) {
        UserDao userAll = new UserDao();
        User[] users = userAll.findAll();
        for (User user : users){
            System.out.println(user);
        }
        Scanner scanner = new Scanner(System.in);
        label:
        while (true) {
            System.out.println("Wybierz jedną z opcji:\n" +
                    "\n" +
                    "add – dodanie użytkownika,\n" +
                    "edit – edycja użytkownika,\n" +
                    "delete – usunięcie użytkownika,\n" +
                    "quit – zakończenie programu.");
            String answer = scanner.nextLine();
            switch (answer) {
                case "add": {
                    User userCreate = new User();
                    System.out.println("Podaj imię:");
                    String name = scanner.nextLine();
                    userCreate.setUserName(name);
                    System.out.println("Podaj email:");
                    String email = scanner.nextLine();
                    userCreate.setEmail(email);
                    System.out.println("Podaj hasło:");
                    String password = scanner.nextLine();
                    userCreate.setEmail(password);
                    UserDao userDao = new UserDao();
                    userDao.create(userCreate);
                    break;
                }
                case "edit": {
                    User userEdit = new User();
                    System.out.println("Podaj id użytkownika do edycji:");
                    int id = scanner.nextInt();
                    userEdit.setId(id);
                    System.out.println("Podaj nowe imię:");
                    String name = scanner.nextLine();
                    userEdit.setUserName(name);
                    System.out.println("Podaj nowy email:");
                    String email = scanner.nextLine();
                    userEdit.setEmail(email);
                    System.out.println("Podaj nowe hasło:");
                    String password = scanner.nextLine();
                    userEdit.setEmail(password);
                    UserDao userDao = new UserDao();
                    userDao.update(userEdit);
                    break;
                }
                case "delete": {
                    System.out.println("Podaj id użytkownika do usunięcia:");
                    int id = scanner.nextInt();
                    UserDao userDao = new UserDao();
                    userDao.delete(id);
                    break;
                }
                default:
                    System.out.println("Koniec programu.");
                    break label;

            }
        }
    }
}
