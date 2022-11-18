package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    private final static UserService user = new UserServiceImpl();
    private static final User user1 = new User("Вася", "Пупкин", (byte) 45);
    private static final User user2 = new User("Петя", "Петров", (byte) 45);
    private static final User user3 = new User("Фирс", "Кочка", (byte) 45);
    private static final User user4 = new User("Ваня", "Иванов", (byte) 45);

    public static void main(String args[]) {

        user.createUsersTable();

        user.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        user.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        user.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        user.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        user.removeUserById(2);

        user.getAllUsers();

        user.cleanUsersTable();

        user.dropUsersTable();
    }

}
