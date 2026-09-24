package com.junkfood.seal.ui.page.downloadv2.configure

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.junkfood.seal.ui.component.SnaptubeYellow
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.DownloadDone
import androidx.compose.material.icons.filled.SettingsSuggest
import androidx.compose.material.icons.filled.VideoFile
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.DoneAll
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.NewLabel
import androidx.compose.material.icons.outlined.SettingsSuggest
import androidx.compose.material.icons.outlined.VideoFile
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.junkfood.seal.App
import com.junkfood.seal.R
import com.junkfood.seal.ui.common.HapticFeedback.longPressHapticFeedback
import com.junkfood.seal.ui.common.motion.materialSharedAxisX
import com.junkfood.seal.ui.component.ButtonChip
import com.junkfood.seal.ui.component.DrawerSheetSubtitle
import com.junkfood.seal.ui.component.OutlinedButtonWithIcon
import com.junkfood.seal.ui.component.SealModalBottomSheet
import com.junkfood.seal.ui.component.SealModalBottomSheetM2Variant
import com.junkfood.seal.ui.component.SingleChoiceChip
import com.junkfood.seal.ui.component.SingleChoiceSegmentedButton
import com.junkfood.seal.ui.component.VideoFilterChip
import com.junkfood.seal.ui.page.command.TemplatePickerDialog
import com.junkfood.seal.ui.page.downloadv2.configure.ActionButton.Download
import com.junkfood.seal.ui.page.downloadv2.configure.ActionButton.FetchInfo
import com.junkfood.seal.ui.page.downloadv2.configure.ActionButton.StartTask
import com.junkfood.seal.ui.page.downloadv2.configure.DownloadDialogViewModel.Action
import com.junkfood.seal.ui.page.downloadv2.configure.DownloadDialogViewModel.SelectionState
import com.junkfood.seal.ui.page.downloadv2.configure.DownloadDialogViewModel.SheetState.Configure
import com.junkfood.seal.ui.page.downloadv2.configure.DownloadDialogViewModel.SheetState.Error
import com.junkfood.seal.ui.page.downloadv2.configure.DownloadDialogViewModel.SheetState.InputUrl
import com.junkfood.seal.ui.page.downloadv2.configure.DownloadDialogViewModel.SheetState.Loading
import com.junkfood.seal.ui.page.settings.command.CommandTemplateDialog
import com.junkfood.seal.ui.page.settings.format.AudioQuickSettingsDialog
import com.junkfood.seal.ui.page.settings.format.VideoQuickSettingsDialog
import com.junkfood.seal.ui.page.settings.network.CookiesQuickSettingsDialog
import com.junkfood.seal.ui.theme.SealTheme
import com.junkfood.seal.util.AUDIO_CONVERSION_FORMAT
import com.junkfood.seal.util.AUDIO_CONVERT
import com.junkfood.seal.util.AUDIO_FORMAT
import com.junkfood.seal.util.AUDIO_QUALITY
import com.junkfood.seal.util.COOKIES
import com.junkfood.seal.util.CUSTOM_COMMAND
import com.junkfood.seal.util.DatabaseUtil
import com.junkfood.seal.util.DownloadType
import com.junkfood.seal.util.DownloadType.Audio
import com.junkfood.seal.util.DownloadType.Command
import com.junkfood.seal.util.DownloadType.Playlist
import com.junkfood.seal.util.DownloadType.Video
import com.junkfood.seal.util.DownloadType.entries
import com.junkfood.seal.util.DownloadUtil
import com.junkfood.seal.util.FORMAT_SELECTION
import com.junkfood.seal.util.PreferenceStrings
import com.junkfood.seal.util.PreferenceUtil
import com.junkfood.seal.util.PreferenceUtil.getBoolean
import com.junkfood.seal.util.PreferenceUtil.updateBoolean
import com.junkfood.seal.util.PreferenceUtil.updateInt
import com.junkfood.seal.util.SUBTITLE
import com.junkfood.seal.util.TEMPLATE_ID
import com.junkfood.seal.util.THUMBNAIL
import com.junkfood.seal.util.ToastUtil
import com.junkfood.seal.util.USE_CUSTOM_AUDIO_PRESET
import com.junkfood.seal.util.VIDEO_FORMAT
import com.junkfood.seal.util.VIDEO_QUALITY
import kotlinx.coroutines.launch

