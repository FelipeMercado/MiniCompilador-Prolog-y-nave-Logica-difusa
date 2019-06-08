package nave;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Imagen extends javax.swing.JPanel {
	String nombre;
	public Imagen(String nombre) {
		this.nombre=nombre;
		this.setSize(300, 400); //se selecciona el tamaño del panel
	}
	
	//Se crea un método cuyo parámetro debe ser un objeto Graphics
	
	public void paint(Graphics grafico) {
		Dimension height = getSize();
		//System.out.println(nombre);
		//Se selecciona la imagen que tenemos en el paquete de la //ruta del programa
		
		ImageIcon Img = new ImageIcon(getClass().getResource(nombre)); 
		
		//se dibuja la imagen que tenemos en el paquete Images //dentro de un panel
		
		grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
		
		setOpaque(false);
		super.paintComponent(grafico);
	}
}
