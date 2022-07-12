package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import modelo.Estudiante;
import services.UtilToSql;

public class EstudianteImpl extends Conexion implements ICRUD<Estudiante> {
// NUMPAC	NOMPAC	APEPAC	SEXPAC	DNIPAC	FNPAC	DIRPAC	NUMUBI

    public static Integer cantRegistros = 0;
            
    @Override
    public void registrar(Estudiante estudiante) throws Exception {
        String sql = "insert into estudiante (NOMPAC,APEPAC,SEXPAC,DNIPAC,FNPAC,DIRPAC,NUMUBI,ESTPAC) values (?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getSexo());
            ps.setString(4, estudiante.getDni());
            ps.setDate(5, UtilToSql.convert(estudiante.getNacimiento()));
            ps.setString(6, estudiante.getDir());
            ps.setString(7, estudiante.getUbigeo());
            ps.setString(8, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en EstudianteImpl/registrar: " + e.getMessage());
        }
    }

    @Override
    public void modificar(Estudiante estudiante) throws Exception {
        String sql = "update estudiante set NOMPAC=?,APEPAC=?,SEXPAC=?,DNIPAC=?,FNPAC=?,DIRPAC=?,NUMUBI=? where numpac=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getSexo());
            ps.setString(4, estudiante.getDni());
            ps.setDate(5, UtilToSql.convert(estudiante.getNacimiento()));
            ps.setString(6, estudiante.getDir());
            ps.setString(7, estudiante.getUbigeo());
            ps.setInt(8, estudiante.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en EstudianteImpl/modificar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int codigo) throws Exception {
        String sql = "delete from estudiante where numpac=" + codigo;
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en EstudianteImpl/eliminar: " + e.getMessage());
        }
    }

    
    public void listar(DefaultTableModel modeloTabla, Integer tipo, String dato, String estado) throws Exception {
      
        String sql = "";
        switch (tipo) {
            case 1:
                sql = "select * from estudiante where ESTPAC ='" + estado + "'";
                break;
            case 2:
                sql = "select * from estudiante where ESTPAC ='" + estado + "' and NOMPAC like '%" + dato + "%'";
                break;
            case 3:
                sql = "select * from estudiante where ESTPAC ='" + estado + "' and APEPAC like '%" + dato + "%'";
                break;
            case 4:
                sql = "select * from estudiante where ESTPAC ='" + estado + "' and DNIPAC like '%" + dato + "%'";
                break;
//            case 5:
//                sql = "select * from paciente where ESTPAC='" + dato + "'";
//                break;
        }
        String datos[] = new String[8];
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            cantRegistros = 0;
            while (rs.next()) {                
                for (int i = 0; i < 8; i++) {
                    datos[i] = rs.getString(i + 1);
                }
                modeloTabla.addRow(datos);
                cantRegistros ++; // cantRegistros = cantRegistros + 1
            } 
            rs.close();
            st.close();             
        } catch (Exception e) {
            System.out.println("Error en EstudianteImpl/listar" + e.getMessage());
        }
    }
    
  
    public void cambiarEstado (String estado, int codigo) throws Exception{
        String sql = "update estudiante set ESTPAC=? where numpac=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, estado);
            ps.setInt(2, codigo);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en EstudianteImpl/cambiarEstado: " + e.getMessage());
        }
    }

}
