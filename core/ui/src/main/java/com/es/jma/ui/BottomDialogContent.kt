package com.es.jma.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Dp
import com.es.jma.designsystem.modifyIf

@Composable
fun BottomDialogContent(
    title: String,
    onBackClicked: (() -> Unit)? = null,
    onCloseClicked: (() -> Unit)? = null,
    showSeparator: Boolean = true,
    isLoading: Boolean = false,
    height: Dp? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val localFocusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }.modifyIf(height != null) { Modifier.height(height!!) }
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            TitleColumn(
                title = title,
                showSeparator = showSeparator,
                onCloseClick = onCloseClicked,
                onBackButton = onBackClicked,
                isLoading = isLoading
            )
        }
        content()
    }
}
