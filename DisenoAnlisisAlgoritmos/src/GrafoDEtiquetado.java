import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

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
	private Vertice[] nodes;
	
	Dictionary<E, Integer> dicVertices;
	private int numeroVertices; //número de vértices del grafo
    private int numeroAristas;  //número de aristas únicas del grafo
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
    
    /*El grafo en sí es un mapa Hash. Toma como llave el vértice, que es mapeado
    a un conjunto Hash de vértices con los cuales hay conexión.*/
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
    	
    	 //Distancia L2 entre vértices para el modelo geofráfico
        
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
            /*Se recuperan los vértices de los índices i y j*/
             Vertice n1 = this.getNode(i);
             Vertice n2 = this.getNode(j);
             /*Se recuperan las aristas de cada vértice*/
             HashSet<Vertice> aristas1 = this.getEdges(i);
             HashSet<Vertice> aristas2 = this.getEdges(j);

             /*Se agregan los vértices al conjunto del otro*/
             aristas1.add(n2);
             aristas2.add(n1);  //en Grafos dirigidos hay que quitar esta línea
             this.numeroAristas +=1; //Para que sean aristas únicas (en lugar de 2)
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
    	 /*Constructor usado para el modelo geofráfico simple.
        Se le tienen que pasar el número de vértices y la cadena "geo-uniforme"*/
       
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
			 
			 public Grafo BFS(Grafo g,int s) {
				 GrafoDEtiquetado arbol = (GrafoDEtiquetado) g;  // grafo de salida
			      
			   
			        Boolean[] discovered = new Boolean[numVertices()];  // arreglo aux
			        PriorityQueue<Integer> L = new PriorityQueue<Integer>();
			        discovered[s] = true;  // se pone como descubierto el vértice raíz
			        for (int i = 0; i < numVertices(); i++) {
			          if (i != s) {   // el resto como no descubiertos
			            discovered[i] = false;
			          }
			        }
			        L.add(s);  // Se agrega a la cola de prioridad el nodo raíz
			        while (L.peek() != null) {  // se revisa que no esté vacía la cola
			          int u = L.poll();  // se extrae un elemento de la cola
			          HashSet<Vertice> aristas = this.getEdges(u);  // aristas del nodo u
			          for (Vertice n : aristas) {
			            if(!discovered[n.getIndex()]) {
			              // si no está descubierto, conectarlo, marcarlo como descubierto
			              // y agregarlo a la cola.
			              conectarVertices(u, n.getIndex());
			              discovered[n.getIndex()] = true;
			              L.add(n.getIndex());
			            }
			          }
			        }
			        return arbol;
			      }
			 /* Método para generar el árbol DFS del Grafo de forma recursiva  */
			  /* Regresa otro grafo. Solo toma como entrada el número de un nodo*/
			  public Grafo DFS_R(int s) {
			  Grafo arbol = new GrafoDEtiquetado(numVertices());  // grafo de salida
			  Boolean[] discovered = new Boolean[numVertices()];  // arreglo aux
			  for (int i = 0; i < numVertices(); i++) {
			    discovered[i] = false;  // se marcan todos como no decubiertos
			  }
			  // se manda a llamar a la función recursiva de DFS
			  recursivoDFS(s, discovered, arbol);
			  return arbol;
			}

			  private void recursivoDFS(int u, Boolean[] discovered, Grafo arbol) {
			  discovered[u] = true;  // vértice con el que se llamó se marca
			  // aristas del vértice u, con el que se llamó el método
			  HashSet<Vertice> aristas = this.getEdges(u);
			  for (Vertice n : aristas) {
			      if (!discovered[n.getIndex()]) {
			        // si no está descubierto, conectar el vértice
			        // y mandar a llamar recursivamente el método con este nuevo vértice
			        conectarVertices(u, n.getIndex());
			        recursivoDFS(n.getIndex(), discovered, arbol);
			        }
			      }
			    }

			  /* Método para generar el árbol DFS del Grafo de forma iterativa  */
			/* Regresa otro grafo. Solo toma como entrada el número de un nodo*/
			  public Grafo DFS_I(int s) {
			  Grafo arbol = new GrafoDEtiquetado(numVertices());  // grafo de salida
			  Boolean[] explored = new Boolean[numVertices()];  // arreglo aux
			  Stack<Integer> S = new Stack<Integer>(); //pila para los vértices
			  Integer[] parent = new Integer[numVertices()]; //arreglo de padres
			  for (int i = 0; i < numVertices(); i++) {
			      explored[i] = false;  //se ponen todos los vértices como no explorados
			    }
			  S.push(s);  //se manda a la pila al nodo raíz
			  while(!S.isEmpty()) {
			    // mientras la pila no esté vacía
			    int u = S.pop();  // se extraen elementos de la pila
			    if(!explored[u]) {
			      explored[u] = true;  // si aún no estaban explorados se marcan como tal
			      if(u != s) {
			        conectarVertices(u, parent[u]); //se conecta con su padre
			      }
			      HashSet<Vertice> aristas = this.getEdges(u);  // aristas del nodo u
			      for (Vertice n : aristas) {
			        S.push(n.getIndex());  // a la pila los vértices conectados con u
			        parent[n.getIndex()] = u;  // se les marca como padre a u
			        }
			      }
			    }
			  return arbol;
			  }
}
