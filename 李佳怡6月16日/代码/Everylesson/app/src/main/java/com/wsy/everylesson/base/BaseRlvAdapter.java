package com.wsy.everylesson.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//T 是当前适配器列表数据的泛型
public abstract class BaseRlvAdapter<T> extends RecyclerView.Adapter<BaseRlvAdapter.BaseViewHolder> {
    private OnClickItemLis onClickItem;
    public final Context mContext;
    public final ArrayList<T> mList;

    public BaseRlvAdapter(Context context, ArrayList<T> list){

        this.mContext = context;
        this.mList = list;
    }
    //创建ViewHolder
    @NonNull
    @Override
    public BaseRlvAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent,final int viewType) {
        final View inflate = LayoutInflater.from(mContext).inflate(getLayout(), parent, false);
        final BaseViewHolder holder = new BaseViewHolder(inflate);
        //子条目点击事件,为什么不在onBindViewHolder里面去写??
        //因为每显示一个条目onBindViewholder就会走一次
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItem!=null){
                    //同holder拿到position
                    onClickItem.onClickItem(inflate,holder.getLayoutPosition());
                }
            }
        });
        return holder;
    }

    protected abstract int getLayout();

    /**
     * 处理ui的核心方法
     * 绑定数据，怎么样就可以把数据设置上去了？控件，数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull BaseRlvAdapter.BaseViewHolder holder, int position) {
        T t = mList.get(position);
        //绑定数据，怎么样就可以把数据设置上去了？控件，数据
        bindDate(holder,t);
    }

    protected abstract void bindDate(BaseRlvAdapter.BaseViewHolder holder, T t);

    //获取子条目数量
    @Override
    public int getItemCount() {
        return mList.size();
    }
//添加更多数据，加载更多是使用
    public void addDate(List<T> list){
        if (list!=null&&list.size()>0){
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }
//刷新数据，下拉刷新使用
    public void updateDate(List<T> list){
        if (list!=null&&list.size()>0){
            list.clear();
            list.addAll(list);
            notifyDataSetChanged();
        }
    }
    //ViewHolder里面是要查找控件并保存成成员变量的
    //但是因为这个viewholder在父类中,父类也不知道子类中有哪些控件
    //如果将findViewByid及保存控件交给子类,那么就必须写一个子类出来,那就和系统的
    //RecyclerView.ViewHolder一样了,这个类就失去作用了
    //应该在这个类中完成找控件+存储控件的功能
    //使用viewholder 的好处就是避免不必要的findviewbyid
    public static class BaseViewHolder extends RecyclerView.ViewHolder{
        //为了不用每次的findViewbyid,这里拿容器去把找出来的控件存储起来
        //没有findviewbyid的find,找过的去容器里面拿
        //ArrayMap,SparseArray 都是Android专门搞得容器,比hashmap要轻量级
        SparseArray<View> mViews=new SparseArray();
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            ///在findViewById的时候,这个类并不知道究竟是哪个控件的id
            //itemView.findViewById(R.id.xx);
        }

        //通过viewholder的一个方法去帮我们找控件
        public View findView(@IdRes int id){
            View view = mViews.get(id);
            //如果容器中没有资格控件
            if (view==null){
                //找控件
                view=itemView.findViewById(id);
                //存储控件
                mViews.append(id,view);
            }
            return view;
        }
        //设置文本
        public void setText(@IdRes int id, String text){
            try {
                TextView view = (TextView) findView(id);
                view.setText(text);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public void setOnClickItemLis(OnClickItemLis onClickItemLis) {
        this.onClickItem = onClickItem;
    }

    public interface OnClickItemLis{
        void onClickItem(View view, int position);
    }
}
