package checker.ifrs.edu.checker.model.bll;

import checker.ifrs.edu.checker.model.dao.RespostaDao;
import checker.ifrs.edu.checker.utils.exception.InvalidStringException;
import checker.ifrs.edu.checker.vo.Avaliacao;
import checker.ifrs.edu.checker.vo.Resposta;

import static checker.ifrs.edu.checker.utils.StringUtils.isNegativeOrZero;

public class RespostaBll
{

    private RespostaDao mRespostaDao;

    /**
     * Metodo construtor
     *
     */
    public RespostaBll()
    {
        mRespostaDao = new RespostaDao();
    }

    /**
     * Metodo para adicionar uma Resposta ao banco de dados
     *
     * @param resposta RealmObject
     */
    public void addResposta(Resposta resposta)
    {
        if (resposta == null)
        {
            throw new NullPointerException();
        }

        this.mRespostaDao.criarResposta(resposta);
    }

    /**
     * Metodo que busca uma resposta pelo ID
     *
     * @param id Codigo de identificado da resposta
     * @return Resposta Realm
     */
    public Resposta getResposta(int id)
    {
        Resposta resposta = null;

        try
        {
            if (isNegativeOrZero(id))
            {
                throw new InvalidStringException();
            }

            resposta = this.mRespostaDao.trazerResposta(id);
        }
        catch (InvalidStringException e)
        {
            //
        }

        return resposta;
    }

    /**
     * Metodo que busca uma resposta pela Avaliacao
     *
     * @param avaliacao Object para pesquisar no realm
     * @return Resposta Realm
     */
    public Resposta getResposta(Avaliacao avaliacao)
    {
        Resposta resposta = null;

        try
        {
            if (avaliacao == null || avaliacao.getId() <= 0)
            {
                throw new InvalidStringException();
            }

            resposta = this.mRespostaDao.trazerResposta(avaliacao);
        }
        catch (InvalidStringException e)
        {
            //
        }

        return resposta;
    }
}
