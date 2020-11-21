import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;

/*
 * Un Grafo Etiquetado es un grafo G = (V,E) sobre el
 * que se define una funci�n f: E -> A, d�nde A es un
 * conjunto cuyas componentes se llaman Etiquetas.
 * Un Grafo Ponderado es un Grafo Etiquetado (sus
 * Aristas) con n�meros Reales.
 * Tambi�n es posible definir la funci�n de etiquetado para
 * los V�rtices, con lo que podemos asignar un nombre a
 * cada V�rtice
 * 
 **/
public class GrafoDEtiquetado<E> extends Grafo {

	E etiquetas[];
	private Vertice[] nodes;
	
	Dictionary<E, Integer> dicVertices;
	private int numeroVertices; //n�mero de v�rtices del grafo
    private int numeroAristas;  //n�mero de aristas �nicas del grafo
    private HashMap<Vertice, HashSet<Arista>> incidencia;

    /** Construye un grafo Dirigido vacio con numeroVertices.
     *  @param numeroVertices  Numero de vertices del grafo vacio
     */
    @SuppressWarnings("unchecked")
	public GrafoDEtiquetado(int numeroVertices) {
		super();
		etiquetas = (E[]) new Object[numeroVertices+1];
		dicVertices = new Hashtable<E, Integer>(numeroVertices);
		
		this.graph = new HashMap<Vertice, HashSet<Vertice>>();
	   
	    this.numeroVertices = numeroVertices;
	    this.nodes = new Vertice[numeroVertices];
	    
	   
	    
		}
    public Vertice getNode(int i) {return this.nodes[i];}
    
    /*El grafo en s� es un mapa Hash. Toma como llave el v�rtice, que es mapeado
    a un conjunto Hash de v�rtices con los cuales hay conexi�n.*/
    private HashMap<Vertice, HashSet<Vertice>> graph;
    
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
    	
    	 //Distancia L2 entre v�rtices para el modelo geofr�fico
        
        public double distanciaVertices(Vertice n1, Vertice n2) {
            return Math.sqrt(Math.pow((n1.getX() - n2.getX()), 2)
            + Math.pow((n1.getY() - n2.getY()), 2));
          }
    	
    	public double pesoArista(int i, int j) {
            
            return 0.0;
        }
    	 public HashSet<Vertice> getEdges(int i) {
    	        Vertice n = this.getNode(i);
    	        return this.graph.get(n);
    	      }
    	
    	public void conectarVertices(int i, int j) {
            /*Se recuperan los v�rtices de los �ndices i y j*/
             Vertice n1 = this.getNode(i);
             Vertice n2 = this.getNode(j);
             /*Se recuperan las aristas de cada v�rtice*/
             HashSet<Vertice> aristas1 = this.getEdges(i);
             HashSet<Vertice> aristas2 = this.getEdges(j);

             /*Se agregan los v�rtices al conjunto del otro*/
             aristas1.add(n2);
             aristas2.add(n1);  //en Grafos dirigidos hay que quitar esta l�nea
             this.numeroAristas +=1; //Para que sean aristas �nicas (en lugar de 2)
          }
    	
    	 public void modeloGeoSimple(int numeroVertices, String modelo) {
         	
    	    	this.graph = new HashMap<Vertice, HashSet<Vertice>>();
    	        this.incidencia = new HashMap<Vertice, HashSet<Arista>>();
    	        this.numeroVertices = numeroVertices;
    	        this.nodes = new Vertice[numeroVertices];
    	        Random coorX = new Random();
    	        Random coorY = new Random();
    	        if (modelo == "geo-uniforme") {
    	          for (int i = 0; i < numeroVertices; i++) {
    	            Vertice n = new Vertice(i, coorX.nextDouble(), coorY.nextDouble());
    	            this.nodes[i] = n;
    	            this.graph.put(n, new HashSet<Vertice>());
    	            this.incidencia.put(n, new HashSet<Arista>());
    	          }
    	        }
    	       
    	      }
    	 public int gradoVertice(int i) {
    		    Vertice n1 = this.getNode(i);
    		    return this.graph.get(n1).size();
    		  }
    	 /*Constructor usado para el modelo geofr�fico simple.
        Se le tienen que pasar el n�mero de v�rtices y la cadena "geo-uniforme"*/
       
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
				return numeroVertices;
			}
			@Override
			public int numAristas() {
				// TODO Auto-generated method stub
				return numeroAristas;
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
			
			public HashSet<Arista> getWeightedEdges(int i) {
			    Vertice n = this.getNode(i);
			    return this.incidencia.get(n);
			  }
			 
			 public String toString() {
				    String salida;
				     salida ="graph {\n";
				      
				      for (int i = 0; i < this.numVertices(); i++) {
				        HashSet<Vertice> aristas = this.getEdges(i);
				        for (Vertice n : aristas) {
				        salida += this.getNode(i).getName() + " - " + n.getName() + ";\n";
				        }
				       }
				      salida += "}\n";
				  
				    return salida;
				  }
		
}
