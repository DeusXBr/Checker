package checker.ifrs.edu.checker.utils;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.vo.Resposta;
import io.realm.RealmList;

public class CalculaAvaliacaoUtils
{
    private RealmList<Resposta> listRespostas;
    private float nota;
    private String status;
    private int respostasRespondidas;

    public static final String STATUS_CRIADO = "Novo";
    public static final String STATUS_POSITIVO = "Bom";
    public static final String STATUS_REGULAR = "Regular";
    public static final String STATUS_NEGATIVO = "Ruim";

    /**
     * Contrutor padrão
     */
    public CalculaAvaliacaoUtils(){}

    /**
     * Construtor com parametros
     * @param listRespostas uma lista contendo todas as respostas
     */
    public CalculaAvaliacaoUtils(RealmList<Resposta> listRespostas)
    {
        this.listRespostas = listRespostas;

        this.status = STATUS_CRIADO;
        this.nota = 0;
        this.respostasRespondidas = listRespostas.size();
    }

    /**
     * Metodo faz o calculo da avaliacao
     * @return float/nota
     * @throws NullPointerException
     */
    public float avaliar() throws NullPointerException
    {
        if(getTotalRespostas() == 0)
        {
            throw new NullPointerException();
        }
        else
        {
            for (Resposta item : listRespostas)
            {
                switch (item.getResposta())
                {
                    case R.id.mRadio1: this.nota += 1;
                                     break;

                    case R.id.mRadio3: this.nota += 0.5;
                                     break;

                    case R.id.mRadio4: this.respostasRespondidas -= 1;
                                     break;

                    default: break;
                }
            }

            // verifica qual status ficou
            setStatus();

            return nota;
        }
    }

    /**
     * Metodo define um status para a avaliacao dependendo de sua nota
     */
    private void setStatus()
    {
        float porcentagem = (100*this.nota)/this.respostasRespondidas;

        if( porcentagem < 50 )
        {
            this.status = STATUS_NEGATIVO;
        }
        else
        {
            if(porcentagem == 50 || porcentagem < 70)
            {
                this.status = STATUS_REGULAR;
            }
            else
            {
                this.status = STATUS_POSITIVO;
            }
        }
    }

    private void setNota(float nota)
    {
        this.nota = nota;
    }

    public String getStatus()
    {
        return this.status;
    }

    private int getTotalRespostas()
    {
        return listRespostas.size();
    }

    public int getTotalRespondidas()
    {
        return this.respostasRespondidas;
    }

    public float getNota()
    {
        return this.nota;
    }


}
