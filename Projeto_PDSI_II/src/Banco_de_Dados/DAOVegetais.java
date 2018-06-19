/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco_de_Dados;

import Getters_e_Setters.Cadastro_Alimento;
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

    public boolean salvar(Cadastro_Alimento p) throws SQLException {
        if (!blindagem(p)) {
            return false;
        }

        c.conexao();

        String sql = "insert into estoque_frios (Tipo, Fornecedor, Quantidade, Preco_de_compra) values (?,?,?,?);";

        PreparedStatement stmt = c.conn.prepareStatement(sql);

        try {

            stmt.setString(1, p.getTipo());
            stmt.setString(2, p.getFornecedor());
            stmt.setDouble(3, p.getQuantT());
            stmt.setDouble(4, p.getPreco());

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    public boolean atualizar(Cadastro_Alimento p, int x) {

        if (!blindagem(p)) {
            return false;
        }

        c.conexao();
        String sql = "UPDATE estoque_frios SET Tipo = ?, Fornecedor = ?, Quantidade = ?, Preco_de_compra = ? where ID_alimento = '" + x + "'";

        try {

            PreparedStatement stmt = c.conn.prepareStatement(sql);

            stmt.setString(1, p.getTipo());
            stmt.setString(2, p.getFornecedor());
            stmt.setDouble(3, p.getQuantT());
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
        String sql = "delete from estoque_frios where ID_alimento='" + x + "'";

        try {

            PreparedStatement stmt = c.conn.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
            return false;
        }
        return true;
    }

    public ArrayList<Cadastro_Alimento> listar_todos() {
        ResultSet rs = null;
        c.conexao();
        PreparedStatement stmt = null;
        ArrayList<Cadastro_Alimento> result = new ArrayList();
        String str = "select * from estoque_bebidas";

        try {
            stmt = c.conn.prepareStatement(str);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cadastro_Alimento p1 = new Cadastro_Alimento(rs.getInt("ID_alimento"), rs.getString("Tipo"), rs.getString("Fornecedor"), rs.getDouble("Quantidade"), rs.getDouble("Preco_de_compra"));
                result.add(p1);
            }
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public ArrayList<Cadastro_Alimento> listarcada(int id) {
        ResultSet rs = null;
        c.conexao();
        PreparedStatement stmt = null;
        ArrayList<Cadastro_Alimento> result = new ArrayList();
        String str = "select * from estoque_frios where ID_alimento like '%" + id + "%'";

        try {
            stmt = c.conn.prepareStatement(str);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cadastro_Alimento p1 = new Cadastro_Alimento(rs.getInt("ID_alimento"), rs.getString("Tipo"), rs.getString("Fornecedor"), rs.getDouble("Quantidade"), rs.getDouble("Preco_de_compra"));
                result.add(p1);
            }
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public boolean blindagem(Cadastro_Alimento p) {
        if ((p.getTipo().equals("")) || (p.getFornecedor().equals(""))) {
            return false;
        }
        if (p.getQuantT() < 0) {
            return false;
        }
        if (p.getPreco() <= 0) {
            return false;
        }
        return true;
    }
}
