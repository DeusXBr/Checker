package checker.ifrs.edu.checker.model.dal;

import checker.ifrs.edu.checker.vo.Avaliacao;
import checker.ifrs.edu.checker.vo.Resposta;
import io.realm.Realm;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

import static checker.ifrs.edu.checker.utils.StringUtils.isNegativeOrZero;

public class RespostaDal {

    private Realm mRealm;

    private static final int SUCCESS_OPERATION = 0;
    private static final int FAIL_OPERATION = 1;

    /**
     * Metodo construtor
     *
     */
    public RespostaDal(){
        this.mRealm = Realm.getDefaultInstance();
    }

    public int criarResposta(Resposta resposta){
        this.mRealm.beginTransaction();

        try {
            this.mRealm.copyToRealm(resposta);
        } catch (IllegalArgumentException | RealmPrimaryKeyConstraintException e){
            this.mRealm.cancelTransaction();
            return FAIL_OPERATION;
        }

        this.mRealm.commitTransaction();

        return SUCCESS_OPERATION;
    }

    public Resposta trazerResposta(int id){
        Resposta resposta = null;

        if(!isNegativeOrZero(id)){
            resposta = this.mRealm.where(Resposta.class).equalTo("id", id).findFirst();
        }

        return resposta;
    }

    public Resposta trazerRespostaByQuestaoId(int id){
        Resposta resposta = null;

        if(!isNegativeOrZero(id)){
            resposta = this.mRealm.where(Resposta.class).equalTo("questao.id", id).findFirst();
        }

        return resposta;
    }

    public Resposta trazerResposta(Avaliacao avaliacao){
        Resposta resposta = null;

        if(avaliacao != null){
            resposta = this.mRealm.where(Resposta.class).equalTo("avaliacao.id", avaliacao.getId()).findFirst();
        }

        return resposta;
    }

    public void removeFromRealm(int id){
        Resposta resposta = null;
        resposta = trazerResposta(id);
        if(resposta != null)
        {
            resposta.deleteFromRealm();
        }

    }
}
