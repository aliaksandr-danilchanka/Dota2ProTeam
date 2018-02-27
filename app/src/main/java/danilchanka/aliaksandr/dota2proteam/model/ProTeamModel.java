package danilchanka.aliaksandr.dota2proteam.model;

import java.util.ArrayList;

import javax.inject.Inject;

import danilchanka.aliaksandr.dota2proteam.di.PerApplication;
import danilchanka.aliaksandr.dota2proteam.entity.ProTeam;
import io.reactivex.Observable;

@PerApplication
public class ProTeamModel {

    protected RestInterface mRestInterface;

    @Inject
    public ProTeamModel(RestInterface restInterface) {
        this.mRestInterface = restInterface;
    }

    /**
     * Concating reading from cache and from API. At first will be emited
     * cache data (if are available) and after will be emited loaded from API data.
     *
     * @return Observable emiting list of contacts loaded from cache or API
     */
    public Observable<ArrayList<ProTeam>> loadProTeamList() {
        return  mRestInterface.getProTeamList();
//
//                Observable.concat(
//                loadProTeamListFromCache()
//                        .onErrorResumeNext(throwable -> Observable.empty()),
//                loadProTeamListFromApi()
//                        .onErrorResumeNext(throwable -> Observable.empty()))
//                .defaultIfEmpty(null);
    }

//    public Observable<ArrayList<ProTeam>> loadProTeamListFromCache() {
//        return RxPaper.<ArrayList<ProTeam>>read(RxPaperHelper.BOOK_DEFAULT, RxPaperHelper.PRO_TEAM_LIST);
//    }
//
//    public Observable<ArrayList<ProTeam>> loadProTeamListFromApi() {
//        return mRestInterface.getProTeamList()
//                .doOnNext(proTeams -> Paper.book(RxPaperHelper.BOOK_DEFAULT).write(RxPaperHelper.PRO_TEAM_LIST, proTeams));
//    }

}