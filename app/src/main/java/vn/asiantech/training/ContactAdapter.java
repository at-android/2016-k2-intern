package vn.asiantech.training;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Administrator on 22/11/2016.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{
    private ArrayList<Task> mArr = new ArrayList<Task>();
    public ContactAdapter(ArrayList<Task> list) {
        this.mArr = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.contact_item, parent, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ViewHolder holder1 = holder;
        if (mArr.get(position).getInterest()==1) {
            holder1.tv.setText(mArr.get(position).getName() + "\n" + mArr.get(position).getPhoneNumber());
            holder1.img.setImageResource(R.drawable.ic_star_red_600_24dp);
        }
        else{
            holder1.tv.setText(mArr.get(position).getName() + "\n" + mArr.get(position).getPhoneNumber());
            holder1.img.setImageResource(R.drawable.ic_star_red_100_24dp);
        }
        holder1.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mArr.get(position).getInterest()==0){
                    holder1.img.setImageResource(R.drawable.ic_star_red_600_24dp);
                    mArr.get(position).setInterest(1);
                }
                else{
                    holder1.img.setImageResource(R.drawable.ic_star_red_100_24dp);
                    mArr.get(position).setInterest(0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArr.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.tvContact);
            img = (ImageView)itemView.findViewById(R.id.imgViewContact);
        }
    }

}
