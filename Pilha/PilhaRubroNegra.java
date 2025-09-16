import EPilhaVazia from "./EPilhaVazia.java";

Public class PilhaRubroNegra {
    
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

    public void pushVermelho(Object valor) {
        if (tamPilhaVermelha + tamPilhaPreta >= capacidade) {
            
            int novaCapacidade = capacidade * 2;
            Object[] novoArray = new Object[novaCapacidade];
    
            for (int i = 0; i < tamPilhaVermelha; i++) {
                novoArray[i] = array[i];
            }
            
            for (int i = 0; i < tamPilhaPreta; i++) {
                novoArray[novaCapacidade - 1 - i] = array[capacidade - 1 - i];
            }
            array = novoArray;
            capacidade = novaCapacidade;
        }
        array[tamPilhaVermelha] = valor;
        tamPilhaVermelha++;
    }

    public void pushPreto(Object valor) {
        if (tamPilhaVermelha + tamPilhaPreta >= capacidade) {

            int novaCapacidade = capacidade * 2;
            Object[] novoArray = new Object[novaCapacidade];

            for (int i = 0; i < tamPilhaVermelha; i++) {
                novoArray[i] = array[i];
            }

            for (int i = 0; i < tamPilhaPreta; i++) {
                novoArray[novaCapacidade - 1 - i] = array[capacidade - 1 - i];
            }
            array = novoArray;
            capacidade = novaCapacidade;
        }
        tamPilhaPreta++;
        array[capacidade - tamPilhaPreta] = valor;
    }

}