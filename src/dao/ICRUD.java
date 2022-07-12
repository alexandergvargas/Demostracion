package dao;

import java.util.List;

public interface ICRUD<Generic> {

    // Los métodos DML BD: insert, delete, update, merge, restore record
    void registrar(Generic object) throws Exception;

    void modificar(Generic object) throws Exception;

    void eliminar(int codigo) throws Exception;
    
//    void cambiarEstado (char estado, int codigo) throws Exception;

//    List<Generic> listarTodos() throws Exception;
}
