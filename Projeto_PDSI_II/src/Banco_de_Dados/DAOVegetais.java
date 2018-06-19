/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco_de_Dados;

import Getters_e_Setters.Vegetais;
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
public class DAOVegetais {

    DAO c = new DAO();

    public boolean salvar(Vegetais p) throws SQLException {
        if (!blindagem(p)) {
            return false;
        }

        c.conexao();

        String sql = "insert into estoque_vegetais (Tipo, Fornecedor, Quantidade, Preco_de_Compra) values (?,?,?,?);";

        PreparedStatement stmt = c.conn.prepareStatement(sql);

        try {

            stmt.setString(1, p.getTipo());
            stmt.setString(2, p.getFornecedor());
            stmt.setDouble(3, p.getQuantidade());
            stmt.setDouble(4, p.getPreco());

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    public boolean atualizar(Vegetais p, int x) {

        if (!blindagem(p)) {
            return false;
        }

        c.conexao();
        String sql = "UPDATE estoque_vegetais SET Tipo = ?, Fornecedor = ?, Quantidade = ?, Preco_de_Compra = ? where ID_alimento = '" + x + "'";

        try {

            PreparedStatement stmt = c.conn.prepareStatement(sql);

            stmt.setString(1, p.getTipo());
            stmt.setString(2, p.getFornecedor());
            stmt.setDouble(3, p.getQuantidade());
            stmt.setDouble(4, p.getPreco());

            stmt.executeUpdate();

        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
            return false;
        }

        return true;
    }

    public boolean deletar(int x) {

        c.conexao();
        String sql = "delete from estoque_vegetais where ID_alimento='" + x + "'";

        try {

            PreparedStatement stmt = c.conn.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
            return false;
        }
        return true;
    }

    public ArrayList<Vegetais> listar_todos() {
        ResultSet rs = null;
        c.conexao();
        PreparedStatement stmt = null;
        ArrayList<Vegetais> result = new ArrayList();
        String str = "select * from estoque_vegetais";

        try {
            stmt = c.conn.prepareStatement(str);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vegetais p1 = new Vegetais(rs.getInt("ID_alimento"), rs.getString("Tipo"), rs.getString("Fornecedor"), rs.getDouble("Quantidade"), rs.getDouble("Preco_de_compra"));
                result.add(p1);
            }
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public ArrayList<Vegetais> listarcada(int id) {
        ResultSet rs = null;
        c.conexao();
        PreparedStatement stmt = null;
        ArrayList<Vegetais> result = new ArrayList();
        String str = "select * from estoque_vegetais where ID_alimento like '%" + id + "%'";

        try {
            stmt = c.conn.prepareStatement(str);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vegetais p1 = new Vegetais(rs.getInt("ID_alimento"), rs.getString("Tipo"), rs.getString("Fornecedor"), rs.getDouble("Quantidade"), rs.getDouble("Preco_de_compra"));
                result.add(p1);
            }
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public boolean blindagem(Vegetais p) {
        if ((p.getTipo().equals("")) || (p.getFornecedor().equals(""))) {
            return false;
        }
        if (p.getQuantidade()< 0) {
            return false;
        }
        if (p.getPreco() <= 0) {
            return false;
        }
        return true;
    }
}
