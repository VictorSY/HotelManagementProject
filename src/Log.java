import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Log extends FileWriter {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    // creates a bufferedWriter that writes to a new text file with current date and time
    public Log() throws IOException {
        super("Logs/" + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Calendar.getInstance().getTime()) + ".txt");
    }

    // writes the statement with a timestamp
    public void write(String string) {
        try {
            super.write(DATE_FORMAT.format(Calendar.getInstance().getTime()) + " " + string + "\n");
        } catch(IOException e) {
            System.out.println("Log offline.");
        }
    }
}
