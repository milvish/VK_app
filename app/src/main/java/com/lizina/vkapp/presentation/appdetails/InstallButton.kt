package com.lizina.vkapp.presentation.appdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lizina.vkapp.R
import com.lizina.vkapp.ui.theme.VKAppTheme

@Composable
fun InstallButton(
    installState: InstallState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        val (text, isCancelStyle) = when (installState) {
            InstallState.NotInstalled -> stringResource(R.string.install) to false
            is InstallState.Installing -> stringResource(R.string.cancel) to true
            InstallState.Installed -> stringResource(R.string.open) to false
        }

        Button(
            onClick = onClick,
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(vertical = 12.dp),
            colors = if (isCancelStyle) {
                ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer,
                )
            } else ButtonDefaults.buttonColors(),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = text)
        }

        if (installState is InstallState.Installing) {
            Spacer(Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { installState.progress / 100f },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "${installState.progress}%",
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewNotInstalled() {
    VKAppTheme {
        InstallButton(installState = InstallState.NotInstalled, onClick = {})
    }
}

@Preview
@Composable
private fun PreviewInstalling() {
    VKAppTheme {
        InstallButton(installState = InstallState.Installing(45), onClick = {})
    }
}

@Preview
@Composable
private fun PreviewInstalled() {
    VKAppTheme {
        InstallButton(installState = InstallState.Installed, onClick = {})
    }
}