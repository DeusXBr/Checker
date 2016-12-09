package checker.ifrs.edu.checker.vo;

import checker.ifrs.edu.checker.utils.ModelUtils;
import checker.ifrs.edu.checker.utils.StringUtils;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Avaliacao extends RealmObject{

    @PrimaryKey
    private int id;
    @Required
    private String nome;
    private String estado;
    private int nota;
    private String dataCriacao;
    private String dataModificado;
    private RealmList<Resposta> respostas;

    /**
     *  Construtor
     */
    public Avaliacao() {
        setId();
        setDataCriacao();
    }

    public Avaliacao(String nome, String estado, String dataModificado) {
        setId();
        this.nome = nome;
        this.estado = estado;
        setDataCriacao();
        this.dataModificado = dataModificado;
    }

    /**
     *  Set
     */
    private void setId() {
        ModelUtils mModelUtils = new ModelUtils();
        this.id = mModelUtils.getProximaIndex(Avaliacao.class);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNota(int nota){
        this.nota = nota;
    }

    private void setDataCriacao() {
        this.dataCriacao = StringUtils.getDataAtual();
    }

    public void setDataModificado(String dataModificado) {
        this.dataModificado = dataModificado;
    }

    public void setRespostas(RealmList<Resposta> respostas){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        this.respostas = respostas;
        realm.commitTransaction();
    }

    public void setResposta(Resposta resposta){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        this.respostas.add(resposta);
        realm.commitTransaction();
    }

    /**
     * Get
     */
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }

    public int getNota(){
        return this.nota;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public String getDataModificado() {
        return dataModificado;
    }

    public RealmList<Resposta> getRespostas(){
        return respostas;
    }
}
