/*Alumno Francisco Ventura Blancas
*
*/
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

/*
 * Un Grafo No Dirigido (GND) es un Par G = (V,E)
 * V es un conjunto finito de V�rtices
 * E es un conjunto de Aristas no Dirigidas
 * Arista: Par no ordenado de V�rtices (u,v) = (v,u)
 * 
 */
public class GrafoNoDirigido  extends Grafo {
	
	protected int numV, numA;
	private int numeroAristas; 
	private Vertice[] nodes;
    protected ListaConPI<Adyacente>[] elArray;
    private HashMap<Vertice, HashSet<Vertice>> graph;
    private List<Adyacente> l;
    public Vertice getNode(int i) {return this.nodes[i];}
    /** Construye un grafo Dirigido vacio con numVertices.
     *  @param numVertices  Numero de vertices del grafo vacio
     */
    @SuppressWarnings("unchecked")
    public GrafoNoDirigido(int numVertices)
    {
    	numV=numVertices;
    	 this.graph = new HashMap<Vertice, HashSet<Vertice>>();
    	    
    	    this.numV = numVertices;
    	    this.nodes = new Vertice[numVertices];
    	    for (int i = 0; i < numVertices; i++) {
    	      Vertice n = new Vertice(i);
    	      this.nodes[i] = n;
    	      this.graph.put(n, new HashSet<Vertice>());
    	      
    	    }
    	      
    	    }
    	
    	
    	
    
    public void modeloBA(int d) {
        Random volado = new Random();
      /*Para los primeros d v�rtices, se conecta el v�rtice i con el v�rtice j
      con i distinto de j y recorriendo todos los v�rtices.*/
        for(int i = 0; i < d; i++){
          for(int j = 0; j < i; j++) {
            if (!existeConexion(i, j)) {
              conectarVertices(i, j);
            }
          }
        }
    }
    
    public void insertarArista(int i, int j, int p) {
    	if ( !existeArista(i,j) && i != j) {
    	l.add(new Adyacente(j,p));
    	l.add(new Adyacente(i,p));
    	}
    	numA++;
    }
    //Regresa 'true' si ya existe la arista
    private Boolean existeConexion(int i, int j) {
      /*Se recuperan los v�rtices de los �ndices i y j*/
       Vertice n1 = this.getNode(i);
       Vertice n2 = this.getNode(j);
      /*Se recuperan las aristas de cada v�rtice*/
      HashSet<Vertice> aristas1 = this.getEdges(i);
      HashSet<Vertice> aristas2 = this.getEdges(j);
      /*Se revisa que un nodo est� en el conjunto de aristas del otro*/
       if (aristas1.contains(n2) || aristas2.contains(n1)) {
         return true;
       }
       else{
         return false;
       }
    }
  //Conecta dos v�rtices
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

    public HashSet<Vertice> getEdges(int i) {
        Vertice n = this.getNode(i);
        return this.graph.get(n);
      }
        
    
    
    
	@Override
	public int numVertices() {
		// TODO Auto-generated method stub
		return numV;
	}


