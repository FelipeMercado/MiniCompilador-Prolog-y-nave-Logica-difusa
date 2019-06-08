package nave;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.TimerTask;

import javax.swing.JSlider;
@SuppressWarnings("unused")
class MyTimerTask extends TimerTask {
 
	public void run() {
		  
		if(Interfaz.nave.getY()<Interfaz.posInicial) {
			  
		 
		Interfaz.nave.repaint();
		Interfaz.mover();
		Interfaz.getFrames();
		}if(Interfaz.nave.getY()>Interfaz.posInicial) {
			  
			System.out.println("--------------");
			System.out.println(Interfaz.tiempoG);
			JFrame c=new JFrame();
			if(Interfaz.park&&Interfaz.v==0) {
				Interfaz.v=1;
				JOptionPane.showMessageDialog(null,"the landing of the ship was successful","You are a good pilot" ,JOptionPane.INFORMATION_MESSAGE);
			}else if(!Interfaz.park&&Interfaz.v==0) {
				Interfaz.v=1;
				JOptionPane.showMessageDialog(null, "ohh!!! Men you crashed the pod", "The speed of the descent was to high", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
 