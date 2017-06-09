package checker.ifrs.edu.checker.model.dao;

import checker.ifrs.edu.checker.vo.Questao;
import io.realm.Realm;
import io.realm.RealmResults;

import static checker.ifrs.edu.checker.utils.StringUtils.isNegativeOrZero;

public class QuestaoDao
{
    private Realm mRealm;

    /**
     * Metodo construtor
     *
     */
    public QuestaoDao ()
    {
        this.mRealm = Realm.getDefaultInstance();
    }

    public Questao trazerQuestao(int id)
    {
        Questao questao = null;

        if (!isNegativeOrZero(id))
        {
            questao = this.mRealm.where(Questao.class).equalTo("id", id).findFirst();
        }

        return questao;
    }

    public RealmResults<Questao> trazerQuestoes(String nomeCategoria)
    {
         return this.mRealm.where(Questao.class).equalTo("categoria.nome", nomeCategoria)
                                                .findAll();
    }

    private void resetDatabase()
    {
        if(mRealm != null && mRealm.isInTransaction())
        {
            mRealm.cancelTransaction();
        }
    }

    public void clearDatabase()
    {
        resetDatabase();

        mRealm.beginTransaction();
        mRealm.where(Questao.class).findAll().deleteAllFromRealm();
        mRealm.commitTransaction();
    }

}
