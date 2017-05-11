package checker.ifrs.edu.checker.view.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.vo.Questao;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

public class QuestaoListAdapter extends RealmBaseAdapter<Questao> implements ListAdapter {

    public static final String TAG = "MeuTeste";

    private RealmResults<Questao> realmResults;
    private SparseIntArray sparseIntArray;

    public QuestaoListAdapter(Context context, RealmResults<Questao> realmResults, SparseIntArray realmResultsRespostas) {
        super(context, realmResults);
        this.realmResults = realmResults;

        this.sparseIntArray = new SparseIntArray();
        for (int i = 0; i < realmResultsRespostas.size(); i++) {
            int key = realmResultsRespostas.keyAt(i);
            int value = realmResultsRespostas.get(key);

            sparseIntArray.put(key, value);
        }
    }

    @Override
    public View getView(final int position, View converterView, ViewGroup viewGroup) {

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

        final Questao questao = realmResults.get(position);
        holder.pergunta.setText((position+1) + " . " + questao.getPergunta());

        holder.radioGroup.setOnCheckedChangeListener(null);
        holder.radioGroup.clearCheck();

        if ( sparseIntArray.get(questao.getId()) > -1 )
        {
            holder.radioGroup.check(sparseIntArray.get(questao.getId()));
        }
        else
        {
            holder.radioGroup.clearCheck();
        }

        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
            {
                // metodo é chamado quando eu escolho e presiono uma opção do radio group.

                if ( checkedId > -1 )
                {
                    sparseIntArray.put(questao.getId(), checkedId);
                }
                else
                {
                    if ( sparseIntArray.indexOfKey(position) > -1 )
                    {
                        sparseIntArray.removeAt(sparseIntArray.indexOfKey(position));
                    }
                }
            }
        });


        return converterView;
    }

    public SparseIntArray getRespostas()
    {
        return this.sparseIntArray;
    }

    private static class CustomViewHolder{
        TextView pergunta = null;
        RadioGroup radioGroup;

        CustomViewHolder(View view){
            pergunta = (TextView) view.findViewById(R.id.textview_pergunta);
            radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup_linha);
        }
    }

}











