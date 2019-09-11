package sanatories.uz.sanatories;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sanatories.uz.sanatories.entity.Reserve;
import sanatories.uz.sanatories.entity.Room;
import sanatories.uz.sanatories.rest.RestConnector;

import static android.app.Activity.RESULT_OK;

public class ReserveFragment extends Fragment {
    Button photo,bdate;
    EditText lname,fname,mname,pasport;
    Button date;
    String date1="",date2="";
    EditText inn;
    EditText phone;
    EditText mail;
    TextView total;
    Button type;
    ImageView img;
    int company;
    public Reserve reserve;
    public static ReserveFragment instance;
    Button reg;
    int price=0;
    boolean isSale=false;
    static ArrayList<Room> rooms;
    private static final int CAMERA_REQUEST = 0;
    ImageButton cash,click,payme;
    private DatePickerDialog datePickerDialog;
    private int totalN=0;
    private String strPay="";
    private int clicked=0;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View v =  inflater.inflate(R.layout.content_reserve_fragment, null);
                    reserve = new Reserve();
            instance = this;
            photo = v.findViewById(R.id.btn_photo);
            pasport = v.findViewById(R.id.pasport);
            bdate = v.findViewById(R.id.bdate);
            type = v.findViewById(R.id.type);
            fname = v.findViewById(R.id.fname);
            lname = v.findViewById(R.id.lname);
            mname = v.findViewById(R.id.mname);
            date = v.findViewById(R.id.date);
            inn = v.findViewById(R.id.inn);
            phone = v.findViewById(R.id.pay_phone);
            mail = v.findViewById(R.id.mail);
            total = v.findViewById(R.id.total);
            img = v.findViewById(R.id.photo);
            reg = v.findViewById(R.id.reg);
            initEvents();
            rooms = new ArrayList<>();
            company = CompanyActivity.instance.company;
            payme = v.findViewById(R.id.payme);
            click = v.findViewById(R.id.click);
            cash = v.findViewById(R.id.cash);

