package o;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import k.AbstractC0285k0;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import p.AbstractC0460a;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TextView f6721A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public TextView f6722B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public View f6723C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public ListAdapter f6724D;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public int f6726F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public int f6727G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public int f6728H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public int f6729I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f6730J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public int f6731K;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public Handler f6733M;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f6735a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n f6736b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Window f6737c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CharSequence f6738d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f6739e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ListView f6740f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public View f6741g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f6742h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f6743i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f6744j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f6745k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f6746l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Button f6748n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public CharSequence f6749o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Message f6750p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Button f6751q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public CharSequence f6752r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Message f6753s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Button f6754t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public CharSequence f6755u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Message f6756v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public NestedScrollView f6757w;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Drawable f6759y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public ImageView f6760z;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f6747m = false;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f6758x = 0;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public int f6725E = -1;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public int f6732L = 0;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public final View.OnClickListener f6734N = new a();

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Message message;
            Message message2;
            Message message3;
            b bVar = b.this;
            Message messageObtain = (view != bVar.f6748n || (message3 = bVar.f6750p) == null) ? (view != bVar.f6751q || (message2 = bVar.f6753s) == null) ? (view != bVar.f6754t || (message = bVar.f6756v) == null) ? null : Message.obtain(message) : Message.obtain(message2) : Message.obtain(message3);
            if (messageObtain != null) {
                messageObtain.sendToTarget();
            }
            b bVar2 = b.this;
            bVar2.f6733M.obtainMessage(1, bVar2.f6736b).sendToTarget();
        }
    }

    /* JADX INFO: renamed from: o.b$b, reason: collision with other inner class name */
    public class C0067b implements NestedScrollView.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f6762a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f6763b;

        public C0067b(View view, View view2) {
            this.f6762a = view;
            this.f6763b = view2;
        }

        @Override // android.support.v4.widget.NestedScrollView.b
        public void a(NestedScrollView nestedScrollView, int i2, int i3, int i4, int i5) {
            b.e(nestedScrollView, this.f6762a, this.f6763b);
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f6765a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f6766b;

        public c(View view, View view2) {
            this.f6765a = view;
            this.f6766b = view2;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.e(b.this.f6757w, this.f6765a, this.f6766b);
        }
    }

    public class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f6771a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f6772b;

        public e(View view, View view2) {
            this.f6771a = view;
            this.f6772b = view2;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.e(b.this.f6740f, this.f6771a, this.f6772b);
        }
    }

    public static class f {

        /* JADX INFO: renamed from: A, reason: collision with root package name */
        public int f6774A;

        /* JADX INFO: renamed from: C, reason: collision with root package name */
        public boolean[] f6776C;

        /* JADX INFO: renamed from: D, reason: collision with root package name */
        public boolean f6777D;

        /* JADX INFO: renamed from: E, reason: collision with root package name */
        public boolean f6778E;

        /* JADX INFO: renamed from: G, reason: collision with root package name */
        public DialogInterface.OnMultiChoiceClickListener f6780G;

        /* JADX INFO: renamed from: H, reason: collision with root package name */
        public Cursor f6781H;

        /* JADX INFO: renamed from: I, reason: collision with root package name */
        public String f6782I;

        /* JADX INFO: renamed from: J, reason: collision with root package name */
        public String f6783J;

        /* JADX INFO: renamed from: K, reason: collision with root package name */
        public AdapterView.OnItemSelectedListener f6784K;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f6786a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final LayoutInflater f6787b;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Drawable f6789d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public CharSequence f6791f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public View f6792g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public CharSequence f6793h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public CharSequence f6794i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public DialogInterface.OnClickListener f6795j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public CharSequence f6796k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public DialogInterface.OnClickListener f6797l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public CharSequence f6798m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public DialogInterface.OnClickListener f6799n;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public DialogInterface.OnCancelListener f6801p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public DialogInterface.OnDismissListener f6802q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public DialogInterface.OnKeyListener f6803r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public CharSequence[] f6804s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public ListAdapter f6805t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public DialogInterface.OnClickListener f6806u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public int f6807v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public View f6808w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public int f6809x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public int f6810y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public int f6811z;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f6788c = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f6790e = 0;

        /* JADX INFO: renamed from: B, reason: collision with root package name */
        public boolean f6775B = false;

        /* JADX INFO: renamed from: F, reason: collision with root package name */
        public int f6779F = -1;

        /* JADX INFO: renamed from: L, reason: collision with root package name */
        public boolean f6785L = true;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public boolean f6800o = true;

        public class a extends ArrayAdapter {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ ListView f6812a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Context context, int i2, int i3, CharSequence[] charSequenceArr, ListView listView) {
                super(context, i2, i3, charSequenceArr);
                this.f6812a = listView;
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int i2, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i2, view, viewGroup);
                boolean[] zArr = f.this.f6776C;
                if (zArr != null && zArr[i2]) {
                    this.f6812a.setItemChecked(i2, true);
                }
                return view2;
            }
        }

        /* JADX INFO: renamed from: o.b$f$b, reason: collision with other inner class name */
        public class C0068b extends CursorAdapter {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final int f6814a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final int f6815b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ ListView f6816c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ b f6817d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0068b(Context context, Cursor cursor, boolean z2, ListView listView, b bVar) {
                super(context, cursor, z2);
                this.f6816c = listView;
                this.f6817d = bVar;
                Cursor cursor2 = getCursor();
                this.f6814a = cursor2.getColumnIndexOrThrow(f.this.f6782I);
                this.f6815b = cursor2.getColumnIndexOrThrow(f.this.f6783J);
            }

            @Override // android.widget.CursorAdapter
            public void bindView(View view, Context context, Cursor cursor) {
                ((CheckedTextView) view.findViewById(R.id.text1)).setText(cursor.getString(this.f6814a));
                this.f6816c.setItemChecked(cursor.getPosition(), cursor.getInt(this.f6815b) == 1);
            }

            @Override // android.widget.CursorAdapter
            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                return f.this.f6787b.inflate(this.f6817d.f6729I, viewGroup, false);
            }
        }

        public class c implements AdapterView.OnItemClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ b f6819a;

            public c(b bVar) {
                this.f6819a = bVar;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
                f.this.f6806u.onClick(this.f6819a.f6736b, i2);
                if (f.this.f6778E) {
                    return;
                }
                this.f6819a.f6736b.dismiss();
            }
        }

        public class d implements AdapterView.OnItemClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ ListView f6821a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ b f6822b;

            public d(ListView listView, b bVar) {
                this.f6821a = listView;
                this.f6822b = bVar;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
                boolean[] zArr = f.this.f6776C;
                if (zArr != null) {
                    zArr[i2] = this.f6821a.isItemChecked(i2);
                }
                f.this.f6780G.onClick(this.f6822b.f6736b, i2, this.f6821a.isItemChecked(i2));
            }
        }

        public f(Context context) {
            this.f6786a = context;
            this.f6787b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public void a(b bVar) {
            View view = this.f6792g;
            if (view != null) {
                bVar.k(view);
            } else {
                CharSequence charSequence = this.f6791f;
                if (charSequence != null) {
                    bVar.p(charSequence);
                }
                Drawable drawable = this.f6789d;
                if (drawable != null) {
                    bVar.m(drawable);
                }
                int i2 = this.f6788c;
                if (i2 != 0) {
                    bVar.l(i2);
                }
                int i3 = this.f6790e;
                if (i3 != 0) {
                    bVar.l(bVar.c(i3));
                }
            }
            CharSequence charSequence2 = this.f6793h;
            if (charSequence2 != null) {
                bVar.n(charSequence2);
            }
            CharSequence charSequence3 = this.f6794i;
            if (charSequence3 != null) {
                bVar.j(-1, charSequence3, this.f6795j, null);
            }
            CharSequence charSequence4 = this.f6796k;
            if (charSequence4 != null) {
                bVar.j(-2, charSequence4, this.f6797l, null);
            }
            CharSequence charSequence5 = this.f6798m;
            if (charSequence5 != null) {
                bVar.j(-3, charSequence5, this.f6799n, null);
            }
            if (this.f6804s != null || this.f6781H != null || this.f6805t != null) {
                b(bVar);
            }
            View view2 = this.f6808w;
            if (view2 != null) {
                if (this.f6775B) {
                    bVar.s(view2, this.f6809x, this.f6810y, this.f6811z, this.f6774A);
                    return;
                } else {
                    bVar.r(view2);
                    return;
                }
            }
            int i4 = this.f6807v;
            if (i4 != 0) {
                bVar.q(i4);
            }
        }

        public final void b(b bVar) {
            ListAdapter hVar;
            ListView listView = (ListView) this.f6787b.inflate(bVar.f6728H, (ViewGroup) null);
            if (this.f6777D) {
                hVar = this.f6781H == null ? new a(this.f6786a, bVar.f6729I, R.id.text1, this.f6804s, listView) : new C0068b(this.f6786a, this.f6781H, false, listView, bVar);
            } else {
                int i2 = this.f6778E ? bVar.f6730J : bVar.f6731K;
                if (this.f6781H != null) {
                    hVar = new SimpleCursorAdapter(this.f6786a, i2, this.f6781H, new String[]{this.f6782I}, new int[]{R.id.text1});
                } else {
                    hVar = this.f6805t;
                    if (hVar == null) {
                        hVar = new h(this.f6786a, i2, R.id.text1, this.f6804s);
                    }
                }
            }
            bVar.f6724D = hVar;
            bVar.f6725E = this.f6779F;
            if (this.f6806u != null) {
                listView.setOnItemClickListener(new c(bVar));
            } else if (this.f6780G != null) {
                listView.setOnItemClickListener(new d(listView, bVar));
            }
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.f6784K;
            if (onItemSelectedListener != null) {
                listView.setOnItemSelectedListener(onItemSelectedListener);
            }
            if (this.f6778E) {
                listView.setChoiceMode(1);
            } else if (this.f6777D) {
                listView.setChoiceMode(2);
            }
            bVar.f6740f = listView;
        }
    }

    public static final class g extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference f6824a;

        public g(DialogInterface dialogInterface) {
            this.f6824a = new WeakReference(dialogInterface);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == -3 || i2 == -2 || i2 == -1) {
                ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) this.f6824a.get(), message.what);
            } else {
                if (i2 != 1) {
                    return;
                }
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    public static class h extends ArrayAdapter {
        public h(Context context, int i2, int i3, CharSequence[] charSequenceArr) {
            super(context, i2, i3, charSequenceArr);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    public b(Context context, n nVar, Window window) {
        this.f6735a = context;
        this.f6736b = nVar;
        this.f6737c = window;
        this.f6733M = new g(nVar);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, p.j.f7841F, AbstractC0460a.f7716j, 0);
        this.f6726F = typedArrayObtainStyledAttributes.getResourceId(p.j.f7842G, 0);
        this.f6727G = typedArrayObtainStyledAttributes.getResourceId(p.j.f7843H, 0);
        this.f6728H = typedArrayObtainStyledAttributes.getResourceId(p.j.f7845J, 0);
        this.f6729I = typedArrayObtainStyledAttributes.getResourceId(p.j.f7846K, 0);
        this.f6730J = typedArrayObtainStyledAttributes.getResourceId(p.j.f7847L, 0);
        this.f6731K = typedArrayObtainStyledAttributes.getResourceId(p.j.f7844I, 0);
        typedArrayObtainStyledAttributes.recycle();
        nVar.c(1);
    }

    public static boolean a(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (a(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    public static void e(View view, View view2, View view3) {
        if (view2 != null) {
            view2.setVisibility(AbstractC0285k0.c(view, -1) ? 0 : 4);
        }
        if (view3 != null) {
            view3.setVisibility(AbstractC0285k0.c(view, 1) ? 0 : 4);
        }
    }

    public Button b(int i2) {
        if (i2 == -3) {
            return this.f6754t;
        }
        if (i2 == -2) {
            return this.f6751q;
        }
        if (i2 != -1) {
            return null;
        }
        return this.f6748n;
    }

    public int c(int i2) {
        TypedValue typedValue = new TypedValue();
        this.f6735a.getTheme().resolveAttribute(i2, typedValue, true);
        return typedValue.resourceId;
    }

    public void d() {
        this.f6736b.setContentView(i());
        x();
    }

    public boolean f(int i2, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f6757w;
        return nestedScrollView != null && nestedScrollView.h(keyEvent);
    }

    public boolean g(int i2, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f6757w;
        return nestedScrollView != null && nestedScrollView.h(keyEvent);
    }

    public final ViewGroup h(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    public final int i() {
        int i2 = this.f6727G;
        return i2 == 0 ? this.f6726F : this.f6732L == 1 ? i2 : this.f6726F;
    }

    public void j(int i2, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message) {
        if (message == null && onClickListener != null) {
            message = this.f6733M.obtainMessage(i2, onClickListener);
        }
        if (i2 == -3) {
            this.f6755u = charSequence;
            this.f6756v = message;
        } else if (i2 == -2) {
            this.f6752r = charSequence;
            this.f6753s = message;
        } else {
            if (i2 != -1) {
                throw new IllegalArgumentException("Button does not exist");
            }
            this.f6749o = charSequence;
            this.f6750p = message;
        }
    }

    public void k(View view) {
        this.f6723C = view;
    }

    public void l(int i2) {
        this.f6759y = null;
        this.f6758x = i2;
        ImageView imageView = this.f6760z;
        if (imageView != null) {
            if (i2 == 0) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                this.f6760z.setImageResource(this.f6758x);
            }
        }
    }

    public void m(Drawable drawable) {
        this.f6759y = drawable;
        this.f6758x = 0;
        ImageView imageView = this.f6760z;
        if (imageView != null) {
            if (drawable == null) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                this.f6760z.setImageDrawable(drawable);
            }
        }
    }

    public void n(CharSequence charSequence) {
        this.f6739e = charSequence;
        TextView textView = this.f6722B;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public final void o(ViewGroup viewGroup, View view, int i2, int i3) {
        View viewFindViewById = this.f6737c.findViewById(p.f.f7808r);
        View viewFindViewById2 = this.f6737c.findViewById(p.f.f7807q);
        if (Build.VERSION.SDK_INT >= 23) {
            AbstractC0285k0.V(view, i2, i3);
            if (viewFindViewById != null) {
                viewGroup.removeView(viewFindViewById);
            }
            if (viewFindViewById2 != null) {
                viewGroup.removeView(viewFindViewById2);
                return;
            }
            return;
        }
        if (viewFindViewById != null && (i2 & 1) == 0) {
            viewGroup.removeView(viewFindViewById);
            viewFindViewById = null;
        }
        if (viewFindViewById2 != null && (i2 & 2) == 0) {
            viewGroup.removeView(viewFindViewById2);
            viewFindViewById2 = null;
        }
        if (viewFindViewById == null && viewFindViewById2 == null) {
            return;
        }
        if (this.f6739e != null) {
            this.f6757w.setOnScrollChangeListener(new C0067b(viewFindViewById, viewFindViewById2));
            this.f6757w.post(new c(viewFindViewById, viewFindViewById2));
            return;
        }
        ListView listView = this.f6740f;
        if (listView != null) {
            listView.setOnScrollListener(new d(viewFindViewById, viewFindViewById2));
            this.f6740f.post(new e(viewFindViewById, viewFindViewById2));
            return;
        }
        if (viewFindViewById != null) {
            viewGroup.removeView(viewFindViewById);
        }
        if (viewFindViewById2 != null) {
            viewGroup.removeView(viewFindViewById2);
        }
    }

    public void p(CharSequence charSequence) {
        this.f6738d = charSequence;
        TextView textView = this.f6721A;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void q(int i2) {
        this.f6741g = null;
        this.f6742h = i2;
        this.f6747m = false;
    }

    public void r(View view) {
        this.f6741g = view;
        this.f6742h = 0;
        this.f6747m = false;
    }

    public void s(View view, int i2, int i3, int i4, int i5) {
        this.f6741g = view;
        this.f6742h = 0;
        this.f6747m = true;
        this.f6743i = i2;
        this.f6744j = i3;
        this.f6745k = i4;
        this.f6746l = i5;
    }

    public final void t(ViewGroup viewGroup) {
        int i2;
        Button button = (Button) viewGroup.findViewById(R.id.button1);
        this.f6748n = button;
        button.setOnClickListener(this.f6734N);
        if (TextUtils.isEmpty(this.f6749o)) {
            this.f6748n.setVisibility(8);
            i2 = 0;
        } else {
            this.f6748n.setText(this.f6749o);
            this.f6748n.setVisibility(0);
            i2 = 1;
        }
        Button button2 = (Button) viewGroup.findViewById(R.id.button2);
        this.f6751q = button2;
        button2.setOnClickListener(this.f6734N);
        if (TextUtils.isEmpty(this.f6752r)) {
            this.f6751q.setVisibility(8);
        } else {
            this.f6751q.setText(this.f6752r);
            this.f6751q.setVisibility(0);
            i2 |= 2;
        }
        Button button3 = (Button) viewGroup.findViewById(R.id.button3);
        this.f6754t = button3;
        button3.setOnClickListener(this.f6734N);
        if (TextUtils.isEmpty(this.f6755u)) {
            this.f6754t.setVisibility(8);
        } else {
            this.f6754t.setText(this.f6755u);
            this.f6754t.setVisibility(0);
            i2 |= 4;
        }
        if (i2 != 0) {
            return;
        }
        viewGroup.setVisibility(8);
    }

    public final void u(ViewGroup viewGroup) {
        NestedScrollView nestedScrollView = (NestedScrollView) this.f6737c.findViewById(p.f.f7809s);
        this.f6757w = nestedScrollView;
        nestedScrollView.setFocusable(false);
        this.f6757w.setNestedScrollingEnabled(false);
        TextView textView = (TextView) viewGroup.findViewById(R.id.message);
        this.f6722B = textView;
        if (textView == null) {
            return;
        }
        CharSequence charSequence = this.f6739e;
        if (charSequence != null) {
            textView.setText(charSequence);
            return;
        }
        textView.setVisibility(8);
        this.f6757w.removeView(this.f6722B);
        if (this.f6740f == null) {
            viewGroup.setVisibility(8);
            return;
        }
        ViewGroup viewGroup2 = (ViewGroup) this.f6757w.getParent();
        int iIndexOfChild = viewGroup2.indexOfChild(this.f6757w);
        viewGroup2.removeViewAt(iIndexOfChild);
        viewGroup2.addView(this.f6740f, iIndexOfChild, new ViewGroup.LayoutParams(-1, -1));
    }

    public final void v(ViewGroup viewGroup) {
        View viewInflate = this.f6741g;
        if (viewInflate == null) {
            viewInflate = this.f6742h != 0 ? LayoutInflater.from(this.f6735a).inflate(this.f6742h, viewGroup, false) : null;
        }
        boolean z2 = viewInflate != null;
        if (!z2 || !a(viewInflate)) {
            this.f6737c.setFlags(PKIFailureInfo.unsupportedVersion, PKIFailureInfo.unsupportedVersion);
        }
        if (!z2) {
            viewGroup.setVisibility(8);
            return;
        }
        FrameLayout frameLayout = (FrameLayout) this.f6737c.findViewById(p.f.f7803m);
        frameLayout.addView(viewInflate, new ViewGroup.LayoutParams(-1, -1));
        if (this.f6747m) {
            frameLayout.setPadding(this.f6743i, this.f6744j, this.f6745k, this.f6746l);
        }
        if (this.f6740f != null) {
            ((LinearLayout.LayoutParams) viewGroup.getLayoutParams()).weight = 0.0f;
        }
    }

    public final void w(ViewGroup viewGroup) {
        if (this.f6723C != null) {
            viewGroup.addView(this.f6723C, 0, new ViewGroup.LayoutParams(-1, -2));
            this.f6737c.findViewById(p.f.f7816z).setVisibility(8);
            return;
        }
        this.f6760z = (ImageView) this.f6737c.findViewById(R.id.icon);
        if (TextUtils.isEmpty(this.f6738d)) {
            this.f6737c.findViewById(p.f.f7816z).setVisibility(8);
            this.f6760z.setVisibility(8);
            viewGroup.setVisibility(8);
            return;
        }
        TextView textView = (TextView) this.f6737c.findViewById(p.f.f7800j);
        this.f6721A = textView;
        textView.setText(this.f6738d);
        int i2 = this.f6758x;
        if (i2 != 0) {
            this.f6760z.setImageResource(i2);
            return;
        }
        Drawable drawable = this.f6759y;
        if (drawable != null) {
            this.f6760z.setImageDrawable(drawable);
        } else {
            this.f6721A.setPadding(this.f6760z.getPaddingLeft(), this.f6760z.getPaddingTop(), this.f6760z.getPaddingRight(), this.f6760z.getPaddingBottom());
            this.f6760z.setVisibility(8);
        }
    }

    public final void x() {
        ListAdapter listAdapter;
        NestedScrollView nestedScrollView;
        View viewFindViewById;
        View viewFindViewById2 = this.f6737c.findViewById(p.f.f7806p);
        View viewFindViewById3 = viewFindViewById2.findViewById(p.f.f7790A);
        View viewFindViewById4 = viewFindViewById2.findViewById(p.f.f7802l);
        View viewFindViewById5 = viewFindViewById2.findViewById(p.f.f7801k);
        ViewGroup viewGroup = (ViewGroup) viewFindViewById2.findViewById(p.f.f7804n);
        v(viewGroup);
        View viewFindViewById6 = viewGroup.findViewById(p.f.f7790A);
        View viewFindViewById7 = viewGroup.findViewById(p.f.f7802l);
        View viewFindViewById8 = viewGroup.findViewById(p.f.f7801k);
        ViewGroup viewGroupH = h(viewFindViewById6, viewFindViewById3);
        ViewGroup viewGroupH2 = h(viewFindViewById7, viewFindViewById4);
        ViewGroup viewGroupH3 = h(viewFindViewById8, viewFindViewById5);
        u(viewGroupH2);
        t(viewGroupH3);
        w(viewGroupH);
        boolean z2 = viewGroup.getVisibility() != 8;
        int i2 = (viewGroupH == null || viewGroupH.getVisibility() == 8) ? 0 : 1;
        boolean z3 = (viewGroupH3 == null || viewGroupH3.getVisibility() == 8) ? false : true;
        if (!z3 && viewGroupH2 != null && (viewFindViewById = viewGroupH2.findViewById(p.f.f7814x)) != null) {
            viewFindViewById.setVisibility(0);
        }
        if (i2 != 0 && (nestedScrollView = this.f6757w) != null) {
            nestedScrollView.setClipToPadding(true);
        }
        if (!z2) {
            View view = this.f6740f;
            if (view == null) {
                view = this.f6757w;
            }
            if (view != null) {
                o(viewGroupH2, view, i2 | (z3 ? 2 : 0), 3);
            }
        }
        ListView listView = this.f6740f;
        if (listView == null || (listAdapter = this.f6724D) == null) {
            return;
        }
        listView.setAdapter(listAdapter);
        int i3 = this.f6725E;
        if (i3 > -1) {
            listView.setItemChecked(i3, true);
            listView.setSelection(i3);
        }
    }

    public class d implements AbsListView.OnScrollListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f6768a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f6769b;

        public d(View view, View view2) {
            this.f6768a = view;
            this.f6769b = view2;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            b.e(absListView, this.f6768a, this.f6769b);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
        }
    }
}
