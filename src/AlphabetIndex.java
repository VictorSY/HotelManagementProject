import java.util.ArrayList;

public class AlphabetIndex<T extends Comparable> {
    private CustomLinkedList[] index;

    public AlphabetIndex() {
        index = new CustomLinkedList[26]; // 26 letters in alphabet
    }

    // adds node if it does not exist
    public void add(T data) {
        if(!contains(data)) {
            // gets first character, makes it lowercase, and shifts the value to match array
            int letter = data.toString().toLowerCase().charAt(0) - 97;
            if(index[letter] == null) {
                index[letter] = new CustomLinkedList<T>();
            }
            index[letter].add(data);
        }
    }

    // removes node if exists
    public void remove(T data) {
        // gets first character, makes it lowercase, and shifts the value to match array
        int letter = data.toString().toLowerCase().charAt(0) - 97;
        if(index[letter] == null) {
            return;
        }
        index[letter].remove(data);
    }

    // looks in array for data
    public boolean contains(T data) {
        // gets first character, makes it lowercase, and shifts the value to match array
        int letter = data.toString().toLowerCase().charAt(0) - 97;
        if(index[letter] == null) {
            return false;
        }
        return index[letter].contains(data);
    }

    public String toString() {
        String returnString = "";
        for(int i = 0; i < index.length; i++) {
            if(index[i] != null) {
                returnString += (char) (i + 41) + index[i].toString();
            }
        }
        return returnString;
    }

    // Find a guest from their name
    public ArrayList<T> find(String data) {
        data = data.replaceAll("[^a-z\\s]", "").trim().toLowerCase();
        ArrayList<T> returnList = new ArrayList<>();
        // gets first character, makes it lowercase, and shifts the value to match array
        int letter = data.toLowerCase().charAt(0) - 97;
        if(index[letter] == null) {
            return returnList;
        }
        LinkedNode<T> current = index[letter].findNode(0);
        while(current != null) {
            // Only compares the name part of the string
            if(current.toString().replaceAll("[^a-z\\s-A-Z']", "").trim().toLowerCase().equals(data)) {
                returnList.add(current.data);
            }
            current = current.nextNode;
        }
        return returnList;
    }

}
