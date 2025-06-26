# 🏰 Sistema de Gerenciamento do Mundo Mágico de Harry Potter

<div align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
  <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/license-MIT-green?style=for-the-badge">
</div>

## 🌟 Funcionalidades Principais

### 🧙‍♂️ Gerenciamento de Bruxos
- **Cadastro completo**: Nome, idade, casa e varinha associada
- **Consulta detalhada**: Busca por ID ou listagem completa
- **Atualização flexível**: Campos individuais podem ser modificados
- **Exclusão segura**: Verificação de dependências antes de remover
- **Relacionamentos**: Integração automática com casas e varinhas

### 🏰 Gerenciamento de Casas de Hogwarts
- **Cadastro**: Todos os atributos das casas (nome, fundador, cores, mascote, fantasma)
- **Consulta**: Listagem completa ou por ID específico
- **Proteção de dados**: Impede exclusão de casas com bruxos associados
- **Relatórios**: Lista de bruxos por casa

### ✨ Gerenciamento de Varinhas
- **Especificações técnicas**: Madeira, núcleo, comprimento e flexibilidade
- **Associação**: Vinculação automática com bruxos
- **Validação**: Impede exclusão de varinhas em uso

### 🔮 Gerenciamento de Feitiços
- **Catálogo completo**: Nome, efeito e nível de dificuldade
- **Relação com bruxos**: Registro de quais bruxos conhecem cada feitiço
- **Níveis de perícia**: Diferentes níveis de domínio por bruxo

### 🐉 Gerenciamento de Criaturas Mágicas
- **Cadastro**: Nome, nível de periculosidade e habitat natural
- **Interações**: Registro de encontros com bruxos
- **Status**: Detalhes sobre a natureza do encontro

### 📊 Relatórios Avançados
| Relatório | Descrição |
|-----------|-----------|
| **Bruxos Completos** | Visão integrada com todas as informações do bruxo (casa, varinha) |
| **Bruxos e Feitiços** | Lista quais feitiços cada bruxo conhece e seu nível de perícia |
| **Interações com Criaturas** | Histórico de encontros entre bruxos e criaturas mágicas |
| **Feitiços Mais Comuns** | Ranking dos feitiços mais conhecidos pelos bruxos |
| **Criaturas por Periculosidade** | Agrupamento inteligente por nível de risco |

## 🛠 Estrutura Técnica

```mermaid
graph TD
    A[Main] --> B[Menu Principal]
    B --> C[Gerenciar Bruxos]
    B --> D[Gerenciar Casas]
    B --> E[Gerenciar Varinhas]
    B --> F[Gerenciar Feitiços]
    B --> G[Gerenciar Criaturas]
    B --> H[Relatórios]
    
    C --> C1[BruxoDAO]
    D --> D1[CasaDAO]
    E --> E1[VarinhaDAO]
    F --> F1[FeiticoDAO]
    G --> G1[CriaturaMagicaDAO]
    H --> H1[Consultas SQL Avançadas]
