package v2.liu.bryan.realcalculator;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liu.bryan.realcalculator.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;




public class MainActivity extends AppCompatActivity {

    TextView edit1;
    Button dot1;
    Button divide1, multiply1, plus1, minus1;
    DecimalFormat dec = new DecimalFormat("0.#####E0");
    DecimalFormat smallDec = new DecimalFormat("0.#########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = (TextView) findViewById(R.id.calcDisplay);
        dot1 = (Button) findViewById(R.id.dot);
        divide1 = (Button) findViewById(R.id.divide);
        multiply1 = (Button) findViewById(R.id.multiply);
        plus1 = (Button) findViewById(R.id.plus);
        minus1 = (Button) findViewById(R.id.minus);
        edit1.setText("0");
    }


    BigDecimal answer, firstNum, secondNum;
    BigDecimal percentage;
    int number = 0;
    int wholeNumber;
    boolean divide, multiply, plus, minus;
    boolean percent;
    boolean blank;
    boolean newNum = false;
    boolean equals;
    boolean error;


    private float x1, x2;
    static final int MIN_DISTANCE = 150;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    String deleted;
                    if (edit1 != null && edit1.length() > 0 && blank == false) {
                        deleted = edit1.getText().toString().substring(0, edit1.length() - 1);
                        edit1.setText(deleted);
                        if (edit1.length() == 0) {
                            edit1.setText("0");
                        }
                    } else {
                   //do nothing
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }


    public void reset(View v) {
        edit1.setText(null);
        edit1.setText("0");
        firstNum = new BigDecimal(0);
        secondNum = new BigDecimal(0);
        percentage = new BigDecimal(0);
        dot1.setClickable(true);
        turnAllOpsFalse();
        newNum = false;
        equals = false;
        deselectButton();
    }


    public void zero(View v) {
        setBlank();
        number = 0;
        display(number);
    }

    public void one(View v) {
        setBlank();
        number = 1;
        display(number);
    }

    public void two(View v) {
        setBlank();
        number = 2;
        display(number);
    }

    public void three(View v) {
        setBlank();
        number = 3;
        display(number);
    }

    public void four(View v) {
        setBlank();
        number = 4;
        display(number);
    }

    public void five(View v) {
        setBlank();
        number = 5;
        display(number);
    }

    public void six(View v) {
        setBlank();
        number = 6;
        display(number);
    }

    public void seven(View v) {
        setBlank();
        number = 7;
        display(number);
    }

    public void eight(View v) {
        setBlank();
        number = 8;
        display(number);
    }

    public void nine(View v) {
        setBlank();
        number = 9;
        display(number);
    }


    public void dot(View v) {
        if (edit1.getText() == "Error") {
            edit1.setText("0.");

        } else {
            if (blank == true) {
                edit1.setText("0.");

            } else {
                edit1.setText(edit1.getText() + ".");
                //method for displaying decimal
            }
        }
        dot1.setClickable(false);
        //sets decimal button disabled when it is used once
    }


    public void display(int number) {
        newNum = true;
        if (edit1.getText().length() == 10 && edit1.getText().toString().indexOf(".") >= 0) {
        }
        if (edit1.getText().length() == 9 && edit1.getText().toString().indexOf(".") < 0) {
        } else {
            if (edit1.getText() == "0") {
                edit1.setText(String.valueOf(number));
                //if the display only shows zero, replace zero with number

            } else {
                edit1.setText(edit1.getText() + String.valueOf(number));
                //add number to the charSequence already displayed
                screenSize();
            }
            deselectButton();
        }
    }


    public void divide(View v) {
        if (edit1.getText() == "Error") {
            edit1.setText("Error");
        } else {
            divide1.setActivated(true);
            multiply1.setActivated(false);
            plus1.setActivated(false);
            minus1.setActivated(false);

            newOperator();
            clickedWrongOp();
            mainFunction();

            firstNum = new BigDecimal(edit1.getText().toString());
            divide = true;
            blank = true;
            newNum = false;
            equals = false;
            multiply = false;
            minus = false;
            plus = false;
            dot1.setClickable(true);
        }
    }

