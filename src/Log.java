import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Log extends BufferedWriter {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public Log() throws IOException {
        super(new BufferedWriter(new FileWriter("Logs/" + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Calendar.getInstance().getTime()) + ".txt", true)));
    }

    public void write(String string) {
        try {
            super.write(DATE_FORMAT.format(Calendar.getInstance().getTime()) + " " + string);
        } catch(IOException e) {
            System.out.println("Log offline.");
        }
    }
}
