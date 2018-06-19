package Getters_e_Setters;

public class Vegetais {

    private int id;
    private String Tipo;
    private String Fornecedor;
    private double Quantidade;
    private double Preco;

    public Vegetais() {

    }

    public Vegetais(int id, String tipo, String forn, double quant, double preco) {
        this.id = id;
        this.Tipo = tipo;
        this.Fornecedor = forn;
        this.Quantidade = quant;
        this.Preco = preco;
    }
    
    public Vegetais(String tipo, String forn, double quant, double preco) {
        this.Tipo = tipo;
        this.Fornecedor = forn;
        this.Quantidade = quant;
        this.Preco = preco;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
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

    public double getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(double Quantidade) {
        this.Quantidade = Quantidade;
    }

    public double getPreco() {
        return Preco;
    }

    public void setPreco(double Precokg) {
        this.Preco = Precokg;
    }

}
