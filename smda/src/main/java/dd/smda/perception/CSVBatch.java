package dd.smda.perception;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 11.02.2017.
 */
public class CSVBatch {

    private String[] title;
    private List<String[]> data = new ArrayList<>();
    private int columnNumber;

    public void setTitle(String[] title) {
        columnNumber = title.length;
        this.title = new String[columnNumber];
        System.arraycopy(title, 0, this.title, 0, columnNumber);
    }

    public void addDataLine(String[] dataLine) {
        String[] dataLineTemp = new String[columnNumber];
        System.arraycopy(dataLine, 0, dataLineTemp, 0, columnNumber);
        data.add(dataLineTemp);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String string : title) {
            sb.append(string).append("\t");
        }
        for (String[] strings : data) {
            for (String string : strings) {
                sb.append(string).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public List<String[]> getData() {
        return data;
    }

    public String[] getTitle() {
        return title;
    }
}
