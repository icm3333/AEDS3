package entidades;

import aed3.Arquivo;
import aed3.ArvoreBMais;
import aed3.ParIdId;
import java.util.ArrayList;

/**
 * CRUD de Perguntas.
 * Estende o Arquivo genérico do professor e adiciona:
 *  - Árvore B+ com par (idUsuario, idPergunta) para o relacionamento 1:N
 *  - Método arquivar() que faz exclusão lógica (ativa = false)
 *  - Listagem de perguntas por usuário via Árvore B+
 *
 * =========================================================================
 * TODO para o grupo:
 *  - Implementar deleteAllByUsuario() para a exclusão em cascata
 * =========================================================================
 */
public class ArquivoPergunta extends Arquivo<Pergunta> {

    // Relacionamento 1:N  →  (idUsuario, idPergunta)
    ArvoreBMais<ParIdId> relUsuarioPergunta;

    // =========================================================================
    // Construtor
    // =========================================================================

    public ArquivoPergunta() throws Exception {
        super("perguntas", Pergunta.class.getConstructor());
        relUsuarioPergunta = new ArvoreBMais<>(
            ParIdId.class.getConstructor(),
            5,
            "./dados/perguntas/relUsuarioPergunta.db"
        );
    }

    // =========================================================================
    // CREATE — grava no arquivo + insere na Árvore B+
    // =========================================================================

    @Override
    public int create(Pergunta pergunta) throws Exception {
        int id = super.create(pergunta);
        relUsuarioPergunta.create(new ParIdId(pergunta.getIdUsuario(), id));
        return id;
    }

    // =========================================================================
    // READ todas por usuário — usa a Árvore B+ para evitar varredura total
    // =========================================================================

    /**
     * Retorna todas as perguntas (ativas e arquivadas) de um usuário.
     * A Árvore B+ com par (idUsuario, idPergunta) garante busca eficiente.
     */
    public ArrayList<Pergunta> readAllByUsuario(int idUsuario) throws Exception {
        ArrayList<Pergunta> lista = new ArrayList<>();
        ArrayList<ParIdId> pares = relUsuarioPergunta.read(new ParIdId(idUsuario, -1));
        for (ParIdId par : pares) {
            Pergunta p = super.read(par.getId2());
            if (p != null) lista.add(p);
        }
        return lista;
    }

    // =========================================================================
    // ARQUIVAR — exclusão lógica: apenas muda ativa para false
    //            O arquivamento é definitivo (não há como restaurar)
    // =========================================================================

    /**
     * Arquiva uma pergunta. A pergunta continua no arquivo, mas ativa = false.
     * @return true se arquivada com sucesso, false se não encontrada.
     */
    public boolean arquivar(int idPergunta) throws Exception {
        Pergunta p = super.read(idPergunta);
        if (p == null) return false;
        p.setAtiva(false);
        p.setAlteracao(System.currentTimeMillis());
        return super.update(p);
    }

    // =========================================================================
    // DELETE — remove da Árvore B+ e exclui o registro
    // =========================================================================

    @Override
    public boolean delete(int id) throws Exception {
        Pergunta p = super.read(id);
        if (p == null) return false;
        relUsuarioPergunta.delete(new ParIdId(p.getIdUsuario(), id));
        return super.delete(id);
    }

    // =========================================================================
    // DELETE ALL BY USUARIO — exclui todas as perguntas de um usuário
    //                         Chamado pela exclusão em cascata no ArquivoUsuario
    //
    // TODO: Implementar este método
    // =========================================================================

    /**
     * Exclui todas as perguntas de um usuário (cascata quando o usuário é deletado).
     */
    public void deleteAllByUsuario(int idUsuario) throws Exception {
        // TODO: implementar
        // Dica: use readAllByUsuario(idUsuario) para obter a lista
        //       e chame delete(p.getId()) para cada uma.
        throw new UnsupportedOperationException("deleteAllByUsuario não implementado ainda.");
    }

    // =========================================================================

    @Override
    public void close() throws Exception {
        // ArvoreBMais não tem close(); apenas o arquivo principal precisa.
        super.close();
    }
}
