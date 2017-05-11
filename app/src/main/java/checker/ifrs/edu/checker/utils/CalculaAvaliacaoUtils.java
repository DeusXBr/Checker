package checker.ifrs.edu.checker.utils;

import android.util.Log;

import checker.ifrs.edu.checker.vo.Resposta;
import io.realm.RealmList;

public class CalculaAvaliacaoUtils
{

    private RealmList<Resposta> listRespostas;
    private float nota;
    private String status;
    private int respostasRespondidas;

    public static final String STATUS_CRIADO = "novo";
    public static final String STATUS_POSITIVO = "bom";
    public static final String STATUS_REGULAR = "regular";
    public static final String STATUS_NEGATIVO = "ruim";

    public CalculaAvaliacaoUtils(){}

    public CalculaAvaliacaoUtils(RealmList<Resposta> listRespostas)
    {
        this.listRespostas = listRespostas;

        this.status = STATUS_CRIADO;
        this.nota = 0;
        this.respostasRespondidas = listRespostas.size();
    }

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
                    case 2131493032: this.nota += 1;
                            break;

                    case 2131493034: this.nota += 0.5;
                            break;

                    case 2131493035: this.respostasRespondidas -= 1;
                            break;

                    default: break;
                }
            }

            // verifica qual status ficou
            setStatus();

            return nota;
        }
    }

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
