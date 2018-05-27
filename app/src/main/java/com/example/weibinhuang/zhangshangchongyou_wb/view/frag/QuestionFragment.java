package com.example.weibinhuang.zhangshangchongyou_wb.view.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weibinhuang.zhangshangchongyou_wb.R;
import com.example.weibinhuang.zhangshangchongyou_wb.bean.Question;
import com.example.weibinhuang.zhangshangchongyou_wb.util.MyImageLoader;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.view.IquestionView;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.presenter.IquestionPresenter;
import com.example.weibinhuang.zhangshangchongyou_wb.presenter.QuestionPresenter;
import com.example.weibinhuang.zhangshangchongyou_wb.view.activity.AskQuestionActivity;

import java.util.List;

/**
 * Created by weibinhuang on 18-5-26.
 */

public class QuestionFragment extends Fragment implements IquestionView, View.OnClickListener{

    private RecyclerView mRecyclerView;
    private List<Question> mQuestionList;
    private YouWenRecyclerViewAdapter mYouWenRecyclerViewAdapter;
    private IquestionPresenter mIquestionPresenter;
    private FloatingActionButton mFloatingActionButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        String tag = bundle.getString("TAG");
        mIquestionPresenter = new QuestionPresenter();
        mIquestionPresenter.initQuestionView(this, tag);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.youwen_recyclerview);
        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mYouWenRecyclerViewAdapter = new YouWenRecyclerViewAdapter(mQuestionList);
        mRecyclerView.setAdapter(mYouWenRecyclerViewAdapter);
        return view;
    }

    @Override
    public void showRecyclerview(List<Question> list) {
        mQuestionList = list;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mYouWenRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                Intent intent = new Intent(getActivity(), AskQuestionActivity.class);
                startActivity(intent);
                break;
        }
    }

    class YouWenRecyclerViewAdapter extends RecyclerView.Adapter{

        private YouWenRecyclerViewAdapter(List<Question> questions){
            mQuestionList = questions;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_question_recyclerview, parent, false);
            return new CardViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof CardViewHolder){
                Log.d("onBindViewHolder", mQuestionList.get(position).getReward() + "");
                ((CardViewHolder) holder).question.setText(mQuestionList.get(position).getTitle());
                ((CardViewHolder) holder).questionReward.setText(mQuestionList.get(position).getReward() + "");
                ((CardViewHolder) holder).questionerName.setText(mQuestionList.get(position).getNickname());
                ((CardViewHolder) holder).questionDisappear.setText(mQuestionList.get(position).getDisappear_at());
                ImageView photo = ((CardViewHolder) holder).questionerPhoto;
                String url = mQuestionList.get(position).getPhoto_thumbnail_src();
                MyImageLoader.with(getContext()).load(url).into(photo, photo.getWidth(), photo.getHeight());
            }
        }

        @Override
        public int getItemCount() {
            return mQuestionList == null ? 0: mQuestionList.size();
        }
    }

    private static class CardViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardView;
        private TextView question;
        private ImageView questionerPhoto;
        private TextView questionerName;
        private TextView questionDisappear;
        private TextView questionReward;
        private CardViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.item_cardview);
            question = (TextView) itemView.findViewById(R.id.item_card_text);
            questionerPhoto = (ImageView) itemView.findViewById(R.id.questioner_photo);
            questionerName = (TextView) itemView.findViewById(R.id.questioner_name);
            questionDisappear = (TextView) itemView.findViewById(R.id.question_disappear);
            questionReward = (TextView) itemView.findViewById(R.id.questioner_reward);
        }
    }
}
