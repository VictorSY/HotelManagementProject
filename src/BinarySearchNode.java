public class
BinarySearchNode<T extends Comparable> implements Comparable {
    public CustomLinkedList<T> data;
    public BinarySearchNode<T> left;
    public BinarySearchNode<T> right;

    public BinarySearchNode(T data) {
        this.data = new CustomLinkedList(data);
    }

    // Compares the node with another Comparable object or throws an error
    public int compareTo(Object o) {
        System.out.println("Compared two binary tree nodes...");
        if(o instanceof BinarySearchNode) {
            // nodes themselves can't be compared so their data is
            return data.compareTo(((BinarySearchNode) o).data);
        } else if(o instanceof Comparable) {
            // if it is unidentified comparable type
            return data.compareTo(o);
        } else {
            throw new IllegalArgumentException("Invalid object of comparison.");
        }
    }

    // returns a string representation of the data
    public String toString() {
        return data.toString();
    }
}