	@Override
	public int numAristas() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean existeArista(int i, int j) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public double pesoArista(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void insertarArista(int i, int j) {
		// TODO Auto-generated method stub
	
		
	}
	public int gradoVertice(int i) {
	    Vertice n1 = this.getNode(i);
	    return this.graph.get(n1).size();
	  }

	
	 public ListaConPI<Adyacente> adyacentesDe(int i) {
	        return elArray[i];
	    }


	@Override
	public void insertarArista(int i, int j, double p) {
		// TODO Auto-generated method stub
		
	}
	public String toString() {
	    String salida;
	     salida ="graph {\n";
	      
	      for (int i = 0; i <numVertices(); i++) {
	        HashSet<Vertice> aristas = this.getEdges(i);
	        for (Vertice n : aristas) {
	        salida += this.getNode(i).getName() + " - " + n.getName() + ";\n";
	        }
	       }
	      salida += "}\n";
	  
	    return salida;
	  }
	
	
	 public Grafo BFS(Grafo g,int s) {
	        	        
	        GrafoNoDirigido arbol = (GrafoNoDirigido) g;  // grafo de salida
	        Boolean[] discovered = new Boolean[numVertices()];  // arreglo aux
	        PriorityQueue<Integer> L = new PriorityQueue<Integer>();
	        discovered[s] = true;  // se pone como descubierto el v�rtice ra�z
	        for (int i = 0; i < numVertices(); i++) {
	          if (i != s) {   // el resto como no descubiertos
	            discovered[i] = false;
	          }
	        }
	        L.add(s);  // Se agrega a la cola de prioridad el nodo ra�z
	        
	        
	   
	        
	        
	        
	        
	        
	        
	        
	        while (L.peek() != null) {  // se revisa que no est� vac�a la cola
	          int u = L.poll();  // se extrae un elemento de la cola
	          HashSet<Vertice> aristas = this.getEdges(u);  // aristas del nodo u
	          for (Vertice n : aristas) {
	            if(!discovered[n.getIndex()]) {
	              // si no est� descubierto, conectarlo, marcarlo como descubierto
	              // y agregarlo a la cola.
	              conectarVertices(u, n.getIndex());
	              discovered[n.getIndex()] = true;
	              L.add(n.getIndex());
	            }
	          }
	        }
	        return arbol;
	      }
	 /* M�todo para generar el �rbol DFS del Grafo de forma recursiva  */
	  /* Regresa otro grafo. Solo toma como entrada el n�mero de un nodo*/
	  public Grafo DFS_R(int s) {
	  Grafo arbol = new GrafoNoDirigido(numVertices());  // grafo de salida
	  Boolean[] discovered = new Boolean[numVertices()];  // arreglo aux
	  for (int i = 0; i < numVertices(); i++) {
	    discovered[i] = false;  // se marcan todos como no decubiertos
	  }
	  // se manda a llamar a la funci�n recursiva de DFS
	  recursivoDFS(s, discovered, arbol);
	  return arbol;
	}

	  private void recursivoDFS(int u, Boolean[] discovered, Grafo arbol) {
	  discovered[u] = true;  // v�rtice con el que se llam� se marca
	  // aristas del v�rtice u, con el que se llam� el m�todo
	  HashSet<Vertice> aristas = this.getEdges(u);
	  for (Vertice n : aristas) {
	      if (!discovered[n.getIndex()]) {
	        // si no est� descubierto, conectar el v�rtice
	        // y mandar a llamar recursivamente el m�todo con este nuevo v�rtice
	        conectarVertices(u, n.getIndex());
	        recursivoDFS(n.getIndex(), discovered, arbol);
	        }
	      }
	    }

	  /* M�todo para generar el �rbol DFS del Grafo de forma iterativa  */
	/* Regresa otro grafo. Solo toma como entrada el n�mero de un nodo*/
	  public Grafo DFS_I(int s) {
	  Grafo arbol = new GrafoNoDirigido(numVertices());  // grafo de salida
	  Boolean[] explored = new Boolean[numVertices()];  // arreglo aux
	  Stack<Integer> S = new Stack<Integer>(); //pila para los v�rtices
	  Integer[] parent = new Integer[numVertices()]; //arreglo de padres
	  for (int i = 0; i < numVertices(); i++) {
	      explored[i] = false;  //se ponen todos los v�rtices como no explorados
	    }
	  S.push(s);  //se manda a la pila al nodo ra�z
	  while(!S.isEmpty()) {
	    // mientras la pila no est� vac�a
	    int u = S.pop();  // se extraen elementos de la pila
	    if(!explored[u]) {
	      explored[u] = true;  // si a�n no estaban explorados se marcan como tal
	      if(u != s) {
	       conectarVertices(u, parent[u]); //se conecta con su padre
	      }
	      HashSet<Vertice> aristas = this.getEdges(u);  // aristas del nodo u
	      for (Vertice n : aristas) {
	        S.push(n.getIndex());  // a la pila los v�rtices conectados con u
	        parent[n.getIndex()] = u;  // se les marca como padre a u
	        }
	      }
	    }
	  return arbol;
	  }

}
