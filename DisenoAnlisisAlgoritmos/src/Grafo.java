

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
        String res = "";
        for (int  i = 0; i < numVertices(); i++) {
            res += "" + i;
            ListaConPI<Adyacente> l = adyacentesDe(i);
            if (l.esVacia()) { res += ","; }
            else { res += ","; }
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                res +=  l.recuperar() + " ";
            }
            res += "\n";
        }
        return res;
    }
		

}
	
	