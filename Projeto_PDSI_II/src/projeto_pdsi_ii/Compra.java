package projeto_pdsi_ii;


import Backgrounds.BG_Venda;
import Getters_e_Setters.*;
import Botoes.Borda_Redonda;
import Banco_de_Dados.DAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.util.logging.Level;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JButton;
import Banco_de_Dados.DAO;
import java.io.FileWriter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;



public class Compra extends JFrame implements ActionListener {

    String x = null, Q_volume = null;
    
    String nome_bebida = null, nome_lanche = null;
    
    float total_compra = 0,total_lanche=0,total_bebida=0 ;
    
    int frios = 0, massas = 0, indexLanche = 0, indexBebida = 0, tipo = 0;  
    
    JButton Registrar_Compra = new JButton("Registrar Pedido");
    JButton Cancelar_Compra = new JButton("Cancelar Pedido");
    JButton Adicionar = new JButton("Adicionar Pedido");
    JButton Voltar = new JButton("Voltar");
   
    ArrayList<Registro> RegistroList = new ArrayList<Registro>();
    ArrayList<Lanche> LancheList = new ArrayList<Lanche>();
    ArrayList<Bebida> BebidaList = new ArrayList<Bebida>();
        
    ArrayList<String> Refrigerante = new ArrayList<>();
    ArrayList<String> Suco = new ArrayList<>();
    ArrayList<String> Milkshake = new ArrayList<>();
    
    JTextField Pega_Quantidade = new JTextField();
    JTextField Pega_Quantidade2 = new JTextField();
    JTextField Mostra_Total = new JTextField();
    JTextField Pega_Nome = new JTextField();
    
    JTextArea Mostra_Quantidade = new JTextArea();
    JTextArea Mostra_Modelo = new JTextArea();
    JTextArea Mostra_Marca = new JTextArea();
    JTextArea Mostra_Preco = new JTextArea();
    JTextArea Mostra_Nome = new JTextArea();
   
    JComboBox<String> Pega_Tipo = new JComboBox<>();
    JComboBox<String> Pega_BebidaP = new JComboBox<>();
    JComboBox<String> Pega_BebidaR = new JComboBox<>();
    JComboBox<String> Pega_BebidaS = new JComboBox<>();
    JComboBox<String> Pega_BebidaM = new JComboBox<>();
   
    JRadioButton aRadioButton = new JRadioButton("Refrigerantes");
    JRadioButton bRadioButton = new JRadioButton("Sucos");
    JRadioButton cRadioButton = new JRadioButton("milkshake"); 
    ButtonGroup group = new ButtonGroup();
        
