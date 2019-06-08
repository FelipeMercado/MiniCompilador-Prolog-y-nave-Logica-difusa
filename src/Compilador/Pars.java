package Compilador;
//import java.nio.file.attribute.FileOwnerAttributeView;
//import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Vector;
public class Pars {
	String mensaje,auxiliar,auxiliar2,estructura,error;
	boolean valida=true;
	boolean dec=true;
	
	String g ;
	Vector<String> table=new Vector<String>();
	Vector<String> token=new Vector<String>();
	Vector<Tok> tokin=new Vector<Tok>();
	StringTokenizer tk ,tk2;
	
	int c,z=-1,ins=0 ,xero;
	Tabla tab;
	ArbolRecur arbol=new ArbolRecur();
	Nodo2 n;
	Nodo2 declar;
	Nodo2 if3;
	//Vector<String> base=new Vector<String>();
	
	
	
	//aqui declaro lo que quiero
	static int linea_actual, primera_vez,cE=-1;
	static String linea,palabra,tok;
	static boolean primero=true;
	String [] abc= {"A","B","C","D","E","G","H","I","J"};
	Vector<Regla> reglas=new Vector<Regla>();
	Vector<Base>  hechos=new Vector<Base>();
	Consulta consultaUsuario ; 
	
	
	//--------------------------------------------Código reglas
	int cEr; String token1;
	public void finR(int codigo,String tok) {
		cEr = codigo;
		valida=false;
		token1=tok;
		tk2=new StringTokenizer("","\n");
		
//		System.out.println("Linea "+(linea_actual+1)+"\t"+cE+"\t"+palabra);
	}
	
