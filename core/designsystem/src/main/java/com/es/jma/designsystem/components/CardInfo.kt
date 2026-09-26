package com.es.jma.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.es.jma.designsystem.modifyIf
import com.es.jma.designsystem.theme.marginBiggest
import com.es.jma.designsystem.theme.marginDefault
import com.es.jma.designsystem.theme.marginMedium
import com.es.jma.designsystem.theme.marginSmall
import com.es.jma.designsystem.theme.marginSmaller

@Composable
fun CardInfo(
    text: String,
    background: Color,
    height: Dp? = marginBiggest,
) {
    Card(
        shape = RoundedCornerShape(marginMedium),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = marginMedium)
            .padding(horizontal = marginDefault)
            .modifyIf(height != null) {
                height(height!!)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = marginSmall),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = background)
                .padding(marginSmaller),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text)
        }
    }
}