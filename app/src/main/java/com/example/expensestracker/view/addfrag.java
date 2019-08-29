package com.example.expensestracker.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.expensestracker.Database.Databaseclient;
import com.example.expensestracker.Database.Task;
import com.example.expensestracker.R;
import com.example.expensestracker.model.spinneritem;
import com.example.expensestracker.utils.GlobalVarClass;
import com.example.expensestracker.utils.RecyclerTouchListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link addfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addfrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ArrayList<spinneritem> mspinneritem;
    private spinneraddapter mAdapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String[] kinds = {"Transport", "Food", "Drink"};
    private EditText am, de,dedia,amdia;
   // List<Task> GlobalVarClass.tasks;
    private Spinner spin,spindia;
    private RecyclerView rec;
    TextView tx2;
    boolean f= false;
    Task selected;
    double currentday=0;
    Date today=new Date();
    List<Task> todaytasks=new ArrayList<>();
  //  int[] kind_img = {R.drawable.trans, R.drawable.food, R.drawable.drinks};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public addfrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addfrag.
     */
    // TODO: Rename and change types and number of parameters
    public static addfrag newInstance(String param1, String param2) {
        addfrag fragment = new addfrag();
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
        View view = inflater.inflate(R.layout.fragment_addfrag, container, false);
        spin = view.findViewById(R.id.taskkind);
        TextView tx = view.findViewById(R.id.TodayDate);
        tx2 = view.findViewById(R.id.TodayAmount);
        am = view.findViewById(R.id.taskammount);
        de = view.findViewById(R.id.taskdisc);
        rec = view.findViewById(R.id.taskslist);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        new prevab().execute();
        Button btn = view.findViewById(R.id.taskadd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (am.getText().toString().trim().length() == 0 || de.getText().toString().trim().length() == 0) {
                    if (am.getText().toString().trim().length() == 0)
                        am.setError("feild required");
                    if (de.getText().toString().trim().length() == 0)
                        de.setError("feild required");
                } else
                    new datafire().execute();
            }
        });
        mspinneritem = new ArrayList<>();
        mspinneritem.add(new spinneritem("Transport", R.drawable.trans));
        mspinneritem.add(new spinneritem("Food", R.drawable.food));
        mspinneritem.add(new spinneritem("Drink", R.drawable.drinks));
        mspinneritem.add(new spinneritem("Shopping", R.drawable.shopping));
        mspinneritem.add(new spinneritem("Other", R.drawable.other));
        mAdapter = new spinneraddapter(view.getContext(), mspinneritem);
        spin.setAdapter(mAdapter);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MM/dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date today = Calendar.getInstance().getTime();
        tx.setText(dateFormat.format(today));
        rec.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rec, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                selected=todaytasks.get(position);
               final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
               builder.setTitle("Choose an operation");
               builder.setItems(R.array.dialog_list, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                      switch (which){
                       case 0:
                           AlertDialog.Builder builder1 =new AlertDialog.Builder(getContext());
                           builder1.setTitle("update");
                           View v=LayoutInflater.from(getContext()).inflate(R.layout.dialogview,null);
                           amdia=v.findViewById(R.id.taskammountdia);
                           dedia=v.findViewById(R.id.taskdiscdia);
                           spindia =v.findViewById(R.id.taskkinddia);
                           amdia.setText(selected.getAmount()+"");
                           dedia.setText(selected.getDiscrip());
                           mspinneritem = new ArrayList<>();
                           mspinneritem.add(new spinneritem("Transport", R.drawable.trans));
                           mspinneritem.add(new spinneritem("Food", R.drawable.food));
                           mspinneritem.add(new spinneritem("Drink", R.drawable.drinks));
                           mspinneritem.add(new spinneritem("Shopping", R.drawable.shopping));
                           mspinneritem.add(new spinneritem("Other", R.drawable.other));
                           spindia.setAdapter(new spinneraddapter(getContext(),mspinneritem));
                           builder1.setView(v);
                           builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   selected.setDiscrip(dedia.getText().toString());
                                   selected.setAmount(Double.parseDouble(amdia.getText().toString()));
                                   int pos=spindia.getSelectedItemPosition();
                                   selected.setKind(mspinneritem.get(pos).getKind());
                                      new update().execute();
                               }
                           }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {

                               }
                           }).show();
                           break;
                          case 1:
                              new delete().execute();
                              break;
                      }
                   }
               }).show();


            }
        }));


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


    //database ops
    addadapter maddadapter;
    public class datafire extends AsyncTask<Void, Void, Void> {
        Task t;

        @Override
        protected Void doInBackground(Void... voids) {
            t = new Task();
            GlobalVarClass.tasks = new ArrayList<>();
            t.setAmount(Double.parseDouble(am.getText().toString()));
            t.setDiscrip(de.getText().toString());
            int pos = spin.getSelectedItemPosition();
            t.setKind(mspinneritem.get(pos).getKind());
            t.setDate(new Date());
            Databaseclient.getInstance(getContext()).getAppDatabase().taskDAO().insert(t);
            GlobalVarClass.tasks = Databaseclient.getInstance(getContext()).getAppDatabase().taskDAO().getAll();
            Snackbar.make(getView(),de.getText()+" inserted",Snackbar.LENGTH_LONG).show();
            de.setText("");
            am.setText("");


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
todaytasks.clear();
           // GlobalVarClass.dayamount += t.getAmount();
            currentday+=t.getAmount();
            if (GlobalVarClass.tasks.size() != 0) {
                String s= (String) DateFormat.format("dd/MM",today);
                for (int i = 0; i < GlobalVarClass.tasks.size(); i++) {
                    //GlobalVarClass.dayamount += GlobalVarClass.tasks.get(i).getAmount();
                    if(s.equals(DateFormat.format("dd/MM",GlobalVarClass.tasks.get(i).getDate())))
                    { todaytasks.add(GlobalVarClass.tasks.get(i));
                        if(!f)
                        {currentday+=GlobalVarClass.tasks.get(i).getAmount();}
                    }

                }
                f=true;
            }


            tx2.setText(currentday+ " EGP");
             maddadapter = new addadapter(todaytasks);
            rec.setAdapter(maddadapter);
            maddadapter.notifyDataSetChanged();
        }
    }


    public class prevab extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            GlobalVarClass.tasks = Databaseclient.getInstance(getContext()).getAppDatabase().taskDAO().getAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
