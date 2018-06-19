
package Cadastros;

import Cadastros.*;
import Backgrounds.*;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import Getters_e_Setters.*;
import Listagem.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Ingredientes extends JFrame implements ActionListener{
      
    ArrayList<Componentes_Pedido> Dados_Frios = new ArrayList<Componentes_Pedido>();
    ArrayList<Componentes_Pedido> Dados_Massas = new ArrayList<Componentes_Pedido>();
    ArrayList<Componentes_Pedido> RegistroList = new ArrayList<Componentes_Pedido>();
    
    ArrayList<String> LFrios = new ArrayList<>();
    ArrayList<String> LMassas = new ArrayList<>();
   
    JRadioButton Frios = new JRadioButton("Frios");
    JRadioButton Massas = new JRadioButton("Massas");
    ButtonGroup group = new ButtonGroup();
    
    JComboBox<String> Representa = new JComboBox<>();
    JComboBox<String> Pega_Frios = new JComboBox<>();
    JComboBox<String> Pega_Massas = new JComboBox<>();
   
    JTextArea Mostra_Tipo = new JTextArea();
    JTextArea Mostra_Nome = new JTextArea();
    JTextArea Mostra_Quantidade = new JTextArea();

    JTextField Pega_Quantidade = new JTextField();
      
    JButton Adicionar = new JButton("Adicionar");
    JButton Cancelar = new JButton("Cancelar");
    JButton Finalizar = new JButton("Finalizar");
    
    String Forma_Pagamento;
   
    int ID = 0, tipo = 0;
    
    DAO c = new DAO();
  
    
    public Ingredientes(int id) {
        
        this.ID = id;
        
        Font fonte = new Font("SansSerif", Font.BOLD, 17);
        Font fonte_botoes = new Font("SansSerif", Font.BOLD, 17);          
               
        Frios.setBackground(Color.decode("#009fe3"));
        Frios.setBounds(200, 60, 100, 30);
        Frios.addActionListener(this);
        Frios.setFont(fonte);
        add(Frios);
              
        Massas.setBackground(Color.decode("#009fe3"));
        Massas.setBounds(350, 60, 100, 30);
        Massas.addActionListener(this);
        Massas.setFont(fonte);
        add(Massas);
                
        group.add(Frios);
        group.add(Massas);      
        
        Representa.setBounds(200, 100, 150, 30);
        Representa.setFont(fonte);
        
        add(Representa);
        add(Pega_Frios);
        add(Pega_Massas);
         
        JLabel Quantidade = new JLabel("Quantidade:");
        Pega_Quantidade.setBounds(300, 155, 70, 30);
        Quantidade.setBounds(200, 150, 130, 40);
        Pega_Quantidade.setFont(fonte);
        Quantidade.setFont(fonte);
        add(Pega_Quantidade);
        add(Quantidade);
                
        JLabel N = new JLabel("Tipo:");
        Mostra_Tipo.setBounds(40, 300, 200, 160);
        N.setBounds(40, 270, 130, 40);
        Mostra_Tipo.setEditable(false);        
        Mostra_Tipo.setFont(fonte);
        N.setFont(fonte);
        add(Mostra_Tipo);
        add(N);
               
        JLabel P = new JLabel("Nome:");
        Mostra_Nome.setBounds(240, 300, 200, 160);
        P.setBounds(240, 270, 130, 40);
        Mostra_Nome.setEditable(false);       
        Mostra_Nome.setFont(fonte);
        P.setFont(fonte);
        add(Mostra_Nome);
        add(P);
               
        JLabel Q = new JLabel("Quantidade:");
        Mostra_Quantidade.setBounds(440, 300, 115, 160);
        Q.setBounds(440, 270, 130, 40);
        Mostra_Quantidade.setEditable(false);       
        Mostra_Quantidade.setFont(fonte);
        Q.setFont(fonte);
        add(Mostra_Quantidade);
        add(Q);
               
        Adicionar.setBorder(new Borda_Redonda(7));
        Adicionar.setBounds(395, 210, 160, 40);
        Adicionar.addActionListener(this);
        Adicionar.setFont(fonte_botoes);
        add(Adicionar);
        
        Cancelar.setBorder(new Borda_Redonda(7));
        Cancelar.setBounds(25, 500, 160, 40);
        Cancelar.addActionListener(this);
        Cancelar.setFont(fonte_botoes);
        add(Cancelar);
               
        Finalizar.setBorder(new Borda_Redonda(7));
        Finalizar.setBounds(395, 500, 160, 40);
        Finalizar.addActionListener(this);
        Finalizar.setFont(fonte_botoes);
        add(Finalizar);
 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new BG_Adicionais());
        setBackground(Color.black);
        setResizable(false);
        setTitle("..:FastZooom:..");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
     
    public void Posiciona_Frios() {

        Pega_Frios.setBounds(200, 100, 150, 30);
        Pega_Frios.setEditable(false);
        Pega_Frios.setSelectedIndex(-1);

    }
    

    public void Posiciona_Massas() {

        Pega_Massas.setBounds(200, 100, 150, 30);
        Pega_Massas.setEditable(false);
        Pega_Massas.setSelectedIndex(-1);

    }
    
 
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == Frios){
            
            tipo = 1;
            
            Posiciona_Frios();
            
            Representa.setVisible(false);
            Pega_Frios.setVisible(true);
            Pega_Massas.setVisible(false);

            if (LFrios.isEmpty() == true) {
                
                Posiciona_Frios();
                
                Preenche_list(1);
 
                Pega_Frios.addItem("");

                for (int i = 0; i < LFrios.size(); i++) {

                    Pega_Frios.addItem(LFrios.get(i));

                }            

            }
        
        }
        
        if (e.getSource() == Massas) {
            
            tipo = 2;    
            
            Posiciona_Massas();
            
            Representa.setVisible(false);
            Pega_Frios.setVisible(false);
            Pega_Massas.setVisible(true);

            if (LMassas.isEmpty() == true) {
                
                Posiciona_Massas();
                
                Preenche_list(2);

                Pega_Massas.addItem("");

                for (int i = 0; i < LMassas.size(); i++) {

                    Pega_Massas.addItem(LMassas.get(i));

                }
                
            }
            
        }else if(e.getSource() == Adicionar){
                
            Itens(tipo);
            MostraPedidos();
                
        }else if(e.getSource() == Cancelar){
            
            dispose();
            
        }else if(e.getSource() == Finalizar){
                   
            try {
            
                ArmazenaDados();
                dispose();
                
            } catch (SQLException ex) {
            
                Logger.getLogger(Ingredientes.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        
        }   
        
    }
        
     
    public void Preenche_list(int tipo) {

        c.conexao();

        if(tipo == 1) c.executaSQL("select * from estoque_frios");
        if(tipo == 2) c.executaSQL("select * from estoque_massas");

        try {

            c.rs.first();
            
            do{

                String nome = c.rs.getString("Tipo");

                if(tipo == 1) LFrios.add(nome);
                if(tipo == 2) LMassas.add(nome);       
            
            }while(c.rs.next());

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        }
        
    }
      
    
    public void Itens(int selecionado) {
        
        c.conexao();
        
        Componentes_Pedido cp = new Componentes_Pedido();
              
        if(selecionado == 1){
            
            cp.setId(ID);
            cp.setTipo("Frio");
            cp.setNome((String) Pega_Frios.getSelectedItem());          
            cp.setQuantidade(Integer.parseInt(Pega_Quantidade.getText()));
            
            Dados_Frios.add(cp);
            RegistroList.add(cp);
                   
        }
        
        if(selecionado == 2){
            
            cp.setId(ID);
            cp.setTipo("Massa");
            cp.setNome((String) Pega_Massas.getSelectedItem());
            cp.setQuantidade(Integer.parseInt(Pega_Quantidade.getText()));
            
            Dados_Massas.add(cp);
            RegistroList.add(cp);
                   
        }
             
        
        Representa.setVisible(true);
        Pega_Frios.setVisible(false);
        Pega_Massas.setVisible(false);
        
        group.clearSelection();
        Pega_Quantidade.setText("");
        

    }
    
    
    public void MostraPedidos(){
            
        for (Componentes_Pedido r : RegistroList) {

            Mostra_Tipo.append("    " + r.getTipo()+ "\n");
            Mostra_Nome.append("|   " + r.getNome()+ "\n");
            Mostra_Quantidade.append("|    " + r.getQuantidade()+ "\n");

        }
        
        RegistroList.clear();
       
    }
    
    
    public void ArmazenaDados() throws SQLException {
  
        c.conexao();
        
        String sql1 = "insert into cardapio_ingredientes_frios (Id, Frio, Quantidade) values (?, ?, ?);";
        String sql2 = "insert into cardapio_ingredientes_massas (Id, Massa, Quantidade) values (?, ?, ?);";
                               
        System.out.println("frio: "+Dados_Frios.size());
        System.out.println("frio: "+Dados_Massas.size());
        
        if(Dados_Frios.isEmpty() == false){
            
            for(Componentes_Pedido cp : Dados_Frios){
                
                PreparedStatement stmt = c.conn.prepareStatement(sql1);
                
                try {
                    
                    stmt.setInt(1,ID);
                    stmt.setString(2,cp.getNome());
                    stmt.setInt(3,cp.getQuantidade());
                
                } catch (SQLException ex) {
                   
                    JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar este produto!");
                    Logger.getLogger(Cadastro_de_Bebidas.class.getName()).log(Level.SEVERE, null, ex);
                
                } 
                
                stmt.execute();
                stmt.close();
           
            }
            
        }
        
        if(Dados_Massas.isEmpty() == false){
            
            for(Componentes_Pedido cp : Dados_Massas){
                
                PreparedStatement stmt = c.conn.prepareStatement(sql2);
                
                try {
                    
                    stmt.setInt(1,ID);
                    stmt.setString(2,cp.getNome());
                    stmt.setInt(3,cp.getQuantidade());
                
                } catch (SQLException ex) {
                
                    JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar este produto!");
                    Logger.getLogger(Cadastro_de_Bebidas.class.getName()).log(Level.SEVERE, null, ex);
                
                } 
                
                stmt.execute();
                stmt.close();
           
            }
            
        }
              
    }
        
    
    public static void main(String[]args){
        
        new Ingredientes(1);
        
    }

}
