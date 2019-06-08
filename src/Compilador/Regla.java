package Compilador;

import java.util.Vector;

public class Regla {
	Vector<Parametro> parame;
	Vector<Consulta> consultas;
	String nombre;
	Vector <Base> hechos;
	@SuppressWarnings("unchecked")
	Regla(String nombre,Vector <Parametro> parametros){
		this.nombre=nombre;
		this.parame=(Vector<Parametro>) parametros.clone();
	}


	@SuppressWarnings("unchecked")
	void setConsultas( Vector<Consulta> consultas ) {
		this.consultas=(Vector<Consulta>) ((consultas.clone()));
	}
	
}
