package visao;

import controle.ControleUsuario;
import controle.ControlePergunta;
import java.util.Scanner;

public class VisaoMenuPrincipal {

    private final Scanner          scanner;
    private final ControleUsuario  controleUsuario;
    private final ControlePergunta controlePergunta;

    public VisaoMenuPrincipal(Scanner scanner, ControleUsuario controleUsuario,
                              ControlePergunta controlePergunta) {
        this.scanner          = scanner;
        this.controleUsuario  = controleUsuario;
        this.controlePergunta = controlePergunta;
    }

    public void exibir() {
        while (true) {
            System.out.println("\n============================");
            System.out.println("      AJUDA AÍ 1.0");
            System.out.println("============================");
            System.out.println("> Início");
            System.out.println("(A) Minha área");
            System.out.println("(B) Buscar perguntas  [Em breve]");
            System.out.println("(S) Sair");
            System.out.print("Opção: ");

            String opcao = scanner.nextLine().trim().toUpperCase();

            switch (opcao) {
                case "A" -> new VisaoMinhaArea(scanner, controleUsuario, controlePergunta).exibir();
                case "B" -> System.out.println("[Em desenvolvimento] Busca de perguntas estará disponível no TP2.");
                case "S" -> { controleUsuario.logout(); return; }
                default  -> System.out.println("Opção inválida.");
            }
        }
    }
}
