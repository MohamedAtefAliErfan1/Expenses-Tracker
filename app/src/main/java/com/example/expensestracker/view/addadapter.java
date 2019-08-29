package com.example.expensestracker.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.expensestracker.Database.Databaseclient;
import com.example.expensestracker.Database.Task;
import com.example.expensestracker.R;
import com.example.expensestracker.model.spinneritem;
import com.example.expensestracker.utils.GlobalVarClass;

import java.util.ArrayList;
import java.util.List;

public class addadapter extends RecyclerView.Adapter<addadapter.addviewholder>{
    private List<Task> data = new ArrayList<>();
    private Context context;
    private Task selected;
    private EditText descdia,amountdia;
    private Spinner spinnerdia;
    private ArrayList<spinneritem> mspinneritem;

    public addadapter(List<Task> tasks) {
        this.data = tasks;
    }
    public addadapter(Context c, ArrayList<Task> data) {
        this.data = data;
        this.context=c;
    }

    @Override
    public addadapter.addviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new addadapter.addviewholder(view);
    }

    @Override
    public void onBindViewHolder(final addadapter.addviewholder holder, final int position) {
        holder.kind.setText(data.get(position).getKind());
        holder.desc.setText(data.get(position).getDiscrip());
        holder.amount.setText(data.get(position).getAmount()+" EGP");
        if(data.get(position).getKind().equals("Transport"))
            holder.img_kind.setImageResource(R.drawable.trans);
        else if (data.get(position).getKind().equals("Food"))
            holder.img_kind.setImageResource(R.drawable.food);
        else if(data.get(position).getKind().equals("Shopping"))
            holder.img_kind.setImageResource(R.drawable.shopping);
        else if(data.get(position).getKind().equals("Other"))
            holder.img_kind.setImageResource(R.drawable.other);
        else
            holder.img_kind.setImageResource(R.drawable.drinks);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class addviewholder extends RecyclerView.ViewHolder {
        TextView kind, desc, amount;
        ImageView img_kind;

        public addviewholder(final View itemView) {
            super(itemView);
            kind = itemView.findViewById(R.id.taskitemkindtext);
            desc = itemView.findViewById(R.id.taskitemdesc);
            amount = itemView.findViewById(R.id.taskitemamount);
            img_kind = itemView.findViewById(R.id.taskitemkind);



        }

    }

//    private class delete extends AsyncTask<Void,Void,Void>
//    {
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            Databaseclient.getInstance(context).getAppDatabase().taskDAO().delete(selected);
//            GlobalVarClass.tasks=Databaseclient.getInstance(context).getAppDatabase().taskDAO().getAll();
//            return null;
//        }
//    }
//    private class update extends AsyncTask<Void,Void,Void>
//    {
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            Task t=new Task();
//            t.setDiscrip(descdia.getText().toString());
//            t.setAmount(Double.parseDouble(amountdia.getText().toString()));
//            int pos =spinnerdia.getSelectedItemPosition();
//            t.setKind(mspinneritem.get(pos).getKind());
//            Databaseclient.getInstance(context).getAppDatabase().taskDAO().update(t);
//            return null;
//        }
//    }
//
//
//    // alert inside
//     itemView.setOnLongClickListener(new View.OnLongClickListener() {
//        @Override
//        public boolean onLongClick(View v) {
//            final AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
//            builder.setTitle("Choose an operation")
//                    .setItems(R.array.dialog_list, new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            switch (which) {
//                                case 0:
//
//                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(itemView.getContext());
//                                    View customview =LayoutInflater.from(itemView.getContext()).
//                                            inflate(R.layout.dialogview,null);
//                                    descdia=customview.findViewById(R.id.taskdiscdia);
//                                    amountdia=customview.findViewById(R.id.taskammountdia);
//                                    spinnerdia=customview.findViewById(R.id.taskkinddia);
//                                    descdia.setText(desc.getText().toString());
//                                    amountdia.setText(amount.getText().toString());
//                                    mspinneritem = new ArrayList<>();
//                                    mspinneritem.add(new spinneritem("Transport", R.drawable.trans));
//                                    mspinneritem.add(new spinneritem("Food", R.drawable.food));
//                                    mspinneritem.add(new spinneritem("Drink", R.drawable.drinks));
//                                    mspinneritem.add(new spinneritem("Shopping", R.drawable.shopping));
//                                    mspinneritem.add(new spinneritem("Other", R.drawable.other));
//                                    spinnerdia.setAdapter(new spinneraddapter(itemView.getContext(),mspinneritem));
//                                    builder1.setView(customview).setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            new update().execute();
//                                        }
//                                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//
//                                        }
//                                    }).show();
//
//
//
//                                    break;
//                                case 1:
//                                    new delete().execute();
//                                    break;
//                            }
//                        }
//                    });
//            return true;
//        }
//    });
}


