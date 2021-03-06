package com.dengit.phrippple.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dengit.phrippple.R;
import com.dengit.phrippple.data.Bucket;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dengit on 15/12/14.
 */
public class BucketsAdapter extends RecyclerViewAdapter<Bucket> {

    @Inject
    public BucketsAdapter() {
        super(new ArrayList<Bucket>());
    }

    @Override
    public long getItemId(int position) {
        return ((Bucket)getItem(position)).id;
    }

    @Override
    protected int getItemLayoutResId() {
        return R.layout.item_bucket;
    }

    @Override
    protected RecyclerView.ViewHolder createViewHolderItem(View itemView) {
        return new BucketVHItem(itemView);
    }

    protected void setupItems(VHItemBase holder, final int position) {
        BucketVHItem itemHolder = (BucketVHItem) holder;
        Bucket bucket = (Bucket) getItem(position);
        itemHolder.bucketName.setText(bucket.name);

        if (bucket.user != null) {
            itemHolder.bucketOwnerPortrait.setImageURI(Uri.parse(bucket.user.avatar_url));
            itemHolder.bucketOwnerName.setText(bucket.user.name);
            itemHolder.bucketOwnerPortrait.setVisibility(View.VISIBLE);
            itemHolder.bucketOwnerName.setVisibility(View.VISIBLE);
        }

        itemHolder.bucketShotCount.setText(bucket.shots_count + " shots");
    }


    class BucketVHItem extends VHItemBase implements View.OnClickListener {
        View bucketItemView;
        @Bind(R.id.bucket_name)
        TextView bucketName;
        @Bind(R.id.bucket_owner_portrait)
        SimpleDraweeView bucketOwnerPortrait;
        @Bind(R.id.bucket_owner_name)
        TextView bucketOwnerName;
        @Bind(R.id.bucket_shot_count)
        TextView bucketShotCount;

        public BucketVHItem(View view) {
            super(view);
            ButterKnife.bind(this, view);
            bucketItemView = view;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
