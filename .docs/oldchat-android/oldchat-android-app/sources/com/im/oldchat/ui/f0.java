package com.im.oldchat.ui;

import android.R;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import o.c;

/* JADX INFO: loaded from: classes.dex */
public abstract class f0 {

    public class a implements AdapterView.OnItemClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ List f4533a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ e f4534b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ o.c f4535c;

        public a(List list, e eVar, o.c cVar) {
            this.f4533a = list;
            this.f4534b = eVar;
            this.f4535c = cVar;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            if (i2 < 0 || i2 >= this.f4533a.size()) {
                return;
            }
            e eVar = this.f4534b;
            if (eVar != null) {
                eVar.a((j0.w) this.f4533a.get(i2));
            }
            this.f4535c.dismiss();
        }
    }

    public class b implements TextWatcher {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ List f4536a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ List f4537b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ ArrayAdapter f4538c;

        public b(List list, List list2, ArrayAdapter arrayAdapter) {
            this.f4536a = list;
            this.f4537b = list2;
            this.f4538c = arrayAdapter;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            f0.h(this.f4536a, this.f4537b, editable == null ? "" : editable.toString());
            this.f4538c.clear();
            this.f4538c.addAll(f0.e(this.f4537b));
            this.f4538c.notifyDataSetChanged();
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }
    }

    public class c implements AdapterView.OnItemClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ List f4539a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ f f4540b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ o.c f4541c;

        public c(List list, f fVar, o.c cVar) {
            this.f4539a = list;
            this.f4540b = fVar;
            this.f4541c = cVar;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            if (i2 < 0 || i2 >= this.f4539a.size()) {
                return;
            }
            f fVar = this.f4540b;
            if (fVar != null) {
                fVar.a((j0.e) this.f4539a.get(i2));
            }
            this.f4541c.dismiss();
        }
    }

    public class d implements TextWatcher {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ List f4542a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ List f4543b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ ArrayAdapter f4544c;

        public d(List list, List list2, ArrayAdapter arrayAdapter) {
            this.f4542a = list;
            this.f4543b = list2;
            this.f4544c = arrayAdapter;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            f0.i(this.f4542a, this.f4543b, editable == null ? "" : editable.toString());
            this.f4544c.clear();
            this.f4544c.addAll(f0.f(this.f4543b));
            this.f4544c.notifyDataSetChanged();
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }
    }

    public interface e {
        void a(j0.w wVar);
    }

    public interface f {
        void a(j0.e eVar);
    }

    public static List e(List list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            j0.w wVar = (j0.w) list.get(i2);
            if (wVar != null) {
                String strB = j0.d.b(wVar);
                if ((strB == null || strB.length() == 0) && (strB = wVar.f5646b) == null) {
                    strB = "";
                }
                String str = wVar.f5646b;
                String str2 = str != null ? str : "";
                if (str2.length() <= 0 || strB.indexOf(str2) >= 0) {
                    arrayList.add(strB);
                } else {
                    arrayList.add(strB + " (" + str2 + ")");
                }
            }
        }
        return arrayList;
    }

    public static List f(List list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            j0.e eVar = (j0.e) list.get(i2);
            if (eVar != null) {
                String strM = m(eVar.f5505b);
                String strM2 = m(eVar.f5504a);
                if (strM.length() == 0) {
                    strM = strM2;
                }
                if (strM2.length() <= 0 || strM.indexOf(strM2) >= 0) {
                    arrayList.add(strM);
                } else {
                    arrayList.add(strM + " (" + strM2 + ")");
                }
            }
        }
        return arrayList;
    }

    public static int g(Activity activity, int i2) {
        return (int) ((activity.getResources().getDisplayMetrics().density * i2) + 0.5f);
    }

    public static void h(List list, List list2, String str) {
        list2.clear();
        if (list == null || list.isEmpty()) {
            return;
        }
        String strJ = j(str);
        if (strJ.length() == 0) {
            list2.addAll(list);
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            j0.w wVar = (j0.w) list.get(i2);
            if (wVar != null) {
                String strJ2 = j(j0.d.b(wVar));
                String strJ3 = j(wVar.f5646b);
                String strJ4 = j(wVar.f5647c);
                String strJ5 = j(wVar.f5648d);
                String strJ6 = j(wVar.f5649e);
                if (strJ2.contains(strJ) || strJ3.contains(strJ) || strJ4.contains(strJ) || strJ5.contains(strJ) || strJ6.contains(strJ)) {
                    list2.add(wVar);
                }
            }
        }
    }

    public static void i(List list, List list2, String str) {
        list2.clear();
        if (list == null || list.isEmpty()) {
            return;
        }
        String strJ = j(str);
        if (strJ.length() == 0) {
            list2.addAll(list);
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            j0.e eVar = (j0.e) list.get(i2);
            if (eVar != null) {
                String strJ2 = j(eVar.f5505b);
                String strJ3 = j(eVar.f5504a);
                if (strJ2.contains(strJ) || strJ3.contains(strJ)) {
                    list2.add(eVar);
                }
            }
        }
    }

    public static String j(String str) {
        return str == null ? "" : str.trim().toLowerCase(Locale.getDefault());
    }

    public static void k(Activity activity, List list, String str, e eVar) {
        if (activity == null) {
            return;
        }
        if (list == null || list.isEmpty()) {
            Toast.makeText(activity, "暂无好友", 0).show();
            return;
        }
        ArrayList arrayList = new ArrayList(list);
        ArrayAdapter arrayAdapter = new ArrayAdapter(activity, R.layout.simple_list_item_1, e(arrayList));
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(1);
        int iG = g(activity, 12);
        linearLayout.setPadding(iG, g(activity, 6), iG, 0);
        EditText editText = new EditText(activity);
        editText.setSingleLine(true);
        editText.setHint("搜索好友昵称/UID");
        linearLayout.addView(editText, new LinearLayout.LayoutParams(-1, -2));
        ListView listView = new ListView(activity);
        listView.setAdapter((ListAdapter) arrayAdapter);
        linearLayout.addView(listView, new LinearLayout.LayoutParams(-1, g(activity, 320)));
        c.a aVar = new c.a(activity);
        if (str == null || str.length() == 0) {
            str = "选择好友";
        }
        o.c cVarA = aVar.t(str).u(linearLayout).k("取消", null).a();
        listView.setOnItemClickListener(new a(arrayList, eVar, cVarA));
        editText.addTextChangedListener(new b(list, arrayList, arrayAdapter));
        cVarA.show();
    }

    public static void l(Activity activity, List list, String str, f fVar) {
        if (activity == null) {
            return;
        }
        if (list == null || list.isEmpty()) {
            Toast.makeText(activity, "暂无群聊", 0).show();
            return;
        }
        ArrayList arrayList = new ArrayList(list);
        ArrayAdapter arrayAdapter = new ArrayAdapter(activity, R.layout.simple_list_item_1, f(arrayList));
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(1);
        int iG = g(activity, 12);
        linearLayout.setPadding(iG, g(activity, 6), iG, 0);
        EditText editText = new EditText(activity);
        editText.setSingleLine(true);
        editText.setHint("搜索群名/群号");
        linearLayout.addView(editText, new LinearLayout.LayoutParams(-1, -2));
        ListView listView = new ListView(activity);
        listView.setAdapter((ListAdapter) arrayAdapter);
        linearLayout.addView(listView, new LinearLayout.LayoutParams(-1, g(activity, 320)));
        c.a aVar = new c.a(activity);
        if (str == null || str.length() == 0) {
            str = "选择群聊";
        }
        o.c cVarA = aVar.t(str).u(linearLayout).k("取消", null).a();
        listView.setOnItemClickListener(new c(arrayList, fVar, cVarA));
        editText.addTextChangedListener(new d(list, arrayList, arrayAdapter));
        cVarA.show();
    }

    public static String m(String str) {
        return str == null ? "" : str.trim();
    }
}
