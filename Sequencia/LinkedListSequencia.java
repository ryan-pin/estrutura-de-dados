public class LinkedListSequencia {
    
    private class Node {
        Object elemento;
        Node proximo;
        Node anterior;

        Node(Object elemento) {
            this.elemento = elemento;
            this.proximo = null;
            this.anterior = null;
        }
    }

    private Node head;
    private Node tail;
    private int tamanho;

    public LinkedListSequencia() {
        this.head = null;
        this.tail = null;
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

    // O(n)
    private Node getNodeAtRank(int rank) {
        Node atual;
        
        if (rank < tamanho / 2) {
            atual = head;
            for (int i = 0; i < rank; i++) {
                atual = atual.proximo;
            }
        } else {
            atual = tail;
            for (int i = tamanho - 1; i > rank; i--) {
                atual = atual.anterior;
            }
        }
        
        return atual;
    }

    // O(n)
    public Object elementAtRank(int rank) {
        return getNodeAtRank(rank).elemento;
    }

    // O(n)
    public Object atRank(int rank) {
        return elementAtRank(rank);
    }

    // O(n)
    public int rankOf(Object elemento) {
        Node atual = head;
        int rank = 0;
        
        while (atual != null) {
            if (atual.elemento.equals(elemento)) {
                return rank;
            }
            atual = atual.proximo;
            rank++;
        }
        
        return -1; // não ta na lista
    }

    // O(n)
    public void insertAtRank(int rank, Object elemento) {
        Node novoNode = new Node(elemento);

        if (rank == 0) {
            if (isEmpty()) {
                head = tail = novoNode;
            } else {
                novoNode.proximo = head;
                head.anterior = novoNode;
                head = novoNode;
            }
        } else if (rank == tamanho) {
            novoNode.anterior = tail;
            tail.proximo = novoNode;
            tail = novoNode;
        } else {
            Node nodeAtual = getNodeAtRank(rank);
            novoNode.proximo = nodeAtual;
            novoNode.anterior = nodeAtual.anterior;
            nodeAtual.anterior.proximo = novoNode;
            nodeAtual.anterior = novoNode;
        }

        tamanho++;
    }

    // O(n)
    public Object removeAtRank(int rank) {
        Node nodeRemover;

        if (rank == 0) {
            nodeRemover = head;
            head = head.proximo;
            if (head != null) {
                head.anterior = null;
            } else {
                tail = null;
            }
        } else if (rank == tamanho - 1) {
            nodeRemover = tail;
            tail = tail.anterior;
            tail.proximo = null;
        } else {
            nodeRemover = getNodeAtRank(rank);
            nodeRemover.anterior.proximo = nodeRemover.proximo;
            nodeRemover.proximo.anterior = nodeRemover.anterior;
        }

        tamanho--;
        return nodeRemover.elemento;
    }

    // O(n)
    public Object replaceAtRank(int rank, Object elemento) {
        Node node = getNodeAtRank(rank);
        Object antigo = node.elemento;
        node.elemento = elemento;
        return antigo;
    }

    // O(1)
    public Object first() {
        return head.elemento;
    }

    // O(1)
    public Object last() {
        return tail.elemento;
    }

    // O(n)
    private Node getNodeByElement(Object elemento) {
        Node atual = head;
        
        while (atual != null) {
            if (atual.elemento.equals(elemento)) {
                return atual;
            }
            atual = atual.proximo;
        }
        
        return null;
    }

    // O(n)
    public Object before(Object elemento) {
        Node node = getNodeByElement(elemento);
        
        return node.anterior.elemento;
    }

    // O(n)
    public Object after(Object elemento) {
        Node node = getNodeByElement(elemento);
        
        return node.proximo.elemento;
    }

    // O(n)
    public Object replaceElement(Object elementoAntigo, Object elementoNovo) {
        Node node = getNodeByElement(elementoAntigo);
        
        Object antigo = node.elemento;
        node.elemento = elementoNovo;
        return antigo;
    }

    // O(n)
    public void swapElements(Object elemento1, Object elemento2) {
        Node node1 = getNodeByElement(elemento1);
        Node node2 = getNodeByElement(elemento2);
        
        Object temp = node1.elemento;
        node1.elemento = node2.elemento;
        node2.elemento = temp;
    }

    // O(n)
    public void insertBefore(Object elementoReferencia, Object novoElemento) {
        Node node = getNodeByElement(elementoReferencia);
        
        Node novoNode = new Node(novoElemento);
        
        if (node == head) {
            novoNode.proximo = head;
            head.anterior = novoNode;
            head = novoNode;
        } else {
            novoNode.proximo = node;
            novoNode.anterior = node.anterior;
            node.anterior.proximo = novoNode;
            node.anterior = novoNode;
        }
        
        tamanho++;
    }

    // O(n)
    public void insertAfter(Object elementoReferencia, Object novoElemento) {
        Node node = getNodeByElement(elementoReferencia);
        
        Node novoNode = new Node(novoElemento);
        
        if (node == tail) {
            novoNode.anterior = tail;
            tail.proximo = novoNode;
            tail = novoNode;
        } else {
            novoNode.anterior = node;
            novoNode.proximo = node.proximo;
            node.proximo.anterior = novoNode;
            node.proximo = novoNode;
        }
        
        tamanho++;
    }

    // O(1)
    public void insertFirst(Object elemento) {
        Node novoNode = new Node(elemento);
        
        if (isEmpty()) {
            head = tail = novoNode;
        } else {
            novoNode.proximo = head;
            head.anterior = novoNode;
            head = novoNode;
        }
        
        tamanho++;
    }

    // O(1)
    public void insertLast(Object elemento) {
        Node novoNode = new Node(elemento);
        
        if (isEmpty()) {
            head = tail = novoNode;
        } else {
            novoNode.anterior = tail;
            tail.proximo = novoNode;
            tail = novoNode;
        }
        
        tamanho++;
    }

    // O(n)
    public Object remove(Object elemento) {
        Node node = getNodeByElement(elemento);
        
        if (node == head) {
            head = head.proximo;
            if (head != null) {
                head.anterior = null;
            } else {
                tail = null;
            }
        } else if (node == tail) {
            tail = tail.anterior;
            tail.proximo = null;
        } else {
            node.anterior.proximo = node.proximo;
            node.proximo.anterior = node.anterior;
        }
        
        tamanho--;
        return node.elemento;
    }

    // O(n)
    public void mostrar() {
        Node atual = head;
        while (atual != null) {
            System.out.print(atual.elemento + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    // Teste
    public static void main(String[] args) {
        LinkedListSequencia seq = new LinkedListSequencia();

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
