
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


public class Remocao_Alimentos_Massas extends JFrame {
    
    
    JButton Cancelar = new JButton("Cancelar");
    JButton Remover = new JButton("Remover");
 

    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_Quantidade = new JTextField();
    JTextField Pega_Unidade_porcao = new JTextField();
    JTextField Pega_Massas = new JTextField();
    
    
    DAO c = new DAO();

    
    public Remocao_Alimentos_Massas() {
        
        
        Font fonte = new Font("SansSerif", Font.BOLD, 15);

           
        JLabel Massas = new JLabel("Massas: ");
        Pega_Massas.setBounds(245, 185, 130, 30);
        Massas.setBounds(180, 180, 130, 40);
        Pega_Massas.setFont(fonte);
        Pega_Massas.setEditable(false);
        Massas.setFont(fonte);
        add(Pega_Massas);
        add(Massas);       
      
        
        
        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(275, 265, 210, 30);
        Fornecedor.setBounds(180, 260, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Pega_Nome_Fornecedor.setEditable(false);
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);
                
        
        JLabel UP = new JLabel("Unidades:");
        Pega_Quantidade.setBounds(735, 185, 100, 30);
        UP.setBounds(655, 180, 160, 40);
        Pega_Quantidade.setFont(fonte);
        Pega_Quantidade.setEditable(false);
        UP.setFont(fonte);
        add(Pega_Quantidade);
        add(UP);
        
        
        
        
        JLabel UP1 = new JLabel("Uni. por porção:");
        Pega_Unidade_porcao.setBounds(780, 265, 100, 30);
        UP1.setBounds(655, 260, 160, 40);
        Pega_Unidade_porcao.setFont(fonte);
        Pega_Unidade_porcao.setEditable(false);
        UP1.setFont(fonte);
        add(Pega_Unidade_porcao);
        add(UP1);
        
        
        
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
        
        new Remocao_Alimentos_Massas();
        
    }
    
    
    
}
