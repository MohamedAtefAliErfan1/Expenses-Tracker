package com.example.expensestracker.view;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.expensestracker.Database.Databaseclient;
import com.example.expensestracker.Database.Task;
import com.example.expensestracker.R;
import com.example.expensestracker.utils.GlobalVarClass;
import com.example.expensestracker.utils.taskgroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link statweekfrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link statweekfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class statweekfrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView rec;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public statweekfrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment statweekfrag.
     */
    // TODO: Rename and change types and number of parameters
    public static statweekfrag newInstance(String param1, String param2) {
        statweekfrag fragment = new statweekfrag();
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
        View view = inflater.inflate(R.layout.fragment_statweekfrag, container, false);
        rec = view.findViewById(R.id.tasksweek);
        final SwipeRefreshLayout swipeRefreshLayout=view.findViewById(R.id.refreshweek);
        new statweek().execute();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new statweek().execute();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
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

    public class statweek extends AsyncTask<Void, Void, Void> {


        ArrayList<List<Task>> aroundyear = new ArrayList<List<Task>>(12);
        double trans = 0, other = 0, shop = 0, drink = 0, food = 0, general;

        @Override
        protected Void doInBackground(Void... voids) {
            GlobalVarClass.tasks = Databaseclient.getInstance(getContext()).getAppDatabase().taskDAO().getAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for (int i = 0; i < 12; i++) {
                List<Task> monthlisttask = new ArrayList<>();
                for (int j = 0; j < GlobalVarClass.tasks.size(); j++) {
                    String s = (String) DateFormat.format("MM", GlobalVarClass.tasks.get(j).getDate());
                    if (s.equals("0" + (i + 1)))
                        monthlisttask.add(GlobalVarClass.tasks.get(j));
                    else if (s.equals((i + 1) + ""))
                        monthlisttask.add(GlobalVarClass.tasks.get(j));

                }
                aroundyear.add(monthlisttask);
            }
            List<taskgroup> mainlist = new ArrayList<>();
            List<Task> tgr = new ArrayList<>();
            for (int i = 0; i < aroundyear.size(); i++) {

                for (int c = 0; c < aroundyear.get(i).size(); c++)
                    general += aroundyear.get(i).get(c).getAmount();
                String month = "";
                if (aroundyear.get(i).size() != 0) {
                    month = (String) DateFormat.format("MMM", aroundyear.get(i).get(0).getDate());
                    taskgroup t = new taskgroup(tgr, month, general + " EGP");
                    mainlist.add(t);
                    general = 0;
                }

                for (int j = 1; j <= 31; j++) {
                    if (aroundyear.get(i).size() != 0) {
                        for (int k = 0; k < aroundyear.get(i).size(); k++) {

                            String day = (String) DateFormat.format("dd", aroundyear.get(i).get(k).getDate());
                            if (day.equals("0" + j)) {
                                if (aroundyear.get(i).get(k).getKind().equals("Transport"))
                                    trans += aroundyear.get(i).get(k).getAmount();
                                else if (aroundyear.get(i).get(k).getKind().equals("Food") )
                                    food += aroundyear.get(i).get(k).getAmount();
                               else if (aroundyear.get(i).get(k).getKind().equals("Shopping"))
                                    shop += aroundyear.get(i).get(k).getAmount();
                               else if (aroundyear.get(i).get(k).getKind().equals("Other") )
                                    other += aroundyear.get(i).get(k).getAmount();
                                else
                                    drink += aroundyear.get(i).get(k).getAmount();
                            } else if (day.equals(j + "")) {

                                if (aroundyear.get(i).get(k).getKind().equals("Transport"))
                                    trans += aroundyear.get(i).get(k).getAmount();
                                else if (aroundyear.get(i).get(k).getKind().equals("Food") )
                                    food += aroundyear.get(i).get(k).getAmount();
                               else if (aroundyear.get(i).get(k).getKind().equals("Shopping"))
                                    shop += aroundyear.get(i).get(k).getAmount();
                              else if (aroundyear.get(i).get(k).getKind().equals("Other") )
                                    other += aroundyear.get(i).get(k).getAmount();
                                else
                                    drink += aroundyear.get(i).get(k).getAmount();

                            }

                        }

                    }
                    if (j % 7 == 0 && j / 7 <= 3) {
                        if (aroundyear.get(i).size() != 0) {
                            String title = "From " + (j - 6) + "/" + DateFormat.format("MM", aroundyear.get(i).get(0).getDate()) + " To " + j + "/" + DateFormat.format("MM", aroundyear.get(i).get(0).getDate());
                            mainlist.add(new taskgroup(Arrays.asList(new Task(1, "Transport", "total", trans, new Date()),
                                    new Task(2, "Food", "total", food, new Date()), new Task(3, "Shopping", "total", shop, new Date()),
                                    new Task(1, "Other", "total", other, new Date()), new Task(1, "Drink", "total", drink, new Date())), title, (trans + other + drink + food + shop) + " EGP"));
                            trans = 0;
                            other = 0;
                            drink = 0;
                            shop = 0;
                            food = 0;
                        }

                    }

                    else if (j==31) {
                        if (aroundyear.get(i).size() != 0) {
                            String title = "From " + (j - 9) + "/" + DateFormat.format("MM", aroundyear.get(i).get(0).getDate()) + " To " + j + "/" + DateFormat.format("MM", aroundyear.get(i).get(0).getDate());
                            mainlist.add(new taskgroup(Arrays.asList(new Task(1, "Transport", "total", trans, new Date()),
                                    new Task(2, "Food", "total", food, new Date()), new Task(3, "Shopping", "total", shop, new Date()),
                                    new Task(1, "Other", "total", other, new Date()), new Task(1, "Drink", "total", drink, new Date())), title, (trans + other + drink + food + shop) + " EGP"));
                            trans = 0;
                            other = 0;
                            drink = 0;
                            shop = 0;
                            food = 0;
                        }

                    }
                }
            }

            statismonthadapter st = new statismonthadapter(getContext(), mainlist);
            rec.setLayoutManager(new LinearLayoutManager(getContext()));
            rec.setAdapter(st);
        }
    }
}
