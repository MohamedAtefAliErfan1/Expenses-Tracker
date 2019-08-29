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
import android.widget.TextView;

import com.example.expensestracker.Database.Databaseclient;
import com.example.expensestracker.Database.Task;
import com.example.expensestracker.R;
import com.example.expensestracker.utils.GlobalVarClass;
import com.example.expensestracker.utils.taskgroup;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link prevfrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link prevfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class prevfrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    TextView prevam,prevdate;
  //  List<Task> tasks;
    private RecyclerView rec;
    List<taskgroup> taskgroups = new ArrayList<>();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public prevfrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment prevfrag.
     */
    // TODO: Rename and change types and number of parameters
    public static prevfrag newInstance(String param1, String param2) {
        prevfrag fragment = new prevfrag();
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
        View view = inflater.inflate(R.layout.fragment_prevfrag, container, false);
        prevam=view.findViewById(R.id.TodayAmountprev);
        prevdate=view.findViewById(R.id.TodayDateprev);
        rec = view.findViewById(R.id.prevtaskslist);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));



        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM, yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date today = Calendar.getInstance().getTime();
        prevdate.setText(dateFormat.format(today));
new prevasync().execute();
        final SwipeRefreshLayout swipeRefreshLayout=view.findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                taskgroups.clear();
                prevam.setText("");
                new prevasync().execute();
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

public class prevasync extends AsyncTask<Void,Void,Void>{
    @Override
    protected Void doInBackground(Void... voids) {
        GlobalVarClass.tasks= Databaseclient.getInstance(getContext()).getAppDatabase().taskDAO().getAll();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
         List<Task> monthtasks=new ArrayList<>();
         String monthcurr= (String) DateFormat.format("MM",new Date());


         double monthamount=0;


        for (int i=0;i<GlobalVarClass.tasks.size();i++)
        {
            if(monthcurr.equals(DateFormat.format("MM",GlobalVarClass.tasks.get(i).getDate())))
            {
                monthtasks.add(GlobalVarClass.tasks.get(i));
            }

        }
        for (int i=0;i<monthtasks.size();i++)
            monthamount+=monthtasks.get(i).getAmount();
        prevam.setText(monthamount+" EGP");

        for(int i=0;i<31;i++)
        {    taskgroup taskgroup;
             List<Task> daytasks=new ArrayList<>();
             double amount=0;
             String datrtopass = "day";
            for(int j=0;j<monthtasks.size();j++)
            {
                String day= (String) DateFormat.format("dd",monthtasks.get(j).getDate());
                String day_f2= (String) DateFormat.format("d",monthtasks.get(j).getDate());
                if(day.equals(i+""))
                {
                    daytasks.add(monthtasks.get(j));
                    amount+=monthtasks.get(j).getAmount();
                    datrtopass= (String) DateFormat.format("EEE, MM/dd",monthtasks.get(j).getDate());
                }
                else if (day_f2.equals(i+""))
                {
                    daytasks.add(monthtasks.get(j));
                    amount+=monthtasks.get(j).getAmount();
                    datrtopass= (String) DateFormat.format("EEE, MM/dd",monthtasks.get(j).getDate());
                }


            }
            if(daytasks.size()!=0)
            {
                taskgroup = new taskgroup(daytasks, datrtopass, amount + " EGP");
                taskgroups.add(taskgroup);
            }
        }

        prevtasksadabter pr=new prevtasksadabter(getContext(),taskgroups);

        rec.setAdapter(pr);
        pr.notifyDataSetChanged();


        // for testing Expand recyclerview
//      Task t1=new Task(12,"Transport","sehfjashas",23,new Date());
//        Task t2=new Task(12,"Shopping","sehfjashas",23,new Date());
//        Task t3=new Task(12,"Transport","srfasdfasdfasd",2333,new Date());
//        double yum=  (t1.getAmount()+t2.getAmount()+t3.getAmount());
//        String s=yum+" EGP";
//        Task t4=new Task(12,"Other","sdfsdf",233,new Date());
//        String z2=t4.getAmount()+" EGP";
//        taskgroup tg1=new taskgroup(Arrays.asList(t1,t2,t3),"today",s);
//        taskgroup tg2=new taskgroup(Arrays.asList(t4),"yesterday",z2);
//        List<taskgroup> r=Arrays.asList(tg1,tg2);
//
//        prevtasksadabter pr=new prevtasksadabter(getContext(),r);
//        rec.setAdapter(pr);



    }
}
}
