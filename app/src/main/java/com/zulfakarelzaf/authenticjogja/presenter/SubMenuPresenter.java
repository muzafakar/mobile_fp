package com.zulfakarelzaf.authenticjogja.presenter;


import com.zulfakarelzaf.authenticjogja.retrofit.Intractor;
import java.util.ArrayList;



public class SubMenuPresenter implements Contract.Presenter{

    private Contract.Intractor intractor;
    private Contract.View myFragment;



    public SubMenuPresenter(Contract.View myFragment) {
        this.myFragment = myFragment;
        intractor = new Intractor(this);

    }


    @Override
    public void getEvent() {
        intractor.getEvent();
    }

    @Override
    public void getCraft() {
        intractor.getCraft();
    }

    @Override
    public void getCulinnary(int id) {
        intractor.getCulinnary(id);
    }

    @Override
    public void getLanguange(int id) {
        intractor.getLanguange(id);
    }

    @Override
    public void getCategory() {
        intractor.getCategory();
    }

    @Override
    public void getCategoryLanguage() {
        intractor.getCategoryLanguage();
    }

    @Override
    public void getTrending() {
        intractor.getTrending();
    }

    @Override
    public void onFinishCall(ArrayList list) {
        myFragment.onCompleteCall(list);
    }

    @Override
    public void onFailurCall(Throwable t) {

    }
}
