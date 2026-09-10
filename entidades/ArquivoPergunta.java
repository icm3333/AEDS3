package entidades;

import aed3.Arquivo;
import aed3.ArvoreBMais;
import aed3.ParIdId;
import java.util.ArrayList;

public class ArquivoPergunta extends Arquivo<Pergunta> {

    // relacionamento 1:N → (idUsuario, idPergunta)
    ArvoreBMais<ParIdId> relUsuarioPergunta;

    public ArquivoPergunta() throws Exception {
        super("perguntas", Pergunta.class.getConstructor());
        relUsuarioPergunta = new ArvoreBMais<>(
            ParIdId.class.getConstructor(),
            5,
            "./dados/perguntas/relUsuarioPergunta.db"
        );
    }

    @Override
    public int create(Pergunta pergunta) throws Exception {
        int id = super.create(pergunta);
        relUsuarioPergunta.create(new ParIdId(pergunta.getIdUsuario(), id));
        return id;
    }

    public ArrayList<Pergunta> readAllByUsuario(int idUsuario) throws Exception {
        ArrayList<Pergunta> lista = new ArrayList<>();
        ArrayList<ParIdId> pares = relUsuarioPergunta.read(new ParIdId(idUsuario, -1));
        for (ParIdId par : pares) {
            Pergunta p = super.read(par.getId2());
            if (p != null) lista.add(p);
        }
        return lista;
    }

    // exclusão lógica: apenas muda ativa para false
    public boolean arquivar(int idPergunta) throws Exception {
        Pergunta p = super.read(idPergunta);
        if (p == null) return false;
        p.setAtiva(false);
        p.setAlteracao(System.currentTimeMillis());
        return super.update(p);
    }

    @Override
    public boolean delete(int id) throws Exception {
        Pergunta p = super.read(id);
        if (p == null) return false;
        relUsuarioPergunta.delete(new ParIdId(p.getIdUsuario(), id));
        return super.delete(id);
    }

    public void deleteAllByUsuario(int idUsuario) throws Exception {
        // TODO: implementar
        throw new UnsupportedOperationException("deleteAllByUsuario não implementado ainda.");
    }

    @Override
    public void close() throws java.io.IOException {
        super.close();
    }
}