@Composable
private fun DownloadType.label(): String =
    stringResource(
        when (this) {
            Audio -> R.string.audio
            Video -> R.string.video
            Command -> R.string.commands
            Playlist -> R.string.playlist
        }
    )

val PreferencesMock = DownloadUtil.DownloadPreferences.EMPTY

data class Config(
    val downloadType: DownloadType? = PreferenceUtil.getDownloadType(),
    val typeEntries: List<DownloadType> =
        when (CUSTOM_COMMAND.getBoolean()) {
            true -> DownloadType.entries
            false -> DownloadType.entries - Command
        },
    val useFormatSelection: Boolean = FORMAT_SELECTION.getBoolean(),
    val savedLinks: Set<String> = PreferenceUtil.getSavedLinks(),
) {
    companion object {
        fun updatePreferences(newValue: Config, oldValue: Config) {
            with(newValue) {
                if (downloadType != oldValue.downloadType) {
                    downloadType?.let { PreferenceUtil.updateDownloadType(it) }
                }
                if (useFormatSelection != oldValue.useFormatSelection) {
                    FORMAT_SELECTION.updateBoolean(useFormatSelection)
                }
                if (savedLinks != oldValue.savedLinks) {
                    PreferenceUtil.updateSavedLinks(savedLinks)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadDialog(
    modifier: Modifier = Modifier,
    config: Config,
    sheetState: SheetState,
    preferences: DownloadUtil.DownloadPreferences,
    onPreferencesUpdate: (DownloadUtil.DownloadPreferences) -> Unit,
    state: DownloadDialogViewModel.SheetState = InputUrl,
    onActionPost: (Action) -> Unit = {},
) {
    var showVideoPresetDialog by remember { mutableStateOf(false) }
    var showAudioPresetDialog by remember { mutableStateOf(false) }

    SealModalBottomSheet(
        sheetState = sheetState,
        contentPadding = PaddingValues(),
        onDismissRequest = { onActionPost(Action.HideSheet) },
    ) {
        DownloadDialogContent(
            modifier = modifier,
            state = state,
            config = config,
            preferences = preferences,
            onPreferencesUpdate = onPreferencesUpdate,
            onPresetEdit = { type ->
                when (type) {
                    Audio -> showAudioPresetDialog = true

                    Video -> showVideoPresetDialog = true

                    else -> {}
                }
            },
            onActionPost = onActionPost,
        )
    }

    if (showVideoPresetDialog) {
        var res by remember(preferences) { mutableIntStateOf(preferences.videoResolution) }
        var format by remember(preferences) { mutableIntStateOf(preferences.videoFormat) }

        VideoQuickSettingsDialog(
            videoResolution = res,
            videoFormatPreference = format,
            onResolutionSelect = { res = it },
            onFormatSelect = { format = it },
            onDismissRequest = { showVideoPresetDialog = false },
            onSave = {
                VIDEO_FORMAT.updateInt(format)
                VIDEO_QUALITY.updateInt(res)
                onPreferencesUpdate(DownloadUtil.DownloadPreferences.createFromPreferences())
            },
        )
    }

    if (showAudioPresetDialog) {
        var quality by remember(preferences) { mutableIntStateOf(preferences.audioQuality) }
        var customPreset by
            remember(preferences) { mutableStateOf(preferences.useCustomAudioPreset) }
        var conversionFmt by
            remember(preferences) { mutableIntStateOf(preferences.audioConvertFormat) }
        var convertAudio by remember(preferences) { mutableStateOf(preferences.convertAudio) }
        var preferredFormat by remember(preferences) { mutableIntStateOf(preferences.audioFormat) }

        AudioQuickSettingsDialog(
            modifier = Modifier,
            preferences = preferences,
            audioQuality = quality,
            onQualitySelect = { quality = it },
            useCustomAudioPreset = customPreset,
            onCustomPresetToggle = { customPreset = it },
            convertAudio = convertAudio,
            onConvertToggled = { convertAudio = it },
            conversionFormat = conversionFmt,
            onConversionSelect = { conversionFmt = it },
            preferredFormat = preferredFormat,
            onPreferredSelect = { preferredFormat = it },
            onDismissRequest = { showAudioPresetDialog = false },
            onSave = {
                AUDIO_QUALITY.updateInt(quality)
                USE_CUSTOM_AUDIO_PRESET.updateBoolean(customPreset)
                AUDIO_CONVERSION_FORMAT.updateInt(conversionFmt)
                AUDIO_CONVERT.updateBoolean(convertAudio)
                AUDIO_FORMAT.updateInt(preferredFormat)
                onPreferencesUpdate(DownloadUtil.DownloadPreferences.createFromPreferences())
            },
        )
    }
}

@Composable
private fun ErrorPage(modifier: Modifier = Modifier, state: Error, onActionPost: (Action) -> Unit) {
    val view = LocalView.current
    val clipboardManager = LocalClipboardManager.current
    val url =
        state.action.run {
            when (this) {
                is Action.FetchFormats -> url
                is Action.FetchPlaylist -> url
                else -> {
                    throw IllegalArgumentException()
                }
            }
        }
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = Icons.Outlined.ErrorOutline,
            contentDescription = null,
            modifier = Modifier.size(40.dp),
        )
        Text(
            text = stringResource(R.string.fetch_info_error_msg),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 12.dp),
        )
        Text(
            text = state.throwable.message.toString(),
            style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier =
                Modifier.padding(vertical = 16.dp, horizontal = 20.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            maxLines = 20,
            overflow = TextOverflow.Clip,
        )

        Row(modifier = Modifier) {
            FilledTonalButton(onClick = { onActionPost(state.action) }) { Text("Retry") }
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {
                    view.longPressHapticFeedback()
                    clipboardManager.setText(
                        AnnotatedString(
                            App.getVersionReport() + "\nURL: ${url}\n${state.throwable.message}"
                        )
                    )
                    ToastUtil.makeToast(R.string.error_copied)
                }
            ) {
                Text(stringResource(R.string.copy_error_report))
            }
        }
    }
}

@Composable
private fun DownloadDialogContent(
    modifier: Modifier = Modifier,
    state: DownloadDialogViewModel.SheetState,
    config: Config,
    preferences: DownloadUtil.DownloadPreferences,
    onPreferencesUpdate: (DownloadUtil.DownloadPreferences) -> Unit,
    onPresetEdit: (DownloadType?) -> Unit,
    onActionPost: (Action) -> Unit,
) {
    AnimatedContent(
        modifier = modifier,
        targetState = state,
        label = "",
        transitionSpec = {
            materialSharedAxisX(initialOffsetX = { it / 4 }, targetOffsetX = { -it / 4 })
        },
    ) { state ->
        when (state) {
            is Configure -> {
                check(state.urlList.isNotEmpty())
                if (state.urlList.size == 1) {
                    ConfigurePage(
                        url = state.urlList.first(),
                        config = config,
                        preferences = preferences,
                        onPresetEdit = onPresetEdit,
                        onConfigSave = {
                            Config.updatePreferences(newValue = it, oldValue = config)
                        },
                        settingChips = {
                            AdditionalSettings(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                isQuickDownload = false,
                                preference = preferences,
                                selectedType = config.downloadType,
                                onPreferenceUpdate = {
                                    onPreferencesUpdate(
                                        DownloadUtil.DownloadPreferences.createFromPreferences()
                                    )
                                },
                            )
                        },
                        onActionPost = { onActionPost(it) },
                    )
                } else {
                    ConfigurePagePlaylistVariant(
                        initialDownloadType = config.downloadType ?: Video,
                        preferences = preferences,
                        onPreferencesUpdate = onPreferencesUpdate,
                        onPresetEdit = onPresetEdit,
                        onDismissRequest = { onActionPost(Action.HideSheet) },
                    ) {
                        onActionPost(
                            Action.DownloadWithPreset(
                                urlList = state.urlList,
                                preferences = preferences.copy(extractAudio = it == Audio),
                            )
                        )
                    }
                }
            }

            is Error -> {
                ErrorPage(state = state, onActionPost = onActionPost)
            }

            is Loading -> {
                Column(modifier = Modifier.fillMaxWidth().padding(vertical = 120.dp)) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }

            InputUrl -> {
                InputUrlPage(
                    config = config,
                    onConfigUpdate = { Config.updatePreferences(newValue = it, oldValue = config) },
                    onActionPost = onActionPost,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun ErrorPreview() {
    SealModalBottomSheet(
        onDismissRequest = {},
        sheetState =
            with(LocalDensity.current) {
                SheetState(
                    initialValue = SheetValue.Expanded,
                    skipPartiallyExpanded = true,
                    velocityThreshold = { 56.dp.toPx() },
                    positionalThreshold = { 125.dp.toPx() },
                )
            },
    ) {
        ErrorPage(
            state =
                Error(
                    action =
                        Action.FetchFormats(
                            url = "",
                            audioOnly = true,
                            preferences = PreferencesMock,
                        ),
                    throwable = Exception("Not good"),
                ),
            onActionPost = {},
        )
    }
}

@Composable
fun FormatPage(
    modifier: Modifier = Modifier,
    state: SelectionState.FormatSelection,
    onDismissRequest: () -> Unit,
) {
    val sheetState =
        androidx.compose.material.rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true,
        )

    LaunchedEffect(state) { sheetState.show() }
    val scope = rememberCoroutineScope()
    BackHandler { scope.launch { sheetState.hide() }.invokeOnCompletion { onDismissRequest() } }

    SealModalBottomSheetM2Variant(sheetState = sheetState, sheetGesturesEnabled = false) {
        FormatPage(
            modifier = modifier,
            videoInfo = state.info,
            onNavigateBack = {
                scope.launch { sheetState.hide() }.invokeOnCompletion { onDismissRequest() }
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
/*@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO)*/
@Composable
private fun ConfigurePagePreview() {
    SealTheme() {
        SealModalBottomSheet(
            sheetState =
                with(LocalDensity.current) {
                    SheetState(
                        initialValue = SheetValue.Expanded,
                        skipPartiallyExpanded = true,
                        velocityThreshold = { 56.dp.toPx() },
                        positionalThreshold = { 125.dp.toPx() },
                    )
                },
            onDismissRequest = {},
            contentPadding = PaddingValues(),
        ) {
            ConfigurePage(
                config =
                    Config(
                        downloadType = Audio,
                        useFormatSelection = true,
                        typeEntries = entries - Command,
                    ),
                preferences = PreferencesMock,
                onConfigSave = {},
                settingChips = {},
            ) {}
        }
    }
}

@Composable
private fun ConfigurePage(
    modifier: Modifier = Modifier,
    url: String = "",
    config: Config,
    preferences: DownloadUtil.DownloadPreferences,
    settingChips: @Composable () -> Unit,
    onPresetEdit: (DownloadType?) -> Unit = {},
    onConfigSave: (Config) -> Unit,
    onActionPost: (Action) -> Unit,
) {
    var selectedOption by remember { mutableStateOf("MUSIC_MP3") }

    val domain = remember(url) {
        try {
            java.net.URI(url).host?.removePrefix("www.") ?: "youtu.be"
        } catch (e: Exception) {
            "youtu.be"
        }
    }
    val titleText = remember(url) {
        val extracted = url.substringAfterLast("/").take(55)
        if (extracted.isNotBlank()) extracted else "Video Download ($domain)"
    }

    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .background(Color(0xFF141416))
                .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        // Drag Handle
        Box(
            modifier =
                Modifier.align(Alignment.CenterHorizontally)
                    .width(42.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(Color(0xFF3E3E44))
        )
        Spacer(Modifier.height(16.dp))

        // Title: Download video as
        Text(
            text = "Download video as",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )

        Spacer(Modifier.height(16.dp))

        // Video Preview Row (Red thumbnail with play arrow + Title + domain)
        Row(
            modifier =
                Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF1E1E22))
                    .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier =
                    Modifier.size(width = 76.dp, height = 50.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF4A1818)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = null,
                    tint = Color(0xFFE53935),
                    modifier = Modifier.size(28.dp),
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = titleText,
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = domain,
                    color = Color(0xFF8E8E93),
                    fontSize = 11.sp,
                )
            }
        }

        Spacer(Modifier.height(18.dp))

        // Section: Music
        Text(
            text = "Music",
            color = Color(0xFF7E7E84),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
        )
        Spacer(Modifier.height(8.dp))

        SnaptubeFormatRow(
            icon = Icons.Filled.MusicNote,
            title = "Fast",
            fileSize = "12.9 MB",
            selected = selectedOption == "MUSIC_FAST",
            onClick = { selectedOption = "MUSIC_FAST" },
        )
        SnaptubeFormatRow(
            icon = Icons.Filled.MusicNote,
            title = "Classic MP3",
            fileSize = "14.0 MB",
            selected = selectedOption == "MUSIC_MP3",
            onClick = { selectedOption = "MUSIC_MP3" },
        )

        Spacer(Modifier.height(14.dp))

        // Section: Video
        Text(
            text = "Video",
            color = Color(0xFF7E7E84),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
        )
        Spacer(Modifier.height(8.dp))

        SnaptubeFormatRow(
            icon = Icons.Filled.Videocam,
            title = "Fast (360p)",
            fileSize = "25.1 MB",
            selected = selectedOption == "VIDEO_360P",
            onClick = { selectedOption = "VIDEO_360P" },
        )
        SnaptubeFormatRow(
            icon = Icons.Filled.Videocam,
            title = "High quality (1080p)",
            fileSize = "126.7 MB",
            selected = selectedOption == "VIDEO_1080P",
            onClick = { selectedOption = "VIDEO_1080P" },
        )

        Spacer(Modifier.height(14.dp))

        // More formats > button
        Row(
            modifier =
                Modifier.fillMaxWidth()
                    .clickable {
                        val isAudio = selectedOption.startsWith("MUSIC")
                        onActionPost(
                            Action.FetchFormats(
                                url = url,
                                audioOnly = isAudio,
                                preferences = preferences,
                            )
                        )
                    }
                    .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "More formats",
                color = Color.White,
                fontSize = 14.sp,
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "All",
                    color = Color(0xFF8E8E93),
                    fontSize = 13.sp,
                )
                Spacer(Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = Color(0xFF8E8E93),
                    modifier = Modifier.size(14.dp),
                )
            }
        }

        Spacer(Modifier.height(18.dp))

        // Big Yellow Download Button
        Button(
            onClick = {
                val updatedPrefs =
                    when (selectedOption) {
                        "MUSIC_FAST" -> preferences.copy(extractAudio = true, convertAudio = false)
                        "MUSIC_MP3" ->
                            preferences.copy(
                                extractAudio = true,
                                convertAudio = true,
                                audioConvertFormat = 0,
                            )
                        "VIDEO_360P" ->
                            preferences.copy(extractAudio = false, videoResolution = 360)
                        "VIDEO_1080P" ->
                            preferences.copy(extractAudio = false, videoResolution = 1080)
                        else -> preferences
                    }
                onActionPost(
                    Action.DownloadWithPreset(
                        urlList = listOf(url),
                        preferences = updatedPrefs,
                    )
                )
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = SnaptubeYellow,
                    contentColor = Color.Black,
                ),
        ) {
            Text(
                text = "Download",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
            )
        }

        Spacer(Modifier.height(12.dp))
    }
}

@Composable
private fun SnaptubeFormatRow(
    icon: ImageVector,
    title: String,
    fileSize: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier =
            Modifier.fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(vertical = 10.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFFC0C0C6),
            modifier = Modifier.size(20.dp),
        )
        Spacer(Modifier.width(14.dp))
        Text(
            text = title,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = fileSize,
            color = Color(0xFF8E8E93),
            fontSize = 13.sp,
        )
        Spacer(Modifier.width(16.dp))

        // Snaptube Custom Circular Radio Button
        if (selected) {
            Box(
                modifier = Modifier.size(22.dp).clip(CircleShape).background(SnaptubeYellow),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(15.dp),
                )
            }
        } else {
            Box(
                modifier =
                    Modifier.size(22.dp).clip(CircleShape).border(1.5.dp, Color(0xFF55555C), CircleShape)
            )
        }
    }
}

@Composable
fun ConfigurePagePlaylistVariant(
    modifier: Modifier = Modifier,
    initialDownloadType: DownloadType,
    preferences: DownloadUtil.DownloadPreferences,
    onPreferencesUpdate: (DownloadUtil.DownloadPreferences) -> Unit,
    onPresetEdit: (DownloadType?) -> Unit = {},
    onDismissRequest: () -> Unit,
    onDownload: (DownloadType) -> Unit,
) {

    var selectedType by remember(initialDownloadType) { mutableStateOf(initialDownloadType) }

    Column {
        Column(modifier = modifier.padding(horizontal = 20.dp)) {
            Header(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                title = stringResource(R.string.settings_before_download),
                icon = Icons.Outlined.DoneAll,
            )
            DrawerSheetSubtitle(text = stringResource(id = R.string.download_type))
            DownloadTypeSelectionGroup(
                typeEntries = listOf(Video, Audio),
                selectedType = selectedType,
                onSelect = { selectedType = it },
            )
            DrawerSheetSubtitle(
                text = stringResource(id = R.string.format_selection),
                modifier = Modifier,
            )
            Preset(
                modifier = Modifier,
                preference = preferences,
                selected = true,
                downloadType = selectedType,
                onClick = { onPresetEdit(selectedType) },
                showEditIcon = true,
                onEdit = { onPresetEdit(selectedType) },
            )
        }
        var expanded by remember { mutableStateOf(false) }
        ExpandableTitle(expanded = expanded, onClick = { expanded = true }) {
            AdditionalSettings(
                modifier = Modifier.padding(horizontal = 16.dp),
                isQuickDownload = false,
                preference = preferences,
                selectedType = Audio,
                onPreferenceUpdate = {
                    onPreferencesUpdate(DownloadUtil.DownloadPreferences.createFromPreferences())
                },
            )
        }

        ActionButtons(
            modifier = Modifier.padding(horizontal = 20.dp),
            canProceed = true,
            selectedType = selectedType,
            useFormatSelection = false,
            onCancel = onDismissRequest,
            onDownload = {
                onDownload(initialDownloadType)
                onDismissRequest()
            },
            onFetchInfo = { throw IllegalStateException() },
            onTaskStart = { throw IllegalStateException() },
        )
    }
}

@Composable
private fun AdditionalSettings(
    modifier: Modifier = Modifier,
    isQuickDownload: Boolean,
    selectedType: DownloadType?,
    preference: DownloadUtil.DownloadPreferences,
    onNavigateToCookieGeneratorPage: (String) -> Unit = {},
    onPreferenceUpdate: () -> Unit,
) {
    val cookiesProfiles by DatabaseUtil.getCookiesFlow().collectAsStateWithLifecycle(emptyList())
    var showCookiesDialog by rememberSaveable { mutableStateOf(false) }

    with(preference) {
        Row(modifier = modifier.fillMaxWidth().horizontalScroll(rememberScrollState())) {
            if (cookiesProfiles.isNotEmpty()) {
                VideoFilterChip(
                    selected = preference.cookies,
                    onClick = {
                        if (isQuickDownload) {
                            COOKIES.updateBoolean(!cookies)
                            onPreferenceUpdate()
                        } else {
                            showCookiesDialog = true
                        }
                    },
                    label = stringResource(id = R.string.cookies),
                )
            }

            VideoFilterChip(
                selected = downloadSubtitle,
                enabled = selectedType != Command,
                onClick = {
                    SUBTITLE.updateBoolean(!downloadSubtitle)
                    onPreferenceUpdate()
                },
                label = stringResource(id = R.string.download_subtitles),
            )
            VideoFilterChip(
                selected = createThumbnail,
                enabled = selectedType != Command,
                onClick = {
                    THUMBNAIL.updateBoolean(!createThumbnail)
                    onPreferenceUpdate()
                },
                label = stringResource(R.string.create_thumbnail),
            )
        }

        if (showCookiesDialog && cookiesProfiles.isNotEmpty()) {
            CookiesQuickSettingsDialog(
                onDismissRequest = { showCookiesDialog = false },
                onConfirm = {},
                cookieProfiles = cookiesProfiles,
                onCookieProfileClicked = { onNavigateToCookieGeneratorPage(it.url) },
                isCookiesEnabled = cookies,
                onCookiesToggled = {
                    COOKIES.updateBoolean(!cookies)
                    onPreferenceUpdate()
                },
            )
        }
    }
}

@Composable
fun ExpandableTitle(
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Column {
        Spacer(Modifier.height(8.dp))
        HorizontalDivider(thickness = Dp.Hairline, modifier = Modifier.padding(horizontal = 20.dp))
        Column(
            modifier =
                modifier
                    .clickable(
                        onClick = onClick,
                        onClickLabel = stringResource(R.string.show_more_actions),
                        enabled = !expanded,
                    )
                    .padding(top = 12.dp, bottom = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.width(24.dp))
                Text(
                    text = stringResource(R.string.additional_settings),
                    style = MaterialTheme.typography.labelLarge,
                )
                Spacer(modifier = Modifier.weight(1f))
                if (!expanded) {
                    Icon(
                        imageVector = Icons.Outlined.ExpandMore,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                    )
                    Spacer(modifier = Modifier.width(32.dp))
                }
            }
            AnimatedVisibility(expanded) {
                Column {
                    Spacer(Modifier.height(8.dp))
                    content()
                }
            }
        }
    }
}

