import javafx.application.Platform;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientWindow {
    private static final String SERVER_HOST = "localhost";
    // порт
    private static final int SERVER_PORT = 3443;
    // клиентский сокет
    private Socket clientSocket;

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    // входящее сообщение
    private  Scanner inMessage;
    // исходящее сообщение
    private  PrintWriter outMessage;
    // следующие поля отвечают за элементы формы
   // private JTextField jtfMessage;
  //  private JTextField jtfName;
  //  private JTextArea jtaTextAreaMessage;
    // имя клиента
    private String clientName = "";

    public static String message1="";

    public void setMessage(String message) {
        this.message = message;
    }

    private String message="";


    // получаем имя клиента
    public String getClientName() {
        return this.clientName;
    }

    public ClientWindow() {
        try {
            // подключаемся к серверу
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            System.out.println("Socket"+clientSocket.toString());
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // в отдельном потоке начинаем работу с сервером
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // бесконечный цикл
                    while (true) {
                        // если есть входящее сообщение
                        if (inMessage.hasNext()) {
                            // считываем его
                            final String inMes = inMessage.nextLine();
                            String clientsInChat = "Клиентов в чате = ";
                            if (inMes.indexOf(clientsInChat) == 0) {


                            } else
                                {
                                // выводим сообщение
                            Platform.runLater(()->{
                            Main.myControllerHandle.chat.appendText(inMes);
                            Main.myControllerHandle.chat.appendText("\n");

                    });


                           }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }).start();

    }

    private void sendMsg(String message) {
             outMessage.println(message);
             outMessage.flush();
    }

}
