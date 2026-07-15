import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pet[] pets = new Pet[100];
        int qntPets = 0; // quantidade atual de Pets cadastrado
        int option = 0;

        while (option != 6) { // Laço de repetição para exibição do MENU
            System.out.println("1. Cadastrar um novo pet\n" +
                    "2. Alterar os dados do pet cadastrado\n" +
                    "3. Deletar um pet cadastrado\n" +
                    "4. Listar todos os pets cadastrados\n" +
                    "5. Listar pets por algum critério (idade, nome, raça)\n" +
                    "6. Sair");

            // Proibe a entrada de 0 ou Negativos & a entrada de letras ou caracteres especiais
            String wrongInput = "Digite uma opção entre 1 e 6";
            if(scanner.hasNextInt()){
                option = scanner.nextInt();
                scanner.nextLine();
            } else{
                System.out.println(wrongInput);
                scanner.next();
            }

            if(option <= 0 || option > 6){
                System.out.println(wrongInput);
                continue;
            }

            switch (option) {
                case 1:
                    System.out.println("1. Cadastrar um novo pet\n");
                    Pet pet = new Pet(); // Criando um Objeto Pet "temporario" para preencher os dados
                    try{
                        pet.cadastrarPet();
                        pets[qntPets] = pet; // Alocando o Objeto Pet no vetor de Pets
                        qntPets++;

                        FormOperations.writeForm(pet); // Metodo para gerar relatório com dados do Pet em um File.txt

                        System.out.println("Pet criado com SUCESSO!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
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
                    break;
            }
        }
    }
}