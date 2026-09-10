package controle;

import entidades.ArquivoUsuario;
import entidades.Usuario;

public class ControleUsuario {

    private ArquivoUsuario arqUsuario;

    // usuário atualmente logado na sessão
    private Usuario usuarioAtivo;

    public ControleUsuario() throws Exception {
        arqUsuario = new ArquivoUsuario();
    }

    public boolean login(String email, String senha) throws Exception {
        Usuario usuario = arqUsuario.readByEmail(email);
        if (usuario != null && usuario.getHashSenha() == Usuario.hashSenha(senha)) {
            this.usuarioAtivo = usuario;
            return true;
        }
        return false;
    }

    public void logout() {
        this.usuarioAtivo = null;
    }

    public Usuario getUsuarioAtivo() {
        return usuarioAtivo;
    }

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

    public boolean validarRespostaSecreta(String email, String resposta) throws Exception {
        // TODO: implementar
        throw new UnsupportedOperationException("validarRespostaSecreta não implementado.");
    }

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

    public void close() throws Exception {
        arqUsuario.close();
    }
}
