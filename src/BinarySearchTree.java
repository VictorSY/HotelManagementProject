public class BinarySearchTree<T extends Comparable> {
    private BinarySearchNode<T> root;

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
                printAddedToTree(data);
            } else {
                add(data, node.left);
            }
        } else if(node.data.compareTo(data) > 0) {
            if(node.right == null) {
                node.right = new BinarySearchNode<>(data);
                printAddedToTree(data);
            } else {
                add(data, node.right);
            }
        } else {
            if(node.data instanceof CustomLinkedList) {
                node.data.add(data);
                System.out.println("Added duplicate to linked list.\n");
            }
        }


    }

    private void printAddedToTree(T data) {
        // remove when tested
        if(data instanceof Room) {
            System.out.printf("Added room %d to binary search tree.\n\n", ((Room) data).getRoomNumber());
        } else if(data instanceof Guest) {
            System.out.printf("Added %s to guest list.\n\n", ((Guest) data).getName());
        }
    }

    public CustomLinkedList<T> binarySearch(T ideal) {
        return binarySearch(ideal, root);
    }

    private CustomLinkedList<T> binarySearch(T ideal, BinarySearchNode<T> node) {
        if(node == null) {
            return null;
        } else if(node.compareTo(ideal) == 0) {
            return node.data;
        } else if(node.compareTo(ideal) < 0) {
            return binarySearch(ideal, node.left);
        } else {
            return binarySearch(ideal, node.right);
        }
    }

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
