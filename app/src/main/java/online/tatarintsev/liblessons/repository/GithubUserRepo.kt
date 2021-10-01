package online.tatarintsev.liblessons.repository

import io.reactivex.Observable
import online.tatarintsev.liblessons.entity.GithubUser
import java.lang.RuntimeException

class GithubUsersRepo {
    private val repositories: List<GithubUser> = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers() : Observable<List<GithubUser>> {
        return Observable.create{ emitter ->
            try {
                emitter.onNext(repositories)
                emitter.onComplete()
            } catch(ex: Exception) {
                emitter.onError(RuntimeException("Error"))
            }
        }
    }
}