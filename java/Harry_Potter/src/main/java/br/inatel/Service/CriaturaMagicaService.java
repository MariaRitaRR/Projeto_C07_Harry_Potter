package br.inatel.Service;

import br.inatel.DAO.CriaturaMagicaDAO;
import br.inatel.model.CriaturaMagica;

import java.util.ArrayList;
import java.util.Scanner;
public class CriaturaMagicaService {

    private CriaturaMagicaDAO criaturaDAO;
    private Scanner scanner;

    public CriaturaMagicaService(Scanner scanner) {
        this.scanner = scanner;
        criaturaDAO = new CriaturaMagicaDAO();
    }

    public void menuCriaturas() {
        int opcao;

        do {
            System.out.println("\n=== GERENCIAR CRIATURAS MÁGICAS ===");
            System.out.println("1. Listar todas as criaturas");
            System.out.println("2. Buscar criatura por ID");
            System.out.println("3. Cadastrar nova criatura");
            System.out.println("4. Atualizar criatura");
            System.out.println("5. Excluir criatura");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarTodasCriaturas();
                    break;
                case 2:
                    buscarCriaturaPorId();
                    break;
                case 3:
                    cadastrarNovaCriatura();
                    break;
                case 4:
                    atualizarCriatura();
                    break;
                case 5:
                    excluirCriatura();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void listarTodasCriaturas(){
        System.out.println("\n=== LISTA DE CRIATURAS MÁGICAS ===");
        ArrayList<CriaturaMagica> criaturas = criaturaDAO.selectAllCriaturas();
        for (CriaturaMagica c : criaturas) {
            System.out.println("ID: " + c.getIdCriatura() +
                    " | Nome: " + c.getNome() +
                    " | Periculosidade: " + c.getPericulosidade() +
                    " | Habitat: " + c.getHabitat());
        }
    }

    private void buscarCriaturaPorId() {
        System.out.print("\nDigite o ID da criatura: ");
        int id = scanner.nextInt();
        CriaturaMagica criatura = criaturaDAO.selectCriaturaById(id);
        if (criatura != null) {
            System.out.println("\n=== DADOS DA CRIATURA ===");
            System.out.println("ID: " + criatura.getIdCriatura());
            System.out.println("Nome: " + criatura.getNome());
            System.out.println("Periculosidade: " + criatura.getPericulosidade());
            System.out.println("Habitat: " + criatura.getHabitat());
        } else {
            System.out.println("Criatura não encontrada!");
        }
    }

    private void cadastrarNovaCriatura() {
        System.out.println("\n=== CADASTRAR NOVA CRIATURA ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Periculosidade: ");
        String periculosidade = scanner.nextLine();
        System.out.print("Habitat: ");
        String habitat = scanner.nextLine();

        CriaturaMagica novaCriatura = new CriaturaMagica(nome, periculosidade, habitat);
        if (criaturaDAO.inserir(novaCriatura)) {
            System.out.println("Criatura cadastrada com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar criatura!");
        }
    }

    private void atualizarCriatura() {
        System.out.println("\n=== ATUALIZAR CRIATURA ===");
        System.out.print("Digite o ID da criatura a ser atualizada: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        CriaturaMagica criatura = criaturaDAO.selectCriaturaById(id);
        if (criatura == null) {
            System.out.println("Criatura não encontrada!");
            return;
        }

        System.out.println("Dados atuais:");
        System.out.println("Nome: " + criatura.getNome());
        System.out.println("Periculosidade: " + criatura.getPericulosidade());
        System.out.println("Habitat: " + criatura.getHabitat());

        System.out.print("Novo nome (deixe em branco para manter): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) criatura.setNome(nome);

        System.out.print("Nova periculosidade (deixe em branco para manter): ");
        String periculosidade = scanner.nextLine();
        if (!periculosidade.isEmpty()) criatura.setPericulosidade(periculosidade);

        System.out.print("Novo habitat (deixe em branco para manter): ");
        String habitat = scanner.nextLine();
        if (!habitat.isEmpty()) criatura.setHabitat(habitat);

        if (criaturaDAO.atualizar(criatura)) {
            System.out.println("Criatura atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar criatura!");
        }
    }

    private void excluirCriatura() {
        System.out.println("\n=== EXCLUIR CRIATURA ===");
        System.out.print("Digite o ID da criatura a ser excluída: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        // Verificar se existem bruxos associados
        if (criaturaDAO.hasBruxosAssociados(id)) {
            System.out.println("Não é possível excluir esta criatura pois existem bruxos com interações registradas!");
            return;
        }

        System.out.print("Tem certeza que deseja excluir? (S/N): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            if (criaturaDAO.deletar(id)) {
                System.out.println("Criatura excluída com sucesso!");
            } else {
                System.out.println("Erro ao excluir criatura!");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}
