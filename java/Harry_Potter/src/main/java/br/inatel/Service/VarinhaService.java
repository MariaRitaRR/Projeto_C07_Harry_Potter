package br.inatel.Service;

import br.inatel.DAO.BruxoDAO;
import br.inatel.DAO.VarinhaDAO;
import br.inatel.model.Bruxo;
import br.inatel.model.Varinha;
import java.util.ArrayList;
import java.util.Scanner;

public class VarinhaService {
    private VarinhaDAO varinhaDAO;
    private Scanner scanner;

    public VarinhaService(Scanner scanner) {
        this.varinhaDAO = new VarinhaDAO();
        this.scanner = scanner;
    }

    public void menuVarinhas() {

        int opcao;

        do {
            System.out.println("\n=== GERENCIAR VARINHAS ===");
            System.out.println("1. Listar todas as varinhas");
            System.out.println("2. Buscar varinha por ID");
            System.out.println("3. Cadastrar nova varinha");
            System.out.println("4. Atualizar varinha");
            System.out.println("5. Excluir varinha");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarTodasVarinhas();
                    break;
                case 2:
                    buscarVarinhaPorId();
                    break;
                case 3:
                    cadastrarNovaVarinha();
                    break;
                case 4:
                    atualizarVarinha();
                    break;
                case 5:
                    excluirVarinha();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void listarTodasVarinhas() {
        System.out.println("\n=== LISTA DE VARINHAS ===");
        ArrayList<Varinha> varinhas = varinhaDAO.selectAllVarinhas();
        for (Varinha v : varinhas) {
            System.out.println("ID: " + v.getIdVarinha() +
                    " | Madeira: " + v.getMadeira() +
                    " | Núcleo: " + v.getNucleo() +
                    " | Comprimento: " + v.getComprimento() + "cm" +
                    " | Flexibilidade: " + v.getFlexibilidade());
        }
    }

    private void buscarVarinhaPorId() {
        System.out.print("\nDigite o ID da varinha: ");
        int id = scanner.nextInt();
        Varinha varinha = varinhaDAO.selectVarinhaById(id);
        if (varinha != null) {
            System.out.println("\n=== DADOS DA VARINHA ===");
            System.out.println("ID: " + varinha.getIdVarinha());
            System.out.println("Madeira: " + varinha.getMadeira());
            System.out.println("Núcleo: " + varinha.getNucleo());
            System.out.println("Comprimento: " + varinha.getComprimento() + "cm");
            System.out.println("Flexibilidade: " + varinha.getFlexibilidade());
        } else {
            System.out.println("Varinha não encontrada!");
        }
    }

    private void cadastrarNovaVarinha() {
        System.out.println("\n=== CADASTRAR NOVA VARINHA ===");
        System.out.print("Madeira: ");
        String madeira = scanner.nextLine();
        System.out.print("Núcleo: ");
        String nucleo = scanner.nextLine();
        System.out.print("Comprimento (cm): ");
        float comprimento = scanner.nextFloat();
        scanner.nextLine(); // Limpar buffer
        System.out.print("Flexibilidade: ");
        String flexibilidade = scanner.nextLine();

        Varinha novaVarinha = new Varinha(nucleo, madeira, comprimento, flexibilidade);
        if (varinhaDAO.inserir(novaVarinha)) {
            System.out.println("Varinha cadastrada com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar varinha!");
        }
    }

    private void atualizarVarinha() {
        System.out.println("\n=== ATUALIZAR VARINHA ===");
        System.out.print("Digite o ID da varinha a ser atualizada: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        Varinha varinha = varinhaDAO.selectVarinhaById(id);
        if (varinha == null) {
            System.out.println("Varinha não encontrada!");
            return;
        }

        System.out.println("Dados atuais:");
        System.out.println("Madeira: " + varinha.getMadeira());
        System.out.println("Núcleo: " + varinha.getNucleo());
        System.out.println("Comprimento: " + varinha.getComprimento());
        System.out.println("Flexibilidade: " + varinha.getFlexibilidade());

        System.out.print("Nova madeira (deixe em branco para manter): ");
        String madeira = scanner.nextLine();
        if (!madeira.isEmpty()) varinha.setMadeira(madeira);

        System.out.print("Novo núcleo (deixe em branco para manter): ");
        String nucleo = scanner.nextLine();
        if (!nucleo.isEmpty()) varinha.setNucleo(nucleo);

        System.out.print("Novo comprimento (0 para manter): ");
        float comprimento = scanner.nextFloat();
        if (comprimento > 0) varinha.setComprimento(comprimento);
        scanner.nextLine(); // Limpar buffer

        System.out.print("Nova flexibilidade (deixe em branco para manter): ");
        String flexibilidade = scanner.nextLine();
        if (!flexibilidade.isEmpty()) varinha.setFlexibilidade(flexibilidade);

        if (varinhaDAO.updateVarinha(varinha)) {
            System.out.println("Varinha atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar varinha!");
        }
    }

    private void excluirVarinha() {
        System.out.println("\n=== EXCLUIR VARINHA ===");
        System.out.print("Digite o ID da varinha a ser excluída: ");
        int id = scanner.nextInt();

        // Verificar se existem bruxos com esta varinha
        ArrayList<Bruxo> bruxosComVarinha;
        try (BruxoDAO bruxoDAO = new BruxoDAO()) {
            bruxosComVarinha = bruxoDAO.selectBruxosByVarinha(id);
        }
        if (!bruxosComVarinha.isEmpty()) {
            System.out.println("Não é possível excluir esta varinha pois existem bruxos associados a ela!");
            System.out.println("Bruxos com esta varinha:");
            for (Bruxo b : bruxosComVarinha) {
                System.out.println("- " + b.getNomeBruxo());
            }
            return;
        }

        System.out.print("Tem certeza que deseja excluir? (S/N): ");
        String confirmacao = scanner.next();

        if (confirmacao.equalsIgnoreCase("S")) {
            if (varinhaDAO.deleteVarinha(id)) {
                System.out.println("Varinha excluída com sucesso!");
            } else {
                System.out.println("Erro ao excluir varinha!");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}
