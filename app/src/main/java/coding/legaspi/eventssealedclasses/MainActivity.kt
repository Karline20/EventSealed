package coding.legaspi.eventssealedclasses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coding.legaspi.eventssealedclasses.ui.theme.EventsSealedClassesTheme
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventsSealedClassesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    @Composable
    fun MainScreen(
        modifier: Modifier = Modifier,
        counterViewModel: CounterViewModel = viewModel()
    ){
        val screenState = counterViewModel.screenState.value
        val scaffoldState  = remember { SnackbarHostState() }
        
        LaunchedEffect(key1 = true,){
            counterViewModel.uiEventFlow.collectLatest {event ->
                when(event){
                    is UIEvent.ShowMessage -> {
                        scaffoldState.showSnackbar(
                            message = event.message
                        )
                    }
                }
            }
        }
            Scaffold(
                snackbarHost = {
                    SnackbarHost(hostState = scaffoldState) {

                } },
                topBar = {

                },
                contentWindowInsets = ScaffoldDefaults.contentWindowInsets ,
                content = {innerPadding ->
                    Column(
                        modifier =
                        modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = screenState.displayingResult,
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            color = Color.DarkGray
                        )
                        OutlinedTextField(
                            value = screenState.inputValue,
                            onValueChange = {
                                counterViewModel.onEvent(CounterEvent.ValueEntered(it))},
                            modifier = modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            textStyle = TextStyle(
                                color = Color.LightGray,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            label = {
                                Text(text = "New Count")
                            }
                        )

                        if (screenState.isCountButtonVisible){
                            Button(
                                onClick = {
                                    counterViewModel.onEvent(CounterEvent.CountButtonClicked)
                                },
                                modifier = modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Count",
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Button(
                            onClick = {
                                counterViewModel.onEvent(CounterEvent.ResetButtonClicked)
                            },
                            modifier = modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Reset",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                }
            )
    }

}

//        val scaffoldState = rememberScaffoldState(){
//
//        }


