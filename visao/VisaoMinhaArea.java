package visao;

import controle.ControleUsuario;
import controle.ControlePergunta;
import java.util.Scanner;

/**
 * Tela "Minha Área".
 *
 * =========================================================================
 * IMPLEMENTADO:
 *  - Estrutura do menu
 *
 * TODO para o grupo:
 *  - Implementar VisaoMeusDados e VisaoPerguntas e conectá-las aqui
 * =========================================================================
 */
public class VisaoMinhaArea {

    private final Scanner          scanner;
    private final ControleUsuario  controleUsuario;
    private final ControlePergunta controlePergunta;

    public VisaoMinhaArea(Scanner scanner, ControleUsuario controleUsuario,
                          ControlePergunta controlePergunta) {
        this.scanner          = scanner;
        this.controleUsuario  = controleUsuario;
        this.controlePergunta = controlePergunta;
    }

    // =========================================================================

    public void exibir() {
        while (true) {
            System.out.println("\n============================");
            System.out.println("      AJUDA AÍ 1.0");
            System.out.println("============================");
            System.out.println("> Início > Minha área");
            System.out.println("Olá, " + controleUsuario.getUsuarioAtivo().getNome() + "!");
            System.out.println("(A) Meus dados");
            System.out.println("(B) Minhas perguntas");
            System.out.println("(C) Minhas respostas    [Em breve]");
            System.out.println("(D) Meus votos          [Em breve]");
            System.out.println("(R) Retornar");
            System.out.print("Opção: ");

            String opcao = scanner.nextLine().trim().toUpperCase();

            switch (opcao) {
                case "A" -> new VisaoMeusDados(scanner, controleUsuario).exibir();
                case "B" -> new VisaoPerguntas(scanner, controleUsuario, controlePergunta).exibir();
                case "C", "D" -> System.out.println("[Em desenvolvimento] Disponível no TP2.");
                case "R" -> { return; }
                default  -> System.out.println("Opção inválida.");
            }
        }
    }
}
