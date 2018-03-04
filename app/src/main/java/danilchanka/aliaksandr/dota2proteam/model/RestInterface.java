package danilchanka.aliaksandr.dota2proteam.model;

import java.util.ArrayList;

import danilchanka.aliaksandr.dota2proteam.entity.ProTeam;
import retrofit2.http.GET;
import retrofit2.http.Path;
import io.reactivex.Observable;

public interface RestInterface {
    @GET("teams")
    Observable<ArrayList<ProTeam>> getProTeamList();

    @GET("teams/{team_id}")
    Observable<ProTeam> getProTeamDetail(@Path("team_id") int teamId);
}
