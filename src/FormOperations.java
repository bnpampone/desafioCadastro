import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FormOperations {
    public static void showForm() {

        String file = "desafioCadastro/formulario.txt"; // Nome do arquivo que será lido
        // Try with resources para fechar o arquivo automaticamente pois ele é um Closeable()
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) { // BufferedReader ler de linha em linha. A ultima ele lê como Null, logo ele deve terminar a leitura
                System.out.println(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Em caso de algum erro no FilReader, ele lança a Exception no terminal
        }
    }

    public static void writeForm(Pet pet) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String currentDate = LocalDateTime.now().format(dateTimeFormatter);
        String fileName = pet.getNomePet().trim().replace(" ", "").toUpperCase();

        String path = "/home/brenopamponet/Área de trabalho/SistemaCadastroPets/" + currentDate + "-" + fileName + ".txt";
        pet.setReportPath(path); // Path para que o File file New File() consiga referenciar qual Pet deletar

        // try with resources para fechar automaticamente, pois o BufferedWriter necessita fechar o recurso após o uso. Ele é um Closeable()
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("1 - " + pet.getNomePet());
            writer.newLine();
            writer.write("2 - " + pet.getTipo());
            writer.newLine();
            writer.write("3 - " + pet.getSexo());
            writer.newLine();
            writer.write("4 - " + pet.getRuaEndereco() + ", " + pet.getNumeroEndereco() + ", " + pet.getCidadeEndereco());
            writer.newLine();
            writer.write("5 - " + pet.getIdade());
            writer.newLine();
            writer.write("6 - " + pet.getPeso() + "kg");
            writer.newLine();
            writer.write("7 - " + pet.getRaca());
            writer.newLine();
            writer.write("8 - " + pet.getRegisterDate());
            for (int i = 0; i < pet.getQntExtraQuestions(); i++) {
                writer.newLine();
                writer.write((8+i) + " - [EXTRA - PERGUNTA NOVA ADICIONADA] - " + pet.getExtraQuestions()[i]);
            }
            writer.flush();
            System.out.println("Relatório do Pet Criado com Sucesso!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void deleteForm(Pet pet) throws FileNotFoundException {
        File file = new File(pet.getReportPath());

        if (!file.exists()) {
            throw new FileNotFoundException("Arquivo não encontrado.");
        }

        if (file.delete()) {
            System.out.println("Arquivo deletado com Sucesso!");
        } else {
            throw new RuntimeException("Não foi possível deletar o arquivo.");
        }

    }

    public static void createQuestion() {
        Scanner scanner = new Scanner(System.in);

        String file = "desafioCadastro/formulario.txt";
        
        System.out.println("Digite a nova pergunta:");
        String question = scanner.nextLine();

        int numberOfQuestions = 0; // Define a quantidade de Perguntas no Array

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) { // Primeiro laço de repetição para descobrir o número de questões
                numberOfQuestions++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] questions = new String[numberOfQuestions]; // Array de questions armazenando o número de perguntas

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            int i = 0;

            while ((currentLine = reader.readLine()) != null) {
                questions[i] = currentLine;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String[] array = new String[numberOfQuestions + 1];

        for (int j = 0; j < numberOfQuestions; j++) {
            array[j] = questions[j];
        }

        array[numberOfQuestions] = (numberOfQuestions + 1) + " - [EXTRA - PERGUNTA NOVA ADICIONADA] - " + question + "?";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for(String line : array) {
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
            System.out.println("Pergunta adicionada com Sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    }
}

