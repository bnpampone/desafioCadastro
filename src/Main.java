import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pet[] pets = new Pet[100];
        int qntPets = 0; // quantidade atual de Pets cadastrado
        int option = 0;

        while (option != 5) { // Laço de repetição para exibição do MENU
            System.out.println("1. Cadastrar um novo pet\n" +
                    "2. Alterar os dados do pet cadastrado\n" +
                    "3. Listar todos os pets cadastrados\n" +
                    "4. Deletar um pet cadastrado\n" +
                    "5. Sair");

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
                    System.out.println("1. Cadastrar um novo pet:");
                    Pet pet = new Pet(); // Criando um Objeto Pet "temporario" para preencher os dados
                    try{
                        pet.registerPet();
                        pets[qntPets] = pet; // Alocando o Objeto Pet no vetor de Pets
                        qntPets++;

                        FormOperations.writeForm(pet); // Metodo para gerar relatório com dados do Pet em um File.txt
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                    break;

                case 2:
                    System.out.println("2. Alterar os dados do pet cadastrado:");
                    FilterPets.editPet(pets, qntPets);
                    System.out.println();
                    break;

                case 3:
                    System.out.println("3. Listar todos os pets cadastrados:");
                    FilterPets.listAllPets(pets, qntPets);
                    System.out.println();
                    break;

                case 4:
                    System.out.println("4. Deletar um pet cadastrado:");
                    qntPets = FilterPets.deletePet(pets, qntPets);
                    System.out.println();
                    break;

                case 5:
                    System.out.println("Saindo...\n");
                    break;

                default:
                    System.out.println("Opção Inválida");
                    break;
            }
        }
    }
}