	void reglas(String regla) {
		tk2=new StringTokenizer(regla," ");
		//linea=tk.nextToken();
//		System.out.println("xsxsxsx");
//		System.out.println(regla);
		tok=tk2.nextToken();
		try {
		ValidaRegla();
		}catch (Exception e) {
			finR(1,"___");
			
		}
	}
	@SuppressWarnings({ "unused", "static-access" })
	void ValidaRegla(){
		Vector<Parametro> parame= new Vector<Parametro>();
		Regla regla1;
		Parametro para ;
//		System.out.println(tok);
		String nombre=tok;//nombre regla
		tok=tk2.nextToken();
//		System.out.println(tok);
		if(tok.equals("(")) {
			tok=tk2.nextToken();
			int ind=0;
			do {
				//System.out.println(tok);
				if(tok.equals(",")) {
					tok=tk2.nextToken();
				}else {
					para=new Parametro(abc[ind],tok);
//					System.out.println(para.nombre+"__"+para.valor);
					parame.add(para);
					ind++;
					tok=tk2.nextToken();
				}
			}while(!tok.equals(")")&&!tok.equals("."));
			if(tok.equals(")")) {
				tok=tk2.nextToken();
//				System.out.println(tok);
				//si se crea la regla se pasa 
//				System.out.println();
//				System.out.println(tk2.hasMoreElements());
				regla1= new Regla (nombre,parame);
//				System.out.println("si se guarda");
				reglas.add(new Regla(nombre,parame));
				Vector <Consulta> consultas=new Vector<Consulta>();
				if(tok.equals(":-")) {
					try {
					do {
						
						tok=tk2.nextToken();
//						System.out.println(tok);
						String nombre1=tok;
						if (existeBR(tok)) {
							nombre=tok;
							Vector<Parametro> parame1 =new Vector<Parametro>();
//							System.out.print("------");
							tok=tk2.nextToken();
							if (tok.equals("(")) {
								Parametro p1;
//								System.out.println("Par abre"+ tok);
								tok=tk2.nextToken();
								
								do {
									boolean val = false;
									if(tok.equals(")")) {
										finR(5,tok);
									}else
									if(tok.equals("(")) {
										finR(4,tok);
									}else
									if(tok.equals(",")) {
										tok=tk2.nextToken();
									}else {
										for (int i = 0; i <reglas.lastElement().parame.size(); i++) {
//											System.out.println(reglas.lastElement().parame.elementAt(i).nombre+"{{}}"+reglas.lastElement().parame.elementAt(i).valor);
											//if (tok.equals(tok.toLowerCase())) {}//
											if (tok.equals(reglas.lastElement().parame.elementAt(i).valor)) {
//												System.out.println(reglas.lastElement().parame.elementAt(i).valor+ "|||||||"+tok);
												p1=new Parametro(reglas.lastElement().parame.elementAt(i).nombre,tok);
												val=true;

												Character c =tok.charAt(0);
//												System.out.println(c.toUpperCase(c));
												if (c.equals(c.toUpperCase(c))) {
												i=reglas.lastElement().parame.size()+1;
												parame1.add(p1);
												}
											}
										}
										if(val==false) {
											
											Character c =tok.charAt(0);
											System.out.println(c.toUpperCase(c));
											if (c.equals(c.toUpperCase(c))) {
//												System.out.println("si");
												p1=new Parametro(tok,"_");
												parame1.add(p1);
											}else
											{
												finR(1,tok);
											}
											
										}
										
//										System.out.println(parame1.lastElement().nombre+"  v "+parame1.lastElement().valor);
//										System.out.println(tok+"----------variable");
										tok=tk2.nextToken();
									}
//									System.out.println();System.out.println();System.out.println();
								}while(!tok.equals(")"));
								if (tok.equals(")")) {
//									System.out.println("Cierra "+ tok);
								}
								
									
								
								
							}
							//guardas los parametros y luego añade a el vector de consultas y ñluego se añade a la regla que le toca
//							System.out.println("long de "+ parame1.size());
							Consulta consulta =new Consulta (nombre,parame1);
							consultas.add(consulta);
							reglas.lastElement().setConsultas(consultas);
							
						}else{
							//finaliza el existe
							finR(11,nombre);
						}
					}while(!tk2.nextToken().equals("."));
//					System.out.println(tok+"------"+tk2.countTokens());
					}catch (Exception e) {
//						System.out.println("que verga");
					}
				}
				
				
//				System.out.println("Aqui debehacer akgo");
//				System.out.println("-.......................................................");
//				System.out.println();System.out.println();System.out.println();
//				for (int i = 0; i < reglas.size(); i++) {
//					for (int j = 0; j <  reglas.elementAt(i).consultas.size(); j++) {
//						System.out.println(reglas.elementAt(i).consultas.elementAt(j).nombre);
//						for (int j2 = 0; j2 < reglas.elementAt(i).consultas.elementAt(j).parametros.size(); j2++) {
//							System.out.print(reglas.elementAt(i).consultas.elementAt(j).parametros.elementAt(j2).nombre);
//							System.out.print(reglas.elementAt(i).consultas.elementAt(j).parametros.elementAt(j2).valor );
//							System.out.println();
//						}
//					}
//				}
			}else 
			{
				finR(8,tok);
			}
		}
	}
	boolean existeBR(String nombre){
		boolean x=false;
		for (int i = 0; i < hechos.size(); i++) {
			//System.out.println(hechos.elementAt(i).nombre+"___"+Arrays.asList(hechos.elementAt(i).parametros));
			if (hechos.elementAt(i).nombre.equals(nombre)) {
					x=true;
			}
		}
		return x;
	}
	
	
	//--------------------------------------------------Código hechos
	
	
	public Pars(String g) {
	reglas.clear();
	hechos.clear();
	linea_actual=0;
	cE=0;
	this.g=g;
	tk=new StringTokenizer(g,"\n");
	try {
		do {
			//
			linea=tk.nextToken();
			validaBase( ); 
			
			linea_actual++;
	 
			}while(tk.countTokens()>=0 || !daval() ||tk.hasMoreTokens());
			validaBase( ); 
			
			for (int i = 0; i < hechos.size(); i++) {
				//System.out.println(hechos.elementAt(i).nombre+"___"+Arrays.asList(hechos.elementAt(i).parametros));
			}
		}catch(Exception e) {
			
		}
			
		}
	public void fin(int codigo) {
		cE=codigo;
		valida=false;
		tk=new StringTokenizer("","\n");
//		System.out.println("Linea "+(linea_actual+1)+"\t"+cE+"\t"+palabra);
	}
	
	
	
