
public class MainGrafo {

	public static void main(String[] args) {
		
		//GenErdosRenyi
		 Grafo g = genErdosRenyi(30,0,true,true);
		 System.out.println(g);		 
		 Grafo g2 = genErdosRenyi(100,0,true,true);
		 System.out.println(g2);
		 Grafo g3 = genErdosRenyi(500,0,true,true);
		 System.out.println(g3);
		 
		 //genGilbert
		
		/*int probabilidad =  (int) Math.random();
				 
		Grafo g = genGilbert(30,probabilidad,true,true);
		System.out.println(g);
		
		Grafo g1 = genGilbert(100,probabilidad,true,true);
		System.out.println(g1);
		
		Grafo g2 = genGilbert(500,probabilidad,true,true);
		System.out.println(g2);*/
		
		//TesterGrafoEtiquetado();		
		//TesterGrafoNoDirigido();
		
		//TesterGrafoNoDirigido();
		
		
		
		
	}
	
	
	


	public static Grafo genErdosRenyi(int n, int m, boolean dirigido, boolean auto)
	{
		System.out.println("Francisco Ventura estudiante MCC ");
		System.out.println("modelo de Erdos Renyi");
		
		
		// define the range 
        int max = n; 
        int min = m; 
        int range = max - min; 
		
		 Grafo g = new GrafoDirigido(n);
		 
		  int m2=0;
		  int m1=0;
		  try
		  {
		  for(int i=0; i<=n; i++)
		  {
			  
			  m1 = (int)(Math.random() * range) + min;
			  m2 = (int)(Math.random() * range) + min;
			  
			  g.insertarArista(m1, m2);
			  
		  }
		  }catch(Exception ex)
	    	{
	    		System.out.println(ex);
	    	}
		  
		  	  
		  return g;
	        
		
	}
	
	public static Grafo genGilbert(int n, double p, boolean dirigido, boolean auto)
	{
		System.out.println("Francisco Ventura estudiante MCC ");
		System.out.println("modelo de Gilbert");
		
		
		// define the range 
        int max = n; 
        int min = 0; 
        int range = max - min; 
        double range1 = 0 ; 
        
        		
		 Grafo g = new GrafoDirigido(n); 
		  int m2=0;
		  int m1=0;
		  try
		  {
		  for(int i=0; i<=n; i++)
		  {	  
			  
			  range1 = (int)Math.round(Math.random());
			
			  if (i%2==0)
			  {
				  if (range1 >0) {
					 
				      m1 = (int)(Math.random() * range) + min;
					  m2 = (int)(Math.random() * range) + min; 
					  
					  
				  g.insertarArista(m1, m2);
				  }
				  
			  }
			  
			  g.insertarArista(m1, m2);
			  
		  }
		  }catch(Exception ex)
	    	{
	    		System.out.println(ex);
	    	}
		  
		  	  
		  return g;
	        
	}
	
	
	public static Grafo genGeografico(int n, double r,boolean dirigido, boolean auto)
	{
		
		System.out.println("Francisco Ventura estudiante MCC ");
		System.out.println("modelo de Geografico");
		
		
		// define the range 
        int max = n; 
        int min = 0; 
        int range = max - min; 
        double range1 = 0 ; 
        
        		
		 Grafo g = new GrafoDirigido(n); 
		  int m2=0;
		  int m1=0;
		  try
		  {
		  for(int i=0; i<=n; i++)
		  {	  
			  
			  range1 = (int)Math.round(Math.random());
			
					 
				      m1 = (int)(Math.random() * range) + min;
					  m2 = (int)(Math.random() * range) + min; 
					  
								 			  
			  g.insertarArista(m1, m2);
			  
		  }
		  }catch(Exception ex)
	    	{
	    		System.out.println(ex);
	    	}
		  
		  	  
		  return g;
	}
	
	public static Grafo genBarabasAlbert(int n, double d, boolean dirigido, boolean auto)
	{
		return null;
	}
	
	private static void TesterGrafoEtiquetado()
	{
		GrafoDEtiquetado<Persona> g = new GrafoDEtiquetado<Persona>(3);
		Persona rene = new Persona("1123F","Rene Luna",42);
		Persona Zvi = new Persona("5656M","Zvi Mordejar",63);
		Persona adolfo = new Persona("8372G", "Adoldo Guzman", 68);
		g.etiquetarVertice(1,rene);
		g.etiquetarVertice(2,Zvi);
		g.etiquetarVertice(3,adolfo);
		g.insertarArista(rene, Zvi, 10);
		g.insertarArista(rene, adolfo, 7);
		g.insertarArista(adolfo, rene, 3);
		System.out.println(g);
	}
	
	private static void TesterDirigidoGrafo()
	{
		GrafoDirigido g = new GrafoDirigido(6);
		g.insertarArista(1, 2, 3);
		g.insertarArista(1, 5, 8);
		g.insertarArista(2, 5, 9);
		g.insertarArista(3, 6, 5);
		System.out.println("El grafo es: "+g.toString());
		System.out.println("existeArista(3,1) = " + g.existeArista(3,1));
		ListaConPI<Adyacente> l = g.adyacentesDe(1);
		System.out.println("Los adyacentes al vértice 1 son: ");
		for (l.inicio(); !l.esFin(); l.siguiente()) {
		System.out.println("(1, " + l.recuperar().destino + ")");
		}
		
	}
	
	private static void TesterGrafoNoDirigido() {
		// TODO Auto-generated method stub
		
		GrafoNoDirigido g = new GrafoNoDirigido(2);
		g.insertarArista(1, 2,2);
		g.insertarArista(1, 2,2);
		
		
		ListaConPI<Adyacente> l = g.adyacentesDe(2);
		System.out.println("Los adyacentes al vértice 1 son: ");
		for (l.inicio(); !l.esFin(); l.siguiente()) {
		System.out.println("(1, " + l.recuperar().destino + ")");
		}
		
		
	}

}
