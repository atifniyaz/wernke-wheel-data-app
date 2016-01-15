package com.atifniyaz.wernkewheel;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int nval;
    double meanval;
    int sdval;

    TextView n;
    TextView mean;
    TextView sd;
    TextView total;

    int zero;
    int ones;
    int twos;
    int fives;
    int tens;
    int fifties;

    NumberFormat decimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        decimal = new DecimalFormat("#0.00000");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            ObjectInputStream zero = getReadStream("ZERO.WW");
            this.zero = zero.readInt();
            zero.close();

            ObjectInputStream ones = getReadStream("ONE.WW");
            this.ones = ones.readInt();
            ones.close();

            ObjectInputStream twos = getReadStream("TWO.WW");
            this.twos = twos.readInt();
            twos.close();

            ObjectInputStream fives = getReadStream("FIVE.WW");
            this.fives = fives.readInt();
            fives.close();

            ObjectInputStream tens = getReadStream("TEN.WW");
            this.tens = tens.readInt();
            tens.close();

            ObjectInputStream fifties = getReadStream("FIFTY.WW");
            this.fifties = fifties.readInt();
            fifties.close();

            ObjectInputStream nval = getReadStream("N.WW");
            this.nval = nval.readInt();
            nval.close();

            ObjectInputStream meanval = getReadStream("MEAN.WW");
            this.meanval = meanval.readDouble();
            meanval.close();
        } catch(Exception e) {
            e.printStackTrace();

            zero = 0;
            ones = 0;
            twos = 0;
            fives = 0;
            tens = 0;
            fifties = 0;
            zero = 0;

            nval = 0;
            meanval = 0;
            sdval = 0;
        }

        n = (TextView) findViewById(R.id.n);
        n.setText("n = " + nval);
        mean = (TextView) findViewById(R.id.mean);
        mean.setText("mean = " + decimal.format(meanval));
        sd = (TextView) findViewById(R.id.sd);
        sd.setText("sd = " + decimal.format(getSD()));
        total = (TextView) findViewById(R.id.total);
        total.setText("total = $" + decimal.format((nval * 5) - (meanval * nval)));

        Button[] ones = new Button[] {
                (Button) findViewById(R.id.oneLeft),
                (Button) findViewById(R.id.oneRight),
                (Button) findViewById(R.id.oneBottom)
        };

        Button[] fifty = new Button[] {
                (Button) findViewById(R.id.fiftyleft),
                (Button) findViewById(R.id.fiftyright)
        };

        for(Button button : ones) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nval++;
                    MainActivity.this.ones++;
                    n.setText("n = " + nval);
                    meanval = ((meanval * (nval  - 1) + 1.0) / (nval));
                    mean.setText("mean = " + decimal.format(meanval));
                    total.setText("total = $" + decimal.format((nval * 5) - (meanval * nval)));
                    sd.setText("sd = " + decimal.format(getSD()));
                }
            });
        }

        for(Button button : fifty) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nval++;
                    MainActivity.this.fifties++;
                    n.setText("n = " + nval);
                    meanval = ((meanval * (nval  - 1) + 50.0) / nval);
                    mean.setText("mean = " + decimal.format(meanval));
                    total.setText("total = $" + decimal.format((nval * 5) - (meanval * nval)));
                    sd.setText("sd = " + decimal.format(getSD()));
                }
            });
        }

        for(Button button : (new Button[] {
                (Button) findViewById(R.id.tenLeft),
                (Button) findViewById(R.id.tenRight)})) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nval++;
                    MainActivity.this.tens++;
                    n.setText("n = " + nval);
                    meanval = ((meanval * (nval  - 1) + 10.0) / nval);
                    mean.setText("mean = " + decimal.format(meanval));
                    total.setText("total = $" +  decimal.format((nval * 5) - (meanval * nval)));
                    sd.setText("sd = " + decimal.format(getSD()));
                }
            });
        }

        ((Button) findViewById(R.id.zero)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nval++;
                MainActivity.this.zero++;
                n.setText("n = " + nval);
                meanval = ((meanval * (nval  - 1) + 0.0) / nval);
                mean.setText("mean = " + decimal.format(meanval));
                total.setText("total = $" +  decimal.format((nval * 5) - (meanval * nval)));
                sd.setText("sd = " + decimal.format(getSD()));
            }
        });

        for(Button button : (new Button[] {
                (Button) findViewById(R.id.fiveLeft),
                (Button) findViewById(R.id.fiveRight)})) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nval++;
                    MainActivity.this.fives++;
                    n.setText("n = " + nval);
                    meanval = ((meanval * (nval  - 1) + 5.0) / nval);
                    mean.setText("mean = " + decimal.format(meanval));
                    total.setText("total = $" +  decimal.format((nval * 5) - (meanval * nval)));
                    sd.setText("sd = " + decimal.format(getSD()));
                }
            });
        }

        for(Button button : (new Button[] {
                (Button) findViewById(R.id.twoLeft),
                (Button) findViewById(R.id.twoRight)})) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nval++;
                    MainActivity.this.twos++;
                    n.setText("n = " + nval);
                    meanval = ((meanval * (nval  - 1) + 2.0) / nval);
                    mean.setText("mean = " + decimal.format(meanval));
                    total.setText("total = $" +  decimal.format((nval * 5) - (meanval * nval)));
                    sd.setText("sd = " + decimal.format(getSD()));
                }
            });
        }

        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nval = 0;
                meanval = 0;
                sdval = 0;
                zero = 0;
                MainActivity.this.ones = 0;
                twos = 0;
                fives = 0;
                tens = 0;
                fifties = 0;
                zero = 0;
                n.setText("n = " + nval);
                mean.setText("mean = " + meanval);
                sd.setText("sd = " + decimal.format(getSD()));
                total.setText("total = $" + decimal.format(0));
            }
        });

        Button allData = (Button) findViewById(R.id.allData);
        allData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Wernke Wheel Data");
                alertDialog.setMessage("P($0) = " + decimal.format(MainActivity.this.zero / (0.0 + getN())) + "\n" +
                        "P($1) = " + decimal.format(getOnes() / (0.0 + getN())) + "\n" +
                        "P($2) = " + decimal.format(getTwos() / (0.0 + getN())) + "\n" +
                        "P($5) = " + decimal.format(MainActivity.this.fives / (0.0 + getN())) + "\n" +
                        "P($10) = " + decimal.format(MainActivity.this.tens / (0.0 + getN())) + "\n" +
                        "P($50) = " + decimal.format(MainActivity.this.fifties / (0.0 + getN())) + "\n\n\n" +
                        "N($0) = " + MainActivity.this.zero + "\n" +
                        "N($1) = " + getOnes() + "\n" +
                        "N($2) = " + getTwos() + "\n" +
                        "N($5) = " + MainActivity.this.fives + "\n" +
                        "N($10) = " + MainActivity.this.tens + "\n" +
                        "N($50) = " + MainActivity.this.fifties + "\n\n\n" +
                        "Gross Winnings = $" + decimal.format((5 * nval)) + "\n" +
                        "Gross Winnings Per Player = $" + decimal.format(5) + "\n" +
                        "Net Winnings = $" + decimal.format((nval * 5) - (meanval * nval)) + "\n" +
                        "Net Winnings Per Player = $" + decimal.format(((nval * 5) - (meanval * nval))/nval) + "\n\n" +
                        "SD = $" + decimal.format(getSD()));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
    }

    public double getSD() {
        return Math.pow(((zero * Math.pow(meanval - 0, 2) + ones * Math.pow(meanval - 1, 2) + twos * Math.pow(meanval - 2, 2) + fives * Math.pow(meanval - 5, 2)
                + tens * Math.pow(meanval - 10, 2) + fifties * Math.pow(meanval - 50, 2)) / (nval - 1)), .5);
    }

    public int getN() {
        return  nval;
    }

    public int getOnes() {
        return ones;
    }

    public int getTwos() {
        return twos;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public ObjectOutputStream getWriteStream(String filename) throws FileNotFoundException, IOException {
        return new ObjectOutputStream(openFileOutput(filename, Context.MODE_PRIVATE));
    }

    public ObjectInputStream getReadStream(String filename) throws FileNotFoundException, IOException {
        return new ObjectInputStream(openFileInput(filename));
    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            ObjectInputStream ones = getReadStream("ONE.WW");
            this.ones = ones.readInt();
            ones.close();

            ObjectInputStream twos = getReadStream("TWO.WW");
            this.twos = twos.readInt();
            twos.close();

            ObjectInputStream fives = getReadStream("FIVE.WW");
            this.fives = fives.readInt();
            fives.close();

            ObjectInputStream tens = getReadStream("TEN.WW");
            this.tens = tens.readInt();
            tens.close();

            ObjectInputStream zero = getReadStream("ZERO.WW");
            this.zero = zero.readInt();
            zero.close();

            ObjectInputStream fifties = getReadStream("FIFTY.WW");
            this.fifties = fifties.readInt();
            fifties.close();

            ObjectInputStream nval = getReadStream("N.WW");
            this.nval = nval.readInt();
            nval.close();

            ObjectInputStream meanval = getReadStream("MEAN.WW");
            this.meanval = meanval.readDouble();
            meanval.close();
        } catch(Exception e) {
            e.printStackTrace();

            zero = 0;
            ones = 0;
            twos = 0;
            fives = 0;
            tens = 0;
            fifties = 0;

            nval = 0;
            meanval = 0;
            sdval = 0;
        }

        n = (TextView) findViewById(R.id.n);
        n.setText("n = " + nval);
        mean = (TextView) findViewById(R.id.mean);
        mean.setText("mean = " + meanval);
        sd = (TextView) findViewById(R.id.sd);
        sd.setText("sd = N/A");
        total = (TextView) findViewById(R.id.total);
        total.setText("total = $" + decimal.format((nval * 5) - (meanval * nval)));

    }

    public void onPause() {
        super.onPause();

        try {
            ObjectOutputStream ones = getWriteStream("ONE.WW");
            ones.writeInt(this.ones);
            ones.close();

            ObjectOutputStream twos = getWriteStream("TWO.WW");
            twos.writeInt(this.twos);
            twos.close();

            ObjectOutputStream fives = getWriteStream("FIVE.WW");
            fives.writeInt(this.fives);
            fives.close();

            ObjectOutputStream tens = getWriteStream("TEN.WW");
            tens.writeInt(this.tens);
            tens.close();

            ObjectOutputStream fifties = getWriteStream("FIFTY.WW");
            fifties.writeInt(this.fifties);
            fifties.close();

            ObjectOutputStream zero = getWriteStream("ZERO.WW");
            zero.writeInt(this.zero);
            zero.close();

            ObjectOutputStream nval = getWriteStream("N.WW");
            nval.writeInt(this.nval);
            nval.close();

            ObjectOutputStream meanval = getWriteStream("MEAN.WW");
            meanval.writeDouble(this.meanval);
            meanval.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
