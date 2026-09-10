package entidades;

import aed3.InterfaceHashExtensivel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Par (email → idUsuario) usado como entrada na Tabela Hash Extensível.
 *
 * Análogo ao ParIsbnID do professor — segue o mesmo padrão.
 * O email é armazenado com tamanho fixo de 100 bytes (padding com espaços).
 */
public class ParEmailId implements InterfaceHashExtensivel {

    // Comprimento máximo do email em bytes (ASCII/UTF-8 puro)
    private static final int    EMAIL_BYTES = 100;
    private static final short  TAMANHO     = (short) (EMAIL_BYTES + 4); // 100 + int

    private String email;
    private int    id;

    // -------------------------------------------------------------------------

    public ParEmailId() {
        this.email = "";
        this.id    = -1;
    }

    public ParEmailId(String email, int id) {
        this.email = email;
        this.id    = id;
    }

    // -------------------------------------------------------------------------

    public String getEmail() { return email; }
    public int    getId()    { return id; }

    // -------------------------------------------------------------------------
    // Interface HashExtensivel
    // -------------------------------------------------------------------------

    /**
     * hashCode do email — mesmo padrão do professor (Math.abs + String.hashCode).
     */
    @Override
    public int hashCode() {
        return hash(this.email);
    }

    public static int hash(String email) {
        return Math.abs(email.hashCode());
    }

    @Override
    public short size() {
        return TAMANHO;
    }

    // -------------------------------------------------------------------------
    // Serialização / Desserialização
    // -------------------------------------------------------------------------

    @Override
    public byte[] serialize() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        // Email com tamanho fixo (pad à direita com espaços)
        byte[] emailBytes = new byte[EMAIL_BYTES];
        byte[] raw = this.email.getBytes();
        System.arraycopy(raw, 0, emailBytes, 0, Math.min(raw.length, EMAIL_BYTES));
        dos.write(emailBytes);

        dos.writeInt(this.id);
        return baos.toByteArray();
    }

    @Override
    public void deserialize(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        byte[] emailBytes = new byte[EMAIL_BYTES];
        dis.readFully(emailBytes);
        this.email = new String(emailBytes).trim();

        this.id = dis.readInt();
    }

    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        return "(" + email + ";" + id + ")";
    }
}
