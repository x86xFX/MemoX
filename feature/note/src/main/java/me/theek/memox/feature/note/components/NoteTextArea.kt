package me.theek.memox.feature.note.components

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.theek.memox.core.domain.use_case.ValidationResult
import me.theek.memox.feature.location.components.LocationChip
import me.theek.memox.feature.note.R

@Composable
internal fun NoteTextArea(
    maxHeight: Dp,
    title: String,
    titleValidationResult: ValidationResult,
    description: String,
    selectedPics: List<Uri>?,
    isLocationAdded: Boolean,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onAddedLocationRemove: () -> Unit,
    onAddedPicsRemove: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = onTitleChange,
            label = {
                Text(text = stringResource(R.string.title))
            },
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(focusDirection = FocusDirection.Down)
                }
            ),
            isError = titleValidationResult.isValid.not(),
            supportingText = {
                if (titleValidationResult.isValid.not() && titleValidationResult.errorMessage != null) {
                    Text(
                        text = titleValidationResult.errorMessage!!,
                        color = MaterialTheme.colorScheme.error,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(maxHeight - 180.dp),
            value = description,
            onValueChange = onDescriptionChange,
            label = {
                Text(text = stringResource(R.string.description))
            },
            shape = RoundedCornerShape(12.dp),
            placeholder = {
                Text(text = stringResource(R.string.type_something_in_your_mind))
            }
        )

        if (isLocationAdded) {
            LocationChip(
                modifier = Modifier.padding(top = 10.dp),
                onRemoveLocation = onAddedLocationRemove
            )
        }

        selectedPics?.let {
            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                itemsIndexed(
                    items = it,
                    key = { index, _ -> index }
                ) { index, uri ->
                    SelectedPicture(
                        index = index,
                        uri = uri,
                        onRemove = onAddedPicsRemove
                    )
                }
            }
        }
    }
}