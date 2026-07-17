import java.text.Normalizer;
import java.time.LocalDate;
import java.util.Scanner;

public class FilterPets {
    public static String normalizeText(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase();
    }

    public static void showIfPetWasFound(boolean foundPet) {
        if (!foundPet) {
            System.out.println("Nenhum pet foi encontrado");
        }
    }

    public static boolean verifySecondCriterion(Pet pet, int criterion, String value) {

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
                double age = Double.parseDouble(value);
                return pet.getIdade() == age;

            case 4:
                double weight = Double.parseDouble(value);
                return pet.getPeso() == weight;

            case 5:
                return normalizeText(pet.getRaca()).contains(normalizeText(value));

            case 6:
                String addres = normalizeText(
                        pet.getRuaEndereco() + " " +
                                pet.getNumeroEndereco() + " " +
                                pet.getCidadeEndereco()
                );
                return addres.contains(normalizeText(value));
            case 7:
                String[] parts = value.split("/");

                int month = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                LocalDate date = pet.getRegisterDate();

                return date.getMonthValue() == month && date.getYear() == year;
            default:
                return false;


        }
    }

//    public void callSecondCriterionOrNot(Pet pet, int secondOption, boolean foundPet, int useSecond, String secondValue){
//        if (secondOption == 0) { // Se Segundo Criterio não existe, mostre o Pet com 1 criterio
//            pet.displayPet();
//            foundPet = true;
//        } else {
//            if (verifySecondCriterion(pet, useSecond, secondValue)) {
//                pet.displayPet();
//                foundPet = true;
//            }
//        }
//    }


    public static void findPet(Pet[] pets, int qntPets) {
        Scanner scanner = new Scanner(System.in);
        boolean foundPet = false;
        int counter = 1;

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

        // Segundo Criterio
        System.out.println("Deseja adicionar um segundo criterio ?\n" +
                "0 - Não\n" +
                "1 - Sim");


        int secondOption = scanner.nextInt();
        scanner.nextLine();

        int useSecond = 0;
        String secondValue = "";

        if (secondOption == 1) {

            System.out.println("Escolha o Segundo Criterio:" +
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


        switch (useSecond) {

            case 1:
                System.out.println("Digite o nome:");
                secondValue = scanner.nextLine();
                break;
            case 2:
                System.out.println("Digite o sexo:");
                secondValue = scanner.nextLine();
                break;
            case 3:
                System.out.println("Digite a idade:");
                secondValue = scanner.nextLine();
                break;
            case 4:
                System.out.println("Digite o peso:");
                secondValue = scanner.nextLine();
                break;
            case 5:
                System.out.println("Digite a raça:");
                secondValue = scanner.nextLine();
                break;
            case 6:
                System.out.println("Digite o endereço:");
                secondValue = scanner.nextLine();
                break;
            case 7:
                System.out.println("Digite a data (Mẽs):");
                String month = scanner.nextLine();
                System.out.println("Digite a data (Ano):");
                String year = scanner.nextLine();
                secondValue = month + "/" + year;
                break;
        }

        switch (firstOption) {

            case 1:
                System.out.println("Digite o Nome ou Sobrenome do Pet:");
                String findName = scanner.nextLine();
                for (int i = 0; i < qntPets; i++) {

                    Pet pet = pets[i]; // Armazena o Pet da Posição [i] na variavel de referencia "pet"

                    if (pet.getTipo() != tipoBusca) {
                        continue; // Compara o Pet Atual da posição [i] com o tipoBusca, se não baterem ele pula pro proximo Pet
                    }

                    if (normalizeText(pet.getNomePet()).contains(normalizeText(findName))) { // Compara o Nome do Pet no sistema com o Nome que foi passado no findName
                        if (secondOption == 0) { // Se Segundo Criterio não existe, mostre o Pet com 1 criterio
                            System.out.print(counter + ". ");
                            pet.displayPet(firstOption, findName);
                            foundPet = true;
                            counter++;
                        } else {
                            if (verifySecondCriterion(pet, useSecond, secondValue)) {
                                System.out.print(counter + ". ");
                                pet.displayPet(firstOption, findName);
                                foundPet = true;
                                counter++;
                            }
                        }
                    }
                }
                FilterPets.showIfPetWasFound(foundPet);
                break;

            case 2:
                System.out.println("Digite o Sexo do seu Pet (MACHO/FEMEA):");
                Pet.SexoPet findSexo;

                try {
                    findSexo = Pet.SexoPet.valueOf(scanner.nextLine().trim().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Sexo Invalido");
                    break;
                }

                for (int i = 0; i < qntPets; i++) {
                    Pet pet = pets[i];

                    if (pet.getTipo() != tipoBusca) {
                        continue;
                    }

                    if (pet.getSexo() == findSexo) {
                        if (secondOption == 0) {
                            System.out.print(counter + ". ");
                            pet.displayPet();
                            foundPet = true;
                            counter++;
                        } else {
                            if (verifySecondCriterion(pet, useSecond, secondValue)) {
                                System.out.print(counter + ". ");
                                pet.displayPet();
                                foundPet = true;
                                counter++;
                            }
                        }
                    }
                }
                FilterPets.showIfPetWasFound(foundPet);
                break;

            case 3:
                System.out.println("Informe a Idade do seu Pet:");
                double findAge = 0;

                try {
                    findAge = Double.parseDouble(scanner.nextLine().replace(",", "."));
                } catch (NumberFormatException e) {
                    System.out.println("Idade invalida");
                    break;
                }

                for (int i = 0; i < qntPets; i++) {
                    Pet pet = pets[i];

                    if (pet.getTipo() != tipoBusca) {
                        continue;
                    }

                    if (pet.getIdade() == findAge) {
                        if (secondOption == 0) {
                            System.out.print(counter + ". ");
                            pet.displayPet();
                            foundPet = true;
                            counter++;
                        } else {
                            if (verifySecondCriterion(pet, useSecond, secondValue)) {
                                System.out.print(counter + ". ");
                                pet.displayPet();
                                foundPet = true;
                                counter++;
                            }
                        }
                    }
                }
                FilterPets.showIfPetWasFound(foundPet);
                break;

            case 4:
                System.out.println("Informe o Peso do seu Pet:");
                double findWeight = 0;

                try {
                    findWeight = Double.parseDouble(scanner.nextLine().replace(",", "."));
                } catch (NumberFormatException e) {
                    System.out.println("Peso Invalido");
                    break;
                }

                for (int i = 0; i < qntPets; i++) {
                    Pet pet = pets[i];

                    if (pet.getTipo() != tipoBusca) {
                        continue;
                    }

                    if (pet.getPeso() == findWeight) {
                        if (secondOption == 0) {
                            System.out.print(counter + ". ");
                            pet.displayPet();
                            foundPet = true;
                            counter++;
                        } else {
                            if (verifySecondCriterion(pet, useSecond, secondValue)) {
                                System.out.print(counter + ". ");
                                pet.displayPet();
                                foundPet = true;
                                counter++;
                            }
                        }
                    }
                }
                FilterPets.showIfPetWasFound(foundPet);
                break;

            case 5:
                System.out.println("Informe a raça do seu Pet:");

                String findBreed = scanner.nextLine().trim();

                for (int i = 0; i < qntPets; i++) {
                    Pet pet = pets[i];

                    if (pet.getTipo() != tipoBusca) {
                        continue;
                    }

                    if (normalizeText(pet.getRaca()).contains(normalizeText(findBreed))) {
                        if (secondOption == 0) {
                            System.out.print(counter + ". ");
                            pet.displayPet(firstOption, findBreed);
                            foundPet = true;
                            counter++;
                        } else {
                            if (verifySecondCriterion(pet, useSecond, secondValue)) {
                                System.out.print(counter + ". ");
                                pet.displayPet(firstOption, findBreed);
                                foundPet = true;
                                counter++;
                            }
                        }
                    }
                }
                FilterPets.showIfPetWasFound(foundPet);
                break;

            case 6:
                System.out.println("Informe o Numero, Rua e Cidade onde o Pet reside:");
                String findAddress = scanner.nextLine().trim();


                for (int i = 0; i < qntPets; i++) {
                    Pet pet = pets[i];

                    if (pet.getTipo() != tipoBusca) {
                        continue;
                    }

                    String address = normalizeText(
                            pet.getRuaEndereco() + " " +
                                    pet.getNumeroEndereco() + " " +
                                    pet.getCidadeEndereco()
                    );

                    if (address.contains(normalizeText(findAddress))) {
                        if (secondOption == 0) {
                            System.out.print(counter + ". ");
                            pet.displayPet(firstOption, findAddress);
                            foundPet = true;
                            counter++;
                        } else {
                            if (verifySecondCriterion(pet, useSecond, secondValue)) {
                                System.out.print(counter + ". ");
                                pet.displayPet(firstOption, findAddress);
                                foundPet = true;
                                counter++;
                            }
                        }

                    }
                }
                FilterPets.showIfPetWasFound(foundPet);
                break;
            case 7:
                System.out.println("Digite o mês:");
                int month = Integer.parseInt(scanner.nextLine());

                System.out.println("Digite o ano:");
                int year = Integer.parseInt(scanner.nextLine());

                for (int i = 0; i < qntPets; i++) {
                    Pet pet = pets[i];

                    if(pet.getTipo() != tipoBusca){
                        continue;
                    }

                    LocalDate date = pet.getRegisterDate();

                    if(date.getMonthValue() == month && date.getYear() == year){
                        if(secondOption == 0){
                            System.out.print(counter + ". ");
                            pet.displayPet();
                            foundPet = true;
                            counter++;
                        } else {
                            if (verifySecondCriterion(pet, useSecond, secondValue)) {
                                System.out.print(counter + ". ");
                                pet.displayPet();
                                foundPet = true;
                                counter++;
                            }
                        }
                    }
                }
                FilterPets.showIfPetWasFound(foundPet);

                break;
            default:
                System.out.println("Valor Invalido");
                break;
        }
    }
}
