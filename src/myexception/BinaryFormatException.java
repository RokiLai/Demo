package myexception;

/**
 * Created by rokilai on 2017/7/25.
 * 检查输入的字符串是否符合2进制格式
 */
public class BinaryFormatException extends NumberFormatException {

    public BinaryFormatException() {
        super();
    }

    public BinaryFormatException(String s) {
        super(s);
    }

    public static BinaryFormatException forInputString(String string){

        return new BinaryFormatException("For input string: \"" + string + "\"");
    }
}
