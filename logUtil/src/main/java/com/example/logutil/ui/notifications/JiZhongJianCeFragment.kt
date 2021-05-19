package com.example.smartcity_1.ui.notifications

import android.graphics.Color
import com.example.smartcity_1.R
import com.example.smartcity_1.ui.BaseFragment
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import kotlinx.android.synthetic.main.jizhongjiancefragment.*
import kotlin.random.Random


class JiZhongJianCeFragment : BaseFragment(R.layout.jizhongjiancefragment) {
    override fun initViews() {
        val datas = listOf(
            PieEntry(60.5f, "已入住"),
            PieEntry(39.5f, "未入住")
        )
        val pieDataSet = PieDataSet(datas, "")
        pieDataSet.setColors(getColors(),getColors())
        pieDataSet.valueTextSize = 12f
        pieDataSet.valueFormatter = PercentFormatter()
        val pieData = PieData(pieDataSet)
        pieChart.data = pieData
        pieChart.description.isEnabled = false
        val legend = pieChart.legend
        pieChart.isDrawHoleEnabled = false
        pieChart.isRotationEnabled = false
        pieChart.setUsePercentValues(true)
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER


        val list1 = mutableListOf<BarEntry>()
        val colors = mutableListOf<Int>()
        repeat(4){
            list1.add(BarEntry(it.toFloat(), Random.nextInt(100).toFloat()))
            colors.add(getColors())
        }
        val ages = arrayOf(
            "60~70",
            "70~80",
            "80~90",
            "90+"
        )
        val barDataSet = BarDataSet(list1, "")
        barDataSet.setColors(colors)
        barDataSet.barBorderWidth = 0.3f
        val barData = BarData(barDataSet)
        barChart1.data = barData
        val xAxis = barChart1.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.valueFormatter = IndexAxisValueFormatter(ages)
        barChart1.axisRight.isEnabled = false
        barChart1.description.isEnabled = false
        barChart1.legend.isEnabled =false

        val list2 = mutableListOf<BarEntry>()
        val colors2 = mutableListOf<Int>()
        for (i in 0..5) {
            colors2.add(getColors())
            list2.add(BarEntry(i.toFloat(),Random.nextInt(1000).toFloat()))
        }
        val lineDataSet = LineDataSet(list2.toList(), "")
        lineDataSet.setColor(getColors())
        lineDataSet.lineWidth = 6f
        lineDataSet.circleRadius = 3f
        lineDataSet.setDrawCircleHole(false)
        lineDataSet.setCircleColors(colors2)
        val lineData = LineData(lineDataSet)
        val days = arrayOf(
            "1日",
            "2日",
            "3日",
            "4日",
            "5日"
        )
        lineChart1.data = lineData
        val xAxis1 = lineChart1.xAxis
        xAxis1.position = XAxis.XAxisPosition.BOTTOM
        xAxis1.granularity = 1f
        xAxis1.valueFormatter = IndexAxisValueFormatter(days)
        lineChart1.axisRight.isEnabled = false
        lineChart1.description.isEnabled = false
        lineChart1.legend.isEnabled = false



        val list3 = mutableListOf<BarEntry>()
        val colors3 = mutableListOf<Int>()

        for (i in 0..5) {
            colors3.add(getColors())
            list3.add(BarEntry(i.toFloat(),Random.nextInt(5).toFloat()))
        }
        val lineDataSet2 = LineDataSet(list3.toList(), "")
        lineDataSet2.setColor(getColors())
        lineDataSet2.lineWidth = 6f
        lineDataSet2.circleRadius = 3f
        lineDataSet2.setDrawCircleHole(false)
        lineDataSet2.setCircleColors(colors3)
        val lineData2 = LineData(lineDataSet2)
        lineChart2.data = lineData2
        val xAxis2 = lineChart2.xAxis
        xAxis2.position = XAxis.XAxisPosition.BOTTOM
        xAxis2.granularity = 1f
        xAxis2.valueFormatter = IndexAxisValueFormatter(days)
        lineChart2.axisRight.isEnabled = false
        lineChart2.description.isEnabled = false
        lineChart2.legend.isEnabled = false

    }
    fun getColors(): Int {
        return Color.rgb(Random.nextInt(256), Random.nextInt(256),Random.nextInt(256))
    }
}