public class CustomLinkedList<E> {
    private LinkedNode<Comparable<E>> front;

    public CustomLinkedList() {
    }

    public void add(E data) {
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

    public void remove(E data) {
        if(front == null) {
            return;
        } else if(front.data.compareTo(data) == 0) {
            front = front.nextNode;
        } else {
            LinkedNode<Comparable<E>> currentNode = front;

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
}
