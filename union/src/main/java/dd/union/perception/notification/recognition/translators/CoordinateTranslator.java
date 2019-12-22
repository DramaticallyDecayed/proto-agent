package dd.union.perception.notification.recognition.translators;

/**
 * Created by slebedev on 15.06.2017.
 */
public class CoordinateTranslator implements Translation<String> {

    enum CoordinateLetters {
        WEST("W","З.Д."),
        EAST("E","В.Д."),
        NORTH("N","С.Ш."),
        SOUTH("S","Ю.Ш.");

        private String original;
        private String translation;

        private CoordinateLetters(String original,String translation){
            this.original = original;
            this.translation = translation;
        }

        public String getOriginal(){
            return original;
        }

        public String getTranslation(){
            return translation;
        }
    }

    @Override
    public String translate(String value) {
        for (CoordinateLetters coordinateLetter : CoordinateLetters.values()) {
            if(value.contains(coordinateLetter.getOriginal())){
                return value.replace(coordinateLetter.getOriginal(), coordinateLetter.getTranslation());
            }
        }
        return null;
    }
}
