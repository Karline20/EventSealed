package coding.legaspi.eventssealedclasses

/**
 * Created by Karlen Legaspi
 */
data class MainScreenState(
    var isCountButtonVisible : Boolean = false,
    var displayingResult : String = "",
    var inputValue : String = ""
)
