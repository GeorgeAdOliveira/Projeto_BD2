package src;

import java.util.List;
import java.util.Scanner;

import src.model.LocalLazer;
import src.service.LocalLazerService;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import service.LocalLazerService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner run(LocalLazerService service) {
        return args -> {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Adicionar Local");
                System.out.println("2. Buscar Locais Próximos");
                System.out.println("3. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();

                if (opcao == 1) {
                    scanner.nextLine(); // Consumir quebra de linha

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();

                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();

                    System.out.print("Latitude: ");
                    double latitude = scanner.nextDouble();

                    System.out.print("Longitude: ");
                    double longitude = scanner.nextDouble();

                    service.salvarLocal(nome, descricao, endereco, latitude, longitude);
                    System.out.println("Local adicionado com sucesso!");

                } else if (opcao == 2) {
                    System.out.print("Sua latitude: ");
                    double latitude = scanner.nextDouble();

                    System.out.print("Sua longitude: ");
                    double longitude = scanner.nextDouble();

                    System.out.print("Raio em metros: ");
                    double raio = scanner.nextDouble();

                    List<LocalLazer> locais = service.buscarProximos(latitude, longitude, raio);

                    System.out.println("Locais encontrados:");
                    for (LocalLazer local : locais) {
                        System.out.printf("%s - %s (%s) [%f, %f]%n", 
                            local.getNome(), local.getDescricao(), local.getEndereco(), 
                            local.getGeom().getY(), local.getGeom().getX());
                    }

                } else if (opcao == 3) {
                    System.out.println("Saindo...");
                    break;
                } else {
                    System.out.println("Opção inválida!");
                }
            }

            scanner.close();
        };
    }
    
}
