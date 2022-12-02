import javax.swing.JOptionPane;

public abstract class Conta {
    private int idCliente;
    private int numeroConta;
    private int agenciaConta;
    private float saldo;
    private String tipoNoti = "SMS";

    EnviarEmail email = new EnviarEmail();
    EnviarSmS sms = new EnviarSmS();


    public Conta(int idCliente, int numeroConta, int agenciaConta, float saldo) {
        this.idCliente = idCliente;
        this.numeroConta = numeroConta;
        this.agenciaConta = agenciaConta;
        this.saldo = saldo;
    
    }

    
    public String getTipoNoti() {
        return tipoNoti;
    }




    public void setTipoNoti(String tipoNoti) {
        this.tipoNoti = tipoNoti;
    }


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getNumeroConta() {
        return numeroConta;
    }
    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public int getAgenciaConta() {
        return agenciaConta;
    }
    public void setAgenciaConta(int agenciaConta) {
        this.agenciaConta = agenciaConta;
    }

    public float getSaldo() {
        return saldo;
    }
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void depositar(float valorDeposito){

    }
    public void sacar(float valorSaque){
        if (valorSaque <= getSaldo()) {
            setSaldo(getSaldo() - valorSaque);
        }
        else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente", "ERRO", 0);
        }
        if(getTipoNoti().equals("SMS")){
            email.enviarNotificacao("Sacado", valorSaque);

        }else if(getTipoNoti().equals("Email")){
            sms.enviarNotificacao("Sacado", valorSaque);
        }
    }

    public void transferirParaContaCorrente(float valorTransferir, ContaCorrente contaC) {

    }
    public void transferirParaContaPoupanca(float valorTransferir, ContaPoupanca contaDestino) {

    }


  
   
    
}