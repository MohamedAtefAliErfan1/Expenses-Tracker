package com.example.expensestracker.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.expensestracker.Database.Task;
import com.example.expensestracker.R;
import com.example.expensestracker.model.spinneritem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    private ArrayList<spinneritem> mspinneritem;
    private spinneraddapter mAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
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
       View view= inflater.inflate(R.layout.fragment_blank, container, false);
//        Spinner spin=view.findViewById(R.id.test1);
//        mspinneritem = new ArrayList<>();
//        mspinneritem.add(new spinneritem("Transport", R.drawable.trans));
//        mspinneritem.add(new spinneritem("Food", R.drawable.food));
//        mspinneritem.add(new spinneritem("Drink", R.drawable.drinks));
//        mAdapter=new spinneraddapter(view.getContext(),mspinneritem);
//        spin.setAdapter(mAdapter);

        //       Task taskrey=new Task(12,"Transport","hsfjsdhjf",2334,new Date());
//       List<Task> test=new ArrayList<T>();
//       test.add(taskrey);
//        test.add(taskrey);
//        test.add(taskrey);
        List<Task> tasks=new ArrayList<>();
        tasks.add(new Task(3123,"Transport","sdgasggarg",2331232,new Date()));
        tasks.add(new Task(3123,"Transport","sdgasggarg",2331232,new Date()));
        tasks.add(new Task(3123,"Transport","sdgasggarg",2331232,new Date()));
        tasks.add(new Task(3123,"Transport","sdgasggarg",2331232,new Date()));

//        RecyclerView ry=view.findViewById(R.id.rectest);
//        VerticalAdapter maddadapter=new VerticalAdapter(tasks);
//
//        ry.setLayoutManager(new LinearLayoutManager(getContext()));
//        ry.setAdapter(maddadapter);
//        maddadapter.notifyDataSetChanged();

       return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
