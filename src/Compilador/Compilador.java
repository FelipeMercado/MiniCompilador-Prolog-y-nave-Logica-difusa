package Compilador;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
 import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class Compilador extends JFrame {
	static boolean valida;
	//Declaración de variables
	File abre;
	ArbolRecur arbol;
	private static final long serialVersionUID = 1L;
	JLabel label=new JLabel("Load a file or type code");
	JTextArea t=new JTextArea();
	JScrollPane scroll;
	JLabel label1=new JLabel("rules");
	JTextArea t1=new JTextArea();
	JLabel label2=new JLabel("Make a query");
	JTextArea t2=new JTextArea();
	JLabel label3=new JLabel("Results");
	JTextArea t3=new JTextArea();
	JScrollPane scroll1;
	JScrollPane scroll2;
	JScrollPane scroll3;
	JButton b2=new JButton ("Compile");
	JButton b1=new JButton ("Load file");
	JButton b3=new JButton ("Exit");
	Oy1 o=new Oy1();
	//es_padre ( X , Y )  :- padre ( X , Y );
	public static Compilador c;
	Vector<Base> hechos=new Vector<Base>();
	Vector<String> mensaje=new Vector<String>();
	Consulta ConsultaUsuario;
	Pars p;
	public Compilador() {
		mensaje.add("Well done");
		mensaje.add("The expected token was a Variable not a parameter ");
		mensaje.add("The number of parameters doesn´t match");
		mensaje.add("The expected token was a parameter not a Variable");
		mensaje.add("The expected token was '('");
		
		mensaje.add("The expected token was ')'");
		mensaje.add("The expected token was '.'");
		mensaje.add("Fact is already on the base");
		mensaje.add("Fact is not on the base");
		mensaje.add("Check your consult");
		mensaje.add("The consult is not correct");
		mensaje.add("The consult is not correct");
		
		setResizable(false);
		setTitle("Prolog compiler made by Felipe Mercado");
		setVisible(true);
		setBounds(350,000 , 600,900);
		JPanel j=new JPanel(); 
		j.setLayout(null);
		j.setSize(600,1100);
		j.setBackground(Color.black);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//finaliza el programa cuando se da click en la X

		b1.setBounds(50, 810,150,20);
		b1.setBackground(Color.blue);
		b1.setMnemonic('l');
		b1.setForeground(Color.white);
		b1.addActionListener(o);
		
		b2.setBounds(270, 810,100,20);
		b2.setBackground(Color.GREEN);
		b2.setMnemonic('c');
		b2.setForeground(Color.white);
		b2.addActionListener(o);
		
		b3.setBounds(450, 810,100,20);
		b3.setBackground(Color.red);
		b3.setMnemonic('e');
		b3.setForeground(Color.white);
		b3.addActionListener(o);
		
		label.setBounds(new Rectangle(220,20,400,20));  
		label.setFont(new java.awt.Font("Arial", 0, 17));
		label.setForeground(Color.blue);
		t.setBackground(Color.WHITE);
		scroll = new JScrollPane(t);
		scroll.setBounds(50, 50, 500, 100);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);//t.setLineWrap(true);
		
		label1.setBounds(new Rectangle(280,170,400,20));  
		label1.setFont(new java.awt.Font("Arial", 0, 17));
		label1.setForeground(Color.blue);
		t1.setBackground(Color.WHITE);
		scroll1 = new JScrollPane(t1);
		scroll1.setBounds(50, 200, 500, 100);
	    scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);//t.setLineWrap(true);
		
	    
	    
	    
		label2.setBounds(new Rectangle(250,320,200,20));  
		label2.setFont(new java.awt.Font("Arial", 0, 17));
		label2.setForeground(Color.blue);
		t2.setBackground(Color.WHITE);
		t2.setFont(new java.awt.Font("Arial", 0, 22));
		scroll2 = new JScrollPane(t2);
		scroll2.setBounds(50, 350, 500, 40);
	    scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);//t.setLineWrap(true);
		
	    label3.setBounds(new Rectangle(280,420,200,20));  
		label3.setFont(new java.awt.Font("Arial", 0, 17));
		label3.setForeground(Color.blue);
		t3.setBackground(Color.WHITE);
		t3.setFont(new java.awt.Font("Arial", 0, 22));
		t3.setEditable(false);
		scroll3 = new JScrollPane(t3);
		scroll3.setBounds(50, 450, 500, 300);
	    scroll3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scroll3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);//t.setLineWrap(true);
		
	    j.add(b1);
		j.add(b2);
		j.add(b3);		//sp.add(t);
		j.add(scroll);
		j.add(label);
		j.add(scroll1);
		j.add(label1);
		j.add(scroll2);
		j.add(label2);
		j.add(scroll3);
		j.add(label3);
		this.add(j);
		t1.setText("abuelo ( Z , X ) :- hijo ( Y , Z ) , padre ( Z , X ) .");
		t2.setText("hijo ( A , luis ) .");
		t.setText("hijo ( X , Y  ) .\nhijo ( pepe , luis ) . \nhijo ( jose , luis ) . \nhijo ( luis , felipe ) .\nhijo ( roberto , felipe ) .\npadre ( X , Y  ) .\npadre ( pepe , luis ) .");		
	}
	
	final JFileChooser fc = new JFileChooser();
	public class Oy1 implements ActionListener	{
		@SuppressWarnings({ "unchecked", "static-access" })
		public void actionPerformed( ActionEvent e ){
			//Si "GUARDAR" es el command que le pasamos es porque se habrá pulsado el botón nuevo_bt 
			if ("Compile".equals(e.getActionCommand())){//				label.setBounds(new Rectangle(200,50,400,21));
				//System.out.println("compilado\n||-----\n"+t.getText()+"-----||");				//-------------------------Modificar
				if(t.getText().equals("")){
					c.setTitle("Empty text box");
					JOptionPane.showMessageDialog(c, "You got no Strings on the text field!!!","Are you crazy mf? this shit empty",JOptionPane.ERROR_MESSAGE);
				}else{
					 	p=new Pars(t.getText());
						hechos=(Vector<Base>) p.hechos.clone();
						if (!p.daval()) {//manda codigo y mensaje
							System.out.println("No entro");
							JOptionPane.showMessageDialog(c,"Knowledge base\nLine: "+p.linea_actual+"\nFailure type: "+p.cE+"\nMessage: "+mensaje.elementAt(p.cE)+"\nclose to: "+p.palabra,"Knowledge base Failure",JOptionPane.ERROR_MESSAGE);
						}if(p.daval()) {
							//System.out.println("Si entro");
							ValidaConsulta(t2.getText(),0);
							if(!t1.getText().equals("")) {
								p.reglas(t1.getText());
								if(p.daval()) {
									t3.setText("");
									System.out.println("{{{{{{{{{{{{{{{{{{{{{");
									esBaseComprobar();
									
								}else {
									JOptionPane.showMessageDialog(c,"Line: "+p.linea_actual+"\nFailure type: "+p.cEr+"\nMessage: "+mensaje.elementAt(p.cEr)+"\nclose to: "+p.palabra,"Rules \n Failure",JOptionPane.ERROR_MESSAGE);

								}
							}
							
						}
				}
			} 
			if ("Load file".equals(e.getActionCommand())){
				abrirArchivo();
				//System.out.println("Load file");
			} 
			if ("Exit".equals(e.getActionCommand())){
				//System.out.println("Exit");
				System.exit(0);
			} 
		}
	}
	StringTokenizer tk;
	String tok;
	private void ValidaConsulta(String string, int descicion) {
		//System.out.println(string);
		tk=new StringTokenizer(string," ");
		tok=tk.nextToken();
		System.out.println(tok);
		String nombre=tok;
		if(existeHecho(nombre)) {
			Vector <Parametro> parametros=validaParametros(tok,descicion);
			for (int i = 0; i < parametros.size(); i++) {
				System.out.println(parametros.elementAt(i).toString());
			}
			ConsultaUsuario=new Consulta(tok,parametros);
			
		}else {
			fin(10);
			System.out.println("no existe");
		}
		
		
		
		
	}
	
	
	
	
	void fin(int codigo) {
		valida=false;
		JOptionPane.showMessageDialog(c,"\nFailure type: "+codigo+"\nMessage: "+mensaje.elementAt(codigo),"Failure on the query",JOptionPane.ERROR_MESSAGE);
	}
	boolean esBaseComprobar(){
		
		for (int i = 0; i < p.hechos.size(); i++) {
			boolean a=false,b=false;
			if(ConsultaUsuario.nombre.equals(p.hechos.elementAt(i).nombre)) {
				System.out.println(p.hechos.elementAt(i).nombre+":hecho\t|\t"+ConsultaUsuario.nombre+":consulta");
				//recorrer los parametros 
				System.out.println("---------------------------------------------");
				System.out.println();System.out.println();
				System.out.println("El valor a comparar "+p.hechos.elementAt(i).parametros.elementAt(0) );
				System.out.print(ConsultaUsuario.parametros.elementAt(0).nombre+" bbbbbb ");
				System.out.println(ConsultaUsuario.parametros.elementAt(0).valor);
				
					
				if(ConsultaUsuario.parametros.elementAt(0).valor.equals(p.hechos.elementAt(i).parametros.elementAt(0))) {
					System.out.println("Existe en A");
					a=true;
				}
				
				System.out.println("El valor a comparar "+p.hechos.elementAt(i).parametros.elementAt(1) );
				System.out.print(ConsultaUsuario.parametros.elementAt(1).nombre+"  ");
				System.out.println(ConsultaUsuario.parametros.elementAt(1).valor);
				if(ConsultaUsuario.parametros.elementAt(1).valor.equals(p.hechos.elementAt(i).parametros.elementAt(1))) {
					System.out.println("Existe en B");
					b=true;
					
				}
				if(ConsultaUsuario.parametros.elementAt(0).valor.equals("_")&&ConsultaUsuario.parametros.elementAt(1).valor.equals("_")) {
					//t3.getText()+""+ConsultaUsuario.parametros.elementAt(0).nombre+" = "+ ConsultaUsuario.parametros.elementAt(0).valor);
					
					if(i!=0) {
					t3.setText(t3.getText()+ConsultaUsuario.parametros.elementAt(0).nombre+"= "+p.hechos.elementAt(i).parametros.elementAt(0)+"\n");

					t3.setText(t3.getText()+ConsultaUsuario.parametros.elementAt(1).nombre+" = "+p.hechos.elementAt(i).parametros.elementAt(1)+"\n\n");
					//t3.setText(t3.getText()+"\n"+ConsultaUsuario.parametros.elementAt(1).nombre+"= "+ConsultaUsuario.parametros.elementAt(1).valor+"\n\n");
					}else {
						t3.setText("");
					}

				}else
				if (a&&b) {
					t3.setText("");
					t3.setText(""+(a==b));
					i= p.hechos.size()+1;
					
				}else {
				
					if(a&&ConsultaUsuario.parametros.elementAt(1).valor.equals("_")) {
						t3.setText(t3.getText()+""+ConsultaUsuario.parametros.elementAt(0).nombre+" = "+ ConsultaUsuario.parametros.elementAt(0).valor);
						t3.setText(t3.getText()+"\n"+ConsultaUsuario.parametros.elementAt(1).nombre+" = "+p.hechos.elementAt(i).parametros.elementAt(1));
						
					}else
					if(b&&ConsultaUsuario.parametros.elementAt(0).valor.equals("_")) {
						t3.setText(t3.getText()+ConsultaUsuario.parametros.elementAt(0).nombre+"= "+p.hechos.elementAt(i).parametros.elementAt(0));
						t3.setText(t3.getText()+"\n"+ConsultaUsuario.parametros.elementAt(1).nombre+"= "+ConsultaUsuario.parametros.elementAt(1).valor+"\n\n");
						
					}
				}
			
			}
		}
		if (t3.getText().equals("")) {
			t3.setText(""+false);
		}
		System.out.println();
		return true;
	}
	
	
	
	
	
	
	
	Vector<Parametro> validaParametros(String tok, int descicion){
		Vector<Parametro> para = new Vector<Parametro>();
		tok=tk.nextToken();
		//System.out.println(tok);
		
		if(tok.equals("(")) {
			int con =0;
			Parametro para1 ;
			do {
				tok=tk.nextToken();
				
				if(tok.equals(",")||tok.equals(")")) {
					//System.out.println("Es coma y no se guarda");
					
				}
				else
				{
					System.out.println(tok+"-.-.-."+con);
					Character c =tok.charAt(0);
// 					System.out.println(c.toUpperCase(c));
					if (c.equals(c.toUpperCase(c))) {
						
						System.out.println("Es variable");
						para1=new Parametro(p.abc[con],"_");
						para.add(para1);
						con++;
					}else {
						
						para1=new Parametro(p.abc[con],tok);
						para.add(para1);
						con++;
					}
				}
				
			}while(!tok.equals(")"));
			if(tok.equals(")")) {
				//System.out.println(tok);
				tok=tk.nextToken( );
				if (tok.equals(".")) {
					//System.out.println("punto");
				}
			}
			
		}
		return para;
	}
	
	
	boolean existeHecho(String tk){
		boolean x = false;
		for (int i = 0; i < hechos.size(); i++) {
			System.out.println(hechos.elementAt(i).nombre);
			if(hechos.elementAt(i).nombre.equals(tk)) {
				i=hechos.size()+1;
				x=true;
			}
		}
		return x;
	}
	private String abrirArchivo() {
		
		String aux="";   
		String texto="";
		try {
			/**llamamos el metodo que permite cargar la ventana*/
			JFileChooser file=new JFileChooser();
			file.showOpenDialog(this);
			/**abrimos el archivo seleccionado*/
			abre=file.getSelectedFile();
			c.setTitle("Trying to compile "+abre.getName());
			/**recorremos el archivo, lo leemos para plasmarlo
			*en el area de texto*/
			 
			if(abre!=null){  
				FileReader archivos=new FileReader(abre);
				BufferedReader lee=new BufferedReader(archivos);
				while((aux=lee.readLine())!=null){
					texto+= aux+ "\n";
			        t.setText(texto);
				}
			    lee.close();
			}    
		} catch(Exception ex){
			JOptionPane.showMessageDialog(c,"MF there's no fucking file you dumbass",
		    "Warning!!!",JOptionPane.WARNING_MESSAGE);
		}
		return texto;//El texto se almacena en el JTextArea
	}
	public static void main(String[] args) {
		
		
		do {
			try {
				c=new Compilador();
			} catch (Exception e) {
				  JOptionPane.showMessageDialog(null,e+"" +
						     "\nError",
						     "Check your code!!!",JOptionPane.WARNING_MESSAGE);
			}
			
		}while(valida);
	}
}

