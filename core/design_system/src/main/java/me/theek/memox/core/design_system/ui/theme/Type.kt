package me.theek.memox.core.design_system.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import me.theek.memox.core.design_system.R

private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

private val K2DFont = GoogleFont("K2D")
private val PoppinsFont = GoogleFont("Poppins")

val K2DFontFamily = FontFamily(
    Font(
        googleFont = K2DFont,
        fontProvider = provider
    )
)

val PoppinsFontFamily = FontFamily(
    Font(
        googleFont = PoppinsFont,
        fontProvider = provider
    )
)

val baseline = Typography()

val Typography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = K2DFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = K2DFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = K2DFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = K2DFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = K2DFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = K2DFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = K2DFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = K2DFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = K2DFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = PoppinsFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = PoppinsFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = PoppinsFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = PoppinsFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = PoppinsFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = PoppinsFontFamily),
)