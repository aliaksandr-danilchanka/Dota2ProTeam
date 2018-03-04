package danilchanka.aliaksandr.dota2proteam.view;

import danilchanka.aliaksandr.dota2proteam.entity.ProTeam;
import eu.inloop.viewmodel.IView;

public interface ProTeamDetailMvpView extends IView {

    void showProTeamDetail(ProTeam proTeam);

    void showLoading();

    void showEmpty();

    void showError();
}
