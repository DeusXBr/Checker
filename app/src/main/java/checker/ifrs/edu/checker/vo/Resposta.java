package checker.ifrs.edu.checker.vo;

import checker.ifrs.edu.checker.utils.ModelUtils;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

public class Resposta extends RealmObject {

    @PrimaryKey
    private int id;
    private Avaliacao avaliacao;
    private RealmList<Questao> questoes;
    private RealmList<RealmInt> ints;

    public Resposta(){
        this.setId();
    }

    public Resposta(Avaliacao avaliacao, RealmList<Questao> questoes, RealmList<RealmInt> ints) {
        this.setId();
        this.avaliacao = avaliacao;
        this.questoes = questoes;
        this.ints = ints;
    }

    public Resposta(Avaliacao avaliacao, Questao questao, RealmInt inteiro) {
        this.setId();
        this.avaliacao = avaliacao;

        this.questoes = new RealmList<>();
        this.questoes.add(questao);

        this.ints = new RealmList<>();
        this.ints.add(inteiro);
    }

    public void setId() {
        ModelUtils mModelUtils = new ModelUtils();
        this.id = mModelUtils.getProximaIndex(Resposta.class);
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setQuestoes(Questao questao) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        this.removeIntsQuestao(this.questoes.indexOf(questao));

        this.questoes.add(questao);

        realm.commitTransaction();
    }

    public void setInts(RealmInt inteiro) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        this.ints.add(inteiro);
        realm.commitTransaction();
    }

    public int getId() {
        return id;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public RealmList<Questao> getQuestoes() {
        return questoes;
    }

    public RealmList<RealmInt> getInts() {
        return ints;
    }

    public void removeIntsQuestao(int position){
        try{
            this.questoes.remove(position);
            this.ints.remove(position);
        }catch (IndexOutOfBoundsException e){
            e.getCause();
            e.getMessage();
        }
    }
}
