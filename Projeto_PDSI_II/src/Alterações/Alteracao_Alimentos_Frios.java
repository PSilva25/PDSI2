
package Alterações;

import Cadastros.*;
import Backgrounds.BG_Alteracao;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
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
import Getters_e_Setters.Cadastro_Alimento;
import Listagem.Listagem_Frios;


public class Alteracao_Alimentos_Frios extends JFrame implements ActionListener {
    
    int Id = 0;
    
    String[]botoes = {"SIM", "NAO"};
    
    JButton Cancelar = new JButton("Cancelar");
    JButton Alterar = new JButton("Alterar");

    Cadastro_Alimento dados_Frios = new Cadastro_Alimento();

    
    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_Preco = new JTextField();
    JTextField Pega_Total_Compra = new JTextField();
    JTextField Pega_QuantidadeKG = new JTextField();
    JTextField Pega_Unidade_porcao = new JTextField();
    JTextField Pega_PT = new JTextField();

    
    JComboBox<String> Pega_Frios = new JComboBox<>();
    
    
    DAO c = new DAO();

    
    public Alteracao_Alimentos_Frios(int ID) throws SQLException  {
        
        this.Id = ID;
        
        ArmazenaDados(Id);
       Font fonte = new Font("SansSerif", Font.BOLD, 15);

           
        JLabel Frios = new JLabel("Frios: ");
        Pega_Frios.setBounds(230, 185, 210, 30);
        Frios.setBounds(180, 180, 130, 40);
        Pega_Frios.setFont(fonte);
        Pega_Frios.setEnabled(true);
        Frios.setFont(fonte);
        add(Pega_Frios);
        add(Frios); 
        
           
        Pega_Frios.addItem(dados_Frios.getTipo());
        
          
            
        
        
        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(275, 265, 210, 30);
        Fornecedor.setBounds(180, 260, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Pega_Nome_Fornecedor.setText(dados_Frios.getFornecedor());
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);
        
        
                
                
        JLabel Quantidade = new JLabel("Quantidade(KG):");
        Pega_QuantidadeKG.setBounds(620, 195, 100, 30);
        Quantidade.setBounds(610, 160, 130, 40);
        Pega_QuantidadeKG.setFont(fonte);
        Pega_QuantidadeKG.setText(String.valueOf(dados_Frios.getQuantT()));
        Quantidade.setFont(fonte);
        add(Pega_QuantidadeKG);
        add(Quantidade);
        
       
    
        JLabel UKG = new JLabel("Preço por Kg:");
        Pega_Preco.setBounds(820, 195, 100, 30);
        UKG.setBounds(820, 160, 130, 40);
        Pega_Preco.setFont(fonte);
        Pega_Preco.setText(String.valueOf(dados_Frios.getPreco()));
        UKG.setFont(fonte);
        add(Pega_Preco);
        add(UKG);
        
        
        
        JLabel UP = new JLabel("Quant. por porção:");
        Pega_Unidade_porcao.setBounds(625, 275, 100, 30);
        UP.setBounds(610, 240, 160, 40);
        Pega_Unidade_porcao.setFont(fonte);
        Pega_Unidade_porcao.setText(String.valueOf(dados_Frios.getUniPorcao()));
        UP.setFont(fonte);
        add(Pega_Unidade_porcao);
        add(UP);
        
         
        JLabel PT = new JLabel("Total Compra:");
        Pega_PT.setBounds(820, 275, 100, 30);
        PT.setBounds(820, 240, 100, 40);
        Pega_PT.setEditable(false);   
        Pega_PT.setFont(fonte);
        Pega_PT.setText(String.valueOf(dados_Frios.getPreco_Total()));
        PT.setFont(fonte);
        add(Pega_PT);
        add(PT);
                
                
                
        
        
        
        
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
                
                int opcao = JOptionPane.showOptionDialog(null, "Realmente deseja alterar este produto", "Aviso!!!",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
           
            if(botoes[opcao].equals("SIM")){
            
                Atualiza(Id);
                
                new Listagem_Frios();
            
                
            }else{
                
            JOptionPane.showMessageDialog(null, "OPERACAO CANCELADA");
            
             new Listagem_Frios();
            
            }
             
            } catch (SQLException ex) {
            
                Logger.getLogger(Alteracao_Estoque_Bebidas.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        } if (e.getSource() == Cancelar) {

            dispose();
            new Listagem_Frios();
     } 
    }
    
    
    public void ArmazenaDados(int ID) throws SQLException{

        c.conexao();
               
        c.executaSQL("select * from cad_alimentos where ID_alimento = " + ID);
   
        try {

            c.rs.first();                   
                    
            dados_Frios.setTipo(c.rs.getString("Tipo"));
            dados_Frios.setFornecedor((c.rs.getString("Fornecedor")));
            dados_Frios.setQuantT(c.rs.getFloat("QuantidadeT"));
            dados_Frios.setPreco(c.rs.getFloat("Preco_de_compra"));
            dados_Frios.setPreco_Total(c.rs.getFloat("preco_total"));
            dados_Frios.setUniPorcao(c.rs.getFloat("Quantidade_kg_porcao"));
           
                                        
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        }               
        
    
    }
    
   
    public void Atualiza(int x) throws SQLException{

        c.conexao();
          
        dados_Frios.setTipo((String)Pega_Frios.getSelectedItem());
        dados_Frios.setFornecedor(Pega_Nome_Fornecedor.getText());
        dados_Frios.setQuantT(Float.parseFloat(Pega_QuantidadeKG.getText()));
        dados_Frios.setPreco(Float.parseFloat(Pega_Preco.getText()));
        dados_Frios.setUniPorcao(Float.parseFloat(Pega_Unidade_porcao.getText()));
        dados_Frios.setPreco_Total((Float.parseFloat(Pega_Preco.getText())*Float.parseFloat(Pega_QuantidadeKG.getText())));
       
        String sql = "UPDATE cad_alimentos SET Tipo=?,Fornecedor=?,QuantidadeT=?,Preco_de_compra=?,preco_total=?, Quantidade_kg_porcao = ?  where ID_alimento = '" + x + "'";

        try {

            PreparedStatement stmt = c.conn.prepareStatement(sql);

            stmt.setString(1, dados_Frios.getTipo());
            stmt.setString(2, dados_Frios.getFornecedor());
            stmt.setFloat(3, dados_Frios.getQuantT());
            stmt.setFloat(4,dados_Frios.getPreco());
            stmt.setFloat(5,dados_Frios.getPreco_Total());
            stmt.setFloat(6, dados_Frios.getUniPorcao());
                      
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
