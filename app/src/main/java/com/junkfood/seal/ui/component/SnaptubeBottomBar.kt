package com.junkfood.seal.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.PlayCircleOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.junkfood.seal.R
import com.junkfood.seal.ui.common.Route

val SnaptubeYellow = Color(0xFFFFCC00)
val SnaptubeDarkBg = Color(0xFF121214)
val SnaptubeSurface = Color(0xFF1C1C20)
val SnaptubeTextPrimary = Color(0xFFF5F5F7)
val SnaptubeTextSecondary = Color(0xFF8E8E93)

@Composable
fun SnaptubeBottomBar(
    modifier: Modifier = Modifier,
    currentRoute: String?,
    onNavigate: (String) -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color(0xFF121214),
        shadowElevation = 8.dp,
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = 24.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val isHome = currentRoute == Route.HOME || currentRoute == null
            val isDownloads = currentRoute == Route.DOWNLOADS || currentRoute == Route.TASK_LIST
            val isSettings =
                currentRoute == Route.SETTINGS_PAGE ||
                    currentRoute == Route.SETTINGS ||
                    currentRoute == Route.ABOUT

            SnaptubeBottomBarItem(
                label = stringResource(R.string.download),
                selected = isHome,
                selectedIcon = Icons.Filled.FileDownload,
                unselectedIcon = Icons.Outlined.FileDownload,
                onClick = { onNavigate(Route.HOME) },
            )

            SnaptubeBottomBarItem(
                label = "Play",
                selected = isDownloads,
                selectedIcon = Icons.Filled.PlayCircle,
                unselectedIcon = Icons.Outlined.PlayCircleOutline,
                onClick = { onNavigate(Route.DOWNLOADS) },
            )

            SnaptubeBottomBarItem(
                label = stringResource(R.string.settings),
                selected = isSettings,
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings,
                onClick = { onNavigate(Route.SETTINGS_PAGE) },
            )
        }
    }
}

@Composable
private fun SnaptubeBottomBarItem(
    label: String,
    selected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier =
            Modifier
                .clip(RoundedCornerShape(24.dp))
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick,
                )
                .animateContentSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (selected) {
            Row(
                modifier =
                    Modifier
                        .background(SnaptubeYellow, RoundedCornerShape(24.dp))
                        .padding(horizontal = 18.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    imageVector = selectedIcon,
                    contentDescription = label,
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = label,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                )
            }
        } else {
            Box(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = unselectedIcon,
                    contentDescription = label,
                    tint = SnaptubeTextSecondary,
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}
