package Listagem;


import Backgrounds.BG_Listagem_Bebidas;
import Alterações.Alteracao_Alimentos_Frios;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import Cadastros.Cadastro_de_Bebidas;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import Cadastros.Cadastro_Alimentos_Frios;


public class Listagem_Frios extends JFrame implements ActionListener {
    
    
    
   int Id=0;
    JTable tableLista;
    DefaultTableModel modelo;
    JScrollPane barra;
    
    JButton Adicionar = new JButton("Adicionar");
    JButton Alterar = new JButton("Alterar");
    JButton Apagar = new JButton("Apagar");
    JButton Voltar = new JButton("Voltar");
    
    JTextField Busca_tabela = new JTextField();
    
    TableRowSorter Filtro;
    
    DAO con = new DAO();
    
    
    
    
    public Listagem_Frios() {
        
        
        Font fonte = new Font("SansSerif", Font.BOLD, 14);
        
        Container c = this.getContentPane();
        c.setLayout(null);
       
      
        tabela();
        
        
        tableLista = new JTable();
        tableLista.setBackground(Color.WHITE);
        tableLista.setModel(modelo);
        tableLista.setFillsViewportHeight(true);
        
 
               
        barra = new JScrollPane(tableLista);
        barra.setBounds(80, 100, 920, 300);
        barra.setBorder(new LineBorder(Color.BLACK));
        barra.add(new BG_Listagem_Bebidas());
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
        
        
        Adicionar.setBorder(new Borda_Redonda(7));
        Adicionar.setBounds(660, 440, 100, 40);
        Adicionar.addActionListener(this);
        Adicionar.setFont(fonte);
        add(Adicionar);
        
        
        Alterar.setBorder(new Borda_Redonda(7));
        Alterar.setBounds(780, 440, 100, 40);
        Alterar.addActionListener(this);
        Alterar.setFont(fonte);
        add(Alterar);
        
        
        Apagar.setBorder(new Borda_Redonda(7));
        Apagar.setBounds(900, 440, 100, 40);
        Apagar.addActionListener(this);
        Apagar.setFont(fonte);
        add(Apagar);
    
        
             

        setTitle("..:FastZooom:..");
        setSize(1100, 550);  
        getContentPane().setBackground(Color.decode("#82b3d1"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }
    
    
    
    
    
    
    public void tabela(){
        
        String[] colunas = {"ID", "Tipo", "Fornecedor", "Quantidade", "Quat. Porção", "Preço de Compra", "Preço Total"};

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
                
                modelo.addRow(dados);
            
            }while (con.rs.next());
                        
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        }
        
        
    }
    
    
    public void actionPerformed(ActionEvent e) {  
        
        if (e.getSource() == Alterar){           
            
            int linhaSelecionada = -1;
            
            linhaSelecionada = tableLista.getSelectedRow();
            
            if (linhaSelecionada >= 0) {
                
                String ID = (String) tableLista.getValueAt(linhaSelecionada, 0);
                
                //AtualizarContato ic = new AtualizarContato(modelo, idContato, linhaSelecionada);
                //ic.setVisible(true);
                
                Id = Integer.parseInt(ID);
               dispose();
                try {
                
                    new Alteracao_Alimentos_Frios(Id);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Listagem_Frios.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            } else  if (e.getSource() == Adicionar) {
                
               
                
                new Cadastro_Alimentos_Frios();
                
            
            }else if (e.getSource() == Apagar) {
             
            //deletando os dados da tabela ao pressionar o botao deletar
            String sql = "delete from bebida where ID_alimento='" + Id + "'";

            try {

                PreparedStatement stmt = con.conn.prepareStatement(sql);


                stmt.executeUpdate();

                //mensagem de alerta informando que os dados foram deletados
                JOptionPane.showMessageDialog(null, "DADO DELETADO!");

            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, e1);
            }

            
            }
            
            else  if (e.getSource() == Voltar) {
               
                dispose();
                
            } else {
                
                JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha.");
            
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
        
        new Listagem_Frios();
        
    }

    

}
