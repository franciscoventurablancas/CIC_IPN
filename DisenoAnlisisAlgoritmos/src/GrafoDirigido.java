import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

/*
 * Grafo Dirigido
 * Un Grafo Dirigido (GD) es un Par G = (V,E)
 * V es un conjunto finito de V�rtices (o Nodos o Puntos)
 * E es un conjunto de Aristas (o Arcos) dirigidas
 * Arista: Par ordenado de V�rtices (u,v)
 * */


public class GrafoDirigido extends Grafo {

	protected int numV, numA;
    protected ListaConPI<Adyacente>[] elArray;
    /*arreglo de objetos 'Vertice'. De esta forma nos podemos referir a cada
    v�rtice por su posici�n en el arreglo*/
    private Vertice[] nodes;
    /*El grafo en s� es un mapa Hash. Toma como llave el v�rtice, que es mapeado
    a un conjunto Hash de v�rtices con los cuales hay conexi�n.*/
    private HashMap<Vertice, HashSet<Vertice>> graph;
    /*Para los algoritmos de Dijkstra, Kruskal y Prim, se necesitan
    aristas con pesos.*/
    private HashMap<Vertice, HashSet<Arista>> incidencia; //mapa para Dijkstra
    private int numeroVertices; //n�mero de v�rtices del grafo
    private int numeroAristas;  //n�mero de aristas �nicas del grafo
    //private static Formatter output; //objeto para escribir a disco
    private Boolean weighted; //bandera a usar si grafo es pesado
    

    
    public int getNumNodes() {return numeroVertices;}

    public int getNumEdges() {return numeroAristas;}

    public Vertice getNode(int i) {return this.nodes[i];}

    public Boolean getWeightedFlag() {return this.weighted;}


    /** Construye un grafo Dirigido vacio con numVertices.
     *  @param numVertices  Numero de vertices del grafo vacio
     */
    @SuppressWarnings("unchecked")
    public GrafoDirigido(int num_Vertices) {
        this.numeroVertices = 0;
		numV = num_Vertices; numA = 0;
        elArray = new ListaConPI[num_Vertices];
        for (int i = 0; i < numV; i++) {
            elArray[i] = new LEGListaConPI<Adyacente>();
        }
        
        
        this.graph = new HashMap<Vertice, HashSet<Vertice>>();
	    this.incidencia = new HashMap<Vertice, HashSet<Arista>>();
	    this.numeroVertices = numVertices();
	    this.nodes = new Vertice[numVertices()];
	    for (int i = 0; i < numVertices(); i++) {
	      Vertice n = new Vertice(i);
	      this.nodes[i] = n;
	      this.graph.put(n, new HashSet<Vertice>());
	      this.incidencia.put(n, new HashSet<Arista>());
	    }
    }

    /**Devuelve el numero de vertices de un grafo.
     * @return  int Numero de vertices de un grafo
     */
    public int numVertices() { return numV; }

    /**Devuelve el numero de aristas del grafo
      * @return     Numero de aristas del grafo
      */
    public int numAristas() { return numA; }

    /** Comprueba si la arista (i,j) esta en un grafo.
     * @param i    Vertice origen
     * @param j    Vertice destino
     * @return boolean true si (i,j) esta y false en caso contrario
     */
    public boolean existeArista(int i, int j) {
        ListaConPI<Adyacente> l = elArray[i];
        boolean esta = false;
        
        try
        {
        	for (l.inicio(); !l.esFin() && !esta; l.siguiente()) {
        		if (l.recuperar().destino == j) { esta = true; }
        }
        }catch(Exception ex) 
        {
        	System.out.println(ex);
        }
        
        return esta;
    }

