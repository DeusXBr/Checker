package checker.ifrs.edu.checker.view.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.vo.Resposta;

public class FeedBackListAdapter extends BaseAdapter {

    private Context context;
    private List<Resposta> respostas;


    public FeedBackListAdapter(Context mContext, List<Resposta> listRespostas) {
        Collections.reverse(listRespostas);

        this.context = mContext;
        this.respostas = listRespostas;
    }

    @Override
    public int getCount() {
        return respostas.size();
    }

    @Override
    public Object getItem(int position) {
        return respostas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup)
    {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_listafeedback, viewGroup, false);
        }

        Resposta currentResposta = (Resposta) getItem(position);

        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.linear_layout_filhofeeedback);

        TextView textViewCategoria = (TextView) linearLayout.findViewById(R.id.categoria_feedback);
        textViewCategoria.setText(currentResposta.getQuestao().getCategoria().getNome());

        TextView textViewTitulo = (TextView) linearLayout.findViewById(R.id.pergunta_feedback);
        textViewTitulo.setText( currentResposta.getQuestao().getPergunta() );

        TextView textViewTexto = (TextView) linearLayout.findViewById(R.id.texto_feedback);
        switch (currentResposta.getResposta())
        {
            case R.id.mRadio2: linearLayout.setBackgroundResource(R.drawable.background_shape_ruim2);
                 textViewTexto.setText(currentResposta.getQuestao().getFeedbackErrado());
                 break;

            case R.id.mRadio3: linearLayout.setBackgroundResource(R.drawable.background_shape_regular);
                 textViewTexto.setText(currentResposta.getQuestao().getFeedbackParcial());
                 break;
        }

        return convertView;
    }
}
