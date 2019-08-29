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

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link statmonthfrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link statmonthfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class statmonthfrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView rec;
    ArrayList<List<Task>> years = new ArrayList<List<Task>>();
    double trans = 0, other = 0, shop = 0, drink = 0, food = 0, general;
    List<taskgroup> mainlist = new ArrayList<>();
    List<Task> tgr = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public statmonthfrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment statmonthfrag.
     */
    // TODO: Rename and change types and number of parameters
    public static statmonthfrag newInstance(String param1, String param2) {
        statmonthfrag fragment = new statmonthfrag();
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
        View view = inflater.inflate(R.layout.fragment_statmonthfrag, container, false);
        rec=view.findViewById(R.id.statrecmonth);
        final SwipeRefreshLayout swipeRefreshLayout=view.findViewById(R.id.refreshmonth);

        new statmonth().execute();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                years.clear();
                mainlist.clear();
                new statmonth().execute();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        // for testing Expand recyclerview
//

        return view ;
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
    public class statmonth extends AsyncTask<Void, Void, Void> {




        @Override
        protected Void doInBackground(Void... voids) {
            GlobalVarClass.tasks = Databaseclient.getInstance(getContext()).getAppDatabase().taskDAO().getAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            int curryear=Integer.parseInt((String) DateFormat.format("yyyy",new Date()));
            for (int i = 2015; i <= curryear; i++) {
                List<Task> yearlisttasks = new ArrayList<>();
                for (int j = 0; j < GlobalVarClass.tasks.size(); j++) {
                    String s = (String) DateFormat.format("yyyy", GlobalVarClass.tasks.get(j).getDate());
                    if (s.equals(i+""))
                        yearlisttasks.add(GlobalVarClass.tasks.get(j));


                }
                years.add(yearlisttasks);
            }

            for (int i = 0; i < years.size(); i++) {

                for (int c = 0; c < years.get(i).size(); c++)
                    general += years.get(i).get(c).getAmount();
                String year = "";
                if (years.get(i).size() != 0) {
                    year = "Year,"+(String) DateFormat.format("yyyy", years.get(i).get(0).getDate());
                    taskgroup t = new taskgroup(tgr, year, general + " EGP");
                    mainlist.add(t);
                    general = 0;
                }

                for (int j = 1; j <= 12; j++) {
                    if (years.get(i).size() != 0) {
                        for (int k = 0; k < years.get(i).size(); k++) {

                            String day = (String) DateFormat.format("MM", years.get(i).get(k).getDate());
                            if (day.equals("0" + j)) {
                                if (years.get(i).get(k).getKind().equals("Transport"))
                                    trans += years.get(i).get(k).getAmount();
                                else if (years.get(i).get(k).getKind().equals("Food") )
                                    food += years.get(i).get(k).getAmount();
                               else if (years.get(i).get(k).getKind().equals("Shopping"))
                                    shop += years.get(i).get(k).getAmount();
                               else if (years.get(i).get(k).getKind().equals("Other") )
                                    other += years.get(i).get(k).getAmount();
                                else
                                    drink += years.get(i).get(k).getAmount();
                            } else if (day.equals(j + "")) {

                                if (years.get(i).get(k).getKind().equals("Transport"))
                                    trans += years.get(i).get(k).getAmount();
                                else if (years.get(i).get(k).getKind().equals("Food") )
                                    food += years.get(i).get(k).getAmount();
                               else if (years.get(i).get(k).getKind().equals("Shopping"))
                                    shop += years.get(i).get(k).getAmount();
                               else if (years.get(i).get(k).getKind().equals("Other") )
                                    other += years.get(i).get(k).getAmount();
                                else
                                    drink += years.get(i).get(k).getAmount();

                            }

                        }

                    }

                        if (years.get(i).size() != 0) {
                           String title= new DateFormatSymbols().getMonths()[j-1];
                            mainlist.add(new taskgroup(Arrays.asList(new Task(1, "Transport", "total", trans, new Date()),
                                    new Task(2, "Food", "total", food, new Date()), new Task(3, "Shopping", "total", shop, new Date()),
                                    new Task(1, "Other", "total", other, new Date()), new Task(1, "Drink", "total", drink, new Date())),
                                    title, (trans + other + drink + food + shop) + " EGP"));
                            trans = 0;
                            other = 0;
                            drink = 0;
                            shop = 0;
                            food = 0;
                        }


                }
            }

            statmonadapter st = new statmonadapter(getContext(), mainlist);
            rec.setLayoutManager(new LinearLayoutManager(getContext()));
            rec.setAdapter(st);
        }
    }

}
 //   List<Task> tgr=new ArrayList<>();
//      Task t1=new Task(12,"Transport","sehfjashas",23,new Date());
//        Task t2=new Task(12,"Shopping","sehfjashas",23,new Date());
//        Task t3=new Task(12,"Transport","srfasdfasdfasd",2333,new Date());
//        double yum=  (t1.getAmount()+t2.getAmount()+t3.getAmount());
//        String s=yum+" EGP";
//        Task t4=new Task(12,"Other","sdfsdf",233,new Date());
//        String z2=t4.getAmount()+" EGP";
//        taskgroup tg1=new taskgroup(tgr,"today",s);
//        taskgroup tg2=new taskgroup(Arrays.asList(t4),"yesterday",z2);
//        taskgroup tg3=new taskgroup(Arrays.asList(t1,t2,t3),"today",s);
//        taskgroup tg4=new taskgroup(Arrays.asList(t4),"yesterday",z2);
//        taskgroup tg5=new taskgroup(Arrays.asList(t1,t2,t3),"today",s);
//        taskgroup tg6=new taskgroup(tgr,"yesterday",z2);
//        List<taskgroup> r=Arrays.asList(tg1,tg2,tg3,tg4,tg5,tg6);
//
//        statismonthadapter pr=new statismonthadapter(getContext(),r);
//        rec.setLayoutManager(new LinearLayoutManager(getContext()));
//        rec.setAdapter(pr);