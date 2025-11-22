package com.r0adkll.swatchbuckler.app.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.FloatingToolbarHorizontalFabPosition
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.unit.dp
import com.r0adkll.swatchbuckler.app.common.icons.Add
import com.r0adkll.swatchbuckler.app.common.icons.Bookmark
import com.r0adkll.swatchbuckler.app.common.icons.Burger
import com.r0adkll.swatchbuckler.app.common.icons.Colors
import com.r0adkll.swatchbuckler.app.common.icons.Image
import com.r0adkll.swatchbuckler.app.common.icons.Weights

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WidgetGallery(modifier: Modifier = Modifier) {
  Card(
    modifier =
      modifier
        .fillMaxHeight(),
    shape = MaterialTheme.shapes.large,
  ) {
    Column(
      modifier = Modifier.padding(16.dp),
      horizontalAlignment = Alignment.Start,
      verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      ButtonRow()
      ControlRow()
      ExpandingToolbarRow()
      IndicatorRow()
      ToolbarRow()
      SliderRow()
    }
  }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun ButtonRow(modifier: Modifier = Modifier) {
  Row(
    modifier =
      modifier
        .fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    val mediumSize = ButtonDefaults.MediumContainerHeight
    Button(
      onClick = {},
      modifier = Modifier.heightIn(mediumSize),
      contentPadding = ButtonDefaults.contentPaddingFor(mediumSize),
      shapes = ButtonDefaults.shapesFor(mediumSize),
    ) {
      Icon(
        _root_ide_package_.com.r0adkll.swatchbuckler.app.common.icons.AppIcons.Burger,
        contentDescription = null,
        modifier = Modifier.size(ButtonDefaults.iconSizeFor(mediumSize)),
      )
      Spacer(Modifier.size(ButtonDefaults.iconSpacingFor(mediumSize)))
      Text(
        text = "Eat!",
        style = ButtonDefaults.textStyleFor(mediumSize),
      )
    }

    val smallSize = ButtonDefaults.MinHeight
    FilledTonalButton(
      onClick = {},
      contentPadding = ButtonDefaults.contentPaddingFor(smallSize),
      shapes = ButtonDefaults.shapesFor(smallSize),
    ) {
      Icon(
        _root_ide_package_.com.r0adkll.swatchbuckler.app.common.icons.AppIcons.Weights,
        contentDescription = null,
        modifier = Modifier.size(ButtonDefaults.iconSizeFor(smallSize)),
      )
      Spacer(Modifier.size(ButtonDefaults.iconSpacingFor(smallSize)))
      Text(
        text = "Lift!",
        style = ButtonDefaults.textStyleFor(smallSize),
      )
    }

    val extraSmall = ButtonDefaults.ExtraSmallContainerHeight
    OutlinedButton(
      onClick = {},
      contentPadding = ButtonDefaults.contentPaddingFor(extraSmall),
      shapes = ButtonDefaults.shapesFor(extraSmall),
      modifier = Modifier.heightIn(extraSmall),
    ) {
      Icon(
        _root_ide_package_.com.r0adkll.swatchbuckler.app.common.icons.AppIcons.Bookmark,
        contentDescription = null,
        modifier = Modifier.size(ButtonDefaults.iconSizeFor(extraSmall)),
      )
      Spacer(Modifier.size(ButtonDefaults.iconSpacingFor(extraSmall)))
      Text(
        text = "Learn!",
        style = ButtonDefaults.textStyleFor(extraSmall),
      )
    }
  }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun ExpandingToolbarRow(modifier: Modifier = Modifier) {
  var expanded by remember { mutableStateOf(true) }
  HorizontalFloatingToolbar(
    expanded = expanded,
    colors = FloatingToolbarDefaults.vibrantFloatingToolbarColors(),
    floatingActionButtonPosition = FloatingToolbarHorizontalFabPosition.Start,
    floatingActionButton = {
      TooltipBox(
        positionProvider =
          TooltipDefaults.rememberTooltipPositionProvider(
            TooltipAnchorPosition.Above,
          ),
        tooltip = { PlainTooltip { Text("Localized description") } },
        state = rememberTooltipState(),
      ) {
        // Match the FAB to the vibrantColors. See also
        // StandardFloatingActionButton.
        FloatingToolbarDefaults.VibrantFloatingActionButton(
          onClick = { expanded = !expanded },
        ) {
          Icon(_root_ide_package_.com.r0adkll.swatchbuckler.app.common.icons.AppIcons.Add, null)
        }
      }
    },
    modifier = modifier,
  ) {
// Make sure the buttons are not focusable if they are not visible, so that
    // keyboard focus doesn't go to an invisible element on the screen.
    TooltipBox(
      positionProvider =
        TooltipDefaults.rememberTooltipPositionProvider(
          TooltipAnchorPosition.Above,
        ),
      tooltip = { PlainTooltip { Text("Localized description") } },
      state = rememberTooltipState(),
    ) {
      IconButton(
        onClick = { /* doSomething() */ },
        Modifier.focusProperties { canFocus = expanded },
      ) {
        Icon(
          _root_ide_package_.com.r0adkll.swatchbuckler.app.common.icons.AppIcons.Colors,
          contentDescription = "Localized description",
        )
      }
    }
    TooltipBox(
      positionProvider =
        TooltipDefaults.rememberTooltipPositionProvider(
          TooltipAnchorPosition.Above,
        ),
      tooltip = { PlainTooltip { Text("Localized description") } },
      state = rememberTooltipState(),
    ) {
      IconButton(
        onClick = { /* doSomething() */ },
        Modifier.focusProperties { canFocus = expanded },
      ) {
        Icon(
          _root_ide_package_.com.r0adkll.swatchbuckler.app.common.icons.AppIcons.Image,
          contentDescription = "Localized description",
        )
      }
    }
    TooltipBox(
      positionProvider =
        TooltipDefaults.rememberTooltipPositionProvider(
          TooltipAnchorPosition.Above,
        ),
      tooltip = { PlainTooltip { Text("Localized description") } },
      state = rememberTooltipState(),
    ) {
      IconButton(
        onClick = { /* doSomething() */ },
        Modifier.focusProperties { canFocus = expanded },
      ) {
        Icon(
          _root_ide_package_.com.r0adkll.swatchbuckler.app.common.icons.AppIcons.Bookmark,
          contentDescription = "Localized description",
        )
      }
    }
    TooltipBox(
      positionProvider =
        TooltipDefaults.rememberTooltipPositionProvider(
          TooltipAnchorPosition.Above,
        ),
      tooltip = { PlainTooltip { Text("Localized description") } },
      state = rememberTooltipState(),
    ) {
      IconButton(
        onClick = { /* doSomething() */ },
        Modifier.focusProperties { canFocus = expanded },
      ) {
        Icon(
          _root_ide_package_.com.r0adkll.swatchbuckler.app.common.icons.AppIcons.Burger,
          contentDescription = "Localized description",
        )
      }
    }
    TooltipBox(
      positionProvider =
        TooltipDefaults.rememberTooltipPositionProvider(
          TooltipAnchorPosition.Above,
        ),
      tooltip = { PlainTooltip { Text("Localized description") } },
      state = rememberTooltipState(),
    ) {
      IconButton(
        onClick = { /* doSomething() */ },
        Modifier.focusProperties { canFocus = expanded },
      ) {
        Icon(
          _root_ide_package_.com.r0adkll.swatchbuckler.app.common.icons.AppIcons.Weights,
          contentDescription = "Localized description",
        )
      }
    }
  }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun ToolbarRow(modifier: Modifier = Modifier) {
  val options = listOf("Burger", "Lift", "Paint")
  val unCheckedIcons =
    listOf(
      _root_ide_package_.com.r0adkll.swatchbuckler.app.common.icons.AppIcons.Burger,
      _root_ide_package_.com.r0adkll.swatchbuckler.app.common.icons.AppIcons.Weights,
      _root_ide_package_.com.r0adkll.swatchbuckler.app.common.icons.AppIcons.Colors,
    )
  var checked by remember { mutableIntStateOf(0) }

  Row(
    modifier.padding(horizontal = 8.dp),
    horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
  ) {
    val modifiers = listOf(Modifier.weight(1f), Modifier.weight(1.5f), Modifier.weight(1f))
    options.forEachIndexed { index, label ->
      ToggleButton(
        checked = checked == index,
        onCheckedChange = { checked = index },
        modifier = modifiers[index],
//        colors = ToggleButtonDefaults.tonalToggleButtonColors(),
        shapes =
          when (index) {
            0 -> ButtonGroupDefaults.connectedLeadingButtonShapes()
            options.lastIndex -> ButtonGroupDefaults.connectedTrailingButtonShapes()
            else -> ButtonGroupDefaults.connectedMiddleButtonShapes()
          },
      ) {
        Icon(
          unCheckedIcons[index],
          contentDescription = "Localized description",
        )
        Spacer(Modifier.size(ToggleButtonDefaults.IconSpacing))
        Text(label)
      }
    }
  }
}

@Composable
private fun ControlRow(modifier: Modifier = Modifier) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    var switchChecked by remember { mutableStateOf(true) }
    Switch(
      checked = switchChecked,
      onCheckedChange = { switchChecked = it },
      thumbContent = {
        Icon(
          _root_ide_package_.com.r0adkll.swatchbuckler.app.common.icons.AppIcons.Burger,
          contentDescription = null,
          modifier = Modifier.size(SwitchDefaults.IconSize),
        )
      },
    )

    var checkboxChecked by remember { mutableStateOf(true) }
    Checkbox(
      checked = checkboxChecked,
      onCheckedChange = { checkboxChecked = it },
    )

    var radioChecked by remember { mutableStateOf(true) }
    RadioButton(
      selected = radioChecked,
      onClick = { radioChecked = !radioChecked },
    )
  }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun IndicatorRow(modifier: Modifier = Modifier) {
  Row(
    modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    LoadingIndicator()

    Column(
      verticalArrangement = Arrangement.spacedBy(12.dp),
      modifier = Modifier.weight(1f),
    ) {
      LinearProgressIndicator(Modifier.fillMaxWidth())
      LinearProgressIndicator({ 0.64f }, Modifier.fillMaxWidth())
      LinearWavyProgressIndicator({ 0.48f }, Modifier.fillMaxWidth())
    }

    CircularWavyProgressIndicator()
  }
}

@Composable
private fun SliderRow(modifier: Modifier = Modifier) {
  var range by remember { mutableStateOf(25f..60f) }
  RangeSlider(
    value = range,
    onValueChange = { range = it },
    modifier =
      modifier
        .fillMaxWidth()
        .padding(vertical = 24.dp),
    valueRange = 0f..100f,
    steps = 10,
  )
}
