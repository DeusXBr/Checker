package checker.ifrs.edu.checker.vo;

import android.util.Log;

import checker.ifrs.edu.checker.utils.ModelUtils;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Resposta extends RealmObject {

    @PrimaryKey
    private int id;
    private Questao questao;
    private int resposta;


    /**
     * Construtor
     */
    public Resposta(){
        setId();
    }

    public Resposta(Questao questao, int resposta) {
        setId();
        this.questao = questao;
        this.resposta = resposta;
    }


    /**
     * Set
     */
    public void setId() {
        ModelUtils mModelUtils = new ModelUtils();
        this.id = mModelUtils.getProximaIndex(Resposta.class);
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }


    /**
     * Get
     */
    public int getId() {
        return id;
    }

    public Questao getQuestao() {
        return questao;
    }

    public int getResposta() {
        return resposta;
    }

    /**
     * Utils
     */
    public void showDadosResposta(){
        Log.i("MeuTeste", "id: " + this.id);
        Log.i("MeuTeste", "Questao: " + this.questao);
        Log.i("MeuTeste", "QuestaoCategoria: " + this.questao.getCategoria().getNome());
        Log.i("MeuTeste", "id: " + this.resposta);
    }
}
