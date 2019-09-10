package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;


public class Usuario {

    public Usuario(float idUsuario, int idTipoUsua, String NombUsua, String ApelUsua, String GeneUsua, String EstaUsua, String NickUsua, String PassUsua) {
        this.idUsuario = new SimpleFloatProperty(idUsuario);
        this.idTipoUsua = new SimpleIntegerProperty(idTipoUsua);
        this.NombUsua = new SimpleStringProperty(NombUsua);
        this.ApelUsua = new SimpleStringProperty(ApelUsua);
        this.GeneUsua = new SimpleStringProperty(GeneUsua);
        this.EstaUsua = new SimpleStringProperty(EstaUsua);
        this.NickUsua = new SimpleStringProperty(NickUsua);
        this.PassUsua = new SimpleStringProperty(PassUsua);
    }
    
    public Usuario(){
        
    }
     
    private FloatProperty idUsuario;
    private IntegerProperty idTipoUsua;
    private StringProperty NombUsua;
    private StringProperty ApelUsua;
    private StringProperty GeneUsua;
    private StringProperty EstaUsua;
    private StringProperty NickUsua;
    private StringProperty PassUsua;
    
    private ResultSet recibido;
    private String error;
    
    
    public float getIdUsuario() {
        return idUsuario.get();
    }

    public void setIdUsuario(float idUsuario) {
        this.idUsuario = new SimpleFloatProperty(idUsuario) ;
    }

    public int getIdTipoUsua() {
        return idTipoUsua.get();
    }

    public void setIdTipoUsua(int idTipoUsua) {
        this.idTipoUsua = new SimpleIntegerProperty(idTipoUsua);
    }

    public String getNombUsua() {
        return NombUsua.get();
    }

    public void setNombUsua(String NombUsua) {
        this.NombUsua = new SimpleStringProperty(NombUsua);
    }

    public String getApelUsua() {
        return ApelUsua.get();
    }

    public void setApelUsua(String ApelUsua) {
        this.ApelUsua = new SimpleStringProperty(ApelUsua);
    }

    public String getGeneUsua() {
        return GeneUsua.get();
    }

    public void setGeneUsua(String GeneUsua) {
        this.GeneUsua = new SimpleStringProperty(GeneUsua);
    }

    public String getEstaUsua() {
        return EstaUsua.get();
    }

    public void setEstaUsua(String EstaUsua) {
        this.EstaUsua = new SimpleStringProperty(EstaUsua);
    }

    public String getNickUsua() {
        return NickUsua.get();
    }

    public final void setNickUsua(String NickUsua) {
        this.NickUsua = new SimpleStringProperty(NickUsua);
    }

    public String getPassUsua() {
        return PassUsua.get();
    }

    public final void setPassUsua(String PassUsua) {
        this.PassUsua = new SimpleStringProperty(PassUsua);
    }

    public ResultSet getRecibido() {
        return recibido;
    } 

    public void setRecibido(ResultSet recibido) {
        this.recibido = recibido;
    }
    
    

    public String getError() {
        return error;
    }

    public boolean iniciarSesion(){
        String nameusp = "{call login('"+getNickUsua()+"','"+getPassUsua()+"')}";
        
        Conexion objcon = new Conexion();
        
        if (objcon.consultar(nameusp)) {
            recibido = objcon.Reader;
            return true;      
        }else{
            error = objcon.error;
            objcon.cerrarConexion();
            return false;
        }
    }

    public boolean crudUsuario(String tipoAEjecutar){
        String nameusp = "call CrudUsuario(" +getIdUsuario()+","
                                             +getIdTipoUsua()+",'"
                                             +getNombUsua()+"','"
                                             +getApelUsua()+"','"
                                             +getGeneUsua()+"','"
                                             +getNickUsua()+"','"
                                             +getPassUsua()+"','"
                                             +tipoAEjecutar+"')";
        Conexion objcon = new Conexion();
   
        if (objcon.ejecutarSentencia(nameusp)) {
            objcon.cerrarConexion();
            return true;
        }else{
            error = objcon.error;
            objcon.cerrarConexion();
            return false;
        }
        
    }

    public void listarUsuario(ObservableList<Usuario> usuario){
        Conexion con = new Conexion();        
        try {
        String nameusp ="call listarUsuarios();";
        if (con.consultar(nameusp)) {
            ResultSet rs = con.Reader;
            
            while(rs.next()){
                usuario.add(
                        new Usuario(
                                rs.getFloat("idUsuario"),
                                rs.getInt("idTipoUsua"),
                                rs.getString("NombUsua"),
                                rs.getString("ApelUsua"),
                                rs.getString("GeneUsua"),
                                rs.getString("EstaUsua"),
                                rs.getString("NickUsua"),
                                rs.getString("PassUsua")
                               ));
            }
            rs.close();
        }
        } catch (Exception e) {
            error = e.getMessage();
        }
        con.cerrarConexion();
    }
    
    public static void tipoUsu(ObservableList tipo){
        Conexion con = new Conexion();
        String sentencia = "SELECT NombTiUs FROM tipousua;";
        int i=0;
        try {
 
            if (con.consultaDirecta(sentencia)) {
                ResultSet rs = con.Reader;
                while (rs.next()) {
                    tipo.add(i, rs.getRow());
                    i++;
                }
            }   
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        con.cerrarConexion();       
    }
    
    public static void genero(ObservableList genero){
       genero.add(0, "Masculino");
       genero.add(1, "Femenino");      
       genero.add(2, "Indefinido"); 
    }
    
    public static void estado(ObservableList estado){
        estado.add(0, "Activo");
        estado.add(1, "Inactivo");     
    }
    
    public boolean consultarUsuario(){
        Conexion con = new Conexion();
        if (con.consultaDirecta("SELECT * FROM usuario WHERE idUsuario = '"+getIdUsuario()+"'")){
            recibido = con.Reader;
            return true;
        }else{
            error = con.error;
            return false;
        } 
    }
    
}
    

