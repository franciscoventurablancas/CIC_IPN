
public class Vertice {
  /* Variables de instancia*/
  private String name;
  private Integer numAristas;
  private Integer index;
  /*Estas variables solo son usadas en el modelo geogr�fico simple
  por lo que solo se usan cuando se llama al constructor correspondiente*/
  private double x;
  private double y;

 
  private double distance;

  /*Constructor que toma una cadena como nombre del v�rtice*/
  public Vertice(String name) {
    this.name = name;
    this.numAristas = 0;
  }

  /*Constructor que toma un entero como argumento. Asigna a la variable
  de instancia 'name' la cadena formada por la letra 'n' concatenada con
  la representaci�n en cadena del n�mero entero que tom� como argumento*/
  public Vertice(int name) {
    this.index = name;
    this.name = "n" + String.valueOf(name);
    this.numAristas = 0;
  }

  /*Constructor usado en el modelo geogr�fico simple. Toma un entero que sirve
  como nombre del v�rtice y dos n�meros de punto flotante como coordenadas
  del v�rtice. El modelo geofr�fico simple toma coordenadas en un cuadrado
  unitario*/
  public Vertice(int name, double x, double y) {
    this.index = name;
    this.name = "n" + String.valueOf(name);
    this.x = x;
    this.y = y;
  }

  /*getters de variables de instancia */

  public String getName() {return name;}

  public Integer getNumEdges() {return numAristas;}

  public Integer getIndex() {return index;}

  public double getDistance() {return distance;}

  public void setDistance(double d) {this.distance = d; }

  /*Variables de instancia usadas en el modelo geogr�fico simple*/
  public double getX() {return x;}

  public double getY() {return y;}
}
