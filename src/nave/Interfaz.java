package nave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Action;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class Interfaz extends JFrame implements ActionListener {
	public static Imagen nave=new Imagen("nave.png");
	public static Imagen f1=new Imagen("f1.png");
	public static Imagen f2=new Imagen("f2.png");
	public static Imagen f3=new Imagen("f3.png");
	
	public static Imagen e1=new Imagen("e1.png");
	public static Imagen e2=new Imagen("e2.png");
	public static Imagen e3=new Imagen("e3.png");
	public static Imagen ptf=new Imagen("ptf.png");
	public static Imagen exp=new Imagen("exp.png");
	static DecimalFormat df =new DecimalFormat(".000");
	public int  masa= 10000;
	public static int v=0;
	static boolean park=false;
	static JSlider slider = new JSlider(JSlider.VERTICAL);
	static double desplaza=1;
	static int potencia=0,potenciaAnt=0,pAct=0,pAnt=0;
	JButton reset =new JButton("Reset");
	static double energiaPotencial=0;
	static double posActual;
	static TimerTask tasknew = new MyTimerTask();
    static Timer timer = new Timer();
    static double tiempo = 0,tiempoS=0,tiempoG=0;
	static String stat="baja",nivelPotencia="cero";
	static String [] abc= {"A","B","C","D","E","G","H","I","J"};
	static double  posInicial=0,  velocidad=0,velSubida=0,gravedad=10, posAnt,posicion;
	@SuppressWarnings({ "unchecked", "rawtypes", "unused", "static-access" })
	 
	public Interfaz() {
		
		this.posInicial=800;
//		if(posInicial>800) {
//			posInicial=(posInicial-800)*-1;
//		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//finaliza el programa cuando se da click en la X
 		//elementos
		DefaultBoundedRangeModel model = new DefaultBoundedRangeModel(20, 0, 0, 50);
		slider.setMajorTickSpacing(50);
		slider.setMinorTickSpacing(10);
		slider.setValue(0);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
				
		// Add positions label in the slider
		Hashtable position = new Hashtable();
		position.put(0,   new JLabel("0"  ));
		position.put(25,  new JLabel("10" ));
		position.put(50,  new JLabel("20" ));
		position.put(75,  new JLabel("35" ));
		position.put(100, new JLabel("50"));
		
		exp.setBounds(70, (int)posInicial-340,600,400);
		ptf.setBounds(315, (int)posInicial+20,100, 40);
		exp.setVisible(false);
		reset.setBounds( 750,200,100,30);
		reset.setActionCommand(reset.getText());
		reset.addActionListener(this);
		 
		// Set the label to be drawn
		slider.setLabelTable(position); 
		slider.setBounds(750, 400, 100, 400);
		slider.setVisible(true);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				potenciaAnt=potencia;
				potencia=slider.getValue();
				if(potencia>potenciaAnt) {
					nivelPotencia="aumenta";
				}
				if(potencia<potenciaAnt) {
					nivelPotencia="disminuye";
				}
				if(potencia==0) {
					nivelPotencia="cero";
				}
				
			}
		});
		ptf.setVisible(true);
		e1.setVisible(true);
		e2.setVisible(false);
		e3.setVisible(false);
		f1.setVisible(false);
		f2.setVisible(false);
		f3.setVisible(false);
		
		
		this.setBounds(350, 0, 850, 900);
		this.setVisible(true);
		this.setLayout(null);
		this.add(exp);
		this.add(f1);
		this.add(f2);
		this.add(f3);
		nave.setBounds(350, 0, 30,30);
		this.add(nave);
		this.add(ptf);
		this.add(slider);
		this.add(reset);
		this.add(e1);
		this.add(e2);
		this.add(e3);
		
		e1.setBounds(0,0,this.getWidth()-100,this.getHeight());
		e2.setBounds(0,0,this.getWidth(),this.getHeight());
		e3.setBounds(0,0,this.getWidth(),this.getHeight());
	}
			
	
	static double v1=50,v2=25,v3=9,v4=6,v5=3;
	private static int fuzzy(int pot) {
	
		double media=posInicial/2;
		double  a=media,b=media*.8,c=media*.6,d=media*4,e=media*.2;
		double pos=posInicial-nave.getY();
		System.out.println(pos);
		double vel=velocidad-velSubida;
		
		int x=slider.getValue();
		
		//reglas
		//System.out.println(a+": a+    b:"+b);
		if(pos<a&&pos>b) {//regla1
			System.out.println("Esta en a");
			if(vel>v1) {
				pot=pot+2;
			}else if(vel<v1&&vel>v2){
				pot--;
			}
			else {
				pot--;
			}
		}
		else
			if(pos<b&&pos>c) {//regla2
				System.out.println("Esta en b");
				if(vel>v2) {
					pot=pot+2;
				}else if(vel<v2&&vel>v3){
					pot--;
				}
				else {
					pot--;
				}
			}
			else
				if(pos<c&&pos>d) {//regla3
					System.out.println("Esta en c");
					if(vel>v3) {
						pot=pot++;
					}else if(vel<v3&&vel>v4){
						pot--;
					}
					else {
						pot--;
					}
				}
				else
					if(pos<d&&pos>e) {//regla4
						System.out.println("Esta en d");
						if(vel>v4) {
							pot=pot+2;
						}else if(vel<v4&&vel>v5){
							pot--;
						}
						else {
							pot--;
						}
					}
					else if(pos<e&&pos>0) {//regla5
						System.out.println("Esta en e");
						if(vel>v5) {
							pot=pot+2;
						}else if(vel<v5&&vel>0){
							pot--;
						}
						else {
							pot--;
						}
					}
					 
		
		
		
		
		return pot ;
	} 
	static void mover(){
		
		
//		if(posInicial-nave.getY()<posInicial/2+posInicial*.2) {
//			System.out.println("mas de la mitad");
//			slider.setValue(fuzzy(slider.getValue()));
//		}
		if(posicion>0) {//trabaja normalmente para calcular potencia y la vel a travez de ella
			//System.out.println("La potencia: "+nivelPotencia);
			velocidad=gravedad*tiempoS;	
			
			//Aquí se verifica que direccion lleva y si se aumentara o disminuira la velocidad
			if(nivelPotencia.equals("cero")) {
				tiempo+=.016;
				if(tiempoS>0) {
					tiempoS-=0.016;
				}
			}else
			if(nivelPotencia.equals("aumenta")) {
				if(velocidad<potencia) {
					tiempoS+=.016;
				}
				if(tiempo>0) {
				//tiempo-=.016;
				}
				if(velSubida>velocidad) {
					//tiempo=0;
				}
			}else
			if(nivelPotencia.equals("disminuye")) {
				if(velSubida>velocidad) {
					//tiempo=0;
				}
				if(potencia>velSubida) {
					 tiempoS+=.016;
				}
				if(potencia<velSubida) {
					//System.out.println("Aqui");
					if(velSubida>potencia) {
						tiempoS-=.016;
					}
					 if(tiempo>0) {
						 //tiempo-=.016;
					 }
				}
			}
			
			//se calcula las velocidades 
			velSubida =(gravedad )*(tiempoS );
			velocidad=gravedad*tiempo;//esta bien 
			posAnt=posicion;
			posicion=nave.getY()+(velocidad-velSubida);
		
			//Saber en que direccion va  
			if (posicion>posAnt) {
				stat="baja";
			}else
				if(posicion<posAnt) {
					stat="sube";
				}else
					if(posicion==posAnt) {
						stat="estable";
					}
			
			//cambio de fuego
			if(potencia==0) {
				f1.setVisible(false);
				f2.setVisible(false);
				f3.setVisible(false);
			}else
				if(potencia>0&&potencia<33) {
					f1.setVisible(true);
					f1.setBounds(nave.getX() , nave.getY()+nave.getHeight(), nave.getWidth(), 30);
					f2.setVisible(false);
					f3.setVisible(false);
				}else
					if(potencia>33&&potencia<67) {
						f1.setVisible(false);
						f2.setVisible(true);
						f2.setBounds(nave.getX() , nave.getY()+nave.getHeight(), nave.getWidth(), 50);
						f3.setVisible(false);
					}else
						if(potencia>66&&potencia<101) {
							f1.setVisible(false);
							f2.setVisible(false);
							f3.setVisible(true);
							f3.setBounds(nave.getX() , nave.getY()+nave.getHeight(), nave.getWidth(), 100);
						}
		}else {
			f1.setVisible(false);
			f2.setVisible(false);
			f3.setVisible(false);
			slider.setValue(0);
			tiempo+=0.016;
			if(tiempoS>0) {
				tiempoS-=0.016;
			}
			velSubida =(gravedad )*(tiempoS );
			velocidad=gravedad*tiempo;//esta bien 
			posicion=nave.getY()+(velocidad-velSubida);
			
		}
		energiaPotencial = ((posicion)*((velocidad-velSubida)*(velocidad-velSubida))/2);
		if(posicion>posInicial) {//landing
			
			if(energiaPotencial>50000) {//se estrwella
				exp.setVisible(true);
				i.setTitle("Altura: "+(0)+"\t|\tVelocidad "+(0)+"\t|\tEnergia potencial "+df.format(energiaPotencial));
			}else {//aterriza bien 
					park=true;
					nave.setLocation(nave.getX(),(int)(posInicial));
					i.setTitle("Altura: "+df.format(0)+"\t|\tVelocidad "+df.format(velocidad-velSubida)+"\t|\tEnergia potencial "+df.format(energiaPotencial));
				}
		 		f1.setVisible(false);
		 		f2.setVisible(false);
		 		f3.setVisible(false);
		}else {
			i.setTitle("Altura: "+df.format(posInicial-nave.getY())+"\t|\tVelocidad "+df.format(velocidad-velSubida)+"\t|\tEnergia potencial "+df.format(energiaPotencial));
		}
 		nave.setLocation(nave.getX(),(int)(posicion));
		tiempoG+=.016;
	}


	static Interfaz i;
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		boolean func=true;
			try{
				i=new Interfaz(   );
				func=true;
			}catch(Exception e) {
				func=false;
			}
			timer.scheduleAtFixedRate(tasknew,500,16);      
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(arg0.getActionCommand());
		 if (arg0.getActionCommand().equals( "Reset")) {
		     tiempoS=0;
		     tiempo=0;
		     
		     nave.setBounds(350, 000, 30,30);
//		 	 Timer timer = new Timer();
		     this.park=false;
		     exp.setVisible(false);
		 	 this.v=0;
		 	 }
	}
}
