public class LinkedNode<Comparable> {
    public Comparable data;
    public LinkedNode<Comparable> nextNode;

    public LinkedNode() {
    }

    public LinkedNode(Comparable data) {
        this.data = data;
    }

    public LinkedNode(Comparable data, LinkedNode nextNode) {
        this.data = data;
        this.nextNode = nextNode;
    }
}
