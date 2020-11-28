# Relatório técnico

## Introdução

Este documento contém os aspectos relevantes do desenho e implementação da fase 1 do projecto de LS.

## Modelação da base de dados

### Modelação conceptual ###

O seguinte diagrama apresenta a modelo entidade-associação para a informação gerida pelo sistema. 

![](https://github.com/isel-leic-ls/2021-1-LI41N-G8/blob/phase1-docs/docs/images/ModeloER-phase1.png)

Destacam-se os seguintes aspectos deste modelo:

* As chaves primárias do modelo relacional, são obtidas através da atribuição do pseudo-tipo SERIAL à respetiva coluna. É de notar que SERIAL não cria implicitamente um índice na coluna. Contudo isso poder resolvido facilmente com a atribuição dessa coluna como PRIMARY KEY.

* Uma review pode ter ou não uma completeReview, cabe ao utilizador decidir. Isto abre a possibilidade para que na coluna completeReview exista tuplos com o respetivo atributo a null.

O modelo conceptual apresenta ainda as seguintes restrições:

* O valor de um rating têm que estar compreendido entre 1 e 5.		
* Um usuário só pode fazer um review por cada movie.		
* Não pode existir users com o mesmo email.		
* Dois filmes podem ter títulos iguais, mas não podem ser do mesmo ano.
   
### Modelação física ###

O modelo físico da base de dados está presente no _[script createSchema.sql](https://raw.githubusercontent.com/isel-leic-ls/2021-1-LI41N-G8/master/src/main/sql/createSchema.sql)_.

Destacam-se os seguintes aspectos deste modelo:

* As chaves primárias do modelo relacional, são obtidas através da atribuição do pseudotipo SERIAL à respetiva coluna. É de notar que SERIAL não cria implicitamente um índice na coluna. Contudo, isso poder resolvido facilmente com a atribuição dessa coluna como PRIMARY KEY.

## Organização do software

A aplicação está dividida em várias camadas, de modo a permitir uma melhor 
gestão dos componentes e uma separação das responsabilidades de cada camada.

![Application layers](https://github.com/isel-leic-ls/2021-1-LI41N-G8/raw/master/docs/images/LayerBlock.png)

O desenvolvimento da aplicação aproxima-se da utilização do _design_ MVC, onde
temos o componente _Model_, compreendendo a configuração e inicialização da 
ligação á base de dados Postgresql, bem como introdução de classes representando
o _domain_.

A componente do _Controller_, que está refletida na existência do _Router_ e dos
diversos _Handlers_ que fazem a ponte entre o _input_ do utilizador, na forma de 
um comando, e o _output_ que vêm da base de dados.

Por fim, procurou-se criar a _View_ da aplicação numa classe que tem toda a 
interação com a consola, e que permite rapidamente mudar para outra _view_ (_Web_)
devido ao seu encapsulamento.

#### Organização do código

O software está organizado por diversos packages dentro da raíz `pt.isel.ls`, refletindo
as camadas aplicacionais em utilização:
*  `config`: classes para configurar a base de dados e o _routing_ da aplicação
*  `data`: classes com ligação á base de dados
*  `model`: representaçao dos modelos de dados na aplicaçao
*  `services`: _handlers_ da aplicação, novos _handlers_ devem ser criados neste 
_package_
*  `utils`: classes auxiliares que sao utilizadas transversalmente

Na raíz apenas temos as classes `App` responsável pelo inicio da aplicação e a `AppConsole`
que interage com o utilizador.

### Processamento de comandos

O processamento dos _inputs_ do utilizador está distribuído, entre a passagem
como argumento para a aplicação ou através de um modo interativo disponível
na `AppConsole`, que solicita _inputs_ até ao comando para sair - `EXIT /`.

Na `AppConsole` é realizada a conversão do _input_ do utilizador na estrutura 
de dados representada pela classe `Command`, que agrega:
*  `enum Method`: representa o pedido, aceitando os valores GET/POST/PUT
*  `class Path`: guarda uma lista com os componentes do caminho
*  `class Parameters`: guarda uma lista com os parâmetros

A classe `Command` tem definida o método `matches(Command)` que lhe permite ser usada
para definir um _template_ para cada comando aceite pela a aplicação e validar
de forma transparente o comando introduzido pelo utilizador. 

Essa validação é replicada com a utilização de métodos similares nos objetos
que compõem a classe `Command`, sendo:
*  método `equals(Object)`: no `Method` porque queremos que seja igual
*  método `matches(Object)` e `isValid(Object)`: na `Path` e `Parameters`, porque queremos que siga a
estrutura do _template_.

O local de validação ocorre no `Router`, no momento em que se pretende obter o 
_handler_ responsável pelo comando. Caso seja um comando inválido, o `Router`
lança uma exceção `RouterException` com a informação do comando inválido.

### Encaminhamento dos comandos

O processo de execução dos comandos é feito através da conjunção de dois 
componentes da aplicação:
- `Router`: lista os _handlers_ da aplicação que executam os comandos
- `Handlers`: responsáveis pela leitura e execução dos comandos

#### _Routing_ 

No processo de início da aplicação é feita a configuração de um objeto `Router`
que é preenchido com todos os _handlers_ disponíveis pela aplicação.

O preenchimento é feito através do método `addHandler(Method, Path, Handler)`
que recebe a indicação do método, caminho e uma nova instância do handler que 
trata desta configuração de comando.

Nota: os parâmetros (objecto `Parameter`) estão definidos no respetivo _handler_.

A utilização do método, permite definir numa lista todos os _handlers_ disponíveis
na aplicação e que podem ser utilizados. A forma de utilizar é através do outro
método disponível `findHandler(Command)`.

No método `findHandler` é feita a comparação do comando do utilizador com o 
`template` caso o mesmo seja confirmado é retornado uma instância de _handler_ para tratar e executar
o comando. Caso contrário é lançado uma exceção a referir que o comando inserido era inválido.

#### _Handlers_

Os _handlers_ são responsáveis por tratar e executar os comandos do utilizador.

Foi definido um contrato que indica que todos os _handlers_ têm o método com a
assinatura `public CommandResult execute(Command)` que é responsável por executar
o comando. Este contrato é definido com recurso à _interface_ `IHandler`.

Para servir de base aos _handlers_ é definida a classe abstrata `Handler`
que define o _template_, bem como o construtor-base dos _handlers_.

Depois é feito o _handler_ para cada comando aceite pela aplicação que extende 
a classe base `Handler` e implementa o interface `IHandler`.
Cada _handler_ tem a definição dos parâmetros que aceita e necessita de receber
para executar o pedido. 

A validação dos parâmetros é feita no início do método `execute` e com base nos 
parâmetros base. A validação é dos nomes dos parâmetros aceites no template, não 
sendo nesta fase validado o valor dos mesmos, sendo que são tratados os erros ou 
inconsistência através da execução dos _PrepareStatement_.

Nesta fase, o _handler_ tem o código que trata do acesso á base de dados, 
que em fase seguinte, será feito um _refactoring_ e movido esse código para 
a camada aplicacional correta, relacionada com o acesso e tratamento de acesso á BD.

##### Retorno dos resultados

Os _handlers_ retornam os resultados da execução do comando através de uma 
instância da classe `CommandResult`. Cada classe CommandResult implementa dois métodos
responsáveis por retornar uma String com o resultado em `html` ou `plaintext`.

A estrutura de código responsável por gerar programaticamente código HTML têm um 
design orientado a objetos e assemelha-se muito ao formato em árvore do html. 
Por exemplo, cada linha têm na sua representação um certo número de colunas.
E uma linha deriva de tabela, a qual por sua vez têm as representações das diversas 
linhas desse resultado. A estrutura é representada da seguinte forma:

HEAD 
* Title

BODY
* Bullets 
* Table
  * Row


### Acesso a dados

A aplicação tem prevista uma camada aplicacional para tratar dos dados e as 
interações com a BD, pelo que se desenvolveu a seguinte lógica:
*  `DataBaseConfig` - Carrega de variáveis ambiente e prepara a configuração da
ligação, inclusive a iniciação da `Data` que guarda a conexão á BD através da
`DataConnection`
*  `DataConnection` - Classe abstrata que simboliza o contrato que as classes 
de implementação respondem para estabelecer a ligação e manter a conexão á BD
   *  `PSQLDataConnection` - classe que criar uma ligação á BD através da DataSource
do _driver_ Postgresql
   *  `TestDataConnection` - classe que liga a uma BD de testes, baseada na implemetação 
da PSQLDataConnection (podia ser uma BD SQLite apenas para executar testes ou MSSQL)
*  `Data` - Classe que torna transparente a ligação á BD, permitindo ao utilizador
obter a conexão á BD quando necessário para fazer as queries _necessárias_.

Estas classes permitem estabelecer a ligação á base de dados sempre que 
necessário ser executado um comando. Para assegurar a atomicidade das transações,
optou-se por nas classes de extensão da `DataConnection` definir á partida que o 
_auto commit_ está desabilitado, sendo necessário em todas as transações fazer o 
_commit_ ou _rollback_. Essa tarefa acrescenta um custo em definir mais uma
linha de código, que seria desnecessária, na operação de _select_ mas sendo 
que pode ser necessário fazer várias operações de _select_ numa transação, 
garantimos que não existiu uma alteração que invalida os dados entre _selects_
 e/ou outras operações.

A definição deste modo para utilização da BD permite a evolução para a definição
da camada de dados de forma completamente separada da lógica de negócio definida
nos _handlers_ para tratar os comandos.

A evolução pode passar pela alteração da classe `Data` para a transformar numa classe 
abstrata que depois serve para definir as várias classes que realizam as operações
de interação com a BD, conforme o _handler_. Ou pode ser a criação de outras 
classes do estilo da _DataConnection_ que possam ser utilizadas para interagir 
com base de dados diferentes da atual ou, mesmo através da ligação a uma API externa.

### Acesso a dados

Após analisar os comandos a implementar nesta fase pudemos destacar dois pelo seu maior nível de complexidade em relação aos restantes, 
estes são `GET /movies/{mid}/ratings` e `GET /tops/rating`, no qual num é retornado a informação de um rating identificado pelo atributo _mid_ sendo esta informação 
a média de _ratings_ e a contagem de votos de cada valor e no segundo comando é retornado uma lista de filmes 
que seguem três condições, o número máximo de tuplos a apresentar, a ordem de apresentação dos filmes tendo em conta o valor da média de 
_ratings_, sendo previsto ordenar ascendentemente ou descendentemente e por último o número mínimo de votos por filme.

A solução para estes comandos foram solucionados e guardados juntamente com os restantes comandos no _[script consultQueries.sql](https://github.com/isel-leic-ls/2021-1-LI41N-G8/blob/master/src/main/sql/consultQueries.sql)_.

### Processamento de erros

No início de cada handler é feita a verificação de todos os parâmetros com o seu respetivo template. Esta verificação é controlada pelo método isValid da Class Parameters, que percorre parâmetro a parâmetro. Caso exista alguma inconsistência nos mesmos é retornado uma exceção (ParametersException) preenchida com uma String que assinala o local onde falhou o matching.

A tentativa de acesso à base de dados, e a execução do query é feita dentro de um bloco try catch. Desta forma, se existir algum incorreto funcionamento é executado o bloco catch que verifica se já tinha sido estabelecido a conexão. Se sim, é iniciado o rollback, o qual reverte uma transação explícita ou implícita. Apagando todas as alterações feitas à mesma. Posteriormente, lança uma exepção a detalhar o handler em que ocorreu a falha, e desliga a conexão à base dados.

Os diversos componentes em utilização nas camadas aplicacionais utilizam uma estratégia
de lançar exceções, regra geral personalizáveis, identificando o componente que a
lançou, juntamente com uma mensagem simples para o utilizador.

As exceções são lançadas, capturadas e apresentadas ao utilizador apenas na `AppConsole`,
que é a área da aplicação que é responsável pela interação com o utilizador.

Procura-se que a exceção informe o utilizador e não termine extemporaneamente a aplicação.

## Configuração

A aplicação quando é iniciada, instancia a `AppConfig` para realizar o _setup_ do componente
da [base de dados](https://github.com/isel-leic-ls/2021-1-LI41N-G8/wiki/Technical-report-of-phase-1/_edit#acesso-a-dados) e do [_routing_](https://github.com/isel-leic-ls/2021-1-LI41N-G8/wiki/Technical-report-of-phase-1/_edit#routing).

### Base de dados

A configuração da base de dados recorre á leitura de variáveis de ambiente para
obter a informação necessária para ligar á base de dados.

As variáveis de ambiente necessárias são:
*  LS_DB_HOST - guarda o endereço para a base de dados (URL)
*  LS_DB_PORT - porto onde o serviço de BD está á espera de ligação, opcional e por defeito 5432
*  LS_DB_DATABASE - nome da base de dados a usar
*  LS_DB_USER - utilizador para acesso á base de dados
*  LS_DB_PASSWD - palavra-passe para acesso á base de dados
*  LS_DB_TYPE - tipo de base de dados, opcional e por defeito Postegresl, disponível Teste

Com base nas variáveis de ambiente, é instanciada a classe correspondente que disponibiliza a conexão á base de dados.

### _Routing_

A configuração do `Router` da aplicação é feita utilizando o método `addHandler`. Para cada comando que a aplicação dá resposta (um _handler_ correspondente), existe uma entrada na classe `AppConfig` responsável por disponibilizar a possibilidade de executar esse comando.

A configuração é feita de acordo com o seguinte exemplo:

```java
this.router.addHandler(Method.POST, new Path("/users"), new CreateUserHandler());
```

Para novos _handlers_ a disponibilizar basta adicionar uma nova entrada, identificando
o método que deve ser utilizado, o caminho padrão e uma nova instância do _handler_ respetivo.

## Avaliação crítica

Não está a ser feita a validação do valor de _input_ para utilizar na definição do _Rating_. Na próxima fase, iremos adaptar validação deste valor, convertendo-o num tipo `Enum` para restringir e facilitar a validação. 

Na execução dos comandos no respetivo _handler_, o resultado da _query_ deverá ser filtrado corretamente. Ou seja, a ordem dos atributos a receber da base de dados deverá ser predefinida pelo utilizador. Assim, caso ocorra uma alteração no script de criação de uma tabela, a mesma continua a corresponder à ordem dos parâmetros no construtor do modelo correspondente.

Com o desenvolver do projeto, surgiram diversas classes de exceções pelo que 
devem ser consolidadas na próxima fase, simplificando o código e a gestão das
mesmas.

A validação dos parâmetros irá ser consolidada de forma a permitir uma validação mais forte
na passagem de parâmetros de forma a alertar o utilizador para erros nos parâmetros passados.

Nesta fase, os _handlers_ têm a lógica de acesso á base de dados, o que nos 
parece ser uma quebra no isolamento que pretendemos alcançar na aplicação.
Na fase seguinte pretendemos desenvolver a camada de acesso a dados (_Model_) 
criando as classes de mapeamento ou _data access objects_ para ser elas a fazerem
a interação com o modelo de dados e tornando transparente para o _handler_ como 
é feito o processamento dos dados, ficando só responsável por reunir a informação
necessária e dar a ordem de execução, bem como retornar os dados tratados.