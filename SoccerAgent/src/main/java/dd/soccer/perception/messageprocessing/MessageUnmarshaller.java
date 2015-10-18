package dd.soccer.perception.messageprocessing;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Sergey on 14.10.2015.
 */
public abstract class MessageUnmarshaller {
    private Scanner scanner;
    private Integer cycle;

    public void unmarshalMessage(Scanner scanner){
        this.scanner = scanner;
        cycle = Integer.parseInt(scanner.next());
        unmarshal();
    }

    protected abstract void unmarshal();

    public String getSeeElement(){
        scanner = scanner.skip(Pattern.compile(".*?\\(+"));
        return scanner.findInLine(Pattern.compile("\\w+"));
    }

    public String getElementParams(){
        String type = scanner.findInLine(Pattern.compile("[\\w\\s\\-\\.]*"));
        scanner = scanner.skip(Pattern.compile("\\)+"));
        return type.trim();
    }

    public boolean hasNextElement(){
        return scanner.hasNext();
    }
}
