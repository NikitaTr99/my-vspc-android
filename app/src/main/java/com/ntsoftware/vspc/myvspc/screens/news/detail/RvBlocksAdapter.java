package com.ntsoftware.vspc.myvspc.screens.news.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntsoftware.vspc.myvspc.R;
import com.ntsoftware.vspc.myvspc.screens.news.model.BlockEntity;
import com.ntsoftware.vspc.myvspc.screens.news.model.NewsBlock;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RvBlocksAdapter extends RecyclerView.Adapter<RvBlocksAdapter.BlockViewHolder>{

    private List<BlockEntity> blocks;

    public RvBlocksAdapter(List<BlockEntity> blocks) {
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

        abstract void bind(BlockEntity newsBlock);
    }
    protected class TextBlockViewHolder extends BlockViewHolder {

        BlockEntity block;
        TextView textView;

        public TextBlockViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.block_text);
        }

        @Override
        void bind(BlockEntity newsBlock) {
            this.block = newsBlock;
            textView.setText(block.getData());
        }
    }

    protected class ImageBlockViewHolder extends BlockViewHolder {

        BlockEntity block;
        ImageView imageView;

        public ImageBlockViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.block_image);
        }

        @Override
        void bind(BlockEntity newsBlock) {
            this.block = newsBlock;
            Picasso.get()
                    .load("https://nikitatr99.fvds.ru/image/" + block.getData())
                    .error(R.drawable.accent_grad)
                    .into(imageView);
        }
    }
}
