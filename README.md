# Ajuda Aí 1.0 — TP1 AEDS III

Sistema de perguntas e respostas inspirado no StackOverflow, desenvolvido como trabalho prático da disciplina **Algoritmos e Estruturas de Dados III** — PUC Minas.

## 👥 Grupo

| Nome | GitHub |
|---|---|
| (Membro 1) | |
| (Membro 2) | |
| (Membro 3) | |
| (Membro 4) | |

---

## 📁 Estrutura do Projeto

```
TP1-AEDS3/
│
├── aed3/                  ← Código base do professor (não modificar)
│   ├── Arquivo.java
│   ├── ArvoreBMais.java
│   ├── HashExtensivel.java
│   └── ...
│
├── entidades/             ← Entidades e seus CRUDs
│   ├── Usuario.java
│   ├── Pergunta.java
│   ├── ParEmailId.java
│   ├── ArquivoUsuario.java
│   └── ArquivoPergunta.java
│
├── controle/              ← Lógica de negócio (sem prints de tela)
│   ├── ControleUsuario.java
│   └── ControlePergunta.java
│
├── visao/                 ← Telas do terminal (MVC — View)
│   ├── VisaoLogin.java
│   ├── VisaoMenuPrincipal.java
│   ├── VisaoMinhaArea.java
│   ├── VisaoMeusDados.java
│   └── VisaoPerguntas.java
│
└── Principal.java         ← main()
```

---

## ▶️ Como compilar e executar

```bash
# Compilar todos os arquivos Java a partir da raiz do projeto
javac -encoding UTF-8 aed3/*.java entidades/*.java controle/*.java visao/*.java Principal.java

# Executar
java Principal
```

---

## ✅ O que está implementado

| Funcionalidade | Status |
|---|---|
| Cadastro de novo usuário | ✅ |
| Login com email e senha | ✅ |
| Índice Hash Extensível por email | ✅ |
| Criar nova pergunta | ✅ |
| Listar perguntas do usuário | ✅ |
| Árvore B+ com relação usuário→perguntas | ✅ |
| Alterar dados do usuário | 🔧 Em desenvolvimento |
| Recuperação de senha | 🔧 Em desenvolvimento |
| Alterar pergunta | 🔧 Em desenvolvimento |
| Arquivar pergunta | 🔧 Em desenvolvimento |
| Exclusão em cascata ao deletar usuário | 🔧 Em desenvolvimento |

---

## 🔧 TODOs abertos para o grupo

Busque por `// TODO` no código para encontrar todos os pontos a implementar:

- **`ControleUsuario.java`**: `alterarNome()`, `alterarEmail()`, `alterarSenha()`, `alterarPerguntaSecreta()`, `validarRespostaSecreta()`
- **`ControlePergunta.java`**: `alterar()`, `arquivar()`
- **`ArquivoUsuario.java`**: exclusão em cascata no `delete()`
- **`ArquivoPergunta.java`**: `deleteAllByUsuario()`
- **`VisaoMeusDados.java`**: conectar cada opção ao controle
- **`VisaoPerguntas.java`**: `alterar()` e `arquivar()` (fluxo de tela)
- **`VisaoLogin.java`**: tela de recuperação de senha

---

## 📋 Checklist do Enunciado

- [ ] Há um CRUD de usuários com Tabela Hash Extensível (índice por email)?
- [ ] Há um CRUD de perguntas com Árvore B+ (relacionamento 1:N)?
- [ ] As perguntas estão vinculadas aos usuários via `idUsuario`?
- [ ] Há uma Árvore B+ com o par `(idUsuario, idPergunta)`?
- [ ] O trabalho compila corretamente?
- [ ] O trabalho está completo e funcionando sem erros?
- [ ] O trabalho é original?

---

## 🏗️ Decisões de Arquitetura

- **Padrão MVC**: separação clara entre `entidades/` (Model), `visao/` (View) e `controle/` (Controller).
- **Hash de senha**: usamos `Math.abs(String.hashCode())`, seguindo o padrão adotado pelo professor no código base.
- **Resposta secreta**: normalizada (sem acentos, minúsculas) antes do hash, conforme o enunciado.
- **Arquivamento**: perguntas nunca são excluídas fisicamente — apenas o atributo `ativa` é alterado para `false`.
- **Código base (`aed3/`)**: os arquivos do professor **não foram modificados**.
