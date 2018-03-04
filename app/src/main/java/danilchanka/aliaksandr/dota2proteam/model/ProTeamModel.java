package danilchanka.aliaksandr.dota2proteam.model;

import java.util.ArrayList;

import javax.inject.Inject;

import danilchanka.aliaksandr.dota2proteam.di.PerApplication;
import danilchanka.aliaksandr.dota2proteam.entity.ProTeam;
import danilchanka.aliaksandr.dota2proteam.util.PaperHelper;
import io.paperdb.Paper;
import io.reactivex.Observable;

@PerApplication
public class ProTeamModel {

    protected RestInterface mRestInterface;

    @Inject
    public ProTeamModel(RestInterface restInterface) {
        this.mRestInterface = restInterface;
    }


    public Observable<ArrayList<ProTeam>> loadProTeamList() {
        return mRestInterface.getProTeamList()
                .doOnNext(proTeams -> Paper.book(PaperHelper.BOOK_DEFAULT).write(PaperHelper.PRO_TEAM_LIST, proTeams));
    }

}