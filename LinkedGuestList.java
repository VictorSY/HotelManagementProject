public class LinkedGuestList {
    private LinkedGuestNode firstGuest;

    public LinkedGuestList(Guest guest) {
        firstGuest = new LinkedGuestNode(guest);
    }

    public void add(Guest guest) {
        if(firstGuest == null) {
            firstGuest = new LinkedGuestNode(guest);
        }
        else {
            LinkedGuestNode currentNode = firstGuest;
            while (currentNode.nextNode != null) {
                currentNode = currentNode.nextNode;
            }
            currentNode.nextNode = new LinkedGuestNode(guest);
        }
    }

    public void remove(Guest guest) {
        if(firstGuest == null) {
            return;
        }
        else if (firstGuest.guest.compareTo(guest) == 0) {
            firstGuest = firstGuest.nextNode;
        }
        else {
            LinkedGuestNode currentNode = firstGuest;
            while (currentNode.nextNode != null && currentNode.nextNode.guest.compareTo(guest) != 0) {
                currentNode = currentNode.nextNode;
            }
            if (currentNode.nextNode.guest.compareTo(guest) == 0) {
                LinkedGuestNode temp = currentNode.nextNode;
                currentNode.nextNode = currentNode.nextNode.nextNode;
                temp.nextNode = null;
            }
        }
    }
}
