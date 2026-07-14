package com.es.jma.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.es.jma.designsystem.theme.BlushWhite
import com.es.jma.designsystem.theme.CharcoalBlack
import com.es.jma.designsystem.theme.InkBlack
import com.es.jma.designsystem.theme.PureWhite
import com.es.jma.designsystem.theme.SmokeWhite
import com.es.jma.model.Breed
import com.es.jma.model.CatInfo

@Composable
fun CatInformation(
    cat: CatInfo,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardColors(
            contentColor = InkBlack,
            containerColor = PureWhite,
            disabledContainerColor = BlushWhite,
            disabledContentColor = SmokeWhite
        )
    ) {
        Row(Modifier.padding(12.dp)) {
            AsyncImage(
                model = cat.url,
                contentDescription = cat.breed?.get(0)?.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(12.dp))
            Column {
                Text(
                    text = cat.breed?.get(0)?.name ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    color = InkBlack
                )
                Text(
                    text = cat.breed?.get(0)?.description ?: "",
                    maxLines = 2,
                    style = MaterialTheme.typography.bodySmall,
                    color = CharcoalBlack
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CatInformationPreview() {
    CatInformation(
        cat = CatInfo(
            id = "",
            url = "https://cdn2.thecatapi.com/images/p6x60nX6U.jpg",
            breed = listOf(Breed(
                id = "aege",
                name = "Aegean",
                temperament = "Affectionate, Social, Intelligent, Playful, Active",
                origin = "Greece",
                description = "Native to the Greek islands known as the Cyclades in the Aegean Sea, these are natural cats, meaning they developed without humans getting involved in their breeding. As a breed, Aegean Cats are rare, although they are numerous on their home islands. They are generally friendly toward people and can be excellent cats for families with children.",
                referenceImage = null
            ))
        )
    )
}