
package Remoção;


import Backgrounds.BG_Remocao;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import Listagem.Listagem_Bebidas;
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
import projeto_pdsi_ii.Bebida;
import projeto_pdsi_ii.Lanche;
import projeto_pdsi_ii.Registro;


public class Remocao_Estoque_Bebidas extends JFrame implements ActionListener {
    
    
    JButton Cancelar = new JButton("Cancelar");
    JButton Remover = new JButton("Remover");

    Bebida dados_bebida = new Bebida();
    
    ArrayList<Registro> RegistroList = new ArrayList<Registro>();
    ArrayList<Bebida> BebidaList = new ArrayList<Bebida>();
    
    
    ArrayList<String> Refrigerante = new ArrayList<>();
    ArrayList<String> Suco = new ArrayList<>();
    ArrayList<String> Milkshake = new ArrayList<>();


    JTextField Pega_Nome_Bebida = new JTextField();
    JTextField Pega_Quantidade_Bebida = new JTextField();
    JTextField Pega_Preco_Bebida = new JTextField();
    JTextField Pega_Volume_Bebida = new JTextField();
    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_Tipo_Bebida = new JTextField();
    JTextField volume = new JTextField();
    
    
    JTextArea Mostra_Quantidade = new JTextArea();
    JTextArea Mostra_Modelo = new JTextArea();
    JTextArea Mostra_Marca = new JTextArea();
    JTextArea Mostra_Preco = new JTextArea();
    JTextArea Mostra_Nome = new JTextArea();
    
    
  
    DAO c = new DAO();

    int Id;
    float total = 0;
    
    int indexLanhe = 0, indexBebida = 0, tipo = 0;

    
    
    public Remocao_Estoque_Bebidas(int ID) throws SQLException {
        
        Id=ID;
        
        Pega_Dados(ID);
       
        Font fonte = new Font("SansSerif", Font.BOLD, 14);

        JLabel Tipo_Bebida = new JLabel("Tipo: ");
        Pega_Tipo_Bebida.setBounds(225, 165, 130, 30);
        Tipo_Bebida.setBounds(180, 160, 130, 40);
        Pega_Tipo_Bebida.setFont(fonte);
        Pega_Tipo_Bebida.setEditable(false);
        Pega_Tipo_Bebida.setText(dados_bebida.getTipo());
        Tipo_Bebida.setFont(fonte);
        add(Pega_Tipo_Bebida);
        add(Tipo_Bebida);
        
            

        JLabel Nome_Bebida = new JLabel("Bebida:");
        Pega_Nome_Bebida.setBounds(240, 225, 210, 30);
        Nome_Bebida.setBounds(180, 220, 130, 40);
        Pega_Nome_Bebida.setFont(fonte);
        Pega_Nome_Bebida.setEditable(false);
        Pega_Nome_Bebida.setText(dados_bebida.getBebida());
        Nome_Bebida.setFont(fonte);
        add(Pega_Nome_Bebida);
        add(Nome_Bebida);

        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(275, 285, 210, 30);
        Fornecedor.setBounds(180, 280, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Pega_Nome_Fornecedor.setEditable(false);
        Pega_Nome_Fornecedor.setText(dados_bebida.getFornecedor());
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);


        
        JLabel Volume = new JLabel("Volume:");
        volume.setBounds(715, 165, 100, 30);
        Volume.setBounds(650, 160, 130, 40);
        volume.setFont(fonte);
        volume.setEditable(false);
        volume.setText(dados_bebida.getVolume());
        Volume.setFont(fonte);
        add(volume);
        add(Volume);
        
        

        JLabel Quantidade = new JLabel("Quantidade:");
        Pega_Quantidade_Bebida.setBounds(745, 225, 100, 30);
        Quantidade.setBounds(650, 220, 130, 40);
        Pega_Quantidade_Bebida.setFont(fonte);
        Pega_Quantidade_Bebida.setEditable(false);
        Pega_Quantidade_Bebida.setText(String.valueOf(dados_bebida.getQuantidade()));
        Quantidade.setFont(fonte);
        add(Pega_Quantidade_Bebida);
        add(Quantidade);

        JLabel Preco = new JLabel("Preço por unidade:");
        Pega_Preco_Bebida.setBounds(790, 285, 100, 30);
        Preco.setBounds(650, 280, 140, 40);
        Pega_Preco_Bebida.setFont(fonte);
        Pega_Preco_Bebida.setEditable(false);
        Pega_Preco_Bebida.setText(String.valueOf(dados_bebida.getPreco()));
        Preco.setFont(fonte);
        add(Pega_Preco_Bebida);
        add(Preco);
        
        
        
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
        
        if (e.getSource() == Remover) {
          
            Remove_dados(Id);
            
            new Listagem_Bebidas();
            
        } if (e.getSource() == Cancelar) {

            dispose();
            new Listagem_Bebidas();
     } 
    }
    
    
    public void Pega_Dados(int ID) throws SQLException{

        c.conexao();
        
         
        c.executaSQL("select * from bebida where ID_bebida = " + ID);
   
        try {

            c.rs.first();
                    
                    dados_bebida.setBebida(c.rs.getString("Nome"));
                    dados_bebida.setTipo(c.rs.getString("Tipo"));
                    dados_bebida.setFornecedor((c.rs.getString("Fornecedor")));
                    dados_bebida.setVolume (c.rs.getString("Volume"));
                    dados_bebida.setQuantidade(c.rs.getInt("Quant"));
                    dados_bebida.setPreco(c.rs.getFloat("Preco"));
                    
                    
            } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        } 
    
    }
    
    public void Remove_dados(int Id){
        
        String sql = "delete from bebida where ID_Bebida='" + Id + "'";

            try {

                PreparedStatement stmt = c.conn.prepareStatement(sql);


                stmt.executeUpdate();

                //mensagem de alerta informando que os dados foram deletados
                JOptionPane.showMessageDialog(null, "DADO DELETADO!");

            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, e1);
            }

        
    }
   
    public static void main(String [] args){
        
       // new Remocao_Estoque_Bebidas();
        
    }
    
    
    
}
