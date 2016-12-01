package checker.ifrs.edu.checker.model.dal;

import java.util.ArrayList;
import java.util.List;

import checker.ifrs.edu.checker.vo.Questao;
import io.realm.Realm;
import io.realm.RealmResults;

import static checker.ifrs.edu.checker.utils.StringUtils.isNegativeOrZero;

public class QuestaoDal {

    private Realm mRealm;

    /**
     * Metodo construtor
     *
     */
    public QuestaoDal(){
        this.mRealm = Realm.getDefaultInstance();
    }

    public Questao trazerQuestao(int id){
        Questao questao = null;

        if(!isNegativeOrZero(id)){
            questao = this.mRealm.where(Questao.class).equalTo("id", id).findFirst();
        }

        return questao;
    }

    public List<Questao> trazerQuestoes(String nomeCategoria){
        List<Questao> questaoList = new ArrayList<>();

        RealmResults<Questao> results = this.mRealm.where(Questao.class)
                                                    .equalTo("categoria.nome", nomeCategoria)
                                                    .findAll();

        if(results.size() != 0){
            for (Questao result: results) {
                questaoList.add(result);
            }
        }

        return questaoList;
    }

    private void resetDatabase(){
        if(mRealm != null && mRealm.isInTransaction()){
            mRealm.cancelTransaction();
        }
    }

    public void clearDatabase(){
        resetDatabase();

        mRealm.beginTransaction();
        mRealm.where(Questao.class).findAll().deleteAllFromRealm();
        mRealm.commitTransaction();
    }

}
