package org.oss.pdfreporter.android;

import java.util.ArrayList;
import java.util.List;

import org.oss.pdfreporter.android.R;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ReportAdapter implements ListAdapter {
	
	private List<String> mReportList;
	private final List<DataSetObserver> mObserverList;
	private final Context mContext;
	private final LayoutInflater inflater;
	
	public ReportAdapter(Context context) {
		mObserverList = new ArrayList<DataSetObserver>();
		mContext = context;
		inflater = LayoutInflater.from(context);
	}

	public void update() {
		for (DataSetObserver obs : mObserverList) {
			obs.onChanged();
		}
	}
	
	public void setReportList(List<String> reportList) {
		mReportList = reportList;
		update();
	}


	@Override
	public int getCount() {
		if(isEmpty()) return 0;
		else return mReportList.size();
	}

	@Override
	public String getItem(int position) {
		return mReportList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public int getItemViewType(int position) {
		return 0;
	}
	
	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null) {
			view = inflater.inflate(R.layout.list_item, parent, false);
		}
		TextView text = (TextView) view.findViewById(android.R.id.text1);
		text.setText(getItem(position));
		return view;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isEmpty() {
		if(mReportList == null || mReportList.size() == 0) return true;
		else return false;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {		
		mObserverList.add(observer);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {	
		mObserverList.remove(observer);
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	@Override
	public boolean isEnabled(int position) {
		return true;
	}

}
