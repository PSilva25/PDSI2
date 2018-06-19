
package Alterações;

import Cadastros.*;
import Backgrounds.*;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import Listagem.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import Getters_e_Setters.*;
import Listagem.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Alteracao_Alimentos_Massas extends JFrame implements ActionListener {
    
    int Id = 0;
    
    String[]botoes = {"SIM", "NAO"};
    
    Massas dados_M = new Massas();
    
    JButton Cancelar = new JButton("Cancelar");
    JButton Alterar = new JButton("Alterar");
 
    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_Unidades = new JTextField();
    JTextField Pega_Quantidade = new JTextField();
    JTextField Pega_Unidade_porcao = new JTextField();
   
    JTextField Pega_Massas = new JTextField();
       
    DAO c = new DAO();
   
    public Alteracao_Alimentos_Massas(int ID) throws SQLException {
        
        this.Id = ID;
        
        ArmazenaDados(ID);
        
        Font fonte = new Font("SansSerif", Font.BOLD, 15);
           
        JLabel Massas = new JLabel("Massas: ");
        Pega_Massas.setBounds(245, 185, 160, 30);
        Massas.setBounds(180, 180, 130, 40);
        Pega_Massas.setFont(fonte);
        Pega_Massas.setText(dados_M.getTipo());
        Massas.setFont(fonte);
        add(Pega_Massas);
        add(Massas);       
             
        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(275, 265, 210, 30);
        Fornecedor.setBounds(180, 260, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Pega_Nome_Fornecedor.setText(dados_M.getFornecedor());
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);
                       
        JLabel UP = new JLabel("Quatidade:");
        Pega_Quantidade.setBounds(735, 185, 100, 30);
        UP.setBounds(655, 180, 160, 40);
        Pega_Quantidade.setFont(fonte);
        Pega_Quantidade.setText(String.valueOf(dados_M.getQuantidade()));
        UP.setFont(fonte);
        add(Pega_Quantidade);
        add(UP);
       
        JLabel UP1 = new JLabel("Preço de Compra:");
        Pega_Unidade_porcao.setBounds(780, 265, 100, 30);
        UP1.setBounds(655, 260, 160, 40);
        Pega_Unidade_porcao.setFont(fonte);
        Pega_Unidade_porcao.setText(String.valueOf(dados_M.getPreco()));
        UP1.setFont(fonte);
        add(Pega_Unidade_porcao);
        add(UP1);
       
        Cancelar.setBorder(new Borda_Redonda(7));
        Cancelar.setBounds(130, 430, 160, 40);
        Cancelar.addActionListener(this);
        Cancelar.setFont(fonte);
        add(Cancelar);
                
        Alterar.setBorder(new Borda_Redonda(7));
        Alterar.setBounds(808, 430, 160, 40);
        Alterar.addActionListener(this);
        Alterar.setFont(fonte);
        add(Alterar);
  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new BG_Alteracao());
        setBackground(Color.black);
        setResizable(false);
        setTitle("..:FastZooom:..");
        setSize(1100, 550);
        setLocationRelativeTo(null);
        setVisible(true);   
    
    }
    
    
    public void actionPerformed(ActionEvent e) { 
     
        if (e.getSource() == Alterar) {
          
            try {
                  
                int opcao = JOptionPane.showOptionDialog(null, "Realmente deseja alterar este produto", "Aviso!!!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
           
                if(botoes[opcao].equals("SIM")){

                    Atualiza(Id);
                    dispose();
                    new Listagem_Massas();

                }else{

                    JOptionPane.showMessageDialog(null, "OPERACAO CANCELADA");
                    dispose();
                    new Listagem_Massas();

                }
             
            } catch (SQLException ex) {
            
                Logger.getLogger(Alteracao_Estoque_Bebidas.class.getName()).log(Level.SEVERE, null, ex);
            
            }
            
        }else if (e.getSource() == Cancelar) {

            dispose();
            new Listagem_Massas();
     
        } 
        
    }
    
    
    public void ArmazenaDados(int ID) throws SQLException{

        c.conexao();
               
        c.executaSQL("select * from estoque_massas where ID_alimento = " + ID);
   
        try {

            c.rs.first();                   
                    
            dados_M.setTipo(c.rs.getString("Tipo"));
            dados_M.setFornecedor((c.rs.getString("Fornecedor")));
            dados_M.setQuantidade(c.rs.getInt("Quantidade"));
            dados_M.setPreco(c.rs.getFloat("Preco_de_Compra"));
            
                                        
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        }                       
    
    }
    
   
    public void Atualiza(int x) throws SQLException{

        c.conexao();
          
        dados_M.setTipo((String)Pega_Massas.getText());
        dados_M.setFornecedor(Pega_Nome_Fornecedor.getText());
        dados_M.setQuantidade(Integer.parseInt(Pega_Quantidade.getText()));
        dados_M.setPreco(Float.parseFloat(Pega_Unidade_porcao.getText()));
       
        String sql = "UPDATE estoque_massas SET Tipo=?,Fornecedor=?,Quantidade=?,Preco_de_Compra = ?  where ID_alimento = '" + x + "'";

        try {

            PreparedStatement stmt = c.conn.prepareStatement(sql);

            stmt.setString(1, dados_M.getTipo());
            stmt.setString(2, dados_M.getFornecedor());
            stmt.setInt(3, dados_M.getQuantidade());
            stmt.setFloat(4,dados_M.getPreco());
                      
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "DADOS ALTERADOS!");

        } catch (SQLException e1) {
        
            JOptionPane.showMessageDialog(null, e1);

        }

        dispose();
    
    }
    
      
    public static void main(String [] args){
        
        //new Alteracao_Alimentos_Massas();
        
    }
    
    
    
}
