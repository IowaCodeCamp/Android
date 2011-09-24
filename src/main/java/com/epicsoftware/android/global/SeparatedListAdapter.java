package com.epicsoftware.android.global;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import com.epicsoftware.android.R;
import com.epicsoftware.entity.Session;
import com.epicsoftware.entity.SpecialSessionIdentifier;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SeparatedListAdapter extends BaseAdapter {
    public final Map<String, List<Session>> attachedSessionsMap = new LinkedHashMap<String, List<Session>>();
    public final Map<String, Adapter> sections = new LinkedHashMap<String, Adapter>();
    public final ArrayAdapter<String> headers;
    public final static int TYPE_SECTION_HEADER = 0;
    private SpecialSessionIdentifier identifier;

    public SeparatedListAdapter(Context context) {
        headers = new ArrayAdapter<String>(context, R.layout.list_header);
        identifier = new SpecialSessionIdentifier();
    }

    public void addSection(String section, Adapter adapter, List<Session> attachedSessions) {
        this.headers.add(section);
        this.sections.put(section, adapter);
        this.attachedSessionsMap.put(section, attachedSessions);
    }

    public Object getItem(int position) {
        for (Object section : this.sections.keySet()) {
            Adapter adapter = sections.get(section);
            int size = adapter.getCount() + 1;

            // check if position inside this section
            if (position == 0) {
                return getSessionFromMapGivenPosition(position, section);
            }
            if (position < size) {
                return getSessionFromMapGivenPosition(position - 1, section);
            }

            // otherwise jump into next section
            position -= size;
        }
        return null;
    }

    private Object getSessionFromMapGivenPosition(int position, Object section) {
        List<Session> sessions = attachedSessionsMap.get(section);
        return sessions.get(position);
    }

    public int getCount() {
        // total together all sections, plus one for each section header
        int total = 0;
        for (Adapter adapter : this.sections.values())
            total += adapter.getCount() + 1;
        return total;
    }

    public int getViewTypeCount() {
        // assume that headers count as one, then total all sections
        int total = 1;
        for (Adapter adapter : this.sections.values())
            total += adapter.getViewTypeCount();
        return total;
    }

    public int getItemViewType(int position) {
        int type = 1;
        for (Object section : this.sections.keySet()) {
            Adapter adapter = sections.get(section);
            int size = adapter.getCount() + 1;

            // check if position inside this section
            if (position == 0) return TYPE_SECTION_HEADER;
            if (position < size) return type + adapter.getItemViewType(position - 1);

            // otherwise jump into next section
            position -= size;
            type += adapter.getViewTypeCount();
        }
        return -1;
    }

    public boolean areAllItemsSelectable() {
        return false;
    }

    public boolean isEnabled(int position) {
        return (getItemViewType(position) != TYPE_SECTION_HEADER);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int sectionnum = 0;
        //((LinkedHashMap) this.sections).values.toArray()[1]
        for (Object section : this.sections.keySet()) {
            Adapter adapter = sections.get(section);
            int size = adapter.getCount() + 1;

            // check if position inside this section
            if (position == 0) return headers.getView(sectionnum, convertView, parent);
            if (position < size) {
                View itemInAdapterWithSessionAndSpeaker = adapter.getView(position - 1, convertView, parent);
                adjustViewHeightWhenSpecialSession(position, adapter, itemInAdapterWithSessionAndSpeaker);

                return itemInAdapterWithSessionAndSpeaker;
            }

            // otherwise jump into next section
            position -= size;
            sectionnum++;
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void adjustViewHeightWhenSpecialSession(int position, Adapter adapter, View itemInAdapterWithSessionAndSpeaker) {
        HashMap<String, String> x = (HashMap) adapter.getItem(position - 1);
        String session = x.get("session");
        if (identifier.sessionNameRequiresSpecialTreatment(session)) {
            ViewGroup.LayoutParams params = itemInAdapterWithSessionAndSpeaker.getLayoutParams();
            params.height = 35;
            itemInAdapterWithSessionAndSpeaker.setLayoutParams(params);
            itemInAdapterWithSessionAndSpeaker.requestLayout();
        }
    }

}
