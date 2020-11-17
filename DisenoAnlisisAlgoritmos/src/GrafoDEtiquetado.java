import java.util.Dictionary;
import java.util.Hashtable;

/*
 * Un Grafo Etiquetado es un grafo G = (V,E) sobre el
 * que se define una función f: E -> A, dónde A es un
 * conjunto cuyas componentes se llaman Etiquetas.
 * Un Grafo Ponderado es un Grafo Etiquetado (sus
 * Aristas) con números Reales.
 * También es posible definir la función de etiquetado para
 * los Vértices, con lo que podemos asignar un nombre a
 * cada Vértice
 * 
 **/
public class GrafoDEtiquetado<E> extends Grafo {

	E etiquetas[];
	Dictionary<E, Integer> dicVertices;
	

    /** Construye un grafo Dirigido vacio con numVertices.
     *  @param numVertices  Numero de vertices del grafo vacio
     */
    @SuppressWarnings("unchecked")
	public GrafoDEtiquetado(int numVertices) {
		super();
		etiquetas = (E[]) new Object[numVertices+1];
		dicVertices = new Hashtable<E, Integer>(numVertices);
		}
    public boolean existeArista(E i, E j) {
    	return existeArista(obtenerCodigo(i), obtenerCodigo(j));
    	}
    	public double pesoArista(E i, E j) {
    	return pesoArista(obtenerCodigo(i), obtenerCodigo(j));
    	}
    	public void insertarArista(E i, E j) {
    	insertarArista(obtenerCodigo(i), obtenerCodigo(j));
    	}
    	public void insertarArista(E i, E j, double p) {
    	insertarArista(obtenerCodigo(i), obtenerCodigo(j), p);
    	}
    	public ListaConPI<Adyacente> adyacentesDe(E i) {
    	return adyacentesDe(obtenerCodigo(i));
    	}
    	
    	public double pesoArista(int i, int j) {
            
            return 0.0;
        }
    	
    	 public boolean existeArista(int i, int j) {
    	       
    	        
    	        return false;
    	    }
    	 
    	 public void insertarArista(int i, int j) {
    	    	
         }
    	
    	public void etiquetarVertice(int codigo, E etiqueta) {
    		etiquetas[codigo] = etiqueta;
    		dicVertices.put(etiqueta, codigo);
    		}
    		public E obtenerEtiqueta(int codigo) {
    		return etiquetas[codigo];
    		}
    		public int obtenerCodigo(E etiqueta) {
    		int codigo;
    		try {
    		codigo = dicVertices.get(etiqueta).intValue();
    		} catch (Exception e) 
    		
    		{ codigo = -1; }
    		return codigo;
    		}
			@Override
			public int numVertices() {
				// TODO Auto-generated method stub
				return 0;
			}
			@Override
			public int numAristas() {
				// TODO Auto-generated method stub
				return 0;
			}
			@Override
			public void insertarArista(int i, int j, double p) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public ListaConPI<Adyacente> adyacentesDe(int i) {
				// TODO Auto-generated method stub
				return null;
			}
			
			 public String toString() {
			        return "Grafo [nodes=" + dicVertices + "]";
			    }
		
}
