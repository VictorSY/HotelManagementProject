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


}
