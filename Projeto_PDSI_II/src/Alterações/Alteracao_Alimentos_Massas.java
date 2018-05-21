
package Alterações;

import Backgrounds.BG_Alteracao;
import Cadastros.*;
import Backgrounds.BG_CadAlimentos_Massas;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import Listagem.Listagem_Frios;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import projeto_pdsi_ii.Massas;
import Listagem.*;

public class Alteracao_Alimentos_Massas extends JFrame implements ActionListener {
    
    int Id=0;
    
    Massas dados_M = new Massas();
    
    JButton Cancelar = new JButton("Cancelar");
    JButton Alterar = new JButton("Alterar");
 

    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_Unidades = new JTextField();
    JTextField Pega_Quantidade = new JTextField();
    JTextField Pega_Unidade_porcao = new JTextField();

    
    JComboBox<String> Pega_Massas = new JComboBox<>();
    
    
    DAO c = new DAO();

    
    public Alteracao_Alimentos_Massas(int ID) throws SQLException {
        
        this.Id = ID;
        
        ArmazenaDados(ID);
        
        Font fonte = new Font("SansSerif", Font.BOLD, 15);

           
        JLabel Massas = new JLabel("Massas: ");
        Pega_Massas.setBounds(245, 185, 130, 30);
        Massas.setBounds(180, 180, 130, 40);
        Pega_Massas.setFont(fonte);
        Massas.setFont(fonte);
        add(Pega_Massas);
        add(Massas);       
        
        Pega_Massas.addItem(dados_M.getTipo());
        
        
        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(275, 265, 210, 30);
        Fornecedor.setBounds(180, 260, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Pega_Nome_Fornecedor.setText(dados_M.getFornecedor());
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);
                
        
        JLabel UP = new JLabel("Unidades:");
        Pega_Quantidade.setBounds(735, 185, 100, 30);
        UP.setBounds(655, 180, 160, 40);
        Pega_Quantidade.setFont(fonte);
        Pega_Quantidade.setText(String.valueOf(dados_M.getQuantidade()));
        UP.setFont(fonte);
        add(Pega_Quantidade);
        add(UP);
        
        
        
        
        JLabel UP1 = new JLabel("Uni. por porção:");
        Pega_Unidade_porcao.setBounds(780, 265, 100, 30);
        UP1.setBounds(655, 260, 160, 40);
        Pega_Unidade_porcao.setFont(fonte);
        Pega_Unidade_porcao.setText(String.valueOf(dados_M.getQuant_porcao()));
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
                
                Atualiza(Id);
                
                new Listagem_Massas();
            
            } catch (SQLException ex) {
            
                Logger.getLogger(Alteracao_Estoque_Bebidas.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        } if (e.getSource() == Cancelar) {

            dispose();
            new Listagem_Massas();
     } 
    }
    public void ArmazenaDados(int ID) throws SQLException{

        c.conexao();
               
        c.executaSQL("select * from cad_massas where ID_alimento = " + ID);
   
        try {

            c.rs.first();                   
                    
            dados_M.setTipo(c.rs.getString("Tipo"));
            dados_M.setFornecedor((c.rs.getString("Fornecedor")));
            dados_M.setQuantidade(c.rs.getInt("Quantidade"));
            dados_M.setQuant_porcao(c.rs.getInt("Quantidade_porcao"));
            
                                        
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        }               
        
    
    }
    
   
    public void Atualiza(int x) throws SQLException{

        c.conexao();
          
        dados_M.setTipo((String)Pega_Massas.getSelectedItem());
        dados_M.setFornecedor(Pega_Nome_Fornecedor.getText());
        dados_M.setQuantidade(Integer.parseInt(Pega_Quantidade.getText()));
        dados_M.setQuant_porcao(Integer.parseInt(Pega_Unidade_porcao.getText()));
       
        String sql = "UPDATE cad_massas SET Tipo=?,Fornecedor=?,Quantidade=?,Quantidade_porcao = ?  where ID_alimento = '" + x + "'";

        try {

            PreparedStatement stmt = c.conn.prepareStatement(sql);

            stmt.setString(1, dados_M.getTipo());
            stmt.setString(2, dados_M.getFornecedor());
            stmt.setFloat(3, dados_M.getQuantidade());
            stmt.setFloat(4,dados_M.getQuant_porcao());
                      
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
