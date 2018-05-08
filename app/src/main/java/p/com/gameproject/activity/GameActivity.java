package p.com.gameproject.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import p.com.gameproject.R;
import p.com.gameproject.utils.SharePref;


public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    public static int RANVAL_COL = 3;
    public static int RANVAL_ROW = 5;

    /**
     * LinearLayout
     */
    private LinearLayout viewRow1,viewRow2,viewRow3;

    /**
     * Button
     */
    private Button btntvGameActivityTickerNo;

    /**
     * Imageview
     */
    private ImageView ivGameActivityRefresh;

    /**
     * Textview
     */
    private TextView tvGameActivityLogout,tvGameActivityTicket;

    /**
     * Column List
     */
    private List<Integer> ranCol1To9 ;
    private List<Integer> ranCol10To19 ;
    private List<Integer> ranCol20To29 ;
    private List<Integer> ranCol30To39 ;
    private List<Integer> ranCol40To49 ;
    private List<Integer> ranCol50To59 ;
    private List<Integer> ranCol60To69 ;
    private List<Integer> ranCol70To79 ;
    private List<Integer> ranCol80To90 ;

    /**
     * Row list
     */
    private List<Integer> ranRowList1;
    private List<Integer> ranRowList2;
    private List<Integer> ranRowList3;

    /**
     * Result List
     */
    private List<Integer> resultList1,resultList2,resultList3;

    /**
     * SharePreference
     */
    private SharePref sharePref;

    private Boolean isSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setUpView();
        registerClickListener();
        objectInitialization();
        rowResult();
        columnResult();
        generateRowColumnView();
        setTicketValue();
    }

    /**
     * Create Row And Column View
     */
    private void generateRowColumnView() {
        genRowColView1(viewRow1,ranRowList1,0);
        genRowColView1(viewRow2,ranRowList2,1);
        genRowColView1(viewRow3,ranRowList3,2);

    }

    /**
     * Row result
     */
    private void rowResult() {
        ranRowList1 = Randomize(RANVAL_ROW,1,9);
        ranRowList2 = Randomize(RANVAL_ROW,1,9);
        ranRowList3 = Randomize(RANVAL_ROW,1,9);
    }

    /**
     * Column result
     */
    private void columnResult() {
        ranCol1To9 = Randomize(RANVAL_COL,1,9);
        ranCol10To19 = Randomize(RANVAL_COL,10,19);
        ranCol20To29 = Randomize(RANVAL_COL,20,29);
        ranCol30To39 = Randomize(RANVAL_COL,30,39);
        ranCol40To49 = Randomize(RANVAL_COL,40,49);
        ranCol50To59 = Randomize(RANVAL_COL,50,59);
        ranCol60To69 = Randomize(RANVAL_COL,60,69);
        ranCol70To79 = Randomize(RANVAL_COL,70,79);
        ranCol80To90 = Randomize(RANVAL_COL,80,90);
    }

    /**
     * Set up view
     */
    private void setUpView() {
        viewRow1 = (LinearLayout)findViewById(R.id.row_1);
        viewRow2 = (LinearLayout)findViewById(R.id.row_2);
        viewRow3 = (LinearLayout)findViewById(R.id.row_3);
        ivGameActivityRefresh = (ImageView) findViewById(R.id.ivGameActivityRefresh);
        btntvGameActivityTickerNo = (Button) findViewById(R.id.btntvGameActivityTickerNo);
        tvGameActivityLogout = (TextView) findViewById(R.id.tvGameActivityLogout);
        tvGameActivityTicket = (TextView) findViewById(R.id.tvGameActivityTicket);
    }

    /**
     *Initialization Basic object
     */
    private void objectInitialization() {
        sharePref = new SharePref(GameActivity.this);
        sharePref.setUserLogin(true);
        resultList1 = new ArrayList<>();
        resultList2 = new ArrayList<>();
        resultList3 = new ArrayList<>();

    }

    /**
     * Register Click ListListener
     */
    private void registerClickListener() {
        ivGameActivityRefresh.setOnClickListener(this);
        btntvGameActivityTickerNo.setOnClickListener(this);
        tvGameActivityLogout.setOnClickListener(this);
    }


    /**
     * Set Ticket Value
     */
    private void setTicketValue(){

        /**
         * if col 2 and col3 found same change order
         */
        int col1 = getRowTicket1();
        int col2 = getRowTicket2();
        int col3 = getRowTicket3();

        int finalCol2 ;
        int finalCol3 ;

        if(colBig2 == colBig3){

            if(col2 > col3){

            }
        }

        if(isSet) {
            tvGameActivityTicket.setText("Ticket Number : " + getRowTicket1()+ ""+ getRowTicket2() + ""+ getRowTicket3());
        } else {
            tvGameActivityTicket.setText("Ticket Number : " + sharePref.getTicketNumber());
        }

    }


    /**
     *Get Row Ticket 1,2 or 3
     * @return
     */

    int colBig2 = 0;
    int colBig3 = 0;
    private int getRowTicket1(){
        Random random = new Random();
        int number = random.nextInt(2 - 0 + 1) + 0;
        return resultList1.get(number);
    }

    private int getRowTicket2(){
        Random random = new Random();
        int number = random.nextInt(2 - 0 + 1) + 0;
        colBig2 = number;
        return resultList2.get(number);
    }

    private int getRowTicket3(){
        Random random = new Random();
        int number = random.nextInt(2 - 0 + 1) + 0;
        colBig3 = number;
        return resultList3.get(number);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ivGameActivityRefresh){
            sharePref.setTicketNumber("");
            isSet = true;
            setTicketValue();
        }else if(v.getId() == R.id.tvGameActivityLogout) {
            sharePref.setTicketNumber(getRowTicket1()+""+getRowTicket2()+""+getRowTicket3());
            sharePref.setUserLogin(false);
            Intent intent = new Intent(GameActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }

    /**
     *
     * @param viewRow1
     * @param list1
     * @param position
     */
    private void genRowColView1(LinearLayout viewRow1, List<Integer> list1,int position){

        TextView[] txtRow1 = new TextView[9];

        int fix = 1;
        for(int i = 0; i < 9; i++){
            txtRow1[i] = getTextView(txtRow1[i]);
            boolean isFound = false;
            for(Integer integer : list1){

                if(integer == fix){

                    isFound = true;
                }
            }
            if(isFound) {
                txtRow1[i].setBackground(getYellowBackground());
                int result = getColVal(fix,position);
                txtRow1[i].setText(""+result);

                if(position == 0 ){
                    resultList1.add(result);
                }else if(position == 1){
                    resultList2.add(result);
                }else if(position == 2){
                    resultList3.add(result);
                }
            }else {
                txtRow1[i].setBackground(getWhiteBackground());
            }

            viewRow1.addView(txtRow1[i]);
            fix ++;
        }
    }


    /**
     *
     * @return
     */
    private GradientDrawable getYellowBackground(){
        GradientDrawable yellowGD = new GradientDrawable();
        yellowGD.setColor(Color.YELLOW);
        yellowGD.setCornerRadius(1);
        yellowGD.setStroke(1, Color.BLACK);
        return yellowGD;
    }

    /**
     *
     * @return
     */
    private GradientDrawable getWhiteBackground(){
        GradientDrawable whiteGD = new GradientDrawable();
        whiteGD.setColor(Color.WHITE);
        whiteGD.setCornerRadius(1);
        whiteGD.setStroke(1, Color.BLACK);
        return whiteGD;
    }

    /**
     *
     * @param txt
     * @return
     */
    private TextView getTextView(TextView txt){
        LinearLayout.LayoutParams dim =
                new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1);
        txt = new TextView(this);
        txt.setLayoutParams(dim);
        txt.setPadding(10,10,10,10);
        txt.setTextSize(18f);
        txt.setGravity(Gravity.CENTER);
        txt.setTypeface(null, Typeface.BOLD);
        return txt;
    }


    /**
     *
     * @param fix
     * @param position
     * @return
     */
    private int getColVal(int fix,int position){
        int printNumber = 1;
        if(fix==1){
            printNumber = ranCol1To9.get(position);
        }else if(fix==2){
            printNumber = ranCol10To19.get(position);
        }else if(fix==3){
            printNumber = ranCol20To29.get(position);
        }else if(fix==4){
            printNumber = ranCol30To39.get(position);
        }else if(fix==5){
            printNumber = ranCol40To49.get(position);
        }else if(fix==6){
            printNumber = ranCol50To59.get(position);
        }else if(fix==7){
            printNumber = ranCol60To69.get(position);
        }else if(fix==8){
            printNumber = ranCol70To79.get(position);
        }else if(fix==9){
            printNumber = ranCol80To90.get(position);
        }
        return printNumber;
    }


    /**
     *
     * @param min
     * @param max
     * @return
     */
    private static int getRandomeVal(int min,int max){
        return new Random().nextInt(max - min + 1) + min;
    }

    /**
     *
     * @param count
     * @param min
     * @param max
     * @return
     */

    private static List<Integer> Randomize(int count, int min, int max){
        List<Integer> resule = new ArrayList<>();

        Random random = new Random();
        if(count < max - min){
            while (resule.size() < count){
                int number = random.nextInt(max - min + 1) + min;
                if(!resule.contains(number)){
                    resule.add(number);
                }
            }
        }

        return resule;

    }
}


