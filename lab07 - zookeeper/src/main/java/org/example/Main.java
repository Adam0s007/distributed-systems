package org.example;

import org.apache.zookeeper.KeeperException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        if (args.length < 2) {
            System.out.println("args: [IP:PORT] [PROCESS NAME]");
            System.exit(1);
        }

        String serverAddress = args[0];
        String procName = args[1];
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        NodeHandler nodeHandlerInstance = new NodeHandler(serverAddress, procName);
        displayCommands();
        while (true) {
            String userInput = inputReader.readLine();
            switch (userInput) {
                case "getTree":
                    nodeHandlerInstance.printTree("/a", 0);
                    break;
                case "exit":
                    System.exit(0);
                    break;
                case "list":
                    displayCommands();
                    break;
                default:
                    System.out.println("Invalid command");
                    displayCommands();
                    break;
            }
        }
    }

    private static void displayCommands() {
        System.out.println("Available commands:");
        System.out.println("  getTree - Display the tree of nodes");
        System.out.println("  list    - Display available commands");
        System.out.println("  exit    - Quit the application");
    }
}