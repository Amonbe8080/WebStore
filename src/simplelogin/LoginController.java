package simplelogin;

import Modelo.Usuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML JFXTextField usuario;
    @FXML JFXPasswordField password;
    @FXML JFXButton iniciar;
    @FXML Label error;
    @FXML AnchorPane ap;
    @FXML Hyperlink linkedin;
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {     
       
    }    

    public void irLinkedin(){
        linkedin = new Hyperlink("https://teamtreehouse.com/community/need-help-please-cannot-run-main-exception-in-application-start-method");
        
//        getHostServices().showDocument(url);
        
    }
    public void iniciarSesion(){    
        Usuario user = new Usuario();
        
        try {    
            user.setNickUsua(usuario.getText().trim());
            user.setPassUsua(password.getText().trim());
        } catch (Exception e) {
            error.setText("Error al digitar datos.");
        }
        
        if (user.iniciarSesion()) {
            ResultSet read = user.getRecibido();
            
            try{
                if (read.next()) {
                    Stage stage = new Stage();                
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    
                    AnchorPane mp = fxmlLoader.load(getClass().getResource("MenuPrincipalUsuario.fxml"));
                 
                    stage.setResizable(false);
                    stage.getIcons().add(new Image("Assets/Img/New-firefox-logo-2019.png"));
                    stage.setTitle("Siempre estamos para atenderte "+ read.getString(3));  
                    stage.setScene(new Scene(mp));  
                    stage.show();  
                    ap.getScene().getWindow().hide();
                    read.close();
                }else{
                    error.setText("");
                    error.setText("Usuario o contraseña incorrectos.");             
                }      
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }else{
            error.setText(user.getError());
            user = null;
        }
    }
    
    public void registro(ActionEvent reg){
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegistroUsuario.fxml"));
            
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.getIcons().add(new Image("Assets/Img/New-firefox-logo-2019.png"));
            stage.setTitle("Regístrate");  
            stage.setScene(new Scene(root1));  
            stage.show();
            ap.getScene().getWindow().hide();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
                
    }
    
}
