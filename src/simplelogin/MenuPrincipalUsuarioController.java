
package simplelogin;

import Modelo.Conexion;
import Modelo.Producto;
import Modelo.Usuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class MenuPrincipalUsuarioController implements Initializable {
    
    //<editor-fold defaultstate="collapsed" desc="Sección de Usuario">
    //Fields usuario
    @FXML private JFXTextField idUsuario;
    @FXML private JFXComboBox tipo;
    @FXML private JFXTextField nombre;  
    @FXML private JFXTextField apellidos;
    @FXML private JFXComboBox genero;
    @FXML private JFXComboBox estado;
    @FXML private JFXTextField nick;
    @FXML private JFXTextField pass;
       
    //Botones usuario
    @FXML private Button btnEliminar;
    @FXML private Button btnModificar;
    @FXML private Button btnAgregar;
    @FXML private Button btnConsultar;
    
    //Label error
    @FXML private Label mensaje;
    
    // Lista usuario
    private ObservableList<Usuario> user;
    
    //Tabla usuario
    @FXML private TableView<Usuario> tblUser;
    
    // Columnas de la tabla usuario
    @FXML private TableColumn<Usuario, Float> clmiId;
    @FXML private TableColumn<Usuario, Integer> clmTipo;
    @FXML private TableColumn<Usuario, String> clmNom;
    @FXML private TableColumn<Usuario, String> clmApel;
    @FXML private TableColumn<Usuario, String> clmGene;
    @FXML private TableColumn<Usuario, String> clmEsta;
    @FXML private TableColumn<Usuario, String> clmNick;
    @FXML private TableColumn<Usuario, String> clmPass;
    
    //ComboBox
    @FXML private ObservableList listaTipos;
    @FXML private ObservableList<Usuario> listaGenero;
    @FXML private ObservableList<Usuario> listaEstado;
    
    //Resulset
    private ResultSet recibido;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Sección de Productos">   
    // Tabla de productos
    @FXML private TableView<Producto> tblProducto;
    
    // Columnas de la tabla productos
    @FXML private TableColumn<Producto, Integer> TCidProductos;
    @FXML private TableColumn<Producto, String> TCidTipoProd;
    @FXML private TableColumn<Producto, String> TCNombProd;
    @FXML private TableColumn<Producto, Double> TCValorComp;
    @FXML private TableColumn<Producto, Double> TCValorVent;
    @FXML private TableColumn<Producto, Double> TCCantProd;
    @FXML private TableColumn<Producto, String> TCEstaProd;
    
    // Fields productos
    @FXML private JFXTextField TFidProducto;
    @FXML private JFXComboBox CBidTipoProd;
    @FXML private JFXTextField TFnomProd;
    @FXML private JFXTextField TFValorComp;
    @FXML private JFXTextField TFValorVenta;
    @FXML private JFXTextField TFCantProd;
    @FXML private JFXComboBox CBEstaProd;
    
    // Botones Productos
    @FXML private Button btnEliminarProducto;
    @FXML private Button btnModificarProducto;
    @FXML private Button btnAgregarProducto;
    @FXML private Button btnConsultarProducto;
    
    @FXML private JFXButton refreshTBProductos;
    
    // ObservableList TipoProductos
    @FXML private ObservableList tipoProducto;
    
    
    // Listar Productos
    private ObservableList<Producto> test;
    
    // Lista estado del producto
    @FXML private ObservableList estados;
    //</editor-fold>
    
    @FXML ImageView img;
    @FXML public static AnchorPane mp;
    @FXML JFXTextField idIMG;
    @FXML ImageView imgMostrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Crear los observable
        listaTipos = FXCollections.observableArrayList();
        listaGenero = FXCollections.observableArrayList();
        listaEstado = FXCollections.observableArrayList();
        tipoProducto = FXCollections.observableArrayList();
        estados = FXCollections.observableArrayList();
           
        //Llenar los observable
        Usuario.tipoUsu(listaTipos);
        Usuario.genero(listaGenero);
        Usuario.estado(listaEstado);
        Producto.tipoProd(tipoProducto);
        Producto.estadoProd(estados);

        //Enviar los valores
        tipo.setItems(listaTipos);
        genero.setItems(listaGenero);
        estado.setItems(listaEstado);
        CBidTipoProd.setItems(tipoProducto);
        CBEstaProd.setItems(estados);
        
        //Inicializar el tableview
        user = FXCollections.observableArrayList();
        Usuario us = new Usuario();
        us.listarUsuario(user);
        
        test = FXCollections.observableArrayList();
        Producto prod = new Producto();
        prod.listarProductos(test);
                
        // TableView Usuario
        clmiId.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        clmTipo.setCellValueFactory(new PropertyValueFactory<>("idTipoUsua"));
        clmNom.setCellValueFactory(new PropertyValueFactory<>("NombUsua"));
        clmApel.setCellValueFactory(new PropertyValueFactory<>("ApelUsua"));
        clmGene.setCellValueFactory(new PropertyValueFactory<>("GeneUsua"));
        clmEsta.setCellValueFactory(new PropertyValueFactory<>("EstaUsua"));
        clmNick.setCellValueFactory(new PropertyValueFactory<>("NickUsua"));
        clmPass.setCellValueFactory(new PropertyValueFactory<>("PassUsua"));
        
        // TableView Producto
        TCidProductos.setCellValueFactory(new PropertyValueFactory<>("idProductos"));
        TCidTipoProd.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
        TCNombProd.setCellValueFactory(new PropertyValueFactory<>("NombProd"));
        TCValorComp.setCellValueFactory(new PropertyValueFactory<>("ValorComp"));
        TCValorVent.setCellValueFactory(new PropertyValueFactory<>("ValorVent"));
        TCCantProd.setCellValueFactory(new PropertyValueFactory<>("CantProd"));
        TCEstaProd.setCellValueFactory(new PropertyValueFactory<>("EstaProd"));

        gestionarEventos();
        recargarTablaProductos();   
        recargarTabla(); 
       
    }

    //<editor-fold defaultstate="collapsed" desc="Metodos de Usuario">    
    private void gestionarEventos() {     
           tblUser.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Usuario> arg0, Usuario valorAnterior, Usuario newValue) -> {               
               if (newValue != null) {
                idUsuario.setText(Float.toString(newValue.getIdUsuario()));
                tipo.setValue(newValue.getIdTipoUsua());
                nombre.setText(newValue.getNombUsua());
                apellidos.setText(newValue.getApelUsua());
                genero.setValue(newValue.getGeneUsua().substring(0, 1));
                estado.setValue(newValue.getEstaUsua());
                nick.setText(newValue.getNickUsua());
                pass.setText(newValue.getPassUsua());
                
                btnAgregar.setDisable(true);
                btnEliminar.setDisable(false);
                btnModificar.setDisable(false);
                btnConsultar.setDisable(true);
                
                nick.setDisable(true);
                pass.setDisable(true);
                

                }
            });   
    }
   
    public void modificarUsuario(){
        try {
            
            float Id = Float.valueOf((idUsuario.getText().trim()));
            int tipoUsua = Integer.parseInt(tipo.getValue().toString());
            String NombUsua = nombre.getText();
            String ApelUsua = apellidos.getText();
            String GeneUsua = genero.getValue().toString().substring(0, 1);
            String EstaUsua = estado.getValue().toString();
            String NickUsua = nick.getText();
            String PassUsua = pass.getText();
        
            Usuario modUser = new Usuario(Id, tipoUsua, NombUsua, ApelUsua, GeneUsua, EstaUsua, NickUsua, PassUsua);
        
        if (modUser.crudUsuario("modificar")) {
            recargarTabla();
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Realizado.");
            mensaje.setHeaderText("Usuario modificado con exito.");
            mensaje.show();
            modUser = null;
            
            recargarTabla();
//            img.setImage(value);
        }else{
            mensaje.setText(modUser.getError());
            modUser = null;
        }
        } catch (Exception e) {
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle(e.getMessage().toString());
            mensaje.setHeaderText("Error al ingresar los datos, intentalo de nuevo.");
            mensaje.show();
        }
    }
    
    public void eliminarUsuario(){
        try {
            float Id = Float.valueOf((idUsuario.getText().trim()));
            int tipoUsua = Integer.parseInt(tipo.getValue().toString());
            String NombUsua = nombre.getText();
            String ApelUsua = apellidos.getText();
            String GeneUsua = genero.getValue().toString().substring(0, 1);
            String EstaUsua = estado.getValue().toString();
            String NickUsua = nick.getText();
            String PassUsua = pass.getText(); 
            
            Usuario delUser = new Usuario(Id, tipoUsua, NombUsua, ApelUsua, GeneUsua, EstaUsua, NickUsua, PassUsua);
            
            if(delUser.crudUsuario("eliminar")) {
                recargarTabla();
                idUsuario.setText(null);
                tipo.setValue(null);
                nombre.setText(null);
                apellidos.setText(null);
                genero.setValue(null);
                estado.setValue(null);
                nick.setText(null);
                pass.setText(null);
                
                Alert mensaje = new Alert(AlertType.INFORMATION);
                mensaje.setTitle("Realizado.");
                mensaje.setHeaderText("Usuario Eliminado con exito.");
                mensaje.show();
                
                recargarTabla();
                nuevo();
            }else{
                mensaje.setText(delUser.getError());
            }  
            } catch (Exception e) {
                Alert mensaje = new Alert(AlertType.WARNING);
                mensaje.setTitle(e.getMessage().toString());
                mensaje.setHeaderText("Error al ingresar los datos, intentalo de nuevo.");
                mensaje.show();
         }
    }

    public void agregarUsuario(){    
        try { 
            float Id = Float.valueOf((idUsuario.getText().trim()));
            int tipoUsua = Integer.parseInt(tipo.getValue().toString());
            String NombUsua = nombre.getText();
            String ApelUsua = apellidos.getText();
            String GeneUsua = genero.getValue().toString().substring(0, 1);
            String EstaUsua = estado.getValue().toString();
            String NickUsua = nick.getText();
            String PassUsua = pass.getText();
            
            if (idUsuario.getLength()>=12) {
                mensaje.setText("El id es muy largo.");
            }
            Usuario addUser = new Usuario(Id, tipoUsua, NombUsua, ApelUsua, GeneUsua, EstaUsua, NickUsua, PassUsua);

            if (addUser.crudUsuario("guardar")) {
                recargarTabla();
                Alert mensaje = new Alert(AlertType.INFORMATION);
                mensaje.setTitle("Realizado");
                mensaje.setHeaderText("Usuario guardado con exito.");
                mensaje.show();
                
                recargarTabla();
            }else{
                mensaje.setText(addUser.getError());
            }
            } catch (Exception e) {
                Alert errormsj = new Alert(AlertType.WARNING);
                errormsj.setTitle(e.getMessage());
                errormsj.setHeaderText("Error al ingresar los datos, intentalo de nuevo.");
                errormsj.show();
            }
    }
    
    public void nuevo(){
        idUsuario.setText(null);
        tipo.setValue(1);
        nombre.setText(null);
        apellidos.setText(null);
        genero.setValue(null);
        estado.setValue("Activo");
        nick.setText(null);
        pass.setText(null);
        
        btnAgregar.setDisable(false);
        btnEliminar.setDisable(true);
        btnModificar.setDisable(true);
        btnConsultar.setDisable(false); 
        
        nick.setDisable(false);
        pass.setDisable(false); 
    }
    
    public void consultar(){
        Usuario user = new Usuario();
        
        user.setIdUsuario(Float.valueOf(idUsuario.getText().trim()));
        try {
            if (user.consultarUsuario()) {
               recibido = user.getRecibido();
                if (recibido.next()) {                    
                    tipo.setValue(recibido.getString("idTipoUsua"));       
                    nombre.setText(recibido.getString("NombUsua"));
                    apellidos.setText(recibido.getString("ApelUsua"));
                    genero.setValue(recibido.getString("GeneUsua").substring(0,1));
                    estado.setValue(recibido.getString("EstaUsua"));
                    nick.setText(recibido.getString("NickUsua"));
                    pass.setText(recibido.getString("PassUsua"));  
                    
                    btnModificar.setDisable(false);
                    btnEliminar.setDisable(false);
                    btnAgregar.setDisable(true);
                    
                    nick.setDisable(true);
                    pass.setDisable(true);
                }else{
                    Alert mensaje = new Alert(AlertType.ERROR);
                    mensaje.setTitle("No encontrado.");
                    mensaje.setHeaderText("Este usuario no existe o su id puede estar erroneo.");
                    mensaje.show();
                }
               recibido.close();
               user = null;
            }else{
                mensaje.setText(user.getError());           
                user = null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
    public void recargarTabla(){    
        user.removeAll(user);
        
        Usuario us = new Usuario();
        us.listarUsuario(user);
        
        gestionarEventos();
        tblUser.setItems(user);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Metodos de Productos"> 
    public boolean verificarCampos(){
        try {
            
       
            float valorComp = Float.parseFloat(TFValorComp.getText());
            float valorVenta = Float.parseFloat(TFValorVenta.getText());
            float cantidad = Float.parseFloat(TFCantProd.getText());
        
            String tipoProd =  CBidTipoProd.getValue().toString();
            String nombProd = TFnomProd.getText();
            String state = CBEstaProd.getValue().toString();

 
        if (" ".equals(tipoProd) || " ".equals(nombProd) || " ".equals(state) || valorComp <= 0|| valorVenta <=0 || cantidad <= 0) {
            Alert vermsj = new Alert(AlertType.WARNING);
            vermsj.setTitle("Ups");
            vermsj.setHeaderText("Error al ingresar los datos, campos vacios o negativos.");
            vermsj.show();
            return false;
        }else{
            return true;
        }
         } catch (Exception e) {
            Alert vermsj = new Alert(AlertType.WARNING);
            vermsj.setTitle("Ups");
            vermsj.setHeaderText("Error al ingresar los datos, campos vacios o negativos.");
            vermsj.show();
            return false;
        }
    }
    
    public void modificarProducto(){
        if (verificarCampos()) {
            int idProd = Integer.parseInt(TFidProducto.getText());
            String tipoProd =  CBidTipoProd.getValue().toString();
            String nombProd = TFnomProd.getText();
            double valorComp = Double.parseDouble(TFValorComp.getText());
            double valorVenta = Double.parseDouble(TFValorVenta.getText());
            double cantidad = Double.parseDouble(TFCantProd.getText());
            String estado = CBEstaProd.getValue().toString();
            
            Producto objProd = new Producto(idProd, tipoProd, nombProd, valorComp, valorVenta, cantidad, estado);
            
            if (objProd.crudProducto("modificar")) {
                tblProducto.getSelectionModel().clearSelection();
                recargarTabla();
                Alert mjsMod = new Alert(AlertType.INFORMATION);
                mjsMod.setTitle("Realizado.");
                mjsMod.setHeaderText("Producto modificado con exito.");
                mjsMod.show();
                objProd = null;
                
                recargarTablaProductos();
                nuevoProducto();
            }else{
                Alert modError = new Alert(AlertType.ERROR);
                modError.setTitle("Ups.");
                modError.setHeaderText("Ha ocurrido un error: "+objProd.getError());
                modError.show();
                objProd = null;
            }  
        }
    }
    
    public void eliminarProducto(){
        try {
            int idProd = Integer.parseInt(TFidProducto.getText());
            String tipoProd = CBidTipoProd.getValue().toString();
            String nombProd = TFnomProd.getText();
            double valorComp = Double.parseDouble(TFValorComp.getText());
            double valorVenta = Double.parseDouble(TFValorVenta.getText());
            double cantidad = Double.parseDouble(TFCantProd.getText());
            String estado = CBEstaProd.getValue().toString();
            Producto objProd = new Producto(idProd, tipoProd, nombProd, valorComp, valorVenta, cantidad, estado);
            
            if (objProd.crudProducto("eliminar")) {
                tblProducto.getSelectionModel().clearSelection();
                recargarTabla();
                Alert msjPos = new Alert(AlertType.INFORMATION);
                msjPos.setTitle("Realizado.");
                msjPos.setHeaderText("Producto eliminado con exito.");
                msjPos.show();
                objProd = null;
                
                recargarTablaProductos();
                nuevoProducto();
            }else{
                Alert mensaje = new Alert(AlertType.ERROR);
                mensaje.setTitle("Ups.");
                mensaje.setHeaderText("Ha ocurrido un error: "+objProd.getError());
                mensaje.show();
                objProd = null;
        }} catch (Exception e) {
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle(e.getMessage());
            mensaje.setHeaderText("Error al ingresar los datos, intentalo de nuevo.");
            mensaje.show();
        }   
    }
    
    public void agregarProducto(){
        if (verificarCampos()) {
            int idProd = Integer.parseInt(TFidProducto.getText());
            String tipoProd = CBidTipoProd.getValue().toString();
            String nombProd = TFnomProd.getText();
            double valorComp = Double.parseDouble(TFValorComp.getText());
            double valorVenta = Double.parseDouble(TFValorVenta.getText());
            double cantidad = Double.parseDouble(TFCantProd.getText());
            String estado = CBEstaProd.getValue().toString();
            System.out.println(idProd);
            Producto objProd = new Producto(idProd, tipoProd, nombProd, valorComp, valorVenta, cantidad, estado);
            
            if (objProd.crudProducto("guardar")) {
                tblProducto.getSelectionModel().clearSelection();
                recargarTabla();
                Alert msjPos = new Alert(AlertType.INFORMATION);
                msjPos.setTitle("Realizado.");
                msjPos.setHeaderText("Producto agregado con exito.");
                msjPos.show();
                objProd = null;
                recargarTablaProductos();
                nuevoProducto();
            }else{
                Alert guardarErr = new Alert(AlertType.ERROR);
                guardarErr.setTitle("Ups.");
                guardarErr.setHeaderText("Ha ocurrido un error: "+objProd.getError());
                guardarErr.show();
                objProd = null;
         } 
       }
    }
    
    public void consultarProducto(){
        try {
            Producto conPro = new Producto();
            if (conPro.consultarProducto(Integer.parseInt(TFidProducto.getText().trim()))) {
                ResultSet rs = conPro.getRsProducto();
                if(rs.next()) {    
                    CBidTipoProd.setValue(rs.getString("Categoria"));
                    TFnomProd.setText(rs.getString("NombProd"));
                    TFValorComp.setText(rs.getString("ValorComp"));
                    TFValorVenta.setText(String.valueOf(rs.getFloat("ValorVent")));
                    TFCantProd.setText(String.valueOf(rs.getFloat("CantProd")));
                    CBEstaProd.setValue(rs.getString("EstaProd"));
                    
                    rs.close();
                    
                    btnModificarProducto.setDisable(false);
                    btnEliminarProducto.setDisable(false);
                    
                    btnAgregarProducto.setDisable(true);
                }else{
                   Alert mensaje = new Alert(AlertType.WARNING);
                    mensaje.setHeaderText("No existe un producto con este id.");
                    mensaje.show(); 
                }            
            } 
        } catch (Exception e) {
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle(e.getMessage());
            mensaje.setHeaderText("Error al ingresar los datos, intentalo de nuevo.");
            mensaje.show();
        }
    }
    
    private void TBProductos() {
           tblProducto.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Producto> arg0, Producto valorAnterior, Producto newValue) -> { 
                if (newValue != null) {
                    TFidProducto.setText(Integer.toString(newValue.getIdProductos()));
                    TFidProducto.setDisable(true);
                    CBidTipoProd.setValue(newValue.getCategoria());
                    TFnomProd.setText(newValue.getNombProd());
                    TFValorComp.setText(Double.toString(newValue.getValorComp()));
                    TFValorVenta.setText(Double.toString(newValue.getValorVent()));
                    TFCantProd.setText(Double.toString(newValue.getCantProd()));
                    CBEstaProd.setValue(newValue.getEstaProd());

                    btnAgregarProducto.setDisable(true);
                    btnEliminarProducto.setDisable(false);
                    btnModificarProducto.setDisable(false);
                    btnConsultarProducto.setDisable(true);
                }
            });   
        
    }
    
    public void nuevoProducto(){
        TFidProducto.setText("");
        CBidTipoProd.setValue(null);
        TFnomProd.setText("");
        TFValorComp.setText("");
        TFValorVenta.setText("");
        TFCantProd.setText("");
        CBEstaProd.setValue("Disponible");
        
        TFidProducto.setDisable(false);
        
        btnAgregarProducto.setDisable(false);
        btnConsultarProducto.setDisable(false);
        btnEliminarProducto.setDisable(true);
        btnModificarProducto.setDisable(true);
   }
    
    public void recargarTablaProductos(){
        test.removeAll(test);
        
        Producto pro = new Producto();
        pro.listarProductos(test);

        TBProductos();    
        tblProducto.setItems(test);
    }
    //</editor-fold>
    
    public void cargarImagen() throws FileNotFoundException{
        try {
            
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar Imagen");

            // Agregar filtros para facilitar la busqueda
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            
            File imgFile = fileChooser.showOpenDialog(null);

            if (imgFile != null) {
                Image image = new Image("file:" + imgFile.getAbsolutePath());
                img.setImage(image);
                
                Producto pro = new Producto();
                if (pro.insertarIMG(imgFile)) {
                    System.out.println("Imagen Guardada");
                }else{
                    System.out.println(pro.getError());
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
 
    }   
    
    public void seleccionarImagen(){
        String as = idIMG.getText();
        try {
            Conexion objCon = new Conexion();
            if (objCon.consultaDirecta("SELECT img FROM productos WHERE idProductos = '"+as+"'")) {

                if(objCon.Reader.next()){   
                    byte byteImage[] = null;

                    // obtener la columna imagen, luego el arreglo de bytes 
                    Blob blob = objCon.Reader.getBlob("img");
                    byteImage = blob.getBytes(1, (int) blob.length());

                    // crear el Image y mostrarlo en el ImageView
                    Image imgen = new Image(new ByteArrayInputStream(byteImage));
                    imgMostrar = new ImageView(imgen);
                    imgMostrar.setImage(imgen);
                }else{
                    System.out.println("No ha devuelto nada.");
                }
             }   
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public void reporteProductos(){
        try {
        Conexion con = new Conexion();
        JasperReport reporte = null;
        String path = "src\\Reportes\\Productos.jasper";       
        reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
        
        con.conectar();
            
        JasperPrint jprint = JasperFillManager.fillReport(path, null, con.getConnection());
        
        JasperViewer view = new JasperViewer(jprint,false);
            
        view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        view.setVisible(true);
        } catch (JRException ex) {
            System.out.println("Error al cargar reporte.");
        }
    }
    
    public void cerrarSesion() {
        try {         
            if (mp.getScene() == null) {
                Stage stage = new Stage();
                AnchorPane ap = FXMLLoader.load(getClass().getResource("Login.fxml"));

                stage.setResizable(false);
                stage.getIcons().add(new Image("Assets/Img/New-firefox-logo-2019.png"));
                stage.setScene(new Scene(ap));  
                stage.show(); 
            }else{
                mp.getScene().getWindow().hide();
            }
            
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            

    }
}