    JRadioButton Pequeno = new JRadioButton("300 ML");
    JRadioButton Medio = new JRadioButton("600 ML");
    JRadioButton Grande1 = new JRadioButton("1 L");
    JRadioButton Grande2 = new JRadioButton("2 L");
    ButtonGroup volume = new ButtonGroup();
    
    
    DAO c = new DAO();

   
    public Compra() {
        
        String x = null, Q_volume = null;
        
        int frios = 0, massas = 0;
       
        Font fonte = new Font("SansSerif", Font.BOLD, 14);

        aRadioButton.setBackground(Color.decode("#009fe3"));
        aRadioButton.setBounds(600, 90, 130, 30);
        aRadioButton.addActionListener(this);
        aRadioButton.setFont(fonte);
        add(aRadioButton);
           
        bRadioButton.setBackground(Color.decode("#009fe3"));
        bRadioButton.setBounds(730, 90, 80, 30);
        bRadioButton.addActionListener(this);
        bRadioButton.setFont(fonte);
        add(bRadioButton);
        
        cRadioButton.setBackground(Color.decode("#009fe3"));       
        cRadioButton.setBounds(810, 90, 110, 30);
        cRadioButton.addActionListener(this);
        cRadioButton.setFont(fonte);
        add(cRadioButton);
                
        group.add(aRadioButton);
        group.add(bRadioButton);
        group.add(cRadioButton);
                    
        JLabel Tipo = new JLabel("Pedido: ");
        Pega_Tipo.setBounds(290, 125, 130, 30);
        Tipo.setBounds(230, 120, 130, 40);
        Pega_Tipo.setFont(fonte);
        Tipo.setFont(fonte);
        add(Pega_Tipo);
        add(Tipo);       
               
        ArrayList<String> tipo = new ArrayList<>();
           
        pegalanche(tipo);
            
        Pega_Tipo.removeAllItems();
            
        Pega_Tipo.addItem("");
            
        for (int i = 0; i < tipo.size(); i++) {

            Pega_Tipo.addItem(tipo.get(i));

        }
        
        JLabel Bebida = new JLabel("Bebida: ");
        Pega_BebidaP.setBounds(680, 135, 130, 30);
        Bebida.setBounds(620, 130, 130, 40);
        Pega_BebidaP.setFont(fonte);
        Bebida.setFont(fonte);
        add(Bebida);
        add(Pega_BebidaR);
        add(Pega_BebidaS);
        add(Pega_BebidaM);
        add(Pega_BebidaP);            
 
        JLabel Quantidade = new JLabel("Quantidade:");
        Pega_Quantidade.setBounds(317, 175, 70, 30);
        Quantidade.setBounds(230, 170, 130, 40);
        Pega_Quantidade.setFont(fonte);
        Quantidade.setFont(fonte);
        add(Pega_Quantidade);
        add(Quantidade);
                
        JLabel Quantidade2 = new JLabel("Quantidade:");
        Pega_Quantidade2.setBounds(707, 180, 70, 30);
        Quantidade2.setBounds(620, 175, 130, 40);
        Pega_Quantidade2.setFont(fonte);
        Quantidade2.setFont(fonte);
        add(Pega_Quantidade2);
        add(Quantidade2);
              
        Pequeno.setBackground(Color.decode("#009fe3"));
        Pequeno.setBounds(600, 220, 80, 30);
        Pequeno.addActionListener(this);
        Pequeno.setFont(fonte);
        add(Pequeno);

        Medio.setBackground(Color.decode("#009fe3"));
        Medio.setBounds(680, 220, 80, 30);
        Medio.addActionListener(this);
        Medio.setFont(fonte);
        add(Medio);

        Grande1.setBackground(Color.decode("#009fe3"));
        Grande1.setBounds(760, 220, 54, 30);
        Grande1.addActionListener(this);
        Grande1.setFont(fonte);
        add(Grande1);

        Grande2.setBackground(Color.decode("#009fe3"));
        Grande2.setBounds(814, 220, 80, 30);
        Grande2.addActionListener(this);
        Grande2.setFont(fonte);
        add(Grande2);

        volume.add(Pequeno);
        volume.add(Medio);
        volume.add(Grande1);
        volume.add(Grande2);
                
        JLabel N = new JLabel("PEDIDOS");
        Mostra_Nome.setBounds(150, 420, 230, 160);
        N.setBounds(150, 380, 130, 40);
        Mostra_Nome.setEditable(false);        
        Mostra_Nome.setFont(fonte);
        N.setFont(fonte);
        add(Mostra_Nome);
        add(N);
               
        JLabel P = new JLabel("PREÇO");
        Mostra_Preco.setBounds(380, 420, 230, 160);
        P.setBounds(380, 380, 130, 40);
        Mostra_Preco.setEditable(false);       
        Mostra_Preco.setFont(fonte);
        P.setFont(fonte);
        add(Mostra_Preco);
        add(P);
               
        JLabel Q = new JLabel("QUANTIDADE");
        Mostra_Quantidade.setBounds(610, 420, 230, 160);
        Q.setBounds(610, 380, 130, 40);
        Mostra_Quantidade.setEditable(false);       
        Mostra_Quantidade.setFont(fonte);
        Q.setFont(fonte);
        add(Mostra_Quantidade);
        add(Q);
               
        JLabel T = new JLabel("TOTAL:");
        Mostra_Total.setBounds(870, 420, 70, 30);
        T.setBounds(880, 380, 130, 40);
        Mostra_Total.setEditable(false);       
        Mostra_Total.setFont(fonte);
        T.setFont(fonte);
        add(Mostra_Total);
        add(T);
                
        Adicionar.setBorder(new Borda_Redonda(7));
        Adicionar.setBounds(808, 290, 160, 40);
        Adicionar.addActionListener(this);
        Adicionar.setFont(fonte);
        add(Adicionar);
        
        Voltar.setBorder(new Borda_Redonda(7));
        Voltar.setBounds(115, 660, 200, 40);
        Voltar.addActionListener(this);
        Voltar.setFont(fonte);
        add(Voltar);
       
        Registrar_Compra.setBorder(new Borda_Redonda(7));
        Registrar_Compra.setBounds(500, 660, 200, 40);
        Registrar_Compra.addActionListener(this);
        Registrar_Compra.setFont(fonte);
        add(Registrar_Compra);
       
        Cancelar_Compra.setBorder(new Borda_Redonda(7));
        Cancelar_Compra.setBounds(770, 660, 200, 40);
        Cancelar_Compra.addActionListener(this);
        Cancelar_Compra.setFont(fonte);
        add(Cancelar_Compra);
       
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new BG_Venda());
        setBackground(Color.black);
        setResizable(false);
        setTitle("..:FastZooom:..");
        setSize(1100, 800);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    
    
