package danilchanka.aliaksandr.dota2proteam.model;

import java.util.ArrayList;

import danilchanka.aliaksandr.dota2proteam.entity.ProTeam;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.Single;

public interface RestInterface {
    @GET("teams")
    Observable<ArrayList<ProTeam>> getProTeamList();

    @GET("teams/{team_id}")
    Single<ProTeam> getProTeamDetail(@Path("team_id") String teamId);
}
