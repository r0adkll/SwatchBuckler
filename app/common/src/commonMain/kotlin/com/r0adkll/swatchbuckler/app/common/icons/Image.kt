package com.r0adkll.swatchbuckler.app.common.icons

import androidx.compose.foundation.Image as foundationImage
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

val AppIcons.Image: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
  ImageVector
    .Builder(
      name = "Image",
      defaultWidth = 24.dp,
      defaultHeight = 24.dp,
      viewportWidth = 960f,
      viewportHeight = 960f,
    ).apply {
      path(fill = SolidColor(Color(0xFFE8EAED))) {
        moveTo(200f, 840f)
        quadToRelative(-33f, 0f, -56.5f, -23.5f)
        reflectiveQuadTo(120f, 760f)
        verticalLineToRelative(-560f)
        quadToRelative(0f, -33f, 23.5f, -56.5f)
        reflectiveQuadTo(200f, 120f)
        horizontalLineToRelative(560f)
        quadToRelative(33f, 0f, 56.5f, 23.5f)
        reflectiveQuadTo(840f, 200f)
        verticalLineToRelative(560f)
        quadToRelative(0f, 33f, -23.5f, 56.5f)
        reflectiveQuadTo(760f, 840f)
        lineTo(200f, 840f)
        close()
        moveTo(200f, 760f)
        horizontalLineToRelative(560f)
        verticalLineToRelative(-560f)
        lineTo(200f, 200f)
        verticalLineToRelative(560f)
        close()
        moveTo(240f, 680f)
        horizontalLineToRelative(480f)
        lineTo(570f, 480f)
        lineTo(450f, 640f)
        lineToRelative(-90f, -120f)
        lineToRelative(-120f, 160f)
        close()
        moveTo(200f, 760f)
        verticalLineToRelative(-560f)
        verticalLineToRelative(560f)
        close()
      }
    }.build()
}

@Preview
@Composable
private fun ImagePreview() {
  Box(modifier = Modifier.padding(12.dp)) {
    foundationImage(imageVector = AppIcons.Image, contentDescription = null)
  }
}
