package Getters_e_Setters;

public class Cadastro_Alimento {

    private int id;
    private String Tipo;
    private String Fornecedor;
    private double QuantTKG;
    private double Preco;
    private double Preco_Total;
    private double uniPorcaoKG;

    public Cadastro_Alimento() {

    }

    public Cadastro_Alimento(String tipo, String forn, double quant, double preco, double precoT, double umpoc) {
        this.Tipo = tipo;
        this.Fornecedor = forn;
        this.QuantTKG = quant;
        this.Preco = preco;
        this.Preco_Total = precoT;
        this.uniPorcaoKG = umpoc;
    }

    public Cadastro_Alimento(int id, String tipo, String forn, double quant, double preco) {
        this.id = id;
        this.Tipo = tipo;
        this.Fornecedor = forn;
        this.QuantTKG = quant;
        this.Preco = preco;
    }

    public Cadastro_Alimento(String tipo, String forn, double quant, double preco) {
        this.Tipo = tipo;
        this.Fornecedor = forn;
        this.QuantTKG = quant;
        this.Preco = preco;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public double getPreco() {
        return Preco;
    }

    public void setPreco(float Preco) {
        this.Preco = Preco;
    }

    public double getPreco_Total() {
        return Preco_Total;
    }

    public void setPreco_Total(float Preco_Total) {
        this.Preco_Total = Preco_Total;
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

    public double getQuantT() {
        return QuantTKG;
    }

    public void setQuantT(float QuantT) {
        this.QuantTKG = QuantT;
    }

    public double getUniPorcao() {
        return uniPorcaoKG;
    }

    public void setUniPorcao(float uniPorcao) {
        this.uniPorcaoKG = uniPorcao;
    }
}
