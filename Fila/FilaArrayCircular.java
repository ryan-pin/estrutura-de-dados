public class FilaArrayCircular {
    private int[] array;
    private int inicio;  // índice do primeiro elemento
    private int fim;    // índice onde será inserido o próximo elemento
    private int tamanho;
    private int capacidade;

    public FilaArrayCircular(int capacidadeInicial) {
        this.capacidade = capacidadeInicial;
        this.array = new int[capacidade];
        this.inicio = 0;
        this.fim = 0;
        this.tamanho = 0;
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
    public boolean isFull() {
        return tamanho == capacidade;
    }

    // O(n) 
    private void aumentarCapacidade() {
        int novaCapacidade = capacidade * 2;
        int[] novoArray = new int[novaCapacidade];

        // Copia os elementos na ordem correta
        for (int i = 0; i < tamanho; i++) {
            novoArray[i] = array[(inicio + i) % capacidade];
        }

        array = novoArray;
        inicio = 0;
        fim = tamanho;
        capacidade = novaCapacidade;
    }

    // O(1)
    public void enqueue(int elemento) {
        if (isFull()) {
            aumentarCapacidade();
        }

        array[fim] = elemento;
        fim = (fim + 1) % capacidade;  // movimento circular
        tamanho++;
    }

    // O(1)
    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Fila vazia");
        }

        int elemento = array[inicio];
        inicio = (inicio + 1) % capacidade;  // movimento circular
        tamanho--;

        return elemento;
    }

    // O(1)
    public int primeiro() {
        return array[inicio];
    }

    // O(1) 
    public int ultimo() {
        int indiceUltimo = (fim - 1 + capacidade) % capacidade;
        return array[indiceUltimo];
    }

    // O(n)
    public void mostrar() {
        if (isEmpty()) {
            System.out.println("Fila Vazia");
            return;
        }

        for (int i = 0; i < tamanho; i++) {
            System.out.print(array[(inicio + i) % capacidade]);
            if (i < tamanho - 1) {
                System.out.print(", ");
            }
        }
    }

    // O(1)
    public void limpar() {
        inicio = 0;
        fim = 0;
        tamanho = 0;
    }

    // Teste
    public static void main(String[] args) {
        FilaArrayCircular fila = new FilaArrayCircular(5);

        // Teste de enqueue
        fila.enqueue(10);
        fila.enqueue(20);
        fila.enqueue(30);
        fila.enqueue(40);
        fila.enqueue(50);
        fila.mostrar();
        System.out.println("Tamanho: " + fila.size());

        System.out.println(fila.primeiro());
        System.out.println(fila.ultimo());

        System.out.println(fila.dequeue());
        System.out.println(fila.dequeue());
        fila.mostrar();
        System.out.println(fila.size());

        fila.enqueue(60);
        fila.enqueue(70);
        fila.mostrar();

        fila.enqueue(80);
        fila.enqueue(90);
        fila.mostrar();
        System.out.println(fila.size());

        // Teste de isEmpty
        System.out.println(fila.isEmpty());

        // Limpar e testar isEmpty
        fila.limpar();
        System.out.println(fila.isEmpty());
    }
}
