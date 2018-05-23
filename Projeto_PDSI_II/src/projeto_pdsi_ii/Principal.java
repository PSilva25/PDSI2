/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_pdsi_ii;

import Backgrounds.BG_Principal;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import Cadastros.*;
import Listagem.*;
import java.awt.event.ActionListener;

/**
 *
 * @author Jonycássio
 */
public class Principal extends JFrame implements ActionListener{
    
    
    Font fonte = new Font("SansSerif", Font.BOLD, 15);  
    
    JMenuBar Barra = new JMenuBar();
    
    JMenu Menu1 = new JMenu("Venda");
    JMenuItem Item1_1 = new JMenuItem("Efetuar Venda");
    
    JMenu Menu2 = new JMenu("Cadastros");
    JMenuItem Item2_1 = new JMenuItem("Pedidos");
    JMenuItem Item2_2 = new JMenuItem("Bebidas");
    JMenu MenuItem2_3 = new JMenu("Alimentos");
    JMenuItem Item2_3_1 = new JMenuItem("Frios");
    JMenuItem Item2_3_2 = new JMenuItem("Massas");
    JMenuItem Item2_3_3 = new JMenuItem("Vegetais");
      
    
    JMenu Menu3 = new JMenu("Listagem");
    JMenuItem Item3_1 = new JMenuItem("Pedidos");
    JMenuItem Item3_2 = new JMenuItem("Bebidas");
    JMenu MenuItem3_3 = new JMenu("Alimentos");
    JMenuItem Item3_3_1 = new JMenuItem("Frios");
    JMenuItem Item3_3_2 = new JMenuItem("Massas");
    JMenuItem Item3_3_3 = new JMenuItem("Vegetais");
    
    
    
    JMenu Menu4 = new JMenu("Caixa");
    JMenuItem Item4_1 = new JMenuItem("Abrir / Fechar Caixa");
    
    JMenu Menu5 = new JMenu("Sobre");
   
 
    
    public Principal(){
        
        
        
        Item1_1.setFont(fonte);
        Item1_1.addActionListener(this);
        
        Item2_1.setFont(fonte);
        Item2_1.addActionListener(this);
        
        Item2_2.setFont(fonte);
        Item2_2.addActionListener(this);
        
        Item2_3_1.setFont(fonte);
        Item2_3_1.addActionListener(this);
        
        Item2_3_2.setFont(fonte);
        Item2_3_2.addActionListener(this);
        
        Item2_3_3.setFont(fonte);
        Item2_3_3.addActionListener(this);
        
        Item3_1.setFont(fonte);
        Item3_1.addActionListener(this);
        
        Item3_2.setFont(fonte);
        Item3_2.addActionListener(this);
        
        Item3_3_1.setFont(fonte);
        Item3_3_1.addActionListener(this);
        
        Item3_3_2.setFont(fonte);
        Item3_3_2.addActionListener(this);
        
        Item3_3_3.setFont(fonte);
        Item3_3_3.addActionListener(this);
        
        Item4_1.setFont(fonte);
        Item4_1.addActionListener(this);
        
        MenuItem2_3.setFont(fonte);
        MenuItem3_3.setFont(fonte);
        
        
        
        Menu1.add(Item1_1);
        Menu1.setFont(fonte);
        
        ///////////////////////////////////
        
        Menu2.add(Item2_1);
        Menu2.add(Item2_2);
        
        MenuItem2_3.add(Item2_3_1);
        MenuItem2_3.add(Item2_3_2);
        MenuItem2_3.add(Item2_3_3);
        
        Menu2.add(MenuItem2_3);
        Menu2.setFont(fonte);
        
        ///////////////////////////////////
        
        Menu3.add(Item3_1);
        Menu3.add(Item3_2);
        
        MenuItem3_3.add(Item3_3_1);
        MenuItem3_3.add(Item3_3_2);
        MenuItem3_3.add(Item3_3_3);
        
        Menu3.add(MenuItem3_3);
        Menu3.setFont(fonte);
        
        ///////////////////////////////////
        
        Menu4.add(Item4_1);
        Menu4.setFont(fonte);
        
    
        Barra.setBounds(0,0,1100,40);
        add(Barra);
        
        Barra.add(Menu1);
        Barra.add(Menu2);
        Barra.add(Menu3);
        Barra.add(Menu4);
       
        Barra.setBackground(Color.GREEN);
        
        
        add(new BG_Principal());
        setBackground(Color.yellow);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);      
        setResizable(false);
        setTitle("..:FastZooom:..");
        setSize(1100, 550);
        setLocationRelativeTo(null);
        setVisible(true);
              
        
    }
    
    
    
    
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == Item1_1) new CompraF();
        //if(e.getSource() == Item2_1) 
        if(e.getSource() == Item2_2) new Cadastro_de_Bebidas();
        if(e.getSource() == Item2_3_1) new Cadastro_Alimentos_Frios();
        if(e.getSource() == Item2_3_2) new Cadastro_Alimentos_Massas();
        if(e.getSource() == Item2_3_3) new Cadastro_Alimentos_Vegetais();
        //if(e.getSource() == Item3_1)
        if(e.getSource() == Item3_2) new Listagem_Bebidas();
        if(e.getSource() == Item3_3_1) new Listagem_Frios();
        if(e.getSource() == Item3_3_2) new Listagem_Massas();
        if(e.getSource() == Item3_3_3) new Listagem_Vegetais();
        //if(e.getSource() == Item4_1)
        //if(e.getSource() == Item5_1)
        
    }
    
    
    
    
    public static void main(String[]args) {
        
        new Principal();
        
    }
    
    
}
