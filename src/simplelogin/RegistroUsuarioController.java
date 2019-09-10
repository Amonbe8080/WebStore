package simplelogin;

import Modelo.Usuario;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class RegistroUsuarioController implements Initializable {
    
//    @FXML JFXTextField txtdocumento;
    @FXML JFXTextField txtname;
    @FXML JFXTextField idUsuario;
    @FXML JFXTextField txtapellido;
    @FXML JFXTextField txtusu;
    @FXML JFXPasswordField txtpass;
    @FXML JFXComboBox cmbgene;
    @FXML Label error;
    
    @FXML public AnchorPane apReg;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> generos = FXCollections.observableArrayList("Masculino","Femenino","Indefinido");
        cmbgene.setItems(generos);
    }    
    
    
    public void registrar() {
        if (idUsuario.getText().isEmpty() || txtname.getText().isEmpty()||
            txtapellido.getText().isEmpty() || txtusu.getText().isEmpty()||
            txtpass.getText().isEmpty()) {
            error.setText("Por favor, Ingresa todos los datos.");
        }else{
        Usuario user = new Usuario();
        
        try{
            user.setIdUsuario(Float.parseFloat((idUsuario.getText().trim())));
            user.setIdTipoUsua(5);
            user.setNombUsua(txtname.getText().trim());
            user.setApelUsua(txtapellido.getText().trim());
             String genero = (String) cmbgene.getSelectionModel().getSelectedItem();
            genero = genero.substring(0, 1);
            user.setGeneUsua(genero);
            user.setNickUsua(txtusu.getText().trim());
            user.setPassUsua(txtpass.getText().trim());
        }catch(Exception e){
            error.setText("Error al ingresar datos.");      
        }
        
        if (user.crudUsuario("guardar")) {      
            try {
     
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuPrincipalUsuario.fxml"));
            
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.getIcons().add(new Image("Assets/Img/New-firefox-logo-2019.png"));
            stage.setTitle("Siempre estamos para atenderte "+ txtname.getText().trim() + " "+ txtapellido.getText().trim());  
            stage.setScene(new Scene(root1)); 
            stage.show();    
            apReg.getScene().getWindow().hide();
            
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else{
            error.setText(user.getError());
           user = null;
        } 
       }
    }
    
    public void irLogin(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
            
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image("Assets/Img/New-firefox-logo-2019.png"));
            stage.setResizable(false);
            stage.setTitle("Comienza ya a construir.");
            stage.setScene(new Scene(root1));  
            stage.show(); 
            apReg.getScene().getWindow().hide();
         } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
