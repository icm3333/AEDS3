package controle;

import entidades.ArquivoUsuario;
import entidades.Usuario;

/**
 * Camada de controle dos usuários.
 * Contém toda a lógica de negócio relacionada a usuários (sem prints de tela).
 *
 * =========================================================================
 * IMPLEMENTADO:
 *  - login()
 *  - cadastrar()
 *
 * TODO para o grupo:
 *  - recuperarSenha()
 *  - alterarNome()
 *  - alterarEmail()
 *  - alterarSenha()
 *  - alterarPerguntaSecreta()
 * =========================================================================
 */
public class ControleUsuario {

    private ArquivoUsuario arqUsuario;

    // Usuário atualmente logado na sessão
    private Usuario usuarioAtivo;

    // =========================================================================
    // Construtor
    // =========================================================================

    public ControleUsuario() throws Exception {
        arqUsuario = new ArquivoUsuario();
    }

    // =========================================================================
    // LOGIN
    // =========================================================================

    /**
     * Valida email e senha. Se corretos, armazena o usuário na sessão.
     * @return true se login bem-sucedido, false caso contrário.
     */
    public boolean login(String email, String senha) throws Exception {
        Usuario usuario = arqUsuario.readByEmail(email);
        if (usuario != null && usuario.getHashSenha() == Usuario.hashSenha(senha)) {
            this.usuarioAtivo = usuario;
            return true;
        }
        return false;
    }

    /**
     * Encerra a sessão do usuário atual.
     */
    public void logout() {
        this.usuarioAtivo = null;
    }

    /**
     * Retorna o usuário atualmente logado, ou null se não houver sessão.
     */
    public Usuario getUsuarioAtivo() {
        return usuarioAtivo;
    }

    // =========================================================================
    // CADASTRO
    // =========================================================================

    /**
     * Cadastra um novo usuário no sistema.
     * Valida email único antes de criar.
     * @return O id do novo usuário, ou -1 em caso de erro.
     */
    public int cadastrar(String nome, String email, String senha,
                         String perguntaSecreta, String respostaSecreta) throws Exception {
        Usuario novo = new Usuario(
            -1,
            nome,
            email,
            Usuario.hashSenha(senha),
            perguntaSecreta,
            Usuario.hashResposta(respostaSecreta)
        );
        return arqUsuario.create(novo); // lança Exception se email já existir
    }

    // =========================================================================
    // RECUPERAÇÃO DE SENHA
    //
    // TODO: Implementar este método.
    // Lógica esperada:
    //   1. Buscar usuário pelo email
    //   2. Apresentar a pergunta secreta (feito na Visão)
    //   3. Comparar hashResposta(respostaInformada) com usuario.getHashRespostaSecreta()
    //   4. Se correto, chamar alterarSenha() com a nova senha
    // =========================================================================

    /**
     * Valida a resposta secreta para recuperação de senha.
     * @return true se a resposta estiver correta.
     */
    public boolean validarRespostaSecreta(String email, String resposta) throws Exception {
        // TODO: implementar
        throw new UnsupportedOperationException("validarRespostaSecreta não implementado.");
    }

    // =========================================================================
    // ALTERAÇÕES DE DADOS
    //
    // TODO: Implementar os métodos abaixo.
    // =========================================================================

    /** Altera o nome do usuário ativo. */
    public boolean alterarNome(String novoNome) throws Exception {
        // TODO: implementar
        throw new UnsupportedOperationException("alterarNome não implementado.");
    }

    /** Altera o email do usuário ativo (valida unicidade via ArquivoUsuario). */
    public boolean alterarEmail(String novoEmail) throws Exception {
        // TODO: implementar
        throw new UnsupportedOperationException("alterarEmail não implementado.");
    }

    /** Altera a senha do usuário ativo. */
    public boolean alterarSenha(String novaSenha) throws Exception {
        // TODO: implementar
        throw new UnsupportedOperationException("alterarSenha não implementado.");
    }

    /** Altera a pergunta e resposta secreta do usuário ativo. */
    public boolean alterarPerguntaSecreta(String novaPergunta, String novaResposta) throws Exception {
        // TODO: implementar
        throw new UnsupportedOperationException("alterarPerguntaSecreta não implementado.");
    }

    // =========================================================================

    public void close() throws Exception {
        arqUsuario.close();
    }
}
