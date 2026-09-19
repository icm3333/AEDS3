# Ajuda Aí 1.0 — TP1 AEDS III

Sistema de perguntas e respostas inspirado no StackOverflow.  
Disciplina: Algoritmos e Estruturas de Dados III — PUC Minas.

## Grupo

| Nome                       | GitHub  |
|----------------------------|---------|
| Arthur De Pinho De Almeida | imartzz |
| Rafael Cardoso Machado     | icm3333 |
| (Membro 3)                 |         |
| (Membro 4)                 |         |

---

## Como compilar e executar

```bash
# Compilar
javac -encoding UTF-8 aed3/*.java entidades/*.java controle/*.java visao/*.java Principal.java

# Executar
java Principal
```

---

## Estrutura do projeto

```
TP1-AEDS3/
├── aed3/              → código base do professor (não modificar)
├── entidades/         → entidades e CRUDs
├── controle/          → lógica de negócio
├── visao/             → telas do terminal
└── Principal.java     → ponto de entrada
```

---

## O que está pronto

- Cadastro de novo usuário
- Login com email e senha
- Índice Hash Extensível por email (busca direta sem varredura)
- Estrutura de menus completa (login, menu principal, minha área)
- Criar nova pergunta vinculada ao usuário logado
- Listar perguntas do usuário via Árvore B+
- Alterar nome, email, senha e pergunta de recuperação do usuário
- Alterar texto e palavras-chave de uma pergunta
- Arquivar pergunta (exclusão lógica)
- Exclusão em cascata das perguntas ao deletar um usuário

---

## O que ainda precisa ser implementado

- Tela de recuperação de senha via pergunta secreta (`VisaoLogin`)

---

## Checklist do enunciado

- [x] CRUD de usuários com Hash Extensível por email
- [x] CRUD de perguntas com Árvore B+
- [x] Perguntas vinculadas ao usuário via `idUsuario`
- [x] Árvore B+ com par `(idUsuario, idPergunta)`
- [x] Compila sem erros
- [ ] Funciona sem erros de execução
- [x] Trabalho original
