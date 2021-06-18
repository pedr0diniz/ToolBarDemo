package com.zinid.toolbardemo;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RespirarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RespirarFragment extends Fragment {

    Button goBtn;
    CardView cardView;
    TextView instructionView, timerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RespirarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Respirar.
     */
    // TODO: Rename and change types and number of parameters
    public static RespirarFragment newInstance(String param1, String param2) {
        RespirarFragment fragment = new RespirarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_respirar, container, false);
        goBtn = view.findViewById(R.id.btn_go);
        cardView = view.findViewById(R.id.card);
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Inspire pelo nariz", Toast.LENGTH_SHORT).show();
                circularRevealCard(cardView, goBtn);
            }
        });
        return view;
    }

    private void circularRevealCard(View view, Button button) {
        float finalRadius = Math.max(view.getWidth(), view.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                view, ((int) view.getPivotX()), ((int) button.getY()), 0, finalRadius * 1f);
        circularReveal.setDuration(4000);

        // make the view visible and start the animation
        view.setVisibility(View.VISIBLE);

        circularReveal.start();
        circularReveal.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                holdBreath(view, button);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void holdBreath(View view, Button button) {
        view.setVisibility(View.VISIBLE);

        final int from = ContextCompat.getColor(getActivity(), R.color.teal_200);
        final int to = ContextCompat.getColor(getActivity(), R.color.purple_200);

        ValueAnimator anim = new ValueAnimator();
        anim.setIntValues(from, to);
        anim.setEvaluator(new ArgbEvaluator());
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Toast.makeText(getContext(), "Segure a respiração", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                circularConcealCard(view, button);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setBackgroundColor((int) animation.getAnimatedValue());
            }
        });

        anim.setDuration(2000);
        anim.start();
    }

    private void circularConcealCard(View view, Button button) {
        float finalRadius = Math.max(view.getWidth(), view.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator circularConceal = ViewAnimationUtils.createCircularReveal(
                view, ((int) view.getPivotX()), ((int) button.getY()), finalRadius * 1f, 0);
        circularConceal.setDuration(6000);

        circularConceal.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Toast toast = Toast.makeText(getContext(), "Expire pela boca", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setBackgroundColor(getResources().getColor(R.color.teal_200));
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        // make the view visible and start the animation
        view.setVisibility(View.VISIBLE);

        circularConceal.start();
    }
}
