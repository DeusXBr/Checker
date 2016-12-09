package checker.ifrs.edu.checker.utils;

import checker.ifrs.edu.checker.vo.Resposta;
import io.realm.RealmList;

public class CalculaAvaliacaoUtils {

    private RealmList<Resposta> listRespostas;
    private float nota;
    private int respostasRespondidas;

    public CalculaAvaliacaoUtils(){}

    public CalculaAvaliacaoUtils(RealmList<Resposta> listRespostas){
        this.listRespostas = listRespostas;

        this.nota = 0;
        this.respostasRespondidas = 0;
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

            return nota;
        }

    }

    private void setNota(float nota){
        this.nota = nota;
    }

    private int getTotalRespostas(){
        return listRespostas.size();
    }

    private int getTotalRespondidas(){
        return this.respostasRespondidas;
    }

    private float getNota(){
        return this.nota;
    }


}