@Composable
private fun SingleChoiceItem(
    modifier: Modifier = Modifier,
    title: String,
    desc: String,
    selected: Boolean,
    icon: (@Composable () -> Unit)? = null,
    action: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val corner by
        animateDpAsState(
            if (selected) 28.dp else 16.dp,
            animationSpec =
                spring(
                    stiffness = Spring.StiffnessMedium,
                    visibilityThreshold = Dp.VisibilityThreshold,
                ),
            label = "",
        )
    val color by
        animateColorAsState(
            if (selected) MaterialTheme.colorScheme.secondaryContainer
            else MaterialTheme.colorScheme.surfaceContainerLow,
            label = "",
        )

    Surface(
        selected = selected,
        onClick = onClick,
        color = color,
        shape = RoundedCornerShape(corner),
        modifier = modifier.padding(vertical = 4.dp).run { if (!enabled) alpha(0.32f) else this },
        enabled = enabled,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f).heightIn(min = 48.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    icon?.invoke()
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = title, style = MaterialTheme.typography.titleMedium)
                }
                Text(
                    text = desc,
                    style = MaterialTheme.typography.bodySmall,
                    minLines = 2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 32.dp),
                )
            }
            action?.invoke()
        }
    }
}

@Composable
internal fun Header(modifier: Modifier = Modifier, icon: ImageVector, title: String) {
    Column(modifier = modifier) {
        Icon(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            imageVector = icon,
            contentDescription = null,
        )
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            modifier =
                Modifier.align(Alignment.CenterHorizontally).padding(top = 16.dp, bottom = 8.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun DownloadTypeSelectionGroup(
    modifier: Modifier = Modifier,
    typeEntries: List<DownloadType>,
    selectedType: DownloadType?,
    onSelect: (DownloadType) -> Unit,
) {
    val typeCount = typeEntries.size
    if (typeCount == DownloadType.entries.size) {
        LazyRow(modifier = modifier) {
            items(typeEntries) { type ->
                SingleChoiceChip(
                    selected = selectedType == type,
                    label = type.label(),
                    onClick = { onSelect(type) },
                )
            }
        }
    } else {
        SingleChoiceSegmentedButtonRow(modifier = modifier.fillMaxWidth()) {
            typeEntries.forEachIndexed { index, type ->
                SingleChoiceSegmentedButton(
                    selected = selectedType == type,
                    onClick = { onSelect(type) },
                    shape = SegmentedButtonDefaults.itemShape(index, typeCount),
                ) {
                    Text(text = type.label())
                }
            }
        }
    }
}

@Composable
private fun Preset(
    modifier: Modifier = Modifier,
    preference: DownloadUtil.DownloadPreferences,
    downloadType: DownloadType?,
    selected: Boolean,
    showEditIcon: Boolean,
    onEdit: () -> Unit,
    onClick: () -> Unit,
) {
    val description =
        when (downloadType) {
            Audio -> {
                PreferenceStrings.getAudioPresetText(preference)
            }

            Video -> {
                PreferenceStrings.getVideoPresetText(preference)
            }

            Playlist -> stringResource(R.string.preset_format_selection_desc)
            else -> ""
        }

    SingleChoiceItem(
        modifier = modifier,
        title = stringResource(R.string.preset),
        desc = description,
        icon = {
            Crossfade(selected, animationSpec = spring(stiffness = Spring.StiffnessMedium)) {
                if (it) {
                    Icon(
                        imageVector = Icons.Filled.SettingsSuggest,
                        null,
                        modifier = Modifier.size(20.dp),
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.SettingsSuggest,
                        null,
                        modifier = Modifier.size(20.dp),
                    )
                }
            }
        },
        selected = selected,
        action = {
            Crossfade(showEditIcon, animationSpec = spring(stiffness = Spring.StiffnessMedium)) {
                if (it) {
                    Icon(
                        imageVector = Icons.Outlined.MoreVert,
                        contentDescription = stringResource(R.string.edit),
                        modifier = Modifier.size(20.dp),
                    )
                }
            }
        },
        onClick = {
            if (showEditIcon) {
                onEdit()
            } else {
                onClick()
            }
        },
    )
}

@Composable
private fun Custom(
    modifier: Modifier = Modifier,
    selected: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    SingleChoiceItem(
        modifier = modifier,
        title = stringResource(R.string.custom),
        desc = stringResource(R.string.custom_format_selection_desc),
        icon = {
            Crossfade(selected, animationSpec = spring(stiffness = Spring.StiffnessMedium)) {
                if (it) {
                    Icon(
                        imageVector = Icons.Filled.VideoFile,
                        null,
                        modifier = Modifier.size(20.dp),
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.VideoFile,
                        null,
                        modifier = Modifier.size(20.dp),
                    )
                }
            }
        },
        selected = selected,
        enabled = enabled,
        onClick = onClick,
    )
}

private enum class ActionButton {
    FetchInfo,
    Download,
    StartTask,
}

@Composable
private fun ActionButton.Icon() {
    Icon(
        imageVector =
            when (this) {
                FetchInfo -> Icons.AutoMirrored.Filled.ArrowForward
                Download -> Icons.Outlined.FileDownload
                StartTask -> Icons.Filled.DownloadDone
            },
        contentDescription = null,
        modifier = Modifier.size(18.dp),
    )
}

@Composable
private fun ActionButton.Label() {
    Text(
        stringResource(
            when (this) {
                FetchInfo -> R.string.proceed
                Download -> R.string.download
                StartTask -> R.string.start
            }
        ),
        modifier = Modifier.padding(start = 8.dp),
    )
}

@Composable
private fun ActionButtons(
    modifier: Modifier = Modifier,
    canProceed: Boolean,
    selectedType: DownloadType?,
    useFormatSelection: Boolean,
    onCancel: () -> Unit,
    onFetchInfo: () -> Unit,
    onDownload: () -> Unit,
    onTaskStart: () -> Unit,
) {
    val action =
        if (selectedType == Command) {
            StartTask
        } else if (selectedType == Playlist || useFormatSelection) {
            FetchInfo
        } else {
            Download
        }

    val state = rememberLazyListState()
    LazyRow(
        modifier = modifier.fillMaxWidth().padding(top = 12.dp),
        horizontalArrangement = Arrangement.End,
        state = state,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        item {
            OutlinedButtonWithIcon(
                modifier = Modifier.padding(horizontal = 12.dp),
                onClick = onCancel,
                icon = Icons.Outlined.Cancel,
                text = stringResource(R.string.cancel),
            )
        }
        item {
            Button(
                modifier = Modifier,
                onClick = {
                    when (action) {
                        FetchInfo -> onFetchInfo()
                        Download -> onDownload()
                        StartTask -> onTaskStart()
                    }
                },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                enabled = canProceed,
            ) {
                AnimatedContent(
                    targetState = action,
                    label = "",
                    transitionSpec = {
                        (fadeIn(animationSpec = tween(220, delayMillis = 90))).togetherWith(
                            fadeOut(animationSpec = tween(90))
                        )
                    },
                ) { action ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        action.Icon()
                        action.Label()
                    }
                }
            }
        }
    }
}
