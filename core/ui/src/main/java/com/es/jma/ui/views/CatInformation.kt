package com.es.jma.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.es.jma.designsystem.components.CatImage
import com.es.jma.designsystem.components.CountryFlag
import com.es.jma.designsystem.components.MeowChips
import com.es.jma.designsystem.components.MeowIconButtonFilled
import com.es.jma.designsystem.icon.MeowIcons
import com.es.jma.designsystem.theme.MeowAppTheme
import com.es.jma.designsystem.theme.imageModalCat
import com.es.jma.designsystem.theme.marginBig
import com.es.jma.designsystem.theme.marginSmaller
import com.es.jma.designsystem.theme.marginZero
import com.es.jma.designsystem.theme.textNormal
import com.es.jma.model.CatInfo

@Composable
fun CatInformation(
    cat: CatInfo,
    modifier: Modifier = Modifier,
    onCatClicked: (CatInfo) -> Unit = {},
    onFavoriteClicked: ((CatInfo, Boolean) -> Unit)? = null,
    onDeletedClicked: ((CatInfo) -> Unit)? = null,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = { onCatClicked(cat) }),
    ) {
        Box(Modifier.fillMaxWidth()) {
            Column {
                CatImage(
                    catImage = cat.url,
                    modifier = modifier
                        .fillMaxWidth()
                        .size(imageModalCat)
                        .clip(
                            RoundedCornerShape(
                                topEnd = marginSmaller,
                                topStart = marginSmaller,
                                bottomEnd = marginZero,
                                bottomStart = marginZero
                            )
                        )
                )
                Spacer(Modifier.width(12.dp))
                Column(Modifier.padding(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CountryFlag(
                            code = cat.origin,
                            size = textNormal
                        )
                        Spacer(Modifier.padding(4.dp))
                        Text(
                            text = cat.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(Modifier.padding(4.dp))
                    Text(
                        text = cat.description,
                        maxLines = 2,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.padding(4.dp))
                    MeowChips(text = cat.temperament)
                }
            }

            Row(
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        top = imageModalCat - marginBig / 2,
                        end = 16.dp
                    )
            ) {
                onFavoriteClicked?.let {
                    MeowIconButtonFilled(
                        icon = if (cat.isFavorite) MeowIcons.Favorite else MeowIcons.FavoriteOutLine,
                        onClick = { onFavoriteClicked(cat, !cat.isFavorite) }
                    )
                }

                onDeletedClicked?.let {
                    MeowIconButtonFilled(
                        icon = MeowIcons.Delete,
                        onClick = { onDeletedClicked(cat) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CatInformationPreview() {
    MeowAppTheme(darkTheme = true) {
        CatInformation(
            cat = CatInfo(
                id = "",
                url = "https://cdn2.thecatapi.com/images/p6x60nX6U.jpg",
                isFavorite = false,
                name = "Aegean",
                temperament = "Affectionate, Social, Intelligent, Playful, Active",
                origin = "GR",
                description = "Native to the Greek islands known as the Cyclades in the Aegean Sea, these are natural cats, meaning they developed without humans getting involved in their breeding. As a breed, Aegean Cats are rare, although they are numerous on their home islands. They are generally friendly toward people and can be excellent cats for families with children.",
                idBreed = ""
            ),
            onDeletedClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CatInformationIconPreview() {
    MeowAppTheme {
        CatInformation(
            cat = CatInfo(
                id = "",
                url = "https://cdn2.thecatapi.com/images/p6x60nX6U.jpg",
                isFavorite = true,
                name = "Aegean",
                temperament = "Affectionate, Social, Intelligent, Playful, Active",
                origin = "GR",
                description = "Native to the Greek islands known as the Cyclades in the Aegean Sea, these are natural cats, meaning they developed without humans getting involved in their breeding. As a breed, Aegean Cats are rare, although they are numerous on their home islands. They are generally friendly toward people and can be excellent cats for families with children.",
                idBreed = ""
            ),
            onDeletedClicked = {}
        )
    }
}