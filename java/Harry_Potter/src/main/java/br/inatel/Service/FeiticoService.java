package br.inatel.Service;

import br.inatel.DAO.FeiticoDAO;
import br.inatel.model.Feitico;
import java.util.ArrayList;
import java.util.Scanner;

public class FeiticoService {
    private FeiticoDAO feiticoDAO;
    private Scanner scanner;

    public FeiticoService(Scanner scanner) {
        this.scanner = scanner;
        this.feiticoDAO = new FeiticoDAO();
    }

    public void menuFeiticos() {

        int opcao;

        do {
            System.out.println("\n=== GERENCIAR FEITIÇOS ===");
            System.out.println("1. Listar todos os feitiços");
            System.out.println("2. Buscar feitiço por ID");
            System.out.println("3. Cadastrar novo feitiço");
            System.out.println("4. Atualizar feitiço");
            System.out.println("5. Excluir feitiço");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarTodosFeiticos();
                    break;
                case 2:
                    buscarFeiticoPorId();
                    break;
                case 3:
                    cadastrarNovoFeitico();
                    break;
                case 4:
                    atualizarFeitico();
                    break;
                case 5:
                    excluirFeitico();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void listarTodosFeiticos() {
        System.out.println("\n=== LISTA DE FEITIÇOS ===");
        ArrayList<Feitico> feiticos = feiticoDAO.selectAllFeiticos();
        for (Feitico f : feiticos) {
            System.out.println("ID: " + f.getIdFeitico() +
                    " | Nome: " + f.getNome() +
                    " | Efeito: " + f.getEfeito() +
                    " | Dificuldade: " + f.getNivelDificuldade());
        }
    }

    private void buscarFeiticoPorId() {
        System.out.print("\nDigite o ID do feitiço: ");
        int id = scanner.nextInt();
        Feitico feitico = feiticoDAO.selectFeiticoById(id);
        if (feitico != null) {
            System.out.println("\n=== DADOS DO FEITIÇO ===");
            System.out.println("ID: " + feitico.getIdFeitico());
            System.out.println("Nome: " + feitico.getNome());
            System.out.println("Efeito: " + feitico.getEfeito());
            System.out.println("Nível de Dificuldade: " + feitico.getNivelDificuldade());
        } else {
            System.out.println("Feitiço não encontrado!");
        }
    }

    private void cadastrarNovoFeitico() {
        System.out.println("\n=== CADASTRAR NOVO FEITIÇO ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Efeito: ");
        String efeito = scanner.nextLine();
        System.out.print("Nível de Dificuldade: ");
        String dificuldade = scanner.nextLine();

        Feitico novoFeitico = new Feitico(nome, efeito, dificuldade);
        if (feiticoDAO.inserir(novoFeitico)) {
            System.out.println("Feitiço cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar feitiço!");
        }
    }

    private void atualizarFeitico() {
        System.out.println("\n=== ATUALIZAR FEITIÇO ===");
        System.out.print("Digite o ID do feitiço a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        Feitico feitico = feiticoDAO.selectFeiticoById(id);
        if (feitico == null) {
            System.out.println("Feitiço não encontrado!");
            return;
        }

        System.out.println("Dados atuais:");
        System.out.println("Nome: " + feitico.getNome());
        System.out.println("Efeito: " + feitico.getEfeito());
        System.out.println("Dificuldade: " + feitico.getNivelDificuldade());

        System.out.print("Novo nome (deixe em branco para manter): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) feitico.setNome(nome);

        System.out.print("Novo efeito (deixe em branco para manter): ");
        String efeito = scanner.nextLine();
        if (!efeito.isEmpty()) feitico.setEfeito(efeito);

        System.out.print("Nova dificuldade (deixe em branco para manter): ");
        String dificuldade = scanner.nextLine();
        if (!dificuldade.isEmpty()) feitico.setNivelDificuldade(dificuldade);

        if (feiticoDAO.updateFeitico(feitico)) {
            System.out.println("Feitiço atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar feitiço!");
        }
    }

    private void excluirFeitico() {
        System.out.println("\n=== EXCLUIR FEITIÇO ===");
        System.out.print("Digite o ID do feitiço a ser excluído: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        // Verificar se existem bruxos que conhecem o feitiço
        Feitico feitico = feiticoDAO.selectFeiticoById(id);
        if (feitico == null) {
            System.out.println("Feitiço não encontrado!");
            return;
        }

        // Verificar dependências usando o próprio DAO
        if (feiticoDAO.hasBruxosAssociados(id)) {
            System.out.println("Não é possível excluir este feitiço pois existem bruxos que o conhecem!");
            return;
        }

        System.out.print("Tem certeza que deseja excluir? (S/N): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            if (feiticoDAO.deleteFeitico(id)) {
                System.out.println("Feitiço excluído com sucesso!");
            } else {
                System.out.println("Erro ao excluir feitiço!");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}
