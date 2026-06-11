import java.util.ArrayList;
import java.util.List;

// Classe Vértice
class Vertice<T> {
    T valor;

    public Vertice(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }
}