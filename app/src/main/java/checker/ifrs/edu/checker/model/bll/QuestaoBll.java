package checker.ifrs.edu.checker.model.bll;

import checker.ifrs.edu.checker.model.dao.QuestaoDao;
import checker.ifrs.edu.checker.utils.exception.InvalidStringException;
import checker.ifrs.edu.checker.vo.Questao;
import io.realm.RealmResults;

import static checker.ifrs.edu.checker.utils.StringUtils.isNegativeOrZero;

public class QuestaoBll
{
    private QuestaoDao mQuestaoDao;

    /**
     * Metodo construtor
     *
     */
    public QuestaoBll()
    {
        mQuestaoDao = new QuestaoDao();
    }

    /**
     * Retorna todas as Questões de uma categoria
     *
     * @return RealmResult lista com os dados do realm
     */
    public RealmResults<Questao> getAllQuestoesByCategoria(String nomeCategoria)
    {
        return this.mQuestaoDao.trazerQuestoes(nomeCategoria);
    }

    /**
     * Metodo que busca uma questao pelo ID
     *
     * @param id Codigo de identificado da questao
     * @return Questao Realm
     */
    public Questao getQuestao(int id)
    {
        Questao questao = null;

        try
        {
            if(isNegativeOrZero(id)){
                throw new InvalidStringException();
            }

            questao = this.mQuestaoDao.trazerQuestao(id);
        }
        catch (InvalidStringException e)
        {
            //
        }

        return questao;
    }
}
