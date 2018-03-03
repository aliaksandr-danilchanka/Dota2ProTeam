package danilchanka.aliaksandr.dota2proteam.view;

import android.view.View;

import java.util.ArrayList;

import danilchanka.aliaksandr.dota2proteam.entity.ProTeam;
import eu.inloop.viewmodel.IView;

public interface ProTeamListMvpView extends IView {

    void showProTeamList(ArrayList<ProTeam> proTeamList);

    void showLoading();

    void showEmpty();

    void showError();

    void startProTeamDetail(ProTeam proTeam, View sharedView);

    void showRefreshingError();
}
