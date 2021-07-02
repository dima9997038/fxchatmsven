import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.net.Socket;

public class Main extends Application {

    public static ClientWindow getCurrentClient() {
        return currentClient;
    }

    public static void setCurrentClient(ClientWindow currentClient) {
        Main.currentClient = currentClient;
    }

    private static volatile ClientWindow currentClient;




    public static void main(String[] args) throws Exception {
        ClientWindow clientWindow = new ClientWindow();
       // currentClient=clientWindow;
        System.out.println("Main"+ clientWindow.toString() );
        setCurrentClient(clientWindow);
        launch(args);
    }
    public static volatile Controller myControllerHandle;
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/hello.fxml"));
        loader.setController(myControllerHandle);
        loader.setRoot(myControllerHandle);
        Parent root =(Parent) loader.load();
        myControllerHandle =(Controller) loader.getController();
        System.out.println("Main "+myControllerHandle.toString());
        primaryStage.setTitle("Chat");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    @Override

    public void stop(){
        Socket socket=Main.getCurrentClient().getClientSocket();
        try {
            PrintWriter outMessage= new PrintWriter(socket.getOutputStream());
            outMessage.println("##session##end##");
            outMessage.flush();
            outMessage.close();
            System.out.println("I left");
            socket.close();
        }
        catch (Exception e){
            e.getMessage();
        }



    }
}