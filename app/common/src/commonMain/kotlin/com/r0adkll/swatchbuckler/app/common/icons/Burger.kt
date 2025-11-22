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

val AppIcons.Burger: ImageVector by
  lazy(LazyThreadSafetyMode.NONE) {
    ImageVector
      .Builder(
        name = "Burger",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f,
      ).apply {
        path(fill = SolidColor(Color.Black)) {
          moveTo(160f, 840f)
          quadToRelative(-33f, 0f, -56.5f, -23.5f)
          reflectiveQuadTo(80f, 760f)
          verticalLineToRelative(-80f)
          quadToRelative(0f, -17f, 11.5f, -28.5f)
          reflectiveQuadTo(120f, 640f)
          horizontalLineToRelative(720f)
          quadToRelative(17f, 0f, 28.5f, 11.5f)
          reflectiveQuadTo(880f, 680f)
          verticalLineToRelative(80f)
          quadToRelative(0f, 33f, -23.5f, 56.5f)
          reflectiveQuadTo(800f, 840f)
          lineTo(160f, 840f)
          close()
          moveTo(160f, 720f)
          verticalLineToRelative(40f)
          horizontalLineToRelative(640f)
          verticalLineToRelative(-40f)
          lineTo(160f, 720f)
          close()
          moveTo(480f, 540f)
          quadToRelative(-36f, 0f, -57f, 20f)
          reflectiveQuadToRelative(-77f, 20f)
          quadToRelative(-56f, 0f, -76f, -20f)
          reflectiveQuadToRelative(-56f, -20f)
          quadToRelative(-28f, 0f, -45.5f, 13.5f)
          reflectiveQuadTo(121f, 575f)
          quadToRelative(-16f, 5f, -28.5f, -6.5f)
          reflectiveQuadTo(80f, 540f)
          quadToRelative(0f, -17f, 11.5f, -29.5f)
          reflectiveQuadTo(119f, 490f)
          quadToRelative(17f, -9f, 37f, -19.5f)
          reflectiveQuadToRelative(58f, -10.5f)
          quadToRelative(56f, 0f, 76f, 20f)
          reflectiveQuadToRelative(56f, 20f)
          quadToRelative(36f, 0f, 57f, -20f)
          reflectiveQuadToRelative(77f, -20f)
          quadToRelative(56f, 0f, 77f, 20f)
          reflectiveQuadToRelative(57f, 20f)
          quadToRelative(36f, 0f, 56f, -20f)
          reflectiveQuadToRelative(76f, -20f)
          quadToRelative(36f, 0f, 57f, 10f)
          reflectiveQuadToRelative(38f, 19f)
          quadToRelative(16f, 8f, 27.5f, 21f)
          reflectiveQuadToRelative(11.5f, 30f)
          quadToRelative(0f, 17f, -12f, 28.5f)
          reflectiveQuadToRelative(-28f, 6.5f)
          quadToRelative(-29f, -8f, -45.5f, -21.5f)
          reflectiveQuadTo(750f, 540f)
          quadToRelative(-36f, 0f, -58f, 20f)
          reflectiveQuadToRelative(-78f, 20f)
          quadToRelative(-56f, 0f, -77f, -20f)
          reflectiveQuadToRelative(-57f, -20f)
          close()
          moveTo(480f, 120f)
          quadToRelative(74f, 0f, 145.5f, 13.5f)
          reflectiveQuadToRelative(128f, 42f)
          quadToRelative(56.5f, 28.5f, 91.5f, 74f)
          reflectiveQuadTo(880f, 360f)
          quadToRelative(0f, 17f, -11.5f, 28.5f)
          reflectiveQuadTo(840f, 400f)
          lineTo(120f, 400f)
          quadToRelative(-17f, 0f, -28.5f, -11.5f)
          reflectiveQuadTo(80f, 360f)
          quadToRelative(0f, -65f, 35f, -110.5f)
          reflectiveQuadToRelative(91.5f, -74f)
          quadToRelative(56.5f, -28.5f, 128f, -42f)
          reflectiveQuadTo(480f, 120f)
          close()
          moveTo(480f, 200f)
          quadToRelative(-124f, 0f, -207.5f, 31f)
          reflectiveQuadTo(166f, 320f)
          horizontalLineToRelative(628f)
          quadToRelative(-23f, -58f, -106.5f, -89f)
          reflectiveQuadTo(480f, 200f)
          close()
          moveTo(480f, 720f)
          close()
          moveTo(480f, 320f)
          close()
        }
      }.build()
  }

@Preview
@Composable
private fun BurgerPreview() {
  Box(modifier = Modifier.padding(12.dp)) {
    Image(imageVector = AppIcons.Burger, contentDescription = null)
  }
}
