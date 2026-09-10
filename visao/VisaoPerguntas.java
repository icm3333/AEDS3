package visao;

import controle.ControleUsuario;
import controle.ControlePergunta;
import entidades.Pergunta;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class VisaoPerguntas {

    private final Scanner          scanner;
    private final ControleUsuario  controleUsuario;
    private final ControlePergunta controlePergunta;

    private static final DateTimeFormatter FMT =
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public VisaoPerguntas(Scanner scanner, ControleUsuario controleUsuario,
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
            System.out.println("> Início > Minha área > Minhas perguntas");
            System.out.println("(A) Listar");
            System.out.println("(B) Incluir");
            System.out.println("(C) Alterar");
            System.out.println("(D) Arquivar");
            System.out.println("(R) Retornar");
            System.out.print("Opção: ");

            String opcao = scanner.nextLine().trim().toUpperCase();

            switch (opcao) {
                case "A" -> listar();
                case "B" -> incluir();
                case "C" -> alterar();
                case "D" -> arquivar();
                case "R" -> { return; }
                default  -> System.out.println("Opção inválida.");
            }
        }
    }

    // retorna a lista para ser reutilizada em alterar/arquivar
    private ArrayList<Pergunta> listar() {
        int idUsuario = controleUsuario.getUsuarioAtivo().getId();
        try {
            ArrayList<Pergunta> perguntas = controlePergunta.listarPorUsuario(idUsuario);

            System.out.println("\nMINHAS PERGUNTAS");
            System.out.println("----------------------------");

            if (perguntas.isEmpty()) {
                System.out.println("Você ainda não possui perguntas.");
            } else {
                for (int i = 0; i < perguntas.size(); i++) {
                    Pergunta p = perguntas.get(i);
                    String data = LocalDateTime
                        .ofInstant(Instant.ofEpochMilli(p.getCriacao()), ZoneId.systemDefault())
                        .format(FMT);

                    System.out.println("(" + (i + 1) + ")" + (p.isAtiva() ? "" : " ARQUIVADA"));
                    System.out.println(data);
                    System.out.println(p.getPergunta());
                    System.out.println("Palavras-chave: " + p.getPalavrasChave());
                    System.out.println();
                }
            }

            System.out.println("Pressione ENTER para continuar...");
            scanner.nextLine();
            return perguntas;

        } catch (Exception e) {
            System.out.println("Erro ao listar perguntas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void incluir() {
        System.out.println("\n--- NOVA PERGUNTA ---");
        System.out.print("Pergunta: ");
        String texto = scanner.nextLine().trim();
        System.out.print("Palavras-chave (separe por ;): ");
        String palavras = scanner.nextLine().trim();

        try {
            int id = controlePergunta.criar(
                controleUsuario.getUsuarioAtivo().getId(), texto, palavras);
            System.out.println("Pergunta incluída com sucesso! ID interno: " + id);
        } catch (Exception e) {
            System.out.println("Erro ao incluir pergunta: " + e.getMessage());
        }
    }

    private void alterar() {
        // TODO: implementar
        System.out.println("[Em desenvolvimento] Alteração de perguntas.");
    }

    private void arquivar() {
        // TODO: implementar
        System.out.println("[Em desenvolvimento] Arquivamento de perguntas.");
    }
}
