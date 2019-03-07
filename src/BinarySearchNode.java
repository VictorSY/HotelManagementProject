public class BinarySearchNode<T extends Comparable> implements Comparable {
    public CustomLinkedList<T> data;
    public BinarySearchNode<T> left;
    public BinarySearchNode<T> right;

    public BinarySearchNode(T data) {
        this.data = new CustomLinkedList(data);
    }

    public int compareTo(Object o) {
        if(o instanceof BinarySearchNode) {
            return data.compareTo(((BinarySearchNode) o).data);
        } else if(o instanceof Comparable) {
            return data.compareTo(o);
        } else {
            throw new IllegalArgumentException("Invalid object of comparison.");
        }
    }

    public String toString() {
        return data.toString();
    }
}
