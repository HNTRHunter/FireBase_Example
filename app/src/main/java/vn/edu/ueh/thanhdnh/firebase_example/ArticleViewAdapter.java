package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleViewAdapter extends RecyclerView.Adapter<ArticleViewAdapter.ArticleViewHolder> {
    private Context context;
    private List<Article> articles;

    public ArticleViewAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    public void update(List<Article> newArticles) {
        this.articles = newArticles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.article_item, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.tvTitle.setText(article.getTitle());
        holder.tvContent.setText(article.getContent());

        if (article.getImage_url() != null && !article.getImage_url().isEmpty()) {
            Picasso.get()
                    .load(article.getImage_url())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.imgArticle);
        } else {
            holder.imgArticle.setImageResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    public int getItemCount() {
        return articles != null ? articles.size() : 0;
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvContent;
        public ImageView imgArticle;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.txt_title);
            tvContent = itemView.findViewById(R.id.txt_content);
            imgArticle = itemView.findViewById(R.id.imgArticle);
        }
    }
}