    public void multiply(View v) {
        if (edit1.getText() == "Error") {
            edit1.setText("Error");
        } else {
            divide1.setActivated(false);
            multiply1.setActivated(true);
            plus1.setActivated(false);
            minus1.setActivated(false);

            newOperator();
            clickedWrongOp();
            mainFunction();

            firstNum = new BigDecimal(edit1.getText().toString());
            multiply = true;
            blank = true;
            equals = false;
            newNum = false;
            divide = false;
            plus = false;
            minus = false;
            dot1.setClickable(true);
        }
    }

    public void minus(View v) {
        if (edit1.getText() == "Error") {
            edit1.setText("Error");
        } else {
            divide1.setActivated(false);
            multiply1.setActivated(false);
            plus1.setActivated(false);
            minus1.setActivated(true);

            newOperator();
            clickedWrongOp();
            mainFunction();

            firstNum = new BigDecimal(edit1.getText().toString());
            minus = true;
            blank = true;
            equals = false;
            newNum = false;
            multiply = false;
            divide = false;
            plus = false;
            dot1.setClickable(true);
        }
    }

    public void plus(View v) {
        if (edit1.getText() == "Error") {
            edit1.setText("Error");
        } else {
            divide1.setActivated(false);
            multiply1.setActivated(false);
            plus1.setActivated(true);
            minus1.setActivated(false);

            newOperator();
            clickedWrongOp();
            mainFunction();

            firstNum = new BigDecimal(edit1.getText().toString());
            plus = true;
            blank = true;
            equals = false;
            newNum = false;
            multiply = false;
            divide = false;
            minus = false;
            dot1.setClickable(true);
        }
    }


    public void equals(View v) {
        mainFunction();
        equals = true;
        deselectButton();
        dot1.setClickable(true);
    }


    public void plusMinus(View v) {
        if (edit1.getText() == "Error") {
            edit1.setText("0");
        }

        if (Double.parseDouble(edit1.getText().toString()) > 0) {
            edit1.setText("-" + edit1.getText());
            //adds negative sign to front

        } else {
            if (Double.parseDouble(edit1.getText().toString()) < 0) {
                //replaces negative sign with nothing
                edit1.setText(edit1.getText().toString().replace("-", ""));
            }
        }
    }


