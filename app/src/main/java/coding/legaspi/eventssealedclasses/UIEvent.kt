package coding.legaspi.eventssealedclasses

/**
 * Created by Karlen Legaspi
 */
sealed class UIEvent {
    data class ShowMessage(val message : String) : UIEvent()
}