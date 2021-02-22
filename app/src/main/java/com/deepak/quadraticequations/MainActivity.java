package com.deepak.quadraticequations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
private EditText a,c,b;
private Button calculate;
private TextView display;
double A,B,C,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a=(EditText)findViewById(R.id.etA);
        b=(EditText)findViewById(R.id.etB);
        c=(EditText)findViewById(R.id.etC);
        calculate=(Button)findViewById(R.id.btnCalculate);
        display=(TextView)findViewById(R.id.tvDisplay);
        final GraphView graph=(GraphView)findViewById(R.id.graph);




        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                   // double A=0,B=0,C=0;
                    if((a.getText().toString().isEmpty()==false) && (b.getText().toString().trim().isEmpty()==false) && (c.getText().toString().trim().isEmpty()==false))
                    {
                        A=Double.parseDouble(a.getText().toString().trim());
                        B=Double.parseDouble(b.getText().toString().trim());
                        C=Double.parseDouble(c.getText().toString().trim());
                    }
                    String a="";
                    //double d=0;
                    d=(B*B)- (4*A*C);
                    if(d>=0) {
                        double root1 = (-B + (Math.sqrt(d))) / (2 * A);
                        double root2 = (-B - (Math.sqrt(d))) / (2 * A);
                        a = "The roots of equation are " + root1 + " and " + root2 + "";
                    }
                    else
                    {
                        d=-d;//To make d positive initially
                        String root1=""+(-B/(2*A))+"+"+Math.sqrt(d)+"i";
                        String root2=""+(-B/(2*A))+"-"+Math.sqrt(d)+"i";
                        String root1Type2=""+(-B/(2*A))+"+ square_root("+d+")"+"i";
                        String root2Type2=""+(-B/(2*A))+"- square_root("+d+")"+"i";
                        a="The roots of the equation are "+root1+" and "+root2+"\n\n";
                        a=a+("The roots of the equation are "+root1Type2+"\nand\n "+root2Type2+"");
                        d=-d;//To make d negative again

                    }
                    String minAndMax="";
                    minAndMax=minAndMax+"\n";
                    String function=A+" X^2 + "+B+" X + "+C+"";
                    if(A>0)
                    {
                        double min=(-d)/(4*A);
                        minAndMax+="Minimum value of the function "+function+" is "+min+" and maximum value is infinity\n";
                        minAndMax+="Minimum Value is at X = "+((-B)/(2*A))+"";

                    }
                    else
                    {
                        if(A<0)
                        {   double max=(-d)/(4*A);
                            minAndMax+="Maximum value of the function "+function+" is "+max+" and minimum value is negative infinity\n";
                            minAndMax+="Maximum Value is at X = "+((-B)/(2*A))+"";
                        }
                    }
                    a+="\n";
                    a+=minAndMax;

                    display.setText(a);

                    //Making Graph

                    double x,y;
                    if (d>0)
                    {
                        //String posFactor = "", negFactor = "";
                        double root1 = (-B + (Math.sqrt(d))) / (2 * A);
                        double root2 = (-B - (Math.sqrt(d))) / (2 * A);
                        double dif=Math.abs((root1-root2));
                        double minX=root2-(dif*2);
                        double maxX=root1+(dif*2);

                        double range=Math.abs(maxX-minX);

                        x=minX;
                      double increase=range/1000;

                        //double positiveFactor=Math.pow(10,);
                        series = new LineGraphSeries<DataPoint>();
                        for(int i=0;i<1000;i++)
                        {
                         x=x+(increase);
                         y=fun(x,A,B,C);
                         series.appendData(new DataPoint(x,y) , true,1000);
                        }
                        graph.addSeries(series);

                    }
                    if (d==0)
                    {

                        //String posFactor = "", negFactor = "";
                        double root1 = (-B + (Math.sqrt(d))) / (2 * A);
                        double root2 = (-B - (Math.sqrt(d))) / (2 * A);
                        double dif=Math.abs((root1-root2));
                        double minX=root1-2;
                        double maxX=root2+2;
                        double range=Math.abs(maxX-minX);

                        x=minX;
                        double increase=range/1000;

                        //double positiveFactor=Math.pow(10,);
                        series = new LineGraphSeries<DataPoint>();
                        for(int i=0;i<1000;i++)
                        {
                            x=x+(increase);
                            y=fun(x,A,B,C);
                            series.appendData(new DataPoint(x,y) , true,1000);
                        }
                        graph.addSeries(series);
                    }
                    if(d<0)
                    {
                        //String posFactor = "", negFactor = "";


                        double minX=(-B/(2*A))-1;
                        double maxX=(-B/(2*A))+1;
                        double range=Math.abs(maxX-minX);

                        x=minX;
                        double increase=range/1000;

                        //double positiveFactor=Math.pow(10,);
                        series = new LineGraphSeries<DataPoint>();
                        for(int i=0;i<1000;i++)
                        {
                            x=x+(increase);
                            y=fun(x,A,B,C);
                            series.appendData(new DataPoint(x,y) , true,1000);
                        }
                        graph.addSeries(series);
                    }
                    graph.getViewport().setXAxisBoundsManual(true);
                    graph.getViewport().setYAxisBoundsManual(true);
                    graph.getViewport().setScrollable(true);
                    graph.getViewport().setScrollableY(true);
                    graph.getViewport().setScalable(true);
                    graph.getViewport().setScalableY(true);









                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this,"Some Error Occurred",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    public double fun(double x,double a,double b,double c)
    {
        double y = a * x * x + b * x + c;
        return y;
    }
}