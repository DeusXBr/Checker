package checker.ifrs.edu.checker.model.dal;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import checker.ifrs.edu.checker.vo.Avaliacao;
import io.realm.Realm;
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
