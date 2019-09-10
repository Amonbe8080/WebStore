
package Modelo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Producto extends javax.swing.JPanel{
    BufferedImage ruta;
    
     public Producto(int x, int y, BufferedImage ruta) {
      this.setSize(x, y); //se selecciona el tamaño del panel
      this.ruta = ruta;
    }
     
     public Producto(){
         
     }
    
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
    
}
