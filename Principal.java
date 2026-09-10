import controle.ControleUsuario;
import controle.ControlePergunta;
import visao.VisaoLogin;
import visao.VisaoMenuPrincipal;
import java.io.File;
import java.util.Scanner;

/**
 * Ponto de entrada do sistema Ajuda Aí 1.0.
 * Inicializa as pastas de dados, as camadas de controle e lança o menu de login.
 */
public class Principal {

    public static void main(String[] args) {

        // Garante que as pastas de dados existam
        new File("./dados/usuarios").mkdirs();
        new File("./dados/perguntas").mkdirs();

        Scanner scanner = new Scanner(System.in);
        ControleUsuario  controleUsuario  = null;
        ControlePergunta controlePergunta = null;

        try {
            controleUsuario  = new ControleUsuario();
            controlePergunta = new ControlePergunta();

            VisaoLogin visaoLogin = new VisaoLogin(scanner, controleUsuario);

            // Loop externo: o sistema roda até o usuário escolher sair
            while (true) {
                boolean logado = visaoLogin.exibir();

                if (!logado) {
                    // Usuário escolheu "Sair" na tela de login
                    System.out.println("Encerrando o sistema. Até mais!");
                    break;
                }

                // Usuário logado: exibe o menu principal
                new VisaoMenuPrincipal(scanner, controleUsuario, controlePergunta).exibir();
                // Após sair do menu principal, volta para a tela de login
            }

        } catch (Exception e) {
            System.err.println("Erro fatal ao iniciar o sistema: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Garante que os arquivos sejam fechados mesmo em caso de erro
            try { if (controleUsuario  != null) controleUsuario.close();  } catch (Exception ignored) {}
            try { if (controlePergunta != null) controlePergunta.close(); } catch (Exception ignored) {}
            scanner.close();
        }
    }
}
