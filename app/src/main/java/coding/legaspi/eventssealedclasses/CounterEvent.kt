package coding.legaspi.eventssealedclasses

/**
 * Created by Karlen Legaspi
 */
sealed class CounterEvent{
    data class ValueEntered(val value : String) : CounterEvent()
    object CountButtonClicked : CounterEvent()
    object ResetButtonClicked : CounterEvent()

}