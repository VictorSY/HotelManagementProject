public class CustomLinkedList<T extends Comparable> {
    private LinkedNode<T> front;

    public CustomLinkedList() {
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
}
