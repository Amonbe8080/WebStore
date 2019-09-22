
package simplelogin;

import Modelo.Conexion;
import Modelo.Producto;
import Modelo.Usuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;


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
    @FXML private JFXButton refreshUsuario;
    
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
                
                idUsuario.setDisable(true);
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
                Alert mensaje = new Alert(AlertType.WARNING);
                mensaje.setTitle(e.getMessage().toString());
                mensaje.setHeaderText("Error al ingresar los datos, intentalo de nuevo.");
                mensaje.show();
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
        
        refreshUsuario.setVisible(true);     
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
        double valorComp = Double.parseDouble(TFValorComp.getText());
        double valorVenta = Double.parseDouble(TFValorVenta.getText());
        double cantidad = Double.parseDouble(TFCantProd.getText());
        
        String tipoProd =  CBidTipoProd.getValue().toString().trim();
        String nombProd = TFnomProd.getText().trim();
        String estado = CBEstaProd.getValue().toString().trim();
        
        if (" ".equals(tipoProd) || " ".equals(nombProd) || " ".equals(estado) || valorComp <= 0|| valorVenta <=0 || cantidad <= 0) {
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("Ups");
            mensaje.setHeaderText("Error al ingresar los datos, campos vacios o negativos.");
            mensaje.show();
            return false;
        }else{
            return true;
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
        try {
            Conexion objCon = new Conexion();
            if (objCon.consultaDirecta("SELECT img FROM productos WHERE idProductos = '6'")) {

                if(objCon.Reader.next()){   
                    InputStream is = objCon.Reader.getBinaryStream("img");
                    OutputStream os = new FileOutputStream(new File("photo.jpg"));
                    byte[] content = new byte[1024];
                    int size = 0;
                    while((size = is.read(content)) != -1) {                        
                        os.write(content, 0, size);
                    }
                    Image image1 = new Image("file:"+getClass().getResourceAsStream("/Assets/Img/pc.jpg"));
                    img.setImage(image1);
                   
                }else{
                    System.out.println("No ha devuelto nada.");
                }
             }   
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
