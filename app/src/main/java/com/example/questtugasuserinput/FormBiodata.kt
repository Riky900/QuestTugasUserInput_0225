package com.example.questtugasuserinput

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
private fun spRes(id: Int): TextUnit =
    with(LocalDensity.current) { dimensionResource(id).toSp() }

private fun formatDate(millis: Long, pattern: String): String {
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("UTC")
    return sdf.format(Date(millis))
}

@Composable
private fun fieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = colorResource(R.color.bio_purple),
    unfocusedBorderColor = colorResource(R.color.bio_field_border),
    focusedTextColor = colorResource(R.color.bio_text_dark),
    unfocusedTextColor = colorResource(R.color.bio_text_dark),
    focusedPlaceholderColor = colorResource(R.color.bio_text_dark),
    unfocusedPlaceholderColor = colorResource(R.color.bio_text_dark),
    cursorColor = colorResource(R.color.bio_purple)
)

@Composable
private fun PagerDots(modifier: Modifier = Modifier) {
    val total = integerResource(R.integer.bio_dot_count)
    val active = integerResource(R.integer.bio_dot_active_index)
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.bio_dot_spacing))
    ) {
        repeat(total) { index ->
            Box(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.bio_dot_size))
                    .clip(CircleShape)
                    .background(
                        colorResource(
                            if (index == active) R.color.bio_dot_active else R.color.bio_dot_inactive
                        )
                    )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormBiodataScreen() {
    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var tglLahir by remember { mutableStateOf("") }
    var pengalaman by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    var showMenu by remember { mutableStateOf(false) }
    var showSummary by remember { mutableStateOf(false) }

    val skills = listOf(R.string.bio_skill_android, R.string.bio_skill_uiux, R.string.bio_skill_web)
    val selectedSkills = remember { mutableStateListOf<Int>() }
    val experienceOptions = stringArrayResource(R.array.bio_experience_options)
    val datePattern = stringResource(R.string.bio_date_pattern)
    val datePickerState = rememberDatePickerState()

    val fieldShape = RoundedCornerShape(dimensionResource(R.dimen.bio_field_radius))
    val formComplete = namaDepan.isNotBlank() && namaBelakang.isNotBlank() &&
            email.isNotBlank() && tglLahir.isNotBlank() &&
            pengalaman.isNotBlank() && selectedSkills.isNotEmpty()

    Scaffold(containerColor = colorResource(R.color.bio_scaffold_bg)) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(R.drawable.bg_neon),
                contentDescription = stringResource(R.string.bio_desc_header_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.bio_header_height))
            )
        }
    }
}
