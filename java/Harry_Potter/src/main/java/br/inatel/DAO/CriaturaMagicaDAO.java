package br.inatel.DAO;

import br.inatel.Model.CriaturaMagica;

import java.sql.SQLException;
import java.util.ArrayList;

public class CriaturaMagicaDAO extends ConnectionDAO {
    // Métodos CRUD básicos
    public boolean inserir(CriaturaMagica criatura) {
        connectToDb();
        String sql = "INSERT INTO CriaturasMagicas (nome, periculosidade, habitat) VALUES (?,?,?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, criatura.getNome());
            pst.setString(2, criatura.getPericulosidade());
            pst.setString(3, criatura.getHabitat());
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    public boolean atualizar(CriaturaMagica criatura) {
        connectToDb();
        String sql = "UPDATE CriaturasMagicas SET nome=?, periculosidade=?, habitat=? WHERE idCriatura=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, criatura.getNome());
            pst.setString(2, criatura.getPericulosidade());
            pst.setString(3, criatura.getHabitat());
            pst.setInt(4, criatura.getIdCriatura());
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    public boolean deletar(int idCriatura) {
        connectToDb();
        String sql = "DELETE FROM CriaturasMagicas WHERE idCriatura=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCriatura);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    // Métodos de consulta
    public ArrayList<CriaturaMagica> selectAllCriaturas() {
        connectToDb();
        ArrayList<CriaturaMagica> criaturas = new ArrayList<>();
        String sql = "SELECT * FROM CriaturasMagicas";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                CriaturaMagica criatura = new CriaturaMagica(
                        rs.getString("nome"),
                        rs.getString("periculosidade"),
                        rs.getString("habitat")
                );
                criatura.setIdCriatura(rs.getInt("idCriatura"));
                criaturas.add(criatura);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try {
                con.close();
                st.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return criaturas;
    }

    public CriaturaMagica selectCriaturaById(int idCriatura) {
        connectToDb();
        String sql = "SELECT * FROM CriaturasMagicas WHERE idCriatura = ?";
        CriaturaMagica criatura = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCriatura);
            rs = pst.executeQuery();

            if (rs.next()) {
                criatura = new CriaturaMagica(
                        rs.getString("nome"),
                        rs.getString("periculosidade"),
                        rs.getString("habitat")
                );
                criatura.setIdCriatura(rs.getInt("idCriatura"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try {
                con.close();
                pst.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return criatura;
    }

    public ArrayList<CriaturaMagica> selectCriaturasPorPericulosidade(String periculosidade) {
        connectToDb();
        ArrayList<CriaturaMagica> criaturas = new ArrayList<>();
        String sql = "SELECT * FROM CriaturasMagicas WHERE periculosidade = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, periculosidade);
            rs = pst.executeQuery();

            while (rs.next()) {
                CriaturaMagica criatura = new CriaturaMagica(
                        rs.getString("nome"),
                        rs.getString("periculosidade"),
                        rs.getString("habitat")
                );
                criatura.setIdCriatura(rs.getInt("idCriatura"));
                criaturas.add(criatura);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try {
                con.close();
                pst.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return criaturas;
    }

    // Métodos para relações com Bruxos
    public ArrayList<CriaturaMagica> selectCriaturasPorBruxo(int idBruxo) {
        connectToDb();
        ArrayList<CriaturaMagica> criaturas = new ArrayList<>();
        String sql = "SELECT cm.* FROM CriaturasMagicas cm " +
                "JOIN Bruxos_has_CriaturasMagicas bcm ON cm.idCriatura = bcm.CriaturasMagicas_idCriatura " +
                "WHERE bcm.Bruxos_idBruxos = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idBruxo);
            rs = pst.executeQuery();

            while (rs.next()) {
                CriaturaMagica criatura = new CriaturaMagica(
                        rs.getString("nome"),
                        rs.getString("periculosidade"),
                        rs.getString("habitat")
                );
                criatura.setIdCriatura(rs.getInt("idCriatura"));
                criaturas.add(criatura);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try {
                con.close();
                pst.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return criaturas;
    }

    public boolean associarBruxo(int idBruxo, int idCriatura, String dataEncontro, String statusInteracao) {
        connectToDb();
        String sql = "INSERT INTO Bruxos_has_CriaturasMagicas (Bruxos_idBruxos, CriaturasMagicas_idCriatura, dataDeEncontro, statusDeInteracao) VALUES (?,?,?,?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idBruxo);
            pst.setInt(2, idCriatura);
            pst.setString(3, dataEncontro);
            pst.setString(4, statusInteracao);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    // Método para verificar se existem bruxos associados
    public boolean hasBruxosAssociados(int idCriatura) {
        connectToDb();
        String sql = "SELECT COUNT(*) FROM Bruxos_has_CriaturasMagicas WHERE CriaturasMagicas_idCriatura = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCriatura);
            rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try {
                con.close();
                pst.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return false;
    }
}