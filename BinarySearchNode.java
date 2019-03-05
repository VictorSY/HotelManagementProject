public class BinarySearchNode<T extends Comparable> implements Comparable {
    public T data;
    public BinarySearchNode<T> left;
    public BinarySearchNode<T> right;

    public BinarySearchNode(T data) {
        this.data = data;
    }

    public int compareTo(Object o) {
        if(o instanceof BinarySearchNode) {
            return data.compareTo(((BinarySearchNode) o).data);
        } else {
            throw new IllegalArgumentException("Invalid object of comparison.");
        }
    }
}
