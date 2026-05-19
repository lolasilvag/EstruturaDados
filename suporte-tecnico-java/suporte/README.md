# Simulador de Suporte Técnico de Informática
**Estrutura de Dados em Java**

## Como compilar e executar

### Pré-requisitos
- Java 17 ou superior instalado
- Terminal / Prompt de Comando

### Passo a passo

1. Extraia o projeto em uma pasta
2. Abra o terminal na pasta raiz do projeto (onde está esta README)
3. Compile todos os arquivos:

```bash
javac -d out src/suporte/modelo/*.java src/suporte/estruturas/*.java src/suporte/sistema/*.java src/suporte/Main.java
```

4. Execute:

```bash
java -cp out suporte.Main
```

---

## Estruturas de Dados utilizadas

| Estrutura       | Onde é usada                                    |
|-----------------|--------------------------------------------------|
| **Fila (FIFO)** | Organiza os chamados aguardando atendimento      |
| **Pilha (LIFO)**| Registra o histórico de chamados concluídos      |
| **Lista Ligada**| Armazena os usuários/solicitantes cadastrados    |
| **Árvore BST**  | Busca e organiza chamados por ID (O log n)       |

---

## Funcionalidades

- Abrir, atender e concluir chamados técnicos
- Fila de espera com ordem FIFO
- Histórico de atendimentos em pilha (LIFO)
- Cadastro de usuários em lista ligada
- Busca de chamados por ID na árvore binária
- Listagem por prioridade (ALTA / MEDIA / BAIXA)

---

## Estrutura do projeto

```
src/
└── suporte/
    ├── Main.java
    ├── modelo/
    │   ├── Chamado.java
    │   └── Usuario.java
    ├── estruturas/
    │   ├── FilaChamados.java
    │   ├── PilhaHistorico.java
    │   ├── ListaUsuarios.java
    │   └── ArvoreChamados.java
    └── sistema/
        └── SistemaSuporteMenu.java
```
