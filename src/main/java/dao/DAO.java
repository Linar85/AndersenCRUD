package dao;

import model.User;

import java.util.ArrayList;
import java.util.List;

public interface DAO {


    public List<User> showAll();

    public void add(User user);

    public boolean update(User user);

    public boolean delete(int id);

    public User read(int id);

}
