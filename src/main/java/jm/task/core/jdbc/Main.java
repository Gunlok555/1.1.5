package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    private final static UserService user = new UserServiceImpl();

    public static void main(String args[]) {

        user.createUsersTable();

        user.saveUser("Вася", "Пупкин", (byte) 45);
        user.saveUser("Петя", "Петров", (byte) 45);
        user.saveUser("Фирс", "Кочка", (byte) 45);
        user.saveUser("Ваня", "Иванов", (byte) 45);

        user.removeUserById(2);

        user.getAllUsers();

        user.cleanUsersTable();

        user.dropUsersTable();
    }

}
