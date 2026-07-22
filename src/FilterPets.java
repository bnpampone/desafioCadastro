import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.Scanner;

public class FilterPets {
    public static String normalizeText(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase(); // Regex utilizada para remover Acentos de palavras e deixa-las unirfomizadas
    }

    public static void showIfPetWasFound(boolean foundPet) {
        if (!foundPet) {
            System.out.println("Nenhum pet foi encontrado");
        }
    }

    public static boolean verifyCriterion(Pet pet, int criterion, String value) {
        switch (criterion) {
            case 1:
                return normalizeText(pet.getNomePet()).contains(normalizeText(value));

            case 2:
                try {
                    Pet.SexoPet sex = Pet.SexoPet.valueOf(value.toUpperCase());
                    return pet.getSexo() == sex;
                } catch (IllegalArgumentException e) {
                    return false;
                }

            case 3:
                try {
                    double age = Double.parseDouble(value);
                    return pet.getIdade() == age;
                } catch (NumberFormatException e) {
                    return false;
                }

            case 4:
                try {
                    double weight = Double.parseDouble(value);
                    return pet.getPeso() == weight;
                } catch (NumberFormatException e) {
                    return false;
                }

            case 5:
                return normalizeText(pet.getRaca()).contains(normalizeText(value));

            case 6:
                String address = normalizeText(
                        pet.getRuaEndereco() + " " +
                                pet.getNumeroEndereco() + " " +
                                pet.getCidadeEndereco()
                );
                return address.contains(normalizeText(value));
            case 7:
                try {
                    String[] parts = value.split("/");
                    int month = Integer.parseInt(parts[0]);
                    int year = Integer.parseInt(parts[1]);
                    LocalDate date = pet.getRegisterDate();

                    return date.getMonthValue() == month && date.getYear() == year;
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    return false;
                }

            default:
                return false;
        }
    }

    public static String readCriterionValue(int criterion, Scanner scanner) {
        switch (criterion) {
            case 1:
                System.out.println("Digite o nome:");
                return scanner.nextLine();
            case 2:
                System.out.println("Digite o sexo:");
                return scanner.nextLine();
            case 3:
                System.out.println("Digite a idade:");
                return scanner.nextLine();
            case 4:
                System.out.println("Digite o peso:");
                return scanner.nextLine();
            case 5:
                System.out.println("Digite a raça:");
                return scanner.nextLine();
            case 6:
                System.out.println("Digite o endereço:");
                return scanner.nextLine();
            case 7:
                System.out.println("Digite a data (Mẽs):");
                String month = scanner.nextLine();
                System.out.println("Digite a data (Ano):");
                String year = scanner.nextLine();
                return month + "/" + year;
        }
        throw new IllegalArgumentException("Critério Inválido.");
    }


