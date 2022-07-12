package services;

import dao.Conexion;
import java.net.URL;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportGenerator extends Conexion {

    public static final String REPORTE_ESTADO_PACIENTE = "ListPacientesEstado.jasper";
    public static final String REPORTE_SIMPLE_PACIENTE = "ListPacientesPOO.jasper";

    public JasperPrint generarReporteSimpleEstudiante() throws JRException, ClassNotFoundException {
        try {
            JasperPrint reporteLleno = JasperFillManager.fillReport(REPORTE_SIMPLE_PACIENTE, new HashMap(), this.conectar());
            return reporteLleno;
        } catch (Exception e) {
            System.out.println("Error en generarReporteSimplePaciente1 " + e.getMessage());
        }
        return null;
    }

    public JasperPrint generarReporteSimpleEstudiante2() throws JRException, ClassNotFoundException {
        try {
            URL in = this.getClass().getResource("/reports/ListPacientesPOO.jasper");
            JasperReport jr = (JasperReport) JRLoader.loadObject(in);            
            JasperPrint reporteLleno = JasperFillManager.fillReport(jr, new HashMap(), this.conectar());
            return reporteLleno;
        } catch (Exception e) {
            System.out.println("Error en generarReporteSimplePaciente2 " + e.getMessage());
        }
        return null;
    }
    
        public JasperPrint generarReporteSimpleEstudiante3(String estadoCli) throws JRException, ClassNotFoundException {
        try {
            HashMap parametro = new HashMap();
            parametro.put("estado", estadoCli);
            JasperPrint reporteFinal = JasperFillManager.fillReport(REPORTE_ESTADO_PACIENTE, parametro, this.conectar());
            return reporteFinal;
        } catch (Exception e) {
            System.out.println("Error en generarReporteSimplePaciente3 " + e.getMessage());
        }
        return null;
    }

}
