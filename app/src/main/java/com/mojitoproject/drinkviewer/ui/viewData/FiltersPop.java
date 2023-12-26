package com.mojitoproject.drinkviewer.ui.viewData;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupWindow;

import com.mojitoproject.drinkviewer.R;

import java.util.ArrayList;

public class FiltersPop extends PopupWindow {
    Context context;

    String[] listOfAlcohols = new String[]{"wodk%", "gin%", "rum%", "caffe%", "metax%"};
    String[] listOfIngrediences = new String[]{"cuk%", "cytryn%", "limon%", "dżem%"};

    final private int numberOfAlcohols = listOfAlcohols.length;
    final private int numberOfIngrediences = listOfIngrediences.length;
    private CheckBox[] alcoholsAraryCB = new CheckBox[numberOfAlcohols];
    private CheckBox[] ingrediencesArrayCB = new CheckBox[numberOfIngrediences];

    ArrayList<String> alcoholsArary = new ArrayList<String>();
    ArrayList<String> ingrediencesArray = new ArrayList<String>();

    private int dx;
    private int dy;
    private OnSubmitListener mListener;

    private Button okB, clearB;


    public FiltersPop(Context context, OnSubmitListener listener){
        super(context);
        this.context = context;
        mListener = listener;

        setContentView(LayoutInflater.from(context).inflate(R.layout.filters_pop_window, null));
//        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
//        setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        View popupView = getContentView();
        setFocusable(true);

        okB = (Button) popupView.findViewById(R.id.searchB);
        clearB = (Button) popupView.findViewById(R.id.clearB);

        alcoholsAraryCB[0] = (CheckBox) popupView.findViewById(R.id.wodkaCB);
        alcoholsAraryCB[1] = (CheckBox) popupView.findViewById(R.id.ginCB);
        alcoholsAraryCB[2] = (CheckBox) popupView.findViewById(R.id.rumCB);
        alcoholsAraryCB[3] = (CheckBox) popupView.findViewById(R.id.tequilaCB);
        alcoholsAraryCB[4] = (CheckBox) popupView.findViewById(R.id.metaxaCB);

        ingrediencesArrayCB[0] = (CheckBox) popupView.findViewById(R.id.cukierCB);
        ingrediencesArrayCB[1] = (CheckBox) popupView.findViewById(R.id.cytrynyCB);
        ingrediencesArrayCB[2] = (CheckBox) popupView.findViewById(R.id.limonkiCB);
        ingrediencesArrayCB[3] = (CheckBox) popupView.findViewById(R.id.jamCB);

        okB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                alcoholsArary.clear();
                ingrediencesArray.clear();
                for(int i = 0; i < numberOfAlcohols; i++)
                {
                    if(alcoholsAraryCB[i].isChecked())
                        alcoholsArary.add(listOfAlcohols[i]);

                }
                for(int i = 0; i < numberOfIngrediences; i++)
                {
                    if(ingrediencesArrayCB[i].isChecked())
                        ingrediencesArray.add(listOfIngrediences[i]);

                }
                Log.e("ALKO2", String.valueOf(alcoholsArary.size()));
                mListener.valueChanged(alcoholsArary, ingrediencesArray);
                dismiss();
            }
        });
        clearB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < numberOfAlcohols; i++)
                {
                    alcoholsAraryCB[i].setChecked(false);
                }
                for(int i = 0; i < numberOfIngrediences; i++)
                {
                    ingrediencesArrayCB[i].setChecked(false);
                }
//                dismiss();
            }
        });

        // przesuwa okno
        // setOnTouchListener is to add drag and drop the popup window.
        // If you didn't want, you can remove it.
                popupView.setOnTouchListener(new View.OnTouchListener() {

                    public boolean onTouch(View arg0, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()) {

                            case MotionEvent.ACTION_DOWN:
                                dx = (int) motionEvent.getRawX();
                                dy = (int) motionEvent.getRawY();
                                break;

                            case MotionEvent.ACTION_MOVE:
                                int x = (int) motionEvent.getRawX();
                                int y = (int) motionEvent.getRawY();
                                int left = (x - dx);
                                int top = (y - dy);
                                update(left, top, -1, -1);
                                break;
                        }
                        return true;
                    }
                });
    }


    public void show(View v) {
        showAtLocation(v, Gravity.CENTER, 0, 0);
//        Log.e("JAJA", "moje");
    }
    public interface OnSubmitListener {
        void valueChanged(ArrayList<String> alcoholsArary, ArrayList<String> ingrediencesArray);
    }
}
