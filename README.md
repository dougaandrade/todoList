### DICAS

- 1 . Clone o repositorio
- 2 . Veja os itens abaixos estão instalados na máquina
    - 1. JDK 23 (Java)
    - 2.  PostegreSQL 17
    - 3. Git
    - 4. NodeJS
    - 5. Angular

- 3 . Com o pgAdmin aberto, crie um database de teste. Por padrão, a aplicação vai tentar conectar com um database chamado "todo_db". Crie seu database com esse mesmo nome, crie o seu mesmo e va em **"teste-sem-medo\backend\src\main\resources\application.properties"** e coloque na URL o nome do seu databse.

- 4 . No backend, navegue até **"teste-sem-medo\backend\"**. Rode o comando **"mvn spring-boot:run"**

- 5 . Depois do NodeJS instalado, instale o Angular usando **"npm install -g @angular/cli"**.

- 6 . Navegue até a pasta frontend e coloque o comando **"npm install"** no terminal. Isso instala dependências e pacotes necessárias.

- 7 . Para rodar a frontend, basta fazer **"ng serve --open"**

- 8 . Caso apareça um erro sobre não poder rodar scripts, faça:
    - 1. Abra o powershell como admin
    - 2. Digite **"Get-ExecutionPolicy"** para verificar a política atual.
    - 3. Digite **"Set-ExecutionPolicy RemoteSigned"**. Aqui funcionou, dito isso... :) 
    - 4. Tente o passo 6 de novo 

- 9 . Se tudo deu certo, vá no seu navegador e coloque **"http://localhost:4200/api/teste"** e/ou **"http://localhost:4200"**. 
- 10 . Qualquer coisa me mande uma mensagem ou abra um issue.
- 10 . Forte abraço.






