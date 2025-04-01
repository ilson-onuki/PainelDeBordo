import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // Criando motoristas
        Motorista motoristaA = new Motorista("MotoristaA");
        Motorista motoristaB = new Motorista("MotoristaB");

        // Criando carros
        Carro carroA = new Carro("Sedan", "Vermelho", "MotoristaA", "Casa", false);
        Carro carroB = new Carro("SUV", "Azul", "MotoristaB", "Trabalho", false);
        Carro carroTrabalhoA = new Carro("Furgão", "Cinza", "MotoristaA", "Trabalho", true);
        Carro carroTrabalhoB = new Carro("Pickup", "Preto", "MotoristaB", "Mecânico", true);

        while (true) {
            System.out.println("\n=== Menu Inicial ===");
            System.out.println("1 - Ver localização dos carros");
            System.out.println("2 - Atualizar localização de um carro");
            System.out.println("3 - Continuar para identificação do motorista");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            int escolha = entrada.nextInt();
            entrada.nextLine(); // Limpar buffer

            if (escolha == 1) {
                exibirLocalizacaoDosCarros(carroA, carroB, carroTrabalhoA, carroTrabalhoB);
            } else if (escolha == 2) {
                atualizarLocalizacaoCarro(entrada, carroA, carroB, carroTrabalhoA, carroTrabalhoB);
            } else if (escolha == 3) {
                break; // Continua para a identificação do motorista
            } else if (escolha == 0) {
                System.out.println("Saindo...");
                entrada.close();
                return;
            } else {
                System.out.println("Opção inválida.");
            }
        }

        while (true) {
            System.out.println("\n=== Identificação do Motorista ===");
            System.out.println("1 - MotoristaA");
            System.out.println("2 - MotoristaB");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            int escolhaMotorista = entrada.nextInt();
            entrada.nextLine(); // Limpar buffer

            Motorista motoristaSelecionado = null;
            if (escolhaMotorista == 1) {
                motoristaSelecionado = motoristaA;
            } else if (escolhaMotorista == 2) {
                motoristaSelecionado = motoristaB;
            } else if (escolhaMotorista == 0) {
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("Opção inválida.");
                continue;
            }

            System.out.println("\n=== Identificação da Localização do Motorista ===");
            System.out.println("1 - Casa");
            System.out.println("2 - Trabalho");
            System.out.println("3 - Mecânico");
            System.out.print("Escolha: ");
            int escolhaLocalizacao = entrada.nextInt();
            entrada.nextLine(); // Limpar buffer

            String localizacao = escolherLocalizacao(escolhaLocalizacao);
            motoristaSelecionado.setLocalizacao(localizacao);

            System.out.println("\n=== Escolha o Carro ===");
            System.out.println("1 - Carro do MotoristaA");
            System.out.println("2 - Carro do MotoristaB");
            System.out.println("3 - Carro de Trabalho do MotoristaA");
            System.out.println("4 - Carro de Trabalho do MotoristaB");
            System.out.print("Escolha: ");
            int escolhaCarro = entrada.nextInt();
            entrada.nextLine(); // Limpar buffer

            Carro carroSelecionado = escolherCarro(escolhaCarro, carroA, carroB, carroTrabalhoA, carroTrabalhoB);

            if (carroSelecionado == null) {
                System.out.println("Opção inválida.");
                continue;
            }

            if (!motoristaSelecionado.getLocalizacao().equals(carroSelecionado.getLocalizacao())) {
                System.out.println("Atenção - O carro não está na mesma localização que o motorista!");
                continue;
            }

            if (!motoristaSelecionado.podeDirigir(carroSelecionado)) {
                System.out.println("Atenção - Você não tem permissão para dirigir este carro!");
                continue;
            }

            System.out.println("\nPermissão concedida! Acesse o menu do carro.");
            menuCarro(entrada, carroSelecionado);
        }

        entrada.close();
    }

    private static void exibirLocalizacaoDosCarros(Carro... carros) {
        System.out.println("\n=== Localização Atual dos Carros ===");
        for (Carro carro : carros) {
            System.out.println(carro.getModelo() + " (" + carro.getProprietario() + "): " + carro.getLocalizacao());
        }
    }

    private static void atualizarLocalizacaoCarro(Scanner entrada, Carro... carros) {
        System.out.println("\n=== Escolha um carro para atualizar localização ===");
        for (int i = 0; i < carros.length; i++) {
            System.out.println((i + 1) + " - " + carros[i].getModelo() + " (" + carros[i].getProprietario() + ")");
        }
        System.out.print("Escolha: ");
        int escolhaCarro = entrada.nextInt();
        entrada.nextLine(); // Limpar buffer

        if (escolhaCarro < 1 || escolhaCarro > carros.length) {
            System.out.println("Opção inválida.");
            return;
        }

        Carro carroSelecionado = carros[escolhaCarro - 1];

        System.out.println("\n=== Escolha a nova localização ===");
        System.out.println("1 - Casa");
        System.out.println("2 - Trabalho");
        System.out.println("3 - Mecânico");
        System.out.print("Escolha: ");
        int escolhaLocalizacao = entrada.nextInt();
        entrada.nextLine(); // Limpar buffer

        String novaLocalizacao = escolherLocalizacao(escolhaLocalizacao);
        carroSelecionado.setLocalizacao(novaLocalizacao);

        System.out.println("Carro - " + carroSelecionado.getModelo() + " agora está em " + novaLocalizacao + "!");
    }

    private static String escolherLocalizacao(int escolha) {
        switch (escolha) {
            case 1:
                return "Casa";
            case 2:
                return "Trabalho";
            case 3:
                return "Mecânico";
            default:
                return "Casa";
        }
    }

    private static Carro escolherCarro(int escolha, Carro carroA, Carro carroB, Carro carroTrabalhoA, Carro carroTrabalhoB) {
        switch (escolha) {
            case 1:
                return carroA;
            case 2:
                return carroB;
            case 3:
                return carroTrabalhoA;
            case 4:
                return carroTrabalhoB;
            default:
                return null;
        }
    }

    private static void menuCarro(Scanner entrada, Carro carroSelecionado) {
        int opcao;
        do {
            System.out.println("\n=== Menu do Carro ===");
            System.out.println("1 - Abrir porta");
            System.out.println("2 - Fechar porta");
            System.out.println("3 - Ligar carro");
            System.out.println("4 - Desligar carro");
            System.out.println("5 - Ver status");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    carroSelecionado.abrir();
                    break;
                case 2:
                    carroSelecionado.fechar();
                    break;
                case 3:
                    carroSelecionado.ligar();
                    break;
                case 4:
                    carroSelecionado.desligar();
                    break;
                case 5:
                    carroSelecionado.status();
                    break;
                case 0:
                    System.out.println("Saindo do carro...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}
