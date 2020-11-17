# CIC_IPN
tareas del cic


Proyecto 1 - Biblioteca de generación y manejo de grafos
Escribir una biblioteca en Java para generar grafos aleatorios con los siguientes modelos de generación:
Modelo Gn,m de Erdös y Rényi. Crear n vértices y elegir uniformemente al azar m distintos pares de distintos vértices.
public static Grafo genErdosRenyi(int n, int m, boolean dirigido, boolean auto);
Modelo Gn,p de Gilbert. Crear m aristas crear n vértices y poner una arista entre cada par independiente y uniformemente con probabilidad p.
public static Grafo genGilbert(int n, double p, boolean dirigido, boolean auto);
Modelo Gn,r geográfico simple. Colocar n vértices en un rectángulo unitario con coordenadas uniformes (o normales) y colocar una arista entre cada par que queda en distancia r o menor.
public static Grafo genGeografico(int n, double r, boolean dirigido, boolean auto);
Variante del modelo Gn,d Barabási-Albert. Colocar n vértices uno por uno, asignando a cada uno d aristas a vértices distintos de tal manera que la probabilidad de que el vértice nuevo se conecte a un vértice existente v es proporcional a la cantidad de aristas que v tiene actualmente - los primeros d vértices se conecta todos a todos.
public static Grafo genBarabasiAlbert(int n, double d, boolean dirigido, boolean auto);


Entregar:
Link del proyecto en un servidor GIT (sugerido GITLab)
Archivos GV generados, 3 por cada modelo; uno con 30 nodos, otro con 100 y el tercero con 500.
Imágenes de los grafos generados. 12 en total, se sugiere utilizar Gephi.

