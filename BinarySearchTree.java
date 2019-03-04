public class BinarySearchTree<T> {
    private BinarySearchNode<Comparable<T>> root;

    public void add(T data) {
        try {
            Comparable<T> temp = (Comparable<T>) data;
        } catch(ClassCastException e) {
            System.out.println(e);
            return;
        }
        if(root == null) {
            root = new BinarySearchNode<>((Comparable<T>) data);
        }
        add(data, root);
    }

    private void add(T data, BinarySearchNode<Comparable<T>> node) {
        if(root.data.compareTo(data) < 0) {
            if(node.left == null) {
                node.left = new BinarySearchNode<>((Comparable<T>) data);
            } else {
                add(data, node.left);
            }
        } else if(root.data.compareTo(data) > 0) {
            if(node.right == null) {
                node.right = new BinarySearchNode<>((Comparable<T>) data);
            } else {
                add(data, node.right);
            }
        } else {

        }
    }
}