    /** Devuelve el peso de la arista (i,j) de un grafo, 0 si dicha arista
      * no esta en el grafo.
      * @return double Peso de la arista (i,j), 0 si no existe.
      */
    public double pesoArista(int i, int j) {
        ListaConPI<Adyacente> l = elArray[i];
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            if (l.recuperar().destino == j) {
                return l.recuperar().peso;
            }
        }
        return 0.0;
    }

    /** Si no esta, inserta la arista (i, j) en un grafo no Ponderado
      * (al principio de la Lista de adyacentes a i).
      * @param i    Vertice origen
      * @param j    Vertice destino
      */
    public void insertarArista(int i, int j) {
    	
    	try 
    	{
        if (!existeArista(i, j)) {
            elArray[i].insertar(new Adyacente(j, 1));
            numA++;
        }
    	}catch(Exception ex)
    	{
    		System.out.println(ex);
    	}
    }

    /** Si no esta, inserta la arista (i, j) de peso p en un grafo Ponderado
      * (al principio de la Lista de adyacentes a i).
      * @param i    Vertice origen
      * @param j    Vertice destino
      * @param p    Peso de (i, j)
      */
    public void insertarArista(int i, int j, double p) {
        if (!existeArista(i, j)) {
            elArray[i].insertar(new Adyacente(j, p));
            numA++;
        }
    }

    /** Devuelve una Lista Con PI que contiene los adyacentes al vertice i.
      * @param i Vertice del que se obtienen los adyacentes
      * @return ListaConPI con los vertices adyacentes a i
     */
    public ListaConPI<Adyacente> adyacentesDe(int i) {
        return elArray[i];
    }

    public boolean esSumidero(int vertice) {
        if (!elArray[vertice].esVacia()) return false;

        for (int i = 0; i < elArray.length; i++) {
            if (i == vertice) continue;
            boolean sinks = false;
            for (elArray[i].inicio(); !elArray[i].esFin(); elArray[i].siguiente()) {
                if (elArray[i].recuperar().destino == vertice) {
                    sinks = true;
                    break;
                }
            }
            if (!sinks) return false;
        }
        return true;
    }
    
    /*Constructor usado para el modelo geofr�fico simple.
    Se le tienen que pasar el n�mero de v�rtices y la cadena "geo-uniforme"*/
    public void modeloGeoSimple(int numVertices, String modelo) {
            	
    	this.graph = new HashMap<Vertice, HashSet<Vertice>>();
        this.incidencia = new HashMap<Vertice, HashSet<Arista>>();
        this.numeroVertices = numVertices;
        this.nodes = new Vertice[numVertices];
        Random coorX = new Random();
        Random coorY = new Random();
        if (modelo == "geo-uniforme") {
          for (int i = 0; i < numVertices; i++) {
            Vertice n = new Vertice(i, coorX.nextDouble(), coorY.nextDouble());
            this.nodes[i] = n;
            this.graph.put(n, new HashSet<Vertice>());
            this.incidencia.put(n, new HashSet<Arista>());
          }
        }
        this.weighted = false;
      }
    
   
    
    
    
    public HashSet<Vertice> getEdges(int i) {
        Vertice n = this.getNode(i);
        return this.graph.get(n);
      }
    
  //Regresa el grado (n�mero de aristas) de un v�rtice i
    public int gradoVertice(int i) {
      Vertice n1 = this.getNode(i);
      return this.graph.get(n1).size();
    }
    public HashSet<Arista> getWeightedEdges(int i) {
        Vertice n = this.getNode(i);
        return this.incidencia.get(n);
      }

    public Grafo BFS(Grafo g,int s) {
        GrafoDirigido arbol = (GrafoDirigido) g;  // grafo de salida
        Boolean[] discovered = new Boolean[g.numAristas()];  // arreglo aux
        PriorityQueue<Integer> L = new PriorityQueue<Integer>();
        discovered[s] = true;  // se pone como descubierto el v�rtice ra�z
        for (int i = 0; i < g.numAristas(); i++) {
          if (i != s) {   // el resto como no descubiertos
            discovered[i] = false;
          }
        }
        L.add(s);  // Se agrega a la cola de prioridad el nodo ra�z
        while (L.peek() != null) {  // se revisa que no est� vac�a la cola
          int u = L.poll();  // se extrae un elemento de la cola
          HashSet<Vertice> aristas = arbol.getEdges(u);  // aristas del nodo u
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
    /* M�todo para generar el �rbol DFS del Grafo de forma recursiva  */
    /* Regresa otro grafo. Solo toma como entrada el n�mero de un nodo*/
    public Grafo DFS_R(Grafo g,int s) {
    	GrafoDirigido arbol = (GrafoDirigido) g;  // grafo de salida
    Boolean[] discovered = new Boolean[g.numAristas()];  // arreglo aux
    for (int i = 0; i < g.numAristas(); i++) {
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
    public Grafo DFS_I(Grafo g,int s) {
    	GrafoDirigido arbol = (GrafoDirigido) g;  // grafo de salida
      
    Boolean[] explored = new Boolean[g.numAristas()];  // arreglo aux
    Stack<Integer> S = new Stack<Integer>(); //pila para los v�rtices
    Integer[] parent = new Integer[g.numAristas()]; //arreglo de padres
    for (int i = 0; i < g.numAristas(); i++) {
        explored[i] = false;  //se ponen todos los v�rtices como no explorados
      }
    S.push(s);  //se manda a la pila al nodo ra�z
    while(!S.isEmpty()) {
      // mientras la pila no est� vac�a
      int u = S.pop() ;  // se extraen elementos de la pila
      
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



