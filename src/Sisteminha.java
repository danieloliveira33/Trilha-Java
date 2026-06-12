import java.util.Scanner;

public class Sisteminha {

    static String[] produtosCadastrados = new String[10];
    static String[] clientesCadastrados = new String[10];
    static Double[] precosProdutos = new Double[10];
    static Integer[] quantidadesProdutos = new Integer[10];
    static Double dinheiroEmCaixa = 0.0;
    static Scanner scanner = new Scanner(System.in);

    public static void main (String[] args){

        String acessoUsuario;
        int acessoSenha;
        boolean login = false;

        produtosCadastrados[0] = "feijao";
        produtosCadastrados[1] = "arroz";
        produtosCadastrados[2] = "sabonete";
        produtosCadastrados[3] = "shampoo";
        produtosCadastrados[4] = "condicionador";

        precosProdutos[0] = 7.99;
        precosProdutos[1] = 6.99;
        precosProdutos[2] = 2.99;
        precosProdutos[3] = 14.99;
        precosProdutos[4] = 14.99;

        quantidadesProdutos[0] = 50;
        quantidadesProdutos[1] = 50;
        quantidadesProdutos[2] = 50;
        quantidadesProdutos[3] = 50;
        quantidadesProdutos[4] = 50;

        //============================================================
        // PROCESSO DE LOGIN
        do {
            System.out.println("Seja Bem vindo!");
            System.out.print("Digite seu login: ");
            acessoUsuario = scanner.nextLine();
            System.out.print("Digite sua senha: ");
            acessoSenha = scanner.nextInt();
            scanner.nextLine();

            if ((acessoUsuario.equals("admin")) && acessoSenha == 123) {
                login = true;
            } else {
                System.out.println("Login ou Senha incorretos");
            }

        } while (!login);

        System.out.println("\nAcesso Permitido!");
        //============================================================
        clearConsole();
        boolean sair = false;
        int escolha;

        do {
            System.out.println("\n=================| MENU |=================");
            System.out.println("1 - Mostrar Produtos");
            System.out.println("2 - Mostrar Clientes ");
            System.out.println("3 - Cadastrar Cliente");
            System.out.println("4 - Cadastrar Produto");
            System.out.println("5 - Vender Produtos");
            System.out.println("6 - Mostrar Dinheiro em Caixa");
            System.out.println("7 - Alterar Estoque");
            System.out.println("8 - Limpar Tela");
            System.out.println("0 - Sair");
            System.out.println("==========================================");


            System.out.println("\nEscolha uma opção: ");
            escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    mostrarProdutos();

                    break;

                case 2:
                    mostrarClientes();

                    break;

                case 3:
                    cadastrarCliente();

                    break;

                case 4:
                    cadastrarProduto();

                    break;

                case 5:
                    venderProdutos();

                    break;

                case 6:
                    mostrarCaixa();

                    break;

                case 7:
                    alterarEstoque();

                    break;

                case 8:
                    clearConsole();

                    break;

                case 0:
                    sair = true;

                    break;

                default:
                    System.out.println("Opcao Invalida!");
            }
        } while(!sair);
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void mostrarProdutos() {
        int indicieConceitual;
        for (int i = 0; i < produtosCadastrados.length; i++) {
            indicieConceitual = i + 1;

            if (produtosCadastrados[i] != null) {
                System.out.printf("\nProduto [%d]: %s -- Preco: %.2f -- Quantidade: %d", indicieConceitual, produtosCadastrados[i], precosProdutos[i], quantidadesProdutos[i]);
            }
        }
        System.out.println();
    }

    public static void mostrarClientes() {
        int indicieConceitual;
        System.out.println();
        for (int i = 0; i < clientesCadastrados.length; i++) {
            indicieConceitual = i + 1;
            if (clientesCadastrados[i] != null)
                System.out.printf("Cliente [%d]: %s\n", indicieConceitual, clientesCadastrados[i]);
            else
                System.out.printf("Cliente [%d]: Nao cadastrado\n", indicieConceitual);
        }
    }

