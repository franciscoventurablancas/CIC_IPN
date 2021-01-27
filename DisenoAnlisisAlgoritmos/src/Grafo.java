/*Alumno Francisco Ventura Blancas
*
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public abstract class Grafo {
	
	
		
	
	public Grafo()	{
	}
	
	
	 /** Devuelve el numero de vertices del grafo
     * @return int numero de vertices del grafo
     */	
	
	public abstract int numVertices();
	
	 /** Devuelve el numero de aristas del grafo
     * @return int numero de aristas del grafo
     */
	public abstract int numAristas();
	
	/** Comprueba si la arista (i,j) esta en un grafo.
     * @param i    Vertice origen
     * @param j    Vertice destino
     * @return boolean true si (i,j) este en el grafo y false en caso contrario
     */
	
	public abstract boolean existeArista(int i, int j);
	
	/** Devuelve el peso de la arista (i,j) de un grafo, 0 si dicha arista
     * no esta en el grafo.
     * @param i    Vertice origen
     * @param j    Vertice destino
     * @return double Peso de la arista (i,j), 0 si no existe.
     */
	
	public abstract double pesoArista(int i, int j);
	
	/** Si no esta, inserta la arista (i, j) en un grafo no Ponderado.
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     */
	
	public abstract void insertarArista(int i, int j);
	
	/** Si no esta, inserta la arista (i, j) de peso p en un grafo Ponderado.
     * @param i    Vertice origen
     * @param j    Vertice destino
     * @param p    Peso de la arista (i,j)
   */
	
	public abstract void insertarArista(int i, int j, double p);
	
	/** Devuelve una ListaConPI que contiene los adyacentes al vertice i
     * @param i Vertice del que se obtienen los adyacentes
     * @return ListaConPI con los vertices adyacentes a i
    */
	
	public abstract ListaConPI<Adyacente> adyacentesDe(int i);
	
	/** Devuelve un String con cada uno de los vertices de un grafo y sus
     *  adyacentes, en orden de insercion
     * @return  String que representa a un grafo
     */
	
	public String toString() {
        String res = "graph {";
        
        for (int  i = 0; i < numVertices(); i++) {
        	
        	res +=  "n"+i+";\n ";
        }
        
        
        for (int  i = 0; i < numVertices(); i++) {
        	
            ListaConPI<Adyacente> l = adyacentesDe(i);
           if (l.esVacia()) { res += " "; }
            else { res += " "; }
            for (l.inicio(); !l.esFin(); l.siguiente()) {
            
            	res +=  "n"+i + "-" + "n"+l.recuperar()+";\n ";
            }
            res += " ";
        }
        return res+"}";
    }
	
	
	
	  
	public void EscribeArchivo(String nombreArchivo, String string) {
		// TODO Auto-generated method stub
		Archivo archivo = new Archivo();
		
    	archivo.escribirArchivo(nombreArchivo,string);
	}
	  
	 public Grafo Dijkstra(int s) {
    Grafo arbol = new Grafo(this.getNumNodes()); //grafo de salida
    double inf = Double.POSITIVE_INFINITY;  // máxima distancia
    //arreglo donde se guarda el nodo padre de cada nodo
    Integer[] padres = new Integer[arbol.getNumNodes()];
    // Las distancias de todos los nodos a infinito y todos los padres a null
    for (int i = 0; i < arbol.getNumNodes(); i++) {
      this.getNode(i).setDistance(inf);
      padres[i] = null;
    }
    // la distancia del nodo fuente a 0.0 y a sí mismo como padre
    this.getNode(s).setDistance(0.0);
    padres[s] = s;
    // Cola de prioridad de los nodos. La llave es la distancia
    PriorityQueue<Vertice> distPQ = new PriorityQueue<>(vertexDistanceComp);
    for (int i = 0; i < this.getNumNodes(); i++) {
        distPQ.add(this.getNode(i));
    }
    //Se explora el grafo
    while (distPQ.peek() != null) {  // se revisa que no esté vacía la cola
      Vertice u = distPQ.poll();  // elemento de la cola de prioridad
      //aristas del nodo u
      HashSet<Arista> aristas = this.getWeightedEdges(u.getIndex());
      for (Arista e : aristas) {
        // actualizar padre y distancia si es menor a la que tenía
        if(this.getNode(e.getIntN2()).getDistance() > this.getNode(u.getIndex()).getDistance() + e.getWeight()) {
          this.getNode(e.getIntN2()).setDistance(this.getNode(u.getIndex()).getDistance() + e.getWeight());
          padres[e.getIntN2()] = u.getIndex();
        }
      }
    }
    //se conecta el árbol de salida con la lista de padres y se asigna la
    //distancia al nodo fuente a cada nodo
    for (int i = 0; i < arbol.getNumNodes(); i++) {
      arbol.setAristaPeso(i, padres[i], 1);
      arbol.getNode(i).setDistance(this.getNode(i).getDistance());
    }
    return arbol;
  }
	
		

}
	
	