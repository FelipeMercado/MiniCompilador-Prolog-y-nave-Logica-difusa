package Compilador;

public class Parametro {

	String valor,nombre;
	Parametro (String nombre,String valor){
		this.valor=valor;
		this.nombre=nombre;
	}
	 
	public String toString() {
		return valor+"....."+ nombre;
	}
}
