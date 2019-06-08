package Compilador;

import java.util.Vector;

public class Base {
	String nombre;
	Vector<String> parametros;
	@SuppressWarnings("unchecked")
	Base(String nombre , Vector<String> parametros){
		this.nombre=nombre;
		this.parametros=(Vector<String>) parametros.clone();
	}
	public String toString (){
		return nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Vector<String> getParametros() {
		return parametros;
	}
	public void setParametros(Vector<String> parametros) {
		this.parametros = parametros;
	}
	 
}
