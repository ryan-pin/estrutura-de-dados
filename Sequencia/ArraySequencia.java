public class ArraySequencia {
    private Object[] array;
    private int tamanho;
    private int capacidade;

    public ArraySequencia(int capacidadeInicial) {
        this.capacidade = capacidadeInicial;
        this.array = new Object[capacidade];
        this.tamanho = 0;
    }

    // O(n)
    private void aumentarCapacidade() {
        int novaCapacidade = capacidade * 2;
        Object[] novoArray = new Object[novaCapacidade];

        for (int i = 0; i < tamanho; i++) {
            novoArray[i] = array[i];
        }

        array = novoArray;
        capacidade = novaCapacidade;
    }

    // O(1)
    public int size() {
        return tamanho;
    }

    // O(1)
    public boolean isEmpty() {
        return tamanho == 0;
    }

    // O(1)
    public Object elementAtRank(int rank) {
        return array[rank];
    }

    // O(1)
    public Object atRank(int rank) {
        return elementAtRank(rank);
    }

    // O(n)
    public int rankOf(Object elemento) {
        for (int i = 0; i < tamanho; i++) {
            if (array[i].equals(elemento)) {
                return i;
            }
        }
        return -1; // não ta na lista
    }

    // O(n)
    public void insertAtRank(int rank, Object elemento) {     
        if (tamanho >= capacidade) {
            aumentarCapacidade();
        }

        // Reposiciona os elementos
        for (int i = tamanho; i > rank; i--) {
            array[i] = array[i - 1];
        }

        array[rank] = elemento;
        tamanho++;
    }

    // O(n)
    public Object removeAtRank(int rank) {
        Object removido = array[rank];

        // Reposiciona os elementos
        for (int i = rank; i < tamanho - 1; i++) {
            array[i] = array[i + 1];
        }

        array[tamanho - 1] = null;
        tamanho--;

        return removido;
    }

    // O(1)
    public Object replaceAtRank(int rank, Object elemento) {
        Object antigo = array[rank];
        array[rank] = elemento;
        return antigo;
    }

    // O(1)
    public Object first() {
        return array[0];
    }

    // O(1)
    public Object last() {
        return array[tamanho - 1];
    }

    // O(n)
    public Object before(Object elemento) {
        int rank = rankOf(elemento);

        return array[rank - 1];
    }

    // O(n)
    public Object after(Object elemento) {
        int rank = rankOf(elemento);
        return array[rank + 1];
    }

    // O(n)
    public Object replaceElement(Object elementoAntigo, Object elementoNovo) {
        int rank = rankOf(elementoAntigo);

        return replaceAtRank(rank, elementoNovo);
    }

    // O(n)
    public void swapElements(Object elemento1, Object elemento2) {
        int rank1 = rankOf(elemento1);
        int rank2 = rankOf(elemento2);

        Object temp = array[rank1];
        array[rank1] = array[rank2];
        array[rank2] = temp;
    }

    // O(n)
    public void insertBefore(Object elementoReferencia, Object novoElemento) {
        int rank = rankOf(elementoReferencia);

        insertAtRank(rank, novoElemento);
    }

    // O(n)
    public void insertAfter(Object elementoReferencia, Object novoElemento) {
        int rank = rankOf(elementoReferencia);
        
        insertAtRank(rank + 1, novoElemento);
    }

    // O(n)
    public void insertFirst(Object elemento) {
        insertAtRank(0, elemento);
    }

    // O(1)
    public void insertLast(Object elemento) {
        insertAtRank(tamanho, elemento);
    }

    // O(n)
    public Object remove(Object elemento) {
        int rank = rankOf(elemento);

        return removeAtRank(rank);
    }

    // O(n)
    public void mostrar() {
        for (int i = 0; i < tamanho; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    // Teste
    public static void main(String[] args) {
        ArraySequencia seq = new ArraySequencia(5);

        seq.insertLast(10);
        seq.insertLast(20);
        seq.insertLast(30);
        seq.insertLast(40);
        seq.mostrar();

        seq.insertFirst(5);
        seq.insertFirst(0);
        seq.mostrar();

        seq.insertAfter(20, 25);
        seq.mostrar();

        seq.insertBefore(30, 28);
        seq.mostrar();

        System.out.println(seq.remove(0));
        seq.mostrar();
    }

}
