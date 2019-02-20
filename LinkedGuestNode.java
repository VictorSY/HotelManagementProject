public class LinkedGuestNode {
    public Guest guest;
    public LinkedGuestNode nextNode;

    public LinkedGuestNode(Guest guest) {
        this.guest = guest;
    }

    public LinkedGuestNode(Guest guest, LinkedGuestNode nextNode) {
        this.guest = guest;
        this.nextNode = nextNode;
    }
}
