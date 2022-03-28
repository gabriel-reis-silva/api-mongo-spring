# Como cadastrar um Benefício

Todos os modelos e exemplos de cadastro de Funcionários, Clientes e Benefícios estão no swagger, porém para criar um Benefício, devemos nos atentar que, dentro do campo “camposFuncionario”,

devemos passar uma lista de Integer com os valores de cada campo necessário para aquele tipo de benefício. O valor dos campos são esses:

| Nome | 1 |
| --- | --- |
| CPF | 2 |
| Data Admissão | 3 |
| E-mail | 4 |
| Endereço | 5 |
| Peso | 6 |
| Altura | 7 |
| Horas Meditadas | 8 |

Então, o benefício que tenha os campos Nome e CPF, ao ser inserido deve ficar como nesse exemplo:

```json
{"nome":"Norte Europa","camposFuncionario":[1,2]}
```