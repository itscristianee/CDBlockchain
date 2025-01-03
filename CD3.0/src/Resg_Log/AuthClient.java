package Resg_Log;

import java.rmi.Naming;
import java.util.Scanner;

public class AuthClient {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Pedir o endereço do servidor
            System.out.print("Digite o endereço do servidor (exemplo: 192.168.1.230): ");
            String serverAddress = scanner.nextLine();

            // Conectar ao serviço RMI
            AuthService authService = (AuthService) Naming.lookup("rmi://" + serverAddress + ":10010/AuthService");

            while (true) {
                System.out.println("1. Registar");
                System.out.println("2. Autenticar");
                System.out.println("3. Sair");
                
                int option = -1; // Inicializar com valor inválido
                while (option == -1) { // Repetir até obter uma entrada válida
                    System.out.print("Escolha uma opção: ");
                    if (scanner.hasNextInt()) {
                        option = scanner.nextInt();
                        scanner.nextLine(); // Consumir o enter
                    } else {
                        System.out.println("Por favor, insira um número inteiro válido.");
                        scanner.nextLine(); // Consumir a entrada inválida
                    }
                }
                
                if (option == 1) {
                    System.out.print("Nome de utilizador: ");
                    String username = scanner.nextLine();
                    System.out.print("Palavra-passe: ");
                    String password = scanner.nextLine();
                    if (authService.register(username, password)) {
                        System.out.println("Utilizador registado com sucesso!");
                    } else {
                        System.out.println("Utilizador já existe.");
                    }
                } else if (option == 2) {
                    System.out.print("Nome de utilizador: ");
                    String username = scanner.nextLine();
                    System.out.print("Palavra-passe: ");
                    String password = scanner.nextLine();
                    if (authService.authenticate(username, password)) {
                        System.out.println("Autenticação bem-sucedida!");
                    } else {
                        System.out.println("Nome de utilizador ou palavra-passe incorretos.");
                    }
                } else if (option == 3) {
                    System.out.println("Saindo...");
                    break;
                } else {
                    System.out.println("Opção inválida.");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
