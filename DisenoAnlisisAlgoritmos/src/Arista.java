/*Alumno Francisco Ventura Blancas
*
*/
public class Arista {
  /* Variables de instancia*/
  private Integer n1;
  private Integer n2;
  private double peso;

  /*Constructor que toma n�umeros de los nodos que conecta y su peso*/
  public Arista(int n1, int n2, double peso) {
    this.n1 = n1;
    this.n2 = n2;
    this.peso = peso;
  }
  public String getNode1() {return "n" + n1.toString();}

  public String getNode2() {return "n" + n2.toString();}

  public int getIntN1() {return n1;}

  public int getIntN2() {return n2;}

  public double getWeight() {return peso;}
}