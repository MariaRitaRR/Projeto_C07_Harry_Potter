package br.inatel.Service;

import br.inatel.DAO.BruxoDAO;
import br.inatel.DAO.CasaDAO;
import br.inatel.model.Bruxo;
import br.inatel.model.Casa;
import java.util.ArrayList;
import java.util.Scanner;

public class CasaService {
    private CasaDAO casaDAO;
    private Scanner scanner;

    public CasaService(Scanner scanner) {
        this.casaDAO = new CasaDAO();
        this.scanner = scanner;
    }
    public void menuCasas() {
        int opcao;

        do {
            System.out.println("\n=== GERENCIAR CASAS ===");
            System.out.println("1. Listar todas as casas");
            System.out.println("2. Buscar casa por ID");
            System.out.println("3. Cadastrar nova casa");
            System.out.println("4. Atualizar casa");
            System.out.println("5. Excluir casa");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarTodasCasas();
                    break;
                case 2:
                    buscarCasaPorId();
                    break;
                case 3:
                    cadastrarNovaCasa();
                    break;
                case 4:
                    atualizarCasa();
                    break;
                case 5:
                    excluirCasa();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
    private void listarTodasCasas() {
        System.out.println("\n=== LISTA DE CASAS ===");
        ArrayList<Casa> casas = casaDAO.selectAllCasas();
        for (Casa c : casas) {
            System.out.println("ID: " + c.getIdCasa() +
                    " | Nome: " + c.getNome() +
                    " | Fundador: " + c.getFundador() +
                    " | Cores: " + c.getCores() +
                    " | Mascote: " + c.getMascote());
        }
    }

    private void buscarCasaPorId() {
        System.out.print("\nDigite o ID da casa: ");
        int id = scanner.nextInt();
        Casa casa = casaDAO.selectCasaById(id);
        if (casa != null) {
            System.out.println("\n=== DADOS DA CASA ===");
            System.out.println("ID: " + casa.getIdCasa());
            System.out.println("Nome: " + casa.getNome());
            System.out.println("Fundador: " + casa.getFundador());
            System.out.println("Cores: " + casa.getCores());
            System.out.println("Mascote: " + casa.getMascote());
            System.out.println("Fantasma: " + casa.getFantasma());
        } else {
            System.out.println("Casa não encontrada!");
        }
    }

    private void cadastrarNovaCasa() {
        System.out.println("\n=== CADASTRAR NOVA CASA ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Fundador: ");
        String fundador = scanner.nextLine();
        System.out.print("Cores: ");
        String cores = scanner.nextLine();
        System.out.print("Mascote: ");
        String mascote = scanner.nextLine();
        System.out.print("Fantasma: ");
        String fantasma = scanner.nextLine();

        Casa novaCasa = new Casa(nome, fundador, cores, mascote, fantasma);
        if (casaDAO.inserir(novaCasa)) {
            System.out.println("Casa cadastrada com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar casa!");
        }
    }

    private void atualizarCasa() {
        System.out.println("\n=== ATUALIZAR CASA ===");
        System.out.print("Digite o ID da casa a ser atualizada: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        Casa casa = casaDAO.selectCasaById(id);
        if (casa == null) {
            System.out.println("Casa não encontrada!");
            return;
        }

        System.out.println("Dados atuais:");
        System.out.println("Nome: " + casa.getNome());
        System.out.println("Fundador: " + casa.getFundador());
        System.out.println("Cores: " + casa.getCores());
        System.out.println("Mascote: " + casa.getMascote());
        System.out.println("Fantasma: " + casa.getFantasma());

        System.out.print("Novo nome (deixe em branco para manter): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) casa.setNome(nome);

        System.out.print("Novo fundador (deixe em branco para manter): ");
        String fundador = scanner.nextLine();
        if (!fundador.isEmpty()) casa.setFundador(fundador);

        System.out.print("Novas cores (deixe em branco para manter): ");
        String cores = scanner.nextLine();
        if (!cores.isEmpty()) casa.setCores(cores);

        System.out.print("Novo mascote (deixe em branco para manter): ");
        String mascote = scanner.nextLine();
        if (!mascote.isEmpty()) casa.setMascote(mascote);

        System.out.print("Novo fantasma (deixe em branco para manter): ");
        String fantasma = scanner.nextLine();
        if (!fantasma.isEmpty()) casa.setFantasma(fantasma);

        if (casaDAO.updateCasa(casa)) {
            System.out.println("Casa atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar casa!");
        }
    }

    private void excluirCasa() {
        System.out.println("\n=== EXCLUIR CASA ===");
        System.out.print("Digite o ID da casa a ser excluída: ");
        int id = scanner.nextInt();

        // Verificar se existem bruxos nesta casa
        BruxoDAO bruxoDAO = new BruxoDAO();
        ArrayList<Bruxo> bruxosNaCasa = bruxoDAO.selectBruxosByCasa(id);
        if (!bruxosNaCasa.isEmpty()) {
            System.out.println("Não é possível excluir esta casa pois existem bruxos associados a ela!");
            System.out.println("Bruxos nesta casa:");
            for (Bruxo b : bruxosNaCasa) {
                System.out.println("- " + b.getNomeBruxo());
            }
            return;
        }

        System.out.print("Tem certeza que deseja excluir? (S/N): ");
        String confirmacao = scanner.next();

        if (confirmacao.equalsIgnoreCase("S")) {
            if (casaDAO.deleteCasa(id)) {
                System.out.println("Casa excluída com sucesso!");
            } else {
                System.out.println("Erro ao excluir casa!");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }

}
