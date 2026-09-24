package com.junkfood.seal.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingTitle(text: String) {
    Text(
        modifier = Modifier.padding(top = 32.dp).padding(horizontal = 20.dp, vertical = 16.dp),
        text = text,
        style = MaterialTheme.typography.displaySmall,
    )
}

@Composable
fun SettingCategoryTitle(text: String) {
    Text(
        text = text,
        color = androidx.compose.ui.graphics.Color(0xFF8E8E93),
        fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
        fontSize = 13.sp,
        modifier = Modifier.padding(start = 20.dp, top = 24.dp, bottom = 6.dp),
    )
}

@Composable
fun SettingItem(title: String, description: String = "", icon: ImageVector?, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.clickable { onClick() },
        color = androidx.compose.ui.graphics.Color.Transparent,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            icon?.let {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 16.dp).size(22.dp),
                    tint = androidx.compose.ui.graphics.Color(0xFFC0C0C8),
                )
            }
            Column(
                modifier = Modifier.weight(1f).padding(start = if (icon == null) 8.dp else 0.dp)
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis,
                )
                if (description.isNotBlank()) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = description,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos,
                contentDescription = null,
                tint = androidx.compose.ui.graphics.Color(0xFF6E6E76),
                modifier = Modifier.size(16.dp),
            )
        }
    }
}
