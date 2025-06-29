import java.util.regex.Pattern;

public class Validator {
    private static final String NAME_REGEX = "^[A-Za-z\\s]+$";
    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.\\w{2,}$";

    public static boolean isValidName(String name){
        return Pattern.matches(NAME_REGEX, name);
    }
    public static boolean isValidEmail(String email){
        return Pattern.matches(EMAIL_REGEX, email);
    }
}
