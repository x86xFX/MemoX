package me.theek.memox.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import me.theek.memox.core.design_system.ui.theme.K2DFontFamily
import me.theek.memox.core.design_system.ui.theme.Onboarding_Background_Dark_Blue
import me.theek.memox.feature.onboarding.components.OnboardBackgroundDesign

@Composable
fun OnboardingScreen(
    onNavigateToHome: () -> Unit,
    onboardingScreenViewModel: OnboardingScreenViewModel = hiltViewModel()
) {
    OnboardBackgroundDesign {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 20.dp, alignment = Alignment.Bottom)
        ) {
            Text(
                text = stringResource(R.string.memox),
                maxLines = 1,
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.displayMedium.fontSize,
                color = MaterialTheme.colorScheme.primary,
                fontFamily = K2DFontFamily,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = stringResource(R.string.onboarding_description),
                maxLines = 7,
                textAlign = TextAlign.Start,
                fontSize = MaterialTheme.typography.displaySmall.fontSize,
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = K2DFontFamily,
                lineHeight = 44.sp,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onboardingScreenViewModel::onHideOnboardingScreen,
                    modifier = Modifier.height(50.dp),
                    colors = ButtonDefaults
                        .buttonColors(
                            containerColor = Onboarding_Background_Dark_Blue,
                            contentColor = Color.White
                        )
                ) {
                    Text(
                        text = stringResource(id = R.string.get_started),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.background)
                            .padding(2.dp),
                        imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                        contentDescription = stringResource(id = R.string.get_started),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }

    LaunchedEffect(onboardingScreenViewModel.shouldNavigateToHome) {
        if (onboardingScreenViewModel.shouldNavigateToHome) {
            onNavigateToHome()
        }
    }
}