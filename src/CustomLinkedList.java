public class CustomLinkedList<T extends Comparable> implements Comparable {
    private LinkedNode<T> front;

    public CustomLinkedList() {
    }

    public CustomLinkedList(T data) {
        add(data);
    }

    public void add(T data) {
        if(front == null) {
            front = new LinkedNode(data);
        } else {
            LinkedNode currentNode = front;
            while(currentNode.nextNode != null) {
                currentNode = currentNode.nextNode;
            }
            currentNode.nextNode = new LinkedNode(data);
        }
    }

    public void remove(T data) {
        if(front == null) {
            return;
        } else if(front.data.compareTo(data) == 0) {
            front = front.nextNode;
        } else {
            LinkedNode<T> currentNode = front;

            while(currentNode.nextNode != null && currentNode.nextNode.data.compareTo(data) != 0) {
                currentNode = currentNode.nextNode;
            }
            if(currentNode.nextNode != null && currentNode.nextNode.data.compareTo(data) == 0) {
                LinkedNode temp = currentNode.nextNode;
                currentNode.nextNode = currentNode.nextNode.nextNode;
                temp.nextNode = null;
            }
        }
    }

    public LinkedNode<T> findNode(int index) {
        LinkedNode<T> current = front;
        for(int i = 0; i < index && current != null; i++) {
            current = current.nextNode;
        }
        if(current != null) {
            return current;
        }
        return null;
    }

    public boolean contains(T data) {
        LinkedNode<T> current = front;
        while(current.nextNode != null) {
            if(current.data.compareTo(data) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return front == null;
    }

    @Override
    // only compares the first element in the list
    public int compareTo(Object o) {
        if(o instanceof CustomLinkedList) {
            if(front != null && ((CustomLinkedList) o).findNode(0) != null) {
                return front.compareTo(((CustomLinkedList) o).findNode(0));
            }
            return -1;
        } else if(o instanceof Comparable) {
            if(front != null) {
                return front.compareTo(o);
            }
            return -1;
        } else {
            throw new IllegalArgumentException("Incompatible object of comparison.");
        }
    }

    public String toString() {
        String returnString = "LinkedList: \n";
        LinkedNode current = front;
        while(current != null) {
            returnString += current.toString() + "\n";
            current = current.nextNode;
        }
        return returnString + "\n";
    }
}
