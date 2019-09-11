package sanatories.uz.sanatories.mygallery;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sanatories.uz.sanatories.CompanyActivity;
import sanatories.uz.sanatories.R;
import sanatories.uz.sanatories.adapters.RecyclerItemClickListener;
import sanatories.uz.sanatories.entity.Image;
import sanatories.uz.sanatories.entity.RoomImage;
import sanatories.uz.sanatories.rest.RestConnector;

public class GalleryFragment extends Fragment {
    RecyclerView lux,standart,simple;
    ArrayList<Image> luxList;
    ArrayList<Image> standList;
    ArrayList<Image> simpList;
    RecyclerItemClickListener nest;
    public static String selected;
    private RecyclerItemClickListener nest1;
    private RecyclerItemClickListener nest2;
    public static String str="";
    TextView tv1,tv2,tv3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.common_gallery_view,null);
        lux = v.findViewById(R.id.lux_list);
        standart = v.findViewById(R.id.standart_list);
        simple = v.findViewById(R.id.simple_list);
        luxList = new ArrayList<>();
        simpList = new ArrayList<>();
        standList = new ArrayList<>();
        tv1= v.findViewById(R.id.first);
        tv2 = v.findViewById(R.id.sec);
        tv3 = v.findViewById(R.id.thir);
        loader();


        return v;
    }
    void loader(){
        final ImageAdapter ia = new ImageAdapter(luxList,getActivity().getResources().getDisplayMetrics());
        lux.setAdapter(ia);
        lux.getAdapter().notifyDataSetChanged();
        lux.removeOnItemTouchListener(nest);
        nest = new RecyclerItemClickListener(lux.getContext(),lux, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                System.out.println("dasdadasd");
                selected = luxList.get(position).getPath();
                Intent intent = new Intent(CompanyActivity.instance,GalleryPreview.class);
//                intent.putExtra("path",luxList.get(position).getPath());
                str=luxList.get(position).getPath();
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
        lux.addOnItemTouchListener(nest);

        final ImageAdapter ia1 = new ImageAdapter(standList,getActivity().getResources().getDisplayMetrics());

        standart.setAdapter(ia1);
        standart.getAdapter().notifyDataSetChanged();
        standart.removeOnItemTouchListener(nest1);
        nest1 = new RecyclerItemClickListener(standart.getContext(),standart, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                System.out.println("dasdadasd");
                selected = standList.get(position).getPath();
                Intent intent = new Intent(CompanyActivity.instance,GalleryPreview.class);
//                intent.putExtra("path",standList.get(position).getPath());
                str=standList.get(position).getPath();
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
        standart.addOnItemTouchListener(nest1);

        final ImageAdapter ia2 = new ImageAdapter(simpList,getActivity().getResources().getDisplayMetrics());
        simple.setAdapter(ia2);
        simple.getAdapter().notifyDataSetChanged();
        simple.removeOnItemTouchListener(nest2);
        nest2 = new RecyclerItemClickListener(simple.getContext(),simple, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                selected = simpList.get(position).getPath();
                System.out.println("selected path "+selected);
                Intent intent = new Intent(CompanyActivity.instance,GalleryPreview.class);
//                intent.putExtra("path",simpList.get(position).getPath());
                str=simpList.get(position).getPath();
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
        simple.addOnItemTouchListener(nest2);


        RestConnector.getInstance().getRestBasicApi().getRoomsImage(CompanyActivity.instance.company).enqueue(new Callback<ArrayList<RoomImage>>() {
            @Override
            public void onResponse(Call<ArrayList<RoomImage>> call, Response<ArrayList<RoomImage>> response) {
                ArrayList<RoomImage> l = response.body();

                System.out.println("rooms list "+l.size());

                ia.setDataList(l.get(0).getImageList());
                ia1.setDataList(l.get(1).getImageList());
                ia2.setDataList(l.get(2).getImageList());

                tv1.setText(l.get(0).getRoomId().getTypeId().getName());
                tv2.setText(l.get(1).getRoomId().getTypeId().getName());
                tv3.setText(l.get(2).getRoomId().getTypeId().getName());
            }

            @Override
            public void onFailure(Call<ArrayList<RoomImage>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }


     class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

        List<Image> list;
        DisplayMetrics des;
        public ImageAdapter(List<Image> list,DisplayMetrics dm) {
            this.des=dm;
            this.list = list;
        }

        @Override
        public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item,parent,false);
            return new ImageHolder(v);
        }

         @Override
         public void onBindViewHolder(ImageHolder holder, int position) {
            byte [] i = Base64.decode(list.get(position).getPath(),Base64.DEFAULT);
            Bitmap b = BitmapFactory.decodeByteArray(i,0,i.length);

            holder.iv.setImageBitmap(b);
             System.out.println(list.get(position).getName());
         }


        @Override
        public int getItemCount() {
            return list.size();
        }

         public void setDataList(ArrayList<Image> imageList) {
            list.clear();
            list.addAll(imageList);

            notifyDataSetChanged();
         }


         class ImageHolder extends RecyclerView.ViewHolder{
            ImageView iv;

            public ImageHolder(View itemView) {
                super(itemView);
                iv = itemView.findViewById(R.id.img);

            }
        }

    }




}
