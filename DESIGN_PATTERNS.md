# Projeto RA2 - Equipe 9 - Rodrigo Petters Sardagna e Lucas Schapiewski Moro

Este projeto é um sistema de gerenciamento para uma clínica veterinária, focado no agendamento e controle de consultas.

---

### **Documento de Análise de Padrões de Projeto – Sistema de Clínica Veterinária**

Este documento detalha os padrões de projeto de software identificados no código-fonte do sistema de gerenciamento de consultas veterinárias. Para cada padrão, explicamos por que ele foi utilizado, seus benefícios e os possíveis pontos negativos no contexto desta aplicação.

---

#### **1. Padrão State**

*   **Contexto de Uso:** O padrão State foi utilizado para gerenciar o ciclo de vida de uma `Consulta`. Uma consulta pode passar por diversos estados, como `Criada`, `Em Andamento`, `Finalizada`, `Cancelada` e `Reagendada`. As ações que podem ser executadas em uma consulta (como iniciar, finalizar, cancelar) dependem diretamente do seu estado atual. A interface `ConsultaState` define as operações, e classes concretas como `CriadaState`, `EmAndamentoState`, etc., implementam o comportamento específico para cada estado.

*   **Justificativa de Encaixe:** O gerenciamento do ciclo de vida de uma `Consulta` é um caso de uso clássico para o padrão State. Como as regras de negócio para o que pode ser feito com uma consulta (cancelar, finalizar, etc.) mudam drasticamente dependendo de seu estado atual, o padrão State organiza essa lógica de forma limpa e coesa. A alternativa, um método monolítico com `if-else` ou `switch` na classe `Consulta`, seria difícil de manter e estender.

*   **Benefícios:**
    *   **Organização do Código:** Evita o uso de grandes e complexas estruturas condicionais (`if/else` ou `switch`) na classe `Consulta` para verificar o estado atual antes de executar uma ação.
    *   **Coesão:** Agrupa o comportamento relacionado a um estado específico em sua própria classe, tornando o código mais limpo e coeso.
    *   **Extensibilidade:** Facilita a adição de novos estados no futuro. Para adicionar um estado "Aguardando Pagamento", por exemplo, bastaria criar uma nova classe `AguardandoPagamentoState` sem modificar as classes de estado existentes.

*   **Malefícios:**
    *   **Aumento do Número de Classes:** O padrão pode levar a um número elevado de classes caso o objeto tenha muitos estados, o que pode aumentar a complexidade geral do projeto.

---

#### **2. Padrão Strategy**

*   **Contexto de Uso:** O padrão Strategy é empregado no sistema de pagamento. A aplicação precisa lidar com múltiplas formas de pagamento (`Cartão`, `Pix`, `Dinheiro`). A interface `PagamentoStrategy` define um método comum (`pagar`), e as classes `PagamentoCartao`, `PagamentoPix` e `PagamentoDinheiro` fornecem as implementações concretas. O `ConsultaController` seleciona a estratégia de pagamento em tempo de execução com base na escolha do usuário.

*   **Justificativa de Encaixe:** O sistema precisava de flexibilidade para processar diferentes formas de pagamento, e a escolha do método é feita em tempo de execução. O padrão Strategy foi ideal porque isolou a lógica de cada método de pagamento em sua própria classe, desacoplando o `ConsultaController` dos detalhes de implementação. Isso torna o sistema aderente ao Princípio Aberto/Fechado, pois novos métodos de pagamento podem ser adicionados sem alterar o código existente.

*   **Benefícios:**
    *   **Flexibilidade e Desacoplamento:** Permite que o algoritmo de pagamento seja selecionado dinamicamente. A classe `Consulta` não precisa conhecer os detalhes de cada método de pagamento, apenas interagir com a interface `PagamentoStrategy`.
    *   **Princípio Aberto/Fechado:** Facilita a adição de novas formas de pagamento (ex: Boleto) sem a necessidade de alterar o código do `ConsultaController` ou da classe `Consulta`. Basta criar uma nova classe que implemente `PagamentoStrategy`.

