import controle.ControleUsuario;
import controle.ControlePergunta;
import visao.VisaoLogin;
import visao.VisaoMenuPrincipal;
import java.io.File;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        new File("./dados/usuarios").mkdirs();
        new File("./dados/perguntas").mkdirs();

        Scanner scanner = new Scanner(System.in);
        ControleUsuario  controleUsuario  = null;
        ControlePergunta controlePergunta = null;

        try {
            controleUsuario  = new ControleUsuario();
            controlePergunta = new ControlePergunta();

            VisaoLogin visaoLogin = new VisaoLogin(scanner, controleUsuario);

            while (true) {
                boolean logado = visaoLogin.exibir();

                if (!logado) {
                    System.out.println("Encerrando o sistema. Até mais!");
                    break;
                }

                new VisaoMenuPrincipal(scanner, controleUsuario, controlePergunta).exibir();
            }

        } catch (Exception e) {
            System.err.println("Erro fatal ao iniciar o sistema: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // garante que os arquivos sejam fechados mesmo em caso de erro
            try { if (controleUsuario  != null) controleUsuario.close();  } catch (Exception ignored) {}
            try { if (controlePergunta != null) controlePergunta.close(); } catch (Exception ignored) {}
            scanner.close();
        }
    }
}