    public void percent(View v) {
        checkErrorZero();
        if (error == true) {

        } else {
            if (error != true) {
                percentage = new BigDecimal(edit1.getText().toString()).divide(BigDecimal.valueOf(100),
                        100, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();

                if (plus == true || minus == true) {
                    percentage = percentage.multiply(firstNum);
                }
                answer = percentage;
                convertInt();

                percent = true;
                blank = true;
            }
        }
        error = false;
    }

    public void checkErrorZero(){
        if (edit1.getText() == "Error") {
            edit1.setText("Error");
            error=true;
        }
        if (edit1.getText() == "0." || edit1.getText() == "0") {
            edit1.setText("0");
            error=true;
        }
    }


    public void convertInt() {
        if (answer.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0
                && answer.toString().length() <= 9
                && answer.compareTo(BigDecimal.valueOf(2147483647)) == -1
                && answer.compareTo(BigDecimal.valueOf(-2147483647)) == 1) {
            wholeNumber = answer.intValue();
            edit1.setText(String.valueOf(wholeNumber));
        } else {
            numFormat();
        }
        screenSize();
    }


    public void numFormat() {
        if (answer.compareTo(BigDecimal.ZERO) == -1) {
            negativeFormat();

        } else {
            if (answer.compareTo(BigDecimal.ONE) == -1) {
                //less than one
                smallDec.setRoundingMode(RoundingMode.HALF_UP);
                edit1.setText(smallDec.format(answer));
            }
            if (answer.compareTo(BigDecimal.valueOf(0.00000001)) == -1) {
                //less than 1E-8
                edit1.setText((dec.format(answer)));
            }
            if (answer.compareTo(BigDecimal.ONE) == 1) {
                //greater than one
                edit1.setText(answer.toPlainString());
            }
            if (answer.toPlainString().length() > 9 && answer.compareTo(BigDecimal.ONE) == 1) {
                //length larger than 9 and value greater than one
                edit1.setText((dec.format(answer)));
            }
        }
    }

    public void negativeFormat() {
        if (answer.compareTo(BigDecimal.valueOf(-1)) == 1) {
            //more than negative one
            smallDec.setRoundingMode(RoundingMode.HALF_UP);
            edit1.setText(smallDec.format(answer));
        }
        if (answer.compareTo(BigDecimal.valueOf(-0.00000001)) == 1) {
            //more than negative 1E-8
            edit1.setText((dec.format(answer)));
        }
        if (answer.compareTo(BigDecimal.valueOf(-1)) == -1) {

            edit1.setText(answer.toPlainString());
        }
        if (answer.toPlainString().length() > 9 && answer.compareTo(BigDecimal.valueOf(-1)) == -1) {
            //length larger than 11 and greater than one

            edit1.setText((dec.format(answer)));
        }
        screenSize();
    }


    public void mainFunction() {
        if (plus == true) {
            nullSecondNum();
            if (percent == true) {
                answer = firstNum.add(percentage);
                percent = false;
            } else {
                answer = firstNum.add(secondNum);
            }
            convertInt();
        }

        if (divide == true) {
            nullSecondNum();
            if (percent == true) {
                if (edit1.getText() == "0" || percentage.equals(BigDecimal.ZERO)) {
                    edit1.setText("Error");
                } else {
                    answer = firstNum.divide(percentage, 100, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();

                    convertInt();
                }
                percent = false;
            }
            if (secondNum.equals(BigDecimal.ZERO)) {
                edit1.setText("Error");
            } else {
                answer = firstNum.divide(secondNum, 100, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
                convertInt();
            }
        }

        if (multiply == true) {
            nullSecondNum();
            if (percent == true) {
                answer = firstNum.multiply(percentage);
                edit1.setText(String.valueOf(answer));
                percent = false;
            } else {
                answer = firstNum.multiply(secondNum);
            }
            convertInt();
        }

        if (minus == true) {
            nullSecondNum();
            if (percent == true) {
                answer = firstNum.subtract(percentage);
                percent = false;
            } else {
                answer = firstNum.subtract(secondNum);
            }
            convertInt();
        }
        blank = true;
    }

    public void nullSecondNum() {
        if (equals == true) {
            firstNum = answer;
        } else {
            if (edit1.getText() == "") {
                secondNum = firstNum;

            } else {
                secondNum = new BigDecimal(edit1.getText().toString());
            }
        }
    }

    public void setBlank() {
        if (edit1.getText() == "0.") {
            blank = false;

        } else {
            if (blank == true) {
                edit1.setText("");
                blank = false;
                dot1.setClickable(true);
            }
        }
    }


    public void clickedWrongOp() {
        if (equals == false && newNum == false) {
            turnAllOpsFalse();
        }
    }

    public void turnAllOpsFalse() {
        multiply = false;
        divide = false;
        plus = false;
        minus = false;
    }

    public void newOperator() {
        if (equals == true) {
            turnAllOpsFalse();
        }
    }


    public void screenSize() {
        if (edit1.getText().length() > 8) {
            edit1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 60);
        } else {
            edit1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 65);
        }
    }

    public void deselectButton() {
        divide1.setActivated(false);
        multiply1.setActivated(false);
        plus1.setActivated(false);
        minus1.setActivated(false);
    }
}




