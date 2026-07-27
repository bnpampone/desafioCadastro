# 🐾 Sistema de Adoção para Pets

> Meu primeiro projeto desenvolvido em Java.

Este projeto foi desenvolvido como solução para o **Desafio de Programação** criado por **Lucas Carrilho (@devmagro)**.

O sistema funciona via terminal (CLI) e permite realizar o gerenciamento dos pets cadastrados, utilizando conceitos de POO, manipulação de arquivos e validação de dados.

Vídeo demonstrativo da execução do projeto: https://www.youtube.com/watch?v=l5C3QaiBSAo

---

## ✨ Funcionalidades

- ✅ Cadastrar um novo pet
- ✅ Buscar pets utilizando diferentes critérios
- ✅ Alterar os dados de um pet cadastrado
- ✅ Excluir um pet
- ✅ Listar todos os pets cadastrados
- ✅ Gerenciar o formulário de cadastro (adicionar, editar e remover perguntas)

---

## 🚀 Tecnologias utilizadas

- Java
- Programação Orientada a Objetos (POO)
- Java IO
- Manipulação de arquivos (.txt)
- Enum
- Tratamento de Exceções
- Regex
- File System

---

## 📂 Estrutura do projeto

Antes de executar o programa, crie a seguinte estrutura:

```text
desafioCadastro/
│
├── Pets/
│
└── (arquivos do projeto)
```

A pasta **Pets** será utilizada para armazenar automaticamente os formulários dos pets cadastrados.

---

## ⚙️ Como executar

1. Clone o repositório:

```bash
git clone https://github.com/SEU-USUARIO/NOME-DO-REPOSITORIO.git
```

2. Abra o projeto na sua IDE.

3. Atualize os caminhos absolutos utilizados na classe `FormOperations.java` para os caminhos do seu computador.

4. Execute a classe `Main`.

---

# 🖥️ Funcionamento

Ao iniciar o programa será exibido o seguinte menu:

```text
1 - Iniciar o sistema para cadastro de PETS
2 - Iniciar o sistema para alterar formulário
```

---

## 🐶 Sistema de Cadastro

Ao escolher a opção **1**, será possível utilizar as seguintes funcionalidades:

```text
1 - Cadastrar um novo pet
2 - Alterar os dados de um pet
3 - Listar todos os pets
4 - Excluir um pet
5 - Voltar ao menu inicial
6 - Sair
```

Nesse menu é possível:

- cadastrar novos pets;
- alterar os dados de um pet já cadastrado;
- listar todos os pets cadastrados;
- excluir um pet;
- retornar ao menu inicial;
- encerrar o programa.

---

## 📝 Sistema de Formulário

Ao escolher a opção **2**, será possível gerenciar o formulário de perguntas utilizado para realizar o cadastro dos pets.

```text
1 - Criar nova pergunta
2 - Alterar pergunta existente
3 - Excluir pergunta existente
4 - Voltar para o menu inicial
5 - Sair
```

Nesse menu é possível:

- criar novas perguntas para o formulário;
- alterar perguntas adicionadas anteriormente;
- excluir perguntas adicionadas pelo usuário;
- retornar ao menu inicial;
- encerrar o programa.

> **Observação:** As perguntas originais do sistema (1 a 7) permanecem protegidas e não podem ser alteradas ou removidas.

---

## 🔎 Busca de Pets

O sistema permite buscar um pet utilizando um ou dois critérios.

Critérios disponíveis:

- Nome ou sobrenome
- Sexo
- Idade
- Peso
- Raça
- Endereço

Além disso, a busca:

- ignora letras maiúsculas e minúsculas;
- ignora acentos;
- permite pesquisar por parte do nome (ex.: pesquisar **"Flor"** encontra **"Florzinha"**);
- Além de destacar em negrito o termo buscado.

---

## 📁 Armazenamento

Cada pet é salvo automaticamente em um arquivo `.txt` dentro da pasta **Pets**, utilizando o seguinte formato:

```text
YYYYMMDD'T'HHMM-NOMEDOPET.txt
```

Exemplo:

```text
20260716T1425-FLORZINHADASILVA.txt
```

Os arquivo contém todas as informações cadastradas sobre o seu respectivo pet, facilitando sua leitura e gerenciamento.

---

## 📚 Aprendizados

Durante o desenvolvimento deste projeto foram praticados conceitos como:

- Classes e Objetos
- Encapsulamento
- Métodos
- Arrays
- Enum
- Tratamento de Exceções
- Leitura e Escrita de Arquivos
- Organização de Código
- Manipulação de Strings
- Expressões Regulares (Regex)

---

## 👨‍💻 Autor

Desenvolvido por **Breno Marcelo Pamponé**.

Como meu primeiro projeto em Java, este desafio foi uma ótima oportunidade para praticar lógica de programação, orientação a objetos, manipulação de arquivos e organização de código.
