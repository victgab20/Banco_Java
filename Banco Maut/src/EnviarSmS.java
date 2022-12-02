public class EnviarSmS implements Notificacao {

    String opera;
    double opavalor;

    @Override
    public void enviarNotificacao(String opera,double opavalor) {
        System.out.println("o valor "+opavalor+"foi"+opera);
        
    }
    
}
