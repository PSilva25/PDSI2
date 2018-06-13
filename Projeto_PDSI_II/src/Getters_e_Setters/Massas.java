package Getters_e_Setters;

public class Massas {

    private String Tipo;
    private String Fornecedor;
    private int Quantidade;
    
    public Massas(){
    
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

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public int getQuant_porcao() {
        return quant_porcao;
    }

    public void setQuant_porcao(int quant_porcao) {
        this.quant_porcao = quant_porcao;
    }
    private int quant_porcao;

 
}
