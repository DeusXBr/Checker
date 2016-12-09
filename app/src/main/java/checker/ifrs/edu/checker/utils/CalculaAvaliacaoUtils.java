package checker.ifrs.edu.checker.utils;

import android.util.Log;

import checker.ifrs.edu.checker.vo.Resposta;
import io.realm.RealmList;

public class CalculaAvaliacaoUtils {

    private RealmList<Resposta> listRespostas;
    private float nota;
    private String status;
    private int respostasRespondidas;

    public CalculaAvaliacaoUtils(){}

    public CalculaAvaliacaoUtils(RealmList<Resposta> listRespostas){
        this.listRespostas = listRespostas;

        this.status = "Novo";
        this.nota = 0;
        this.respostasRespondidas = listRespostas.size();
    }

    public float avaliar() throws NullPointerException{

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
                    case 1: this.nota += 1;
                            break;

                    case 3: this.nota += 0.5;
                            break;

                    case 4: this.respostasRespondidas -= 1;
                            break;

                    default: break;
                }
            }

            // verifica qual status ficou
            setStatus();

            return nota;
        }
    }

    public void setStatus(){
        float porcentagem = (100*this.nota)/this.respostasRespondidas;

        if( porcentagem < 50 )
        {
            this.status = "Ruim";
        }
        else
        {
            if(porcentagem == 50 || porcentagem < 70)
            {
                this.status = "Regular";
            }
            else
            {
                this.status = "Bom";
            }
        }
    }

    private void setNota(float nota){
        this.nota = nota;
    }

    public String getStatus(){
        return this.status;
    }

    public int getTotalRespostas(){
        return listRespostas.size();
    }

    public int getTotalRespondidas(){
        return this.respostasRespondidas;
    }

    public float getNota(){
        return this.nota;
    }


}
