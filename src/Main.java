import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        while (option != 6) { // Laço de repetição para exibição do MENU
            System.out.println("1. Cadastrar um novo pet\n" +
                    "2. Alterar os dados do pet cadastrado\n" +
                    "3. Deletar um pet cadastrado\n" +
                    "4. Listar todos os pets cadastrados\n" +
                    "5. Listar pets por algum critério (idade, nome, raça)\n" +
                    "6. Sair");


            switch (option) {
                case 1:
                    System.out.println("1. Cadastrar um novo pet\n");
                    break;

                case 2:
                    System.out.println("2. Alterar os dados do pet cadastrado\n");
                    break;

                case 3:
                    System.out.println("3. Deletar um pet cadastrado\n");
                    break;

                case 4:
                    System.out.println("4. Listar todos os pets cadastrados\n");
                    break;

                case 5:
                    System.out.println("5. Listar pets por algum critério (idade, nome, raça)\n");
                    break;

                case 6:
                    System.out.println("Saindo...\n");
                    break;

                default:
                    // Proibe a entrada de 0 ou Negativos & a entrada de letras ou caracteres especiais
                    String wrongInput = "Digite uma opção entre 1 e 6";
                    if(scanner.hasNextInt()){
                        option = scanner.nextInt();
                    } else{
                        System.out.println(wrongInput);
                        scanner.next();
                    }

                    if(option <= 0 || option > 6){
                        System.out.println(wrongInput);
                    }
                    break;
            }
        }
    }
}