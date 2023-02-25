
package edu.dottn.services;

import edu.dottn.entities.Association;
import java.util.List;

public interface IController<T> {
    public boolean Signup(T t);
    public Association Signin(String username,String password);
    public void update(T t);
    public void delete(int id);
    public List<T> getAll();
    public Association getById(int id);
}
