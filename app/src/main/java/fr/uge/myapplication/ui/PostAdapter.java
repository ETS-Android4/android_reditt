package fr.uge.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Vector;

import fr.uge.myapplication.R;
import fr.uge.myapplication.model.PC;
import fr.uge.myapplication.ui.profile.RecyclerViewAdapter;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.postholder> {

    ItemClickListener itemClickListener;
    Context context;
    List<PC> list = new Vector<>();

    public PostAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<PC> list) {
        this.list = list;
    }

    public void refresh(){
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public postholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postitems, parent, false);
        return new postholder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull postholder holder, int position) {
        holder.author.setText(list.get(position).getAuthor().getName());
        holder.title.setText(list.get(position).getTitle());
        holder.date.setText(list.get(position).getDate()+"");
        holder.itemView.setOnClickListener(v->{
            System.out.println(list.get(position).getId() + "wtf");
            Intent intent = new Intent(context,Postdetail.class);
            intent.putExtra("id",list.get(position).getId());
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public interface  ItemClickListener {
        void onItemClick(int pos);
    }


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class postholder extends RecyclerView.ViewHolder{

        TextView title;
        TextView author;
        TextView date;

        public postholder(@NonNull View itemView,ItemClickListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.titlePub);
            author = itemView.findViewById(R.id.author);
            date = itemView.findViewById(R.id.date);
        }
    }
}