            cash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isClick();
                    strPay="Naqd";
                    cash.setBackgroundTintMode(PorterDuff.Mode.LIGHTEN);
                    cash.setBackgroundColor(Color.GREEN);
                }
            });

            click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isClick();
                    click.setBackgroundTintMode(PorterDuff.Mode.LIGHTEN);
                    click.setBackgroundColor(Color.GREEN);
                    click("Click",totalN+"");
                }
            });

        payme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClick();
                payme.setBackgroundTintMode(PorterDuff.Mode.LIGHTEN);
                payme.setBackgroundColor(Color.GREEN);
                click("PayMe",totalN+"");
            }
        });

            RestConnector.getInstance().getRestBasicApi().getRooms(company).enqueue(new Callback<ArrayList<Room>>() {
                @Override
                public void onResponse(Call<ArrayList<Room>> call, Response<ArrayList<Room>> response) {
                    rooms.addAll(response.body());
                }

                @Override
                public void onFailure(Call<ArrayList<Room>> call, Throwable t) {

                }
            });




        return v;
    }

    void click(String payType,String sum){
            Intent intent = new Intent(getActivity(),PayActivity.class);
            strPay=payType;
        System.out.println("strPay "+strPay);

            intent.putExtra("payType",payType);
            intent.putExtra("sum",sum);
            startActivity(intent);

    }

    void isClick(){
        cash.setBackgroundColor(Color.WHITE);
        payme.setBackgroundColor(Color.WHITE);
        click.setBackgroundColor(Color.WHITE);
    }


    public void initEvents(){
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);


            }
        });

        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent typeIntent = new Intent(type.getContext(),TypeModal.class);
                startActivityForResult(typeIntent,10);
            }
        });



        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            verifyAll();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDateTimeField();
            }
        });

        bdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDateTimeField1();
            }
        });


    }

    private void setDateTimeField() {
        final Calendar newCalendar = Calendar.getInstance();
            final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
        DatePickerDialog datePickerDialog1 = new DatePickerDialog(CompanyActivity.instance,R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                newCalendar.set(year, monthOfYear, dayOfMonth, 0, 0);
                date.setText(sdf.format(newCalendar.getTime()));
                date1 = date.getText().toString();
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog1.show();
        System.out.println("reg date1 "+date1);


    }

    private void setDateTimeField1() {
        final Calendar newCalendar = Calendar.getInstance();
        Date date = new Date();


        final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
         datePickerDialog = new DatePickerDialog(CompanyActivity.instance,R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                newCalendar.set(year, monthOfYear, dayOfMonth, 0, 0);
                bdate.setText(sdf.format(newCalendar.getTime()));
                date2 = bdate.getText().toString();
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
        System.out.println("reg date2 "+date2);

    }

    public void verifyAll(){
        int isValid=0;
        if (!fname.getText().toString().equals("")){
            reserve.setName(fname.getText().toString()+" ");
            isValid++;
        }else {
            fname.setError("Familiyangizni kiriting  ");

        }
        if (!mname.getText().toString().equals("")){
            reserve.setName(reserve.getName()+mname.getText().toString()+"  ");
            isValid++;
        }else {
            fname.setError("Ismingizni kiriting ");

        }
        if (!lname.getText().toString().equals("")){
            reserve.setName(reserve.getName()+lname.getText().toString()+"  ");
            isValid++;
        }else {
            lname.setError("SHarifingizni kiriting ");

        }
        if (!pasport.getText().toString().equals("")){
            if (pasport.getText().length()==9){
                if ('A'<=pasport.getText().charAt(0)&&pasport.getText().charAt(0)<='I'&&'A'<=pasport.getText().charAt(1)&&pasport.getText().charAt(1)<='I'){
                    isValid++;
                    reserve.setPasport(pasport.getText().toString());
                }else
                    pasport.setError("Pasportni seriyasi kiritilgan");

            }else
                pasport.setError("Pasportni seriyasi kiritilgan");

            reserve.setPasport(pasport.getText().toString());
        }else {
            pasport.setError("Pasportni seriyasini kiriting");

        }
        if (!date1.equals("")){
            System.out.println("date1 "+date1);

            reserve.setData(date.getText().toString());
            isValid++;
        }else {
            Toast.makeText(CompanyActivity.instance,"Kelish sanani tanlang ",Toast.LENGTH_LONG).show();
        }
        if (!date2.equals("")){
            System.out.println("date2 "+date2);
            reserve.setBData(bdate.getText().toString());
            isValid++;
        }else {
            Toast.makeText(CompanyActivity.instance,"Tugilgan sanani tanlang ",Toast.LENGTH_LONG).show();
        }
        if (!inn.getText().toString().equals("")&&inn.getText().length()==9){
            isValid++;
            reserve.setInn(Integer.parseInt(inn.getText().toString()));
        }else
            inn.setError("INN raqamingiz to'liq emas");
        if (!mail.getText().toString().equals("")){
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(mail.getText().toString()).matches())
            {
                isValid++;
                reserve.setMail(mail.getText().toString());
            }else
                mail.setError("Eleqtron manzil xato kiritilgan");


        }else
            Toast.makeText(CompanyActivity.instance,"Electron mainzilingizni kiriting ",Toast.LENGTH_LONG).show();
        if (!phone.getText().toString().equals("")&&phone.getText().length()==12){
            isValid++;
            reserve.setTelefon(phone.getText().toString());

        }else
            phone.setError("Telefon ramingiz to'liq emas ");
        if (strPay.equals("")){
            Toast.makeText(CompanyActivity.instance,"To'lov turini tanlang ",Toast.LENGTH_LONG).show();

        }else {
            reserve.setPayType(strPay);
            reserve.setTotal(totalN);
            reserve.setCompany(CompanyActivity.instance.company);
            System.out.println("verify total: "+totalN);
            isValid++;
        }
        System.out.println("isValid "+isValid);
        if (isValid==10){
            RestConnector.getInstance().getRestBasicApi().addReserve(reserve).enqueue(new Callback<Reserve>() {
                @Override
                public void onResponse(Call<Reserve> call, Response<Reserve> response) {

                    Toast.makeText(CompanyActivity.instance,"Arizangiz yuborildi tez qunda siz bilan habaralashadilar",Toast.LENGTH_LONG).show();


                }

                @Override
                public void onFailure(Call<Reserve> call, Throwable t) {
                    Toast.makeText(CompanyActivity.instance,"Hato ro'y berdi",Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });

            fname.setText("");
            lname.setText("");
            mname.setText("");
            pasport.setText("");
            date2="";
            date1="";
            date.setText("band qilish sanasi");
            bdate.setText("Tugilgan sana");
            inn.setText("");
            mail.setText("");
            phone.setText("");
            strPay="";
            totalN=0;
            type.setText("Xona turini tanlang");
            total.setText("jami");
            reserve = new Reserve();
            img.setBackgroundColor(Color.WHITE);
            isClick();
        }




    }

    public int  calculate(int price,int days){
        int temp = 0;

        if (days!=0){

            if (isSale){
                temp= (int) (days*price*0.5);

                total.setText("Jami "+days+" kunga + 50% chegirma yollanma uchun : "+temp);
            }
            else{
                temp=days*price;
                total.setText("Jami "+days+" kunga: "+temp);

            }
        }
        totalN = temp;
        System.out.println("temp "+temp);
        return  temp;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOS);

            String s = null;
            byte[] temp = byteArrayOS.toByteArray();
            s=Base64.encodeToString(temp,Base64.DEFAULT);
            img.setImageBitmap(bitmap);
            reserve.setPath(s);
            isSale = true;
        }
        else if(requestCode ==10 &&resultCode==RESULT_OK){
            String s = data.getStringExtra("name");
            int id = data.getIntExtra("room_id",0);
            price = data.getIntExtra("price",0);
            reserve.setRoom_id(id);
            type.setText("xona turi "+s);
            calculate(price,CompanyActivity.instance.days);
        }
    }
}
