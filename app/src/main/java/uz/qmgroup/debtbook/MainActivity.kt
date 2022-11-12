package uz.qmgroup.debtbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uz.qmgroup.debtbook.ui.providers.LocalSnackbarHostState
import uz.qmgroup.debtbook.ui.screens.contacts.ContactsScreen
import uz.qmgroup.debtbook.ui.theme.DebtBookTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DebtBookTheme {
                val snackbarHostState = remember { SnackbarHostState() }

                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = stringResource(id = R.string.app_name))
                            }
                        )
                    },
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                ) {
                    CompositionLocalProvider(LocalSnackbarHostState provides snackbarHostState) {
                        ContactsScreen(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                        )
                    }
                }
            }
        }
    }
}