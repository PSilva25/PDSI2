
package Alterações;

import Cadastros.*;
import Backgrounds.*;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
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


public class Alteracao_Alimentos_Frios extends JFrame implements ActionListener {
    
    int Id = 0;
    
    String[]botoes = {"SIM", "NAO"};
    
    JButton Cancelar = new JButton("Cancelar");
    JButton Alterar = new JButton("Alterar");

    Cadastro_Alimento dados_Frios = new Cadastro_Alimento();
    
    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_Preco = new JTextField();
    JTextField Pega_Total_Compra = new JTextField();
    JTextField Pega_Quantidade = new JTextField();
    JTextField Pega_Unidade_porcao = new JTextField();
    JTextField Pega_PT = new JTextField();
   
    JTextField Pega_Frios = new JTextField();
        
    DAO c = new DAO();
    
    public Alteracao_Alimentos_Frios(int ID) throws SQLException  {
        
        this.Id = ID;
        
        ArmazenaDados(Id);
        
        Font fonte = new Font("SansSerif", Font.BOLD, 15);
         
        JLabel Frios = new JLabel("Frios: ");
        Pega_Frios.setBounds(230, 185, 210, 30);
        Frios.setBounds(180, 180, 130, 40);
        Pega_Frios.setFont(fonte);
        Pega_Frios.setText(dados_Frios.getTipo());
        Pega_Frios.setEnabled(true);
        Frios.setFont(fonte);
        add(Pega_Frios);
        add(Frios); 
               
        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(275, 265, 210, 30);
        Fornecedor.setBounds(180, 260, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Pega_Nome_Fornecedor.setText(dados_Frios.getFornecedor());
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);               
                
        JLabel Quantidade = new JLabel("Quantidade:");
        Pega_Quantidade.setBounds(720, 195, 100, 30);
        Quantidade.setBounds(720, 160, 130, 40);
        Pega_Quantidade.setFont(fonte);
        Pega_Quantidade.setText(String.valueOf(dados_Frios.getQuantT()));
        Quantidade.setFont(fonte);
        add(Pega_Quantidade);
        add(Quantidade);
            
        JLabel UKG = new JLabel("Preço pde Compra:");
        Pega_Preco.setBounds(720, 275, 100, 30);
        UKG.setBounds(720, 240, 140, 40);
        Pega_Preco.setFont(fonte);
        Pega_Preco.setText(String.valueOf(dados_Frios.getPreco()));
        UKG.setFont(fonte);
        add(Pega_Preco);
        add(UKG);
             
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
                    new Listagem_Frios();

                }else{

                    JOptionPane.showMessageDialog(null, "OPERACAO CANCELADA");
                    dispose();
                    new Listagem_Frios();

                }
             
            } catch (SQLException ex) {
            
                Logger.getLogger(Alteracao_Estoque_Bebidas.class.getName()).log(Level.SEVERE, null, ex);
            
            }
            
        }else if (e.getSource() == Cancelar) {

            dispose();
            new Listagem_Frios();
     
        } 
        
    }
    
    
    public void ArmazenaDados(int ID) throws SQLException{

        c.conexao();
               
        c.executaSQL("select * from estoque_frios where ID_alimento = " + ID);
   
        try {

            c.rs.first();                   
                    
            dados_Frios.setTipo(c.rs.getString("Tipo"));
            dados_Frios.setFornecedor((c.rs.getString("Fornecedor")));
            dados_Frios.setQuantT(c.rs.getFloat("Quantidade"));
            dados_Frios.setPreco(c.rs.getFloat("Preco_de_compra"));
           
                                        
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        }               
           
    }
    
   
    public void Atualiza(int x) throws SQLException{

        c.conexao();
          
        dados_Frios.setTipo((String)Pega_Frios.getText());
        dados_Frios.setFornecedor(Pega_Nome_Fornecedor.getText());
        dados_Frios.setQuantT(Float.parseFloat(Pega_Quantidade.getText()));
        dados_Frios.setPreco(Float.parseFloat(Pega_Preco.getText()));
       
        String sql = "UPDATE estoque_bebidas SET Tipo=?,Fornecedor=?,Quantidade=?,Preco_de_compra=?  where ID_alimento = '" + x + "'";

        try {

            PreparedStatement stmt = c.conn.prepareStatement(sql);

            stmt.setString(1, dados_Frios.getTipo());
            stmt.setString(2, dados_Frios.getFornecedor());
            stmt.setDouble(3, dados_Frios.getQuantT());
            stmt.setDouble(4,dados_Frios.getPreco());
                      
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "DADOS ALTERADOS!");

        } catch (SQLException e1) {
        
            JOptionPane.showMessageDialog(null, e1);

        }

        dispose();
    
    }
         
    
    public static void main(String [] args){
        
        //new Alteracao_Alimentos_Frios();
        
    }
    
    
    
}
