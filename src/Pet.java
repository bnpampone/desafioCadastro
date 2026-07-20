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
        FormOperations.showForm();
        System.out.println("1 - Nome e Sobrenome do Pet");
        this.nomePet = scanner.nextLine();
        nameValidation();

        System.out.println("2 - Tipo do Pet (Cachorro/Gato)");
        try {
            this.tipo = TipoPET.valueOf(scanner.nextLine().trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception("Tipo Invalido");
        }

        System.out.println("3 - Sexo do Pet (MACHO/FEMEA)");
        try {
            this.sexo = SexoPet.valueOf(scanner.nextLine().trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception("Tipo Invalido");
        }

        System.out.println("4.1 - Rua");
        this.ruaEndereco = scanner.nextLine();
        if (this.ruaEndereco.isBlank()) {
            this.ruaEndereco = NIF;
        }
        System.out.println("4.2 - Numero");
        this.numeroEndereco = scanner.nextLine();
        if (this.numeroEndereco.isBlank()) {
            this.numeroEndereco = NIF;
        }

        System.out.println("4.3 - Cidade");
        this.cidadeEndereco = scanner.nextLine();
        if (this.cidadeEndereco.isBlank()) {
            this.cidadeEndereco = NIF;
        }

        System.out.println("5 - Idade do Pet");
        ageValidation();

        System.out.println("6 - Peso do Pet");
        weightValidation();

        System.out.println("7 - Raça do Pet");
        this.raca = scanner.nextLine();
        breedValidation();
    }

    public void editPet() throws Exception {
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
        ageValidation();

        System.out.println("Editar peso: ");
        weightValidation();

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

    public void weightValidation() throws Exception {
        try {
            this.peso = Double.parseDouble(scanner.nextLine().replace(",", "."));
        } catch (NumberFormatException e) {
            throw new Exception("Idade deve conter apenas números");
        }
        if (this.peso > 60 || this.peso < 0.5) {
            throw new Exception("Peso invalido para o sistema");
        }
    }

    public void ageValidation() throws Exception {
        try {
            this.idade = Double.parseDouble(scanner.nextLine().replace(",", "."));
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
        System.out.println(
                nomePet + " - " +
                        tipo + " - " +
                        sexo + " - " +
                        ruaEndereco + ", " +
                        numeroEndereco + " - " +
                        cidadeEndereco + " - " +
                        idade + " anos - " +
                        peso + "kg - " +
                        raca + " - " +
                        registerDate.getMonthValue() + "/" + registerDate.getYear()
        );
    }

    public void displayPet(int criterion, String findSearch){
        String name = nomePet;
        String breed = raca;
        String address = ruaEndereco + ", " + numeroEndereco + " - " + cidadeEndereco;
        String date = registerDate.getMonthValue() + "/" + registerDate.getYear();

        switch (criterion){
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

    public static String highlight(String text, String search){
        if(search.isBlank()){
            return text;
        }
        return text.replaceAll("(?i)" + Pattern.quote(search), BOLD + "$0" + RESET);
    }


    @Override
    public String toString() {
        return "Pet{" +
                "nomePet='" + nomePet + '\'' +
                ", sexo=" + sexo +
                ", idade=" + idade +
                ", peso=" + peso +
                ", tipo=" + tipo +
                ", raca='" + raca + '\'' +
                ", ruaEndereco='" + ruaEndereco + '\'' +
                ", numeroEndereco='" + numeroEndereco + '\'' +
                ", cidadeEndereco='" + cidadeEndereco + '\'' +
                ", registerDate=" + registerDate +
                '}';
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
}
