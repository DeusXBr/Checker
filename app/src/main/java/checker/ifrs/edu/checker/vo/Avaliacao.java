package checker.ifrs.edu.checker.vo;

import android.util.Log;

import java.text.DateFormat;
import java.util.Date;

import checker.ifrs.edu.checker.utils.ModelUtils;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Avaliacao extends RealmObject{

    @PrimaryKey
    private int id;
    @Required
    private String nome;
    private String resultado; // tipo não definido
    private String dataCriacao;
    private String dataModificado;

    public Avaliacao() {
        setId();
        setDataCriacao();
    }

    public Avaliacao(String nome, String resultado, String dataModificado) {
        setId();
        this.nome = nome;
        this.resultado = resultado;
        setDataCriacao();
        this.dataModificado = dataModificado;
    }

    private void setId() {
        ModelUtils mModelUtils = new ModelUtils();
        this.id = mModelUtils.getProximaIndex(Avaliacao.class);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    private void setDataCriacao() {
        this.dataCriacao = DateFormat.getDateInstance().format(new Date());
    }

    public void setDataModificado(String dataModificado) {
        this.dataModificado = dataModificado;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getResultado() {
        return resultado;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public String getDataModificado() {
        return dataModificado;
    }
}
