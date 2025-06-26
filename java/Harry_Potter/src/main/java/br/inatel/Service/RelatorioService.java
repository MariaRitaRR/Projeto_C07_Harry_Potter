package br.inatel.Service;

import br.inatel.DAO.*;
import java.sql.SQLException;
import java.util.Scanner;

public class RelatorioService {

    private Scanner scanner;
    public RelatorioService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void menuRelatorios() {
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


    private void relatorioBruxosCompletos() {
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

    private void relatorioBruxosFeiticos() {
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

    private void relatorioBruxosCriaturas() {
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

    private void relatorioFeiticosComuns() {
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

    private void relatorioCriaturasPorPericulosidade() {
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
