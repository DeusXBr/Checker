package checker.ifrs.edu.checker.model.bll;
import java.util.List;

import checker.ifrs.edu.checker.model.dal.AvaliacaoDal;
import checker.ifrs.edu.checker.utils.exception.InvalidStringException;
import checker.ifrs.edu.checker.vo.Avaliacao;

import static checker.ifrs.edu.checker.utils.StringUtils.isNullOrEmpty;

public class AvaliacaoBll {

    private AvaliacaoDal mAvaliacaoDal;

    /**
     * Metodo construtor
     *
     */
    public AvaliacaoBll(){
        mAvaliacaoDal = new AvaliacaoDal();
    }

    /**
     * Metodo para adicionar uma Avaliacao ao banco de dados
     *
     * @param avaliacao RealmObject
     */
    public void addAvaliacao(Avaliacao avaliacao){
        if (avaliacao == null){
            throw new NullPointerException();
        }

        this.mAvaliacaoDal.criarAvaliacao(avaliacao);
    }

    /**
     * Metodo que busca uma avaliacao pelo NOME
     *
     * @param nome String
     * @return Retorna um RealmObject Avaliacao.
     * Caso não encontrar nada retornará um RealmObject nullo.
     */
    public Avaliacao getAvaliacao(String nome){
        Avaliacao avaliacao = null;

        try{
            if(isNullOrEmpty(nome)){
                throw new InvalidStringException();
            }

            avaliacao = this.mAvaliacaoDal.trazerAvaliacao(nome);
        } catch (InvalidStringException e){
            //
        }

        return avaliacao;
    }

    /**
     * Retorna todas as Avaliacoes do banco de dados
     *
     * @return List - Lista de Avaliacoes
     */
    public List<Avaliacao> getAllAvaliacoes(){
        return this.mAvaliacaoDal.trazerAvaliacoes();
    }


    public void limparBancoDados(){
        mAvaliacaoDal.clearDatabase();
    }

}
