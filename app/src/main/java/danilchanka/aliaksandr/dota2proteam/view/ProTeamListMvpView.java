package danilchanka.aliaksandr.dota2proteam.view;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import danilchanka.aliaksandr.dota2proteam.entity.ProTeam;

public interface ProTeamListMvpView extends MvpView {

    void showProTeamList(ArrayList<ProTeam> proTeamList);

    void showLoading();

    void showEmpty();

    void showError();

    void showRefreshingError();
}
