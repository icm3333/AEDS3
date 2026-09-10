package controle;

import entidades.ArquivoPergunta;
import entidades.Pergunta;
import java.util.ArrayList;

public class ControlePergunta {

    private ArquivoPergunta arqPergunta;

    public ControlePergunta() throws Exception {
        arqPergunta = new ArquivoPergunta();
    }

    public int criar(int idUsuario, String textoPergunta, String palavrasChave) throws Exception {
        long agora = System.currentTimeMillis();
        Pergunta p = new Pergunta(-1, idUsuario, agora, agora, (short) 0,
                                  textoPergunta, palavrasChave, true);
        return arqPergunta.create(p);
    }

    public ArrayList<Pergunta> listarPorUsuario(int idUsuario) throws Exception {
        return arqPergunta.readAllByUsuario(idUsuario);
    }

    public boolean alterar(int idPergunta, String novoTexto, String novasPalavrasChave) throws Exception {
        // TODO: implementar
        throw new UnsupportedOperationException("alterar() não implementado.");
    }

    public boolean arquivar(int idPergunta, int idUsuarioAtivo) throws Exception {
        // TODO: implementar
        throw new UnsupportedOperationException("arquivar() não implementado.");
    }

    public void close() throws Exception {
        arqPergunta.close();
    }
}