    public static void cadastrarCliente () {
        int contNull = 0;
        for (int i = 0; i < clientesCadastrados.length; i++) {
            if (clientesCadastrados[i] == null) {
                contNull++;
            }

        }
        if (contNull < (clientesCadastrados.length / 3)) {
            clientesCadastrados = dobrarVetor(clientesCadastrados.length, clientesCadastrados);
        }

        System.out.print("\nDigite o nome do Cliente: ");
        for (int i = 0; i < clientesCadastrados.length; i++) {
            if (clientesCadastrados[i] == null) {
                clientesCadastrados[i] = scanner.nextLine();
                break;
            }
        }
    }

    public static  void cadastrarProduto () {
        int contNull = 0;

        for (int i = 0; i < produtosCadastrados.length; i++) {
            if (produtosCadastrados[i] == null)
               contNull++;
        }

        if (contNull < (produtosCadastrados.length / 3.0)) {
            produtosCadastrados = dobrarVetor(produtosCadastrados.length, produtosCadastrados);
            precosProdutos = dobrarVetor(precosProdutos.length, precosProdutos);
            quantidadesProdutos = dobrarVetor(quantidadesProdutos.length, quantidadesProdutos);
        }
        System.out.print("\nDigite o nome do Produto: ");
        int indicieMaster = 0;

        boolean produtoJaExiste = false;
        String verificaProduto;
        int indiceVerificaProduto = 0;
        verificaProduto = scanner.nextLine();

        for (int i = 0; i < produtosCadastrados.length; i++) {
            if (verificaProduto.equals(produtosCadastrados[i])) {
                produtoJaExiste = true;
                indiceVerificaProduto = i;
            }
        }

        for (int i = 0; i < produtosCadastrados.length; i++) {
            if (produtosCadastrados[i] == null) {
                indicieMaster = i;
                break;
            }
        }

        if (!produtoJaExiste) {
            produtosCadastrados[indicieMaster] = verificaProduto;

            System.out.print("\nAVISO: A JVM herda o idioma do SO, ou seja, o separador decimal deve ser:");
            System.out.print("\nPortugues: , -- Ingles: .\n\n");
            System.out.print("\nDigite o preço do Produto: ");
            precosProdutos[indicieMaster] = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("\nDigite a quantidade do Produto em estoque: ");
            quantidadesProdutos[indicieMaster] = scanner.nextInt();
            scanner.nextLine();

        } else {
            System.out.println ("Produto ja esta cadastrado, indicie: " + (indiceVerificaProduto + 1));
        }

    }

