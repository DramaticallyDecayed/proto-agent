package dd.union.perception;

import dd.union.perception.notification.model.NotificationTree;
import dd.union.perception.notification.recognition.processing.NotificationProcessingService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class NotificationRecognitionTest {

    private Data data;

    private NotificationProcessingService notificationProcessingService = new NotificationProcessingService();


    @Before
    public void before() {
        //        data = Data.LAUNCH_WITH_CIRCLE;
        //        data = Data.LAUNCH_WITH_CONVEX;

        data = Data.SLBM_LOADING;
        //        data = Data.ICBM_ARRIVAL;
        //        data = Data.ICBM_UNLOADING;
        //        data = Data.ICBM_DEPARTURE;
        //        data = Data.ICBM_LOADING;
        //        data = Data.STB_DEPLOYED;
        //        data = Data.STB_UNDEPLOYED;
    }

    @Test
    public void recognizeTest() throws IOException {
//        NotificationTree notificationTree =
//                notificationProcessingService.processNotification(data.getFilename());
//        System.out.println(notificationTree.getFormat());
    }


    enum Data {
        SLBM_LOADING("/notification_docs/F3_Loading_SLBM.docx", "17-005/3"),
        ICBM_ARRIVAL("/notification_docs/F3_Arrival_MMIII.docx", "17-002/3"),
        ICBM_UNLODAING("/notification_docs/USA140961_Unloading_MM_III.docx", "14-961/3"),
        ICBM_DEPARTURE("/notification_docs/USA140954_Departure_MM_III.docx", "14-954/3"),
        ICBM_LOADING("/notification_docs/USA140964_Loading_III.docx", "14-964/3"),
        STB_DEPLOYED("/notification_docs/SOA_US_14_0946_F003_SB_became_deployed.docx", "14-946/3"),
        STB_UNDEPLOYED("/notification_docs/F3_SB_became_undeployed.docx", "17-004/3"),

        LAUNCH_WITH_CIRCLE("/notification_docs/USA160236_EN.docx", "16-236/15"),
        LAUNCH_WITH_CONVEX("/notification_docs/USA160236_EN_fake.docx", "16-236/15");

        private String filename;
        private String ident;

        private Data(String filename, String ident) {
            this.filename = filename;
            this.ident = ident;
        }

        public String getFilename() {
            return filename;
        }

        public String getIdent() {
            return ident;
        }
    }
}
