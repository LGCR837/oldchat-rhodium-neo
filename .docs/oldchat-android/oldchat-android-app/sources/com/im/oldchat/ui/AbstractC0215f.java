package com.im.oldchat.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputFilter;
import android.widget.EditText;
import android.widget.Toast;
import com.im.oldchat.ui.AbstractC0227s;
import java.util.ArrayList;
import java.util.List;
import o.c;

/* JADX INFO: renamed from: com.im.oldchat.ui.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0215f {

    /* JADX INFO: renamed from: com.im.oldchat.ui.f$a */
    public class a implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f4518a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ AbstractC0227s.c f4519b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ List f4520c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ List f4521d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Runnable f4522e;

        public a(Activity activity, AbstractC0227s.c cVar, List list, List list2, Runnable runnable) {
            this.f4518a = activity;
            this.f4519b = cVar;
            this.f4520c = list;
            this.f4521d = list2;
            this.f4522e = runnable;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (i2 == 0) {
                AbstractC0215f.d(this.f4518a, this.f4519b, this.f4520c, this.f4521d, this.f4522e);
                return;
            }
            if (i2 != 1) {
                this.f4518a.startActivity(new Intent(this.f4518a, (Class<?>) EmojiPickerActivity.class));
                return;
            }
            if (!AbstractC0227s.p(this.f4518a, this.f4519b)) {
                Toast.makeText(this.f4518a, "删除失败", 0).show();
                return;
            }
            Runnable runnable = this.f4522e;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.f$b */
    public class b implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ArrayList f4523a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Activity f4524b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ AbstractC0227s.c f4525c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ List f4526d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Runnable f4527e;

        public b(ArrayList arrayList, Activity activity, AbstractC0227s.c cVar, List list, Runnable runnable) {
            this.f4523a = arrayList;
            this.f4524b = activity;
            this.f4525c = cVar;
            this.f4526d = list;
            this.f4527e = runnable;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String str = (String) this.f4523a.get(i2);
            if ("＋ 新建分类".equals(str)) {
                AbstractC0215f.c(this.f4524b, this.f4525c, this.f4526d, this.f4527e);
                return;
            }
            this.f4525c.f4747e = AbstractC0227s.x(str);
            AbstractC0227s.E(this.f4524b, this.f4526d);
            Runnable runnable = this.f4527e;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.f$c */
    public class c implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f4528a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ AbstractC0227s.c f4529b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Activity f4530c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ List f4531d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Runnable f4532e;

        public c(EditText editText, AbstractC0227s.c cVar, Activity activity, List list, Runnable runnable) {
            this.f4528a = editText;
            this.f4529b = cVar;
            this.f4530c = activity;
            this.f4531d = list;
            this.f4532e = runnable;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            this.f4529b.f4747e = AbstractC0227s.x(this.f4528a.getText() == null ? "" : this.f4528a.getText().toString());
            AbstractC0227s.E(this.f4530c, this.f4531d);
            Runnable runnable = this.f4532e;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public static void c(Activity activity, AbstractC0227s.c cVar, List list, Runnable runnable) {
        EditText editText = new EditText(activity);
        editText.setHint("输入分类名（最多10字）");
        editText.setSingleLine();
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        new c.a(activity).t("新建分类").u(editText).k("取消", null).q("确定", new c(editText, cVar, activity, list, runnable)).v();
    }

    public static void d(Activity activity, AbstractC0227s.c cVar, List list, List list2, Runnable runnable) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            String str = (String) list.get(i2);
            if (!"全部".equals(str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add("未分类");
        }
        arrayList.add("＋ 新建分类");
        new c.a(activity).t("移动到分类").g((CharSequence[]) arrayList.toArray(new CharSequence[arrayList.size()]), new b(arrayList, activity, cVar, list2, runnable)).k("取消", null).v();
    }

    public static void e(Activity activity, AbstractC0227s.c cVar, List list, List list2, Runnable runnable) {
        if (activity == null || cVar == null) {
            return;
        }
        new c.a(activity).t("管理表情").g(new CharSequence[]{"移动到分类", "删除表情", "进入多选"}, new a(activity, cVar, list, list2, runnable)).v();
    }
}
