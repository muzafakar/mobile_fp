package com.zulfakarelzaf.authenticjogja.common;

import com.zulfakarelzaf.authenticjogja.database.Saved;
import com.zulfakarelzaf.authenticjogja.model.CategoryFood;
import com.zulfakarelzaf.authenticjogja.model.CategoryLanguages;
import com.zulfakarelzaf.authenticjogja.model.Craft;
import com.zulfakarelzaf.authenticjogja.model.Culinaries;
import com.zulfakarelzaf.authenticjogja.model.Event;
import com.zulfakarelzaf.authenticjogja.model.Languages;
import com.zulfakarelzaf.authenticjogja.retrofit.IAuthenticJogja;
import com.zulfakarelzaf.authenticjogja.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

public class Common {

    private static String API_URI = "https://api-authentic.herokuapp.com/api/v1/";
    private static String TESTER_API_URI = "https://testerapi.herokuapp.com/api/v1/";

    public static List<Saved> savedDatabase = null;
    public static ArrayList<Culinaries> culinaries = null;
    private static ArrayList<Craft> crafts = null;
    private static ArrayList<Event> events = null;
    private static ArrayList<Languages> languages = null;
    private static ArrayList<CategoryFood> categories = null;
    private static ArrayList<CategoryLanguages> categoryLanguages = null;

    public static List<Saved> getSavedDatabase() {
        return savedDatabase;
    }

    public static void setSavedDatabase(List<Saved> savedDatabase) {
        Common.savedDatabase = savedDatabase;
    }

    public static List<String> getLanguagesUrl(){
        List<String> url = new ArrayList<>();
        url.add("http://agusart.tk/images/native/transaksi.png");
        url.add("http://agusart.tk/images/native/greetings.png");
        url.add("http://agusart.tk/images/native/direction.png");
        url.add("http://agusart.tk/images/native/number.png");
        return url;
    }

    public static ArrayList<CategoryFood> getCategories() {
        //if(categories == null) categories = new ArrayList<>();
        return categories;
    }

    public static void setCulinaries(ArrayList<Culinaries> culinaries) {
        Common.culinaries = culinaries;
    }

    public static void setCrafts(ArrayList<Craft> crafts) {
        Common.crafts = crafts;
    }

    public static void setEvents(ArrayList<Event> events) {
        Common.events = events;
    }

    public static void setLanguages(ArrayList<Languages> languages) {
        Common.languages = languages;
    }

    public static void setCategories(ArrayList<CategoryFood> categories) {
        Common.categories = categories;
    }

    public static void setCategoryLanguages(ArrayList<CategoryLanguages> categoryLanguages) {
        Common.categoryLanguages = categoryLanguages;
    }

    public static ArrayList<CategoryLanguages> getCategoryLanguages() {

        //if(categoryLanguages == null) categoryLanguages = new ArrayList<>();
        return categoryLanguages;
    }

    public static ArrayList<Languages> getLanguages() {

        //if(languages == null) languages = new ArrayList<>();
        return languages;
    }


    public static ArrayList<Culinaries> getCulinaries() {

        return culinaries;
    }

    public static ArrayList<Craft> getCrafts() {

        return crafts;
    }

    public static ArrayList<Event> getEvents() {

        return events;
    }


    public static IAuthenticJogja createService() {
        return RetrofitClient.getClient(API_URI).create(IAuthenticJogja.class);
    }

    public static IAuthenticJogja createServiceTester() {
        return RetrofitClient.getClient(TESTER_API_URI).create(IAuthenticJogja.class);
    }
}
