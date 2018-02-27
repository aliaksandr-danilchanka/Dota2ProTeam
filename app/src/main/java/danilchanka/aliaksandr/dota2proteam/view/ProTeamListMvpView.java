package danilchanka.aliaksandr.dota2proteam.view;

import java.util.ArrayList;

import danilchanka.aliaksandr.dota2proteam.entity.ProTeam;
import danilchanka.aliaksandr.dota2proteam.view.base.MvpView;

public interface ProTeamListMvpView extends MvpView {

    void showProTeamList(ArrayList<ProTeam> proTeamList);

    void showLoading();

    void showEmpty();

    void showError();

    void showRefreshingError();
}
