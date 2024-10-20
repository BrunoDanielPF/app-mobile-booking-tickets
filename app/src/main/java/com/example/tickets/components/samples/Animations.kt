package com.example.tickets.components.samples

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AnimatedVisibilityCookbook() {
    Box(modifier = Modifier.fillMaxSize()) {
        // [START android_compose_animation_cookbook_visibility]
        val isVisible = remember { mutableStateOf(true) }
        // Animated visibility will eventually remove the item from the composition once the animation has finished.
        AnimatedVisibility(isVisible.value) {
            // your composable here
            // [START_EXCLUDE]
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Green)
            ) {
            }
            // [END_EXCLUDE]
        }
        // [END android_compose_animation_cookbook_visibility]
        Button(modifier = Modifier.align(Alignment.BottomCenter), onClick = {
            isVisible.value = !isVisible.value
        }) {
            Text("Show/Hide")
        }
    }
}