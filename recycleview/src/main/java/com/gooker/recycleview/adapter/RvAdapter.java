package com.gooker.recycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gooker.recycleview.R;

import java.util.List;

/**
 * Created by sczhang on 15/6/29. 下午4:25
 * Package Name com.gooker.recycleview.adapter
 * Project Name DFG
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvHolder>{

    private final LayoutInflater mLayoutInflater;
    private List<String> mDatas ;
    private Context mContext ;

    public RvAdapter(Context context,List<String> datas){
        this.mContext = context;
        this.mDatas = datas;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public RvHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RvHolder(mLayoutInflater.inflate(R.layout.ui_rv_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(RvHolder rvHolder, int i) {
        if (null != rvHolder){
            rvHolder.tvMsg.setText(mDatas.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class RvHolder extends RecyclerView.ViewHolder{
        TextView tvMsg ;

        public RvHolder(View itemView) {
            super(itemView);
            tvMsg = (TextView) itemView.findViewById(R.id.tvMsg);
        }
    }
}

