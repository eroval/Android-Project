package com.example.f95108_calisthenics_app;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BodyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BodyFragment extends Fragment {
    private static final String ARG_HEIGHT = "heightparam";
    private static final String ARG_WEIGHT = "weightparam";

    private Activity activityContext;
    private double heightValue;
    private double weightValue;

    private TextInputEditText height;
    private TextInputEditText weight;
    private TextView bmi;

    private Button btnSave;

    private DatabaseHelper dbController;


    public BodyFragment() {
        // Required empty public constructor
    }

    public BodyFragment(Activity mainActivity){
        activityContext = mainActivity;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param height Parameter 1.
     * @param weight Parameter 2.
     * @return A new instance of fragment BodyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BodyFragment newInstance(int height,
                                           int weight) {
        BodyFragment fragment = new BodyFragment();
        Bundle args = new Bundle();
        args.putDouble(ARG_HEIGHT, height);
        args.putDouble(ARG_WEIGHT, weight);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            heightValue = Double.valueOf(getArguments().getString(ARG_HEIGHT));
            weightValue = Double.valueOf(getArguments().getString(ARG_WEIGHT));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_body, container, false);
        height = view.findViewById(R.id.height);
        weight = view.findViewById(R.id.weight);
        bmi = view.findViewById(R.id.bmivalue);
        btnSave = view.findViewById(R.id.saveBtn);
        dbController = new DatabaseHelper(activityContext);

        loadSavedValues();

        height.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    setHeight();
                    if(weightValue!=0){
                        bmi.setText(String.format("%,.2f", calculateBmi()) + "%");
                    }
                }
                catch (Exception e){
                    Toast.makeText(activityContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    bmi.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    setWeight();
                    if(heightValue!=0){
                        bmi.setText(String.format("%,.2f", calculateBmi()) + "%");
                    }
                }
                catch (Exception e) {
                    Toast.makeText(activityContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    bmi.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryUpdating();
            }
        });

        return view;
    }

//    private void checkValues() throws Exception{
//        if (heightValue <=0 || heightValue >=600){ //|| height.length() == 0){
//            throw new Exception("Invalid value for height.");
//        }
//        if (weightValue <= 0 || weightValue >= 600){ // || weight.length() == 0) {
//            throw new Exception("Invalid value for weight.");
//        }
//    }

    private void loadSavedValues(){
        UserModel user = dbController.getUser();
        if (user!=null){
            try {
                height.setText(user.getHeight().toString());
                weight.setText(user.getWeight().toString());
                setHeight();
                setWeight();
                bmi.setText(String.format("%,.2f", calculateBmi()) + "%");
            }
            catch (Exception e){
                Toast.makeText(activityContext, "Couldn't load user data", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void tryUpdating(){
        if(dbController.insertOrUpdateUserTable(heightValue, weightValue)){
            Toast.makeText(activityContext, "Updated", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(activityContext, "Update failed", Toast.LENGTH_SHORT).show();
    }

    private void setHeight() throws  Exception{
        if (height.length()==0){
            heightValue = 0;
            throw new Exception("Height must not be empty.");
        }
        double tmpValue = Double.valueOf(height.getText().toString());
        if (tmpValue <=0 || tmpValue >=600){
            throw new Exception("Invalid value for height.");
        }
        heightValue = tmpValue;
    }

    private void setWeight() throws  Exception{
        if (weight.length()==0){
            weightValue=0;
            throw new Exception("Weight must not be empty.");
        }
        double tmpValue = Double.valueOf(weight.getText().toString());
        if (tmpValue <=0 || tmpValue >=600){
            throw new Exception("Invalid value for weight.");
        }
        weightValue = tmpValue;
    }

    private double calculateBmi() throws Exception{
        return weightValue / ((heightValue / 100) * (heightValue / 100));
    }
}