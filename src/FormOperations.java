import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
}