    public static Pet findPet(Pet[] pets, int qntPets) {
        Scanner scanner = new Scanner(System.in);
        int counterPets = 0;
        Pet[] listPets = new Pet[qntPets];

        if (qntPets == 0) { // Nenhum pet foi cadastrado
            System.out.println("Nenhum Pet foi cadastrado.");
            return null;
        }

        System.out.println("Tipo:\n" +
                "1- Cachorro\n" +
                "2- Gato");
        int typeOption = scanner.nextInt();
        scanner.nextLine();


        Pet.TipoPET tipoBusca; // Enum para os Tipo do Pet (Cachorro ou Gato)

        if (typeOption == 1) {
            tipoBusca = Pet.TipoPET.CACHORRO;
        } else if (typeOption == 2) {
            tipoBusca = Pet.TipoPET.GATO;
        } else {
            System.out.println("Tipo inválido");
            return null;
        }

        int firstOption;
        while (true) {
            // Primeiro Criterio
            System.out.println("Escolha o Primeiro Criterio:\n" +
                    "1- Nome ou sobrenome\n" +
                    "2- Sexo\n" +
                    "3- Idade\n" +
                    "4- Peso\n" +
                    "5- Raça\n" +
                    "6- Endereço\n" +
                    "7- Data (Mês/Ano)");


            if (!scanner.hasNextInt()) {
                System.out.println("Digite apenas números!");
                scanner.nextLine();
                continue;
            }

            firstOption = scanner.nextInt();
            scanner.nextLine();

            if (firstOption >= 1 && firstOption <= 7) {
                break;
            }
            System.out.println("Critério Inválido.");
        }

        int secondOption;
        while (true) {
            System.out.println("Deseja adicionar um segundo criterio ?\n" +
                    "0 - Não\n" +
                    "1 - Sim");

            if (!scanner.hasNextInt()) {
                System.out.println("Digite apenas números!");
                scanner.nextLine();
                continue;
            }

            secondOption = scanner.nextInt();
            scanner.nextLine();


            if (secondOption == 0 || secondOption == 1) {
                break;
            }
            System.out.println("Opção Inválida");

        }


        // Segundo Criterio

        int useSecond = 0;
        if (secondOption == 1) {
            while (true) {
                System.out.println("Escolha o Segundo Criterio:\n" +
                        "1- Nome ou sobrenome\n" +
                        "2- Sexo\n" +
                        "3- Idade\n" +
                        "4- Peso\n" +
                        "5- Raça\n" +
                        "6- Endereço\n" +
                        "7- Data (Mês/Ano)");

                if (!scanner.hasNextInt()) {
                    System.out.println("Digite apenas números!");
                    scanner.nextLine();
                    continue;
                }

                useSecond = scanner.nextInt();
                scanner.nextLine();

                if (useSecond >= 1 && useSecond <= 7) {
                    break;
                }
                break;
            }
        }


        String firstValue = readCriterionValue(firstOption, scanner); // Entrada do primeiro criterio
        String secondValue = "";

        if (secondOption == 1) {
            secondValue = readCriterionValue(useSecond, scanner); // Entrada do segundo criterio (opcional)
        }

        for (int i = 0; i < qntPets; i++) {

            Pet pet = pets[i];
            if (pet.getTipo() != tipoBusca) {
                continue; // Caso o Tipo (Cachorro/Gato) seja diferente do tipoBusca, ele nem compara com o proximo criterio
            }

            if (!verifyCriterion(pet, firstOption, firstValue)) {
                continue; // O pet não atende ao primeiro criterio informado
            }

            if (secondOption == 1 && !verifyCriterion(pet, useSecond, secondValue)) {
                continue; // O pet não atende ao segundo criterio informado
            }

            listPets[counterPets] = pet; // Armazenando no array o Pet da posição atual no vetor
            System.out.print((counterPets + 1) + ". ");
            pet.displayPet(firstOption, firstValue);
            counterPets++;
        }
        if (counterPets == 0) { // Caso nenhum dos Pets atenda ao critérios pesquisados
            showIfPetWasFound(false);
            return null;
        }

        int opcao;
        while (true) { // While para que o usuario não consiga sair sem terminar a seleção
            System.out.println("Digite o Pet que você deseja selecionar:");

            if (!scanner.hasNextInt()) { // Se o valor de entrada for diferente de um inteiro, ele é ignorado
                System.out.println("Digite apenas números!");
                scanner.nextLine();
                continue;
            }

            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao < 1 || opcao > counterPets) { // Se a opção for -1 ou maior que o numero de PETS, ela é ignorada
                System.out.println("Opção Inválida");
                continue;
            }

            break;
        }

        return listPets[opcao - 1]; // Retorna a lista de Pets
    }

    public static void editPet(Pet[] pets, int qntPets) {
        Pet selectedPet = findPet(pets, qntPets);

        if (selectedPet == null) {
            return;
        }

        try { // Caso o edit lançe uma exceção, o deleteForm nunca será executado
            selectedPet.editPetAtributtes(); // Troca a referencia para o novo objeto atualizado
            FormOperations.deleteForm(selectedPet); // Apaga o Arquivo Pet antigo
            FormOperations.writeForm(selectedPet); // Cria o Arquivo Pet atualizado com as alterações
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int deletePet(Pet[] pets, int qntPets) {
        Scanner scanner = new Scanner(System.in);
        Pet pet = findPet(pets, qntPets);

        if (pet == null) {
            return qntPets;
        }


        while (true) {
            System.out.println("Você deseja continuar para deletar o Pet " + pet.getNomePet() + "? (SIM/NÃO)");
            String text = normalizeText(scanner.nextLine()).trim();
            if (text.equals("sim")) {
                try {
                    FormOperations.deleteForm(pet);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                int index = 0;
                for (int i = 0; i < qntPets; i++) { // Busca o valor da posição onde o Pet está no Array de Pets
                    if (pets[i] == pet) {
                        index = i;
                        break;
                    }
                }

                for (int i = index; i < qntPets - 1; i++) { // Reorganiza o vetor, apartir de Index
                    pets[i] = pets[i + 1];
                }

                pets[qntPets - 1] = null; // Limpa a ultima posição, pois está duplicada com a penultima
                qntPets--;
                return qntPets;
            } else if (text.equals("nao")) {
                System.out.println("Operação cancelada");
                return qntPets;
            } else {
                System.out.println("Digite apenas SIM ou NÃO");
            }
        }
    }

    public static void listAllPets(Pet[] pets, int qntPets) {
        if (qntPets == 0) {
            System.out.println("Nenhum Pet foi cadastrado!");
            return;
        }

        for (int i = 0; i < qntPets; i++) {
            Pet pet = pets[i];

            System.out.print((i + 1) + ". ");
            pets[i].displayPet();
        }
    }
}
