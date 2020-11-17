import java.util.List;

/*
 * Un Grafo No Dirigido (GND) es un Par G = (V,E)
 * V es un conjunto finito de Vértices
 * E es un conjunto de Aristas no Dirigidas
 * Arista: Par no ordenado de Vértices (u,v) = (v,u)
 * 
 */
public class GrafoNoDirigido  extends Grafo {
	
	protected int numV, numA;
    protected ListaConPI<Adyacente>[] elArray;
    private List<Adyacente> l;

    /** Construye un grafo Dirigido vacio con numVertices.
     *  @param numVertices  Numero de vertices del grafo vacio
     */
    @SuppressWarnings("unchecked")
    public GrafoNoDirigido(int numVertices)
    {
    	super();
    }
    
    
    public void insertarArista(int i, int j, int p) {
    	if ( !existeArista(i,j) && i != j) {
    	l.add(new Adyacente(j,p));
    	l.add(new Adyacente(i,p));
    	}
    	numA++;
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


	
	 public ListaConPI<Adyacente> adyacentesDe(int i) {
	        return elArray[i];
	    }


	@Override
	public void insertarArista(int i, int j, double p) {
		// TODO Auto-generated method stub
		
	}

}
