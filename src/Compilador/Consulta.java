package Compilador;

import java.util.Vector;

public class Consulta{
	String nombre;
	Vector<Parametro>parametros;
	@SuppressWarnings("unchecked")
	public Consulta(String nombre, Vector <Parametro>parametros) {
		this.nombre=nombre;
		this.parametros=(Vector<Parametro>)(parametros.clone());
	}
	

}
