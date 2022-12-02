import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class App {
    //static List<Conta> contas = new ArrayList<>();
    static List<Cliente> clientes = new ArrayList<>();
    static List<Integer> numerosId = new ArrayList<>();
    static List<ContaCorrente> contasC = new ArrayList<>();
    static List<ContaPoupanca> contasP = new ArrayList<>();
    static EnviarEmail email = new EnviarEmail();
    static EnviarSmS sms = new EnviarSmS();


    public static void main(String[] args) throws Exception {
        List<Integer> opcoesIniciais = new ArrayList<>();
        opcoesIniciais.add(1);
        opcoesIniciais.add(2);
        opcoesIniciais.add(3);

        int opcaoSelecionada = 1;
        while (opcoesIniciais.get(opcaoSelecionada) != 3) {
            opcaoSelecionada = exibirMenuInicial(opcoesIniciais);

            if (opcoesIniciais.get(opcaoSelecionada) == 1){ //cadastrar um cliente
                cadastrarCliente();
            }
            else if (opcoesIniciais.get(opcaoSelecionada) == 2) { //realizar login do cliente
                //telaLogin();
                String idClienteStr = JOptionPane.showInputDialog("Código de identificação(ID): ");
                String senha = JOptionPane.showInputDialog("Senha: ");
                int idCliente = Integer.parseInt(idClienteStr);

                for (Cliente cliente : clientes) { //percorrer a lista de clientes para encontrar o cliente com o ID e senha informado
                    if(idCliente == cliente.getIdCliente() && senha.equals(cliente.getSenha())) {
                        List<Integer> opcoes = new ArrayList<>();
                        opcoes.add(1);
                        opcoes.add(2);
                        opcoes.add(3);

                        Object[] opcoesObject = opcoes.toArray();
                        int opcaoEscolhida = 1;
                        while (opcoes.get(opcaoEscolhida) != 3) {
                            opcaoEscolhida = JOptionPane.showOptionDialog(null,
                            "1 - Criar conta\n2 - Acessar conta\n3 - Fechar", cliente.getNome(), JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE, null, opcoesObject, null);

                            if (opcoes.get(opcaoEscolhida) == 1) { //criar uma conta
                                criarConta(cliente);
                                
                            }
                            else if (opcoes.get(opcaoEscolhida) == 2) { //acessar a conta
                                List<Integer> opcoesTipo = new ArrayList<>();
                                opcoesTipo.add(1);
                                opcoesTipo.add(2);
                                //escolher o tipo de conta (corrente ou poupança)
                                Object[] opcoesArray = opcoesTipo.toArray();
                                int tipoConta = JOptionPane.showOptionDialog(null, 
                                "1 - Conta Corrente\n2 - Conta Poupança", "Tipo da conta", JOptionPane.CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoesArray, opcoesArray);                
                                
                                String numConta = JOptionPane.showInputDialog("Número da conta: ");
                                String agConta = JOptionPane.showInputDialog("Número da agência: ");
                                
                                if (opcoesTipo.get(tipoConta) == 1) {
                                    for (ContaCorrente contaC:contasC) {
                                        if(Integer.parseInt(numConta) == contaC.getNumeroConta() && Integer.parseInt(agConta) == contaC.getAgenciaConta() && contaC.getIdCliente() == cliente.getIdCliente()) {
                                            List<Integer> menuContaC = new ArrayList<>();
                                            menuContaC.add(1);
                                            menuContaC.add(2);
                                            menuContaC.add(3);
                                            menuContaC.add(4);
                                            menuContaC.add(5);
                                            Object[] opcoesMenu = menuContaC.toArray();
                                            int opcaoMenu = 1;
                                            while (menuContaC.get(opcaoMenu) != 5) {
                                                opcaoMenu = JOptionPane.showOptionDialog(null, 
                                                "1 - ver saldo\n2 - Depositar\n3 - Sacar\n4 - Realizar transferência\n5 - Voltar", "CONTA CORRENTE",
                                                JOptionPane.CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoesMenu, opcoesMenu);     
                                                        
                                                if (menuContaC.get(opcaoMenu) == 1) {
                                                    JOptionPane.showMessageDialog(null, "Saldo: " + contaC.getSaldo() + "\nCheque especial: " + contaC.getChequeEspecial(), "titulo", 1);
                                                }
                                                else if(menuContaC.get(opcaoMenu) == 2) {
                                                    depositarCC(contaC);
                                                }
                                                else if(menuContaC.get(opcaoMenu) == 3) {
                                                    sacarCC(contaC);
                                                }
                                                else if(menuContaC.get(opcaoMenu) == 4) {
                                                    transferirCC(contaC);
                                                }
                                            }
                                        }
                                    }
                                }
                                else if (opcoesTipo.get(tipoConta) == 2) {
                                    for (ContaPoupanca contaP:contasP) {
                                        if(Integer.parseInt(numConta) == contaP.getNumeroConta() && Integer.parseInt(agConta) == contaP.getAgenciaConta() && contaP.getIdCliente() == cliente.getIdCliente()) {
                                            List<Integer> menuContaP = new ArrayList<>();
                                            menuContaP.add(1);
                                            menuContaP.add(2);
                                            menuContaP.add(3);
                                            menuContaP.add(4);
                                            menuContaP.add(5);
                                            Object[] opcoesMenu = menuContaP.toArray();
                                            int opcaoMenu = 1;
                                            while (menuContaP.get(opcaoMenu) != 5) {
                                                opcaoMenu = JOptionPane.showOptionDialog(null, 
                                                "1 - ver saldo\n2 - Depositar\n3 - Sacar\n4 - Realizar transferência\n5 - Voltar", "CONTA POUPANÇA",
                                                JOptionPane.CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoesMenu, opcoesMenu);     
                                                        
                                                if (menuContaP.get(opcaoMenu) == 1) {
                                                    JOptionPane.showMessageDialog(null, "Saldo: " + contaP.getSaldo(), "titulo", 1);
                                                }
                                                else if(menuContaP.get(opcaoMenu) == 2) {
                                                    depositarCP(contaP);
                                                }
                                                else if(menuContaP.get(opcaoMenu) == 3) {
                                                    sacarCP(contaP);
                                                } else if(menuContaP.get(opcaoMenu) == 4) {
                                                    transferirCP(contaP);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private static void selecioarNotifi(){
        String tipoNoti = JOptionPane.showInputDialog("Qual o tipo de notificaçã: ");

          
        }
    
    private static int exibirMenuInicial(List<Integer> opcoesIniciais) {
        Object[] opcoesArray = opcoesIniciais.toArray();
        int opcaoSelecionada = JOptionPane.showOptionDialog(null, 
        "1 - Cadastrar Cliente\n2 - Login\n3 - Encerrar", "INÍCIO", JOptionPane.CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoesArray, opcoesArray);
        return opcaoSelecionada;
    }



    private static void cadastrarCliente() {
        String nomeCliente = JOptionPane.showInputDialog("Nome: ");
        String cpfCliente = JOptionPane.showInputDialog("CPF: ");
        String email = JOptionPane.showInputDialog("Digite seu Email: ");
        String telefone = JOptionPane.showInputDialog("Digite seu telefone");
        selecioarNotifi();


        int idCliente;
        do {
            idCliente = 10000 + (int) (Math.random() * 89999); //gerar um numero aleatorio para o id do cliente                
        } while (numerosId.contains(idCliente) == true); //verifica se o id gerado já foi gerado e adicionado no array alguma outra vez
        numerosId.add(idCliente); //adiciona o id no array para verificação

        String estado = JOptionPane.showInputDialog("Estado que você mora: ");
        String cidade = JOptionPane.showInputDialog("Nome da cidade: ");
        String bairro = JOptionPane.showInputDialog("Nome do bairro: ");
        String rua = JOptionPane.showInputDialog("Nome da rua: ");
        String numeroCasa = JOptionPane.showInputDialog("Número da casa: ");

        Endereco endereco = new Endereco(rua, bairro, Integer.parseInt(numeroCasa), estado, cidade);

        String senha = JOptionPane.showInputDialog("Cadastre uma senha: ");

        Cliente novoCliente = new Cliente(nomeCliente, Long.parseLong(cpfCliente), idCliente, null, endereco, senha, email,  telefone);
        clientes.add(novoCliente);

        JOptionPane.showMessageDialog(null, "Nome do cliente: " + nomeCliente + "\nCPF: " + cpfCliente + 
        "\nId: " + idCliente + "\nGuarde o ID para poder fazer login", "CADASTRO REALIZADO", JOptionPane.PLAIN_MESSAGE);
    }
    private static void criarConta(Cliente cliente) {
        List<Integer> opcoesTipo = new ArrayList<>();
        opcoesTipo.add(1);
        opcoesTipo.add(2);
        //escolher o tipo de conta (corrente ou poupança)
        Object[] opcoesArray = opcoesTipo.toArray();
        int tipoConta = JOptionPane.showOptionDialog(null, 
        "1 - Conta Corrente\n2 - Conta Poupança", "Selecione", JOptionPane.CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoesArray, opcoesArray);                
        if (opcoesTipo.get(tipoConta) == 1) {
            //criarContaCorrente();
            int numeroContaC = 10000 + (int) (Math.random() * 89999);
            int agenciaContaC = 10 + (int) (Math.random() * 89);

            ContaCorrente novaContaCorrente = new ContaCorrente(2000, cliente.getIdCliente(), numeroContaC, agenciaContaC, 0, 0);
            JOptionPane.showMessageDialog(null, "Cliente: " + cliente.getNome() + "\nID: " + cliente.getIdCliente() + "\n\nNúmero da conta: " + numeroContaC + "\nNúmero da agência: " + agenciaContaC,
            "CONTA CORRENTE CRIADA", JOptionPane.PLAIN_MESSAGE);
            contasC.add(novaContaCorrente);
        }
        else if (opcoesTipo.get(tipoConta) == 2) {
            //criarContaPoupanca();
            int numeroContaP = 10000 + (int) (Math.random() * 89999);
            int agenciaContaP = 10 + (int) (Math.random() * 89);

            ContaPoupanca novaContaPoupanca = new ContaPoupanca(cliente.getIdCliente(), numeroContaP, agenciaContaP, 0, 0.10f);
            JOptionPane.showMessageDialog(null, "Cliente: " + cliente.getNome() + "\nID: " + cliente.getIdCliente() + "\n\nNúmero da conta: " + numeroContaP + "\nNúmero da agência: " + agenciaContaP,
            "CONTA POUPANÇA CRIADA", JOptionPane.PLAIN_MESSAGE);
            contasP.add(novaContaPoupanca);
        }
    }

    private static void depositarCC(ContaCorrente contaC) {//depósito em conta corrente
        String valorDep = JOptionPane.showInputDialog(null, "Valor do Deposito", "Depositar", 1);
        float valorDeposito = Float.parseFloat(valorDep);
        contaC.depositar(valorDeposito);
    }

    private static void depositarCP(ContaPoupanca contaP) {//depósito em conta poupança
        String valorDep = JOptionPane.showInputDialog(null, "Valor do Deposito", "Depositar", 1);
        float valorDeposito = Float.parseFloat(valorDep);
        contaP.depositar(valorDeposito);
    }


    private static void sacarCC(ContaCorrente contaC) {//saque de conta corrente
        
        String valorSacar = JOptionPane.showInputDialog(null, "Valor do Saque", "Sacar", 1);
        float valorSaque = Float.parseFloat(valorSacar);
        contaC.sacar(valorSaque);
    }
    private static void sacarCP(ContaPoupanca contaP) {//saque de conta poupança
        String valorSacar = JOptionPane.showInputDialog(null, "Valor do Saque", "Sacar", 1);
        float valorSaque = Float.parseFloat(valorSacar);
        contaP.sacar(valorSaque);
    }

    private static void transferirCC(ContaCorrente contaC) { //transferir a partir da conta corrente
        List<Integer> opcoesTipo = new ArrayList<>();
        opcoesTipo.add(1);
        opcoesTipo.add(2);
        //escolher o tipo de conta (corrente ou poupança)
        Object[] opcoesArray = opcoesTipo.toArray();
        
        int tipoContaDestino = JOptionPane.showOptionDialog(null, 
        "Tipo da conta do destinatário:\n1 - Conta Corrente\n2 - Conta Poupança", "Tipo da conta", JOptionPane.CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoesArray, opcoesArray);
        String numContaDestino = JOptionPane.showInputDialog("Número da conta: ");
        String agContaDestino = JOptionPane.showInputDialog("Número da agência: ");
        String valorTr = JOptionPane.showInputDialog("Valor que deseja transferir: ");
        float valorTransferir = Float.parseFloat(valorTr);
        if (opcoesTipo.get(tipoContaDestino) == 1) {
            for (ContaCorrente contaDestino : contasC) {
                if(contaDestino.getNumeroConta() == Integer.parseInt(numContaDestino) && contaDestino.getAgenciaConta() == Integer.parseInt(agContaDestino)) {
                    contaC.transferirParaContaCorrente(valorTransferir, contaDestino);
                }
            }
        } else if (opcoesTipo.get(tipoContaDestino) == 2) {
            for (ContaPoupanca contaDestino : contasP) {
                if(contaDestino.getNumeroConta() == Integer.parseInt(numContaDestino) && contaDestino.getAgenciaConta() == Integer.parseInt(agContaDestino)) {
                    contaC.transferirParaContaPoupanca(valorTransferir, contaDestino);
                }    
            }
        }
    }
    private static void transferirCP(ContaPoupanca contaP) {//transferência a partir da conta poupança
        List<Integer> opcoesTipo = new ArrayList<>();
        opcoesTipo.add(1);
        opcoesTipo.add(2);
        //escolher o tipo de conta (corrente ou poupança)
        Object[] opcoesArray = opcoesTipo.toArray();

        int tipoContaDestino = JOptionPane.showOptionDialog(null, 
        "Tipo da conta do destinatário:\n1 - Conta Corrente\n2 - Conta Poupança", "Tipo da conta", JOptionPane.CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoesArray, opcoesArray);
        String numContaDestino = JOptionPane.showInputDialog("Número da conta: ");
        String agContaDestino = JOptionPane.showInputDialog("Número da agência: ");
        String valorTr = JOptionPane.showInputDialog("Valor que deseja transferir: ");
        float valorTransferir = Float.parseFloat(valorTr);

        if (opcoesTipo.get(tipoContaDestino) == 1) {
            for (ContaCorrente contaDestino : contasC) {
                if(contaDestino.getNumeroConta() == Integer.parseInt(numContaDestino) && contaDestino.getAgenciaConta() == Integer.parseInt(agContaDestino)) {
                    contaP.transferirParaContaCorrente(valorTransferir, contaDestino);
                }
            }
        }
        else if (opcoesTipo.get(tipoContaDestino) == 2) {
            for (ContaPoupanca contaDestino : contasP) {
                if(contaDestino.getNumeroConta() == Integer.parseInt(numContaDestino) && contaDestino.getAgenciaConta() == Integer.parseInt(agContaDestino)) {
                    contaP.transferirParaContaPoupanca(valorTransferir, contaDestino);
                }
            }
        }
    }
}