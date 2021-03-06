package com.dengit.phrippple.ui.base;

import android.text.TextUtils;

import com.dengit.phrippple.api.DribbbleAPI;
import com.dengit.phrippple.api.DribbbleAPIHelper;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by dengit on 15/12/16.
 */
public abstract class FetchBaseModelImpl<T> implements FetchBaseModel<T> {

    protected int mCurrPage = 0;
    protected DribbbleAPI mDribbbleAPI;
    protected String mAccessToken;
    private FetchBasePresenter<T> mFetchBasePresenter;
    protected CompositeSubscription mSubscriptions;

    public FetchBaseModelImpl(FetchBasePresenter<T> fetchBasePresenter) {
        mFetchBasePresenter = fetchBasePresenter;
        mDribbbleAPI = DribbbleAPIHelper.getInstance().getDribbbleAPI();
        mAccessToken = DribbbleAPIHelper.getInstance().getAccessToken();
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void onDetach() {
        mSubscriptions.unsubscribe();
    }

    @Override
    public void loadMore() {
        fetchItems(mCurrPage + 1);
    }

    @Override
    public boolean checkIfCanRefresh() {
        return !TextUtils.isEmpty(mAccessToken);
    }

    @Override
    public void loadNewest() {
        fetchItems(1);
    }

    protected abstract void fetchItems(final int page);

}

