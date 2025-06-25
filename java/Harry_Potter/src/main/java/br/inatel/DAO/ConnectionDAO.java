package br.inatel.DAO;

import java.sql.*;

public class ConnectionDAO implements AutoCloseable {
    public Connection con;
    public PreparedStatement pst;
    public Statement st;
    public ResultSet rs;

    public final String database = "mydb";
    public final String user = "root";
    public final String password = "root";
    public final String url;

    public ConnectionDAO() {
        this.url = "jdbc:mysql://localhost:3306/" + database
                + "?useTimezone=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    }

    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Falha crítica na conexão:");
            e.printStackTrace();
            throw new RuntimeException("Não foi possível estabelecer conexão com o banco", e);
        }
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        st = con.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        pst = con.prepareStatement(sql);
        return pst;
    }

    @Override
    public void close() throws SQLException {
        if (rs != null) rs.close();
        if (st != null) st.close();
        if (pst != null) pst.close();
        if (con != null) con.close();
    }
}