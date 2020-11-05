# Relatório técnico da Fase 1

## Introdução

Este documento contém os aspectos relevantes do desenho e implementação da fase 1 do projecto de LS.

## Modelação da base de dados

### Modelação conceptual ###

O seguinte diagrama apresenta a modelo entidade-associação para a informação gerida pelo sistema. 

(_include an image or a link to the conceptual diagram_)

Destacam-se os seguintes aspectos deste modelo:

* (_include a list of relevant design issues_)

O modelo conceptual apresenta ainda as seguintes restrições:

* (_include a list of relevant design issues_)
    
### Modelação física ###

O modelo físico da base de dados está presente em (_link to the SQL script with the schema definition_).

Destacam-se os seguintes aspectos deste modelo:

* (_include a list of relevant design issues_)

## Organização do software

A aplicação está dividida em várias camadas, de modo a permitir uma melhor 
gestão dos componentes e uma separação das responsabilidades de cada camada.

![](images/LayerBlock.png)

O desenvolvimento da aplicação aproxima-se da utilização do _design_ MVC, onde
temos o componente _Model_, compreendendo a configuração e instânciação da 
ligação á base de dados Postgresql, bem como introdução de classes representando
o _domain_ da aplicação.

A componente do _Controller_, que está refletida na existência do _Router_ e dos
diversos _Handlers_ que fazem a ponte entre o _input_ do utilizador, na forma de 
um comando, e o _output_ que vêm da base de dados.

Por fim, procurou-se criar a _View_ da aplicação numa classe que tem toda a 
interação com a consola, e que permite rapidamente mudar para outra _view_ (_Web_)
devido ao seu encapsulamento. 

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
*  método `matches(Object)`: na `Path` e `Parameters`, porque queremos que siga a
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

Nota: que os parâmetros (objecto `Parameter`) está definido no _handler_.

A utilização do método, permite definir numa lista todos os _handlers_ disponíveis
na aplicação e que podem ser utilizados. A forma de utilizar é através do outro
método disponível `findHandler(Command)`.

No método `findHandler` é feita a comparação do comando do utilizador com o 
`template` e dessa forma retornar a instância de _handler_ para tratar e executar
o comando.

#### _Handlers_

Os _handlers_ são responsáveis por tratar e executar os comandos do utilizador.

Foi definido um contrato que indica que todos os _handlers_ têm o método com a
assinatura `public CommandResult execute(Command)` que é responsável por executar
o comando. Este contrato é definido com recurso à _interface_ `IHandler`.

Para servir de base aos _handlers_ é definida a classe abstrata `Handler`
que define o _template_, bem como o construtor-base dos _handlers_.

Depois é feito o _handler_ para cada comando aceite pela aplicação que extende 
a classe base `Handler` e implementa o interface `IHandler`.

(_describe how the router works and how path parameters are extracted_)

### Gestão de ligações

(_describe how connections are created, used and disposed_, namely its relation with transaction scopes).

### Acesso a dados

(_describe any created classes to help on data access_).

(_identify any non-trivial used SQL statements_).

### Processamento de erros

(_describe how errors are handled and communicated to the application user_).

## Avaliação crítica

(_enumerate the functionality that is not concluded and the identified defects_)

(_identify improvements to be made on the next phase_)