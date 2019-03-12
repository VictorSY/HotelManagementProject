public class BinarySearchTree<T extends Comparable> {
    private BinarySearchNode<T> root;

    // Add new node with generic data T
    public void add(T data) {
        if(root == null) {
            root = new BinarySearchNode(data);
        } else {
            add(data, root);
        }
    }
    private void add(T data, BinarySearchNode<T> node) {
        if(node.data.compareTo(data) < 0) {
            if(node.left == null) {
                node.left = new BinarySearchNode<>(data);
            } else {
                add(data, node.left);
            }
        } else if(node.data.compareTo(data) > 0) {
            if(node.right == null) {
                node.right = new BinarySearchNode<>(data);
            } else {
                add(data, node.right);
            }
        } else {
            if(node.data instanceof CustomLinkedList) {
                node.data.add(data);
            }
        }
    }


    // Searches for match inside tree
    public CustomLinkedList<T> binarySearch(T match) {
        return binarySearch(match, root);
    }

    private CustomLinkedList<T> binarySearch(T match, BinarySearchNode<T> node) {
        if(node == null) {
            return null;
        } else if(node.compareTo(match) == 0) {
            return node.data;
        } else if(node.compareTo(match) < 0) {
            return binarySearch(match, node.left);
        } else {
            return binarySearch(match, node.right);
        }
    }

    // Print tree in order
    public void printTreeInOrder() {
        printTreeInOrder(root);
    }
    private void printTreeInOrder(BinarySearchNode<T> node) {
        if(node == null) {
            return;
        }
        printTreeInOrder(node.left);
        System.out.println(node.toString());
        printTreeInOrder(node.right);
    }

    // prints tree pre-order
    public void printTreePreOrder() {
        printTreePreOrder(root);
    }
    private void printTreePreOrder(BinarySearchNode<T> node) {
        if(node == null) {
            return;
        }
        System.out.println(node.toString());
        printTreeInOrder(node.left);
        printTreeInOrder(node.right);
    }
}
