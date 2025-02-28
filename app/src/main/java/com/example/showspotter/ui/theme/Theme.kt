import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.showspotter.R

private val fontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

private val playfairDisplay = GoogleFont("Playfair Display")
private val poppins = GoogleFont("Poppins")

private val playfairDisplayFamily = FontFamily(
    Font(googleFont = playfairDisplay, fontProvider = fontProvider, weight = FontWeight.Bold),
    Font(googleFont = playfairDisplay, fontProvider = fontProvider, weight = FontWeight.SemiBold)
)

private val poppinsFamily = FontFamily(
    Font(googleFont = poppins, fontProvider = fontProvider, weight = FontWeight.SemiBold),
    Font(googleFont = poppins, fontProvider = fontProvider, weight = FontWeight.Medium),
    Font(googleFont = poppins, fontProvider = fontProvider, weight = FontWeight.Normal)
)

private val ColorPalette = lightColorScheme(
    primary = Color(0xFF2B2A4C),
    secondary = Color(0xFFB31312),
    background = Color(0xFFEEE2DE),
    surface = Color.White,
    onPrimary = Color(0xFFEEE2DE),
    onSecondary = Color.White,
    onBackground = Color(0xFF2B2A4C),
    onSurface = Color(0xFF2B2A4C)
)


private val AppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = playfairDisplayFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    titleLarge = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)

@Composable
fun ShowSpotterTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorPalette,
        typography = AppTypography,
        content = content
    )
}
