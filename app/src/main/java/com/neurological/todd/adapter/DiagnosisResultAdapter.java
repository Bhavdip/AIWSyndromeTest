package com.neurological.todd.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.neurological.todd.R;
import com.neurological.todd.databinding.ItemTestResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import doctor.neurological.model.PatientsData;

/**
 * Created by bhavdip on 8/21/16.
 */
public class DiagnosisResultAdapter extends RecyclerView.Adapter<DiagnosisResultAdapter.ResultViewHolder> {

    private final static int FADE_DURATION = 1000;

    private List<PatientsData> patientsDataArrayList = new ArrayList<>();

    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    private Context activityContext;

    public DiagnosisResultAdapter(Context context) {
        this.activityContext = context;
    }

    public void addPatientDataSet(List<PatientsData> dataset) {
        if (patientsDataArrayList.size() > 0) {
            patientsDataArrayList.clear();
        }
        patientsDataArrayList.addAll(dataset);
        notifyDataSetChanged();
    }

    public void addPatientItemOnTop(PatientsData patientsData) {
        if (patientsDataArrayList.size() > 0) {
            Collections.reverse(patientsDataArrayList);
            patientsDataArrayList.add(patientsData);
            Collections.reverse(patientsDataArrayList);
            lastPosition = -1;
        } else {
            patientsDataArrayList.add(patientsData);
        }
        notifyItemInserted(0);
    }


    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ResultViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diagnosis_result, parent, false));
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        holder.bindViewItem(position);
    }

    @Override
    public int getItemCount() {
        return patientsDataArrayList.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        private ItemTestResult itemDataBinding;

        public ResultViewHolder(View itemView) {
            super(itemView);
            itemDataBinding = DataBindingUtil.bind(itemView);
        }

        public void bindViewItem(int position) {
            PatientsData patientsData = patientsDataArrayList.get(position);
            if (itemDataBinding != null && patientsData != null) {
                itemDataBinding.textViewFirstName.setText(String.valueOf(patientsData.getPatientName().charAt(0)).toUpperCase());

                itemDataBinding.textViewPatientName.setText(firstCharacterUpperCase(patientsData.getPatientName()));
                itemDataBinding.textViewResultMessage.setText(String.format(Locale.getDefault(),
                        "%d %% of Probability of syndrome detected", patientsData.getSyndromePercentage()));
                setAnimation(itemDataBinding.getRoot(), position);
            }
        }

        /**
         * Here is the key method to apply the animation
         */
        private void setAnimation(View viewToAnimate, int position) {
            // If the bound view wasn't previously displayed on screen, it's animated
            if (position > lastPosition) {

                /*AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(FADE_DURATION);
                viewToAnimate.startAnimation(anim);*/

                setScaleAnimation(viewToAnimate);

                lastPosition = position;
            }
        }

        private void setScaleAnimation(View view) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(FADE_DURATION);
            view.startAnimation(anim);
        }

        private String firstCharacterUpperCase(String patientsData) {
            return patientsData.substring(0, 1).toUpperCase() + patientsData.substring(1).toLowerCase();
        }
    }
}
