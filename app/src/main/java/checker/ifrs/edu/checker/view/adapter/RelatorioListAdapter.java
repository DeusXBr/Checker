package checker.ifrs.edu.checker.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.vo.Avaliacao;

public class RelatorioListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Avaliacao> mAvaliacaoList;

    public RelatorioListAdapter(Context mContext, List<Avaliacao> mAvaliacaoList) {
        this.mContext = mContext;
        this.mAvaliacaoList = mAvaliacaoList;
    }

    @Override
    public int getCount() {
        return mAvaliacaoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAvaliacaoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = view.inflate(mContext, R.layout.item_listview_listarelatorio, null); // set o layout de cada item

        TextView textViewTitulo = (TextView) v.findViewById(R.id.seila); //recupera objeto do layout
        textViewTitulo.setText(mAvaliacaoList.get(position).getNome()); // set o text do textView

        TextView textViewDataCriacao = (TextView) v.findViewById(R.id.data); //recupera objeto do layout
        textViewDataCriacao.setText(mAvaliacaoList.get(position).getDataCriacao()); // set o text do textView

        return v;
    }
}
