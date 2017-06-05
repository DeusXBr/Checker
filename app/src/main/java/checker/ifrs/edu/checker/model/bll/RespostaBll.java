package checker.ifrs.edu.checker.model.bll;

import checker.ifrs.edu.checker.model.dal.RespostaDal;
import checker.ifrs.edu.checker.utils.exception.InvalidStringException;
import checker.ifrs.edu.checker.vo.Avaliacao;
import checker.ifrs.edu.checker.vo.Resposta;

import static checker.ifrs.edu.checker.utils.StringUtils.isNegativeOrZero;

public class RespostaBll
{

    private RespostaDal mRespostaDal;

    /**
     * Metodo construtor
     *
     */
    public RespostaBll()
    {
        mRespostaDal = new RespostaDal();
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

        this.mRespostaDal.criarResposta(resposta);
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

            resposta = this.mRespostaDal.trazerResposta(id);
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

            resposta = this.mRespostaDal.trazerResposta(avaliacao);
        }
        catch (InvalidStringException e)
        {
            //
        }

        return resposta;
    }
}
