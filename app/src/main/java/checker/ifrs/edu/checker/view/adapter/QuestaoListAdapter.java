package checker.ifrs.edu.checker.view.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.model.bll.AvaliacaoBll;
import checker.ifrs.edu.checker.model.bll.QuestaoBll;
import checker.ifrs.edu.checker.model.bll.RespostaBll;
import checker.ifrs.edu.checker.view.activities.MainActivity;
import checker.ifrs.edu.checker.vo.Avaliacao;
import checker.ifrs.edu.checker.vo.Questao;
import checker.ifrs.edu.checker.vo.RealmInt;
import checker.ifrs.edu.checker.vo.Resposta;
import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmList;
import io.realm.RealmResults;

public class QuestaoListAdapter extends RealmBaseAdapter<Questao> implements ListAdapter {

    public static final String TAG = "MeuTeste";

    private RealmResults<Questao> realmResults;
    private HashMap<Integer, Integer> myMap;

    public QuestaoListAdapter(Context context, RealmResults<Questao> realmResults) {
        super(context, realmResults);
        this.realmResults = realmResults;
        this.myMap = new HashMap<>();
    }

    @Override
    public View getView(final int position, View converterView, ViewGroup viewGroup) {

        Log.i("MeuTeste", "Posicao: " + position);

        CustomViewHolder holder;

        if(converterView == null)
        {
            converterView = inflater.inflate(R.layout.item_listview_questao, viewGroup, false);
            holder = new CustomViewHolder(converterView);
            converterView.setTag( holder );
        }
        else
        {
            holder = (CustomViewHolder) converterView.getTag();
        }

        Questao questao = realmResults.get(position);

        holder.pergunta.setText((position+1)+". "+questao.getPergunta());
        holder.radioGroup.setTag(position);

        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch(checkedId)
                {
                    case R.id.mRadio1:myMap.put(realmResults.get(position).getId(),1);
                        break;
                    case R.id.mRadio2:myMap.put(realmResults.get(position).getId(),2);
                        break;
                    case R.id.mRadio3:myMap.put(realmResults.get(position).getId(),3);
                        break;
                    case R.id.mRadio4:myMap.put(realmResults.get(position).getId(),4);
                        break;
                }
            }
        });

        return converterView;
    }

    public HashMap<Integer, Integer> getRespostas(){
        return this.myMap;
    }

    private static class CustomViewHolder{
//        TextView numeracao = null;
        TextView pergunta = null;
        RadioGroup radioGroup;
        RadioButton radioButton1, radioButton2, radioButton3, radioButton4;

        CustomViewHolder(View view){
//            numeracao = (TextView) view.findViewById(R.id.textview_numeracao);
            pergunta = (TextView) view.findViewById(R.id.textview_pergunta);
            radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup_linha);
            radioButton1 = (RadioButton) view.findViewById(R.id.mRadio1);
            radioButton2 = (RadioButton) view.findViewById(R.id.mRadio2);
            radioButton3 = (RadioButton) view.findViewById(R.id.mRadio3);
            radioButton4 = (RadioButton) view.findViewById(R.id.mRadio4);
        }
    }


//    private Context mContext;
//    private List<Questao> mQuestaoList;
//    private Map<Integer, Integer> myMap;
//
//    public QuestaoListAdapter(Context mContext, List<Questao> mQuestaoList) {
//        this.mContext = mContext;
//        this.mQuestaoList = mQuestaoList;
//
//        this.myMap = new HashMap<Integer, Integer>();
//    }
//
//    @Override
//    public int getCount() {
//        return mQuestaoList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return mQuestaoList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup viewGroup) {
//        //TODO arrumar layout -> item_listview_questao.xml, texto cortado
//
//        View view = convertView;
//
//        if(view == null){
//            LayoutInflater li = LayoutInflater.from(mContext);
//            view = li.inflate(R.layout.item_listview_questao, null);
//        }
//
//        // pega os dados do sharedPreference
//        SharedPreferences sharedPrefs = mContext.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
//        final String avaliacaoTitulo = sharedPrefs.getString("avaliacaoTitulo", null);
//
//        TextView textViewNumeracao = (TextView) view.findViewById(R.id.textview_numeracao); //recupera objeto do layout
//        TextView textViewPergunta = (TextView) view.findViewById(R.id.textview_pergunta); //recupera objeto do layout
//
//        textViewNumeracao.setText(" "+(position + 1)); // coloca a numeracao das perguntas
//        textViewPergunta.setText(mQuestaoList.get(position).getPergunta()); // set o text do textView
//
//        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup_linha);
//
//        if( myMap.get(mQuestaoList.get(position).getId()) != null ){
//            radioGroup.check(myMap.get(mQuestaoList.get(position).getId()));
//        }
//
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int checked) {
//                RadioButton checkedRadioButton = (RadioButton)radioGroup.findViewById(checked);
//
//                boolean isChecked = checkedRadioButton.isChecked();
//                if (isChecked) {
//
//                    AvaliacaoBll avaliacaoBll = new AvaliacaoBll();
//                    Avaliacao avaliacao = avaliacaoBll.getAvaliacao(avaliacaoTitulo);
//
//                    QuestaoBll questaoBll = new QuestaoBll();
//                    Questao questao = questaoBll.getQuestao(mQuestaoList.get(position).getId());
//
//                    RealmInt realmInt = new RealmInt();
//                    realmInt.setValor(checkedRadioButton.getText().toString());
//
//                    myMap.put(mQuestaoList.get(position).getId(), checkedRadioButton.getId());
//
//                    RespostaBll respostaBll = new RespostaBll();
//                    Resposta resposta = respostaBll.getResposta(avaliacao);
//
//                    // verificar se a resposta ja está criada
//                    if(resposta != null){
//                        resposta.setQuestoes(questao);
//                        resposta.setInts(realmInt);
//                    }else{
//                        resposta = new Resposta(avaliacao, questao, realmInt);
//                        respostaBll.addResposta(resposta);
//                    }
//
////                    Log.i("MeuTeste", "Avaliacao: " + resposta.getAvaliacao().getNome());
////                    Log.i("MeuTeste", "Questão: " + resposta.getQuestoes().size());
////                    Log.i("MeuTeste", "Resposta: " + resposta.getInts().size());
//                }
//            }
//        });
//
//
//        return view;
//    }
}