*   **Malefícios:**
    *   **Complexidade Adicional:** Para um número muito pequeno e fixo de estratégias, o padrão pode parecer um exagero, introduzindo mais classes e interfaces do que uma simples estrutura condicional.
    *   **Comunicação entre Estratégia e Contexto:** O cliente (neste caso, o `ConsultaController`) precisa ter conhecimento das diferentes estratégias disponíveis para poder escolher uma.

---

#### **3. Padrão Singleton**

*   **Contexto de Uso:** O padrão Singleton é utilizado nas classes `DataManager` (ex: `ConsultaDataManager.getInstance()`). Ele garante que exista apenas uma única instância dessas classes em toda a aplicação.

*   **Justificativa de Encaixe:** Para garantir a consistência e a integridade dos dados, era crucial que houvesse apenas um ponto de acesso e manipulação dos arquivos de persistência (`.dat`). O padrão Singleton foi a escolha natural para as classes `DataManager`, pois ele impõe a existência de uma única instância, evitando conflitos de acesso e garantindo que todas as partes do sistema trabalhem com a mesma fonte de dados.

*   **Benefícios:**
    *   **Ponto de Acesso Global:** Fornece um ponto de acesso único e global para a fonte de dados, evitando a criação de múltiplos gerenciadores que poderiam competir pelo acesso ao mesmo arquivo (`.dat`), causando inconsistências.
    *   **Gerenciamento de Estado Único:** Garante que o estado do gerenciador de dados seja consistente em toda a aplicação.

*   **Malefícios:**
    *   **Dificulta Testes Unitários:** O estado global introduzido por um Singleton pode tornar os testes de unidade mais difíceis, pois os testes podem interferir uns nos outros através da instância única.
    *   **Oculta Dependências:** Classes que usam o Singleton não declaram explicitamente suas dependências, o que pode dificultar o entendimento do acoplamento no sistema.

---

#### **4. Padrão Command**

*   **Contexto de Uso:** O padrão Command é usado para encapsular as ações dos botões da interface gráfica (Inserir, Atualizar, Excluir) no `ConsultaController`. Cada ação é implementada como uma classe de Comando separada (ex: `InserirConsultaCommand`, `AtualizarConsultaCommand`), que implementa a interface `Command`. O botão (invocador) é configurado para executar um objeto de comando, que por sua vez chama a ação correspondente no `ConsultaController` (receptor).

*   **Justificativa de Encaixe:** A interação do usuário com a interface gráfica (clicar em botões de "Inserir", "Excluir") precisava ser desacoplada da lógica de negócio que executa essas operações. O padrão Command encapsula cada solicitação como um objeto, permitindo que a UI (invocador) não conheça os detalhes do receptor (`ConsultaController`). Isso não apenas limpa o código, mas também abre caminho para futuras extensões, como adicionar funcionalidades de log de ações ou um sistema de desfazer/refazer.

*   **Benefícios:**
    *   **Desacoplamento Forte:** A refatoração para classes separadas tornou o desacoplamento ainda mais explícito. O "invocador" (o botão) é completamente independente do "receptor" (o `ConsultaController`). O botão apenas executa o método `execute()` de qualquer objeto que implemente `Command`.
    *   **Coesão e Reutilização:** Cada comando é agora uma classe coesa e autocontida, facilitando o entendimento e a manutenção. Esses objetos de comando poderiam ser reutilizados em diferentes contextos (por exemplo, menus de contexto ou atalhos de teclado).
    *   **Extensibilidade:** Facilita a adição de novas ações. Também abre portas para funcionalidades como enfileirar comandos, registrar ações (logging) ou implementar operações de "desfazer" (undo).

*   **Malefícios:**
    *   **Aumento do Número de Classes:** A principal desvantagem, agora mais evidente, é que cada nova ação requer a criação de uma nova classe. Para ações muito simples, isso pode parecer um excesso de código (boilerplate), mas é uma troca consciente em favor de um design mais flexível e desacoplado.
