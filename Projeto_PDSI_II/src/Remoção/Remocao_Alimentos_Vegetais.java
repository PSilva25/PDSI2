
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


public class Remocao_Alimentos_Vegetais extends JFrame {
    
    
    JButton Cancelar = new JButton("Cancelar");
    JButton Remover = new JButton("Remover");
 

    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_QuantsPocKG = new JTextField();
    JTextField Pega_QuantidadeKG = new JTextField();
    JTextField Pega_Preco = new JTextField();
    JTextField Pega_Vegetais = new JTextField();
    
    
    DAO c = new DAO();

    
    public Remocao_Alimentos_Vegetais() {
        
        
        Font fonte = new Font("SansSerif", Font.BOLD, 15);

           
        JLabel Vegetais = new JLabel("Vegetais: ");
        Pega_Vegetais.setBounds(255, 185, 130, 30);
        Vegetais.setBounds(180, 180, 130, 40);
        Pega_Vegetais.setFont(fonte);
        Pega_Vegetais.setEditable(false);
        Vegetais.setFont(fonte);
        add(Pega_Vegetais);
        add(Vegetais);       
        
        
        
        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(275, 265, 210, 30);
        Fornecedor.setBounds(180, 260, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Pega_Nome_Fornecedor.setEditable(false);
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);
        
        
                
                
        JLabel Quantidade = new JLabel("Quantidade(KG):");
        Pega_QuantidadeKG.setBounds(775, 165, 100, 30);
        Quantidade.setBounds(650, 160, 130, 40);
        Pega_QuantidadeKG.setFont(fonte);
        Pega_QuantidadeKG.setEditable(false);
        Quantidade.setFont(fonte);
        add(Pega_QuantidadeKG);
        add(Quantidade);
        
       
    
        JLabel UKG = new JLabel("Quant. por Porção:");
        Pega_QuantsPocKG.setBounds(790, 225, 100, 30);
        UKG.setBounds(650, 220, 160, 40);
        Pega_QuantsPocKG.setFont(fonte);
        Pega_QuantsPocKG.setEditable(false);
        UKG.setFont(fonte);
        add(Pega_QuantsPocKG);
        add(UKG);
                
                
                
        JLabel UP = new JLabel("Preço por Kg:");
        Pega_Preco.setBounds(755, 285, 100, 30);
        UP.setBounds(650, 280, 160, 40);
        Pega_Preco.setFont(fonte);
        Pega_Preco.setEditable(false);
        UP.setFont(fonte);
        add(Pega_Preco);
        add(UP);
        
        
        
        Cancelar.setBorder(new Borda_Redonda(7));
        Cancelar.setBounds(130, 430, 160, 40);
        //Cancelar.addActionListener(this);
        Cancelar.setFont(fonte);
        add(Cancelar);
        
        
        Remover.setBorder(new Borda_Redonda(7));
        Remover.setBounds(808, 430, 160, 40);
        //Remover.addActionListener(this);
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
        
        new Remocao_Alimentos_Vegetais();
        
    }
    
    
    
}
