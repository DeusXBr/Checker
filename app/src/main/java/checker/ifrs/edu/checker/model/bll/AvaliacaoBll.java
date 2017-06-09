package checker.ifrs.edu.checker.model.bll;

import java.util.List;

import checker.ifrs.edu.checker.model.dao.AvaliacaoDao;
import checker.ifrs.edu.checker.utils.exception.InvalidStringException;
import checker.ifrs.edu.checker.vo.Avaliacao;
import checker.ifrs.edu.checker.vo.Resposta;
import io.realm.RealmList;

import static checker.ifrs.edu.checker.utils.StringUtils.isNullOrEmpty;

public class AvaliacaoBll
{

    private AvaliacaoDao mAvaliacaoDao;

    /**
     * Metodo construtor
     *
     */
    public AvaliacaoBll()
    {
        mAvaliacaoDao = new AvaliacaoDao();
    }

    /**
     * Metodo para adicionar uma Avaliacao ao banco de dados
     *
     * @param avaliacao RealmObject
     */
    public void addAvaliacao(Avaliacao avaliacao)
    {
        if (avaliacao == null)
        {
            throw new NullPointerException();
        }

        this.mAvaliacaoDao.criarAvaliacao(avaliacao);
    }

    /**
     * Edita uma resposta de uma avaliacao
     *
     * @param avalicaoId identifica qual avaliação a ser editada
     * @param resposta nova resposta
     */
    public void editRespostaDeUmaAvaliacao(int avalicaoId, Resposta resposta)
    {
        this.mAvaliacaoDao.editRespostaFromAvaliacao(avalicaoId, resposta);
    }

    /**
     * Metodo que busca uma avaliacao pelo NOME
     *
     * @param nome String
     * @return Retorna um RealmObject Avaliacao.
     * Caso não encontrar nada retornará um RealmObject nullo.
     */
    public Avaliacao getAvaliacao(String nome)
    {
        Avaliacao avaliacao = null;

        try
        {
            if(isNullOrEmpty(nome))
            {
                throw new InvalidStringException();
            }

            avaliacao = this.mAvaliacaoDao.trazerAvaliacao(nome);
        }
        catch (InvalidStringException e)
        {
            //
        }

        return avaliacao;
    }

    /**
     * Retorna todas as Avaliacoes do banco de dados
     *
     * @return List - Lista de Avaliacoes
     */
    public List<Avaliacao> getAllAvaliacoes()
    {
        return this.mAvaliacaoDao.trazerAvaliacoes();
    }

    /**
     * Metodo traz todas as respostas da avaliacao especifica
     * @param avaliacao
     */
    public RealmList<Resposta> getAllRespostas(Avaliacao avaliacao)
    {
        if (avaliacao != null)
        {
            return avaliacao.getRespostas();
        }

        return null;
    }

    public boolean temResposta(int avalicaoId, Resposta resposta)
    {
        return this.mAvaliacaoDao.hasResposta(avalicaoId, resposta);
    }

    public void limparBancoDados()
    {
        mAvaliacaoDao.clearDatabase();
    }

    public void removeAvaliacao(Avaliacao avaliacao)
    {
        this.mAvaliacaoDao.remove(avaliacao);
    }

}
