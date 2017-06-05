package checker.ifrs.edu.checker.vo;

import checker.ifrs.edu.checker.utils.ModelUtils;
import checker.ifrs.edu.checker.utils.StringUtils;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

import static checker.ifrs.edu.checker.utils.StringUtils.primeiroCaracterMaiusculo;

public class Avaliacao extends RealmObject
{

    @PrimaryKey
    private int id;
    @Required
    private String nome;
    private String estado;
    private float nota;
    private String dataCriacao;
    private String dataModificado;
    private RealmList<Resposta> respostas;

    @Ignore
    private Realm mRealm;

    /**
     *  Construtor
     */
    public Avaliacao()
    {
        setId();
        setDataCriacao();
        setDataModificado();
        this.mRealm = Realm.getDefaultInstance();
    }

    public Avaliacao(String nome, String estado)
    {
        setId();
        this.nome = nome;
        this.estado = primeiroCaracterMaiusculo(estado);
        this.nota = 0;
        setDataCriacao();
        setDataModificado();
        this.mRealm = Realm.getDefaultInstance();
    }

    /**
     *  Set
     */
    private void setId()
    {
        ModelUtils mModelUtils = new ModelUtils();
        this.id = mModelUtils.getProximaIndex(Avaliacao.class);
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public void setEstado(String estado)
    {
        this.estado = primeiroCaracterMaiusculo(estado);
    }

    public void setEstadoDirect(String estado)
    {
        this.mRealm.beginTransaction();
        this.estado = primeiroCaracterMaiusculo(estado);
        this.mRealm.commitTransaction();
    }

    public void setNota(float nota)
    {
        this.nota = nota;
    }

    private void setDataCriacao()
    {
        this.dataCriacao = StringUtils.getDataAtual();
    }

    public void setDataModificado()
    {
        this.dataModificado = StringUtils.getDataAtual();
    }

    public void setRespostas(RealmList<Resposta> respostas)
    {
        this.mRealm.beginTransaction();
        this.respostas = respostas;
        this.mRealm.commitTransaction();
    }

    public void setResposta(Resposta resposta)
    {
        this.mRealm.beginTransaction();
        this.respostas.add(resposta);
        this.mRealm.commitTransaction();
    }

    /**
     * Get
     */
    public int getId()
    {
        return id;
    }

    public String getNome()
    {
        return nome;
    }

    public String getEstado()
    {
        return estado;
    }

    public float getNota()
    {
        return this.nota;
    }

    public String getDataCriacao()
    {
        return dataCriacao;
    }

    public String getDataModificado()
    {
        return dataModificado;
    }

    public RealmList<Resposta> getRespostas()
    {
        return respostas;
    }
}
