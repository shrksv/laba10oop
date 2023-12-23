package ua.edu.ucu.apps;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TimedDocument implements Document {
    public Document document;
    @Override
    public String parse() {
        long start = System.currentTimeMillis();
        String parsed = document.parse();
        long end = System.currentTimeMillis();
        System.out.println("Document parsing took " + (end-start) + " milliseconds\n");
        return parsed;
    }
}