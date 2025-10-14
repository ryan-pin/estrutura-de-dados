

public class PilhaRubroNegra {

    private Object[] array;
    private int capacidade;
    private int tamPilhaVermelha;
    private int tamPilhaPreta;

    public PilhaRubroNegra(int capacidade) {
        this.capacidade = capacidade;
        this.array = new Object[capacidade];
        this.tamPilhaVermelha = 0;
        this.tamPilhaPreta = 0;
    }

     private void aumentaCapacidade() {
        int novaCapacidade = capacidade * 2;
        Object[] novoArray = new Object[novaCapacidade];

        // Copia dos vermelhos
        for (int i = 0; i < tamPilhaVermelha; i++) {
            novoArray[i] = array[i];
        }

        // Copia dos pretos
        for (int i = 0; i < tamPilhaPreta; i++) {
            novoArray[novaCapacidade - 1 - i] = array[capacidade - 1 - i];
        }

        array = novoArray;
        capacidade = novaCapacidade;
    }

    private void reduzCapacidade() {
    int elementos = tamPilhaVermelha + tamPilhaPreta;

    if (elementos == capacidade / 3) {
        int novaCapacidade = capacidade / 2;

        Object[] novoArray = new Object[novaCapacidade];

        // Copia dos vermelhos
        for (int i = 0; i < tamPilhaVermelha; i++) {
            novoArray[i] = array[i];
        }

        // Copia dos pretos
        for (int i = 0; i < tamPilhaPreta; i++) {
            novoArray[novaCapacidade - 1 - i] = array[capacidade - 1 - i];
        }

            array = novoArray;
            capacidade = novaCapacidade;
        }
    }

    public void pushVermelho(Object valor) {
        if (tamPilhaVermelha + tamPilhaPreta >= capacidade) {
            aumentaCapacidade();
        }
        array[tamPilhaVermelha] = valor;
        tamPilhaVermelha++;
    }

    public void pushPreto(Object valor) {
        if (tamPilhaVermelha + tamPilhaPreta >= capacidade) {
            aumentaCapacidade();
        }
        tamPilhaPreta++;
        array[capacidade - tamPilhaPreta] = valor;
    }

    public int topVermelho() throws EPilhaVazia {
        if (tamPilhaVermelha == 0) {
            throw new EPilhaVazia();
        }
        return (int) array[tamPilhaVermelha - 1];
    }

    public int topPreto() throws EPilhaVazia {
        if (tamPilhaPreta == 0) {
            throw new EPilhaVazia();
        }
        return (int) array[capacidade - tamPilhaPreta];
    }

    public int popVermelho() throws EPilhaVazia {
        if (tamPilhaVermelha == 0) {
            throw new EPilhaVazia();
        }
        tamPilhaVermelha--;
        int valor = (int) array[tamPilhaVermelha]; // conversao de tipo
        reduzCapacidade();
        return valor;
    }

    public int popPreto() throws EPilhaVazia {
        if (tamPilhaPreta == 0) {
            throw new EPilhaVazia();
        }
        int valor = (int) array[capacidade - tamPilhaPreta];
        tamPilhaPreta--;
        reduzCapacidade();
        return valor;
    }

    public void mostrar() {
        for (int i = 0; i < capacidade; i++) {
            if (array[i] != null) {
                System.out.print(array[i] + " ");
            }
        }
    }
    

    // TESTE
    public static class Main {
        public static void main(String[] args) throws EPilhaVazia {
            PilhaRubroNegra pilha = new PilhaRubroNegra(4);
            pilha.pushVermelho(1);
            pilha.pushVermelho(2);
            pilha.pushPreto(10);
            pilha.pushPreto(20);

            pilha.mostrar();

            System.out.println(pilha.topVermelho()); // 2
            System.out.println(pilha.topPreto());    // 20
            System.out.println(pilha.popVermelho()); // 2
            System.out.println(pilha.popPreto());    // 20

            pilha.mostrar();

            pilha.pushVermelho(3);
            pilha.pushVermelho(4);
            pilha.pushVermelho(5);
            pilha.pushPreto(30);
            pilha.pushPreto(40);
            System.out.println(pilha.topVermelho());
            System.out.println(pilha.topPreto());

            pilha.mostrar(); // Imprime o estado atual da pilha
        }
    }
}