	void  validaBase(  ) {
		tk2=new StringTokenizer(linea," ");
//		System.out.println("Elementos en hechos"+ hechos.size());
		//System.out.println("----------------------");
		String tipo;
		next();
		
		if(!existeHecho()) {
			tipo=palabra;
			//System.out.println(palabra);
			next();
			Vector<String> parametros= validaParametros();
			
			if(!sonVariables(parametros)) {
//				System.out.println("Aqui valio madre ");
				fin(1);
			}else{
				//añade el primer el primer hecho 
				Base b=new Base (tipo,parametros);
				hechos.add(b);
			}
		
		}
		if( existeHecho()) {
			tipo=palabra;
			//System.out.println(palabra);
			next();
			Vector<String> parametros= validaParametros();
			 
			if( !sonDatos(parametros)) {
				
				fin(3);
			
			}else {
				
				Base b=new Base (tipo,parametros);
				 
				if (corresponde(b) ) {
					if(!existe(b)){
						hechos.add(b);
						//System.out.println("Se agrego");
					}else {
						//System.out.println("esta repetido");
						fin(7);
					}
				}else {
					fin(2);
					//System.out.println("Nel alv");
				}
			}
		
		}
		 
			
	}
	boolean existe(Base b){
		boolean x=false;
		for (int i = 0; i < hechos.size(); i++) {
			//System.out.println(hechos.elementAt(i).nombre+"___"+Arrays.asList(hechos.elementAt(i).parametros));
			if (hechos.elementAt(i).nombre.equals(b.nombre)) {
				if (hechos.elementAt(i).parametros.equals(b.parametros)) {
					x=true;
				}
			}
		}
		return x;
	}
	@SuppressWarnings("static-access")
	boolean sonVariables(Vector <String>parametros) {
		boolean x = true;
		for (int i = 0; i < parametros.size(); i++) {
			Character c =parametros.elementAt(i).charAt(0);
			//System.out.println(c.toUpperCase(c));
			if (!c.equals(c.toUpperCase(c))) {
				x = false;
				i=parametros.size()+1;
			}
			  
		}
		return x;
	}
	@SuppressWarnings("static-access")
	boolean sonDatos(Vector <String>parametros) {
		boolean x = true;
		for (int i = 0; i < parametros.size(); i++) {
			Character c =parametros.elementAt(i).charAt(0);
			//System.out.println(c.toUpperCase(c));
			if (c.equals(c.toUpperCase(c))) {
				x = false;
				i=parametros.size()+1;
			}
			  
		}
		return x;
	}
	boolean corresponde (Base b){
		boolean x = false;
		for (int i = 0; i < hechos.size(); i++) {
			if(hechos.elementAt(i).nombre.equals(b.nombre)&&hechos.elementAt(i).parametros.size()==b.parametros.size()) {
				
				i=hechos.size()+1;
				x=true;
				
			}
		}
		return x;
	}
	boolean existeHecho(){
		boolean x = false;
		for (int i = 0; i < hechos.size(); i++) {
			if(hechos.elementAt(i).nombre.equals(palabra)) {
				
				i=hechos.size()+1;
				x=true;
				
			}
		}
		return x;
	}
	Vector<String> validaParametros(){
		Vector<String> para = new Vector<String>();
		if(palabra.equals("(")) {
			next();
			do {
				 
				 if (!palabra.equals(",") ) {
					// System.out.println(palabra);
					para.add(palabra);
				 }
				 
				 next();
			}while(!palabra.equals(")")); 
			if(!palabra.equals(")")) {
				fin(5);
			}
				
		//System.out.println("aQUI CUERRA");
			next();
			if(palabra.equals(".")) {
				//System.out.println("vas bie n");
				
			}else fin(6);
		}else fin(4);
		return para;
	}
	void next(){
		c++;
		palabra=tk2.nextToken();
	 	//System.out.println((c)+"\t-- "+palabra);
	}
	boolean daval(){
 		return valida;
 	}
	public String getMensaje() {
		return  "Linea "+(linea_actual+1)+"\t"+cE+"\t"+palabra;
	}

}