    public static void venderProdutos () {
        mostrarProdutos();

        int indicie;
        boolean produtoExiste = false;
        boolean verificaIndicie = false;

        do {
            System.out.print("\nQual produto deseja vender? (Digite o índicie): ");
            do {
                verificaIndicie = false;
                indicie = scanner.nextInt();
                scanner.nextLine();
                if (indicie < 0 || indicie > produtosCadastrados.length)
                    System.out.print("INVALIDO, TENTE NOVAMENTE: ");
                else
                    verificaIndicie = true;
            }while(!verificaIndicie);

            indicie--;

                if (produtosCadastrados[indicie] != null) {
                    produtoExiste = true;
                }else {
                    System.out.println("Produto nao Encontrado, tente novamente");
                    mostrarProdutos();
                }

        }while (!produtoExiste);

        int qtdVendas = 0;
        boolean verificado = false;

        do {
            System.out.print("\nQuantas vendas? ");
            qtdVendas = scanner.nextInt();
            scanner.nextLine();
            if (quantidadesProdutos[indicie] < qtdVendas) {
                System.out.println("\nNao existem tantos produtos em estoque");
            } else {
                verificado = true;
            }
        }while(!verificado);

        boolean verificaCliente = false;
        int escolha = 0;

        System.out.print("\n------------------| RECIBO |------------------");
        System.out.printf("\nPRODUTO: %s", produtosCadastrados[indicie]);
        System.out.printf("\nPRECO UNITARIO: %.2f", precosProdutos[indicie]);
        System.out.printf("\nUNIDADES VENDIDAS: %d", qtdVendas);
        System.out.print("\n----------------------------------------------");

        System.out.print("\n\nCONFIRMA A VENDAA? (1 - Sim | 0 - Nao): ");
        escolha = scanner.nextInt();
        scanner.nextLine();

        int codCliente = 0;
        boolean cancelaVenda = false;
        boolean verificaCodCliente = false;

        if (escolha == 1) {

            do {

                System.out.print ("\nDIGITE O IDENTIFICADOR DO CLIENTE: ");
                codCliente = scanner.nextInt();
                scanner.nextLine();
                codCliente--;

                if (codCliente >= 0 && codCliente < clientesCadastrados.length && clientesCadastrados[codCliente] != null) {
                    verificaCliente = true;
                }
                else {
                    escolha = 0;
                    System.out.print ("\nCLIENTE NAO ENCONTRADO");
                    System.out.print ("\n0 - Cancelar | 1 - Tentar novamente | 2 - Cadastrar Cliente | ");
                    escolha = scanner.nextInt();
                    scanner.nextLine();
                    if (escolha == 2) {
                        cadastrarCliente();
                    } else if (escolha == 0) {
                        cancelaVenda = true;
                    }
                }
            }while(!verificaCliente && !cancelaVenda);

            if (verificaCliente) {
                System.out.print("\nVENDA CONFIRMADA");

                System.out.printf("\nVendendo %d unidades de %s", qtdVendas, produtosCadastrados[indicie]);
                quantidadesProdutos[indicie] -= qtdVendas;
                dinheiroEmCaixa += precosProdutos[indicie] * qtdVendas;
                System.out.println("\nDinheiro arrecadado: R$" + (precosProdutos[indicie] * qtdVendas));
            }

        } else {
            escolha = 0;
            System.out.print ("\nVENDA CANCELADA!");
            System.out.print ("\nDESEJA REALIZAR OUTRA VENDA? ");
            System.out.print ("\n1 - Sim | 0 - Nao");
            escolha = scanner.nextInt();
            scanner.nextLine();
            if (escolha == 1) {
                venderProdutos();
            }
        }

    }

    public static void mostrarCaixa() {
        System.out.println("\n============================================");
        System.out.printf("Dinheiro em caixa: %.2f", dinheiroEmCaixa);
        System.out.println("\n============================================\n");
    }

    public static void alterarEstoque() {
        mostrarProdutos();
        System.out.print("\nQual produto quer repor? ");
        int indice = 0;
        int qtdAdd = 0;
        boolean produtoValido = false;
        
        do {
            indice = scanner.nextInt();
            scanner.nextLine();
            indice--;

            if (indice >= produtosCadastrados.length || indice < 0)
                System.out.println("INVALIDO, TENTE NOVAMENTE");
            else if (produtosCadastrados[indice] != null)
                produtoValido = true;
            else
                System.out.println("INVALIDO, TENTE NOVAMENTE");

        }while (!produtoValido);

        boolean verificado = false;

        do {
            System.out.print("\nUnidades adicionadas: ");
            qtdAdd = scanner.nextInt();
            scanner.nextLine();

            if (qtdAdd < 0) {
                System.out.println("Dado inválido! Adicione um numero positivo!");
                System.out.println("Se quiser cancelar, digite 0");
            } else {
                quantidadesProdutos[indice] += qtdAdd;
                verificado = true;
            }

        } while (!verificado);

    }

    // DOBRAR QUALQUER VETOR PARA EVITAR COLAPSO
    public static <T> T[] dobrarVetor(int x, T[] y){
        T[] novoVetor = (T[]) new Object[x * 2];
        for (int i = 0; i < x; i++) {
            novoVetor[i] = y[i];
        }
        return novoVetor;
    }

}