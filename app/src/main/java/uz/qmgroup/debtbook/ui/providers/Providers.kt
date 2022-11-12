package uz.qmgroup.debtbook.ui.providers

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf

val LocalSnackbarHostState = compositionLocalOf<SnackbarHostState> {
    throw IllegalStateException("Snackbar host is not provided")
}