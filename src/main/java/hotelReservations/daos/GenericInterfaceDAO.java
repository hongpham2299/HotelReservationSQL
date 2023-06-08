package hotelReservations.daos;

import java.util.List;

public interface GenericInterfaceDAO<T> {

    void getByID(T t);
    void getAll();
    void create( T t);
    void update(T t);
    void delete(T t);


}
