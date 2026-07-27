import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pet[] pets = new Pet[100];

        // quantidade atual de Pets cadastrado
        int qntPets = FormOperations.loadPets(pets);

        int initMenu = 0;
        boolean running = true;

        while (running) {
            while (true) {
                System.out.println("1 - Iniciar o sistema para cadastro de PETS\n" +
                        "2 - Iniciar o sistema para alterar formulário");


                if (!scanner.hasNextInt()) {
                    System.out.println("Apenas números");
                    scanner.nextLine();
                    continue;
                }

                initMenu = scanner.nextInt();
                scanner.nextLine();

                if (initMenu == 1 || initMenu == 2) {
                    break;
                }
                System.out.println("Opção Inválida");
            }

            if (initMenu == 1) {
                int option = 0;
                while (option != 6) { // Laço de repetição para exibição do MENU
                    System.out.println("1. Cadastrar um novo pet\n" +
                            "2. Alterar os dados do pet cadastrado\n" +
                            "3. Listar todos os pets cadastrados\n" +
                            "4. Deletar um pet cadastrado\n" +
                            "5. Voltar para o menu inicial\n" +
                            "6. Sair");

                    // Proibe a entrada de 0 ou Negativos & a entrada de letras ou caracteres especiais
                    if (!scanner.hasNextInt()) {
                        System.out.println("Digite uma opção entre 1 e 6");
                        scanner.nextLine();
                        continue;
                    }

                    option = scanner.nextInt();
                    scanner.nextLine();

                    if (option < 1 || option > 6) {
                        System.out.println("Digite uma opção entre 1 e 6");
                        continue;
                    }

                    switch (option) {
                        case 1:
                            System.out.println("1. Cadastrar um novo pet:");
                            Pet pet = new Pet(); // Criando um Objeto Pet "temporario" para preencher os dados
                            try {
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
                            System.out.println("Voltar para o menu inicial:");
                            System.out.println();
                            option = 6;
                            break;

                        case 6:
                            System.out.println("Saindo...\n");
                            running = false;
                            break;

                        default:
                            System.out.println("Opção Inválida");
                            break;
                    }
                }
            } else {

                int option = 0;
                while (option != 5) {
                    System.out.println("1. Criar nova pergunta\n" +
                            "2. Alterar pergunta existente\n" +
                            "3. Excluir pergunta existente\n" +
                            "4. Voltar para o menu inicial\n" +
                            "5. Sair");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Apenas números");
                        scanner.nextLine();
                        continue;
                    }

                    option = scanner.nextInt();
                    scanner.nextLine();

                    if (option < 1 || option > 6) {
                        System.out.println("Digite uma opção entre 1 e 6");
                        continue;
                    }


                    switch (option) {
                        case 1:
                            System.out.println("Criar nova pergunta:");
                            FormOperations.createQuestion();
                            System.out.println();
                            break;

                        case 2:
                            System.out.println("Alterar pergunta existente:");
                            FormOperations.changeQuestion();
                            System.out.println();
                            break;

                        case 3:
                            System.out.println("Excluir pergunta existente:");
                            FormOperations.deleteQuestion();
                            System.out.println();
                            break;

                        case 4:
                            System.out.println("Voltar para o menu inicial:");
                            System.out.println();
                            option = 5;
                            break;

                        case 5:
                            System.out.println("Saindo...");
                            running = false;
                            break;
                    }
                }
            }

        }
    }
}