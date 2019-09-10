package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion { 
    private final String url = "jdbc:mysql://localhost/webstore";
    private final String usuario = "root";
    private final String password = "";  
    private Connection connection;
    
    public ResultSet Reader;
    public String error;
    
    public boolean conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, usuario, password);
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            return true;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        return false;
    }
    
    public void cerrarConexion(){
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean consultar(String nameusp) {
        if (nameusp.isEmpty()) {
            error = "Nombre de USP vacio";
            return false;
        }  
        
        if (!conectar()) { conectar();}
        
        try{
            CallableStatement proc = connection.prepareCall(nameusp);
            proc.execute();
            
            Reader = proc.getResultSet();
            
            return true;
        } catch (SQLException ex) {
            error = "Falla al ejecutar USP: "+ex.getMessage();
            return false;
        }
    }
    
    public boolean ejecutarSentencia(String nameusp){
        if (nameusp.isEmpty()) {
            error = "Nombre de USP vacio";
            return false;
        }  
        if (!conectar()) { conectar();}
        
         System.out.println(nameusp);
        try{
            CallableStatement proc = connection.prepareCall(nameusp);
            proc.execute();

            return true;    
        }catch (SQLException ex) {
            error = ex.getMessage();
            return false;
        }       
    }
    
    public boolean consultaDirecta(String sentencia){
        if (sentencia.isEmpty()) {
            error = "Sentencia vacia";
            return false;
        }  
        
        if (!conectar()) { conectar();}
         
        try{
            Statement st = connection.createStatement();
            Reader = st.executeQuery(sentencia);
            return true;
        } catch (SQLException ex) {
            error = "Falla al ejecutar sentencia: "+ex.getMessage();
            return false;
        }
    }
}
