
package Cadastros;

import Backgrounds.BG_CadAlimentos_Frios;
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
import projeto_pdsi_ii.Cadastro_Alimento;


public class Cadastro_Alimentos_Frios extends JFrame implements ActionListener {
    
    JButton Voltar = new JButton("Voltar");
    JButton Adicionar = new JButton("Cadastrar Frios");
 
    Cadastro_Alimento dados_AL = new Cadastro_Alimento();
    
    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_Preco = new JTextField();
    JTextField Pega_Total_Compra = new JTextField();
    JTextField Pega_QuantidadeKG = new JTextField();
    JTextField Pega_Unidade_porcao = new JTextField();
    JTextField Pega_PT = new JTextField();

    
    JComboBox<String> Pega_Frios = new JComboBox<>();
    
    
    DAO c = new DAO();

    
    public Cadastro_Alimentos_Frios() {
        
        
        Font fonte = new Font("SansSerif", Font.BOLD, 15);

           
        JLabel Frios = new JLabel("Frios: ");
        Pega_Frios.setBounds(230, 185, 210, 30);
        Frios.setBounds(180, 180, 130, 40);
        Pega_Frios.setFont(fonte);
        Pega_Frios.setEnabled(true);
        Frios.setFont(fonte);
        add(Pega_Frios);
        add(Frios); 
        
           
        Pega_Frios.addItem("");
        Pega_Frios.addItem("peito de frango");
        Pega_Frios.addItem("peito de peru");
        Pega_Frios.addItem("frango");
        Pega_Frios.addItem("salsicha");
        Pega_Frios.addItem("Ovo");
          
            
        
        
        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(275, 265, 210, 30);
        Fornecedor.setBounds(180, 260, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);
        
        
                
                
        JLabel Quantidade = new JLabel("Quantidade(KG):");
        Pega_QuantidadeKG.setBounds(620, 195, 100, 30);
        Quantidade.setBounds(610, 160, 130, 40);
        Pega_QuantidadeKG.setFont(fonte);
        Quantidade.setFont(fonte);
        add(Pega_QuantidadeKG);
        add(Quantidade);
        
       
    
        JLabel UKG = new JLabel("Preço por Kg:");
        Pega_Preco.setBounds(820, 195, 100, 30);
        UKG.setBounds(820, 160, 130, 40);
        Pega_Preco.setFont(fonte);
        UKG.setFont(fonte);
        add(Pega_Preco);
        add(UKG);
        
        
        
        JLabel UP = new JLabel("Quant. por porção:");
        Pega_Unidade_porcao.setBounds(625, 275, 100, 30);
        UP.setBounds(610, 240, 160, 40);
        Pega_Unidade_porcao.setFont(fonte);
        UP.setFont(fonte);
        add(Pega_Unidade_porcao);
        add(UP);
        
         
        JLabel PT = new JLabel("Total Compra:");
        Pega_PT.setBounds(820, 275, 100, 30);
        PT.setBounds(820, 240, 100, 40);
        Pega_PT.setEditable(false);   
        Pega_PT.setFont(fonte);
        PT.setFont(fonte);
        add(Pega_PT);
        add(PT);
                
                
                
        
        
        
        
        Voltar.setBorder(new Borda_Redonda(7));
        Voltar.setBounds(130, 430, 160, 40);
        Voltar.addActionListener(this);
        Voltar.setFont(fonte);
        add(Voltar);
        
        
        Adicionar.setBorder(new Borda_Redonda(7));
        Adicionar.setBounds(808, 430, 160, 40);
        Adicionar.addActionListener(this);
        Adicionar.setFont(fonte);
        add(Adicionar);

  
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new BG_CadAlimentos_Frios());
        setBackground(Color.black);
        setResizable(false);
        setTitle("..:FastZooom:..");
        setSize(1100, 550);
        setLocationRelativeTo(null);
        setVisible(true);

    
    }
    
    public void preenche(){
        
         dados_AL.setTipo((String) Pega_Frios.getSelectedItem());
            dados_AL.setFornecedor(Pega_Nome_Fornecedor.getText());
            dados_AL.setFornecedor(Pega_QuantidadeKG.getText());
            dados_AL.setQuantT(Float.parseFloat(Pega_QuantidadeKG.getText()));
            dados_AL.setPreco(Float.parseFloat(Pega_Preco.getText()));
            float x = Float.parseFloat(Pega_Preco.getText())*Float.parseFloat(Pega_QuantidadeKG.getText());  
            Pega_PT.setText(String.valueOf(x));
            dados_AL.setPreco_Total(Float.parseFloat(Pega_Preco.getText())*Float.parseFloat(Pega_QuantidadeKG.getText()));
            
            dados_AL.setUniPorcao(Float.parseFloat(Pega_Unidade_porcao.getText()));
            
       

       
       
         
    }
    public void actionPerformed(ActionEvent e) {

      
       
        if (e.getSource() == Adicionar) {
            
            try {
                 preenche();
                ArmazenaDados();
                
                
            } catch (SQLException ex) {
                
        

            }
        
        }

        else if (e.getSource() == Voltar) {
            
            dispose();
           
        }
    }
 
    public void ArmazenaDados() throws SQLException {

                
         
        
         c.conexao();
        String sql = "insert into cad_alimentos (Tipo, Fornecedor,QuantidadeT, Preco_de_compra, preco_total, Quantidade_kg_porcao) values (?,?,?,?,?,?);";

        PreparedStatement stmt = c.conn.prepareStatement(sql);
        
        
        try {
                
            stmt.setString(1,dados_AL.getTipo());
            stmt.setString(2,dados_AL.getFornecedor());
            stmt.setFloat(3,dados_AL.getQuantT());
            stmt.setFloat(4,dados_AL.getPreco());
            stmt.setFloat(5,dados_AL.getPreco_Total());
            stmt.setFloat(6,dados_AL.getUniPorcao());
        
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar este produto!");
                        
            Logger.getLogger(Cadastro_de_Bebidas.class.getName()).log(Level.SEVERE, null, ex);
                       
       } 
   
        
        stmt.execute();

        stmt.close();
        
    }

    
    public static void main(String [] args){
        
        new Cadastro_Alimentos_Frios();
        
    }
    
    
    
}
