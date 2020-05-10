package io.github.sassy.githubrepolist.repository


class RepoRepository {

    private val repos: List<String> =listOf("test1", "test2")

    fun getRepos() : List<String> {
        return repos
    }
}