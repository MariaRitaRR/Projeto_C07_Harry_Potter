package br.inatel.DAO;

import br.inatel.Model.Casa;
import java.sql.SQLException;
import java.util.ArrayList;

public class CasaDAO extends ConnectionDAO {
    public boolean inserir(Casa casa) {
        connectToDb();
        String sql = "INSERT INTO Casas (nome, fundador, cores, mascote, fantasma) VALUES (?,?,?,?,?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, casa.getNome());
            pst.setString(2, casa.getFundador());
            pst.setString(3, casa.getCores());
            pst.setString(4, casa.getMascote());
            pst.setString(5, casa.getFantasma());
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

    public boolean updateCasa(Casa casa) {
        connectToDb();
        String sql = "UPDATE Casas SET nome=?, fundador=?, cores=?, mascote=?, fantasma=? WHERE idCasa=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, casa.getNome());
            pst.setString(2, casa.getFundador());
            pst.setString(3, casa.getCores());
            pst.setString(4, casa.getMascote());
            pst.setString(5, casa.getFantasma());
            pst.setInt(6, casa.getIdCasa());
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

    public boolean deleteCasa(int idCasa) {
        connectToDb();
        String sql = "DELETE FROM Casas WHERE idCasa=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCasa);
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

    public ArrayList<Casa> selectAllCasas() {
        connectToDb();
        ArrayList<Casa> casas = new ArrayList<>();
        String sql = "SELECT * FROM Casas";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Casa casa = new Casa(
                        rs.getString("nome"),
                        rs.getString("fundador"),
                        rs.getString("cores"),
                        rs.getString("mascote"),
                        rs.getString("fantasma")
                );
                casa.setIdCasa(rs.getInt("idCasa"));
                casas.add(casa);
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
        return casas;
    }

    public Casa selectCasaById(int idCasa) {
        connectToDb();
        String sql = "SELECT * FROM Casas WHERE idCasa = ?";
        Casa casa = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCasa);
            rs = pst.executeQuery();

            if (rs.next()) {
                casa = new Casa(
                        rs.getString("nome"),
                        rs.getString("fundador"),
                        rs.getString("cores"),
                        rs.getString("mascote"),
                        rs.getString("fantasma")
                );
                casa.setIdCasa(rs.getInt("idCasa"));
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
        return casa;
    }

    public ArrayList<Casa> selectCasasComBruxos() {
        connectToDb();
        ArrayList<Casa> casas = new ArrayList<>();
        String sql = "SELECT c.*, COUNT(b.idBruxos) as total_bruxos " +
                "FROM Casas c LEFT JOIN Bruxos b ON c.idCasa = b.Casas_idCasa " +
                "GROUP BY c.idCasa";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Casa casa = new Casa(
                        rs.getString("nome"),
                        rs.getString("fundador"),
                        rs.getString("cores"),
                        rs.getString("mascote"),
                        rs.getString("fantasma")
                );
                casa.setIdCasa(rs.getInt("idCasa"));
                casas.add(casa);
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
        return casas;
    }
}