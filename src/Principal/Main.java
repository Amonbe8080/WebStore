/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 *
 * @author SENA
 */
public class Main extends Application {
   
    
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/simplelogin/MenuPrincipalUsuario.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.getIcons().add(new Image("Assets/Img/New-firefox-logo-2019.png"));
            stage.setResizable(false);
            stage.setTitle("Comienza ya a construir.");;
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
