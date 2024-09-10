package me.theek.memox.feature.note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import me.theek.memox.core.design_system.ui.theme.K2DFontFamily
import me.theek.memox.core.domain.use_case.ValidationResult
import me.theek.memox.feature.note.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewFolderDialog(
    folderName: String,
    validationResult: ValidationResult,
    onFolderNameChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onFolderCreate: () -> Unit,
    modifier: Modifier = Modifier
) {
    BasicAlertDialog (
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        content = {
            Column(
                modifier = Modifier
                    .clip(AlertDialogDefaults.shape)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.CenterVertically)
            ) {
                Text(
                    text = stringResource(R.string.create_a_folder),
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    fontFamily = K2DFontFamily,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = folderName,
                    onValueChange = onFolderNameChange,
                    label = {
                        Text(text = stringResource(R.string.folder_name))
                    },
                    isError = validationResult.isValid.not(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { onFolderCreate() }
                    ),
                    supportingText = {
                        if (validationResult.isValid.not() && validationResult.errorMessage != null) {
                            Text(
                                text = validationResult.errorMessage!!,
                                color = MaterialTheme.colorScheme.error,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    FilledTonalButton (onClick = onFolderCreate) {
                        Text(text = stringResource(R.string.create))
                    }
                }
            }
        }
    )
}