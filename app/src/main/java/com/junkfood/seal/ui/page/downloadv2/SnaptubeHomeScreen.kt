package com.junkfood.seal.ui.page.downloadv2

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.junkfood.seal.R
import com.junkfood.seal.ui.component.SnaptubeYellow
import com.junkfood.seal.util.findURLsFromString
import com.junkfood.seal.util.makeToast

@Composable
fun SnaptubeHomeScreen(
    modifier: Modifier = Modifier,
    activeTaskCount: Int = 0,
    onUrlSubmit: (String) -> Unit,
    onNavigateToTasks: () -> Unit = {},
) {
    var selectedCategoryTab by remember { mutableIntStateOf(0) }
    val categoryTabs = listOf("Search", "YouTube", "Music", "More")
    var searchText by remember { mutableStateOf("") }
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(Color(0xFF0E0E10))
                .statusBarsPadding()
                .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // 1. Top Category Tabs matching Screenshot 1
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            categoryTabs.forEachIndexed { index, title ->
                val isSelected = selectedCategoryTab == index
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier =
                        Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) {
                                selectedCategoryTab = index
                                if (index == 1 && searchText.isBlank()) {
                                    searchText = "https://www.youtube.com"
                                }
                            }
                            .padding(horizontal = 6.dp, vertical = 4.dp),
                ) {
                    Text(
                        text = title,
                        color = if (isSelected) Color.White else Color(0xFF8E8E93),
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 17.sp,
                    )
                    Spacer(Modifier.height(6.dp))
                    Box(
                        modifier =
                            Modifier
                                .height(2.5.dp)
                                .width(if (isSelected) 36.dp else 0.dp)
                                .background(
                                    if (isSelected) SnaptubeYellow else Color.Transparent,
                                    RoundedCornerShape(2.dp),
                                )
                    )
                }
            }
        }

        Spacer(Modifier.height(28.dp))

        // 2. Central SnapSeal Hero Section with decorative floating social cards
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center,
        ) {
            // Background Decorative Floating App Badges
            // Facebook blue box
            Box(
                modifier =
                    Modifier
                        .offset(x = (-130).dp, y = (-40).dp)
                        .size(54.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF1877F2).copy(alpha = 0.85f)),
                contentAlignment = Alignment.Center,
            ) {
                Text("f", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 28.sp)
            }

            // Green Music / WhatsApp box
            Box(
                modifier =
                    Modifier
                        .offset(x = 18.dp, y = (-55).dp)
                        .size(50.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color(0xFF25D366).copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Filled.MusicNote,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp),
                )
            }

            // Blue Twitter box
            Box(
                modifier =
                    Modifier
                        .offset(x = 130.dp, y = (-35).dp)
                        .size(52.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color(0xFF1DA1F2).copy(alpha = 0.85f)),
                contentAlignment = Alignment.Center,
            ) {
                Text("f", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 26.sp)
            }

            // Red YouTube box
            Box(
                modifier =
                    Modifier
                        .offset(x = (-110).dp, y = 50.dp)
                        .size(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFFF0000).copy(alpha = 0.85f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Filled.PlayArrow,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp),
                )
            }

            // Instagram gradient box
            Box(
                modifier =
                    Modifier
                        .offset(x = 0.dp, y = 56.dp)
                        .size(54.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    Color(0xFF833AB4),
                                    Color(0xFFFD1D1D),
                                    Color(0xFFFCB045),
                                )
                            )
                        ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Outlined.PhotoCamera,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(26.dp),
                )
            }

            // Magenta/Red TikTok box
            Box(
                modifier =
                    Modifier
                        .offset(x = 120.dp, y = 50.dp)
                        .size(54.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFCC1844).copy(alpha = 0.85f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Outlined.Share,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp),
                )
            }

            // Main SnapSeal Logo Title (Golden Serif Typography matching Screenshot 1)
            Text(
                text = "SnapSeal",
                style =
                    TextStyle(
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 44.sp,
                        color = SnaptubeYellow,
                        shadow =
                            Shadow(
                                color = Color.Black.copy(alpha = 0.8f),
                                blurRadius = 12f,
                            ),
                    ),
            )
        }

        Spacer(Modifier.height(18.dp))

        // 3. Search & URL Input Bar (Pill shape with circular yellow button)
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(56.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(Color(0xFF1E1E22))
                    .border(1.dp, Color(0xFF2C2C32), RoundedCornerShape(28.dp))
                    .padding(start = 16.dp, end = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Outlined.FileDownload,
                contentDescription = null,
                tint = Color(0xFF8E8E93),
                modifier = Modifier.size(22.dp),
            )
            Spacer(Modifier.width(12.dp))
            BasicTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier.weight(1f),
                textStyle = TextStyle(color = Color.White, fontSize = 15.sp),
                singleLine = true,
                cursorBrush = SolidColor(SnaptubeYellow),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions =
                    KeyboardActions(
                        onSearch = {
                            keyboardController?.hide()
                            if (searchText.isNotBlank()) {
                                val urls = findURLsFromString(searchText)
                                if (urls.isNotEmpty()) {
                                    onUrlSubmit(urls.first())
                                } else {
                                    onUrlSubmit(searchText.trim())
                                }
                            }
                        }
                    ),
                decorationBox = { innerTextField ->
                    if (searchText.isEmpty()) {
                        Text(
                            text = "Search to download",
                            color = Color(0xFF6E6E73),
                            fontSize = 15.sp,
                        )
                    }
                    innerTextField()
                },
            )

            // Circular Yellow Search Button
            Box(
                modifier =
                    Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(SnaptubeYellow)
                        .clickable {
                            keyboardController?.hide()
                            if (searchText.isNotBlank()) {
                                val urls = findURLsFromString(searchText)
                                if (urls.isNotEmpty()) {
                                    onUrlSubmit(urls.first())
                                } else {
                                    onUrlSubmit(searchText.trim())
                                }
                            } else {
                                context.makeToast("Please enter a link or search query")
                            }
                        },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search",
                    tint = Color.Black,
                    modifier = Modifier.size(22.dp),
                )
            }
        }

        Spacer(Modifier.height(18.dp))

        // 4. "📋 Paste Link from Clipboard" Pill Button
        Row(
            modifier =
                Modifier
                    .clip(RoundedCornerShape(22.dp))
                    .background(Color(0xFF1A1A1E))
                    .border(1.dp, Color(0xFF2E2E34), RoundedCornerShape(22.dp))
                    .clickable {
                        val clip = clipboardManager.getText()?.text
                        if (!clip.isNullOrBlank()) {
                            val urls = findURLsFromString(clip)
                            if (urls.isNotEmpty()) {
                                searchText = urls.first()
                                onUrlSubmit(urls.first())
                            } else {
                                searchText = clip.trim()
                                onUrlSubmit(clip.trim())
                            }
                        } else {
                            context.makeToast(R.string.paste_fail_msg)
                        }
                    }
                    .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("📋", fontSize = 14.sp)
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Paste Link from Clipboard",
                color = Color(0xFFE5C158),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
            )
        }

        Spacer(Modifier.height(16.dp))

        // 5. Developer Credit Badge: "⚡ Built with ❤️ by dev-abuhurairah"
        Row(
            modifier =
                Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF16161A))
                    .border(1.dp, Color(0xFF28282E), RoundedCornerShape(20.dp))
                    .padding(horizontal = 18.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("⚡", fontSize = 12.sp)
            Spacer(Modifier.width(6.dp))
            Text(
                text = "Built with ",
                color = Color(0xFF9E9EA4),
                fontSize = 12.sp,
            )
            Text("❤️", fontSize = 12.sp)
            Text(
                text = " by dev-abuhurairah",
                color = SnaptubeYellow,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }

        // Active downloads shortcut if any tasks active
        if (activeTaskCount > 0) {
            Spacer(Modifier.height(28.dp))
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF1E1C12))
                        .border(1.dp, SnaptubeYellow.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                        .clickable { onNavigateToTasks() }
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Outlined.FileDownload,
                        contentDescription = null,
                        tint = SnaptubeYellow,
                        modifier = Modifier.size(20.dp),
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "$activeTaskCount download(s) in progress",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
                Text(
                    text = "View in Play >",
                    color = SnaptubeYellow,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
