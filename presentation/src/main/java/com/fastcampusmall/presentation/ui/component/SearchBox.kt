package com.fastcampusmall.presentation.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.fastcampusmall.presentation.R

@Composable
fun SearchBox(
    keyword: String,
    onValueChange: (String) -> Unit,
    searchAction: () -> Unit,
) {
    Row(Modifier.fillMaxWidth()) {
        TextField(value = keyword,
            onValueChange = onValueChange,
            placeholder = { Text(text = "검색어를 입력해주세요.") },
            shape = RoundedCornerShape(size = 8.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
                autoCorrectEnabled = true
            ),
            keyboardActions = KeyboardActions(
                onSearch = { searchAction() }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            maxLines = 1,
            singleLine = true,
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.icon_search), "SearchIcon") }
        )
    }
}