public class LinkedNode<T extends Comparable> implements Comparable {
    public T data;
    public LinkedNode<T> nextNode;

    public LinkedNode() {
    }

    public LinkedNode(T data) {
        this.data = data;
    }

    public LinkedNode(T data, LinkedNode<T> nextNode) {
        this.data = data;
        this.nextNode = nextNode;
    }

    @Override
    // compares the data if comparable or throws exception
    public int compareTo(Object o) {
        if(o instanceof LinkedNode) {
            return data.compareTo(((LinkedNode) o).data);
        } else if(o instanceof Comparable) {
            return data.compareTo(o);
        } else {
            throw new IllegalArgumentException("Invalid compare type in Linked Node.");
        }
    }

    public String toString() {
        return data.toString();
    }
}
