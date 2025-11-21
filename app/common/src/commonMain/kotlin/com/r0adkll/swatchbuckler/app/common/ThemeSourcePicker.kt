package com.r0adkll.swatchbuckler.app.common

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.*
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.r0adkll.swatchbuckler.app.common.icons.*
import com.r0adkll.swatchbuckler.compose.*
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.dialogs.compose.PickerResultLauncher
import io.github.vinceglb.filekit.dialogs.compose.rememberFilePickerLauncher
import io.github.vinceglb.filekit.dialogs.compose.util.toImageBitmap

enum class ThemeSource {
  SeedColor,
  Image,
}

val Int.asColor: Color
  get() = Color(this)

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ThemeSourcePicker(
  isDarkMode: Boolean,
  onThemeGenerated: (Theme) -> Unit,
  onDarkModeChanged: (Boolean) -> Unit,
  modifier: Modifier = Modifier,
) {
  Card(
    modifier = modifier.fillMaxHeight(),
    shape = MaterialTheme.shapes.large,
  ) {
    var currentSourceTab by remember { mutableStateOf(ThemeSource.SeedColor) }
    var selectedSeedColor by remember { mutableStateOf(_root_ide_package_.com.r0adkll.swatchbuckler.app.common.theme.seedColors.first()) }
    var selectedImage by remember { mutableStateOf<ImageBitmap?>(null) }

    // Generate a theme from the selected seed color
    LaunchedEffect(selectedSeedColor) {
      val scheme = ThemeBuilder()
        .seedColor(selectedSeedColor)
        .dynamicSchema(Schema.Expressive)
        .build()

      onThemeGenerated(scheme)
    }

    ToggleButton(
      checked = isDarkMode,
      onCheckedChange = onDarkModeChanged,
      modifier =
        Modifier.fillMaxWidth()
          .padding(
            top = 16.dp,
            start = 16.dp,
            end = 16.dp,
          )) {
        Icon(
          if (isDarkMode) AppIcons.LightMode else AppIcons.DarkMode,
          contentDescription = null,
        )
        Spacer(Modifier.size(ToggleButtonDefaults.IconSpacing))
        Text(
          text = if (isDarkMode) "Light Mode" else "Dark Mode",
          modifier = Modifier.padding(start = 8.dp),
        )
      }

    ButtonGroup(overflowIndicator = {}, modifier = Modifier.fillMaxWidth().padding(16.dp)) {
      ThemeSource.entries.forEach { source ->
        toggleableItem(
          weight = 1f,
          label =
            when (source) {
              ThemeSource.SeedColor -> "Color"
              ThemeSource.Image -> "Image"
            },
          icon = {
            Icon(
              when (source) {
                ThemeSource.SeedColor -> AppIcons.Colors
                ThemeSource.Image -> AppIcons.Image
              },
              contentDescription = null,
            )
          },
          checked = currentSourceTab == source,
          onCheckedChange = { currentSourceTab = source },
        )
      }
    }

    AnimatedContent(
      targetState = currentSourceTab,
      transitionSpec = {
        if (targetState == ThemeSource.Image) {
          slideIntoContainer(SlideDirection.Start) togetherWith
            slideOutOfContainer(SlideDirection.Start)
        } else {
          slideIntoContainer(SlideDirection.End) togetherWith
            slideOutOfContainer(SlideDirection.End)
        }
      },
    ) { source ->
      when (source) {
        ThemeSource.SeedColor ->
          SeedColorPicker(
            selectedColor = selectedSeedColor,
            onColorPicked = { color -> selectedSeedColor = color },
          )
        ThemeSource.Image ->
          SourceImagePicker(
            image = selectedImage,
            onImagePicked = { image -> selectedImage = image },
            onColorPicked = { color -> selectedSeedColor = color },
          )
      }
    }
  }
}

@Composable
private fun SeedColorPicker(
  selectedColor: Color,
  onColorPicked: (Color) -> Unit = {},
  modifier: Modifier = Modifier
) {
  LazyVerticalGrid(
    columns = GridCells.Adaptive(ColorChoiceSize),
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    contentPadding = PaddingValues(16.dp),
  ) {
    items(_root_ide_package_.com.r0adkll.swatchbuckler.app.common.theme.seedColors) { color ->
      ColorChoiceButton(
        color = color,
        selected = color == selectedColor,
        onClick = { onColorPicked(color) },
      )
    }
  }
}

private val ColorChoiceSize = 40.dp

@Composable
private fun ColorChoiceButton(
  color: Color,
  selected: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val borderColor =
    if (selected) {
      Color.White
    } else {
      MaterialTheme.colorScheme.outlineVariant
    }

  Box(
    modifier =
      modifier
        .clip(CircleShape)
        .clickable(onClick = onClick)
        .size(ColorChoiceSize)
        .scale(if (selected) 1.2f else 1f)
        .background(color = color, shape = CircleShape)
        .border(
          width = 2.dp,
          color = borderColor,
          shape = CircleShape,
        ),
  )
}

