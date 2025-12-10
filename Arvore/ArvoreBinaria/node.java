public class Node {
    public Object value =  (Integer) null;
    public Node filhoD = null;
    public Node filhoE =  null;
    public Node Pai =  null;
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    public Node getFilhoD() {
        return filhoD;
    }
    public void setFilhoD(Node filhoD) {
        this.filhoD = filhoD;
    }
    public Node getFilhoE() {
        return filhoE;
    }
    public void setFilhoE(Node filhoE) {
        this.filhoE = filhoE;
    }
    public Node getPai() {
        return Pai;
    }
    public void setPai(Node pai) {
        Pai = pai;
    }
}