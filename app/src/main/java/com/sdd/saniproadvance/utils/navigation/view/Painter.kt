package com.sdd.saniproadvance.utils.navigation.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.Preview
import com.sdd.saniproadvance.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import kotlin.math.roundToInt

@Preview
@Composable
fun CustomPainterUsage() {
    // [START android_compose_images_custom_painter_usage]
    val rainbowImage = ImageBitmap.imageResource(id = R.drawable.rainbow)
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)
    val customPainter = remember {
        OverlayImagePainter(dogImage, rainbowImage)
    }
    Image(
        painter = customPainter,
        contentDescription = stringResource(id = R.string.app_name),
        contentScale = ContentScale.Crop,
        modifier = Modifier.wrapContentSize()
    )
    // [END android_compose_images_custom_painter_usage]
}

// [START android_compose_images_custom_painter]
class OverlayImagePainter constructor(
    private val image: ImageBitmap,
    private val imageOverlay: ImageBitmap,
    private val srcOffset: IntOffset = IntOffset.Zero,
    private val srcSize: IntSize = IntSize(image.width, image.height),
    private val overlaySize: IntSize = IntSize(imageOverlay.width, imageOverlay.height)
) : Painter() {

    private val size: IntSize = validateSize(srcOffset, srcSize)
    override fun DrawScope.onDraw() {
        // draw the first image without any blend mode
        drawImage(
            image,
            srcOffset,
            srcSize,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            )
        )
        // draw the second image with an Overlay blend mode to blend the two together
        drawImage(
            imageOverlay,
            srcOffset,
            overlaySize,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            ),
            blendMode = BlendMode.Overlay
        )
    }

    /**
     * Return the dimension of the underlying [ImageBitmap] as it's intrinsic width and height
     */
    override val intrinsicSize: Size get() = size.toSize()

    private fun validateSize(srcOffset: IntOffset, srcSize: IntSize): IntSize {
        require(
            srcOffset.x >= 0 &&
                    srcOffset.y >= 0 &&
                    srcSize.width >= 0 &&
                    srcSize.height >= 0 &&
                    srcSize.width <= image.width &&
                    srcSize.height <= image.height
        )
        return srcSize
    }


}
// [END android_compose_images_custom_painter]

@Preview
@Composable
fun CustomPainterModifier() {
    // [START android_compose_custom_painter_modifier]
    val rainbowImage = ImageBitmap.imageResource(id = R.drawable.rainbow)
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)
    val customPainter = remember {
        OverlayImagePainter(dogImage, rainbowImage)
    }
    Box(
        modifier =
        Modifier.background(color = Color.Gray)
            .padding(30.dp)
            .background(color = Color.Yellow)
            .paint(customPainter)
    ) { /** intentionally empty **/ }
    // [END android_compose_custom_painter_modifier]
}