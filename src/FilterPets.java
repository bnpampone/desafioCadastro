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
        return "erro";
    }


    public static void findPet(Pet[] pets, int qntPets) {
        Scanner scanner = new Scanner(System.in);
        int counterPets = 0;
        Pet[] listPets = new Pet[qntPets];

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
            return;
        }


        // Primeiro Criterio
        System.out.println("Escolha o Primeiro Criterio:\n" +
                "1- Nome ou sobrenome\n" +
                "2- Sexo\n" +
                "3- Idade\n" +
                "4- Peso\n" +
                "5- Raça\n" +
                "6- Endereço\n" +
                "7- Data (Mês/Ano)");


        int firstOption = scanner.nextInt();
        scanner.nextLine();


        System.out.println("Deseja adicionar um segundo criterio ?\n" +
                "0 - Não\n" +
                "1 - Sim");

        int secondOption = scanner.nextInt();
        scanner.nextLine();

        // Segundo Criterio

        int useSecond = 0;
        if (secondOption == 1) {
            System.out.println("Escolha o Segundo Criterio:\n" +
                    "1- Nome ou sobrenome\n" +
                    "2- Sexo\n" +
                    "3- Idade\n" +
                    "4- Peso\n" +
                    "5- Raça\n" +
                    "6- Endereço\n" +
                    "7- Data (Mês/Ano)");
            useSecond = scanner.nextInt();
            scanner.nextLine();
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
        if (counterPets == 0) {
            showIfPetWasFound(false);
            return;
        }
        int opcao;
        while (true) { // While para que o usuario não consiga sair sem terminar a alteração
            // Alteração de um pet cadastrado
            System.out.println("Digite o Pet que você deseja alterar:");

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

        Pet selectedPet = listPets[opcao - 1];

        try {
            selectedPet.editPet();
            FormOperations.writeForm(selectedPet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
