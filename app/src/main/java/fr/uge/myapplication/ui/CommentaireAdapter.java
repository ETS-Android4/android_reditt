package fr.uge.myapplication.ui;

import android.content.Context;
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

public class CommentaireAdapter extends RecyclerView.Adapter<CommentaireAdapter.Comholder>{

    Context context;
    List<PC> list = new Vector<>();

    @NonNull
    @Override
    public Comholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listcommentview, parent, false);
        return new Comholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Comholder holder, int position) {
        System.out.println(">>>>I<<<<<");
        holder.author.setText(list.get(position).getAuthor().getName());
        holder.content.setText(list.get(position).getContent());
        holder.date.setText(list.get(position).getDate()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public CommentaireAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<PC> list) {
        this.list = list;
    }

    public void refresh(){
        notifyDataSetChanged();
    }


    public class Comholder extends RecyclerView.ViewHolder{

        TextView content;
        TextView author;
        TextView date;

        public Comholder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.contentcom);
            author = itemView.findViewById(R.id.authorcom);
            date = itemView.findViewById(R.id.datecom);
        }
    }
}
