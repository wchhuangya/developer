/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-8-23 下午12:50:13
 */
package com.wchhuangya.developer.fragment;

import com.wchhuangya.developer.R;
import com.wchhuangya.developer.fragment.HeadlinesFragment.OnHeadlineSelectedListener;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

/**
 * <p>
 * 官方Training里Fragment的例子
 * </p>
 * @company WSuperman
 * @project developer
 * @author wchhuangya
 * @date 2014-8-23 下午12:50:13	
 * @class com.wchhuangya.developer.fragment.TrainingFragment
 *
 */
public class TrainingFragment extends FragmentActivity implements OnHeadlineSelectedListener {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.new_articles);
		
		//如果当前使用的是layout文件夹里的布局而不是layout-large文件夹里的布局
		//应该动态的把显示标题的fragment动态加载到activity上
		if(findViewById(R.id.fragment_container) != null){
			
			//但是，如果是从前一个视图恢复到这里的
			//那么我们应该啥都不做，只是返回或者是结束现在正在显示的fragment
			if(arg0 != null)
				return;
			
			//创建HeadlinesFragment的实例
			HeadlinesFragment headFragment = new HeadlinesFragment();
			
			//如果activity是使用带有特殊命令的intent启动的，那么把该参数传递给fragment
			headFragment.setArguments(getIntent().getExtras());
			
			//把HeadlinesFragment的实例添加到id为fragment_container的布局上
			getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, headFragment).commit();
		}
	}

	@Override
	public void onArticleSelected(int position) {
		//当用户在HeadlinesFragment里选择了某个文章时，得从布局文件里获取内容的fragment
		ArticleFragment articleFragment = (ArticleFragment)getSupportFragmentManager()
				.findFragmentById(R.id.article_fragment);
		
		if(articleFragment != null){
			//如果fragment是可见的，也就是说现在处于双面板的状态
			//那就可以调用ArticleFragment里的方法来更新显示的内容了
			articleFragment.updateArticleView(position);
		} else {
			//如果fragment不可用，那么现在处于单面板的状态，必须得切换fragment了
			//因此我们动态的创建一个fragment并给它传入一个参数来指定显示哪个文章
			ArticleFragment af = new ArticleFragment();
			Bundle bd = new Bundle();
			bd.putInt(ArticleFragment.ARG_POSITION, position);
			af.setArguments(bd);
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			
			//不论当前fragment容器里放的是什么，都会被替换掉
			//并且会把这次转换放入后退堆栈中，这样，用户就可以通过点击后退键回到前一状态
			ft.replace(R.id.fragment_container, af);
			ft.addToBackStack(null);
			
			//提交事务
			ft.commit();
		}
	}
}
