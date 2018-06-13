package Getters_e_Setters;

public class Cadastro_Alimento {
    private String Tipo;
    private String Fornecedor;
    private float QuantTKG;
    private float Preco;
    private float Preco_Total;

    public float getPreco() {
        return Preco;
    }

    public void setPreco(float Preco) {
        this.Preco = Preco;
    }

    public float getPreco_Total() {
        return Preco_Total;
    }

    public void setPreco_Total(float Preco_Total) {
        this.Preco_Total = Preco_Total;
    }
    private float uniPorcaoKG;

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

    public float getQuantT() {
        return QuantTKG;
    }

    public void setQuantT(float QuantT) {
        this.QuantTKG = QuantT;
    }

    public float getUniPorcao() {
        return uniPorcaoKG;
    }

    public void setUniPorcao(float uniPorcao) {
        this.uniPorcaoKG = uniPorcao;
    }
    

    public Cadastro_Alimento() {
        
        
    }
    
}
