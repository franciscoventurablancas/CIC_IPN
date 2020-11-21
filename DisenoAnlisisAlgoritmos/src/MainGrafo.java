import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Properties;
import java.util.Random;

public class MainGrafo {

	public static void main(String[] args)throws Exception {
		
		//Utilizado para leer la ruta donde se genera el nuevo archivo
		
		FileReader reader=new FileReader("src/config.properties"); 	      
	    Properties p=new Properties();  
	    p.load(reader);  
	      
		//GenErdosRenyi
		Grafo GenErdosRenyi30 = genErdosRenyi(30,0,true,true);
		 GenErdosRenyi30.EscribeArchivo(p.getProperty("PathfileGenErdosRenyi30"),GenErdosRenyi30.toString());
		 System.out.println(GenErdosRenyi30.toString());
		 
		 Grafo GenErdosRenyi100 = genErdosRenyi(100,0,true,true);
		 GenErdosRenyi100.EscribeArchivo(p.getProperty("PathfileGenErdosRenyi100"), GenErdosRenyi100.toString());
		 System.out.println(GenErdosRenyi100.toString());
		 		 
		 Grafo GenErdosRenyi500 = genErdosRenyi(500,0,true,true);
		 GenErdosRenyi500.EscribeArchivo(p.getProperty("PathfileGenErdosRenyi500"), GenErdosRenyi500.toString());
		 System.out.println(GenErdosRenyi500.toString());
		 	 
    	 //genGilbert
		 		 
		Grafo genGilbert30 = genGilbert(30,0.25,true,true);
		genGilbert30.EscribeArchivo(p.getProperty("PathfileGenGilbert30"), genGilbert30.toString());
		System.out.println(genGilbert30);
		
		Grafo genGilbert100 = genGilbert(100,0.25,true,true);
		genGilbert100.EscribeArchivo(p.getProperty("PathfileGenGilbert100"), genGilbert100.toString());
		System.out.println(genGilbert100);
		
		Grafo genGilbert500 = genGilbert(500,0.25,true,true);
		genGilbert500.EscribeArchivo(p.getProperty("PathfilegenGilbert500"), genGilbert500.toString());
		System.out.println(genGilbert500);
		
		
		//genGeografico
		
		GrafoDEtiquetado genGeografico30 = genGeografico(30,0.3,true,true);			
		genGeografico30.EscribeArchivo(p.getProperty("PathfilegenGeografico30"), genGeografico30.toString());
		
		
		GrafoDEtiquetado genGeografico100 = genGeografico(100,.3,true,true);
		genGeografico100.EscribeArchivo(p.getProperty("PathfilegenGeografico100"), genGeografico100.toString());
		
		GrafoDEtiquetado genGeografico500 = genGeografico(500,0.3,true,true);
		
		genGeografico500.EscribeArchivo(p.getProperty("PathfilegenGeografico500"), genGeografico500.toString());
		
		
			
		//genBarabasAlbert
		
	    GrafoNoDirigido genBarabasAlbert30 = genBarabasAlbert(30,7,true,true);
	    genBarabasAlbert30.EscribeArchivo(p.getProperty("PathfilegenBarabasAlbert30"), genBarabasAlbert30.toString());
		System.out.println(genBarabasAlbert30.toString());
		
		GrafoNoDirigido genBarabasAlbert100 = genBarabasAlbert(100,7,true,true);
		genBarabasAlbert100.EscribeArchivo(p.getProperty("PathfilegenBarabasAlbert100"), genBarabasAlbert100.toString());
		System.out.println(genBarabasAlbert100.toString());
		
		GrafoNoDirigido genBarabasAlbert500 = genBarabasAlbert(500,7,true,true);
		genBarabasAlbert100.EscribeArchivo(p.getProperty("PathfilegenBarabasAlbert500"), genBarabasAlbert500.toString());
		System.out.println(genBarabasAlbert500.toString());
		
				
	}
	
	  	
	
	/*Modelo Erdös-Rényi.
	  Crea n vértices y elige uniformemente al azar m distintos
	   pares de distintos vértices*/
	
	public static Grafo genErdosRenyi(int n, int m, boolean dirigido, boolean auto)
	{
		System.out.println("Francisco Ventura estudiante MCC ");
		System.out.println("modelo de Erdos Renyi");
		
		
        Random randomNum1 = new Random();
        Random randomNum2 = new Random();
		
		 Grafo g = new GrafoDirigido(n);
		 
		 /*mientras el número de aristas del grafo sea menor que el número con el
		    que se llamó este modelo se generan números al azar entre 0 y el número de
		    nodos -1. Se verifica si ya existe conexión entre los nodos que
		    corresponden a esos índices y si no, se conectan. Se verifica que
		    los números pseudoaleatorios no sean el mismo*/
		 
		try { 
		 while((g.numAristas()<g.numVertices()))
		 {
			 
			  n = randomNum1.nextInt(g.numVertices());
		      m = randomNum2.nextInt(g.numVertices());
		      
		      if (n != m) {
		          if (!g.existeArista(n, m)) {
		        	  g.insertarArista(n, m);
		          }
		            
						 
		 }
		 
		  }
		  }catch(Exception ex)
	    	{
	    		System.out.println(ex);
	    	}
		  
		  	  
		  return g;
	        
		
	}
	
	
	/*Modelo de Gilbert.
	  Crear m aristas crear n vértices y poner una arista entre cada par
	  independiente y uniformemente con probabilidad p*/
	
