package com.iowacodecamp.android.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.iowacodecamp.android.R;
import com.iowacodecamp.android.activity.SessionDetails;
import com.iowacodecamp.android.global.AppDelegate;
import com.iowacodecamp.android.global.DialogHelper;
import com.iowacodecamp.android.global.SeparatedListAdapter;
import com.iowacodecamp.entity.Session;
import com.iowacodecamp.entity.SpecialSessionIdentifier;
import com.iowacodecamp.service.SessionService;

import java.util.*;

public class GetSessionsAsyncTask extends AsyncTask<String, Void, List<Session>> {
    private Activity activity;
    private AppDelegate delegate;
    private SeparatedListAdapter adapter = null;
    private SpecialSessionIdentifier identifier;
    private ProgressDialog dialog;

    public GetSessionsAsyncTask(Activity activity, Context context) {
        this.activity = activity;
        this.delegate = (AppDelegate) context;
        this.identifier = new SpecialSessionIdentifier();
    }

    @Override
    protected void onPreExecute() {
        this.dialog = ProgressDialog.show(activity, "", "Loading...");
    }

    @Override
    protected List<Session> doInBackground(String... stuff) {
        return new SessionService().getListOfSessions();
    }

    @Override
    protected void onPostExecute(final List<Session> sessionsList) {
        this.dialog.dismiss();

        if (sessionsList == null || sessionsList.size() < 1) {
            DialogHelper.showDialogWithMessageAndTitleThatWillFinish("No network connection", "This application requires some form of internet connectivity to function", activity);
        } else {
            adapter = new SeparatedListAdapter(activity);

            pushSessionsIntoAdapter(sessionsList);

            ListView list = new ListView(activity);
            list.setAdapter(adapter);
            activity.setContentView(list);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Session s = (Session) adapter.getItem(i);

                    if (identifier.sessionNameRequiresSpecialTreatment(s.getSession())) {
                        return;
                    }

                    delegate.setSelectedSession(s);

                    Intent details = new Intent(activity, SessionDetails.class);
                    activity.startActivity(details);
                }
            }
            );
        }
    }

    private void pushSessionsIntoAdapter(List<Session> sessionsList) {
        String time = "";
        Map<String, String> sessionInfo = null;
        List<Session> attachedSessions = null;

        for (Session session : sessionsList) {
            if (time == "") {
                time = session.getTime();
                sessionInfo = new LinkedHashMap<String, String>();
                attachedSessions = new ArrayList<Session>();
                putSessionInfoIntoList(session, attachedSessions, sessionInfo);
            } else {
                if (session.getTime().equals(time)) {
                    putSessionInfoIntoList(session, attachedSessions, sessionInfo);
                } else {
                    addSessionInfoToAdapter(adapter, sessionInfo, time, attachedSessions);

                    sessionInfo = null;
                    attachedSessions = null;
                    sessionInfo = new LinkedHashMap<String, String>();
                    attachedSessions = new ArrayList<Session>();
                    putSessionInfoIntoList(session, attachedSessions, sessionInfo);

                    time = session.getTime();
                }
            }
        }

        addSessionInfoToAdapter(adapter, sessionInfo, time, attachedSessions);
    }

    private void putSessionInfoIntoList(Session session, List<Session> attachedSessions, Map<String, String> sessionInfo) {
        sessionInfo.put(session.getFormattedSession(), session.getSpeaker().getName());
        attachedSessions.add(session);
    }

    private void addSessionInfoToAdapter(SeparatedListAdapter adapter, Map<String, String> sessionInfo, String time, List<Session> attachedSessions) {

        List<Map<String, ?>> sessionData = new LinkedList<Map<String, ?>>();
        for (Map.Entry<String, String> entry : sessionInfo.entrySet()) {
            sessionData.add(createItemForComplexAdapter(entry.getKey(), entry.getValue()));
        }
        adapter.addSection(time, new SimpleAdapter(activity, sessionData, R.layout.list_complex, new String[]{"session", "speaker"}, new int[]{R.id.list_complex_title, R.id.list_complex_caption}), attachedSessions);
    }

    private Map<String, ?> createItemForComplexAdapter(String title, String caption) {
        Map<String, String> item = new HashMap<String, String>();
        item.put("session", title);
        item.put("speaker", caption);
        return item;
    }
}
