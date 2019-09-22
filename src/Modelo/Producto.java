package Modelo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Producto extends javax.swing.JPanel{
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    private IntegerProperty idProductos;
    private StringProperty categoria;
    private IntegerProperty idTipoProd;
    private StringProperty NombProd;
    private DoubleProperty ValorComp;
    private DoubleProperty ValorVent;
    private DoubleProperty CantProd;
    private StringProperty EstaProd;
    
    BufferedImage ruta;
    private String error;
    private ResultSet rsProducto;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructores">
    public Producto(int idProductos, String idTipoProd, String NombProd, double ValorComp, double ValorVent, double CantProd, String EstaProd) {
        this.idProductos = new SimpleIntegerProperty(idProductos);
        this.categoria = new SimpleStringProperty(idTipoProd);
        this.NombProd = new SimpleStringProperty(NombProd);
        this.ValorComp = new SimpleDoubleProperty(ValorComp);
        this.ValorVent = new SimpleDoubleProperty(ValorVent);
        this.CantProd = new SimpleDoubleProperty(CantProd);
        this.EstaProd = new SimpleStringProperty(EstaProd);
    }
    
    // Constructor para inicializar la imagen
    public Producto(int x, int y, BufferedImage ruta) {
      this.setSize(x, y); //se selecciona el tamaño del panel
      this.ruta = ruta;
    }
    public Producto(){
         
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Setters y Getters">
    public String getCategoria() {
        return categoria.get();
    }
    public int getIdTipoProd() {
        return idTipoProd.get();
    }

    public void setIdTipoProd(int idTipoProd) {
        this.idTipoProd = new SimpleIntegerProperty(idTipoProd);
    }
    public void setCategoria(String categoria) {
        this.categoria = new SimpleStringProperty(categoria);
    }
    public int getIdProductos() {
        return idProductos.get();
    }
    
    public void setIdProductos(int idProductos) {
        this.idProductos = new SimpleIntegerProperty(idProductos);
    }

    public String getNombProd() {
        return NombProd.get();
    }

    public void setNombProd(String NombProd) {
        this.NombProd = new SimpleStringProperty(NombProd);
    }

    public double getValorComp() {
        return ValorComp.get();
    }

    public void setValorComp(double ValorComp) {
        this.ValorComp = new SimpleDoubleProperty(ValorComp);
    }

    public double getValorVent() {
        return ValorVent.get();
    }

    public void setValorVent(double ValorVent) {
        this.ValorVent = new SimpleDoubleProperty(ValorVent);
    }

    public double getCantProd() {
        return CantProd.get();
    }

    public void setCantProd(double CantProd) {
        this.CantProd = new SimpleDoubleProperty(CantProd);
    }

    public String getEstaProd() {
        return EstaProd.get();
    }

    public void setEstaProd(String EstaProd) {
        this.EstaProd = new SimpleStringProperty(EstaProd);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ResultSet getRsProducto() {
        return rsProducto;
    }

    public void setRsProducto(ResultSet rsProducto) {
        this.rsProducto = rsProducto;
    }
    
    // </editor-fold>
    
    @Override
    public void paint(Graphics grafico) {
      Dimension height = getSize();
        BufferedImage img = ruta;

        if (img != null) {
            grafico.drawImage(img, 0, 0, height.width, height.height, null);
        }

        setOpaque(false);
        super.paintComponent(grafico);
   }

    public boolean crudProducto(String tipoAEjecutar) {
        Conexion con = new Conexion();
        try {
            con.consultaDirecta("SELECT idTipoProd FROM tipoprod WHERE NoTiProd = '"+getCategoria()+"'");
            if (con.Reader.next()) {
                setIdTipoProd(con.Reader.getInt(1));
            }
            String nameusp = "call CrudProducto( "+getIdProductos()+","
                                                  +getIdTipoProd()+",'"
                                                  +getNombProd()+"', "
                                                  +getValorComp()+","
                                                  +getValorVent()+","
                                                  +getCantProd()+",'"
                                                  +getEstaProd()+"','"
                                                  +tipoAEjecutar+"' )";
        if (con.noReturnUSP(nameusp)) {
            con.cerrarConexion();
            return true;
        }else{
            error = con.error;
            con.cerrarConexion();
            return false;
        }
        
        } catch (SQLException e) {
            error = e.getMessage();
            System.out.println(e.getMessage());
        }
        
        return true;
    }
    
    public void listarProductos(ObservableList<Producto> pro){
        Conexion objcon = new Conexion();  
        try {
            if (objcon.consultaDirecta("SELECT * FROM listarProductos;")) {
                ResultSet ra = objcon.Reader;  
                
                while(ra.next()) {
                    pro.add(
                            new Producto(
                                    ra.getInt(1),
                                    ra.getString(2),
                                    ra.getString(3), 
                                    ra.getDouble(4),
                                    ra.getDouble(5),
                                    ra.getDouble(6),
                                    ra.getString(7)));                   
                }
                ra.close();
            }
        } catch (SQLException e) {
            error = e.getMessage();
            System.out.println(e.getMessage());
        }
        objcon.cerrarConexion();  
    }
    
    public static void tipoProd(ObservableList tipoProd){
        Conexion con = new Conexion();
        String sentencia = "SELECT NoTiProd FROM tipoprod;";
        int i=0;
        try {
            if (con.consultaDirecta(sentencia)) {
                ResultSet rs = con.Reader;
                while (rs.next()) {
                    tipoProd.add(i, rs.getString(1));
                    i++;
                }
            }   
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        con.cerrarConexion(); 
    }   
    
    public boolean consultarProducto(int idProd){
        Conexion con = new Conexion();
        if (con.consultaDirecta("SELECT * FROM listarProductos WHERE idProductos = '"+idProd+"'")){
            rsProducto = con.Reader;
            return true;
        }else{
            error = con.error;
            return false;
        } 
    }
    
    public static void estadoProd(ObservableList estados){
        estados.add(0, "Disponible");
        estados.add(1, "No Disponible"); 
        estados.add(2, "Agotado");
        estados.add(3, "En Revisión");
    }
    
    public boolean insertarIMG(File img) throws FileNotFoundException{
        FileInputStream input = new FileInputStream(img);
        Conexion objCon = new Conexion();
        
        if (objCon.ejecucionDirecta("UPDATE productos SET img = '"+input+"' WHERE idProductos = 8")) {
            return true;
        }else{  
            error = objCon.error;
            return false;
        }
    }
    
    public void consultarIMG(){

    }
}