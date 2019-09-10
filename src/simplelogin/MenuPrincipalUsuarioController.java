
package simplelogin;

import Modelo.Usuario;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MenuPrincipalUsuarioController implements Initializable {
    //Fields
    @FXML private JFXTextField idUsuario;
    @FXML private JFXComboBox tipo;
    @FXML private JFXTextField nombre;  
    @FXML private JFXTextField apellidos;
    @FXML private JFXComboBox genero;
    @FXML private JFXComboBox estado;
    @FXML private JFXTextField nick;
    @FXML private JFXTextField pass;
    
    //Botones
    @FXML private Button btnEliminar;
    @FXML private Button btnModificar;
    @FXML private Button btnAgregar;
    @FXML private Button btnConsultar;
    
    
    //Label error
    @FXML private Label mensaje;
    
    // Lista
    private ObservableList<Usuario> user;
    
    //Tabla
    @FXML private TableView<Usuario> tblUser;
    
    // Columnas de la tabla
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
    
    @FXML public static AnchorPane mp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Crear los observable
        listaTipos = FXCollections.observableArrayList();
        listaGenero = FXCollections.observableArrayList();
        listaEstado = FXCollections.observableArrayList();
        
        //Llenar los observable
        Usuario.tipoUsu(listaTipos);
        Usuario.genero(listaGenero);
        Usuario.estado(listaEstado);

        //Enviar los valores
        tipo.setItems(listaTipos);
        genero.setItems(listaGenero);
        estado.setItems(listaEstado);
        
        //Inicializar el tableview
        user = FXCollections.observableArrayList();
        Usuario us = new Usuario();
        us.listarUsuario(user);
        
        clmiId.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        clmTipo.setCellValueFactory(new PropertyValueFactory<>("idTipoUsua"));
        clmNom.setCellValueFactory(new PropertyValueFactory<>("NombUsua"));
        clmApel.setCellValueFactory(new PropertyValueFactory<>("ApelUsua"));
        clmGene.setCellValueFactory(new PropertyValueFactory<>("GeneUsua"));
        clmEsta.setCellValueFactory(new PropertyValueFactory<>("EstaUsua"));
        clmNick.setCellValueFactory(new PropertyValueFactory<>("NickUsua"));
        clmPass.setCellValueFactory(new PropertyValueFactory<>("PassUsua"));
        
        recargarTabla();
        
        
    }    
    
    private void gestionarEventos() {
        try {
            tblUser.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Usuario>(){     
            @Override
            public void changed(ObservableValue<? extends Usuario> arg0,Usuario valorAnterior, Usuario newValue) {               
                idUsuario.setText(Float.toString(newValue.getIdUsuario()));
                tipo.setValue(newValue.getIdTipoUsua());
                nombre.setText(newValue.getNombUsua());
                apellidos.setText(newValue.getApelUsua());
                genero.setValue(newValue.getGeneUsua());
                estado.setValue(newValue.getEstaUsua());
                nick.setText(newValue.getNickUsua());
                pass.setText(newValue.getPassUsua());

                btnAgregar.setDisable(true);
                btnEliminar.setDisable(false);
                btnModificar.setDisable(false);
                btnConsultar.setDisable(true);
                
                nick.setDisable(true);
                pass.setDisable(true);
                }});
        } catch (Exception e) {
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle(e.getMessage().toString());
            mensaje.setHeaderText("Error al cargar TableView.");
            mensaje.show();
        }
               
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

            Usuario addUser = new Usuario(Id, tipoUsua, NombUsua, ApelUsua, GeneUsua, EstaUsua, NickUsua, PassUsua);

            if (addUser.crudUsuario("guardar")) {
                recargarTabla();
                Alert mensaje = new Alert(AlertType.INFORMATION);
                mensaje.setTitle("Realizado");
                mensaje.setHeaderText("Usuario guardado con exito.");
                mensaje.show();

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

//        Alert mensaje = new Alert(AlertType.WARNING);
//        mensaje.setTitle("Registro actualizado");
//        mensaje.setHeaderText("El registro ha sido actualizado exitosamente");
//        mensaje.show();
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

    public void initData(Stage stage) {
        stage.close();    
    }
    
}
