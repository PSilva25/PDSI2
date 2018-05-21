
package Alterações;

import Backgrounds.BG_Alteracao;
import Cadastros.*;
import Backgrounds.BG_CadAlimentos_Vegetais;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import Listagem.Listagem_Vegetais;
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
import projeto_pdsi_ii.Vegetais;


public class Alteracao_Alimentos_Vegetais extends JFrame implements ActionListener {
    
    int Id=0;
    
    Vegetais dados_V = new Vegetais();
    
    JButton Cancelar = new JButton("Cancelar");
    JButton Alterar = new JButton("Alterar");
 

    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_Preco = new JTextField();
    JTextField Pega_QuantidadeKG = new JTextField();
    JTextField Pega_Unidade_porcao = new JTextField();

    
    JComboBox<String> Pega_Vegetais = new JComboBox<>();
    
    
    DAO c = new DAO();

    
    public Alteracao_Alimentos_Vegetais(int ID) throws SQLException {
        
        this.Id = ID;
        ArmazenaDados(Id);
        
         Font fonte = new Font("SansSerif", Font.BOLD, 15);

           
        JLabel Vegetais = new JLabel("Vegetais: ");
        Pega_Vegetais.setBounds(255, 185, 130, 30);
        Vegetais.setBounds(180, 180, 130, 40);
        Pega_Vegetais.setFont(fonte);
        Vegetais.setFont(fonte);
        add(Pega_Vegetais);
        add(Vegetais);       
        
        Pega_Vegetais.addItem(dados_V.getTipo());
       
            
        
        
        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(275, 265, 210, 30);
        Fornecedor.setBounds(180, 260, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Pega_Nome_Fornecedor.setText(dados_V.getFornecedor());
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);
        
        
                
                
        JLabel Quantidade = new JLabel("Quantidade(KG):");
        Pega_QuantidadeKG.setBounds(775, 165, 100, 30);
        Quantidade.setBounds(650, 160, 130, 40);
        Pega_QuantidadeKG.setFont(fonte);
        Pega_QuantidadeKG.setText(String.valueOf(dados_V.getQuantTKG()));
        Quantidade.setFont(fonte);
        add(Pega_QuantidadeKG);
        add(Quantidade);
        
       
    
        JLabel UKG = new JLabel("Quant. por Porção:");
        Pega_Unidade_porcao.setBounds(790, 225, 100, 30);
        UKG.setBounds(650, 220, 160, 40);
        Pega_Unidade_porcao.setFont(fonte);
        Pega_Unidade_porcao.setText(String.valueOf(dados_V.getQunTPorC()));
        UKG.setFont(fonte);
        add(Pega_Unidade_porcao);
        add(UKG);
                
                
                
        JLabel UP = new JLabel("Preço por Kg:");
        Pega_Preco.setBounds(755, 285, 100, 30);
        UP.setBounds(650, 280, 160, 40);
        Pega_Preco.setFont(fonte);
        Pega_Preco.setText(String.valueOf(dados_V.getPrecokg()));
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
                
                Atualiza(Id);
                
                new Listagem_Vegetais();
            
            } catch (SQLException ex) {
            
                Logger.getLogger(Alteracao_Alimentos_Vegetais.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        } if (e.getSource() == Cancelar) {

            dispose();
            new Listagem_Vegetais();
     } 
    }
    public void ArmazenaDados(int ID) throws SQLException{

        c.conexao();
               
        c.executaSQL("select * from cad_vegetais where ID_vegetal = " + ID);
   
        try {

            c.rs.first();                   
                    
            dados_V.setTipo(c.rs.getString("Tipo"));
            dados_V.setFornecedor((c.rs.getString("Fornecedor")));
            dados_V.setQuantTKG(c.rs.getFloat("Quantidade"));
            dados_V.setPrecokg(c.rs.getFloat("preco_kg"));
            dados_V.setQunTPorC(c.rs.getFloat("Quantidade_porcao"));
            
                                        
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        }               
        
    
    }
    
   
    public void Atualiza(int x) throws SQLException{

        c.conexao();
          
        dados_V.setTipo((String)Pega_Vegetais.getSelectedItem());
        dados_V.setFornecedor(Pega_Nome_Fornecedor.getText());
        dados_V.setQuantTKG(Float.parseFloat(Pega_QuantidadeKG.getText()));
        dados_V.setPrecokg(Float.parseFloat(Pega_Preco.getText()));
        dados_V.setQunTPorC(Float.parseFloat(Pega_Unidade_porcao.getText()));
       
        String sql = "UPDATE cad_vegetais SET Tipo=?,Fornecedor=?,Quantidade=?,preco_kg=?,Quantidade_porcao = ?  where ID_vegetal = '" + x + "'";

        try {

            PreparedStatement stmt = c.conn.prepareStatement(sql);

            stmt.setString(1, dados_V.getTipo());
            stmt.setString(2, dados_V.getFornecedor());
            stmt.setFloat(3, dados_V.getQuantTKG());
            stmt.setFloat(4, dados_V.getPrecokg());
            stmt.setFloat(5,dados_V.getQunTPorC());
                      
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