internal val LineWidth = 10.dp
internal val PickerCornerRadius = 24.dp

@Composable
private fun SourceImagePicker(
  image: ImageBitmap?,
  onImagePicked: (ImageBitmap) -> Unit,
  onColorPicked: (Color) -> Unit,
  modifier: Modifier = Modifier,
) {
  var selectedImageFile by remember { mutableStateOf<PlatformFile?>(null) }
  var selectedSwatch by remember { mutableStateOf<Swatch?>(null) }

  LaunchedEffect(selectedImageFile) {
    if (selectedImageFile != null) {
      val bitmap = selectedImageFile!!.toImageBitmap()
      onImagePicked(bitmap)
    }
  }

  LaunchedEffect(image) {
    if (image != null) {
      selectedSwatch = SwatchBuilder.build(image)
    }
  }

  val shape = RoundedCornerShape(PickerCornerRadius)
  val borderColor = MaterialTheme.colorScheme.tertiary

  val infiniteTransition = rememberInfiniteTransition()
  val phaseOffset =
    infiniteTransition.animateFloat(
      0f,
      2000f,
      animationSpec =
        infiniteRepeatable(
          animation = tween(90_000, easing = LinearEasing),
          repeatMode = RepeatMode.Reverse,
        ),
    )

  // Pick a single file
  val launcher = rememberFilePickerLauncher { file ->
    file?.let { selectedImageFile = it }
  }

  Column(
    modifier =
      modifier
        .fillMaxSize()
        .padding(
          horizontal = 24.dp,
          vertical = 24.dp,
        ),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
  ) {
    Box(
      modifier = Modifier.weight(1f).fillMaxWidth(),
      contentAlignment = Alignment.Center,
    ) {
      EmptyPickerBox(
        shape = shape,
        launcher = launcher,
        borderColor = borderColor,
        phaseOffset = phaseOffset,
      )

      image?.let {
        Image(
          it,
          contentDescription = null,
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .fillMaxSize()
            .clip(shape)
            .border(width = 2.dp, color = borderColor, shape = shape),
        )
      }
    }

    Row(
      modifier = Modifier.height(ColorChoiceSize + 32.dp).fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      selectedSwatch?.let { swatch ->
        ColorChoiceButton(
          color = swatch.dominant,
          selected = false,
          onClick = { onColorPicked(swatch.dominant) },
        )

        swatch.vibrant.forEach { color ->
          ColorChoiceButton(
            color = color,
            selected = false,
            onClick = { onColorPicked(color) },
          )
        }
      }
    }
  }
}

@Composable
private fun EmptyPickerBox(
  shape: RoundedCornerShape,
  launcher: PickerResultLauncher,
  borderColor: Color,
  phaseOffset: State<Float>,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier =
      modifier
        .clip(shape)
        .clickable { launcher.launch() }
        .fillMaxSize()
        .background(
          color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
          shape = shape,
        )
        .drawWithContent {
          drawContent()
          val offsetX = 0.dp.toPx()
          val offsetY = 0.dp.toPx()

          val dashOnInterval1 = (LineWidth * 4).toPx()
          val dashOffInterval1 = (LineWidth * 1.5f).toPx()
          val dashOnInterval2 = (LineWidth / 4).toPx()
          val dashOffInterval2 = (LineWidth * 1.5f).toPx()
          drawRoundRect(
            color = borderColor,
            style =
              Stroke(
                width = 4.dp.toPx(),
                cap = StrokeCap.Round,
                pathEffect =
                  PathEffect.dashPathEffect(
                    intervals =
                      floatArrayOf(
                        dashOnInterval1,
                        dashOffInterval1,
                        dashOnInterval2,
                        dashOffInterval2,
                      ),
                    phase = phaseOffset.value,
                  ),
              ),
            cornerRadius = CornerRadius(PickerCornerRadius.toPx()),
            topLeft = Offset(offsetX, offsetY * 2f),
            size =
              size.copy(
                width = size.width - (offsetX * 2f),
                height = size.height - (offsetY * 4f),
              ),
          )
        }
        .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
  ) {
    Icon(
      AppIcons.ImageSearch,
      contentDescription = null,
      modifier =
        Modifier.background(MaterialTheme.colorScheme.secondaryContainer, CircleShape)
          .size(72.dp)
          .padding(16.dp),
      tint = MaterialTheme.colorScheme.onSecondaryContainer,
    )

    Text(
      text = "Browse for an image",
      style = MaterialTheme.typography.titleMedium,
      color = MaterialTheme.colorScheme.secondary,
    )
  }
}
