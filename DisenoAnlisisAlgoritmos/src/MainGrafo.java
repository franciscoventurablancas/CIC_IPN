
public class MainGrafo {

	public static void main(String[] args) {
		
		System.out.println("Francisco Ventura");
		
		 // construim un GrafoDirigido amb 5 vertex
        Grafo g = new GrafoDirigido(5);

        // afegim les aristes:
        g.insertarArista(0, 1, 10);
        g.insertarArista(0, 3, 5);
        g.insertarArista(1, 2, 1);
        g.insertarArista(1, 3, 2);
        g.insertarArista(3, 1, 3);
        g.insertarArista(3, 2, 9);
        g.insertarArista(3, 4, 2);
        g.insertarArista(2, 4, 4);
        g.insertarArista(4, 2, 6);

        // escrivim el GrafoDirigido:
        System.out.println("=================================");
        System.out.println("El GrafoDirigido es: ");
        System.out.println(g);
        System.out.println("=================================");

	}

}
