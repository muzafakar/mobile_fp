package com.zulfakarelzaf.authenticjogja.common;

import android.content.Context;

import com.zulfakarelzaf.authenticjogja.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

import com.zulfakarelzaf.authenticjogja.R;


public class Dummy {
    //coordinate test
    public static double latitude = -7.8002752;
    public static double longitude = 110.3844902;
    private Context context;

    public Dummy(Context context) {
        this.context = context;
    }

    public List<ItemModel> getListKuliner() {
        List<ItemModel> kuliner = new ArrayList<>();
        kuliner.add(new ItemModel("https://media.travelingyuk.com/wp-content/uploads/2017/10/Gudeg-Geprek-Jogja.jpg", context.getString(R.string.gudeg)));
        kuliner.add(new ItemModel("https://rajakuliner.com/wp-content/uploads/2017/10/Sate-Ayam.jpg", context.getString(R.string.skewer)));
        kuliner.add(new ItemModel("https://user-uploaded-asset.s3.amazonaws.com/uploads/bootsy/image/860/medium_minuman_tradisional_indonesia_wedang_ronde.jpg", context.getString(R.string.drink)));
        kuliner.add(new ItemModel("https://spartanfitness360.com/wp-content/uploads/2016/10/How-to-Snack-Blog-Post-Pic.jpg", context.getString(R.string.snack)));
        kuliner.add(new ItemModel("http://3.bp.blogspot.com/-Tma7vLYj8qc/VCzjuj1InnI/AAAAAAAADIw/p3_3RFpX2Dc/s1600/nb%2Bjkaf.png", context.getString(R.string.other_food)));
        return kuliner;
    }


    public List<ItemModel> getListKerajinan() {
        List<ItemModel> kerajinan = new ArrayList<>();
        kerajinan.add(new ItemModel("https://silkwindsmagazine.com/wp-content/uploads/2017/04/Morning-evening-batik-1280x715.jpg", context.getString(R.string.batik)));
        kerajinan.add(new ItemModel("https://www.bepetur.com/wp-content/uploads/2016/02/kasongan-head1.jpg", context.getString(R.string.pottery)));
        kerajinan.add(new ItemModel("https://asset.kompas.com/data/photo/2015/05/10/1339549makann780x390.jpg", context.getString(R.string.silver_craft)));

        return kerajinan;
    }


    //TODO Check the events name
    public List<ItemModel> getAcara() {
        List<ItemModel> acara = new ArrayList<>();
        acara.add(new ItemModel("https://i2.wp.com/lpmpressisi.com/wp-content/uploads/2018/05/IMG_5963.jpg?fit=900%2C600&quality=95&ssl=1", "ArtJog 2018"));
        acara.add(new ItemModel("http://img.bisnis.com/thumb/posts/2017/08/20/682180/190817-bi-nh-15-suasana-prambanan-jazz-11.jpg?w=600&h=400", "Prambanan Jazz 2018"));
        acara.add(new ItemModel("http://yogyakarta.panduanwisata.id/files/2015/08/Yogya-Gamelan-Festival-2015-2-gayam16.png", "Gamelan Festifal 2018"));
        return acara;
    }

    public List<ItemModel> getBahasaDaerah() {
        List<ItemModel> bahasaDaerah = new ArrayList<>();
        bahasaDaerah.add(new ItemModel("https://images.unsplash.com/photo-1483378096604-1294ca36d0f3?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=544e1e4f3116624edbcdea444f54537e&w=1000&q=80", context.getString(R.string.transaction)));
        bahasaDaerah.add(new ItemModel("http://www.englishcafe.co.id/wp-content/uploads/2017/03/asking-direction.jpg", context.getString(R.string.ask_direction)));
        bahasaDaerah.add(new ItemModel("http://www.littlethingsmatter.com/wp-content/blogs.dir/1/files/2010/02/Greet-in-Person-image1.jpg", context.getString(R.string.greeting)));
        return bahasaDaerah;
    }
}
