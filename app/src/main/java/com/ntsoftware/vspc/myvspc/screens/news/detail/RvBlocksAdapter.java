package com.ntsoftware.vspc.myvspc.screens.news.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntsoftware.vspc.myvspc.R;
import com.ntsoftware.vspc.myvspc.screens.news.model.NewsBlock;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RvBlocksAdapter extends RecyclerView.Adapter<RvBlocksAdapter.BlockViewHolder>{

    private List<NewsBlock> blocks;

    public RvBlocksAdapter(List<NewsBlock> blocks) {
        this.blocks = blocks;
    }


    @Override
    public int getItemViewType(int position) {
        return (int) blocks.get(position).getType();
    }

    @NonNull
    @Override
    public BlockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        BlockViewHolder viewHolder = null;
        View holderLayoutView;

        switch (viewType){
            case 1:
                holderLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_block_text,parent,false);
                viewHolder = new TextBlockViewHolder(holderLayoutView);
                break;
            case 2:
                holderLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_block_image,parent,false);
                viewHolder = new ImageBlockViewHolder(holderLayoutView);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BlockViewHolder holder, int position) {
        holder.bind(blocks.get(position));
    }

    @Override
    public int getItemCount() {
        return blocks.size();
    }

    protected abstract class BlockViewHolder extends RecyclerView.ViewHolder {
        public BlockViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        abstract void bind(NewsBlock newsBlock);
    }
    protected class TextBlockViewHolder extends BlockViewHolder {

        NewsBlock block;

        @BindView(R.id.block_text)
        TextView textView;

        public TextBlockViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        void bind(NewsBlock newsBlock) {
            this.block = newsBlock;
            textView.setText(block.getData());
        }
    }

    protected class ImageBlockViewHolder extends BlockViewHolder {

        NewsBlock block;

        @BindView(R.id.block_image)
        ImageView imageView;

        public ImageBlockViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        void bind(NewsBlock newsBlock) {
            this.block = newsBlock;
            Picasso.get()
                    .load(block.getData())
                    .error(R.drawable.accent_grad)
                    .into(imageView);
        }
    }
}
