package danilchanka.aliaksandr.dota2proteam.view;

import java.util.ArrayList;

import danilchanka.aliaksandr.dota2proteam.entity.ProTeam;
import eu.inloop.viewmodel.IView;

public interface ProTeamListMvpView extends IView {

    void showProTeamList(ArrayList<ProTeam> proTeamList);

    void showLoading();

    void showEmpty();

    void showError();

    void showRefreshingError();
}
