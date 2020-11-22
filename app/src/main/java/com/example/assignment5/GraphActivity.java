package com.example.assignment5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        double x,y;
        MainActivity obj=new MainActivity();
        int i=obj.good_list.size();
        int g=i/10;

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        for (int j=0;j<=g;j++)
        {
            int index=10*j;
            y=obj.good_list.get(index);
            x=10*j;
            series.appendData(new DataPoint(x,y),true,500);

        }

        series.setThickness(10);
        series.setDrawDataPoints(true);
        series.setTitle("Good Strategy");
        series.setColor(Color.parseColor("#0000FF"));


        graph.addSeries(series);


        int k=obj.bad_list.size();
        int l=k/10;

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>();
        for (int j=0;j<l;j++)
        {
            int index=10*j;
            double y1=obj.bad_list.get(index);
            double x1=10*j;
            series2.appendData(new DataPoint(x1,y1),true,500);

        }
        series2.setThickness(10);

        series2.setTitle("Bad Strategy");
        series2.setColor(Color.parseColor("#FF0000"));
        graph.addSeries(series2);




    }
}