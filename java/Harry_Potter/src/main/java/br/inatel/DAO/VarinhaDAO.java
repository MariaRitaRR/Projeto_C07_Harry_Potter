package br.inatel.DAO;

import br.inatel.Model.Varinha;
import java.sql.SQLException;
import java.util.ArrayList;

public class VarinhaDAO extends ConnectionDAO {
    public boolean inserir(Varinha v) {
        connectToDb();
        String sql = "INSERT INTO Varinhas (nucleo, madeira, comprimento, flexibilidade) VALUES (?,?,?,?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, v.getNucleo());
            pst.setString(2, v.getMadeira());
            pst.setFloat(3, v.getComprimento());
            pst.setString(4, v.getFlexibilidade());
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
    }//varinha

    public ArrayList<Varinha> selectAllVarinhas() {
        connectToDb();
        ArrayList<Varinha> varinhas = new ArrayList<>();
        String sql = "SELECT * FROM Varinhas";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Varinha varinha = new Varinha(
                        rs.getInt("idVarinha"),
                        rs.getString("nucleo"),
                        rs.getString("madeira"),
                        rs.getFloat("comprimento"),
                        rs.getString("flexibilidade")
                );
                varinhas.add(varinha);
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
        return varinhas;
    }

    public boolean updateVarinha(Varinha v) {
        connectToDb();
        String sql = "UPDATE Varinhas SET nucleo=?, madeira=?, comprimento=?, flexibilidade=? WHERE idVarinha=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, v.getNucleo());
            pst.setString(2, v.getMadeira());
            pst.setFloat(3, v.getComprimento());
            pst.setString(4, v.getFlexibilidade());
            pst.setInt(5, v.getIdVarinha());
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

    public boolean deleteVarinha(int idVarinha) {
        connectToDb();
        String sql = "DELETE FROM Varinhas WHERE idVarinha=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idVarinha);
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

    public Varinha selectVarinhaById(int idVarinha) {
        connectToDb();
        Varinha varinha = null;
        String sql = "SELECT * FROM Varinhas WHERE idVarinha=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idVarinha);
            rs = pst.executeQuery();

            if (rs.next()) {
                varinha = new Varinha(
                        rs.getInt("idVarinha"),
                        rs.getString("nucleo"),
                        rs.getString("madeira"),
                        rs.getFloat("comprimento"),
                        rs.getString("flexibilidade")
                );
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
        return varinha;
    }
}