package controlador;

import dao.EstudianteImpl;
import java.io.Serializable;
import javax.swing.table.DefaultTableModel;
import modelo.Estudiante;
import vistas.EstudianteView;

// NOMPAC	APEPAC	SEXPAC	DNIPAC	FNPAC	DIRPAC	NUMUBI
public class EstudianteC implements Serializable {

    Estudiante pac;
    EstudianteImpl dao;

    public EstudianteC() {
        pac = new Estudiante();        
    }

    public void registrar() {
        try {
            setVariables();
            dao = new EstudianteImpl();
            dao.registrar(pac);            
        } catch (Exception e) {
            System.out.println("Error en PacienteC/registrar: " + e.getMessage());
        }
    }
    
    public void modificar(){
        try {
            pac.setCodigo(Integer.parseInt(EstudianteView.jlblCodigo.getText()));
            setVariables();
            dao = new EstudianteImpl();
            dao.modificar(pac);
        } catch (Exception e) {
            System.out.println("Error en PacienteC/modificar: " + e.getMessage());
        }
    }

    public void eliminar(){
        try {
            pac.setCodigo(Integer.parseInt(EstudianteView.jlblCodigo.getText()));
            dao = new EstudianteImpl();
            dao.eliminar(pac.getCodigo());
        } catch (Exception e) {
            System.out.println("Error en EstudianteC/eliminar: " + e.getMessage());
        }
    }
    public void listar(DefaultTableModel modelo, Integer tipo, String dato, String estado) {
        try {
            dao = new EstudianteImpl();
            dao.listar(modelo, tipo, dato, estado);
        } catch (Exception e) {
            System.out.println("Error en EstudianteC/listar: " + e.getMessage());
        }
    }

    public void setVariables() {
        try {   
            pac.setNombre(EstudianteView.jtxtNombre.getText());
            pac.setApellido(EstudianteView.jtxtApellido.getText());
            pac.setSexo(EstudianteView.sexo);
            pac.setDni(EstudianteView.jtxtDni.getText());
            pac.setNacimiento(EstudianteView.jdcNac.getDate());
            pac.setDir(EstudianteView.jtxtdir.getText());
        } catch (Exception e) {
            System.out.println("Error en PacienteC/setVariables: " + e.getMessage());
        }
    }

    public static void limpiar() {
        try {
            EstudianteView.jrdnFemenino.setSelected(false);
            EstudianteView.jrdnMasculino.setSelected(false);
            EstudianteView.jtxtNombre.setText("");
            EstudianteView.jtxtApellido.setText("");
            EstudianteView.jtxtDni.setText("");
        } catch (Exception e) {
            System.out.println("Error en PacienteC/limpiar: " + e.getMessage());
        }
    }
    
    public void cambiarEstado(String estado){
        try {
            dao = new EstudianteImpl();
            dao.cambiarEstado(estado, Integer.parseInt(EstudianteView.jlblCodigo.getText()));
        } catch (Exception e) {
            
        }
    }

}
