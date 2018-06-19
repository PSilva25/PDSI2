package Getters_e_Setters;


public class Bebida {
    
    private int id;
    private String Bebida;
    private String Tipo;
    private String Fornecedor;
    private String Volume;
    private double Preco;
    private int Quantidade;

    public Bebida() {
        
    }

    public Bebida(String skol, String cerveja, String dipai, String lt, double d, int i) {
        this.Bebida = skol;
        this.Tipo = cerveja;
        this.Fornecedor = dipai;
        this.Volume = lt;
        this.Preco = d;
        this.Quantidade = i;
    }

    public Bebida(int id, String skol, String cerveja, String dipai, String lt, double d, int i) {
        this.id = id;
        this.Bebida = skol;
        this.Tipo = cerveja;
        this.Fornecedor = dipai;
        this.Volume = lt;
        this.Preco = d;
        this.Quantidade = i;
    }
    
    public int getID(){
        return id;
    }
    
    public void setID(int id){
        this.id = id;
    }

    public String getBebida() {
        return Bebida;
    }

    public void setBebida(String Bebida) {
        this.Bebida = Bebida;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    
    public String getFornecedor() {
        return Fornecedor;
    }

    public void setFornecedor(String Fornecedor) {
        this.Fornecedor = Fornecedor;
    }

    public String getVolume() {
        return Volume;
    }

    public void setVolume(String Volume) {
        this.Volume = Volume;
    }

    public double getPreco() {
        return Preco;
    }

    public void setPreco(float Preco) {
        this.Preco = Preco;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }
    
    
    
    
    
}
