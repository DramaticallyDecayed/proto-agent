package dd.soccer.perception.messageprocessing;

/**
 * Created by Sergey on 28.10.2015.
 */
public class SenseBodyMessageUnmarshaller extends MessageUnmarshaller{
    @Override
    protected void unmarshal() {
        while (hasNextElement()) {

            System.out.println(getSeeElement());
            System.out.println(getElementParams());
        }
    }
}
