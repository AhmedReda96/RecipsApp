package alphagroup.veramovieapp.com.UI.TvPart.API


import androidx.room.Query
import com.example.yackeensolutionstask.db.RecipeEntity
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
interface Service {

    @GET("android-test/recipes.json")
    suspend fun getRecipesData(): Response<List<RecipeEntity?>>?

    @Query("SELECT * FROM recipe WHERE name LIKE '%' || :search || '%'")
    fun searchRecipe(search: String?): Flowable<List<RecipeEntity>>


}