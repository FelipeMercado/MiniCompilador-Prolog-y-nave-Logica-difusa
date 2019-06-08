package Compilador;

import java.util.Vector;

public class Resultado {
	boolean estado;
	Vector<Parametro>parametros;
	@SuppressWarnings("unchecked")
	public Resultado(boolean estado) {
		this.estado=estado;
		
	}
	void setParametros(Vector<Parametro>Parametros) {
		this.parametros=(Vector<Parametro>)(parametros.clone());
	}
}