	public static Grafo genGilbert(int n, double p, boolean dirigido, boolean auto)
	{
		System.out.println("Francisco Ventura estudiante MCC ");
		System.out.println("modelo de Gilbert");
		
		 Grafo g = new GrafoDirigido(n);
		Random randomNum = new Random();
		
		 try
		  {
		
		
		/*Para cada vértice i se recorren todos los vértices j con i<>j. En cada
	    par se calcula un número pseudoaleatorio entre 0.0 y 1.0 y se compara
	    con la probabilidad que se le pasó como argumento al modelo. Si es menor
	    que la probabilidad y no existe ya una conexión, se realiza la conexión.*/
	    for(int i = 0; i < g.numVertices(); i++) {
	      for(int j = 0; j <g.numVertices(); j++) {
	        if ((i != j) && (randomNum.nextDouble() <= p)) {
	          if (!g.existeArista(i, j)) {
	        	  g.insertarArista(i, j);
	          }
	        }
	      }
	    }
	  
		
		 
		  }catch(Exception ex)
	    	{
	    		System.out.println(ex);
	    	}
		  
		  	  
		  return g;
	        
	}
	
	
	  /*Modelo geográfico simple.
	Colocar n vértices en un rectángulo unitario con coordenadas uniformes
	(o normales) y colocar una arista entre cada par que queda a una
	distancia r o menor*/
	
	public static GrafoDEtiquetado  genGeografico(int n, double r,boolean dirigido, boolean auto)
	{
		
		System.out.println("Francisco Ventura estudiante MCC ");
		System.out.println("modelo de Geografico");
		
		GrafoDEtiquetado g = new GrafoDEtiquetado(n);		
		g.modeloGeoSimple(n,"geo-uniforme");
		
			 try
		  {
		
		/*Para cada vértice i se compara con el resto de los vértices con los que
	    no se ha comparado y si están a una distancia r o menor se realiza la
	    conexión.*/
	    for(int i = 0; i < g.numVertices(); i++) {
	      for(int j = i + 1; j <g.numVertices(); j++) {
	        double distancia = g.distanciaVertices(g.getNode(i), g.getNode(j));
	        if (distancia <= r) {
	            g.conectarVertices(i, j);
	            
	           
	        }
	      }
	    }
	    
		  }catch(Exception ex)
	    	{
	    		System.out.println(ex);
	    	}
		  
		  	  
		  return g;
	}
	
	public static GrafoNoDirigido genBarabasAlbert(int n, int d, boolean dirigido, boolean auto)
	{
		GrafoNoDirigido g = new GrafoNoDirigido(n);		
		g.modeloBA(d);
	
		Random volado = new Random();
		  /*Para los primeros d vértices, se conecta el vértice i con el vértice j
		  con i distinto de j y recorriendo todos los vértices.*/
		    for(int i = 0; i < d; i++){
		      for(int j = 0; j < i; j++) {
		        if (!g.existeArista(i, j)) {
		          g.conectarVertices(i, j);
		        }
		      }
		    }
		  /*Del vértice d en adelante hasta el vértice n, el vértice i de trata de
		  emparejar con cada vértice j desde el primero hasta i-1. La manera de hacer
		  el emparejamiento es con probabilidad. La probabilidad de que el vértice i
		  se conecte con j es proporcional al número de aristas del vértice j dividido
		  por el número total de aristas en el grafo hasta ese momento. Un número
		  pseudoaleatorio se compara con esa probabilidad, de ser menor, se realiza
		  la conexión. Antes de realizar la conexión se verifica que no exista ya y que
		  el vértice i no tenga ya d o más conexiones.*/
		  // i no se incrementa hasta que ese vértice tiene d conexiones
		    for(int i =  d; i < g.numVertices();) {
		      for(int j = 0; j < i; j++) {
		        double probabilidad =
		        (double)g.gradoVertice(j)/(double)g.numAristas();
		        if (volado.nextDouble() <= probabilidad) {
		          if (!g.existeArista(i, j) && (g.gradoVertice(i) < d)) {
		            g.conectarVertices(i, j);
		          }
		        }
		      }
		      if (g.gradoVertice(i) >= d) i++;
		    }
		    
		    return g;
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
