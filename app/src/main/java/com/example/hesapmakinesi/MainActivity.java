package com.example.hesapmakinesi;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    double cevap,sayi;
    int secim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        EditText label=findViewById(R.id.labelEkrani);
        EditText sonucEkrani=findViewById(R.id.sonucEkrani);
        Button esittirbuton=findViewById(R.id.esittirButon);
        Button karakterSil=findViewById(R.id.karakterSilButonu);
        Button Cbuton=findViewById(R.id.CButonu);
        Button karealbuton=findViewById(R.id.kareAlButonu);
        Button toplamabuton=findViewById(R.id.artıButonu);
        Button cikarmabuton=findViewById(R.id.eksiButonu);
        Button carpmabuton=findViewById(R.id.carpmaButonu);
        Button bolmebuton=findViewById(R.id.bolmeButonu);
        Button kokbuton=findViewById(R.id.kokButonu);
        Button faktoriyelbuton=findViewById(R.id.faktoriyelButonu);
        Button modbuton=findViewById(R.id.modButonu);
        Button artieksibuton=findViewById(R.id.artieksiButonu);
        Button birboluxbuton=findViewById(R.id.birboluxButonu);
        Button noktabuton=findViewById(R.id.noktaButon);



        Button[] button=new Button[10];
        //Buradaki döngü sebebi düğmeleri bulmak için
        for(int i=0;i< button.length;i++){
            int deger=getResources().getIdentifier("button"+i,"id",getPackageName());
            button[i]=findViewById(deger);
        }

        //burada sayılara tıkladığımızda ekrana yazdırılmasını sağlıyor
        for(int i=0;i< button.length;i++) {
            int index = i;
            button[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ekranaDegerEkle(button[index].getText().toString());
                }
            });

        }

        noktabuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=sonucEkrani.getText().toString();

                if(text.contains(".")){
                    return;
                }
                if(text.isEmpty()){
                    sonucEkrani.setText("0.");
                }
                else{
                    sonucEkrani.append(".");
                }
            }
        });

        //Burada eşittir sonucu işlemleri uyguluyor
        esittirbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sonucEkrani.getText().toString().isEmpty()){
                    hesapMakinesi();
                    label.setText("");
                }
            }
        });

        //Burada karakterlerin tek tek silinmesini sağlıyor
        karakterSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text= String.valueOf(sonucEkrani.getText());
                if(!text.isEmpty()){
                    //substring metodu, bir String'den belirli bir aralığı çıkararak yeni bir String elde etmenizi sağlar.
                    text=text.substring(0,text.length()-1);
                    sonucEkrani.setText(text);
                }
            }
        });

        //Burada ekranın tamamen silinmesini sağlıyor
        Cbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonucEkrani.setText("");
                label.setText("");
            }
        });

        //Toplama işlemini yaptıracağız
        toplamabuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sonucEkrani.getText().toString().isEmpty()){
                    //hesapmakinesi() fonksiyonun çağırma sebebimiz
                    // ikinci sayıyı girdikten sonra eşittire basmam gerekiyor bu komutla birlikte uygulama önceden girilen değerleri hemen hesaplar
                    hesapMakinesi();
                    sayi=Double.parseDouble(sonucEkrani.getText().toString());
                    secim=1;
                    sonucEkrani.setText("");

                    //TextView de + sembolünü renklendirme
                    String metin=sayi+"+";
                    SpannableString spannableMetin=new SpannableString(metin);

                    //+ işaretinin rengini değiştirme
                    ForegroundColorSpan colorSpan=new ForegroundColorSpan(Color.GREEN);
                    spannableMetin.setSpan(
                            colorSpan,
                            metin.indexOf('+'),
                            metin.length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    );
                    label.setText(spannableMetin); // Değiştirilen metni ayarla
                }
            }
        });

        //Çıkarma işlemi
        cikarmabuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sonucEkrani.getText().toString().isEmpty()){
                    //hesapmakinesi() fonksiyonun çağırma sebebimiz
                    // ikinci sayıyı girdikten sonra eşittire basmam gerekiyor bu komutla birlikte uygulama önceden girilen değerleri hemen hesaplar
                    hesapMakinesi();
                    sayi=Double.parseDouble(sonucEkrani.getText().toString());
                    secim=2;
                    sonucEkrani.setText("");;
                    //TextView de + sembolünü renklendirme
                    String metin=sayi+"-";
                    SpannableString spannableMetin=new SpannableString(metin);

                    //+ işaretinin rengini değiştirme
                    ForegroundColorSpan colorSpan=new ForegroundColorSpan(Color.GREEN);
                    spannableMetin.setSpan(
                            colorSpan,
                            metin.indexOf('-'),
                            metin.length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    );
                    label.setText(spannableMetin); // Değiştirilen metni ayarla
                }
            }
        });

        //Çarpma işlemi
        carpmabuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sonucEkrani.getText().toString().isEmpty()){
                    //hesapmakinesi() fonksiyonun çağırma sebebimiz
                    // ikinci sayıyı girdikten sonra eşittire basmam gerekiyor bu komutla birlikte uygulama önceden girilen değerleri hemen hesaplar
                    hesapMakinesi();
                    sayi=Double.parseDouble(sonucEkrani.getText().toString());
                    secim=3;
                    sonucEkrani.setText("");
                    //TextView de + sembolünü renklendirme
                    String metin=sayi+"x";
                    SpannableString spannableMetin=new SpannableString(metin);

                    //+ işaretinin rengini değiştirme
                    ForegroundColorSpan colorSpan=new ForegroundColorSpan(Color.GREEN);
                    spannableMetin.setSpan(
                            colorSpan,
                            metin.indexOf('x'),
                            metin.length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    );
                    label.setText(spannableMetin); // Değiştirilen metni ayarla
                }
            }
        });

        //Bölme işlemi
        bolmebuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sonucEkrani.getText().toString().isEmpty()){
                    //hesapmakinesi() fonksiyonun çağırma sebebimiz
                    // ikinci sayıyı girdikten sonra eşittire basmam gerekiyor bu komutla birlikte uygulama önceden girilen değerleri hemen hesaplar
                    hesapMakinesi();
                    sayi=Double.parseDouble(sonucEkrani.getText().toString());
                    secim=4;
                    sonucEkrani.setText("");
                    //TextView de + sembolünü renklendirme
                    String metin=sayi+"÷";
                    SpannableString spannableMetin=new SpannableString(metin);

                    //+ işaretinin rengini değiştirme
                    ForegroundColorSpan colorSpan=new ForegroundColorSpan(Color.GREEN);
                    spannableMetin.setSpan(
                            colorSpan,
                            metin.indexOf('÷'),
                            metin.length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    );
                    label.setText(spannableMetin); // Değiştirilen metni ayarla
                }
            }
        });
        //Kök alma işlemi
        kokbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sonucEkrani.getText().toString().isEmpty()){
                    sayi=Double.parseDouble(sonucEkrani.getText().toString());
                    if(sayi<0){
                        Toast.makeText(getApplicationContext(),"Hata",Toast.LENGTH_SHORT).show();
                    }
                    cevap=Math.sqrt(sayi);
                    sonucEkrani.setText(Double.toString(cevap));
                    label.setText("√"+sayi);
                }

            }
        });

        //Faktoriyel
        faktoriyelbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sonucEkrani.getText().toString().isEmpty()){
                    sayi=Double.parseDouble(sonucEkrani.getText().toString());
                    if (sayi < 0) {
                        Toast.makeText(getApplicationContext(),"Hata",Toast.LENGTH_SHORT).show();
                    } else if (sayi > 20) { // Örneğin, faktöriyel çok büyük olabileceğinden limit belirleyin
                        Toast.makeText(getApplicationContext(),"Hata: Çok büyük sayı",Toast.LENGTH_SHORT).show();
                    } else {
                        cevap=faktoriyelHesapla(sayi);
                        sonucEkrani.setText(Double.toString(cevap));
                        label.setText(sayi+"!");
                    }
                }
            }
        });

        //Karesini alma işlemi yapıyoruz
        karealbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sonucEkrani.getText().toString().isEmpty()){
                    sayi=Double.parseDouble(sonucEkrani.getText().toString());
                    cevap=sayi*sayi;
                    sonucEkrani.setText(Double.toString(cevap));
                    label.setText(sayi+"²");
                }
            }
        });

        //1/x
        birboluxbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sonucEkrani.getText().toString().isEmpty()){
                    sayi=Double.parseDouble(sonucEkrani.getText().toString());
                    sayi=1/sayi;
                    sonucEkrani.setText(Double.toString(sayi));
                    label.setText("");
                }
            }
        });

        //artı eksi
        artieksibuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sonucEkrani.getText().toString().isEmpty()){
                    sayi = Double.parseDouble(sonucEkrani.getText().toString());
                    sayi = -sayi; // Sayının işaretini değiştir
                    sonucEkrani.setText(Double.toString(sayi));
                    label.setText("");
                }
            }
        });

        //mod alma
        modbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sonucEkrani.getText().toString().isEmpty()){
                    sayi=Double.parseDouble(sonucEkrani.getText().toString());
                    secim=5;
                    sonucEkrani.setText("");
                    label.setText(sayi+"%");
                }
            }
        });





    }
    public void ekranaDegerEkle(String deger){
        EditText sonucEkrani=findViewById(R.id.sonucEkrani);
        sonucEkrani.setText(sonucEkrani.getText()+deger);
    }

    @SuppressLint("SetTextI18n")
    public void hesapMakinesi(){
        EditText sonucEkrani=findViewById(R.id.sonucEkrani);

        try{
            double islenenSayi=Double.parseDouble(sonucEkrani.getText().toString());

            switch (secim){

                case 1:{
                    cevap=sayi+islenenSayi;
                    sonucEkrani.setText(Double.toString(cevap));
                    break;
                }
                case 2:{
                    cevap=sayi-islenenSayi;
                    sonucEkrani.setText(Double.toString(cevap));
                    break;
                }
                case 3:{
                    cevap=sayi*islenenSayi;
                    sonucEkrani.setText(Double.toString(cevap));
                    break;
                }
                case 4:{
                    if(islenenSayi==0){
                        Toast.makeText(getApplicationContext(),"Geçersiz sayı girişi",Toast.LENGTH_SHORT).show();
                        sonucEkrani.setText("");
                        return;
                    }
                    else{
                        cevap=sayi/islenenSayi;
                        break;
                    }
                }
                case 5:{
                    if(islenenSayi==0){
                        Toast.makeText(getApplicationContext(),"Geçersiz sayı girişi",Toast.LENGTH_SHORT).show();
                        sonucEkrani.setText("");
                        return;
                    }
                    else{
                        cevap=sayi%islenenSayi;
                        sonucEkrani.setText(Double.toString(cevap));
                        break;
                    }
                }
            }
        }
        catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Geçersiz sayı girişi",Toast.LENGTH_SHORT).show();
        }
    }



    public double faktoriyelHesapla(double sayi){

        int i,sonuc = 1;

        for(i=1;i<=sayi;i++){
            sonuc*=i;
        }
        return sonuc;
    }
}