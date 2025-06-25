package br.inatel.DAO;

import br.inatel.model.Feitico;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeiticoDAO extends ConnectionDAO {
    public boolean inserir(Feitico feitico) {
        connectToDb();
        String sql = "INSERT INTO Feiticos (nome, efeito, nivelDificuldade) VALUES (?,?,?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, feitico.getNome());
            pst.setString(2, feitico.getEfeito());
            pst.setString(3, feitico.getNivelDificuldade());
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

    public boolean updateFeitico(Feitico feitico) {
        connectToDb();
        String sql = "UPDATE Feiticos SET nome=?, efeito=?, nivelDificuldade=? WHERE idFeitico=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, feitico.getNome());
            pst.setString(2, feitico.getEfeito());
            pst.setString(3, feitico.getNivelDificuldade());
            pst.setInt(4, feitico.getIdFeitico());
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

    public boolean deleteFeitico(int idFeitico) {
        connectToDb();
        String sql = "DELETE FROM Feiticos WHERE idFeitico=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idFeitico);
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

    public ArrayList<Feitico> selectAllFeiticos() {
        connectToDb();
        ArrayList<Feitico> feiticos = new ArrayList<>();
        String sql = "SELECT * FROM Feiticos";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Feitico feitico = new Feitico(
                        rs.getString("nome"),
                        rs.getString("efeito"),
                        rs.getString("nivelDificuldade")
                );
                feitico.setIdFeitico(rs.getInt("idFeitico"));
                feiticos.add(feitico);
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
        return feiticos;
    }

    public Feitico selectFeiticoById(int idFeitico) {
        connectToDb();
        String sql = "SELECT * FROM Feiticos WHERE idFeitico = ?";
        Feitico feitico = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idFeitico);
            rs = pst.executeQuery();

            if (rs.next()) {
                feitico = new Feitico(
                        rs.getString("nome"),
                        rs.getString("efeito"),
                        rs.getString("nivelDificuldade")
                );
                feitico.setIdFeitico(rs.getInt("idFeitico"));
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
        return feitico;
    }

    public ArrayList<Feitico> selectFeiticosPorDificuldade(String dificuldade) {
        connectToDb();
        ArrayList<Feitico> feiticos = new ArrayList<>();
        String sql = "SELECT * FROM Feiticos WHERE nivelDificuldade = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, dificuldade);
            rs = pst.executeQuery();

            while (rs.next()) {
                Feitico feitico = new Feitico(
                        rs.getString("nome"),
                        rs.getString("efeito"),
                        rs.getString("nivelDificuldade")
                );
                feitico.setIdFeitico(rs.getInt("idFeitico"));
                feiticos.add(feitico);
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
        return feiticos;
    }
    // Adicione estes métodos à classe FeiticoDAO

    public ArrayList<Feitico> selectFeiticosPorBruxo(int idBruxo) {
        connectToDb();
        ArrayList<Feitico> feiticos = new ArrayList<>();
        String sql = "SELECT f.*, fb.nivelPericia FROM Feiticos f " +
                "JOIN Feiticos_has_Bruxos fb ON f.idFeitico = fb.Feiticos_idFeitico " +
                "WHERE fb.Bruxos_idBruxos = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idBruxo);
            rs = pst.executeQuery();

            while (rs.next()) {
                Feitico feitico = new Feitico(
                        rs.getString("nome"),
                        rs.getString("efeito"),
                        rs.getString("nivelDificuldade")
                );
                feitico.setIdFeitico(rs.getInt("idFeitico"));
                feiticos.add(feitico);
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
        return feiticos;
    }
    public boolean hasBruxosAssociados(int idFeitico) {
        connectToDb();
        String sql = "SELECT COUNT(*) FROM Feiticos_has_Bruxos WHERE Feiticos_idFeitico = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idFeitico);
            rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar dependências: " + e.getMessage());
        } finally {
            try {
                con.close();
                pst.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean associarBruxo(int idBruxo, int idFeitico, String nivelPericia) {
        connectToDb();
        String sql = "INSERT INTO Feiticos_has_Bruxos (Feiticos_idFeitico, Bruxos_idBruxos, nivelPericia) VALUES (?,?,?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idFeitico);
            pst.setInt(2, idBruxo);
            pst.setString(3, nivelPericia);
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

    public boolean atualizarPericiaBruxo(int idBruxo, int idFeitico, String novoNivelPericia) {
        connectToDb();
        String sql = "UPDATE Feiticos_has_Bruxos SET nivelPericia=? WHERE Bruxos_idBruxos=? AND Feiticos_idFeitico=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoNivelPericia);
            pst.setInt(2, idBruxo);
            pst.setInt(3, idFeitico);
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
}