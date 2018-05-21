
package Remoção;


import Backgrounds.BG_Remocao;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Remocao_Alimentos_Frios extends JFrame {
    
    
    JButton Cancelar = new JButton("Cancelar");
    JButton Remover = new JButton("Remover");

    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_Preco = new JTextField();
    JTextField Pega_Total_Compra = new JTextField();
    JTextField Pega_QuantidadeKG = new JTextField();
    JTextField Pega_Unidade_porcao = new JTextField();
    JTextField Pega_PT = new JTextField();
    JTextField Pega_Frios = new JTextField();
    
    
    DAO c = new DAO();

    
    public Remocao_Alimentos_Frios() {
        
        
        Font fonte = new Font("SansSerif", Font.BOLD, 15);

           
        JLabel Frios = new JLabel("Frios: ");
        Pega_Frios.setBounds(230, 185, 210, 30);
        Frios.setBounds(180, 180, 130, 40);
        Pega_Frios.setFont(fonte);
        Pega_Frios.setEnabled(true);
        Pega_Frios.setEditable(false);
        Frios.setFont(fonte);
        add(Pega_Frios);
        add(Frios); 
                
        
        
        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(275, 265, 210, 30);
        Fornecedor.setBounds(180, 260, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Pega_Nome_Fornecedor.setEditable(false);
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);
        
        
                
                
        JLabel Quantidade = new JLabel("Quantidade(KG):");
        Pega_QuantidadeKG.setBounds(620, 195, 100, 30);
        Quantidade.setBounds(610, 160, 130, 40);
        Pega_QuantidadeKG.setFont(fonte);
        Pega_QuantidadeKG.setEditable(false);
        Quantidade.setFont(fonte);
        add(Pega_QuantidadeKG);
        add(Quantidade);
        
       
    
        JLabel UKG = new JLabel("Preço por Kg:");
        Pega_Preco.setBounds(820, 195, 100, 30);
        UKG.setBounds(820, 160, 130, 40);
        Pega_Preco.setFont(fonte);
        Pega_Preco.setEditable(false);
        UKG.setFont(fonte);
        add(Pega_Preco);
        add(UKG);
        
        
        
        JLabel UP = new JLabel("Quant. por porção:");
        Pega_Unidade_porcao.setBounds(625, 275, 100, 30);
        UP.setBounds(610, 240, 160, 40);
        Pega_Unidade_porcao.setFont(fonte);
        Pega_Unidade_porcao.setEditable(false);
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
                
                
                
        
        
        
        
        Cancelar.setBorder(new Borda_Redonda(7));
        Cancelar.setBounds(130, 430, 160, 40);
        //Cancelar.addActionListener(this);
        Cancelar.setFont(fonte);
        add(Cancelar);
        
        
        Remover.setBorder(new Borda_Redonda(7));
        Remover.setBounds(808, 430, 160, 40);
        //Alterar.addActionListener(this);
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
    
    
    
    public static void main(String [] args){
        
        new Remocao_Alimentos_Frios();
        
    }
    
    
    
}
