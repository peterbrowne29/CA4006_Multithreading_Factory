import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Record {
    private String fileName = "output.dat";
    private BufferedWriter writer;
    private StringBuilder sb;

    public Record() {
        this.sb = new StringBuilder();
    }

    public synchronized void addToFile(String str) {
        this.sb.append(str + "\n");
    }

    public void writeToFile() throws IOException {
        this.writer = new BufferedWriter(new FileWriter(this.fileName));
        try {
            this.writer.write(this.sb.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }

}
