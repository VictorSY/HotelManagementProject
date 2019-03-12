import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        String string = "Cost: $196.67\n\nCost: $14.99";
        String newString = "";
        String s;
        for(int i = 0; i < string.length(); i++) {
            s = string.substring(i, i + 1);
            if(s.equals("$")) {
                s = "-$";
            }
            newString += s;
        }
        System.out.println(newString);

    }
}
