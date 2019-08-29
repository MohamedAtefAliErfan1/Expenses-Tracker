package com.example.expensestracker.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.example.expensestracker.Database.Task;
import com.example.expensestracker.R;
import com.example.expensestracker.utils.taskgroup;

import java.util.List;

public class statmonadapter extends ExpandableRecyclerAdapter<taskgroup,Task, statmonadapter.daytasks, statmonadapter.taskschild> {


    /**
     * Primary constructor. Sets up {@link #mParentList} and {@link #mFlatItemList}.
     * <p>
     * Any changes to {@link #mParentList} should be made on the original instance, and notified via
     * {@link #notifyParentInserted(int)}
     * {@link #notifyParentRemoved(int)}
     * {@link #notifyParentChanged(int)}
     * {@link #notifyParentRangeInserted(int, int)}
     * {@link #notifyChildInserted(int, int)}
     * {@link #notifyChildRemoved(int, int)}
     * {@link #notifyChildChanged(int, int)}
     * methods and not the notify methods of RecyclerView.Adapter.
     *
     * @param parentList List of all parents to be displayed in the RecyclerView that this
     *                   adapter is linked to
     */
    LayoutInflater mInflater;
    List<taskgroup> titlelist;
    int pos=0;
    public statmonadapter(Context context, @NonNull List<taskgroup> parentList) {
        super(parentList);
        mInflater = LayoutInflater.from(context);
        titlelist=parentList;
    }



    @NonNull
    @Override
    public daytasks onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View parview=null;
       if(pos%14==0)
            parview = mInflater.inflate(R.layout.title, parentViewGroup, false);
       else
           parview = mInflater.inflate(R.layout.collapsedlayout, parentViewGroup, false);

        return new daytasks(parview);
    }

    @NonNull
    @Override
    public taskschild onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View chiview = mInflater.inflate(R.layout.task_item, childViewGroup, false);
        return new taskschild(chiview);
    }


    @Override
    public void onBindParentViewHolder(@NonNull daytasks parentViewHolder, int parentPosition, @NonNull taskgroup parent) {
        pos++;
     parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull taskschild childViewHolder, int parentPosition, int childPosition, @NonNull Task child) {
      childViewHolder.bind(child);
    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public class daytasks extends ParentViewHolder {

        private TextView day,amount;

        public daytasks(View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.TodayDatecollapsed);
            amount = itemView.findViewById(R.id.TodayAmountcollapsed);
        }

        public void bind(taskgroup taskg) {
            day.setText(taskg.getName());
            amount.setText(taskg.getAmount());
        }
    }




    public class taskschild extends ChildViewHolder {
        TextView kind, desc, amount;
        ImageView img_kind;

        public taskschild (View itemView) {
            super(itemView);
            kind = itemView.findViewById(R.id.taskitemkindtext);
            desc = itemView.findViewById(R.id.taskitemdesc);
            amount = itemView.findViewById(R.id.taskitemamount);
            img_kind = itemView.findViewById(R.id.taskitemkind);
        }

        public void bind(Task task) {
            kind.setText(task.getKind());
            desc.setText(task.getDiscrip());
            amount.setText(task.getAmount()+" EGP");
            if(task.getKind().equals("Transport"))
                img_kind.setImageResource(R.drawable.trans);
            else if (task.getKind().equals("Food"))
                img_kind.setImageResource(R.drawable.food);
            else if(task.getKind().equals("Shopping"))
                img_kind.setImageResource(R.drawable.shopping);
            else if(task.getKind().equals("Other"))
                img_kind.setImageResource(R.drawable.other);
            else
                img_kind.setImageResource(R.drawable.drinks);
        }
    }

}
