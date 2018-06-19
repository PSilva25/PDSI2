
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


public class Alteracao_Alimentos_Vegetais extends JFrame implements ActionListener {
    
    int Id = 0;
    
    String[]botoes = {"SIM", "NAO"};
    
    Vegetais dados_V = new Vegetais();
    
    JButton Cancelar = new JButton("Cancelar");
    JButton Alterar = new JButton("Alterar");
 
    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_Preco = new JTextField();
    JTextField Pega_Quantidade = new JTextField();
    JTextField Pega_Unidade_porcao = new JTextField();
  
     JTextField Pega_Vegetais = new  JTextField();
       
    DAO c = new DAO();

    
    public Alteracao_Alimentos_Vegetais(int ID) throws SQLException {
        
        this.Id = ID;
        
        ArmazenaDados(Id);
        
        Font fonte = new Font("SansSerif", Font.BOLD, 15);
          
        JLabel Vegetais = new JLabel("Vegetais: ");
        Pega_Vegetais.setBounds(255, 185, 130, 30);
        Vegetais.setBounds(180, 180, 130, 40);
        Pega_Vegetais.setFont(fonte);
        Pega_Vegetais.setText(dados_V.getTipo());
        Vegetais.setFont(fonte);
        add(Pega_Vegetais);
        add(Vegetais);       
        
        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(275, 265, 210, 30);
        Fornecedor.setBounds(180, 260, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Pega_Nome_Fornecedor.setText(dados_V.getFornecedor());
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);
                
        JLabel Quantidade = new JLabel("Quantidade:");
        Pega_Quantidade.setBounds(720, 195, 100, 30);
        Quantidade.setBounds(720, 160, 130, 40);
        Pega_Quantidade.setFont(fonte);
        Pega_Quantidade.setText(String.valueOf(dados_V.getQuantidade()));
        Quantidade.setFont(fonte);
        add(Pega_Quantidade);
        add(Quantidade);    
                
        JLabel UP = new JLabel("Preço de Compra:");
        Pega_Preco.setBounds(720, 275, 100, 30);
        UP.setBounds(720, 240, 140, 40);
        Pega_Preco.setFont(fonte);
        Pega_Preco.setText(String.valueOf(dados_V.getPreco()));
        UP.setFont(fonte);
        add(Pega_Preco);
        add(UP);
         
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
                    new Listagem_Vegetais();

                }else{

                    JOptionPane.showMessageDialog(null, "OPERACAO CANCELADA");
                    dispose();
                    new Listagem_Vegetais();

                }
             
            } catch (SQLException ex) {
            
                Logger.getLogger(Alteracao_Alimentos_Vegetais.class.getName()).log(Level.SEVERE, null, ex);
            
            }
            
        }else if (e.getSource() == Cancelar) {

            dispose();
            new Listagem_Vegetais();
     
        }
        
    }
    
    
    public void ArmazenaDados(int ID) throws SQLException{

        c.conexao();
               
        c.executaSQL("select * from estoque_vegetais where ID_alimento = " + ID);
   
        try {

            c.rs.first();                   
                    
            dados_V.setTipo(c.rs.getString("Tipo"));
            dados_V.setFornecedor((c.rs.getString("Fornecedor")));
            dados_V.setQuantidade(c.rs.getInt("Quantidade"));
            dados_V.setPreco(c.rs.getFloat("Preco_de_Compra"));           
                                        
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        }               
            
    }
    
   
    public void Atualiza(int x) throws SQLException{

        c.conexao();
          
        dados_V.setTipo((String)Pega_Vegetais.getText());
        dados_V.setFornecedor(Pega_Nome_Fornecedor.getText());
        dados_V.setQuantidade(Integer.parseInt(Pega_Quantidade.getText()));
        dados_V.setPreco(Float.parseFloat(Pega_Preco.getText()));
       
        String sql = "UPDATE estoque_vegetais SET Tipo=?,Fornecedor=?,Quantidade=?,Preco_de_Compra=?  where ID_vegetal = '" + x + "'";

        try {

            PreparedStatement stmt = c.conn.prepareStatement(sql);

            stmt.setString(1, dados_V.getTipo());
            stmt.setString(2, dados_V.getFornecedor());
            stmt.setFloat(3, dados_V.getQuantidade());
            stmt.setFloat(4, dados_V.getPreco());
                      
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "DADOS ALTERADOS!");

        } catch (SQLException e1) {
        
            JOptionPane.showMessageDialog(null, e1);

        }

        dispose();
    
    }

    
    public static void main(String [] args){
        
        //new Alteracao_Alimentos_Vegetais();
        
    }
    
    
    
}
