import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pet {
    private String nomePet;
    private SexoPet sexo;
    private double idade;
    private double peso;
    private TipoPET tipo;
    private String raca;
    private String ruaEndereco;
    private String numeroEndereco;
    private String cidadeEndereco;
    private static final String NIF = "NÃO INFORMADO";
    private LocalDate registerDate;
    private String reportPath;
    private String[] extraQuestions = new String[100];
    private int qntExtraQuestions;

    public static final String BOLD = "\033[1m";
    public static final String RESET = "\033[0m";

    Scanner scanner = new Scanner(System.in);

    public Pet() {
        this.registerDate = LocalDate.now();
    }

    public enum SexoPet {
        MACHO,
        FEMEA,
    }

    public enum TipoPET {
        CACHORRO,
        GATO,
    }

    public void registerPet() throws Exception {
       readForm();
    }

    public void readForm() throws Exception {
        String file = "desafioCadastro/formulario.txt"; // Nome do arquivo que será lido

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine;

            int question = 1;
            while ((currentLine = reader.readLine()) != null) {

                System.out.println(currentLine);

                String response = scanner.nextLine();

                switch (question) {
                    case 1:
                        this.nomePet = response;
                        nameValidation();
                        break;

                    case 2:
                        try {
                            this.tipo = TipoPET.valueOf(response.toUpperCase().trim());
                        } catch (IllegalArgumentException e) {
                            throw new Exception("Tipo Invalido");
                        }
                        break;

                    case 3:
                        try {
                            this.sexo = SexoPet.valueOf(response.trim().toUpperCase());
                        } catch (IllegalArgumentException e) {
                            throw new Exception("Tipo Invalido");
                        }
                        break;

                    case 4:
                        addresValidation(response);

                        String[] addres = response.split(",");

                        this.ruaEndereco = addres[0].trim();
                        this.numeroEndereco = addres[1].trim();
                        this.cidadeEndereco = addres[2].trim();
                        break;

                    case 5:
                        ageValidation(response);
                        break;

                    case 6:
                        weightValidation(response);
                        break;

                    case 7:
                        this.raca = response;
                        breedValidation();
                        break;

                    default:
                        if (response.isBlank()) {
                            response = NIF;
                        }

                        extraQuestions[qntExtraQuestions] =  response;
                        qntExtraQuestions++;
                }
                question++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void editPetAtributtes() throws Exception {
        System.out.println("Editar nome: ");
        this.nomePet = scanner.nextLine();
        nameValidation();

        System.out.println("Editar rua: ");
        this.ruaEndereco = scanner.nextLine();

        System.out.println("Editar numero da casa: ");
        this.numeroEndereco = scanner.nextLine();

        System.out.println("Editar cidade: ");
        this.cidadeEndereco = scanner.nextLine();

        System.out.println("Editar idade: ");
        ageValidation(scanner.nextLine());

        System.out.println("Editar peso: ");
        weightValidation(scanner.nextLine());

        System.out.println("Editar raça: ");
        this.raca = scanner.nextLine();
        breedValidation();
    }

    public void nameValidation() throws Exception {
        String regex = "[a-zA-Z]+\\s[a-zA-Z]+$"; // Define uma Regex onde só permite letras de A-Z e a-z
        Pattern pattern = Pattern.compile(regex); // Compila o padrão
        Matcher matcher = pattern.matcher(this.nomePet); // Matcher para achar se o NOME está no padrão da REGEX
        if (!matcher.matches() || nomePet.isBlank()) {
            throw new Exception("Nome e Sobrenome do Pet são OBRIGATORIOS"); // Se não bater com o padrão, lança-se uma EXCEPTION
        }
    }

    public void addresValidation(String value) throws Exception {
        value = value.trim();

        String regex = "[a-zA-ZÀ-ÿ ]+,\\s*\\d+,\\s*[a-zA-ZÀ-ÿ ]+$";

        if(!value.matches(regex)){
            throw new Exception("Informe no formato: Rua, Número, Cidade");
        }
    }

    public void weightValidation(String value) throws Exception {
        try {
            this.peso = Double.parseDouble(value.replace(",", "."));
        } catch (NumberFormatException e) {
            throw new Exception("Pdade deve conter apenas números");
        }
        if (this.peso > 60 || this.peso < 0.5) {
            throw new Exception("Peso invalido para o sistema");
        }
    }

    public void ageValidation(String value) throws Exception {
        try {
            this.idade = Double.parseDouble(value.replace(",", "."));
        } catch (NumberFormatException e) {
            throw new Exception("Idade deve conter apenas números");
        }
        if (this.idade > 20 || this.idade <= 0) {
            throw new Exception("Idade invalida para o sistema");
        } else {
            if (this.idade < 12) {
                this.idade /= 12;
            }
        }
    }

    public void breedValidation() throws Exception {
        if (this.raca.isBlank()) {
            this.raca = NIF;
            return;
        }
        String regex = "[a-zA-Z ]+$"; // Define uma Regex onde só permite letras de A-Z e a-z
        Pattern pattern = Pattern.compile(regex); // Compila o padrão
        Matcher matcher = pattern.matcher(this.raca); // Matcher para achar se a RAÇA está no padrão da REGEX
        if (!matcher.matches()) {
            throw new Exception("Raça do Pet deve conter APENAS letras de A-Z"); // Se não bater com o padrão, lança-se uma EXCEPTION
        }

    }

    public void displayPet() {
        System.out.print(
                nomePet + " - " +
                        tipo + " - " +
                        sexo + " - " +
                        ruaEndereco + ", " +
                        numeroEndereco + " - " +
                        cidadeEndereco + " - " +
                        idade + " anos - " +
                        peso + "kg - " +
                        raca + " - " +
                        registerDate.getMonthValue() + "/" + registerDate.getYear() + " - "
        );
        for (int i = 0; i < qntExtraQuestions; i++) {
            System.out.println(extraQuestions[i]);
        }
    }

    public void displayPet(int criterion, String findSearch) {
        String name = nomePet;
        String breed = raca;
        String address = ruaEndereco + ", " + numeroEndereco + " - " + cidadeEndereco;
        String date = registerDate.getMonthValue() + "/" + registerDate.getYear();

        switch (criterion) {
            case 1:
                name = highlight(nomePet, findSearch);
                break;
            case 5:
                breed = highlight(raca, findSearch);
                break;
            case 6:
                address = highlight(address, findSearch);
                break;

        }
        System.out.println(
                name + " - " +
                        tipo + " - " +
                        sexo + " - " +
                        address + " - " +
                        idade + " anos - " +
                        peso + "kg - " +
                        breed + " - " +
                        date
        );

    }

    public static String highlight(String text, String search) {
        if (search.isBlank()) {
            return text;
        }
        return text.replaceAll("(?i)" + Pattern.quote(search), BOLD + "$0" + RESET);
    }


    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public SexoPet getSexo() {
        return sexo;
    }

    public void setSexo(SexoPet sexo) {
        this.sexo = sexo;
    }

    public double getIdade() {
        return idade;
    }

    public void setIdade(double idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public TipoPET getTipo() {
        return tipo;
    }

    public void setTipo(TipoPET tipo) {
        this.tipo = tipo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getRuaEndereco() {
        return ruaEndereco;
    }

    public void setRuaEndereco(String ruaEndereco) {
        this.ruaEndereco = ruaEndereco;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getCidadeEndereco() {
        return cidadeEndereco;
    }

    public void setCidadeEndereco(String cidadeEndereco) {
        this.cidadeEndereco = cidadeEndereco;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public int getQntExtraQuestions() {
        return qntExtraQuestions;
    }

    public void setQntExtraQuestions(int qntExtraQuestions) {
        this.qntExtraQuestions = qntExtraQuestions;
    }

    public String[] getExtraQuestions() {
        return extraQuestions;
    }

    public void setExtraQuestions(String[] extraQuestions) {
        this.extraQuestions = extraQuestions;
    }
}
