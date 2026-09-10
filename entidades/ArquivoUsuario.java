package entidades;

import aed3.Arquivo;
import aed3.HashExtensivel;

/**
 * CRUD de Usuários.
 * Estende o Arquivo genérico do professor e adiciona:
 *  - Índice Hash Extensível pelo email (busca em O(1))
 *  - Validação de email único
 *  - Exclusão em cascata (ao deletar usuário, deleta suas perguntas)
 *
 * =========================================================================
 * TODO para o grupo:
 *  - Implementar o método delete() com exclusão em cascata das perguntas
 *  - Implementar o método update() atualizando o índice caso o email mude
 * =========================================================================
 */
public class ArquivoUsuario extends Arquivo<Usuario> {

    // Índice indireto: email → idUsuario
    HashExtensivel<ParEmailId> indiceEmail;

    // =========================================================================
    // Construtor
    // =========================================================================

    public ArquivoUsuario() throws Exception {
        super("usuarios", Usuario.class.getConstructor());
        indiceEmail = new HashExtensivel<>(
            ParEmailId.class.getConstructor(),
            4,
            "./dados/usuarios/indiceEmail.diretorio.db",
            "./dados/usuarios/indiceEmail.cestos.db"
        );
    }

    // =========================================================================
    // CREATE — grava no arquivo + insere no índice de email
    // =========================================================================

    @Override
    public int create(Usuario usuario) throws Exception {
        // Garante que não exista outro usuário com o mesmo email
        if (readByEmail(usuario.getEmail()) != null) {
            throw new Exception("Email já cadastrado: " + usuario.getEmail());
        }
        int id = super.create(usuario);
        indiceEmail.create(new ParEmailId(usuario.getEmail(), id));
        return id;
    }

    // =========================================================================
    // READ por email — usa o índice hash para busca direta
    // =========================================================================

    /**
     * Busca um usuário pelo email usando o índice Hash Extensível.
     * @return O usuário encontrado, ou null se não existir.
     */
    public Usuario readByEmail(String email) throws Exception {
        ParEmailId par = indiceEmail.read(ParEmailId.hash(email));
        if (par != null) {
            return super.read(par.getId());
        }
        return null;
    }

    // =========================================================================
    // UPDATE — atualiza o registro e mantém o índice de email consistente
    // =========================================================================

    @Override
    public boolean update(Usuario usuarioAtualizado) throws Exception {

        Usuario usuarioAntigo = super.read(usuarioAtualizado.getId());
        if (usuarioAntigo == null) return false;

        // Se o email mudou, precisamos atualizar o índice
        if (!usuarioAntigo.getEmail().equals(usuarioAtualizado.getEmail())) {

            // Verifica se o novo email já está em uso por outro usuário
            if (readByEmail(usuarioAtualizado.getEmail()) != null) {
                throw new Exception("Email já em uso: " + usuarioAtualizado.getEmail());
            }

            // Remove o índice antigo e cria o novo
            indiceEmail.delete(ParEmailId.hash(usuarioAntigo.getEmail()));
            indiceEmail.create(new ParEmailId(usuarioAtualizado.getEmail(), usuarioAtualizado.getId()));
        }

        return super.update(usuarioAtualizado);
    }

    // =========================================================================
    // DELETE — remove do índice e exclui o registro
    //
    // TODO: Implementar exclusão em cascata das perguntas do usuário.
    //       Será necessário receber uma instância de ArquivoPergunta aqui
    //       ou chamar via ControleUsuario antes de deletar.
    // =========================================================================

    @Override
    public boolean delete(int id) throws Exception {
        Usuario usuario = super.read(id);
        if (usuario == null) return false;

        // Remove do índice de email
        indiceEmail.delete(ParEmailId.hash(usuario.getEmail()));

        // TODO: Deletar todas as perguntas do usuário antes de deletá-lo
        // ArquivoPergunta arqPergunta = ...
        // arqPergunta.deleteAllByUsuario(id);

        return super.delete(id);
    }

    // =========================================================================
    // CLOSE — fecha o arquivo principal e os índices
    // =========================================================================

    @Override
    public void close() throws Exception {
        indiceEmail.close();
        super.close();
    }
}
