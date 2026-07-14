import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {
    public static void main(String[] args) {

        String file = "desafioCadastro/formulario.txt"; // Nome do arquivo que será lido
        // Try with resources para fechar o arquivo automaticamente pois ele é um Closeable()
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) { // BufferedReader ler de linha em linha. A ultima ele lê como Null, logo ele deve terminar a leitura
                System.out.println(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Em caso de algum erro no FilReader, ele lança a Exception no terminal
        }
    }
}
