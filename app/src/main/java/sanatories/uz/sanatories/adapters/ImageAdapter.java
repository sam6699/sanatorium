package sanatories.uz.sanatories.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.content.Context;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import sanatories.uz.sanatories.R;
import sanatories.uz.sanatories.entity.Image;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

    List<Image> list;

    public ImageAdapter(List<Image> list) {
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
            Bitmap b1 = Bitmap.createScaledBitmap(b,200,200,true);

            holder.iv.setImageBitmap(b1);
//            holder.iv.draw(draw(holder.iv.getContext(),b));
//        Glide.with(holder.iv.getContext())
//                .load(b1) // Uri of the picture
//                .into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ImageHolder extends RecyclerView.ViewHolder{
        ImageView iv;

        public ImageHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.img);

        }
    }

   public Canvas draw(Context context,Bitmap b){
        Paint paint = new Paint();
        Canvas canvas = new Canvas();
        canvas.drawColor(Color.YELLOW);


        paint.setFilterBitmap(true);

        int targetWidth  = 50;
        int targetHeight = 50;
        Bitmap bmp = Bitmap.createBitmap(targetWidth, targetHeight,Bitmap.Config.ARGB_8888);
        RectF rectf = new RectF(0, 0, targetWidth, targetHeight);
        Canvas c = new Canvas(bmp);
        Path path = new Path();
        path.addRect(rectf, Path.Direction.CW);
        c.clipPath(path);
        c.drawBitmap( b, new Rect(0, 0, b.getWidth(), b.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), paint);
        Matrix matrix = new Matrix();
        matrix.postScale(1f, 1f);
        Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0, targetWidth, targetHeight, matrix, true);
        int h = b.getHeight();
        canvas.drawBitmap(b, 10,10, paint);
        canvas.drawBitmap(resizedBitmap, 10,10 + h + 10, paint);
        return canvas;

    }

}