    public void Posiciona_BebidaR() {

        Pega_BebidaR.setBounds(680, 135, 130, 30);
        Pega_BebidaR.setEditable(false);
        Pega_BebidaR.setSelectedIndex(-1);

    }
    

    public void Posiciona_BebidaS() {

        Pega_BebidaS.setBounds(680, 135, 130, 30);
        Pega_BebidaS.setEditable(false);
        Pega_BebidaS.setSelectedIndex(-1);

    }
    

    public void Posiciona_BebidaM() {

        Pega_BebidaM.setBounds(680, 135, 130, 30);
        Pega_BebidaM.setEditable(false);
        Pega_BebidaM.setSelectedIndex(-1);

    }
    
    
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Pequeno) Q_volume = "300 ML";
        if (e.getSource() == Medio) Q_volume = "600 ML";
        if (e.getSource() == Grande1) Q_volume = "1 L";
        if (e.getSource() == Grande2) Q_volume = "2 L";
        
        if(e.getSource() == aRadioButton){
            
            tipo = 1;
            
            Posiciona_BebidaR();
            
            Pega_BebidaP.setVisible(false);
            Pega_BebidaR.setVisible(true);
            Pega_BebidaS.setVisible(false);
            Pega_BebidaM.setVisible(false);

            if (Refrigerante.isEmpty() == true) {
                
                Posiciona_BebidaR();
                Pega_do_Banco(1);
 
                Pega_BebidaR.addItem("");

                for (int i = 0; i < Refrigerante.size(); i++) {

                    Pega_BebidaR.addItem(Refrigerante.get(i));

                }            

            }
        
        }else if (e.getSource() == bRadioButton) {
            
            tipo = 2;    
            
            Posiciona_BebidaS();
            
            Pega_BebidaP.setVisible(false);
            Pega_BebidaR.setVisible(false);
            Pega_BebidaS.setVisible(true);
            Pega_BebidaM.setVisible(false);

            if (Suco.isEmpty() == true) {
                
                Posiciona_BebidaS();
                Pega_do_Banco(2);

                Pega_BebidaS.addItem("");

                for (int i = 0; i < Suco.size(); i++) {

                    Pega_BebidaS.addItem(Suco.get(i));

                }
                
            }
            
        } else if (e.getSource()==cRadioButton) {
            
            tipo = 3;
            
            Posiciona_BebidaM();           
            
            Pega_BebidaP.setVisible(false);
            Pega_BebidaR.setVisible(false);
            Pega_BebidaS.setVisible(false);
            Pega_BebidaM.setVisible(true);
           
            if (Milkshake.isEmpty() == true) {
                
                Posiciona_BebidaM();
                Pega_do_Banco(3);
                
                Pega_BebidaM.addItem("");
                
                for (int i = 0; i < Milkshake.size(); i++) {

                    Pega_BebidaM.addItem(Milkshake.get(i));

                }
            
            }
            
        }else if (e.getSource() == Adicionar) {
                       
            Lanche lanche = new Lanche();
            Bebida bebida = new Bebida();          

            if (Pega_Tipo.getSelectedItem().equals("") == false){
                              
                lanche.setPedido((String) Pega_Tipo.getSelectedItem());
                lanche.setQuantidade(Integer.parseInt(Pega_Quantidade.getText()));
            
                LancheList.add(lanche);
                
            }
                
            
            if(tipo != 0){
                
                if(tipo == 1) bebida.setBebida((String) Pega_BebidaR.getSelectedItem());
                if(tipo == 2) bebida.setBebida((String) Pega_BebidaS.getSelectedItem());
                if(tipo == 3) bebida.setBebida((String) Pega_BebidaM.getSelectedItem()); 

                bebida.setVolume(Q_volume);
                bebida.setQuantidade(Integer.parseInt(Pega_Quantidade2.getText()));

                BebidaList.add(bebida);
                    
            }
                              
            if(LancheList.isEmpty() == false) pegaDadosPedido(); 
            if(BebidaList.isEmpty() == false) pegaDadosBebida(); 
                
            MostraPedidos();

            Pega_BebidaP.setVisible(true);
            Pega_BebidaR.setVisible(false);
            Pega_BebidaS.setVisible(false);
            Pega_BebidaM.setVisible(false);

            group.clearSelection();
            volume.clearSelection();

            ArrayList<String> tipo = new ArrayList<>();

            pegalanche(tipo);
            Pega_Tipo.removeAllItems();
            Pega_Tipo.addItem("");

            for (int i = 0; i < tipo.size(); i++) {

                Pega_Tipo.addItem(tipo.get(i));

            }    
                   
        }else if (e.getSource() == Registrar_Compra) {
            
            new Decrementa_Estoque(LancheList, BebidaList, RegistroList);
                                           
            RegistroList.clear();
            BebidaList.clear();
            LancheList.clear();
            indexBebida = 0;
            indexLanche = 0;
            total_compra = 0;
            
            Mostra_Quantidade.setText(""); ;
            Mostra_Modelo.setText("");             
            Mostra_Marca.setText("");             
            Mostra_Preco.setText("");             
            Mostra_Nome.setText("") ;
            Mostra_Total.setText("");
               
            Pega_BebidaP.setVisible(true);             
            Pega_BebidaR.setVisible(false);
            Pega_BebidaS.setVisible(false);            
            Pega_BebidaM.setVisible(false);
                
            group.clearSelection();
            volume.clearSelection();
               
            ArrayList<String> tipo = new ArrayList<>();
           
            pegalanche(tipo);            
            Pega_Tipo.removeAllItems();            
            Pega_Tipo.addItem("");
            
            for (int i = 0; i < tipo.size(); i++) {

               Pega_Tipo.addItem(tipo.get(i));

            }       

        }else if (e.getSource() == Cancelar_Compra) {

            Mostra_Quantidade.setText(""); ;
            Mostra_Modelo.setText("");
            Mostra_Marca.setText("");
            Mostra_Preco.setText("");
            Mostra_Nome.setText("");
            Mostra_Total.setText("");

            Pega_BebidaP.setVisible(true);
            Pega_BebidaR.setVisible(false);
            Pega_BebidaS.setVisible(false);
            Pega_BebidaM.setVisible(false);
                
            group.clearSelection();
            volume.clearSelection();
               
            ArrayList<String> tipo = new ArrayList<>();
           
            pegalanche(tipo);            
            Pega_Tipo.removeAllItems();           
            Pega_Tipo.addItem("");
            
            for (int i = 0; i < tipo.size(); i++) {

                Pega_Tipo.addItem(tipo.get(i));

            }
            
        }else if(e.getSource() == Voltar){
            
            dispose();
            
        }

    }
    
    
    public void pegaDadosPedido() {
        

        c.conexao();

        c.executaSQL("select * from cardapio where Nome = '" + LancheList.get(indexLanche).getPedido() + "'");
        
        try {
                    
            Registro reg = new Registro();

            c.rs.first();
                                          
            reg.setNOME(c.rs.getString("Nome"));
            reg.setPRECO(Float.parseFloat(c.rs.getString("Preco")));
            reg.setQUANTIDADE(LancheList.get(indexLanche).getQuantidade());                                     

            total_lanche = total_lanche + (reg.getPRECO() * reg.getQUANTIDADE());
                                                          
            RegistroList.add(reg);
                    
            indexLanche++;

        }catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto1!");

        }
                
        Pega_Tipo.addItem("");
        Pega_Quantidade.setText("");
        Mostra_Preco.setText("");
        Mostra_Nome.setText("");
        Mostra_Quantidade.setText("");
    
    }
    
    
    public void pegaDadosBebida() {
               
        c.conexao();
        
        c.executaSQL("select * from estoque_bebidas where Nome = '" +BebidaList.get(indexBebida).getBebida()+ "'");
        
            try {
                    
                Registro reg = new Registro();
                    
                c.rs.first();
                    
                reg.setNOME(c.rs.getString("Nome"));                    
                reg.setPRECO(Float.parseFloat(c.rs.getString("Preco")));
                reg.setQUANTIDADE(BebidaList.get(indexBebida).getQuantidade());
  
                total_bebida = total_bebida + (reg.getPRECO() * reg.getQUANTIDADE());
                    
                RegistroList.add(reg);
                    
                indexBebida++;
                   
            }catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto2!");

            }
                
            total_compra = total_bebida + total_lanche; 
                
            Mostra_Total.setText(String.valueOf(total_compra));
               
            Pega_Quantidade2.setText("");               
            Mostra_Preco.setText("");
            Mostra_Nome.setText("");
            Mostra_Quantidade.setText("");    
        
    }
    
    
    public void MostraPedidos(){
            
        for (Registro r : RegistroList) {

            Mostra_Quantidade.append("|    " + r.getQUANTIDADE() + "\n");
            Mostra_Preco.append("|   " + r.getPRECO() + "\n");
            Mostra_Nome.append("    " + r.getNOME() + "\n");

        }
       
    }
    
    
    public void Pega_do_Banco(int bebida) {

        c.conexao();

        if(bebida == 1) c.executaSQL("select * from estoque_bebidas where Tipo = 'Refrigerante'");
        if(bebida == 2) c.executaSQL("select * from estoque_bebidas where Tipo = 'Suco'");
        if(bebida == 3) c.executaSQL("select * from estoque_bebidas where Tipo = 'Milkshake'");

        try {

            c.rs.first();
            
            do{

                String nome = c.rs.getString("Nome");

                if(bebida == 1) Refrigerante.add(nome);
                if(bebida == 2) Suco.add(nome);
                if(bebida == 3) Milkshake.add(nome);
                                        
            }while(c.rs.next());

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto3!");

        }
        
    }
    
    
    public void pegalanche(ArrayList abc) {

        int cont = 0;
        
        c.conexao();

        c.executaSQL("select * from cardapio");

        try {

            c.rs.first();
            
            do{

                String nome = c.rs.getString("Nome");

                abc.add(nome);

                cont++;
            
            }while(c.rs.next());

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto4!");

        }
        
    }
    
 
    public static void main(String[] args) {

        new Compra();

    }

}
