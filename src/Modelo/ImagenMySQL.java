package Modelo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImagenMySQL extends javax.swing.JPanel {

    BufferedImage ruta;

    public ImagenMySQL(int x, int y, BufferedImage ruta) {
        this.setSize(x, y); //se selecciona el tamaño del panel
        this.ruta = ruta;
    }

//Se crea un método cuyo parámetro debe ser un objeto Graphics
    public void paint(Graphics grafico) {
        Dimension height = getSize();

        BufferedImage img = ruta;

        if (img != null) {
            grafico.drawImage(img, 0, 0, height.width, height.height, null);
        }

        setOpaque(false);
        super.paintComponent(grafico);
    }

}
