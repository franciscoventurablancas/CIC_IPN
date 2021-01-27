/*Alumno Francisco Ventura Blancas
*
*/
public class Persona {
	
	private String matricula;
	private String nombre;
	private int edad;
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	Persona(String matricula,String nombre, int edad)
	{
		this.edad = edad;
		this.matricula = matricula;
		this.nombre = nombre;
	}
	
	public String toString() {
		return "\n Nombre [nombre=" + getNombre() + ", matricula=" + getMatricula() + ", Edad="
				+ getEdad() + "]";
	}

}
