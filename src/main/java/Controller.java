import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.PrintWriter;
import java.net.Socket;

public class Controller {
    @FXML
    public Button sendMsg;
    @FXML
    public TextField message;
    @FXML
    public TextField name;
    @FXML
    public TextArea chat;
    @FXML
    protected PasswordField password;

    public static String getMsg1() {
        return msg1;
    }

    public static void setMsg1(String msg1) {
        Controller.msg1 = msg1;
    }

    public static volatile String msg1;

    public void send(ActionEvent actionEvent) {
        if (!Main.myControllerHandle.message.getText().trim().isEmpty() && !Main.myControllerHandle.name.getText().trim().isEmpty()) {
            //  clientName = name.getText();
            // sendMsg();
            // фокус на текстовое поле с сообщением
            // jtfMessage.grabFocus();

        }
        String nameText=Main.myControllerHandle.name.getText();
        String msg=Main.myControllerHandle.message.getText();
        System.out.println("Controller "+ Main.myControllerHandle.toString());
        msg1=nameText + ": " +msg;
        System.out.println("Controller "+Main.getCurrentClient().toString());
        Socket socket=Main.getCurrentClient().getClientSocket();
        System.out.println("ControllerSocket"+ socket.toString());
        try {
            PrintWriter outMessage= new PrintWriter(socket.getOutputStream());
            outMessage.println(msg1);
            outMessage.flush();
        }
        catch (Exception e){
            e.getMessage();
        }

      //  return nameText + ": " +msg ;

    }
}
