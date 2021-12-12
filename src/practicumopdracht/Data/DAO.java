package practicumopdracht.data;

import java.util.ArrayList;

public interface DAO<B> {
    ArrayList<B>  getAll();

    void  add(B model);
    void remove(B model);

    boolean load();
}
