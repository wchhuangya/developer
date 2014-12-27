/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-8-25 上午11:35:17
 */
package com.wchhuangya.developer.fragment;

import com.wchhuangya.developer.R;
import com.wchhuangya.developer.common.Constants;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * <p>
 * 官方Training里Fragment的例子--显示内容的fragment
 * </p>
 * @company WSuperman
 * @project developer
 * @author wchhuangya
 * @date 2014-8-25 上午11:35:17	
 * @class com.wchhuangya.developer.fragment.ArticleFragment
 *
 */
public class ArticleFragment extends Fragment {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 如果activity被重建（例如屏幕旋转），那么，先前正在浏览的文章的相关设置就会被onSaveInstanceState()方法所保存
		// 这是两个面板布局中很重要的东东
		if(savedInstanceState != null)
			mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
		
		return inflater.inflate(R.layout.article_view, container, false);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		//在启动期间，检查是否有参数传入
		//检查的工作放在onStart方法里做是最合适的，因为这时候，布局已经被应用到fragment了
		//这样，我们就可以安全的调用下面的方法来设置文章的内容了
		Bundle args = getArguments();
		if(args != null){
			updateArticleView(args.getInt(ARG_POSITION));
		} else {
			updateArticleView(mCurrentPosition);
		}
	}
	
	public void updateArticleView(int pos){
		TextView article = (TextView)getActivity().findViewById(R.id.article);
		article.setText(Constants.Articles[pos]);
		mCurrentPosition = pos;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		// 因为我们有可能需要重建fragment，因此我们需要保存当前阅读的文章
        outState.putInt(ARG_POSITION, mCurrentPosition);
	}
}
