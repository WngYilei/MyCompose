package com.xl.mycompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

/**
 * @Author : wyl
 * @Date : 2022/4/20
 * Desc :
 */


@Preview(showBackground = true)
@Composable
fun SkinDataSimple(
    season: String = "春季正常值",
    oil: String = "41%-52%",
    water: String = "45%-55%",
    ela: String = "32%-49%"
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 13.dp, end = 13.dp, top = 10.dp)
            .height(118.dp)
            .background(shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp), color = Color.White)
    ) {
        Text(text = season, modifier = Modifier.padding(start = 19.dp, top = 14.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 18.dp, end = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(text = oil)
            Text(text = water)
            Text(text = ela)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 18.dp, end = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SkinIcon(R.mipmap.ic_circle_oil, "油份")
            SkinIcon(R.mipmap.ic_circle_water, "水份")
            SkinIcon(R.mipmap.ic_circle_elasti, "弹性")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SkinIcon(icon: Int = R.mipmap.ic_circle_oil, tip: String = "油份") {
    ConstraintLayout() {
        val (image, text) = createRefs()
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )
        Text(text = tip, modifier = Modifier
            .constrainAs(text) {
                top.linkTo(image.top)
                bottom.linkTo(image.bottom, margin = 3.dp)
                start.linkTo(image.end, margin = 5.dp)
            }
        )
    }


}
