package android.example.socialx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private Context context;
    private List<Articles> articles;

    public RecyclerViewAdapter(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.articles_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.publishedAt.setText(articles.get(position).getPublishedAt());
        holder.source.setText( articles.get(position).getSource().getName());
        holder.title.setText(articles.get(position).getTitle());
        holder.description.setText(articles.get(position).getDescription());

        if(articles.get(position).getUrlToImg() != null){
           // Picasso.get().load(articles.get(position).getUrlToImg()).into(holder.imageNews);
        }

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView title, publishedAt, description, source;
        ImageView imageNews;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title);
            description = itemView.findViewById(R.id.description);
            publishedAt = itemView.findViewById(R.id.time);
            source = itemView.findViewById(R.id.source);

            imageNews = itemView.findViewById(R.id.img_headline);
        }
    }
}
