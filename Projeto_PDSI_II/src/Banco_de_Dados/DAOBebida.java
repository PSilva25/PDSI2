/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco_de_Dados;

import Getters_e_Setters.Bebida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael PSilva
 */
public class DAOBebida {

    DAO c = new DAO();

    public boolean salvar(Bebida p) throws SQLException {
        if (!blindagem(p)) {
            return false;
        }

        c.conexao();

        String sql = "insert into estoque_bebidas (Tipo, Nome, Fornecedor, Volume, Quantidade, Preco) values (?,?,?,?,?,?);";

        PreparedStatement stmt = c.conn.prepareStatement(sql);

        try {

            stmt.setString(1, p.getTipo());
            stmt.setString(2, p.getBebida());
            stmt.setString(3, p.getFornecedor());
            stmt.setString(4, p.getVolume());
            stmt.setInt(5, p.getQuantidade());
            stmt.setDouble(6, p.getPreco());

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    public boolean atualizar(Bebida p, int x) {

        if (!blindagem(p)) {
            return false;
        }

        c.conexao();
        String sql = "UPDATE estoque_bebidas SET Tipo = ?, Nome = ?, Fornecedor = ?, Volume = ?, Quantidade = ?, Preco = ? where ID_bebida = '" + x + "'";

        try {

            PreparedStatement stmt = c.conn.prepareStatement(sql);

            stmt.setString(1, p.getTipo());
            stmt.setString(2, p.getBebida());
            stmt.setString(3, p.getFornecedor());
            stmt.setString(4, p.getVolume());
            stmt.setInt(5, p.getQuantidade());
            stmt.setDouble(6, p.getPreco());

            stmt.executeUpdate();

        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
            return false;
        }

        return true;
    }

    public boolean deletar(int x) {

        c.conexao();
        String sql = "delete from estoque_bebidas where ID_Bebida='" + x + "'";

        try {

            PreparedStatement stmt = c.conn.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
            return false;
        }
        return true;
    }

    public ArrayList<Bebida> listar_todos() {
        ResultSet rs = null;
        c.conexao();
        PreparedStatement stmt = null;
        ArrayList<Bebida> result = new ArrayList();
        String str = "select * from estoque_bebidas";

        try {
            stmt = c.conn.prepareStatement(str);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Bebida p1 = new Bebida(rs.getInt("ID_bebida"), rs.getString("Tipo"), rs.getString("Nome"), rs.getString("Fornecedor"), rs.getString("Volume"), rs.getInt("Quantidade"), (int) rs.getDouble("Preco"));
                result.add(p1);
            }
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public ArrayList<Bebida> listarcada(int id) {
        ResultSet rs = null;
        c.conexao();
        PreparedStatement stmt = null;
        ArrayList<Bebida> result = new ArrayList();
        String str = "select * from estoque_bebidas where ID_bebida like '%" + id + "%'";

        try {
            stmt = c.conn.prepareStatement(str);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Bebida p1 = new Bebida(rs.getInt("ID_bebida"), rs.getString("Tipo"), rs.getString("Nome"), rs.getString("Fornecedor"), rs.getString("Volume"), rs.getInt("Quantidade"), (int) rs.getDouble("Preco"));
                result.add(p1);
            }
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public boolean blindagem(Bebida p) {
        if ((p.getTipo().equals("")) || (p.getBebida().equals("")) || (p.getFornecedor().equals("")) || (p.getVolume().equals(""))) {
            return false;
        }
        if (p.getQuantidade() < 0) {
            return false;
        }
        if (p.getPreco() <= 0) {
            return false;
        }
        return true;
    }
}
