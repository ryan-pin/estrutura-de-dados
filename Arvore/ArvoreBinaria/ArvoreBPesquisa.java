import java.util.ArrayList;

public class ArvoreBPesquisa {
 
    int size = 0;
    Node raiz = null;

    
    public int size(){
        return this.size;
    }

    public int height(Node no) {
        if (no == null) {
            return -1; 
        }
    
        int altura_direita = height(no.filhoD);
        int altura_esquerda = height(no.filhoE); 
    
        return 1 + Math.max(altura_direita, altura_esquerda);
    }
    public int depth(Node no) {

        if(isRoot(no)){
            return 0;
        }
    
        return 1 + depth(no.getPai());
    }


    public boolean isEmpty(){
        return this.size == 0;
    }
    public boolean isInternal(Node no){
        return no.getFilhoD() != null ||  no.getFilhoE() != null;
    }
    public boolean isExternal(Node no){
        return no.getFilhoD() == null && no.getFilhoE() == null ;
    }

    public boolean isRoot(Node no){
        return no.getValue() ==  this.raiz;
    }

    public  void Preordem(Node n, ArrayList<Object> s) {
        s.add(n.getValue());
        if (n.getFilhoE() != null)
            Preordem(n.getFilhoE(), s);
        if (n.getFilhoD() != null)
            Preordem(n.getFilhoD(), s);
    }

    public void Emordem(Node n, ArrayList<Object> s) {
        if (n != null) {
            Emordem(n.getFilhoE(), s);  
            s.add(n.getValue());        
            Emordem(n.getFilhoD(), s);  
        }
    }
    
    public ArrayList<Object> elements() {
        ArrayList<Object> array = new ArrayList<>();
        Emordem(raiz, array);
        return array;
    }
    public Node insertAB(Object value) {

        this.size++;
        if (this.raiz == null) {
            Node  new_no = new Node();
            new_no.setValue(value);
            this.raiz = new_no;
            return this.raiz;
        } else {
            Node new_no = insertNo(this.raiz, value);
            return new_no;
        }

    }
    public Node insertNo(Node no, Object value) {
        
        if ((int) value < (int) no.getValue()) { // se o valor do novo nó for menor que o valor do no atual
            if (no.getFilhoE() == null) { // verifica se o filho esquerdo é nulo
                Node new_no = new Node(); // cria um novo nó
                new_no.setValue(value); // seta o valor do novo nó
                no.setFilhoE(new_no); // seta o filho esquerdo do nó atual (filho da esquerda) como o novo nó
                new_no.setPai(no); // seta o pai do novo nó como o nó atual (filho da esquerda)
                return new_no;
            } else {
                return insertNo(no.getFilhoE(), value); // se o filho esquerdo não for nulo (contém filhos), chama a função recursivamente
            }
        } else {
            if (no.getFilhoD() == null) { // verifica se o filho direito é nulo
                Node new_no = new Node(); // cria um novo nó
                new_no.setValue(value); // seta o valor do novo nó
                no.setFilhoD(new_no); // seta o filho direito do nó atual (filho da direita) como o novo nó
                new_no.setPai(no);
                return new_no; // seta o pai do novo nó como o nó atual (filho da direita)
            } else {
                return insertNo(no.getFilhoD(), value); // se o filho direito não for nulo (contém filhos), chama a função recursivamente
            }
        }
    }

    public Node search(Node no, Object value) {
        if (no == null) {
            return null;
        }
        if (no.getValue() == value) {
            return no;
        } else if ((int) value < (int) no.getValue()) {
            return search(no.getFilhoE(), value);
        } else {
            return search(no.getFilhoD(), value);
        }
    }
    public Node removeNo(Node no, Object value) {
        Node aux = new Node();
        aux.setValue(value);
        remove(search(no, value));
        return  aux;
    }

    public void remove(Node no) {
        this.size--;
        if (no.getFilhoE() == null && no.getFilhoD() == null) {
                if (no == this.raiz) {
                    this.raiz = null;
                }
              
                if(no.getPai().getFilhoD().getValue() == no.getValue()){
                    no.getPai().setFilhoD(null);

                }else{
                    no.getPai().setFilhoE(null);
                }
               
        } else if (no.getFilhoE() == null) {
               
                if (no == this.raiz) {
                    this.raiz = no.getFilhoD();
                    this.raiz.setPai(null);
                } else  if(no.getPai().getFilhoD().getValue() == no.getValue()){
                    no.getPai().setFilhoD(no.getFilhoD());

                }else{
                    no.getPai().setFilhoE(no.getFilhoD());
                }

            } else if (no.getFilhoD() == null) {
                Node auxi = no;
                no = no.getFilhoE();
                no.setPai(auxi.getPai());
                if (auxi == this.raiz) {
                    this.raiz = no;
                } else if(no.getPai().getFilhoD() == no){
                    no.getPai().setFilhoD(no);

                }else{
                    no.getPai().setFilhoE(no);
                }
            } else {

                Node auxi = no.getFilhoD();
                while (auxi.getFilhoE() != null) {
                    auxi = auxi.getFilhoE();
                }
                
                no.setValue(auxi.getValue());
                if(auxi.getPai().getFilhoD() == auxi){
                    auxi.getPai().setFilhoD(null);

                }else{
                    auxi.getPai().setFilhoE(null);
                }

            }
    }

    // Imprime a árvore de forma visual
    public void mostrar() {
        if (raiz == null) {
            System.out.println("Árvore vazia");
            return;
        }
        mostrarArvore(raiz, "", true);
    }

    private void mostrarArvore(Node no, String prefixo, boolean isUltimo) {
        if (no != null) {
            System.out.println(prefixo + (isUltimo ? "└── " : "├── ") + no.getValue());
            
            String novoPrefixo = prefixo + (isUltimo ? "    " : "│   ");
            
            if (no.getFilhoE() != null || no.getFilhoD() != null) {
                if (no.getFilhoE() != null) {
                    mostrarArvore(no.getFilhoE(), novoPrefixo, no.getFilhoD() == null);
                }
                if (no.getFilhoD() != null) {
                    mostrarArvore(no.getFilhoD(), novoPrefixo, true);
                }
            }
        }
    }
    
}