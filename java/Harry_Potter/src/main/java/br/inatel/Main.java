package br.inatel;

import br.inatel.Service.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcao;

        BruxoService bruxoService = new BruxoService(scanner);
        CasaService casaService = new CasaService(scanner);
        VarinhaService varinhaService = new VarinhaService(scanner);
        FeiticoService feiticoService = new FeiticoService(scanner);
        CriaturaMagicaService criaturaMagicaService = new CriaturaMagicaService(scanner);
        RelatorioService relatorioService = new RelatorioService(scanner);

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Gerenciar Bruxos");
            System.out.println("2. Gerenciar Casas");
            System.out.println("3. Gerenciar Varinhas");
            System.out.println("4. Gerenciar Feitiços");
            System.out.println("5. Gerenciar Criaturas Mágicas");
            System.out.println("6. Relatórios Avançados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    bruxoService.menuBruxo();
                    break;
                case 2:
                    casaService.menuCasas();
                    break;
                case 3:
                    varinhaService.menuVarinhas();
                    break;
                case 4:
                    feiticoService.menuFeiticos();
                    break;
                case 5:
                    criaturaMagicaService.menuCriaturas();
                    break;
                case 6:
                    relatorioService.menuRelatorios();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}