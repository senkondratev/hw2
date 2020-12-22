package ru.senkondr.hw2.repository

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}


class NetworkState(val status: Status, val msg: String) {
    //companion object - буквально "вспомогательный" класс
    companion object {
        //val - immutable
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState
        val ENDOFLIST: NetworkState

        //Блок инициализационный блок
        init {
            LOADED = NetworkState(Status.SUCCESS, "Success")

            LOADING = NetworkState(Status.RUNNING, "Running")

            ERROR = NetworkState(Status.FAILED, "Oops")

            ENDOFLIST = NetworkState(Status.FAILED, "Out of data. You should go for a walk...")
        }
    }
}