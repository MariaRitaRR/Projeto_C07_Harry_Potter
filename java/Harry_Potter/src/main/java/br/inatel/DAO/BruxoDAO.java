package br.inatel.DAO;

import br.inatel.model.Bruxo;
import java.sql.SQLException;
import java.util.ArrayList;

public class BruxoDAO extends ConnectionDAO {
    public boolean inserir(Bruxo b) {
        connectToDb();
        String sql = "INSERT INTO Bruxos (nome, idade, Casas_idCasa, Varinhas_idVarinha) VALUES (?,?,?,?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, b.getNomeBruxo());
            pst.setInt(2, b.getIdade());
            pst.setInt(3, b.getIdCasa());
            pst.setInt(4, b.getIdVarinha());
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
    public ArrayList<Bruxo> selectAllBruxos() {
        connectToDb();
        ArrayList<Bruxo> bruxos = new ArrayList<>();
        String sql = "SELECT * FROM Bruxos";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Bruxo bruxo = new Bruxo(
                        rs.getInt("idBruxos"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getInt("Casas_idCasa"),
                        rs.getInt("Varinhas_idVarinha"),
                        rs.getString("data_cadastro")
                );
                bruxos.add(bruxo);
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
        return bruxos;
    }

    // SELECT by ID
    public Bruxo selectBruxoById(int id) {
        connectToDb();
        Bruxo bruxo = null;
        String sql = "SELECT * FROM Bruxos WHERE idBruxos = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                bruxo = new Bruxo(
                        rs.getInt("idBruxos"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getInt("Casas_idCasa"),
                        rs.getInt("Varinhas_idVarinha"),
                        rs.getString("data_cadastro")
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
        return bruxo;
    }

    // UPDATE
    public boolean updateBruxo(Bruxo b) {
        connectToDb();
        String sql = "UPDATE Bruxos SET nome=?, idade=?, Casas_idCasa=?, Varinhas_idVarinha=? WHERE idBruxos=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, b.getNomeBruxo());
            pst.setInt(2, b.getIdade());
            pst.setInt(3, b.getIdCasa());
            pst.setInt(4, b.getIdVarinha());
            pst.setInt(5, b.getIdBruxo());
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

    // DELETE
    public boolean deleteBruxo(int id) {
        connectToDb();
        String sql = "DELETE FROM Bruxos WHERE idBruxos=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
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

    // Método adicional: Buscar bruxos por casa
    public ArrayList<Bruxo> selectBruxosByCasa(int idCasa) {
        connectToDb();
        ArrayList<Bruxo> bruxos = new ArrayList<>();
        String sql = "SELECT * FROM Bruxos WHERE Casas_idCasa = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCasa);
            rs = pst.executeQuery();

            while (rs.next()) {
                Bruxo bruxo = new Bruxo(
                        rs.getInt("idBruxos"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getInt("Casas_idCasa"),
                        rs.getInt("Varinhas_idVarinha"),
                        rs.getString("data_cadastro")
                );
                bruxos.add(bruxo);
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
        return bruxos;
    }

    //Buscar bruxos por varinha
    public ArrayList<Bruxo> selectBruxosByVarinha(int idVarinha) {
        connectToDb();
        ArrayList<Bruxo> bruxos = new ArrayList<>();
        String sql = "SELECT * FROM Bruxos WHERE Varinhas_idVarinha = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idVarinha);
            rs = pst.executeQuery();

            while (rs.next()) {
                Bruxo bruxo = new Bruxo(
                        rs.getInt("idBruxos"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getInt("Casas_idCasa"),
                        rs.getInt("Varinhas_idVarinha"),
                        rs.getString("data_cadastro")
                );
                bruxos.add(bruxo);
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
        return bruxos;
    }
}