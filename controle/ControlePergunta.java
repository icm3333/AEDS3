package controle;

import entidades.ArquivoPergunta;
import entidades.Pergunta;
import java.util.ArrayList;

/**
 * Camada de controle das perguntas.
 * Contém toda a lógica de negócio relacionada a perguntas (sem prints de tela).
 *
 * =========================================================================
 * IMPLEMENTADO:
 *  - criar()
 *  - listarPorUsuario()
 *
 * TODO para o grupo:
 *  - alterar()
 *  - arquivar()
 * =========================================================================
 */
public class ControlePergunta {

    private ArquivoPergunta arqPergunta;

    // =========================================================================
    // Construtor
    // =========================================================================

    public ControlePergunta() throws Exception {
        arqPergunta = new ArquivoPergunta();
    }

    // =========================================================================
    // CRIAR
    // =========================================================================

    /**
     * Cria uma nova pergunta vinculada ao usuário informado.
     * Timestamps de criação e alteração são definidos automaticamente.
     * @return O id da pergunta criada.
     */
    public int criar(int idUsuario, String textoPergunta, String palavrasChave) throws Exception {
        long agora = System.currentTimeMillis();
        Pergunta p = new Pergunta(-1, idUsuario, agora, agora, (short) 0,
                                  textoPergunta, palavrasChave, true);
        return arqPergunta.create(p);
    }

    // =========================================================================
    // LISTAR POR USUÁRIO
    // =========================================================================

    /**
     * Retorna todas as perguntas de um usuário (ativas e arquivadas).
     * Usa a Árvore B+ — não faz varredura sequencial no arquivo inteiro.
     */
    public ArrayList<Pergunta> listarPorUsuario(int idUsuario) throws Exception {
        return arqPergunta.readAllByUsuario(idUsuario);
    }

    // =========================================================================
    // ALTERAR
    //
    // TODO: Implementar este método.
    // Lógica esperada:
    //   1. Buscar a pergunta pelo id (para garantir que existe e pertence ao usuário ativo)
    //   2. Atualizar os campos editáveis (pergunta, palavrasChave)
    //   3. Atualizar o timestamp de alteração: p.setAlteracao(System.currentTimeMillis())
    //   4. Chamar arqPergunta.update(p)
    // =========================================================================

    /**
     * Altera o texto e as palavras-chave de uma pergunta existente.
     * O timestamp de alteração é atualizado automaticamente.
     */
    public boolean alterar(int idPergunta, String novoTexto, String novasPalavrasChave) throws Exception {
        // TODO: implementar
        throw new UnsupportedOperationException("alterar() não implementado.");
    }

    // =========================================================================
    // ARQUIVAR
    //
    // TODO: Implementar este método.
    // Lógica esperada:
    //   1. Verificar se a pergunta pertence ao usuário ativo (segurança)
    //   2. Chamar arqPergunta.arquivar(idPergunta)
    // =========================================================================

    /**
     * Arquiva uma pergunta (exclusão lógica, definitiva).
     */
    public boolean arquivar(int idPergunta, int idUsuarioAtivo) throws Exception {
        // TODO: implementar
        throw new UnsupportedOperationException("arquivar() não implementado.");
    }

    // =========================================================================

    public void close() throws Exception {
        arqPergunta.close();
    }
}