todaytasks.clear();

            if (GlobalVarClass.tasks.size() != 0) {
                String s= (String) DateFormat.format("dd/MM",today);
                for (int i = 0; i < GlobalVarClass.tasks.size(); i++) {
                       //GlobalVarClass.dayamount += GlobalVarClass.tasks.get(i).getAmount();
                    if(s.equals(DateFormat.format("dd/MM",GlobalVarClass.tasks.get(i).getDate())))
                    { todaytasks.add(GlobalVarClass.tasks.get(i));
                    if(!f)
                    {currentday+=GlobalVarClass.tasks.get(i).getAmount();}
                    }

                   }
                f=true;
            }


                tx2.setText(currentday+ " EGP");
                 maddadapter = new addadapter(todaytasks);
                rec.setAdapter(maddadapter);
                maddadapter.notifyDataSetChanged();

            }
        }
    private class delete extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            currentday-=selected.getAmount();
            Databaseclient.getInstance(getContext()).getAppDatabase().taskDAO().delete(selected);
            GlobalVarClass.tasks=Databaseclient.getInstance(getContext()).getAppDatabase().taskDAO().getAll();
            new prevab().execute();
//            maddadapter.notifyDataSetChanged();
            return null;
        }
    }
    private class update extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {

            Databaseclient.getInstance(getContext()).getAppDatabase().taskDAO().update(selected);
          //  maddadapter.notifyDataSetChanged();
            currentday=0;
            f=false;
            new prevab().execute();
            return null;
        }
    }
    }



