import java.io.Serializable;

public class CustomException extends Exception implements Serializable {
    public static String msg = "";

    public CustomException(){
        super();
    }

    public CustomException(String message){
        super(message);
        msg = message;
    }

    public CustomException(String message, Exception inner){
        super(message, inner);
    }

    public static void processError(String msg, int value){
        switch(msg){
            case "error1":
                System.out.println(HardCodedValue.ERROR1 + value);
                break;
            case "error2":
                System.out.println(HardCodedValue.ERROR4 + value);
                break;

        }
    }
}
