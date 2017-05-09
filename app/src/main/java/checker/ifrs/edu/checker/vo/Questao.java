package checker.ifrs.edu.checker.vo;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class Questao extends RealmObject{

    @PrimaryKey
    private int id;
    private String pergunta;
    private String feedbackCorreto;
    private String feedbackErrado;
    private String feedbackParcial;
    private Categoria categoria;

    @Ignore
    private Realm mRealm;

    /**
     * Construtor
     */
    public Questao()
    {
    };

    public Questao(int id, String pergunta, String feedbackCorreto, String feedbackErrado, String feedbackParcial, Categoria categoria) {
        this.id = id;
        this.pergunta = pergunta;
        this.feedbackCorreto = feedbackCorreto;
        this.feedbackErrado = feedbackErrado;
        this.feedbackParcial = feedbackParcial;
        this.categoria = categoria;
    }

    /**
     * Set
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public void setFeedbackCorreto(String feedbackCorreto) {
        this.feedbackCorreto = feedbackCorreto;
    }

    public void setFeedbackErrado(String feedbackErrado) {
        this.feedbackErrado = feedbackErrado;
    }

    public void setFeedbackParcial(String feedbackParcial) {
        this.feedbackParcial = feedbackParcial;
    }

    public void setCategoria(Categoria categoria) {
        this.mRealm = Realm.getDefaultInstance();

        this.mRealm.beginTransaction();
        this.categoria = categoria;
        this.mRealm.commitTransaction();
    }

    /**
     * Get
     */
    public int getId() {
        return id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public String getFeedbackCorreto() {
        return feedbackCorreto;
    }

    public String getFeedbackErrado() {
        return feedbackErrado;
    }

    public String getFeedbackParcial() {
        return feedbackParcial;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
