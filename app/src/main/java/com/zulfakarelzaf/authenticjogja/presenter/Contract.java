package com.zulfakarelzaf.authenticjogja.presenter;

import java.util.ArrayList;

public class Contract {
    public interface Presenter{
        void getEvent();
        void getCraft();
        void getCulinnary(int id);
        void getLanguange(int id);
        void getCategory();
        void getCategoryLanguage();
        void getTrending();
        void onFinishCall(ArrayList list);
        void onFailurCall(Throwable t);
    }

    public interface View{
         void onCompleteCall(ArrayList data);
    }

    public interface Intractor{
        void getEvent();
        void getCraft();
        void getCulinnary(int id);
        void getLanguange(int id);
        void getCategory();
        void getCategoryLanguage();
        void getTrending();
    }
}
