package Listagem;


import Backgrounds.BG_CadAlimentos_Bebida;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import Alterações.Alteracao_Estoque_Bebidas;
import java.util.logging.Level;
import java.util.logging.Logger;
import Cadastros.Cadastro_de_Bebidas;
import Remoção.Remocao_Estoque_Bebidas;
import java.sql.PreparedStatement;

public class Listagem_Caixa extends JFrame implements ActionListener {
    
    
    int Id = 0, Status_Abrir = 0, Status_Fechar = 0;
   
    JTable tableLista;
    JTable TableListaAbrir;
    JTable TableListaFechar;
    
    DefaultTableModel modelo;
    DefaultTableModel modeloAbrir;
    DefaultTableModel modeloFechar;
    
    JScrollPane barra;
    JScrollPane barraAbrir;
    JScrollPane barraFechar;
    
    JButton Voltar = new JButton("Voltar");
    
    JTextField Busca_tabela = new JTextField();  
    
    JRadioButton Abrir = new JRadioButton("Abertura de Caixa");
    JRadioButton Fechar = new JRadioButton("Fechamento de Caixa");
    
    ButtonGroup Caixa = new ButtonGroup();  
    
    TableRowSorter Filtro;
     
    DAO con = new DAO();
    
    
    
    
    public Listagem_Caixa() {
        
        
        Font fonte = new Font("SansSerif", Font.BOLD, 15);
        
        
        Container c = this.getContentPane();
        c.setLayout(null);
       
      
        nada();
        
        
        Abrir.setBounds(460, 45, 170, 30);
        Abrir.addActionListener(this);
        Abrir.setFont(fonte);
        Abrir.setBackground(Color.decode("#009fe3"));
        add(Abrir);
        
                
        Fechar.setBounds(630, 45, 200, 30);
        Fechar.addActionListener(this);
        Fechar.setFont(fonte);
        Fechar.setBackground(Color.decode("#009fe3"));
        add(Fechar);
        
        
        Caixa.add(Abrir);
        Caixa.add(Fechar);
        
              
        tableLista = new JTable();
        tableLista.setBackground(Color.WHITE);
        tableLista.setModel(modelo);
        tableLista.setFillsViewportHeight(true);
        
                
        barra = new JScrollPane(tableLista);
        barra.setBounds(80, 100, 920, 300);
        barra.setBorder(new LineBorder(Color.BLACK));
        barra.add(new BG_CadAlimentos_Bebida());
        add(barra);   
        
        
        JLabel Busca = new JLabel("Filtro:");
        Busca_tabela.setBounds(130, 45, 300, 30);
        Busca.setBounds(80, 40, 50, 40);
        Busca_tabela.setFont(fonte);
        Busca.setFont(fonte);
        add(Busca_tabela);
        add(Busca);
        
        
        Busca_tabela.addKeyListener(new java.awt.event.KeyAdapter() {
            
            public void keyTyped(java.awt.event.KeyEvent evt) {
                
                textBuscarKeyTyped(evt);
                
            }
        }
        );
        
        
        Voltar.setBorder(new Borda_Redonda(7));
        Voltar.setBounds(80, 440, 100, 40);
        Voltar.addActionListener(this);
        Voltar.setFont(fonte);
        add(Voltar);
        
        
        
        setTitle("..:FastZooom:..");
        setSize(1100, 550);  
        getContentPane().setBackground(Color.decode("#009fe3"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }
    
    
    
    public void Posiciona_tabela_Abrir(){
        
        tabela();
        
        TableListaAbrir = new JTable();
        TableListaAbrir.setBackground(Color.WHITE);
        TableListaAbrir.setModel(modeloAbrir);
        TableListaAbrir.setFillsViewportHeight(true);
                       
        barraAbrir = new JScrollPane(TableListaAbrir);
        barraAbrir.setBounds(80, 100, 920, 300);
        barraAbrir.setBorder(new LineBorder(Color.BLACK));
        add(barraAbrir);   
        
    }
    
     
    public void Posiciona_tabela_Fechar(){
        
        tabela2();
        
        TableListaFechar = new JTable();
        TableListaFechar.setBackground(Color.WHITE);
        TableListaFechar.setModel(modeloFechar);
        TableListaFechar.setFillsViewportHeight(true);
                       
        barraFechar = new JScrollPane(TableListaFechar);
        barraFechar.setBounds(80, 100, 920, 300);
        barraFechar.setBorder(new LineBorder(Color.BLACK));
        add(barraFechar);   
        
    }
    
     
    public void nada(){
        
        String[] colunas = {"  ", "  ", "  ", "  "};

        modelo = (DefaultTableModel) (new DefaultTableModel() {

            public boolean isCellEditable(int row, int coluna) {
                return false;
            }
            
            public boolean isCellRedimentionable(int row, int coluna) {
                return true;
            }
            
        });
        
        modelo.setColumnIdentifiers(colunas);
        modelo.setNumRows(0);
       
        
    }
    
       
    public void tabela(){
        
        String[] colunas = {"ID", "Bebida", "Tipo", "Preco"};

        modeloAbrir = (DefaultTableModel) (new DefaultTableModel() {

            public boolean isCellEditable(int row, int coluna) {
                return false;
            }
            
            public boolean isCellRedimentionable(int row, int coluna) {
                return true;
            }
            
        });
        
        modeloAbrir.setColumnIdentifiers(colunas);
        modeloAbrir.setNumRows(0);
        
        con.conexao();

        con.executaSQL("select * from bebida");
   
        try {

            con.rs.first();
                   
            do{     
                
                String[] dados = new String[4];

                for (int i = 0; i < 4; i++) {
                    
                    dados[0] = String.valueOf(con.rs.getInt("ID_bebida"));
                    dados[1] = con.rs.getString("Nome");
                    dados[2] = con.rs.getString("Tipo");
                    dados[3] = String.valueOf(con.rs.getString("Preco"));

                }
                
                modeloAbrir.addRow(dados);
            
            }while (con.rs.next());
                        
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        }
        
        
    }
    
    
    public void tabela2(){
        
        String[] colunas = {"ID", "Tipo", "Fornecedor", "Quantidade", "Quat. Porção", "Preço de Compra", "Preço Total"};

        modeloFechar = (DefaultTableModel) (new DefaultTableModel() {

            public boolean isCellEditable(int row, int coluna) {
                return false;
            }
            
            public boolean isCellRedimentionable(int row, int coluna) {
                return true;
            }
            
        });
        
        modeloFechar.setColumnIdentifiers(colunas);
        modeloFechar.setNumRows(0);
        
        con.conexao();

        con.executaSQL("select * from cad_alimentos");
   
        try {

            con.rs.first();
                   
            do{     
                
                String[] dados = new String[7];

                for (int i = 0; i < 7; i++) {
                    
                    dados[0] = String.valueOf(con.rs.getInt("ID_alimento"));
                    dados[1] = con.rs.getString("Tipo");
                    dados[2] = con.rs.getString("Fornecedor");
                    dados[3] = String.valueOf(con.rs.getInt("QuantidadeT"));
                    dados[4] = String.valueOf(con.rs.getFloat("Quantidade_kg_porcao"));
                    dados[5] = String.valueOf(con.rs.getInt("Preco_de_compra"));
                    dados[6] = String.valueOf(con.rs.getInt("Preco_total"));

                }
                
                modeloFechar.addRow(dados);
            
            }while (con.rs.next());
                        
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        }
        
        
    }
    
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == Abrir){
            
            Status_Abrir = 1;
            
            barra.setVisible(false);
            
            if(Status_Fechar != 0) barraFechar.setVisible(false);
                       
            Posiciona_tabela_Abrir();
            
            
        }else if(e.getSource() == Fechar){
            
            Status_Fechar = 1;
            
            barra.setVisible(false);
            
            if(Status_Abrir != 0) barraAbrir.setVisible(false);
            
            Posiciona_tabela_Fechar();
            
            
        }else if(e.getSource() == Voltar) {
               
                dispose();
                      
            }    
        }
       
         
    private void textBuscarKeyTyped(java.awt.event.KeyEvent evt) {                                   
        
        Busca_tabela.addKeyListener(new KeyAdapter() {
            
            public void keyReleased(final KeyEvent e) {
                
                String cadena = (Busca_tabela.getText());
                
                Busca_tabela.setText(cadena);
                
                filtro();
            }
        });
        
        Filtro = new TableRowSorter(tableLista.getModel());
        tableLista.setRowSorter(Filtro);

    }
    
    
    public void filtro() {
        
        Filtro.setRowFilter(RowFilter.regexFilter(Busca_tabela.getText(), 1));
    
    }
    
      
    public static void main(String [] args){
        
        new Listagem_Caixa();
        
    }

    

}
