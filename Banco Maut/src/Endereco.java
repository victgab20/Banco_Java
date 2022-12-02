public class Endereco {
    private String nomeRua;
    private String bairro;
    private int numeroCasa;
    private String uf;
    private String cidade;

    public Endereco (String nomeRua, String bairro, int numeroCasa, String uf, String cidade) {
        setNomeRua(nomeRua);
        setBairro(bairro);
        setNumeroCasa(numeroCasa);
        setUf(uf);
        setCidade(cidade);
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNomeRua() {
        return nomeRua;
    }
    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public int getNumeroCasa() {
        return numeroCasa;
    }
    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    

}
