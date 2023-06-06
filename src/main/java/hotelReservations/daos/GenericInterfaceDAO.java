package hotelReservations.daos;

import java.util.List;

public interface GenericInterfaceDAO<T> {

    T getByID(int id);
    List<T> getAll();
    void create( T t);
    void update(T t);
    void delete(T t);


}
