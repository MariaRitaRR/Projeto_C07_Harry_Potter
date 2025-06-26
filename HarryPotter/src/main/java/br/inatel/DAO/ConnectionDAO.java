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

    // Bloco estático para registrar o driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC do MySQL não encontrado no classpath", e);
        }
    }

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
        if (con == null || con.isClosed()) {
            connectToDb();
        }
        st = con.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        if (con == null || con.isClosed()) {
            connectToDb();
        }
        pst = con.prepareStatement(sql);
        return pst;
    }

    @Override
    public void close() {
        try {
            if (rs != null && !rs.isClosed()) rs.close();
            if (st != null && !st.isClosed()) st.close();
            if (pst != null && !pst.isClosed()) pst.close();
            if (con != null && !con.isClosed()) con.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexões:");
            e.printStackTrace();
        }
    }
}