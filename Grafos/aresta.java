class Aresta<T> {
    Vertice<?> origem;
    Vertice<?> destino;
    T valor;

    public Aresta(Vertice<?> origem, Vertice<?> destino, T valor) {
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
    }

    public Vertice<?>[] finalVertices() {
        return new Vertice<?>[]{origem, destino};
    }

    public Vertice<?> oposto(Vertice<?> v) {
        if (v == origem) return destino;
        if (v == destino) return origem;
        throw new IllegalArgumentException("Vértice não está nesta aresta");
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }
}