package br.inatel.Service;

import br.inatel.DAO.BruxoDAO;
import br.inatel.model.Bruxo;

import java.util.ArrayList;
import java.util.Scanner;
public class BruxoService {
    private BruxoDAO bruxoDAO;
    private Scanner scanner;

    public BruxoService(Scanner scanner) {
        this.bruxoDAO = new BruxoDAO();
        this.scanner = scanner;
    }

    public void menuBruxo() {
        int opcao;
        do {
            System.out.println("\n=== GERENCIAR BRUXOS ===");
            System.out.println("1. Listar todos os bruxos");
            System.out.println("2. Buscar bruxo por ID");
            System.out.println("3. Cadastrar novo bruxo");
            System.out.println("4. Atualizar bruxo");
            System.out.println("5. Excluir bruxo");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarTodosBruxos();
                    break;
                case 2:
                    buscarBruxoPorId();
                    break;
                case 3:
                    cadastrarNovoBruxo();
                    break;
                case 4:
                    atualizarBruxo();
                    break;
                case 5:
                    excluirBruxo();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        }while (opcao != 0) ;
    }
    private void listarTodosBruxos() {
        System.out.println("\n=== LISTA DE BRUXOS ===");
        ArrayList<Bruxo> bruxos = bruxoDAO.selectAllBruxos();
        for (Bruxo b : bruxos) {
            System.out.println(b.toString());
        }
    }
    private void buscarBruxoPorId() {
        System.out.print("\nDigite o ID do bruxo: ");
        int id = scanner.nextInt();
        Bruxo bruxo = bruxoDAO.selectBruxoById(id);
        if (bruxo != null) {
            System.out.println("\n=== DADOS DO BRUXO ===");
            System.out.println(bruxo.toString());
        } else {
            System.out.println("Bruxo não encontrado!");
        }
    }
    private void cadastrarNovoBruxo() {
        System.out.println("\n=== CADASTRAR NOVO BRUXO ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        System.out.print("ID da Casa: ");
        int idCasa = scanner.nextInt();
        System.out.print("ID da Varinha: ");
        int idVarinha = scanner.nextInt();

        Bruxo novoBruxo = new Bruxo(nome, idade, idCasa, idVarinha);
        if (bruxoDAO.inserir(novoBruxo)) {
            System.out.println("Bruxo cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar bruxo!");
        }
    }
    private void atualizarBruxo() {
        System.out.println("\n=== ATUALIZAR BRUXO ===");
        System.out.print("Digite o ID do bruxo a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        Bruxo bruxo = bruxoDAO.selectBruxoById(id);
        if (bruxo == null) {
            System.out.println("Bruxo não encontrado!");
            return;
        }

        System.out.println("Dados atuais:");
        System.out.println(bruxo.toString());

        System.out.print("Novo nome (deixe em branco para manter): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) bruxo.setNomeBruxo(nome);

        System.out.print("Nova idade (0 para manter): ");
        int idade = scanner.nextInt();
        if (idade > 0) bruxo.setIdade(idade);

        System.out.print("Novo ID da Casa (0 para manter): ");
        int idCasa = scanner.nextInt();
        if (idCasa > 0) bruxo.setIdCasa(idCasa);

        System.out.print("Novo ID da Varinha (-1 para manter, 0 para remover): ");
        int idVarinha = scanner.nextInt();
        if (idVarinha >= 0) bruxo.setIdVarinha(idVarinha);

        if (bruxoDAO.updateBruxo(bruxo)) {
            System.out.println("Bruxo atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar bruxo!");
        }
    }
    private void excluirBruxo() {
        System.out.println("\n=== EXCLUIR BRUXO ===");
        System.out.print("Digite o ID do bruxo a ser excluído: ");
        int id = scanner.nextInt();

        System.out.print("Tem certeza que deseja excluir? (S/N): ");
        String confirmacao = scanner.next();

        if (confirmacao.equalsIgnoreCase("S")) {
            if (bruxoDAO.deleteBruxo(id)) {
                System.out.println("Bruxo excluído com sucesso!");
            } else {
                System.out.println("Erro ao excluir bruxo!");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }

}
