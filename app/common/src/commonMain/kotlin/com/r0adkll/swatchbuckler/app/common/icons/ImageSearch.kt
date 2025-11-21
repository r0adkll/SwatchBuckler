package com.r0adkll.swatchbuckler.app.common.icons

import androidx.compose.foundation.Image
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

val AppIcons.ImageSearch: ImageVector by
  lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "ImageSearch",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f,
      )
      .apply {
        path(fill = SolidColor(Color(0xFFE8EAED))) {
          moveToRelative(450f, 640f)
          lineToRelative(-66f, -88f)
          quadToRelative(-9f, -12f, -24f, -12f)
          reflectiveQuadToRelative(-24f, 12f)
          lineToRelative(-72f, 96f)
          quadToRelative(-8f, 10f, -2f, 21f)
          reflectiveQuadToRelative(18f, 11f)
          horizontalLineToRelative(400f)
          quadToRelative(12f, 0f, 18f, -11f)
          reflectiveQuadToRelative(-2f, -21f)
          lineToRelative(-99f, -132f)
          quadToRelative(-11f, -2f, -22.5f, -5f)
          reflectiveQuadToRelative(-22.5f, -7f)
          lineTo(450f, 640f)
          close()
          moveTo(200f, 840f)
          quadToRelative(-33f, 0f, -56.5f, -23.5f)
          reflectiveQuadTo(120f, 760f)
          verticalLineToRelative(-560f)
          quadToRelative(0f, -33f, 23.5f, -56.5f)
          reflectiveQuadTo(200f, 120f)
          horizontalLineToRelative(160f)
          quadToRelative(17f, 0f, 28.5f, 11.5f)
          reflectiveQuadTo(400f, 160f)
          quadToRelative(0f, 17f, -11.5f, 28.5f)
          reflectiveQuadTo(360f, 200f)
          lineTo(200f, 200f)
          verticalLineToRelative(560f)
          horizontalLineToRelative(560f)
          verticalLineToRelative(-133f)
          quadToRelative(0f, -17f, 11.5f, -28.5f)
          reflectiveQuadTo(800f, 587f)
          quadToRelative(17f, 0f, 28.5f, 11.5f)
          reflectiveQuadTo(840f, 627f)
          verticalLineToRelative(133f)
          quadToRelative(0f, 33f, -23.5f, 56.5f)
          reflectiveQuadTo(760f, 840f)
          lineTo(200f, 840f)
          close()
          moveTo(480f, 480f)
          close()
          moveTo(642f, 440f)
          quadToRelative(-74f, 0f, -126f, -52.5f)
          reflectiveQuadTo(464f, 260f)
          quadToRelative(0f, -75f, 52.5f, -127.5f)
          reflectiveQuadTo(644f, 80f)
          quadToRelative(75f, 0f, 127.5f, 52.5f)
          reflectiveQuadTo(824f, 260f)
          quadToRelative(0f, 27f, -8f, 52f)
          reflectiveQuadToRelative(-20f, 46f)
          lineToRelative(94f, 94f)
          quadToRelative(11f, 11f, 11f, 28f)
          reflectiveQuadToRelative(-11f, 28f)
          quadToRelative(-11f, 11f, -28f, 11f)
          reflectiveQuadToRelative(-28f, -11f)
          lineToRelative(-96f, -96f)
          quadToRelative(-21f, 14f, -45f, 21f)
          reflectiveQuadToRelative(-51f, 7f)
          close()
          moveTo(644f, 360f)
          quadToRelative(42f, 0f, 71f, -29f)
          reflectiveQuadToRelative(29f, -71f)
          quadToRelative(0f, -42f, -29f, -71f)
          reflectiveQuadToRelative(-71f, -29f)
          quadToRelative(-42f, 0f, -71f, 29f)
          reflectiveQuadToRelative(-29f, 71f)
          quadToRelative(0f, 42f, 29f, 71f)
          reflectiveQuadToRelative(71f, 29f)
          close()
        }
      }
      .build()
  }

@Preview
@Composable
private fun ImageSearchPreview() {
  Box(modifier = Modifier.padding(12.dp)) {
    Image(imageVector = AppIcons.ImageSearch, contentDescription = null)
  }
}
