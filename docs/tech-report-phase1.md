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

(_insert diagram_)

O desenvolvimento da aplicação aproxima-se da utilização do _design_ MVC, onde
temos o componente _Model_ onde se define a ligação á base de dados Postgresql bem
como a representação do _domain_ na aplicação.

A componente do _Controller_, que está refletida na existência do _Router_ e dos
diversos _Handlers_ que fazem a ponte entre o _input_ do utilizador, na forma de 
um comando, e o _output_ que vêm da base de dados.

Por fim, procurou-se criar a _View_ da aplicação numa classe que tem toda a 
interação com a consola, e que permite rápidamente mudar para uma outra view (_Web_)
devido ao seu encapsulamento. 

### Processamento de comandos

(_describe the command handling interface_)

(_describe any additional classes used internally by the command handlers_)

(_describe how command parameters are obtained and validated_)

### Encaminhamento dos comandos

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