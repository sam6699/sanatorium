package sanatories.uz.sanatories.adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sanatories.uz.sanatories.R;
import sanatories.uz.sanatories.entity.Room;
import sanatories.uz.sanatories.entity.RoomImage;
import sanatories.uz.sanatories.entity.RoomType;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.TypeHolder>  {
    List<Room> list;

    public TypeAdapter(ArrayList<Room> ls) {
            this.list = ls;

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public TypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.type_item,parent,false);
        TypeHolder lih = new TypeHolder(v);
        return  lih;
    }

    @Override
    public void onBindViewHolder(TypeHolder holder, int position) {
            holder.tv1.setText(list.get(position).getTypeId().getName());
            holder.tv2.setText(list.get(position).getPrice()+" so'm  kunga" );




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TypeHolder extends RecyclerView.ViewHolder {
        TextView tv1,tv2;
        ConstraintLayout cv;
        public TypeHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.type_item_content);
            tv1 = cv.findViewById(R.id.type_name);
            tv2 = cv.findViewById(R.id.price);


        }
    }
}
