package com.boonex.oo.NewsFeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.boonex.oo.R;
import com.boonex.oo.ViewText;

public class NewsFeedAdapter extends BaseAdapter {
	private Context m_context;
	protected Map<String, Object> m_map;
	protected Object[] m_aMenu;
	protected String m_sUsername;
	protected NewsFeedView m_actNewsFeedView;
	protected List<View> m_listViews;
	
	public NewsFeedAdapter(Context context, Map<String, Object> mapNewsFeedInfo, Object[] aMenu, String username) {
		this.m_context = context;
		this.m_map = mapNewsFeedInfo;
		this.m_aMenu = aMenu;
		this.m_sUsername = username;
		initViews();
	}
	
	protected void initViews() {
		m_listViews = new ArrayList<View>();
		for (int i=0 ; i < getCount() ; ++i)			
			m_listViews.add(i, getView(i, null, null));		
	}
	
	public int getCount() {		 
		return m_aMenu.length + 1;
	}

	public Object getItem(int position) {		
		return "not implemented";
	}

	public long getItemId(int position) {		
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (position >= 0 && position < m_listViews.size())
			return m_listViews.get(position);		
		
		if (0 == position) { // first view is always profile info
			m_actNewsFeedView = new NewsFeedView(m_context, m_map, m_sUsername);
			return m_actNewsFeedView;
		} else { // all other items are customizable
			@SuppressWarnings("unchecked")
			Map<String, String> map = (Map<String, String>)m_aMenu[position-1];
            String sTitle = map.get("title");
            String sBubble = map.get("bubble");
            String s;
            if (!sBubble.equals("") && !sBubble.equals("0"))
            	s = String.format(m_context.getString(R.string.menu_item_format), sTitle, sBubble);
            else
            	s = sTitle;            
            return new ViewText (m_context, s);
		}
	}

	public Map<String, Object> getMap() {
		return m_map;
	}
	
	public Object[] getMenu() {
		return m_aMenu;
	}
	
	public NewsFeedView getProileView() {
		return m_actNewsFeedView;
	}
}
