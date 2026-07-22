/*
 * Copyright 2026, MOY Launcher contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.lawnchair.moy

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.math.roundToInt

/**
 * The first visual prototype of the MOY home surface.
 *
 * It intentionally sits above, rather than replaces, Launcher3's workspace. That keeps the
 * underlying desktop available while the four-panel interaction is iterated on real devices.
 */
class MoyFourPanelView(
    context: Context,
    private val onDismissed: (MoyFourPanelView) -> Unit,
) : FrameLayout(context) {

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        setPadding(dp(20), dp(24), dp(20), dp(24))
        background = GradientDrawable(
            GradientDrawable.Orientation.TL_BR,
            intArrayOf(Color.rgb(19, 27, 47), Color.rgb(38, 35, 72)),
        ).apply {
            alpha = 244
        }
        isClickable = true
        isFocusable = true

        val content = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
        }
        addView(
            content,
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT),
        )

        content.addView(titleBlock(), LinearLayout.LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT,
        ))

        val panels = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }
        content.addView(
            panels,
            LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1f).apply {
                topMargin = dp(24)
                bottomMargin = dp(18)
            },
        )

        panels.addView(panelRow("常用应用", "把最常用的应用留在触手可及的位置", "01", "应用"), rowParams())
        panels.addView(panelRow("最近使用", "下一步将显示你的最近操作和继续入口", "02", "继续"), rowParams())
        panels.addView(panelRow("闪念", "三指截图后的信息，会在这里被整理", "03", "AI"), rowParams())
        panels.addView(panelRow("收藏", "保存网页、图片、文字和稍后要做的事", "04", "收纳"), rowParams(last = true))

        content.addView(ordinaryDesktopButton(), LinearLayout.LayoutParams(
            LayoutParams.MATCH_PARENT,
            dp(54),
        ))
    }

    private fun titleBlock(): View = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL

        addView(label("MOY", 15f, Color.rgb(153, 196, 255), bold = true).apply {
            letterSpacing = 0.18f
        })
        addView(label("我的四分屏", 30f, Color.WHITE, bold = true).apply {
            setPadding(0, dp(6), 0, 0)
        })
        addView(label("先把手机里最重要的四件事放在眼前", 14f, Color.rgb(201, 207, 226)).apply {
            setPadding(0, dp(8), 0, 0)
        })
    }

    private fun panelRow(title: String, subtitle: String, index: String, action: String): View =
        LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(18), dp(14), dp(16), dp(14))
            background = roundedBackground(Color.argb(212, 255, 255, 255), dp(24))
            elevation = dp(2).toFloat()

            addView(label(index, 13f, Color.rgb(89, 98, 147), bold = true).apply {
                gravity = Gravity.CENTER
                background = roundedBackground(Color.rgb(220, 229, 255), dp(14))
            }, LinearLayout.LayoutParams(dp(48), dp(48)))

            val copy = LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(dp(14), 0, dp(10), 0)
                addView(label(title, 18f, Color.rgb(29, 34, 57), bold = true))
                addView(label(subtitle, 12f, Color.rgb(96, 101, 126)).apply {
                    setPadding(0, dp(4), 0, 0)
                    maxLines = 1
                })
            }
            addView(copy, LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f))

            addView(label(action, 12f, Color.rgb(67, 84, 177), bold = true).apply {
                gravity = Gravity.CENTER
                background = roundedBackground(Color.rgb(230, 235, 255), dp(14))
                setPadding(dp(12), 0, dp(12), 0)
            }, LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, dp(34)))
        }

    private fun ordinaryDesktopButton(): View = label("进入普通桌面", 16f, Color.WHITE, bold = true).apply {
        gravity = Gravity.CENTER
        background = roundedBackground(Color.argb(55, 255, 255, 255), dp(18))
        setOnClickListener { dismiss() }
    }

    private fun rowParams(last: Boolean = false) = LinearLayout.LayoutParams(
        LayoutParams.MATCH_PARENT,
        0,
        1f,
    ).apply {
        if (!last) bottomMargin = dp(12)
    }

    private fun label(text: String, size: Float, color: Int, bold: Boolean = false) = TextView(context).apply {
        this.text = text
        textSize = size
        setTextColor(color)
        includeFontPadding = false
        if (bold) typeface = android.graphics.Typeface.DEFAULT_BOLD
    }

    private fun roundedBackground(color: Int, radius: Int) = GradientDrawable().apply {
        setColor(color)
        cornerRadius = radius.toFloat()
    }

    private fun dismiss() {
        animate()
            .alpha(0f)
            .translationY(dp(20).toFloat())
            .setDuration(180)
            .withEndAction { onDismissed(this) }
            .start()
    }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).roundToInt()
}
