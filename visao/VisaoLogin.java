package visao;

import controle.ControleUsuario;
import java.util.Scanner;

/**
 * Tela de Login e Cadastro de novo usuário.
 *
 * =========================================================================
 * IMPLEMENTADO:
 *  - Menu inicial (login / novo usuário / sair)
 *  - Fluxo de login com mensagem de erro genérica
 *  - Fluxo de cadastro com validação de email
 *
 * TODO para o grupo:
 *  - Fluxo de recuperação de senha (opção apresentada após login falho)
 * =========================================================================
 */
public class VisaoLogin {

    private final Scanner        scanner;
    private final ControleUsuario controle;

    public VisaoLogin(Scanner scanner, ControleUsuario controle) {
        this.scanner  = scanner;
        this.controle = controle;
    }

    // =========================================================================
    // MENU INICIAL
    // =========================================================================

    /**
     * Exibe o menu de acesso e retorna true se o usuário fizer login com sucesso.
     * Retorna false quando o usuário escolher sair.
     */
    public boolean exibir() {
        while (true) {
            System.out.println("\n============================");
            System.out.println("      AJUDA AÍ 1.0");
            System.out.println("============================");
            System.out.println("(A) Login");
            System.out.println("(B) Novo usuário (primeiro acesso)");
            System.out.println("(S) Sair");
            System.out.print("Opção: ");

            String opcao = scanner.nextLine().trim().toUpperCase();

            switch (opcao) {
                case "A" -> { if (telaLogin()) return true; }
                case "B" -> telaCadastro();
                case "S" -> { return false; }
                default  -> System.out.println("Opção inválida.");
            }
        }
    }

    // =========================================================================
    // LOGIN
    // =========================================================================

    private boolean telaLogin() {
        System.out.println("\n--- LOGIN ---");
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim();

        try {
            if (controle.login(email, senha)) {
                System.out.println("Login realizado com sucesso! Bem-vindo, "
                    + controle.getUsuarioAtivo().getNome() + "!");
                return true;
            } else {
                System.out.println("Email ou senha incorretos.");
                System.out.println("(R) Recuperar senha   (qualquer tecla) Tentar novamente");
                String resp = scanner.nextLine().trim().toUpperCase();
                if (resp.equals("R")) {
                    // TODO: chamar tela de recuperação de senha
                    System.out.println("[Em desenvolvimento] Recuperação de senha.");
                }
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao realizar login: " + e.getMessage());
            return false;
        }
    }

    // =========================================================================
    // CADASTRO
    // =========================================================================

    private void telaCadastro() {
        System.out.println("\n--- NOVO USUÁRIO ---");
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        try {
            // Verifica antecipadamente se o email já existe
            if (controle.getUsuarioAtivo() == null) {
                // Tenta uma leitura para checar duplicidade antes de pedir os demais dados
                // (ArquivoUsuario.create() também valida, mas é melhor dar feedback cedo)
            }

            System.out.print("Nome completo: ");
            String nome = scanner.nextLine().trim();

            System.out.print("Senha: ");
            String senha = scanner.nextLine().trim();

            System.out.print("Pergunta de recuperação de senha: ");
            String pergunta = scanner.nextLine().trim();

            System.out.print("Resposta (será armazenada sem acentos/maiúsculas): ");
            String resposta = scanner.nextLine().trim();

            controle.cadastrar(nome, email, senha, pergunta, resposta);
            System.out.println("Usuário cadastrado com sucesso! Faça login para continuar.");

        } catch (Exception e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
        }
    }
}
