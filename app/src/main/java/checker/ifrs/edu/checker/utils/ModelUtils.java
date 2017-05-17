package checker.ifrs.edu.checker.utils;

import android.util.Log;
import android.util.SparseIntArray;

import java.util.ArrayList;
import java.util.List;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.model.bll.AvaliacaoBll;
import checker.ifrs.edu.checker.model.bll.CategoriaBll;
import checker.ifrs.edu.checker.vo.Avaliacao;
import checker.ifrs.edu.checker.vo.Categoria;
import checker.ifrs.edu.checker.vo.Resposta;
import io.realm.Realm;

import static checker.ifrs.edu.checker.utils.StringUtils.isNullOrEmpty;

public class ModelUtils
{

    private Realm mRealm;

    /**
     * Construtor
     */
    public ModelUtils()
    {
        this.mRealm = Realm.getDefaultInstance();
    }

    /**
     * Este metodo retorna o primeiro index livre da tabela requisitada.
     * Implementação do auto increment, já que o realm não suporta.
     *
     * @param classe a classe do object que será procurado no BD
     * @return Um inteiro correspondente ao id livre da tabela
     */
    public int getProximaIndex(Class classe)
    {
        Number result = this.mRealm.where(classe).count();
        long idVago = 0;

        if (result != null)
        {
            idVago = result.longValue() + 1;
            return (int)idVago;
        }
        else
        {
            return 1;
        }
    }

    /**
     * Método valida o campo título da avaliação.
     *
     * Vazio/Nullo
     * Tamanho máximo
     * Titulo existente
     *
     * @param titulo
     * @return map com os erros
     */
    public List<String> validacaoTitulo(String titulo, AvaliacaoBll mAvaliacaoBll)
    {
        List<String> listErros = new ArrayList<String>();

        if ( isNullOrEmpty(titulo) )
        {
            listErros.add("O título não pode ficar em branco");
        }

        if ( titulo.length() > 50 )
        {
            listErros.add("O título não pode ter mais do que 50 caracteres.");
        }

        if ( mAvaliacaoBll.getAvaliacao(titulo) != null )
        {
            listErros.add("O título já existe.");
        }

        if ( listErros.isEmpty() )
        {
            listErros.add(0, "false");
        }
        else
        {
            listErros.add(0, "true");
        }

        return listErros;
    }

    public boolean categoriaEstaCompleta(Avaliacao avaliacao, Categoria categoria)
    {
        int quantidade = 0;

        CategoriaBll categoriaBll = new CategoriaBll();
        int quantidadeCategorias = categoriaBll.getQuestoes(categoria).size();

        for (int i = 0; i < avaliacao.getRespostas().size(); i++)
        {
            if (avaliacao.getRespostas().get(i).getQuestao().getCategoria().getNome().equals(categoria.getNome()) )
            {
                quantidade ++;
            }
        }

        if ( quantidade == quantidadeCategorias && quantidade != 0)
        {
            return true;
        }

        return false;
    }

    public static SparseIntArray getRespostas(Avaliacao avaliacao, String nomeCategoria)
    {
        SparseIntArray sparseIntArray = new SparseIntArray();

        for (int i = 0; i < avaliacao.getRespostas().size(); i++)
        {
            if (avaliacao.getRespostas().get(i).getQuestao().getCategoria().getNome().equals(nomeCategoria) )
            {
                sparseIntArray.put(avaliacao.getRespostas().get(i).getQuestao().getId(), avaliacao.getRespostas().get(i).getResposta());
            }
        }

        //Log.i("MeuTeste", "Respostas: " + sparseIntArray.toString());

        return sparseIntArray;
    }

    public static List<Resposta> getRespostasErradasRegulares(Avaliacao avaliacao)
    {
        List<Resposta> listResposta = new ArrayList<>();

        if (avaliacao != null)
        {
            for ( Resposta item : avaliacao.getRespostas() )
            {
                if (item.getResposta() == R.id.mRadio2 || item.getResposta() == R.id.mRadio3)
                {
                   listResposta.add(item);
                }
            }
        }

        return listResposta;
    }

}
