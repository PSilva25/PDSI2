
package Remoção;


import Backgrounds.BG_Remocao;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import Listagem.Listagem_Massas;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import Getters_e_Setters.Massas;


public class Remocao_Alimentos_Massas extends JFrame implements ActionListener {
    
    
    int Id=0;
    
    String[]botoes = {"SIM", "NAO"};
    
    Massas dados_M = new Massas();
    
    
    JButton Cancelar = new JButton("Cancelar");
    JButton Remover = new JButton("Remover");
 

    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_Quantidade = new JTextField();
    JTextField Pega_Unidade_porcao = new JTextField();
    JTextField Pega_Massas = new JTextField();
    
    
    DAO c = new DAO();

    
    public Remocao_Alimentos_Massas(int ID) throws SQLException {
        
        Id = ID;
        
        Pega_Dados(Id);
        
        Font fonte = new Font("SansSerif", Font.BOLD, 15);

           
        JLabel Massas = new JLabel("Massas: ");
        Pega_Massas.setBounds(245, 185, 130, 30);
        Massas.setBounds(180, 180, 130, 40);
        Pega_Massas.setFont(fonte);
        Pega_Massas.setEditable(false);
        Pega_Massas.setText(dados_M.getTipo());
        Massas.setFont(fonte);
        add(Pega_Massas);
        add(Massas);       
      
        
        
        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(275, 265, 210, 30);
        Fornecedor.setBounds(180, 260, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Pega_Nome_Fornecedor.setEditable(false);
        Pega_Nome_Fornecedor.setText(dados_M.getFornecedor());
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);
                
        
        JLabel UP = new JLabel("Unidades:");
        Pega_Quantidade.setBounds(735, 185, 100, 30);
        UP.setBounds(655, 180, 160, 40);
        Pega_Quantidade.setFont(fonte);
        Pega_Quantidade.setEditable(false);
        Pega_Quantidade.setText(String.valueOf(dados_M.getQuantidade()));
        UP.setFont(fonte);
        add(Pega_Quantidade);
        add(UP);
        
        
        
        
        JLabel UP1 = new JLabel("Uni. por porção:");
        Pega_Unidade_porcao.setBounds(780, 265, 100, 30);
        UP1.setBounds(655, 260, 160, 40);
        Pega_Unidade_porcao.setFont(fonte);
        Pega_Unidade_porcao.setEditable(false);
        Pega_Unidade_porcao.setText(String.valueOf(dados_M.getQuant_porcao()));
        UP1.setFont(fonte);
        add(Pega_Unidade_porcao);
        add(UP1);
        
        
        
        Cancelar.setBorder(new Borda_Redonda(7));
        Cancelar.setBounds(130, 430, 160, 40);
        Cancelar.addActionListener(this);
        Cancelar.setFont(fonte);
        add(Cancelar);
        
        
        Remover.setBorder(new Borda_Redonda(7));
        Remover.setBounds(808, 430, 160, 40);
        Remover.addActionListener(this);
        Remover.setFont(fonte);
        add(Remover);

  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new BG_Remocao());
        setBackground(Color.black);
        setResizable(false);
        setTitle("..:FastZooom:..");
        setSize(1100, 550);
        setLocationRelativeTo(null);
        setVisible(true);

    
    }
    public void actionPerformed(ActionEvent e) { 
        
         String[]botoes = {"SIM", "NAO"};
        if (e.getSource() == Remover) {
          
           int opcao = JOptionPane.showOptionDialog(null, "Realmente deseja excluir este produto", "Aviso!!!",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
           
            if(botoes[opcao].equals("SIM")){
            
                Remove_dados(Id);
                
                new Listagem_Massas();
            
            }else{
                
            JOptionPane.showMessageDialog(null, "OPERACAO CANCELADA");
            
            new Listagem_Massas();
            
            }
             
        } if (e.getSource() == Cancelar) {

            dispose();
            new Listagem_Massas();
     } 
    }
    
    
    public void Pega_Dados(int ID) throws SQLException{

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
    
    public void Remove_dados(int Id){
        
        String sql = "delete from cad_massas where ID_alimento='" + Id + "'";

            try {

                PreparedStatement stmt = c.conn.prepareStatement(sql);


                stmt.executeUpdate();

                //mensagem de alerta informando que os dados foram deletados
                JOptionPane.showMessageDialog(null, "DADO DELETADO!");

            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, e1);
            }

        dispose();
    }
   
    
    
    public static void main(String [] args){
        
        //new Remocao_Alimentos_Massas();
        
    }
    
    
    
}
