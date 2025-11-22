/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.r0adkll.swatchbuckler.color.scheme

import com.r0adkll.swatchbuckler.color.dynamiccolor.ColorSpec.SpecVersion
import com.r0adkll.swatchbuckler.color.dynamiccolor.ColorSpecs
import com.r0adkll.swatchbuckler.color.dynamiccolor.DynamicScheme
import com.r0adkll.swatchbuckler.color.dynamiccolor.Variant
import com.r0adkll.swatchbuckler.color.hct.Hct

/** A loud theme, colorfulness is maximum for Primary palette, increased for others. */
class SchemeVibrant(
  sourceColorHct: Hct,
  isDark: Boolean,
  contrastLevel: Double,
  specVersion: SpecVersion = DEFAULT_SPEC_VERSION,
  platform: Platform = DEFAULT_PLATFORM,
) : DynamicScheme(
    sourceColorHct,
    Variant.VIBRANT,
    isDark,
    contrastLevel,
    platform,
    specVersion,
    ColorSpecs
      .get(specVersion)
      .getPrimaryPalette(Variant.VIBRANT, sourceColorHct, isDark, platform, contrastLevel),
    ColorSpecs
      .get(specVersion)
      .getSecondaryPalette(Variant.VIBRANT, sourceColorHct, isDark, platform, contrastLevel),
    ColorSpecs
      .get(specVersion)
      .getTertiaryPalette(Variant.VIBRANT, sourceColorHct, isDark, platform, contrastLevel),
    ColorSpecs
      .get(specVersion)
      .getNeutralPalette(Variant.VIBRANT, sourceColorHct, isDark, platform, contrastLevel),
    ColorSpecs
      .get(specVersion)
      .getNeutralVariantPalette(Variant.VIBRANT, sourceColorHct, isDark, platform, contrastLevel),
    ColorSpecs
      .get(specVersion)
      .getErrorPalette(Variant.VIBRANT, sourceColorHct, isDark, platform, contrastLevel),
  )
