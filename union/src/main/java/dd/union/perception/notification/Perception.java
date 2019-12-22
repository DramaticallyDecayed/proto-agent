package dd.union.perception.notification;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Perception {

    private static Logger logger = LoggerFactory.getLogger(Perception.class);
    private ConcurrentLinkedQueue<String> filenames = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<String> texts = new ConcurrentLinkedQueue<>();

    public Perception() {
        filenames.offer("/notification_docs/F3_Loading_SLBM.docx");
    }

    public void cycle() {
        if (!filenames.isEmpty()) {
            try {
                InputStream is = this.getClass().getResourceAsStream(filenames.poll());
                texts.offer(readFile(is));
            } catch (IOException e) {
                logger.error("Some problems with file reading...", e);
            }
        }
    }

    private String readFile(InputStream is) throws IOException {
        XWPFDocument docx = new XWPFDocument(is);
        XWPFWordExtractor we = new XWPFWordExtractor(docx);
        String text = we.getText();
        Pattern whitespace = Pattern.compile("\\s*\\n\\s*");
        Matcher matcher = whitespace.matcher(text);
        text = matcher.replaceAll(" ");
        return text;
    }

    public ConcurrentLinkedQueue<String> getTexts() {
        return texts;
    }
}
