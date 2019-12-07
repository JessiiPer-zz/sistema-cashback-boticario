# Sistema de cashback para revendedores O Boticário

A oportunidade proposta é criar um sistema de Cashback, onde o valor será disponibilizado como crédito para a próxima compra da revendedora no Boticário.

Cashback quer dizer “dinheiro de volta”, e funciona de forma simples: o revendedor faz uma compra e seu benefício vem com a devolução de parte do dinheiro gasto.
Sendo assim o Boticário quer disponibilizar um sistema para seus revendedores(as) cadastrarem suas compras e acompanhar o retorno de cashback de cada um.

# Indíce

* [Requisitos Não Funcionais](#requisitos-não-funcionais)
* [Requisitos Funcionais](#requisitos-funcionais)
* [Guia de instalação](#guia-de-instalação)
* [Autoria](#autoria)

## Requisitos Não Funcionais
1. Linguagem de programação Java - Versão 11
2. Autenticação JWT
3. Aplicação em Springboot com requisições REST
4. IDE Spring Tool Suite 4

## Requisitos Funcionais

### Requisitos back-end:
* Rota para cadastrar um novo revendedor(a) exigindo no mínimo nome completo, CPF, e-mail e senha;
* Rota para validar um login de um revendedor(a);
* Rota para cadastrar uma nova compra exigindo no mínimo código, valor, data e CPF do revendedor(a). Todos os cadastros são salvos com o status “Em validação” exceto quando o CPF do revendedor(a) for 153.509.460-56, neste caso o status é salvo como “Aprovado”;
* Rota para editar uma compra, permitir editar apenas se o status da venda for “Em validação”;
* Rota para excluir uma compra, permitir exclusão apenas se o status da venda for “Em validação”;
* Rota para listar as compras cadastradas retornando código, valor, data, % de cashback aplicado para esta compra, valor de cashback para esta compra e status;
* Rota para exibir o acumulado de cashback até o momento, essa rota irá consumir essa informação de uma API externa disponibilizada pelo Boticário.

## Diagrama de Entidade - Relacionamento
![DER](https://github.com/JessiiPer/sistema-cashback-boticario/blob/master/src/main/resources/documentos/DER.PNG)

## Guia de instalação
Para o sistema de cashback funcionar é necessário/aconselhável possuir:
* Mysql Workbench 5.7 (Usuário: root, senha: p@ssword, porta padrão: 3306)
* JDK 11
* Postman (Existe uma collection com todos os requests na pasta resources>documentos>collection-postaman para auxiliar nos testes)

## Autoria
* Jessica Pereira Rocha
