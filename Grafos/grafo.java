class Grafo<T> {
    
    private List<Vertice<T>> vertices;
    private List<List<List<Aresta<?>>>> matrizAdj;

    public Grafo() {
        vertices = new ArrayList<>();
        matrizAdj = new ArrayList<>();
    }
}