package checker.ifrs.edu.checker.model.dal;

import java.util.ArrayList;
import java.util.List;

import checker.ifrs.edu.checker.vo.Avaliacao;
import checker.ifrs.edu.checker.vo.Resposta;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

import static checker.ifrs.edu.checker.utils.StringUtils.isNegativeOrZero;
import static checker.ifrs.edu.checker.utils.StringUtils.isNullOrEmpty;

public class AvaliacaoDal {

    private Realm mRealm;

    private static final int SUCCESS_OPERATION = 0;
    private static final int FAIL_OPERATION = 1;

    /**
     * Metodo construtor
     *
     */
    public AvaliacaoDal(){
        this.mRealm = Realm.getDefaultInstance();
    }

    public int criarAvaliacao(Avaliacao avaliacao){
        this.mRealm.beginTransaction();

        try {
            this.mRealm.copyToRealm(avaliacao);
        } catch (IllegalArgumentException | RealmPrimaryKeyConstraintException e){
            this.mRealm.cancelTransaction();
            return FAIL_OPERATION;
        }

        this.mRealm.commitTransaction();

        return SUCCESS_OPERATION;
    }

    public void editRespostaFromAvaliacao(int avaliacaoId, Resposta resposta){
        Avaliacao avaliacao = trazerAvaliacao(avaliacaoId);

        if(avaliacao != null)
        {
            RealmList<Resposta> respostaList = avaliacao.getRespostas();
            for (Resposta item : respostaList) {
                if( item.getQuestao().getId() == resposta.getQuestao().getId() )
                {
                    this.mRealm.beginTransaction();
                    item.setResposta(resposta.getResposta());
                    this.mRealm.commitTransaction();
                }
            }
        }

    }

    public Avaliacao trazerAvaliacao(int id){
        Avaliacao avaliacao = null;

        if(!isNegativeOrZero(id)){
            avaliacao = this.mRealm.where(Avaliacao.class).equalTo("id", id).findFirst();
        }

        return avaliacao;
    }

    public Avaliacao trazerAvaliacao(String nome){
        Avaliacao avaliacao = null;

        if(!isNullOrEmpty(nome)){
            avaliacao = this.mRealm.where(Avaliacao.class).equalTo("nome", nome).findFirst();
        }

        return avaliacao;
    }

    public List<Avaliacao> trazerAvaliacoes(){
        ArrayList<Avaliacao> avaliacaoArrayList = new ArrayList<>();

        RealmResults<Avaliacao> results = this.mRealm.where(Avaliacao.class).findAll();

        if(results.size() != 0){
            for (Avaliacao result: results) {
                avaliacaoArrayList.add(result);
            }
        }

        return avaliacaoArrayList;
    }

    public boolean hasResposta(int avaliacaoId, Resposta resposta){

        Avaliacao avaliacao = trazerAvaliacao(avaliacaoId);

        if(avaliacao != null)
        {
            RealmList<Resposta> respostaList = avaliacao.getRespostas();
            for (Resposta item : respostaList) {
                if( item.getQuestao().getId() == resposta.getQuestao().getId() )
                {
                    return true;
                }
            }
        }

        return false;
    }


    private void resetDatabase(){
        if(mRealm != null && mRealm.isInTransaction()){
            mRealm.cancelTransaction();
        }
    }

    public void clearDatabase(){
        resetDatabase();

        mRealm.beginTransaction();
        mRealm.where(Avaliacao.class).findAll().deleteAllFromRealm();
        mRealm.commitTransaction();
    }

}
