package dao;

import model.User;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/andersenHW";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public List<User> showAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM public.users";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurename(resultSet.getString("surname"));
                user.setAge(resultSet.getInt("age"));
                users.add(user);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        System.out.println(users);
        return users;
    }

    @Override
    public void add(User user) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO public.users  VALUES(?, ?, ?, ?)");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


    @Override
    public boolean update(User user) {
        boolean updated = false;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE public.users SET name=?, surname=?, age=? WHERE id=?");

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setInt(4, user.getId());
            updated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return updated;

    }

    @Override
    public boolean delete(int id) {
        boolean deleted = false;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM public.users WHERE id=?");
            preparedStatement.setInt(1, id);
            deleted = preparedStatement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return deleted;

    }

    @Override
    public User read(int id) {
        User user = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT id, name, surname, age FROM public.users WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = Integer.parseInt(resultSet.getString("age"));
                user = new User(id, name, surname, age);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }
}
