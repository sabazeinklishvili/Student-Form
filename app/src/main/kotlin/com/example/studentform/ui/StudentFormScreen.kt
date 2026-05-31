package com.example.studentform.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.studentform.R
import com.example.studentform.ui.theme.StudentFormColors
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val favoriteDirectionOptions = listOf("Android", "iOS", "Web")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentFormScreen() {
    val context = LocalContext.current

    var nameState by remember { mutableStateOf("") }
    var surnameState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var dateState by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var isAgreed by remember { mutableStateOf(false) }

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    val scroll = rememberScrollState()
    val fieldShape = RoundedCornerShape(18.dp)
    val cardShape = RoundedCornerShape(28.dp)

    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = StudentFormColors.TextOnDark,
        unfocusedTextColor = StudentFormColors.TextOnDark,
        disabledTextColor = StudentFormColors.TextOnDark,
        focusedBorderColor = StudentFormColors.CoralAccent,
        unfocusedBorderColor = StudentFormColors.FieldBorder,
        disabledBorderColor = StudentFormColors.FieldBorder,
        focusedLabelColor = StudentFormColors.CoralBright,
        unfocusedLabelColor = StudentFormColors.TextMuted,
        disabledLabelColor = StudentFormColors.TextMuted,
        cursorColor = StudentFormColors.MintGlow,
        focusedContainerColor = StudentFormColors.NavyCard,
        unfocusedContainerColor = StudentFormColors.NavyCard,
        disabledContainerColor = StudentFormColors.NavyCard
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        StudentFormColors.NavyDeep,
                        Color(0xFF162238),
                        StudentFormColors.NavyDeep
                    )
                )
            )
            .verticalScroll(scroll)
            .padding(horizontal = 22.dp, vertical = 28.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineLarge,
            color = StudentFormColors.CoralBright
        )
        Text(
            text = stringResource(R.string.form_subtitle_ka),
            style = MaterialTheme.typography.bodyLarge,
            color = StudentFormColors.TextMuted
        )

        Spacer(modifier = Modifier.height(4.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(cardShape)
                .background(StudentFormColors.NavyCard)
                .border(1.dp, StudentFormColors.FieldBorder, cardShape)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = nameState,
                onValueChange = { nameState = it },
                label = { Text(stringResource(R.string.label_name_ka)) },
                modifier = Modifier.fillMaxWidth(),
                shape = fieldShape,
                colors = fieldColors,
                singleLine = true
            )

            OutlinedTextField(
                value = surnameState,
                onValueChange = { surnameState = it },
                label = { Text(stringResource(R.string.label_surname_ka)) },
                modifier = Modifier.fillMaxWidth(),
                shape = fieldShape,
                colors = fieldColors,
                singleLine = true
            )

            OutlinedTextField(
                value = emailState,
                onValueChange = { emailState = it },
                label = { Text(stringResource(R.string.label_email_ka)) },
                modifier = Modifier.fillMaxWidth(),
                shape = fieldShape,
                colors = fieldColors,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showDatePicker = true }
            ) {
                OutlinedTextField(
                    value = dateState,
                    onValueChange = {},
                    readOnly = true,
                    enabled = false,
                    label = { Text(stringResource(R.string.label_date_ka)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = fieldShape,
                    colors = fieldColors,
                    singleLine = true
                )
            }

            Text(
                text = stringResource(R.string.label_direction_ka),
                style = MaterialTheme.typography.titleMedium,
                color = StudentFormColors.MintGlow,
                modifier = Modifier.padding(top = 8.dp)
            )

            favoriteDirectionOptions.forEach { option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .clickable { selectedOption = option }
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedOption == option,
                        onClick = { selectedOption = option },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = StudentFormColors.CoralAccent,
                            unselectedColor = StudentFormColors.TextMuted
                        )
                    )
                    Text(text = option, color = StudentFormColors.TextOnDark)
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.label_terms_ka),
                    color = StudentFormColors.TextOnDark,
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = isAgreed,
                    onCheckedChange = { isAgreed = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = StudentFormColors.NavyDeep,
                        checkedTrackColor = StudentFormColors.MintGlow,
                        uncheckedThumbColor = StudentFormColors.TextMuted,
                        uncheckedTrackColor = StudentFormColors.FieldBorder
                    )
                )
            }
        }

        Button(
            onClick = {
                if (!isFormValid(nameState, surnameState, emailState, dateState, selectedOption, isAgreed)) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.toast_error_ka),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.toast_success_ka),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = StudentFormColors.CoralAccent,
                contentColor = StudentFormColors.NavyDeep
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
        ) {
            Text(text = stringResource(R.string.btn_submit), style = MaterialTheme.typography.titleMedium)
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            dateState = formatBirthDateDdMmYyyy(millis)
                        }
                        showDatePicker = false
                    }
                ) {
                    Text(stringResource(R.string.date_picker_ok), color = StudentFormColors.CoralAccent)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text(stringResource(R.string.date_picker_cancel_ka), color = StudentFormColors.TextMuted)
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

/** Submit validation: all fields, radio choice, and terms switch. */
private fun isFormValid(
    nameState: String,
    surnameState: String,
    emailState: String,
    dateState: String,
    selectedOption: String?,
    isAgreed: Boolean
): Boolean {
    return nameState.isNotBlank() &&
        surnameState.isNotBlank() &&
        emailState.isNotBlank() &&
        dateState.isNotBlank() &&
        selectedOption != null &&
        isAgreed
}

private fun formatBirthDateDdMmYyyy(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}
