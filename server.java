import java.net.*;
import java.io.*;

public class server {

  public static String roundWinner(String clientChoice, String serverChoice) {
    if (clientChoice.equals(serverChoice)) {
      return "Tie";
    }
    else if ((clientChoice.equals("rock") && (serverChoice.equals("scissors") || serverChoice.equals("lizard")))
      || (clientChoice.equals("paper") && (serverChoice.equals("rock") || serverChoice.equals("spock")))
      || (clientChoice.equals("scissors") && (serverChoice.equals("paper") || serverChoice.equals("lizard")))
      || (clientChoice.equals("lizard") && (serverChoice.equals("paper") || serverChoice.equals("spock")))
      || (clientChoice.equals("spock") && (serverChoice.equals("rock") || serverChoice.equals("scissors")))) {
      return "Client";
    }
    else
      return "Server";
  }
  
  public static void main(String[] args) throws IOException {
    String[] options = new String[] {"rock", "paper", "scissors", "lizard", "spock"};
    int clientScore = 0;
    int serverScore = 0;

    ServerSocket serverSocket = new ServerSocket(1234);
    Socket clientSocket = serverSocket.accept();
    
    for(int round = 1; round < 16; round++) {
      String serverChoice = options[(int)(Math.random() * 5)];
      DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
      out.writeUTF(serverChoice);

      DataInputStream in = new DataInputStream(clientSocket.getInputStream());
      String clientChoice = in.readUTF();

      String result = roundWinner(clientChoice, serverChoice);

      if(result.equals("Client")) {
        clientScore++;
      }
      else if(result.equals("Server")) {
        serverScore++;
      }

      System.out.println("Round " + (round) + ": " + "Client: " + clientChoice + " x Server: " + serverChoice + " -- Winner: " + result);

    }

    System.out.println("Final Score: Client: " + (clientScore) + " x Server: " + (serverScore));

    if(clientScore > serverScore) {
      System.out.println("Client wins!");
    }
    else if(clientScore < serverScore) {
      System.out.println("Server wins!");
    }
    else {
      System.out.println("Tie!");
    }

    clientSocket.close();
    serverSocket.close();
  }
}