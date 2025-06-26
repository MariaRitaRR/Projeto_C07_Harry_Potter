package br.inatel;

import br.inatel.DAO.*;
import br.inatel.model.*;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //Testar conexão
        BruxoDAO bruxoDao = new BruxoDAO();
        bruxoDao.connectToDb();

        Scanner scanner = new Scanner(System.in);
        int opcao;

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
                    menuBruxos(scanner);
                    break;
                case 2:
                    menuCasas(scanner);
                    break;
                case 3:
                    menuVarinhas(scanner);
                    break;
                case 4:
                    menuFeiticos(scanner);
                    break;
                case 5:
                    menuCriaturas(scanner);
                    break;
                /*case 6:
                    menuRelatorios(scanner);
                    break;*/
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void menuBruxos(Scanner scanner) {
        BruxoDAO bruxoDAO = new BruxoDAO();
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
                    listarTodosBruxos(bruxoDAO);
                    break;
                case 2:
                    buscarBruxoPorId(scanner, bruxoDAO);
                    break;
                case 3:
                    cadastrarNovoBruxo(scanner, bruxoDAO);
                    break;
                case 4:
                    atualizarBruxo(scanner, bruxoDAO);
                    break;
                case 5:
                    excluirBruxo(scanner, bruxoDAO);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void menuCasas(Scanner scanner) {
        CasaDAO casaDAO = new CasaDAO();
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
                    listarTodasCasas(casaDAO);
                    break;
                case 2:
                    buscarCasaPorId(scanner, casaDAO);
                    break;
                case 3:
                    cadastrarNovaCasa(scanner, casaDAO);
                    break;
                case 4:
                    atualizarCasa(scanner, casaDAO);
                    break;
                case 5:
                    excluirCasa(scanner, casaDAO);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void menuVarinhas(Scanner scanner) {
        VarinhaDAO varinhaDAO = new VarinhaDAO();
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
                    listarTodasVarinhas(varinhaDAO);
                    break;
                case 2:
                    buscarVarinhaPorId(scanner, varinhaDAO);
                    break;
                case 3:
                    cadastrarNovaVarinha(scanner, varinhaDAO);
                    break;
                case 4:
                    atualizarVarinha(scanner, varinhaDAO);
                    break;
                case 5:
                    excluirVarinha(scanner, varinhaDAO);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void menuFeiticos(Scanner scanner) {
        FeiticoDAO feiticoDAO = new FeiticoDAO();
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
                    listarTodosFeiticos(feiticoDAO);
                    break;
                case 2:
                    buscarFeiticoPorId(scanner, feiticoDAO);
                    break;
                case 3:
                    cadastrarNovoFeitico(scanner, feiticoDAO);
                    break;
                case 4:
                    atualizarFeitico(scanner, feiticoDAO);
                    break;
                case 5:
                    excluirFeitico(scanner, feiticoDAO);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void menuCriaturas(Scanner scanner) {
        CriaturaMagicaDAO criaturaDAO = new CriaturaMagicaDAO();
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
                    listarTodasCriaturas(criaturaDAO);
                    break;
                case 2:
                    buscarCriaturaPorId(scanner, criaturaDAO);
                    break;
                case 3:
                    cadastrarNovaCriatura(scanner, criaturaDAO);
                    break;
                case 4:
                    atualizarCriatura(scanner, criaturaDAO);
                    break;
                case 5:
                    excluirCriatura(scanner, criaturaDAO);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    /* private static void menuRelatorios(Scanner scanner) {
         int opcao;

         do {
             System.out.println("\n=== RELATÓRIOS AVANÇADOS ===");
             System.out.println("1. Bruxos com informações completas (VIEW)");
             System.out.println("2. Bruxos e seus feitiços");
             System.out.println("3. Bruxos e suas interações com criaturas");
             System.out.println("4. Feitiços mais comuns entre os bruxos");
             System.out.println("5. Criaturas por nível de periculosidade");
             System.out.println("0. Voltar ao menu principal");
             System.out.print("Escolha uma opção: ");

             opcao = scanner.nextInt();
             scanner.nextLine();

             switch (opcao) {
                 case 1:
                     relatorioBruxosCompletos();
                     break;
                 case 2:
                     relatorioBruxosFeiticos();
                     break;
                 case 3:
                     relatorioBruxosCriaturas();
                     break;
                 case 4:
                     relatorioFeiticosComuns();
                     break;
                 case 5:
                     relatorioCriaturasPorPericulosidade();
                     break;
                 case 0:
                     break;
                 default:
                     System.out.println("Opção inválida!");
             }
         } while (opcao != 0);
     }
 */
    // Métodos para operações com Bruxos
    private static void listarTodosBruxos(BruxoDAO bruxoDAO) {
        System.out.println("\n=== LISTA DE BRUXOS ===");
        ArrayList<Bruxo> bruxos = bruxoDAO.selectAllBruxos();
        for (Bruxo b : bruxos) {
            System.out.println(b.toString());
        }
    }

    private static void buscarBruxoPorId(Scanner scanner, BruxoDAO bruxoDAO) {
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

    private static void cadastrarNovoBruxo(Scanner scanner, BruxoDAO bruxoDAO) {
        System.out.println("\n=== CADASTRAR NOVO BRUXO ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        System.out.print("ID da Casa: ");
        int idCasa = scanner.nextInt();
        System.out.print("ID da Varinha (0 se não tiver): ");
        int idVarinha = scanner.nextInt();

        Bruxo novoBruxo = new Bruxo(nome, idade, idCasa, idVarinha);
        if (bruxoDAO.inserir(novoBruxo)) {
            System.out.println("Bruxo cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar bruxo!");
        }
    }

    private static void atualizarBruxo(Scanner scanner, BruxoDAO bruxoDAO) {
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

    private static void excluirBruxo(Scanner scanner, BruxoDAO bruxoDAO) {
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

    // Métodos para operações com Casas
    private static void listarTodasCasas(CasaDAO casaDAO) {
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

    private static void buscarCasaPorId(Scanner scanner, CasaDAO casaDAO) {
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

    private static void cadastrarNovaCasa(Scanner scanner, CasaDAO casaDAO) {
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

    private static void atualizarCasa(Scanner scanner, CasaDAO casaDAO) {
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

    private static void excluirCasa(Scanner scanner, CasaDAO casaDAO) {
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

    // Métodos para operações com Varinhas (implementar de forma similar)
    private static void listarTodasVarinhas(VarinhaDAO varinhaDAO) {
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

    private static void buscarVarinhaPorId(Scanner scanner, VarinhaDAO varinhaDAO) {
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

    private static void cadastrarNovaVarinha(Scanner scanner, VarinhaDAO varinhaDAO) {
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

    private static void atualizarVarinha(Scanner scanner, VarinhaDAO varinhaDAO) {
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

    private static void excluirVarinha(Scanner scanner, VarinhaDAO varinhaDAO) {
        System.out.println("\n=== EXCLUIR VARINHA ===");
        System.out.print("Digite o ID da varinha a ser excluída: ");
        int id = scanner.nextInt();

        // Verificar se existem bruxos com esta varinha
        BruxoDAO bruxoDAO = new BruxoDAO();
        ArrayList<Bruxo> bruxosComVarinha = bruxoDAO.selectBruxosByVarinha(id);
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

    // Métodos para operações com Feitiços (implementar de forma similar)
    private static void listarTodosFeiticos(FeiticoDAO feiticoDAO) {
        System.out.println("\n=== LISTA DE FEITIÇOS ===");
        ArrayList<Feitico> feiticos = feiticoDAO.selectAllFeiticos();
        for (Feitico f : feiticos) {
            System.out.println("ID: " + f.getIdFeitico() +
                    " | Nome: " + f.getNome() +
                    " | Efeito: " + f.getEfeito() +
                    " | Dificuldade: " + f.getNivelDificuldade());
        }
    }

    private static void buscarFeiticoPorId(Scanner scanner, FeiticoDAO feiticoDAO) {
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

    private static void cadastrarNovoFeitico(Scanner scanner, FeiticoDAO feiticoDAO) {
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

    private static void atualizarFeitico(Scanner scanner, FeiticoDAO feiticoDAO) {
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

    private static void excluirFeitico(Scanner scanner, FeiticoDAO feiticoDAO) {
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

    // Métodos para operações com Criaturas (implementar de forma similar)
    private static void listarTodasCriaturas(CriaturaMagicaDAO criaturaDAO){
        System.out.println("\n=== LISTA DE CRIATURAS MÁGICAS ===");
        ArrayList<CriaturaMagica> criaturas = criaturaDAO.selectAllCriaturas();
        for (CriaturaMagica c : criaturas) {
            System.out.println("ID: " + c.getIdCriatura() +
                    " | Nome: " + c.getNome() +
                    " | Periculosidade: " + c.getPericulosidade() +
                    " | Habitat: " + c.getHabitat());
        }
    }

    private static void buscarCriaturaPorId(Scanner scanner, CriaturaMagicaDAO criaturaDAO) {
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

    private static void cadastrarNovaCriatura(Scanner scanner, CriaturaMagicaDAO criaturaDAO) {
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

    private static void atualizarCriatura(Scanner scanner, CriaturaMagicaDAO criaturaDAO) {
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

    private static void excluirCriatura(Scanner scanner, CriaturaMagicaDAO criaturaDAO) {
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

    // Métodos para relatórios
    private static void relatorioBruxosCompletos() {
        System.out.println("\n=== BRUXOS COM INFORMAÇÕES COMPLETAS ===");
        try {
            ConnectionDAO connection = new ConnectionDAO();
            connection.connectToDb();

            String sql = "SELECT * FROM vw_BruxosCompletos";
            connection.st = connection.con.createStatement();
            connection.rs = connection.st.executeQuery(sql);

            while (connection.rs.next()) {
                System.out.println(
                        "ID: " + connection.rs.getInt("idBruxos") + " | " +
                                "Nome: " + connection.rs.getString("Nome_Bruxo") + " | " +
                                "Idade: " + connection.rs.getInt("idade") + " | " +
                                "Casa: " + connection.rs.getString("Casa") + " | " +
                                "Fundador: " + connection.rs.getString("Fundador_Casa") + " | " +
                                "Varinha: " + connection.rs.getString("Madeira_Varinha") + " com " +
                                connection.rs.getString("Nucleo_Varinha")
                );
            }

            connection.con.close();
            connection.st.close();
            connection.rs.close();
        } catch (SQLException e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
        }
    }

    private static void relatorioBruxosFeiticos() {
        System.out.println("\n=== BRUXOS E SEUS FEITIÇOS ===");
        try {
            ConnectionDAO connection = new ConnectionDAO();
            connection.connectToDb();

            String sql = "SELECT b.nome AS Bruxo, f.nome AS Feitico, fb.nivelPericia " +
                    "FROM Bruxos b " +
                    "JOIN Feiticos_has_Bruxos fb ON b.idBruxos = fb.Bruxos_idBruxos " +
                    "JOIN Feiticos f ON fb.Feiticos_idFeitico = f.idFeitico " +
                    "ORDER BY b.nome, f.nome";

            connection.st = connection.con.createStatement();
            connection.rs = connection.st.executeQuery(sql);

            String bruxoAtual = "";
            while (connection.rs.next()) {
                String bruxo = connection.rs.getString("Bruxo");
                if (!bruxo.equals(bruxoAtual)) {
                    System.out.println("\n" + bruxo + ":");
                    bruxoAtual = bruxo;
                }
                System.out.println("- " + connection.rs.getString("Feitico") +
                        " (Nível: " + connection.rs.getString("nivelPericia") + ")");
            }

            connection.con.close();
            connection.st.close();
            connection.rs.close();
        } catch (SQLException e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
        }
    }

    private static void relatorioBruxosCriaturas() {
        System.out.println("\n=== INTERAÇÕES DE BRUXOS COM CRIATURAS ===");
        try {
            ConnectionDAO connection = new ConnectionDAO();
            connection.connectToDb();

            String sql = "SELECT b.nome AS Bruxo, c.nome AS Criatura, bc.dataDeEncontro, bc.statusDeInteracao " +
                    "FROM Bruxos b " +
                    "JOIN Bruxos_has_CriaturasMagicas bc ON b.idBruxos = bc.Bruxos_idBruxos " +
                    "JOIN CriaturasMagicas c ON bc.CriaturasMagicas_idCriatura = c.idCriatura " +
                    "ORDER BY b.nome, c.nome";

            connection.st = connection.con.createStatement();
            connection.rs = connection.st.executeQuery(sql);

            String bruxoAtual = "";
            while (connection.rs.next()) {
                String bruxo = connection.rs.getString("Bruxo");
                if (!bruxo.equals(bruxoAtual)) {
                    System.out.println("\n" + bruxo + ":");
                    bruxoAtual = bruxo;
                }
                System.out.println("- " + connection.rs.getString("Criatura") +
                        " (Encontro: " + connection.rs.getDate("dataDeEncontro") +
                        ", Status: " + connection.rs.getString("statusDeInteracao") + ")");
            }

            connection.con.close();
            connection.st.close();
            connection.rs.close();
        } catch (SQLException e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
        }
    }

    private static void relatorioFeiticosComuns() {
        System.out.println("\n=== FEITIÇOS MAIS COMUNS ENTRE BRUXOS ===");
        try {
            ConnectionDAO connection = new ConnectionDAO();
            connection.connectToDb();

            String sql = "SELECT f.nome AS Feitico, COUNT(fb.Bruxos_idBruxos) AS TotalBruxos " +
                    "FROM Feiticos f " +
                    "LEFT JOIN Feiticos_has_Bruxos fb ON f.idFeitico = fb.Feiticos_idFeitico " +
                    "GROUP BY f.nome " +
                    "ORDER BY TotalBruxos DESC, f.nome";

            connection.st = connection.con.createStatement();
            connection.rs = connection.st.executeQuery(sql);

            while (connection.rs.next()) {
                System.out.println(
                        connection.rs.getString("Feitico") + ": " +
                                connection.rs.getInt("TotalBruxos") + " bruxos conhecem"
                );
            }

            connection.con.close();
            connection.st.close();
            connection.rs.close();
        } catch (SQLException e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
        }
    }

    private static void relatorioCriaturasPorPericulosidade() {
        System.out.println("\n=== CRIATURAS POR NÍVEL DE PERICULOSIDADE ===");
        try {
            ConnectionDAO connection = new ConnectionDAO();
            connection.connectToDb();

            String sql = "SELECT periculosidade, COUNT(idCriatura) AS Total, " +
                    "GROUP_CONCAT(nome SEPARATOR ', ') AS Criaturas " +
                    "FROM CriaturasMagicas " +
                    "GROUP BY periculosidade " +
                    "ORDER BY CASE periculosidade " +
                    "WHEN 'Extremamente perigoso' THEN 1 " +
                    "WHEN 'Muito perigoso' THEN 2 " +
                    "WHEN 'Moderadamente perigoso' THEN 3 " +
                    "WHEN 'Pouco perigoso' THEN 4 " +
                    "ELSE 5 END";

            connection.st = connection.con.createStatement();
            connection.rs = connection.st.executeQuery(sql);

            while (connection.rs.next()) {
                System.out.println(
                        "\n" + connection.rs.getString("periculosidade") + " (" +
                                connection.rs.getInt("Total") + " criaturas):\n" +
                                connection.rs.getString("Criaturas")
                );
            }

            connection.con.close();
            connection.st.close();
            connection.rs.close();
        } catch (SQLException e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
        }
    }
}