# Gerenciamento-De-Funcionarios
O Sistema de Gerenciamento de Funcionários é uma aplicação desenvolvida para simplificar e automatizar o controle dos colaboradores em uma empresa. Com um conjunto abrangente de recursos, visa facilitar o cadastro, visualização, atualização e remoção de informações dos funcionários, oferecendo uma interface intuitiva e eficiente para a administração de recursos humanos.

# Índice 
* [Tecnologias](#Tecnologias)
* [Funcionalidades do projeto](#Funcionalidades-do-Projeto)
* [Diagramas do Projeto](#Diagramas-Do-Projeto)
* [Testes Unitários](#Testes-Unitários)
  
# Tecnologias
- IDE Eclipse;
- Java;
- Banco de dados: MySQL;
- Testes unitários: JUnit.

# Funcionalidades Do Projeto
- `Validações`:
  - Nomes precisam ter pelo menos três letras;
  - Verifica se o email é valido e único;
  - A data de nascimento para funcionários é a partir de 18 até 75 anos;
  - Verifica se o CPF é valido;
    
- `Login`: Apenas usuários com a permissão de ADMIN tem acesso ao sistema.

![login](https://github.com/Jenifer-Rafaela/Gerenciamento-De-Funcionarios/assets/100365167/d06da2d5-75a5-4df2-b54e-7e31554d31d5)

- `Login sem Permissão`:

![Login sem permissao](https://github.com/Jenifer-Rafaela/Gerenciamento-De-Funcionarios/assets/100365167/4b6f7bd0-dd83-4b6c-84e7-451b97abe108)
  
- `Funcionários`: É possível ver os funcionários cadastrados.

![Tela Funcionarios](https://github.com/Jenifer-Rafaela/Gerenciamento-De-Funcionarios/assets/100365167/17d39e61-c5af-4c91-891c-6557fb527b57)
  
- `Cadastrar Funcionário`: É possível cadastrar funcionário, informando: nome, email, cpf, data de nascimento e setor.

![Cadastrar Funcionario](https://github.com/Jenifer-Rafaela/Gerenciamento-De-Funcionarios/assets/100365167/158f7325-3d12-45ac-8a1c-d9a9926ff19a)

- `Cadastrar Funcionário com Email e CPF inválido`:

![Cadastrar Funcionario invalido](https://github.com/Jenifer-Rafaela/Gerenciamento-De-Funcionarios/assets/100365167/e3205eb9-f347-46f2-84aa-e5f14931bcdc)
  
- `Atualizar Funcionário`: É possível atualizar funcionário.

![Atualizar Funcionario](https://github.com/Jenifer-Rafaela/Gerenciamento-De-Funcionarios/assets/100365167/b1e1687a-a08b-4490-adfd-621d3b78704e)
  
- `Remover Funcionário`: É possível remover funcionário.

![Remover Funcionario](https://github.com/Jenifer-Rafaela/Gerenciamento-De-Funcionarios/assets/100365167/52cc84e3-c679-49a1-81bc-108b5dacf6d0)
  
- `Sair`: É possível sair e voltar ao login.

![Sair](https://github.com/Jenifer-Rafaela/Gerenciamento-De-Funcionarios/assets/100365167/dfd5b038-51a7-4259-8ad7-123a40952c5b)

# Diagramas Do Projeto
- Esquema do Banco de Dados
![CURSO](https://github.com/Jenifer-Rafaela/Instituicao-De-Ensino/assets/100365167/db08b747-acd2-4a56-982c-80bc18b95938)

# Testes Unitários
