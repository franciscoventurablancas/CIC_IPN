import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/*
 * Un Grafo No Dirigido (GND) es un Par G = (V,E)
 * V es un conjunto finito de Vértices
 * E es un conjunto de Aristas no Dirigidas
 * Arista: Par no ordenado de Vértices (u,v) = (v,u)
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
      /*Para los primeros d vértices, se conecta el vértice i con el vértice j
      con i distinto de j y recorriendo todos los vértices.*/
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
      /*Se recuperan los vértices de los índices i y j*/
       Vertice n1 = this.getNode(i);
       Vertice n2 = this.getNode(j);
      /*Se recuperan las aristas de cada vértice*/
      HashSet<Vertice> aristas1 = this.getEdges(i);
      HashSet<Vertice> aristas2 = this.getEdges(j);
      /*Se revisa que un nodo esté en el conjunto de aristas del otro*/
       if (aristas1.contains(n2) || aristas2.contains(n1)) {
         return true;
       }
       else{
         return false;
       }
    }
  //Conecta dos vértices
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

}
